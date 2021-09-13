package com.hscrm.dao;

import com.hscrm.domain.Emp;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/6 21:10
 */
public interface EmpDao {
    /**
     * 添加员工
     */
    int addEmp(Emp emp);

    /**
     * 通过用户名查询员工
     */
    Emp findEmpByUsername(String username);

    /**
     * 通过用户名查询老密码
     */
    String findOldPasswdByUsername(String username);

    /**
     * 通过用户名修改密码
     */
    int updatePasswdByUsername(String username,String passwd);
}
