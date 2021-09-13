package com.hscrm.dao;

import com.hscrm.domain.Customer;

import java.util.List;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/7 11:03
 */
public interface CustomerDao {
    /**
     * 添加客户
     */
    int addCustomer(Customer customer);

    /**
     * 通过id删除客户
     */
    int deleteCustomer(int c_id);

    /**
     * 通过id修改客户
     */
    int updateCustomer(Customer customer);

    /**
     * 查询所有客户
     */
    List<Customer> findAllCustomer();

    /**
     * 查询单个客户
     */
    Customer findCustomer(int c_id);
}
