package com.bypay.service.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bypay.domain.TractInfo;
import com.bypay.domain.TractStatictis;
import com.bypay.service.OrderInfoService;
import com.bypay.service.TractInfoService;
import com.bypay.service.TractStatictisService;
import com.bypay.util.DateUtil;
import com.google.common.collect.Maps;

@Component("statisticsTimingComponent")
public class StatisticsTimingTask {
  @Autowired
  private TractInfoService tractInfoService;
  @Autowired
  private OrderInfoService orderInfoService;
  @Autowired
  private TractStatictisService tractStatictisService;

  private static String STATUS = "1";// 正在使用中通道

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public void execute() {
    logger.info("-----------开始统计通道手续费-------------");
    TractInfo tractInfo = new TractInfo();
    tractInfo.setStatus(STATUS);
    Map<String, Object> map = Maps.newHashMap();
    map.put("tractInfo", tractInfo);

    List<TractInfo> tractInfos = tractInfoService.selectTractInfoByStatus(map);
    for (TractInfo tractInfo2 : tractInfos) {
      // 查询手续费统计数据
      Map<String, Object> param = Maps.newHashMap();
      param.put("transMerId", tractInfo2.getTransMerId());
      param.put("settleDate", DateUtil.getDate(DateUtil.getPreviousWeekDay(new Date()), "yyyyMMdd"));
      Map<String, Object> result = orderInfoService.selectTotalTractCost(param);
      // 修改统计数据
      TractStatictis tractStatictis = new TractStatictis();
      tractStatictis.setTractId(tractInfo2.getTractId());
      tractStatictis.setStatictisDate(DateUtil.getDate(DateUtil.getPreviousWeekDay(new Date()),
          "yyyyMMdd"));
      tractStatictis.setReserved1(String.valueOf(result.get("sum")));

      tractStatictisService.updateTractStatictis(tractStatictis);
    }

    logger.info("-----------结束统计通道手续费-------------");
  }

}
