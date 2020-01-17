package com.benwon.shop.controller.page.admin;

import com.benwon.shop.common.contract.SystemVars;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class AdminProductPageController {


    @GetMapping(value = {"/admin/productList.html"})
    public String list(Model model) {
        try {
            model.addAttribute("contentPage", "admin/product/content-productList");
        } catch (Exception e) {
            log.error("解析授權資料失敗 ", e);
        }
        return SystemVars.adminLayoutTemplate;
    }

    @GetMapping(value = {"/admin/productCreate.html"})
    public String create(Model model) {
        try {
            model.addAttribute("contentPage", "admin/product/content-productCreate");
        } catch (Exception e) {
            log.error("解析授權資料失敗 ", e);
        }
        return SystemVars.adminLayoutTemplate;
    }

    @GetMapping(value = {"/admin/productEdit.html"})
    public String edit(Model model) {
        try {
            model.addAttribute("contentPage", "admin/product/content-productEdit");
        } catch (Exception e) {
            log.error("解析授權資料失敗 ", e);
        }
        return SystemVars.adminLayoutTemplate;
    }
}
