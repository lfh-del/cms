package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Link;
import com.briup.demo.utils.CustomerException;

/*
 * 关于链接的相关操作
 */
public interface ILinkService {
	/*
	 * 保存或者修改链接信息
	 */
	void saveOrUpdateLink(Link link) throws CustomerException;
	/*
	 * 删除链接信息 id
	 */
	void deleteLinkById(Integer id) throws CustomerException;
	/*
	 * 查找
	 */
	List<Link> findLinkByName(String name) throws CustomerException;
	List<Link> findAllLink() throws CustomerException;
	/*
	 * 更新
	 */
	void updateLink(Link link) throws CustomerException;

	
}
