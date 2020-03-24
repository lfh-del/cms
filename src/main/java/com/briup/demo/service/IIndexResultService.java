package com.briup.demo.service;

import com.briup.demo.bean.ex.IndexResult;
import com.briup.demo.utils.CustomerException;

/*
 * 首页数据管理service层接口
 */
public interface IIndexResultService {
	IndexResult findIndexAllResult() throws CustomerException;
	
}
