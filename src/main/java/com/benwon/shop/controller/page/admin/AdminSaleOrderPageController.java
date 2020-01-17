package com.benwon.shop.controller.page.admin;

import com.benwon.shop.common.contract.SystemVars;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class AdminSaleOrderPageController {

    @GetMapping(value = {"saleOrderUnShippedList", "saleOrderUnShippedList.html"})
    public String unShipList(Model model) {
        try {
            model.addAttribute("contentPage", "admin/saleorder/content-saleOrderUnShipped");
        } catch (Exception e) {
            log.error("解析授權資料失敗 ", e);
        }
        return SystemVars.adminLayoutTemplate;
    }

    @GetMapping(value = {"saleOrderDetail", "saleOrderDetail.html"})
    public String detail(Model model) {
        try {
            model.addAttribute("contentPage", "admin/saleorder/content-saleOrderDetail");
        } catch (Exception e) {
            log.error("解析授權資料失敗 ", e);
        }
        return SystemVars.adminLayoutTemplate;
    }
}
