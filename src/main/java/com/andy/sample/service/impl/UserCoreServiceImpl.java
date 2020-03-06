package com.andy.sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andy.idempotent.annotation.Idempotent;
import com.andy.sample.dao.UserAccountMapper;
import com.andy.sample.dto.UserAccountDto;
import com.andy.sample.service.UserCoreService;

@Service
public class UserCoreServiceImpl implements UserCoreService {

    @Autowired
    private UserAccountMapper userAccountMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    @Idempotent(
            idempotentColumns = { "userAccount.nickName", "userAccount.sex" }, 
            idempotentMinutes = 1440, 
            responseStrategy = 0
            )
    public int saveUserAccount1(UserAccountDto userAccount) {
        // ...
        return userAccountMapper.saveUserAccount(userAccount);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Idempotent(
            idempotentColumns = { "userAccount.nickName", "userAccount.sex" }, 
            idempotentMinutes = 1440, 
            responseStrategy = 0
            )
    public int saveUserAccount2(UserAccountDto userAccount) {
        // ...
        userAccountMapper.saveUserAccount(userAccount);
        int a = 1;
        if(a != 2)
            throw new RuntimeException("saveUserAccount2 error");
        return 1;
    }

}
