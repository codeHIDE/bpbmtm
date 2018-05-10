package com.bypay.common.ibatis3.transaction;

import java.sql.SQLException;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

public class SimpleTransactionSupport {
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;
    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public SimpleTransactionSupport() {}
    public SimpleTransactionSupport(SqlSessionFactory sqlSessionFactory) {
        setSqlSessionFactory(sqlSessionFactory);
    }
    public void start(ExecutorType executorType,TransactionIsolationLevel transactionIsolationLevel) throws SQLException{
    	session = sqlSessionFactory.openSession(executorType,transactionIsolationLevel);
    	session.getConnection().setAutoCommit(false);
    }
    public void start() throws SQLException{
    	session = sqlSessionFactory.openSession(ExecutorType.BATCH,TransactionIsolationLevel.READ_UNCOMMITTED);
    	session.getConnection().setAutoCommit(false);
    }
    public void commit() throws SQLException {
    	session.commit();
    }
    public void rollback() throws SQLException {
    	session.rollback();
    }
    public void end() throws SQLException {
    	session.close();
    }
	public SqlSession getSession() {
		return session;
	}
	public void setSession(SqlSession session) {
		this.session = session;
	}
}
