package com.bypay.dao;

import java.util.List;

import com.bypay.domain.SendFile;

public interface SendFileDao {
	List<SendFile> selectSendFileList(SendFile sendFile);
}
