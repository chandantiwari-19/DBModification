package com.rev.repo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.rev.domain.Transliteration_request_new_infra;

public class UserBatchPreparedStatementSetter implements BatchPreparedStatementSetter{

	List<Transliteration_request_new_infra> rec= null;
	public UserBatchPreparedStatementSetter(List<Transliteration_request_new_infra> rec) {
		this.rec=rec;
	}

	@Override
	public void setValues(PreparedStatement ps, int i) throws SQLException {
		ps.setLong(1, rec.get(i).getPayloadDataLength());
		ps.setLong(2, rec.get(i).getTokenCount());
		ps.setString(3, rec.get(i).getInString());		
	}

	@Override
	public int getBatchSize() {
		return rec.size();
	}
}
