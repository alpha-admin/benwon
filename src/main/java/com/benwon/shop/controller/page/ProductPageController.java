package com.benwon.shop.controller.page;

import com.benwon.shop.bo.ProductBo;
import com.benwon.shop.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductPageController {

    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @GetMapping(value = {"/product/{productid}.html"})
    public String unShipList(@PathVariable("productid") String productid, Model model) {
        ProductBo productBo = productService.getDetail(productid);
        try {
            model.addAttribute("product", productBo);
            model.addAttribute("productId", productBo.getId());
            model.addAttribute("productName", productBo.getProductName());
            model.addAttribute("discPrice", productBo.getDiscPrice());
            model.addAttribute("expiryDate", productBo.getExpiryDate());
            model.addAttribute("productItemList", objectMapper.writeValueAsString(productBo.getProductItemList()));
        } catch (Exception e) {
            log.error("解析授權資料失敗 ", e);
        }
        return "product";
    }
}
