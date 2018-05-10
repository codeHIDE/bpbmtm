package com.bypay.dao;

import java.util.Map;

public interface ProcedureDao {
	Integer getSequence(Map map) throws Exception;
	
	String getOrderId() throws Exception;
}
