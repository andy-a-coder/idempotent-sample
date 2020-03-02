package com.andy.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andy.sample.common.ResponseData;
import com.andy.sample.dto.UserAccountDto;
import com.andy.sample.service.UserService;

@RestController
@RequestMapping("/sample")
public class SampleController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/createUserAccount")
    public ResponseData<?> createUserAccount(@RequestParam("nickName") String nickName, @RequestParam("sex") Integer sex) {
        UserAccountDto userAccount = new UserAccountDto();
        userAccount.setNickName(nickName);
        userAccount.setSex(sex);
        return new ResponseData<>(userService.saveUserAccount(userAccount));
    }

}
