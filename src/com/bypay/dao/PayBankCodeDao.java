package com.bypay.dao;

import java.util.*;

import com.bypay.domain.PayBankCode;

public interface PayBankCodeDao {
	/**
	 * 添加代发通道表
	 * @param pbd 实体类
	 * @return
	 */
	public boolean insertPayBankCode(PayBankCode pbd);
	/**
	 * 查询所有代发通道
	 * @param map 分页
	 * @return
	 */
	public List<PayBankCode> selectPayBankCodeAll(Map<String,Object> map);
	/**
	 * 查询代发通道的信息数
	 * @return
	 */
	public Integer selectPayBankCodeCount();
	/**
	 * 删除
	 */
	public boolean deletePayBankCode(PayBankCode pbc);
}
