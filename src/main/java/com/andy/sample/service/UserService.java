package com.andy.sample.service;

import com.andy.sample.dto.UserAccountDto;

public interface UserService {

    public int saveUserAccount(UserAccountDto userAccount);

    public int saveUserAccount(String nickName, int sex);

    public int saveUserAccount1(UserAccountDto userAccount);

    public int saveUserAccount2(UserAccountDto userAccount);

    public int saveUserAccount3(UserAccountDto userAccount);
}
