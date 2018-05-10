package com.bypay.dao.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.BookOrderDetailDao;
import com.bypay.domain.BookOrderDetail;
@Repository("bookOrderDetailDao")
public class BookOrderDetailDaoImpl implements BookOrderDetailDao {

	@Inject
	  private SqlSessionTemplate sqlSessionTemplate;
	

	@Override
	public int insertBookOrderDetail(BookOrderDetail bookOrderDetail) {
		 return sqlSessionTemplate.insert("insertBookOrderDetail", bookOrderDetail);
	}

}
