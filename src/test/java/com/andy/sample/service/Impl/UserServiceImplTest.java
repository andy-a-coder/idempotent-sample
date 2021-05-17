package com.andy.sample.service.Impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.andy.sample.IdempotentSampleWebApplication;
import com.andy.sample.dto.UserAccountDto;
import com.andy.sample.service.UserService;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=IdempotentSampleWebApplication.class)
public class UserServiceImplTest {
    
    @Autowired
    private UserService userSerivce;

    @Test
    public void saveUserAccount() {
        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto.setNickName("andy");
        userAccountDto.setSex(1);
        int result = userSerivce.saveUserAccount(userAccountDto);
        TestCase.assertEquals(1, result);;
    }
}
