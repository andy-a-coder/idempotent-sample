package com.andy.sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.idempotent.annotation.Idempotent;
import com.andy.sample.dao.UserAccountMapper;
import com.andy.sample.dto.UserAccountDto;
import com.andy.sample.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    @Idempotent(idempotentColumns = { "nickName", "sex" })
    public int saveUserAccount(String nickName, int sex) {
        UserAccountDto userAccount = new UserAccountDto();
        // ...
        
        return userAccountMapper.saveUserAccount(userAccount);
    }

    @Override
    @Idempotent(
            idempotentColumns = { "userAccount.nickName", "userAccount.sex" }, 
            idempotentMinutes = 1440, 
            responseStrategy = 0
            )
    public int saveUserAccount(UserAccountDto userAccount) {
        // ...
        return userAccountMapper.saveUserAccount(userAccount);
    }
    

    @Override
    @Idempotent(
            idempotentColumns = { "userAccount.nickName", "userAccount.sex" }, 
            prjName = "test project", 
            interfaceName = "UserServiceImpl.saveUserAccount", 
            idempotentMinutes = 1440, 
            idempotentParamOnly = true, 
            responseStrategy = 1
            )
    public int saveUserAccount1(UserAccountDto userAccount) {
        // ...
        return userAccountMapper.saveUserAccount(userAccount);
    }
    
    @Override
    @Idempotent(
            idempotentColumns = { "userAccount.nickName", "userAccount.sex" },
            idempotentMinutes = 1440
            )
    public int saveUserAccount2(UserAccountDto userAccount) {
        // ...
        
        return userAccountMapper.saveUserAccount(userAccount);
    }

    
    @Override
    @Idempotent(idempotentMinutes = 1440)
    public int saveUserAccount3(UserAccountDto userAccount) {
        // ...
        return userAccountMapper.saveUserAccount(userAccount);
    }
}
