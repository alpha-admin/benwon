package com.benwon.shop.controller.page;

import com.benwon.shop.configuration.properties.AuthProperty;
import com.benwon.shop.configuration.properties.BenwonProperties;
import com.benwon.shop.dto.OAuthTokenRsDto;
import com.benwon.shop.entity.AuthRecords;
import com.benwon.shop.entity.UserProfile;
import com.benwon.shop.service.AuthRecordsService;
import com.benwon.shop.service.AuthService;
import com.benwon.shop.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SignPageController {

    private final BenwonProperties benwonProperties;

    private final AuthService authService;
    private final AuthRecordsService authRecordsService;
    private final UserProfileService userProfileService;
    
    @GetMapping(value = {"/sign.html"})
    public String sign(Model model) {
        return "sign";
    }
    
    /**
     * 這一頁不用給Token, 因為前面已儲存
     * @param model
     * @return
     */
    @GetMapping(value = {"/signUpUserProfile.html"})
    public String signUpUserProfile(Model model) {
        return "sign_up_user_profile";
    }

//    @GetMapping(value = {"/loginRs"})
//    public String sign(@RequestParam(value = "code", required = false) String code,
//                       @RequestParam(value = "state", required = false) String state,
//                       Model model) {
//        String naxtPage = "";
//        log.info("code={}, state={}", code, state);
//        try {
//            UserProfile userProfile = null;
//            AuthRecords authRecords = authRecordsService.findByAuthCode(code);
//            Optional<UserProfile> userProfileOptional = userProfileService.findById(authRecords.getMyUserId());
//            if (!userProfileOptional.isPresent()) {// 如果不存在就先建檔
//                userProfile = userProfileService.createByAuthRecords(authRecords);
//            } else {
//                userProfile = userProfileOptional.get();
//            }
//            if (userProfile.getFinished().equals(0)) {
//                naxtPage = "sign_up";
//            } else {
//                naxtPage = "sign_in";
//            }
//            OAuthTokenRsDto oAuthTokenRsDto = authService.getToken(code, state);
//
//            model.addAttribute("access_token", oAuthTokenRsDto.getAccessToken());
//            model.addAttribute("refresh_token", oAuthTokenRsDto.getRefreshToken());
//            model.addAttribute("userProfile", userProfile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return naxtPage;
//    }

}
