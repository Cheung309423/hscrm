package com.hscrm.service.impl;

import com.hscrm.dao.CustomerDao;
import com.hscrm.dao.TrackDao;
import com.hscrm.dao.impl.CustomerDaoImpl;
import com.hscrm.dao.impl.TrackDaoImpl;
import com.hscrm.domain.Customer;
import com.hscrm.service.CustomerService;

import java.util.List;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/7 11:35
 */
public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao = new CustomerDaoImpl();
    private TrackDao trackDao = new TrackDaoImpl();

    @Override
    public int addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    @Override
    public int deleteCustomer(int c_id) {
        //级联删除
        trackDao.deleteTrackByCid(c_id);
        return customerDao.deleteCustomer(c_id);
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    @Override
    public List<Customer> findAllCustomer() {
        return customerDao.findAllCustomer();
    }

    @Override
    public Customer findCustomer(int c_id) {
        return customerDao.findCustomer(c_id);
    }
}
