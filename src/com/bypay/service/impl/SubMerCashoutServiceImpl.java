package com.bypay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bypay.dao.SubMerCashoutDao;
import com.bypay.domain.SubMerCashout;
import com.bypay.service.SubMerCashoutService;

@Service
public class SubMerCashoutServiceImpl implements SubMerCashoutService {
  @Autowired
  private SubMerCashoutDao subMerCashoutDao;

  @Override
  public SubMerCashout selectSubMerCashoutById(String id) {
    return subMerCashoutDao.selectSubMerCashoutById(id);
  }
  
  @Override
  public List<SubMerCashout> selectSubMerCashout() {
    return subMerCashoutDao.selectSubMerCashout();
  }

  @Override
  public Integer insertSubMerCashout(SubMerCashout subMerCashout) {
    return subMerCashoutDao.insertSubMerCashout(subMerCashout);
  }

  @Override
  public List<SubMerCashout> selectSubMerCashoutList(Map<String, Object> map) {
    return subMerCashoutDao.selectSubMerCashoutList(map);
  }

  @Override
  public Integer selectSubMerCashoutListCount(Map<String, Object> map) {
    return (Integer) subMerCashoutDao.selectSubMerCashoutListCount(map);
  }
  
  @Override
  public List<SubMerCashout> selectSubMerCashoutList3(Map<String, Object> map) {
	  return subMerCashoutDao.selectSubMerCashoutList3(map);
  }
  
  @Override
  public Integer selectSubMerCashoutListCount3(Map<String, Object> map) {
	  return (Integer) subMerCashoutDao.selectSubMerCashoutListCount3(map);
  }

  @Override
  public Integer deleteSubMerCashout(SubMerCashout subMerCashout) {
    return subMerCashoutDao.deleteSubMerCashout(subMerCashout);
  }

  @Override
  public List<SubMerCashout> selectSubMerCashoutListNotPage(Map<String, Object> map) {
    return subMerCashoutDao.selectSubMerCashoutListNotPage(map);
  }

  @Override
  public Integer updateSubMerCashout(SubMerCashout subMerCashout) {
    return subMerCashoutDao.updateSubMerCashout(subMerCashout);
  }
  
  @Override
  public Integer updateSubMerCashoutByBatch(SubMerCashout subMerCashout) {
	  return subMerCashoutDao.updateSubMerCashoutByBatch(subMerCashout);
  }

  //yjh所有 LIST
	@Override
	public List<SubMerCashout> getSubMerCashoutList(Map map) {
		List<SubMerCashout> list = null;
		try {
			list = subMerCashoutDao.getSubMerCashoutList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	 //yjh所有 LIST 条数分页用
	@Override
	public int getSubMerCashoutListCount(SubMerCashout subMerCashout) {
		return subMerCashoutDao.getSubMerCashoutListCount(subMerCashout);
	}

	// 查询SubMerCashout信息LIST
	@Override
	public List<SubMerCashout> getSubMerCashoutListByOrderStatus(
			SubMerCashout subMerCashout) {
		// TODO Auto-generated method stub
		return subMerCashoutDao.getSubMerCashoutListByOrderStatus(subMerCashout);
	}

	@Override
	public List<SubMerCashout> selectSubMerCashoutReport(Map map) {
		return subMerCashoutDao.selectSubMerCashoutReport(map);
	}

	@Override
	public Integer selectSubMerCashoutReportCount(Map map) {
		return subMerCashoutDao.selectSubMerCashoutReportCount(map);
	}

	@Override
	public Integer selectSubMerCashoutListNotPageCount(Map<String, Object> map) {
		return subMerCashoutDao.selectSubMerCashoutListNotPageCount(map);
	}

	@Override
	public List<SubMerCashout> seleSubMerCashoutReport(
			SubMerCashout subMerCashout) {
		return subMerCashoutDao.seleSubMerCashoutReport(subMerCashout);
	}

	@Override
	public List<SubMerCashout> selectSubMerCashoutAmt(Map map) {
		return subMerCashoutDao.selectSubMerCashoutAmt(map);
	}

	@Override
	public List<SubMerCashout> selectSubMerCashoutList2(
			SubMerCashout subMerCashout) {
		return subMerCashoutDao.selectSubMerCashoutList2(subMerCashout);
	}
}
