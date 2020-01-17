package com.benwon.shop.controller.page;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderPageController {

    @GetMapping(value = {"/order.html"})
    public String order(Model model) {
        try {
        } catch (Exception e) {
            log.error("解析授權資料失敗 ", e);
        }
        return "order";
    }
}
