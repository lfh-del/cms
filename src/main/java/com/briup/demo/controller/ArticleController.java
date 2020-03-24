package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Article;
import com.briup.demo.service.IArticleService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtile;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * 文章相关信息的controller
 */
@RestController
@Api(description = "文章相关链接")
public class ArticleController {
	@Autowired
	private IArticleService articleService;
	
	@PostMapping("/addArticle")
	@ApiOperation("添加文章信息")
	public Message<String> saveArticle(Article article){
		try {
			articleService.saveOrUpdateArticle(article);
			return MessageUtile.success();
		} catch (CustomerException e) {
			return MessageUtile.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
		}
		
	}
	
	
	@PostMapping("/updateArticle")
	@ApiOperation("修改文章信息")
	public Message<String> updateArticle(Article article){
		try {
			articleService.saveOrUpdateArticle(article);
			return MessageUtile.success();
		} catch (CustomerException e) {
			return MessageUtile.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
		}
		
	}

	
	@PostMapping("/deleteArticle")
	@ApiOperation("根据id删除文章信息")
	public Message<String> deleteArticleById(int id){
		articleService.deleteArticleById(id);
		return MessageUtile.success();
	}
	
	@GetMapping("/findArticleByCondition")
	@ApiOperation("根据条件查询文章信息")
	public Message<List<Article>> getArticleByCondition(String keyStr,String condition){
		try {
			List<Article> list = articleService.findArticleByCondition(keyStr, condition);
			return MessageUtile.success(list);
		} catch (CustomerException e) {
			 return MessageUtile.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
		}
	   
	}
	
	@GetMapping("/findArticleById")
	@ApiOperation("根据id查询文章信息")
	public Message<Article> getArticleById(int id){
		    Article article = articleService.findArticleById(id);
	    return MessageUtile.success(article);
	}
	
	
}
