package com.benwon.shop.bo;

import lombok.Data;

@Data
public class PhoneVerfyCodeEvent {
	private String smsId;
	private String verifyCode;
}
