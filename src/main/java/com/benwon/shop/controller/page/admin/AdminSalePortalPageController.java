package com.benwon.shop.controller.page.admin;

import com.benwon.shop.common.contract.SystemVars;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class AdminSalePortalPageController {

    // 入口商品列表
    @GetMapping(value = {"/admin/salePortalList.html"})
    public String list(Model model) {
        try {
            model.addAttribute("contentPage", "admin/saleportal/content-salePortalList");
        } catch (Exception e) {
            log.error("解析授權資料失敗 ", e);
        }
        return SystemVars.adminLayoutTemplate;
    }

    // 入口商品管理
    @GetMapping(value = {"/admin/salePortalManager.html"})
    public String edit(Model model) {
        try {
            model.addAttribute("contentPage", "admin/saleportal/content-salePortalManager");
        } catch (Exception e) {
            log.error("解析授權資料失敗 ", e);
        }
        return SystemVars.adminLayoutTemplate;
    }
}
