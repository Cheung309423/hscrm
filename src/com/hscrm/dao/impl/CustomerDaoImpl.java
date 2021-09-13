package com.hscrm.dao.impl;

import com.hscrm.dao.CustomerDao;
import com.hscrm.domain.Customer;
import com.hscrm.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/7 11:08
 */
public class CustomerDaoImpl implements CustomerDao {
    private DBUtil db = new DBUtil();

    @Override
    public int addCustomer(Customer customer) {
        String sql = "insert into customer values(null,?,?,?,?,?)";
        Object[] objs = {customer.getC_name(), customer.getC_sex(), customer.getC_tel(), customer.getC_job(), customer.getC_company()};
        int i = db.update(sql, objs);
        db.close();
        return i;
    }

    @Override
    public int deleteCustomer(int c_id) {
        String sql = "delete from customer where c_id = ?";
        Object[] objs = {c_id};
        int i = db.update(sql, objs);
        db.close();
        return i;
    }

    @Override
    public int updateCustomer(Customer customer) {
        String sql = "update customer set c_name=?,c_sex=?,c_tel=?,c_job=?,c_company=? where c_id=?";
        Object[] objs = {customer.getC_name(), customer.getC_sex(), customer.getC_tel(), customer.getC_job(), customer.getC_company(), customer.getC_id()};
        int i = db.update(sql, objs);
        db.close();
        return i;
    }

    @Override
    public List<Customer> findAllCustomer() {
        String sql = "select * from customer";
        Object[] objs = {};
        ResultSet rs = db.select(sql, objs);
        List<Customer> customerList = new ArrayList<>();
        try {
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("c_id"),
                        rs.getString("c_name"),
                        rs.getString("c_sex"),
                        rs.getString("c_tel"),
                        rs.getString("c_job"),
                        rs.getString("c_company")
                );
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return customerList;
    }

    @Override
    public Customer findCustomer(int c_id) {
        String sql = "select * from customer where c_id=?";
        Object[] objs = {c_id};
        ResultSet rs = db.select(sql, objs);
        Customer customer = null;
        try {
            if (rs.next()) {
                customer = new Customer(
                        rs.getInt("c_id"),
                        rs.getString("c_name"),
                        rs.getString("c_sex"),
                        rs.getString("c_tel"),
                        rs.getString("c_job"),
                        rs.getString("c_company")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return customer;
    }
}
