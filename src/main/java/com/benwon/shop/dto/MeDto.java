package com.benwon.shop.dto;

import lombok.Data;

@Data
public class MeDto {
	private String id;
	private String realName;
	private String nickName;
	private String picture;
	private String lineId;
	private String lineUserId;
	private String lineUserName;
	private String mobilePhone;
	private String email;
	private String county;
	private String district;
	private String zipcode;
	private String address;
	private Integer finished;
}
