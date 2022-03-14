package com.dyl.usercenter.service.impl;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyl.usercenter.model.domain.User;
import com.dyl.usercenter.service.UserService;
import com.dyl.usercenter.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户服务实现类
 *
 * @author admin
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

//    @Resource
//    private UserMapper userMapper;
//
//    @Override
//    public long userRegister(String userAccount, String userPassword, String checkPassword) {
//
//        // 1.校验
//        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
//            return -1;
//        }
//
//        if (userAccount.length() < 4) {
//            return -1;
//        }
//
//        if (userPassword.length() < 8 || checkPassword.length() < 8) {
//            return -1;
//        }
//
//
//        //账户不能包含特殊字符
//        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
//        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
//        if (matcher.find()) {
//            return -1;
//        }
//
//        // 密码和校验密码相同
//        if (!userPassword.equals(checkPassword)) {
//            return -1;
//        }
//
//        //账户不能重复
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("userAccount", userAccount);
//        long count = userMapper.selectCount(queryWrapper);
//        if (count > 0) {
//            return -1;
//        }
//
//        // 2.加密
//        final String salt = "dyl";
//        String encryptPassword = DigestUtils.md5DigestAsHex((salt + userPassword).getBytes());
//
//        //3、插入数据
//        User user = new User();
//        user.setUserAccount(userAccount);
//        user.setUserPassword(encryptPassword);
//        boolean saveResult = this.save(user);
//        if (!saveResult) {
//            return -1;
//        }
//
//        return user.getId();
//    }

    @Resource
    private  UserMapper userMapper;

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1.校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return -1;
        }
        if (userAccount.length() < 4) {
            return -1;
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8){
            return -1;
        }

        // 账户不能包含特殊字符
        String validPattern = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }
        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }

        // 账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }
        // 2.加密
        final String SALT = "yupi";
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 3.插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1;
        }
        return user.getId();
    }
}




