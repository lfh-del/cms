package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtile;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * 和栏目相关的 和前端交互的web层
 */
@RestController
@Api(description = "和栏目相关的链接")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	@PostMapping("/saveOrUpdateCategory")
	@ApiOperation("增加或者更新栏目")
	public Message<String> saveOrUpdateCategory(Category category){
		try {
			categoryService.saveOrUpdateCategory(category);
			return MessageUtile.success();
		} catch (CustomerException e) {
			return MessageUtile.error(StatusCodeUtil.ERROR_NAME, "栏目名已经存在");		
	}
	}
	@GetMapping("/deleteCategoryById")
	@ApiOperation("通过id删除栏目")
	public Message<String> deleteCategoryById(int id){
		try {
			categoryService.deleteCategoryById(id);
			return MessageUtile.success();
		} catch (CustomerException e) {
			return MessageUtile.error(StatusCodeUtil.ERROR_CODE, "系统错误异常");
		}
		
	}

	@GetMapping("/selectCategorys")
	@ApiOperation("查询所有栏目")
	public Message<List<Category>> selectAllCategory(){
		try {
			List<Category> list = categoryService.findAllCategorys();
			return MessageUtile.success(list);
		} catch (CustomerException e) {
			return MessageUtile.error(StatusCodeUtil.ERROR_CODE, "系统错误异常");
		}
	}
	
	@GetMapping("/selectCategoryById")
	@ApiOperation("通过id查询栏目")
	public Message<Category>  selectCategoryById(int id){
		try {
			Category category = categoryService.findCategoryById(id);
			return MessageUtile.success(category);
		} catch (CustomerException e) {
			return MessageUtile.error(StatusCodeUtil.ERROR_CODE, "系统错误异常");
		}
		
	}
	

	@GetMapping("/findCategoryExById")
	@ApiOperation("通过栏目id查询栏目下所有文章")
	public Message<CategoryEx> findCategoryExById(Integer id){
		CategoryEx categoryEx = categoryService.findCategoryExById(id);
		return MessageUtile.success(categoryEx);
		
	}
}
