package com.rev.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rev.domain.Transliteration_request_new_infra;
import com.rev.repo.Repos;

@Component
public class JdbcOp {

	@Autowired
	private Repos repos;
	

    public void runjdbc() throws InterruptedException, ExecutionException {
    	System.out.println("App Started !!!");
    	List<Transliteration_request_new_infra> rec = Collections.synchronizedList(new ArrayList<Transliteration_request_new_infra>());
        rec =repos.findTrans("Day");
        rec.parallelStream().forEach(row-> {
        	if(row.getInString()!=null) {
        		row.setTokenCount(countTokens(row.getInString()));
        		row.setPayloadDataLength(row.getInString().length());
        	}
        });
        rec.parallelStream().forEach(row-> System.out.println(row.getApiKey()+ "\t" + row.getInString()+ 
        		"\t" + row.getPayloadDataLength() + "\t" + row.getTokenCount() + "\t" + row.getTarget_lang()));
        System.out.println(rec.size());
       repos.batchUpdateAsync(rec);
        System.out.println("Operation Finished !!!");
    }
    
    public static int countTokens(String s1) {
		s1 = preprocess(s1);
		char array1[] = s1.toCharArray();
		int i = 0, countWords = 0;
		while (i < s1.length()) {
			if (array1[i] == ' ') {
				countWords++;
			}
			i++;
		}
		return (countWords + 1);
	}
	
	private static String preprocess(String s1){
		s1 = s1.replaceAll("\n", " ");
		s1 = s1.replaceAll("\t", " ");
		s1 = s1.replaceAll("~", " ");
		s1 = s1.replaceAll("\\|", " ");
		s1 = s1.replaceAll("\\.", " ");
		s1 = s1.replaceAll(",", " ");
		s1 = s1.trim().replaceAll("\\s+", " ");
		return s1;
	}

}
