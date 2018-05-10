package com.bypay.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bypay.dao.CssConfigDao;
import com.bypay.domain.CssConfig;
import com.bypay.domain.TractInfo;
import com.bypay.service.CssConfigService;


@Service("cssConfigService")
public class CssConfigServiceImpl implements CssConfigService {

	
	@Inject
	private CssConfigDao cssConfigDao;
	
	
	//添加对应表
	@Override
	public int insertCssConfig(CssConfig cssConfig) {
		return cssConfigDao.insertCssConfig(cssConfig);
	}


	@Override
	public List<CssConfig> selectCssConfigList(Map map) {
		return cssConfigDao.selectCssConfigList(map);
	}


	@Override
	public int selectCssConfigListCount(CssConfig cssConfig) {
		return cssConfigDao.selectCssConfigListCount(cssConfig);
	}


	@Override
	public int deleteCssConfig(CssConfig cssConfig) {
		return cssConfigDao.deleteCssConfig(cssConfig);
	}

	
	

}
