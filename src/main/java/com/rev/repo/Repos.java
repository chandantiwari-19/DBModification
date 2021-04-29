package com.rev.repo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rev.domain.Transliteration_request_new_infra;

@Repository
public class Repos {
	
	private static final ExecutorService executor = Executors.newFixedThreadPool(10);
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<Transliteration_request_new_infra> findTrans(String inString) {
		List<Map<String, Object>> rows= 
				jdbcTemplate.queryForList
				("SELECT DISTINCT apiKey, inString, source_lang FROM laas_parabola.transliteration_request_new_infra "
						+ "WHERE inString = \'3.2 IT Forms Upload (Offline)\'");
		List<Transliteration_request_new_infra> result = Collections.synchronizedList(new ArrayList<Transliteration_request_new_infra>());
		/*for(Map<String, Object> row:rows){
			Transliteration_request_new_infra rec = new Transliteration_request_new_infra();
			rec.setApiKey((String)row.get("apiKey"));
			rec.setInString((String)row.get("inString"));
			rec.setTarget_lang((String)row.get("source_lang"));
			result.add(rec);
		}*/
		
		rows.parallelStream().forEach(row-> {
			Transliteration_request_new_infra rec = new Transliteration_request_new_infra();
			rec.setApiKey((String)row.get("apiKey"));
			rec.setInString((String)row.get("inString"));
			rec.setTarget_lang((String)row.get("source_lang"));
			result.add(rec);
		});
		return result;
		}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void batchUpdateAsync(final List<Transliteration_request_new_infra> rec) throws InterruptedException, ExecutionException {
		
		 final AtomicInteger sublists = new AtomicInteger();
		
		String sql= "UPDATE transliteration_request_new_infra set "
				+ "payloadDataLength = ?, tokenCount = ? where inString= ?";
		
		CompletableFuture[] futures = rec.stream()
		        .collect(Collectors.groupingBy(t -> sublists.getAndIncrement() / 100))// 100 BatchSize
		        .values()
		        .stream()
		        .map(ul -> runBatchInsert(ul, sql))
		        .toArray(CompletableFuture[]::new);
		
	    CompletableFuture<Void> run = CompletableFuture.allOf(futures);
	    
	    run.get();
		
    }
	
	public CompletableFuture<Void> runBatchInsert(List<Transliteration_request_new_infra> rec, String sql) {
	    return CompletableFuture.runAsync(() -> {
	      jdbcTemplate.batchUpdate(sql, new UserBatchPreparedStatementSetter(rec));
	    }, executor);
	  }

}
