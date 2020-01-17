package com.benwon.shop.controller.page.admin;

import com.benwon.shop.common.contract.SystemVars;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class AdminSaleGroupPageController {

    @GetMapping(value = {"/admin/saleGroupCreate.html"})
    public String create(Model model) {
        try {
            model.addAttribute("contentPage", "admin/salegroup/content-saleGroupCreate");
        } catch (Exception e) {
            log.error("解析授權資料失敗 ", e);
        }
        return SystemVars.adminLayoutTemplate;
    }

    @GetMapping(value = {"/admin/saleGroupList.html"})
    public String list(Model model) {
        try {
            model.addAttribute("contentPage", "admin/salegroup/content-saleGroupList");
        } catch (Exception e) {
            log.error("解析授權資料失敗 ", e);
        }
        return SystemVars.adminLayoutTemplate;
    }

    @GetMapping(value = {"/admin/saleGroupEdit.html"})
    public String edit(Model model) {
        try {
            model.addAttribute("contentPage", "admin/salegroup/content-saleGroupEdit");
        } catch (Exception e) {
            log.error("解析授權資料失敗 ", e);
        }
        return SystemVars.adminLayoutTemplate;
    }
}
