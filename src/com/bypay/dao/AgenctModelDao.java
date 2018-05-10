package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.*;
public interface AgenctModelDao {
	List<AgenctModel> selectAgenctModelAll(Map map);
	Integer selectAgenctModelCount();
	boolean deleteAgenctModel(AgenctModel a);
	boolean insertAgenctModel(AgenctModel a);
	boolean updateAgenctModel(AgenctModel a);
	AgenctModel selectAgenctModel(AgenctModel a);
}
