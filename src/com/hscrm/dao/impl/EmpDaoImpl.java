package com.hscrm.dao.impl;

import com.hscrm.dao.EmpDao;
import com.hscrm.domain.Emp;
import com.hscrm.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/6 21:13
 */
public class EmpDaoImpl implements EmpDao {
    private DBUtil db = new DBUtil();

    @Override
    public int addEmp(Emp emp) {
        String sql = "insert into emp values(null,?,?,?,?,?)";
        Object[] objs = {emp.getE_name(), emp.getE_sex(), emp.getE_tel(), emp.getUsername(), emp.getPasswd()};
        int i = db.update(sql, objs);
        db.close();
        return i;
    }

    @Override
    public Emp findEmpByUsername(String username) {
        String sql = "select * from emp where username=?";
        Object[] objs = {username};
        ResultSet rs = db.select(sql, objs);
        Emp emp = null;
        try {
            if (rs.next()) {
                emp = new Emp();
                emp.setE_id(rs.getInt("e_id"));
                emp.setE_sex(rs.getString("e_name"));
                emp.setE_tel(rs.getString("e_sex"));
                emp.setUsername(rs.getString("username"));
                emp.setPasswd(rs.getString("passwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return emp;
    }

    @Override
    public String findOldPasswdByUsername(String username) {
        String oldPasswd = null;
        String sql = "select passwd from emp where username=?";
        Object[] objs = {username};
        ResultSet rs = db.select(sql, objs);
        try {
            if (rs.next()) {
                oldPasswd = rs.getString("passwd");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return oldPasswd;
    }

    @Override
    public int updatePasswdByUsername(String username, String newPasswd) {
        String sql = "update emp set passwd=? where username=?";
        Object[] objs = {newPasswd, username};
        int i = db.update(sql, objs);
        db.close();
        return i;
    }
}
