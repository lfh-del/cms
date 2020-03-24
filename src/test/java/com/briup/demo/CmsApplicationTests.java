package com.briup.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.briup.demo.bean.Customer;
import com.briup.demo.service.CustomerDao;

@SpringBootTest
class CmsApplicationTests {

	@Autowired
	private CustomerDao customerDao;
	@Test
	void contextLoads() throws Exception {
		String string = customerDao.findPasswordByName("李四");
		System.out.println(string);
	}

	@Test
	public void c() {
		Customer customer = new Customer();
		
		customer.setUsername("tom");
		customer.setPassword("123");
		customerDao.save(customer);
		System.out.println("成功");
	}

}
