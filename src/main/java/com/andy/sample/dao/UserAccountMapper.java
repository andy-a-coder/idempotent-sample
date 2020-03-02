package com.andy.sample.dao;

import org.apache.ibatis.annotations.Insert;

import com.andy.sample.dto.UserAccountDto;

public interface UserAccountMapper {

    public static final String BASE_COLUMNS = "id, nick_name, sex, status, create_time";

    @Insert({
            "insert user_account (nick_name, sex, status)",
            "values(#{nickName}, #{sex}, 0)"
    })
    public int saveUserAccount(UserAccountDto userAccount);

}