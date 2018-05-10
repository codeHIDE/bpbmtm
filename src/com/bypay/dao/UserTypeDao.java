package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.UserType;

public interface UserTypeDao {

	 Integer selectUserTypeCount(Map map);
	  
	 List<UserType> selectUserType(Map map);
	 
	 int insertUserType(UserType userType);
}
