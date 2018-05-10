package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.TractInfoDao;
import com.bypay.domain.TractInfo;
import com.bypay.util.DateUtil;

@Repository("tractInfoDao")
public class TractInfoDaoImpl implements TractInfoDao{
	
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override //添加通道
	public int insertTractInfo(TractInfo tractInfo) {
		int i = -1;
			//tractInfo.setTractId(sequenceIdDao.getSequenceTract_Id()); // sequenceIdDao
			// 生成的通道号
			i = sqlSessionTemplate.insert("insertTractInfo", tractInfo);
		return i;

	}
	
	// 查询添加通道时候的支付商户号号是否存在
	public TractInfo selectTractInfoRepeat(String transMerId) {
		return (TractInfo) sqlSessionTemplate.selectOne("selectTractInfoRepeat", transMerId);
	}
	
	
	// 查询通道 按条件查询
	public List<TractInfo> selectTractInfoList(Map map){
		return (List<TractInfo>) sqlSessionTemplate.selectList("selectTractInfoList", map);
	}
	
	
	// 查询数据库 通道的总条数
	public int selectTractInfoCount(TractInfo TractInfo) {
		return (Integer) sqlSessionTemplate.selectOne("selectTractInfoCount", TractInfo);
	}
	
	
	// 通道详情 审批
	public TractInfo selectTractById(String tractId) {
		return (TractInfo) sqlSessionTemplate.selectOne("selectTractById", tractId);
	}

	// 更新更新通道 启动
	public int updateTractInfo(TractInfo tractInfo) {
		return sqlSessionTemplate.update("updateTractInfo", tractInfo);
	}

	// 修改商户信息
	public int updateTract(TractInfo tractInfo) {
		return sqlSessionTemplate.update("updateTract", tractInfo);
	}

	// 更新通道 暂停
	public int updateTractInfoOFF(TractInfo tractInfo) {
		return sqlSessionTemplate.update("updateTractInfoOFF", tractInfo);
	}

	@Override
	public List<TractInfo> selectTractInfoByRatesType(TractInfo tractInfo) {
		return sqlSessionTemplate.selectList("selectTractInfoByRatesType",tractInfo);
	}

	// 跑批用
	@Override
	public List<TractInfo> selectTractInfoAllSettle(TractInfo tractInfo) {
		return sqlSessionTemplate.selectList("selectTractInfoAllSettle", tractInfo);
	}

	@Override
	public List<TractInfo> selectTractInfoSubMerInfoOnline() {
		return sqlSessionTemplate.selectList("selectTractInfoSubMerInfoOnline");
	}
	@Override
	public List<TractInfo> selectTractInfoByStatus(Map<String,Object> map){
      return (List<TractInfo>) sqlSessionTemplate.selectList("selectTractInfoByStatus", map);
  }
	
	@Override
	public Integer updateReserved(TractInfo tractInfo) {
		return sqlSessionTemplate.update("updateReserved", tractInfo);
	}
	
	// 通道详情 审批
	@Override
	public TractInfo selectTractByMerId(String transMerId) {
		return (TractInfo) sqlSessionTemplate.selectOne("selectTractByMerId", transMerId);
	}
}
