package com.dyl.usercenter.service;

import com.dyl.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户服务
 *
 * @author admin
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 检查密码
     * @return 新用户Id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);
}
