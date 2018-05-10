package com.bypay.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import com.bypay.dao.TractStatictisDao;
import com.bypay.domain.TractStatictis;
import com.bypay.service.TractStatictisService;

@Service("tractStatictisService")
public class TractStatictisServiceImpl implements TractStatictisService {

  @Inject
  private TractStatictisDao tractStatictisDao;

  @Override
  public  Map<String,Object> selectTractStatictisCount(TractStatictis tractStatictis) {
    return tractStatictisDao.selectTractStatictisCount(tractStatictis);
  }

  @Override
  public List<TractStatictis> selectTractStatictisList(Map map) {
    List<TractStatictis> list = null;
    try {
      list = tractStatictisDao.selectTractStatictisList(map);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // 通道跑批用
  @Override
  public int insertTractStatictisCount(TractStatictis tractStatictis) {
    return tractStatictisDao.insertTractStatictisCount(tractStatictis);
  }

  @Override
  public Integer updateTractStatictis(TractStatictis tractStatictis) {
    return tractStatictisDao.updateTractStatictis(tractStatictis);
  }
  
  @Override
	public TractStatictis getTractStatictis(TractStatictis tractStatictis) {
		return tractStatictisDao.getTractStatictis(tractStatictis);
	}

}
