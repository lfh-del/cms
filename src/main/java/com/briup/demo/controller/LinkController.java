package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Link;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtile;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * 和链接相关的 和前端交互的web层
 */
@RestController
@Api(description = "链接相关接口")
public class LinkController {
	@Autowired
	private ILinkService linkService;

	@PostMapping("/saveOrUpdateLink")
	@ApiOperation("插入或者更改")
	public Message<String> addLink(Link link){
		try {
			linkService.saveOrUpdateLink(link);
			System.out.println("------------");
			return MessageUtile.success();
			
		} catch (CustomerException e) {
			return MessageUtile.error(StatusCodeUtil.ERROR_CODE, "系统错误异常");
		}	
		
	}
	
	@GetMapping("/deleteLinkById")
	@ApiOperation("根据id删除链接")
	public Message<String> deleteById(int id){
		try {
			linkService.deleteLinkById(id);
			return MessageUtile.success();
		} catch (CustomerException e) {
			return MessageUtile.error(StatusCodeUtil.ERROR_CODE, "系统错误异常");
		}
		
	}
	
	@PostMapping("/updateLink")
	@ApiOperation("更改链接")
	public  Message<String> updateLink(Link link) {
		
		try {
			linkService.updateLink(link);
			return MessageUtile.success();
		} catch (CustomerException e) {
			return MessageUtile.error(StatusCodeUtil.ERROR_CODE, "系统错误");
		}
		
	}

	
	@GetMapping("/selectAllLink")
	@ApiOperation("查询所有")
	public Message<List<Link>> selelectLinks(){
		try {
			 List<Link> list = linkService.findAllLink();
			 return MessageUtile.success(list);
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	
	@GetMapping("/selectLinkByName")
	@ApiOperation("根据链接名查询")
	public Message<List<Link>> selectLinkByName(String name){
		try {
			List<Link> list = linkService.findLinkByName(name);
			return MessageUtile.success(list);
		} catch (CustomerException e) {
			return MessageUtile.error(StatusCodeUtil.ERROR_CODE, "系统错误异常");
		}
		
	}

}
