package com.benwon.shop.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.benwon.shop.bo.SalePortalUpdateBo;
import com.benwon.shop.service.SalePortalService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class SalePortalRestController {
	private final SalePortalService salePortalService;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/v1/SalePortals")
    public void update(@RequestBody SalePortalUpdateBo salePortalUpdateBo) {
        log.debug("SalePortalUpdateBo=" + salePortalUpdateBo);
        salePortalService.update(salePortalUpdateBo);
    }
}

