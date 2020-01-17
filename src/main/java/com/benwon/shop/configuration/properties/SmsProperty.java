package com.benwon.shop.configuration.properties;

import lombok.Data;

@Data
public class SmsProperty {
	private Integer retryWaitSecond;
	private Integer retryIntervalMinute;
	private Integer retryIntervalLimit;
	private String url;
	private String uid;
	private String pwd;
	private String sb;
	private String msg;
}
