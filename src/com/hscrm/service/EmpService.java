package com.hscrm.service;

import com.hscrm.domain.Emp;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/6 21:46
 */
public interface EmpService {
    /**
     * 注册
     */
    int register(Emp emp);

    /**
     * 登录
     */
    int login(String username, String passwd);

    /**
     * 判断用户名唯一性
     */
    int usernameUnique(String username);

    /**
     * 修改密码
     */
    int updatePasswd(String username, String oldPasswd, String newPasswd);

    /**
     * 通过用户名查找
     */
    public int getEmpId(String username);
}
