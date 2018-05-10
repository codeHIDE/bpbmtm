package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.SubMerCashoutDao;
import com.bypay.domain.SubMerCashout;

@Repository("subMerCashoutDao")
public class SubMerCashoutDaoImpl implements SubMerCashoutDao {

  @Inject
  private SqlSessionTemplate sqlSessionTemplate;

  @Override
  public SubMerCashout selectSubMerCashoutById(String id) {
    return (SubMerCashout) sqlSessionTemplate.selectOne("selectSubMerCashoutById", id);
  }
  
  @Override
  public SubMerCashout selectSubMerCashoutByOrderId(String orderId) {
	  return (SubMerCashout) sqlSessionTemplate.selectOne("selectSubMerCashoutByOrderId", orderId);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<SubMerCashout> selectSubMerCashout() {
    return sqlSessionTemplate.selectList("selectSubMerCashout");
  }

  @Override
  public Integer insertSubMerCashout(SubMerCashout subMerCashout) {
    return sqlSessionTemplate.insert("insertSubMerCashout", subMerCashout);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<SubMerCashout> selectSubMerCashoutList(Map<String, Object> map) {
    return sqlSessionTemplate.selectList("selectSubMerCashoutList", map);
  }

  @Override
  public Integer selectSubMerCashoutListCount(Map<String, Object> map) {
    return (Integer) sqlSessionTemplate.selectOne("selectSubMerCashoutCount", map);
  }
  @SuppressWarnings("unchecked")
  @Override
  public List<SubMerCashout> selectSubMerCashoutList3(Map<String, Object> map) {
	  return sqlSessionTemplate.selectList("selectSubMerCashoutList3", map);
  }
  
  @Override
  public Integer selectSubMerCashoutListCount3(Map<String, Object> map) {
	  return (Integer) sqlSessionTemplate.selectOne("selectSubMerCashoutCount3", map);
  }

  @Override
  public Integer deleteSubMerCashout(SubMerCashout subMerCashout) {
    return sqlSessionTemplate.delete("deleteSubMerCashout", subMerCashout);
  }

  /**
   * 根据商户号获取提现记录总条数
   */
  @Override
  public Integer getSubMerCashoutBySubIdCount(Map map) {
    return (Integer) sqlSessionTemplate.selectOne("getSubMerCashoutBySubIdCount", map);
  }

  /**
   * 根据商户号获取提现记录
   */
  @Override
  public List<SubMerCashout> getSubMerCashoutBySubId(Map map) {
    return sqlSessionTemplate.selectList("getSubMerCashoutBySubId", map);
  }

  @Override
  public Integer updateSubMerCashout(SubMerCashout subMerCashout) {
    return sqlSessionTemplate.update("updateSubMerCashout", subMerCashout);
  }
  
  @Override
  public Integer updateSubMerCashoutByBatch(SubMerCashout subMerCashout) {
	  return sqlSessionTemplate.update("updateSubMerCashoutByBatch", subMerCashout);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<SubMerCashout> selectSubMerCashoutListNotPage(Map<String, Object> map) {
    return sqlSessionTemplate.selectList("selectSubMerCashoutListNotPage", map);
  }
  
  
	// 查询通道 按条件查询
	public List<SubMerCashout> getSubMerCashoutList(Map map){
		return (List<SubMerCashout>) sqlSessionTemplate.selectList("getSubMerCashoutList", map);
	}
	
	
	// 查询数据库 通道的总条数
	public int getSubMerCashoutListCount(SubMerCashout subMerCashout) {
		return (Integer) sqlSessionTemplate.selectOne("getSubMerCashoutListCount", subMerCashout);
	}

	
	//通道查询 查询SubMerCashout信息LIST
	@Override
	public List<SubMerCashout> getSubMerCashoutListByOrderStatus(SubMerCashout subMerCashout) {
		return sqlSessionTemplate.selectList("getSubMerCashoutListByOrderStatus", subMerCashout);
	}

	@Override
	public List<SubMerCashout> selectSubMerCashoutReport(Map map) {
		return sqlSessionTemplate.selectList("selectSubMerCashoutReport",map);
	}
	
	@Override
	public Integer selectSubMerCashoutReportCount(Map map) {
		return (Integer) sqlSessionTemplate.selectOne("selectSubMerCashoutReportCount",map);
	}

	@Override
	public Integer selectSubMerCashoutListNotPageCount(Map<String, Object> map) {
		return (Integer) sqlSessionTemplate.selectOne("selectSubMerCashoutListNotPageCount", map);
	}

	@Override
	public List<SubMerCashout> seleSubMerCashoutReport(
			SubMerCashout subMerCashout) {
		return sqlSessionTemplate.selectList("seleSubMerCashoutReport",subMerCashout);
	}

	@Override
	public List<SubMerCashout> selectSubMerCashoutAmt(Map map) {
		return sqlSessionTemplate.selectList("selectSubMerCashoutAmt",map);
	}

	@Override
	public List<SubMerCashout> selectSubMerCashoutList2(
			SubMerCashout subMerCashout) {
		return sqlSessionTemplate.selectList("selectSubMerCashoutList2", subMerCashout);
	}
	
}
