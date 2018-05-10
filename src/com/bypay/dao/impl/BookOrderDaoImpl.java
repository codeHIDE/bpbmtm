package com.bypay.dao.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.BookOrderDao;
import com.bypay.domain.BookOrder;
@Repository("bookOrderDao")
public class BookOrderDaoImpl implements BookOrderDao {

	@Inject
	  private SqlSessionTemplate sqlSessionTemplate;
	

	@Override
	public int insertBookOrder(BookOrder bookOrder) {
		 return sqlSessionTemplate.insert("insertBookOrder", bookOrder);
	}

}
