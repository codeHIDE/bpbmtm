package com.bypay.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.bypay.dao.TractInfoDao;
import com.bypay.domain.TractInfo;
import com.bypay.domain.TractStatictis;
import com.bypay.service.TractInfoService;
import com.bypay.service.TractStatictisService;
import com.bypay.util.DateUtil;


@Service("tractInfoService")
public class TractInfoServiceImpl implements TractInfoService {

	@Inject
	private TractInfoDao tractInfoDao;
	
	@Inject
	private TractStatictisService tractStatictisService;
	private TractStatictis tractStatictis;
	
	private TractInfo tractInfo;
	private List<TractInfo> tractInfoList;
	
	@Override //添加通道
	public int insertTractInfo(TractInfo tractInfo) {
		return tractInfoDao.insertTractInfo(tractInfo);
	}
	
	// 查询添加时候的通道号是否存在
	public TractInfo selectTractInfoRepeat(String transMerId) {
		return tractInfoDao.selectTractInfoRepeat(transMerId);
	}

	
	// 通道查询 查询所有通道信息
	public List<TractInfo> selectTractInfoList(Map map) {
		List<TractInfo> list = null;
		try {
			list = tractInfoDao.selectTractInfoList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 查询通道的条数 分页用
	public int selectTractInfoCount(TractInfo TractInfo) {
		return tractInfoDao.selectTractInfoCount(TractInfo);
	}
	
	// 通道详情
	public TractInfo selectTractById(String tractId) {
		return tractInfoDao.selectTractById(tractId);
	}

	// 通道审批更新
	public int updateTractInfo(TractInfo tractInfo) {
		return tractInfoDao.updateTractInfo(tractInfo);
	}

	// 修改通道信息
	public int updateTract(TractInfo tractInfo) {
		return tractInfoDao.updateTract(tractInfo);
	}

	// 通道 暂停
	public int updateTractInfoOFF(TractInfo tractInfo) {
		return tractInfoDao.updateTractInfoOFF(tractInfo);
	}

	//跑批用
	@Override
	public List<TractInfo> selectTractInfoAllSettle(TractInfo tractInfo) {
		return tractInfoDao.selectTractInfoAllSettle(tractInfo);
	}
	
	//跑批启动Service.
	//查询通道信息跑批用
	public void selectAllTractInfoByStatictis(){
		tractInfoList=selectTractInfoAllSettle(tractInfo);
		for(int i=0; i<tractInfoList.size();i++){
			tractStatictis=new TractStatictis();
			tractStatictis.setTractId(tractInfoList.get(i).getTractId());
			tractStatictis.setStatictisDate(DateUtil.getDate(DateUtil.getNextDay(new Date()), "yyyyMMdd"));
			if(tractInfoList.get(i).getTractQuota()==null||tractInfoList.get(i).getTractQuota()==""){
				tractStatictis.setPayAmt("0");
			}else{
				tractStatictis.setPayAmt(tractInfoList.get(i).getTractQuota());
			}
			tractStatictis.setUseAmt("0");
			tractStatictis.setRefundAmt("0");
			int insert=tractStatictisService.insertTractStatictisCount(tractStatictis);
			if(insert>0){
				System.out.println("插入成功！");
			}
			
		}
		
	}

	@Override
	public List<TractInfo> selectTractInfoByRatesType(TractInfo tractInfo) {
		return tractInfoDao.selectTractInfoByRatesType(tractInfo);
	}

  @Override
  public List<TractInfo> selectTractInfoByStatus(Map<String, Object> map) {
    return tractInfoDao.selectTractInfoByStatus(map);
  }
  
  @Override
	public Integer updateReserved(TractInfo tractInfo) {
		return tractInfoDao.updateReserved(tractInfo);
	}
	
	public TractInfo selectTractByMerId(String tractId) {
		return tractInfoDao.selectTractByMerId(tractId);
	}
}
