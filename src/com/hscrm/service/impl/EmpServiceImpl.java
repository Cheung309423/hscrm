package com.hscrm.service.impl;

import com.hscrm.dao.EmpDao;
import com.hscrm.dao.impl.EmpDaoImpl;
import com.hscrm.domain.Emp;
import com.hscrm.service.EmpService;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/6 21:51
 */
public class EmpServiceImpl implements EmpService {
    private EmpDao empDao = new EmpDaoImpl();

    @Override
    public int register(Emp emp) {
        return empDao.addEmp(emp);
    }

    @Override
    public int login(String username, String passwd) {
        Emp emp = empDao.findEmpByUsername(username);
        if (emp == null) {
            return 0;
        } else if (!emp.getPasswd().equals(passwd)) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public int usernameUnique(String username) {
        Emp emp = empDao.findEmpByUsername(username);
        if (emp == null) {
            return 0;
        } else {
            return 1;
        }
    }


    @Override
    public int updatePasswd(String username, String oldPasswd, String newPasswd) {
        String oldPasswdString = empDao.findOldPasswdByUsername(username);
        if (!oldPasswdString.equals(oldPasswd)) {
            return -1;
        }
        int i = empDao.updatePasswdByUsername(username, newPasswd);
        return i;
    }

    @Override
    public int getEmpId(String username) {
        return empDao.findEmpByUsername(username).getE_id();
    }
}
