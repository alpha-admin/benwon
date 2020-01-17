package com.benwon.shop.controller.rest;

import com.benwon.shop.bo.SaleGroupBo;
import com.benwon.shop.entity.SaleGroup;
import com.benwon.shop.service.SaleGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class SaleGroupRestController {
    private final SaleGroupService saleGroupService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/v1/saleGroup")
    public SaleGroup create(@RequestBody SaleGroupBo saleGroupBo) {

        SaleGroup saleGroup = saleGroupService.create(saleGroupBo);
        return saleGroup;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/v1/saleGroup/{id}")
    public SaleGroupBo getOne(
            @PathVariable("id") String id) {
        SaleGroupBo saleGroupBo = saleGroupService.getOne(id);
        return saleGroupBo;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/v1/saleGroup/{id}")
    public SaleGroup update(
            @PathVariable("id") String id,
            @RequestBody SaleGroupBo saleGroupBo) {
        SaleGroup saleGroup = saleGroupService.update(id, saleGroupBo);
        return saleGroup;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/v1/saleGroup/{id}")
    public void delete(
            @PathVariable("id") String id) {
        saleGroupService.delete(id);
    }


}
