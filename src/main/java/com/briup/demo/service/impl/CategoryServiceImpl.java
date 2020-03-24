package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.bean.LinkExample.Criteria;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.mapper.ex.CategoryExMapper;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;
import com.briup.demo.bean.CategoryExample;
/*
 * 操作栏目的service功能类
 */
@Service
public class CategoryServiceImpl implements ICategoryService{
	@Autowired
	private CategoryMapper categorymapper;
	@Autowired
	private ArticleMapper articlemapper;
	@Autowired
	private CategoryExMapper categoryExMapper;
	
	
	@Override
	public List<Category> findAllCategorys() throws CustomerException {
		CategoryExample example = new CategoryExample();
		List<Category> list = categorymapper.selectByExample(example);
		return list;
	}

	@SuppressWarnings("unused")
	@Override
	public void saveOrUpdateCategory(Category category) throws CustomerException {

		CategoryExample example = new CategoryExample();
		example.createCriteria().andNameEqualTo(category.getName());
		List<Category> list = categorymapper.selectByExample(example);
		if(category == null ) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE,"参数为空");
		}
		if (category.getId() == null ) {
			if(list.size()==0) {
			categorymapper.insert(category);
		    }
			else {
				throw new CustomerException(StatusCodeUtil.ERROR_NAME,"栏目名已经存在");
			}
		}
		else {
			categorymapper.updateByPrimaryKey(category);
		}
	}

	@Override
	public void deleteCategoryById(int id) throws CustomerException {
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryIdEqualTo(id);
		articlemapper.deleteByExample(example);
		categorymapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public Category findCategoryById(int id) throws CustomerException {
		Category category = categorymapper.selectByPrimaryKey(id);
		return category;
	}

	@Override
	public List<CategoryEx> findAllCategoryEx() throws CustomerException {
		
		return categoryExMapper.findAllCategoryExs();
	}

	@Override
	public CategoryEx findCategoryExById(Integer id) throws CustomerException {
		
		return categoryExMapper.findCategoryExById(id);
	}

	

}
