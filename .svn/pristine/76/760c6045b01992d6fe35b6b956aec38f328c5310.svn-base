package com.bypay.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.SendFileDao;
import com.bypay.domain.SendFile;

@Repository("SendFileDao")
public class SendFileDaoImpl implements SendFileDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<SendFile> selectSendFileList(SendFile sendFile) {
		return sqlSessionTemplate.selectList("selectSendFileList",sendFile);
	}

}
