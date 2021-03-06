package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.SumMerProfitDao;
import com.bypay.domain.SumMerProfit;

@Repository("sumMerProfitDao")
public class SumMerProfitDaoImpl implements SumMerProfitDao {
    @Inject
    private SqlSessionTemplate sqlSessionTemplate;
    
    @Override
    public void insertMerProfit(SumMerProfit sumMerProfit) {
        sqlSessionTemplate.insert("insertMerProfit",sumMerProfit);

    }

    @Override
    public List<Map> getSumList(Map<String, String> map) {
        return sqlSessionTemplate.selectList("getSumList", map);

    }

    @Override
    public List<SumMerProfit> selectSumMerList(Map map) {
        return sqlSessionTemplate.selectList("selectSumMerList",map);
    }

    @Override
    public Integer selectSumMerCount(Map map) {
        return (Integer) sqlSessionTemplate.selectOne("selectSumMerCount",map);
    }

    @Override
    public SumMerProfit selectById(SumMerProfit sumMerProfit) {
        return (SumMerProfit) sqlSessionTemplate.selectOne("selectById", sumMerProfit);
    }

    @Override
    public void updateProfit(SumMerProfit sumMerProfit) {
        sqlSessionTemplate.update("updateProfit", sumMerProfit);
    }

    @Override
    public Map getApplyList(Map map) {
        return (Map) sqlSessionTemplate.selectOne("getApplyList", map);
    }

}
