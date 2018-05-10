package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.MobileUserDao;
import com.bypay.domain.MobileUser;
@Repository("mobileUserDao")
public class MobileUserDaoImpl implements MobileUserDao {
	@Inject
	  private SqlSessionTemplate sqlSessionTemplate;

	  // get set
	  public SqlSessionTemplate getSqlSessionTemplate() {
	    return sqlSessionTemplate;
	  }

	  public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
	    this.sqlSessionTemplate = sqlSessionTemplate;
	  }
	  @Override
	  public int insertMobileUser(MobileUser mobileUser) {
	    return sqlSessionTemplate.insert("com.bypay.dao.MobileUserDao.insertMobileUser", mobileUser);
	  }
	  
	  @Override
	  public int deleteMobileUser(MobileUser mobileUser) {
	    return sqlSessionTemplate.delete("com.bypay.dao.MobileUserDao.deleteMobileUser", mobileUser);
	  }
	  
	  @Override
		public MobileUser getMobileUser(MobileUser mobileUser) {
			return (MobileUser) sqlSessionTemplate.selectOne("com.bypay.dao.MobileUserDao.selectMobileUser",mobileUser);
		}
	  
	  @Override
		public MobileUser getMobileUserById(MobileUser mobileUser) {
			return (MobileUser) sqlSessionTemplate.selectOne("com.bypay.dao.MobileUserDao.selectMobileUserById",mobileUser);
		}
	  
	  /**
		 * 修改设备最近登录时间
		 */
		@Override
		public void updateUserEavFail(MobileUser mobileUser) {
			sqlSessionTemplate.update("com.bypay.dao.MobileUserDao.updateUserEavFail", mobileUser); 
		}
		
		@Override
		public void updateUserByLastLoginTime(MobileUser mobileUser) {
			sqlSessionTemplate.update("com.bypay.dao.MobileUserDao.updateUserByLastLoginTime", mobileUser); 
		}
		
		@Override
		public void updateMobileUser(MobileUser mobileUser) {
			sqlSessionTemplate.update("com.bypay.dao.MobileUserDao.updateMobileUser", mobileUser); 
		}
		
		@Override
		public boolean updateTerminal(MobileUser mobileUser) {
			if(sqlSessionTemplate.update("com.bypay.dao.MobileUserDao.updateTerminal", mobileUser)>0)
				return true;
			else
				return false;
		}
		
		@Override
		public List<MobileUser> selectMobileUsers(Map map) {
			return sqlSessionTemplate.selectList("com.bypay.dao.MobileUserDao.selectMobileUsers",map);
		}
		@Override
		public Integer selectMobileUsersCount(Map map) {
			return (Integer) sqlSessionTemplate.selectOne("com.bypay.dao.MobileUserDao.selectMobileUsersCount",map);
		}
		
		@Override
		public boolean updateMemberId(MobileUser mobileUser){
			if(sqlSessionTemplate.update("com.bypay.dao.MobileUserDao.updateMemberId", mobileUser)>0)
				return true;
			else
				return false;
		}
}
