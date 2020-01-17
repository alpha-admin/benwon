package com.benwon.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 409 表示請求與伺服器目前狀態衝突
@ResponseStatus(code = HttpStatus.CONFLICT)
public class BenwonConflictException extends BenwonRuntimeException {

	public BenwonConflictException(String message) {
		super(message);
	}

}
