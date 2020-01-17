package com.benwon.shop.controller.page.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.benwon.shop.common.contract.SystemVars;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminInviteCodePageController {
	
	@GetMapping(value = { "/admin/inviteCodeList.html" })
	public String list(Model model) {
		try {
			model.addAttribute("contentPage", "admin/inviteCode/content-inviteCodeList");
		} catch (Exception e) {
			log.error("解析授權資料失敗 ", e);
		}
		return SystemVars.adminLayoutTemplate;
	}
}
