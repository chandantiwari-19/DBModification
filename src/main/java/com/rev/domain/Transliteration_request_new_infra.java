package com.rev.domain;

import java.sql.Timestamp;


public class Transliteration_request_new_infra {
	private String appId;
	private String apiKey;
	private String outString;
	private String inString;
	private int domain;
	private int payloadDataLength;
	private int tokenCount;
	private String target_lang;
	private String source_lang;
	private String content_lang;
	private Timestamp timestamp;

	public Transliteration_request_new_infra() {
		super();
	}

	public Transliteration_request_new_infra(String appId, String apiKey, String outString, String inString, int domain,
			int payloadDataLength, int tokenCount, String target_lang, String source_lang, String content_lang,
			Timestamp timestamp) {
		super();
		this.appId = appId;
		this.apiKey = apiKey;
		this.outString = outString;
		this.inString = inString;
		this.domain = domain;
		this.payloadDataLength = payloadDataLength;
		this.tokenCount = tokenCount;
		this.target_lang = target_lang;
		this.source_lang = source_lang;
		this.content_lang = content_lang;
		this.timestamp = timestamp;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getOutString() {
		return outString;
	}

	public void setOutString(String outString) {
		this.outString = outString;
	}

	public String getInString() {
		return inString;
	}

	public void setInString(String inString) {
		this.inString = inString;
	}

	public int getDomain() {
		return domain;
	}

	public void setDomain(int domain) {
		this.domain = domain;
	}

	public int getPayloadDataLength() {
		return payloadDataLength;
	}

	public void setPayloadDataLength(int payloadDataLength) {
		this.payloadDataLength = payloadDataLength;
	}

	public int getTokenCount() {
		return tokenCount;
	}

	public void setTokenCount(int tokenCount) {
		this.tokenCount = tokenCount;
	}

	public String getTarget_lang() {
		return target_lang;
	}

	public void setTarget_lang(String target_lang) {
		this.target_lang = target_lang;
	}

	public String getSource_lang() {
		return source_lang;
	}

	public void setSource_lang(String source_lang) {
		this.source_lang = source_lang;
	}

	public String getContent_lang() {
		return content_lang;
	}

	public void setContent_lang(String content_lang) {
		this.content_lang = content_lang;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Transliteration_request_new_infra [appId=" + appId + ", apiKey=" + apiKey + ", outString=" + outString
				+ ", inString=" + inString + ", domain=" + domain + ", payloadDataLength=" + payloadDataLength
				+ ", tokenCount=" + tokenCount + ", target_lang=" + target_lang + ", source_lang=" + source_lang
				+ ", content_lang=" + content_lang + ", timestamp=" + timestamp + "]";
	}
	
}
