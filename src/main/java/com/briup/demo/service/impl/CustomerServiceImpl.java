package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Customer;
import com.briup.demo.bean.CustomerExample;
import com.briup.demo.mapper.CustomerMapper;
import com.briup.demo.service.ICustomerService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired 
	private CustomerMapper customerMapper;

	@Override
	public List<Customer> findAllCustomers() throws CustomerException {
		CustomerExample example = new CustomerExample();
		List<Customer> list = customerMapper.selectByExample(example);		
		return list;
	}

	@Override
	public void saveOrUpdateCustomer(Customer customer) throws CustomerException {
		if(customer==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		else if(customer.getId()==null) {
			customerMapper.insert(customer);
		}
		else {
			customerMapper.updateByPrimaryKey(customer);
		}
	}

	@Override
	public void deleteCustomerById(int id) throws CustomerException {
		customerMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public Customer findCustomerById(int id) throws CustomerException {
		Customer c = customerMapper.selectByPrimaryKey(id);
		return c;
	}

}
