package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Customer;
import com.briup.demo.utils.CustomerException;

public interface ICustomerService {
/*
	 * 查询所有
	 */
		public List<Customer> findAllCustomers() throws CustomerException;

		
	/*
	 * 添加或者修改
	 *
	 */
		public void saveOrUpdateCustomer(Customer customer) throws CustomerException;
		
		
	/*
	 * 根据id删除
	 */
		public void deleteCustomerById(int id) throws CustomerException;
		
	/*根据id查找
	 * 
	 */
		public Customer findCustomerById(int id) throws CustomerException;
	

}
