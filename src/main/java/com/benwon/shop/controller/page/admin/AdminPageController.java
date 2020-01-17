package com.benwon.shop.controller.page.admin;

import com.benwon.shop.common.contract.SystemVars;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
public class AdminPageController {

    @GetMapping(value = {"/admin/dashboard.html"})
    public String dashboard(Model model) {
        try {
            //model.addAttribute("sidebarPage", "admin/sidebarPage :: sidebar");
            model.addAttribute("contentPage", "admin/content-dashboard");
        } catch (Exception e) {
            log.error("解析授權資料失敗 ", e);
        }
        return SystemVars.adminLayoutTemplate;
    }
}
