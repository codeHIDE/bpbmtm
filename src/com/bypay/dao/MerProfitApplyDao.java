package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.MerProfitApply;

public interface MerProfitApplyDao {

    public void insertApply(MerProfitApply merProfitApply);
    
    public int selectApplyCount(Map map);
    
    public List<MerProfitApply> selectApplyList(Map map);

    public MerProfitApply selectApplyInfo(MerProfitApply merProfitApply);
    
    public void updateApplyInfo(MerProfitApply merProfitApply);
    
}
