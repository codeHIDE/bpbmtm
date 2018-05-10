package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.MerProfitApplyDao;
import com.bypay.domain.MerProfitApply;

@Repository("merProfitApplyDao")
public class MerProfitApplyDaoImpl implements MerProfitApplyDao {
    @Inject
    private SqlSessionTemplate sqlSessionTemplate;
    
    public void insertApply(MerProfitApply merProfitApply){
        sqlSessionTemplate.insert("insertApply",merProfitApply);
    }

    @Override
    public int selectApplyCount(Map map) {
        return (Integer) sqlSessionTemplate.selectOne("selectMerProfitApplyCount",map);

    }

    @Override
    public List<MerProfitApply> selectApplyList(Map map) {
        return sqlSessionTemplate.selectList("selectMerProfitApplyList",map);

    }

    @Override
    public MerProfitApply selectApplyInfo(MerProfitApply merProfitApply) {
        return (MerProfitApply) sqlSessionTemplate.selectOne("selectApplyInfo",merProfitApply);
    }

    @Override
    public void updateApplyInfo(MerProfitApply merProfitApply) {
        sqlSessionTemplate.update("updateApplyInfo",merProfitApply);
    }
}
