package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Link;
import com.briup.demo.bean.LinkExample;
import com.briup.demo.bean.LinkExample.Criteria;
import com.briup.demo.mapper.LinkMapper;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;
/*
 * 操作链接的service功能类
 */
@Service
public class LinkServiceImpl implements ILinkService {

	@Autowired
	private LinkMapper linkMapper;
	
	@Override
	public void saveOrUpdateLink(Link link) throws CustomerException {
		
		if(link == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE,"参数为空");
		}
		if (link.getId() == null) {
			linkMapper.insert(link);
		}
		else {
			linkMapper.updateByPrimaryKey(link);
		}
	}

	@Override
	public void deleteLinkById(Integer id) throws CustomerException {
		if(id==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		linkMapper.deleteByPrimaryKey(id);

	}

	@Override
	public void updateLink(Link link) throws CustomerException {
		if(link==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		linkMapper.updateByPrimaryKey(link);

		}
	
	@Override
	public List<Link> findLinkByName(String name) throws CustomerException {
		name = name == null ? "" : name.trim();
		LinkExample example = new LinkExample();
		if("".equals(name)) {
			//如果搜索条件没写，返回所有数据
			return linkMapper.selectByExample(example);
		}
		else {
			//如果不为null，返回满足条件数据
			Criteria criteria = example.createCriteria();
			criteria.andNameLike("%"+name+"%");
			return linkMapper.selectByExample(example);
		}
		
	}

	@Override
	public List<Link> findAllLink() throws CustomerException {
		LinkExample example = new LinkExample();
		List<Link> list = linkMapper.selectByExample(example);
		return list;
	
	}
  
}
