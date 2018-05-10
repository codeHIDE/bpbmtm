package com.bypay.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.TractStatictisDao;
import com.bypay.domain.TractStatictis;

@Repository("tractStatictisDao")
public class TractStatictisDaoImpl implements TractStatictisDao {

  @Inject
  private SqlSessionTemplate sqlSessionTemplate;

  @SuppressWarnings("unchecked")
  @Override
  public Map<String, Object> selectTractStatictisCount(TractStatictis tractStatictis) {
    return (HashMap<String, Object>) sqlSessionTemplate.selectOne("selectTractStatictisCount",
        tractStatictis);
  }

  @Override
  public List<TractStatictis> selectTractStatictisList(Map map) {
    return sqlSessionTemplate.selectList("selectTractStatictisList", map);
  }

  // 跑批插入用
  @Override
  public int insertTractStatictisCount(TractStatictis tractStatictis) {
    return sqlSessionTemplate.insert("insertTractStatictisCount", tractStatictis);
  }

  @Override
  public Integer updateTractStatictis(TractStatictis tractStatictis) {
    return sqlSessionTemplate.update("updateTractStatictis", tractStatictis);
  }
  
  @Override
	public TractStatictis getTractStatictis(TractStatictis tractStatictis) {
		return (TractStatictis) sqlSessionTemplate.selectOne("getTractStatictis", tractStatictis);
	}

	@Override
	public int updateTractStatictisReserved(TractStatictis tractStatictis) {
		return sqlSessionTemplate.update("updateTractStatictisReserved", tractStatictis);
	}

}
