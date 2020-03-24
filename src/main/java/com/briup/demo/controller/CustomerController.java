package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Customer;
import com.briup.demo.service.CustomerDao;
import com.briup.demo.service.ICustomerService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtile;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "顾客相关链接")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private CustomerDao customerDao;
	
	@PostMapping("/saveOrUpdateCustomer")
	@ApiOperation("添加或者更改")
	public Message<String> saveOrUpdateCustomer(Customer customer){
		try {
			customerService.saveOrUpdateCustomer(customer);
		} catch (CustomerException e) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		return MessageUtile.success();
		
	}
	
	@GetMapping("/deleteCustomerById")
	@ApiOperation("根据id删除")
	public Message<String> deleteCustomerById(int id){
		try {
			customerService.deleteCustomerById(id);
		} catch (CustomerException e) {
			
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		return MessageUtile.success();
		
	}
	
	@GetMapping("/finddeleteCustomerById")
	@ApiOperation("根据id查找")
	public Message<Customer> findCutomerById(int id){
		Customer customer = customerService.findCustomerById(id);
		return MessageUtile.success(customer);//josn
		
	}
	
	@GetMapping("/findCustomers")
	@ApiOperation("查找全部")
	public Message<List<Customer>> findCustomers(){
		List<Customer> list = customerService.findAllCustomers();
		return MessageUtile.success(list);
		/*
		 * mvc:
		 * 	model:			mybaits  返user   封装到 domain/model/bean  User
		 *  view:        viewSolver    return "hello"   hello.jsp
		 *  controller    springmvc     response.redirect()
		 */
	}

	@PostMapping("/saveCustomerByJPA")
	@ApiOperation("注册用户JPA")
	public Message<Customer> saveCustomerByJPA(Customer c){
		Customer customer = new Customer();
		customer.setUsername(c.getUsername());
		customer.setPassword(c.getPassword());
		customerDao.save(customer);
		return MessageUtile.success();
		
	}
	@PostMapping("/Login")
	@ApiOperation("登录用户JPA")
	public Message<String> Long(String name,String password) throws Exception{
		String p = customerDao.findPasswordByName(name);
		if(p.equals(password)) {
			return MessageUtile.success("登录成功");
		}
		else
		return MessageUtile.error(StatusCodeUtil.ERROR_CODE, "登录失败");
		
	}
}
