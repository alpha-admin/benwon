package com.benwon.shop.controller.rest;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benwon.shop.dto.MeDto;
import com.benwon.shop.dto.SaleOrderDto;
import com.benwon.shop.entity.UserProfile;
import com.benwon.shop.service.MeService;
import com.benwon.shop.service.SecurityService;
import com.benwon.shop.service.TokenService;
import com.benwon.shop.service.UserProfileService;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class MeRestController {

	private final UserProfileService userProfileService;
	private final SecurityService securityService;
	private final TokenService tokenService;
	private final MeService meService;

	@GetMapping(path = "/v1/me")
	public ResponseEntity<MeDto> getInfo(@RequestHeader("Authorization") String authorization) {
		MeDto meDto = null;
		ModelMapper modelMapper = new ModelMapper();
		log.debug("authorization={}", authorization);
		String uid = tokenService.verify(authorization);
		if (!StringUtils.isEmpty(uid)) {
			Optional<UserProfile> userProfileOptional = userProfileService.findById(uid);
			log.debug("userProfileOptional={}", userProfileOptional);
			if (userProfileOptional.isPresent()) {
				meDto = modelMapper.map(userProfileOptional.get(), MeDto.class);
			}
			return new ResponseEntity<MeDto>(meDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<MeDto>(meDto, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/v1/me/saleOrders/all")
	public ResponseEntity<List<SaleOrderDto>> getSaleOrders(@RequestHeader("Authorization") String authorization) {
		MeDto meDto = null;
		ModelMapper modelMapper = new ModelMapper();
		List<SaleOrderDto> saleOrderDtos = null;
		String uid = tokenService.verify(authorization);
		if (!StringUtils.isEmpty(uid)) {
			saleOrderDtos = meService.getAllSaleOrder(uid);
			return new ResponseEntity<List<SaleOrderDto>>(saleOrderDtos, HttpStatus.OK);
		}else{
			return new ResponseEntity<List<SaleOrderDto>>(saleOrderDtos, HttpStatus.UNAUTHORIZED);
		}
	}

}
