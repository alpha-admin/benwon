package com.benwon.shop.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.benwon.shop.bo.SaleGroupBo;
import com.benwon.shop.service.SaleGroupService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexPageController {

	private final SaleGroupService saleGroupService;

	@GetMapping(value = { "/", "index.html" })
	public String authorize(Model model) {
		try {
			SaleGroupBo saleGroupBo = saleGroupService.getSaleOnPortal();
			model.addAttribute("saleGroup", saleGroupBo);
		} catch (Exception e) {
			log.error("解析授權資料失敗 ", e);
		}
		return "portal";
	}

}
