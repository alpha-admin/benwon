package com.benwon.shop.controller.page;

import com.benwon.shop.bo.SaleGroupBo;
import com.benwon.shop.service.SaleGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GroupPageController {

    private final SaleGroupService saleGroupService;

    @GetMapping(value = {"/group/{groupid}.html"})
    public String unShipList(@PathVariable("groupid") String groupid, Model model) {
        SaleGroupBo saleGroupBo = saleGroupService.getOne(groupid);
        try {
            model.addAttribute("saleGroup", saleGroupBo);
        } catch (Exception e) {
            log.error("解析授權資料失敗 ", e);
        }
        return "group";
    }
}
