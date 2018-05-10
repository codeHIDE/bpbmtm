package com.bypay.common.ibatis3;

import org.springframework.dao.UncategorizedDataAccessException;


/**
 * mybatis specific subclass of UncategorizedDataAccessException, for mybatis system errors that do
 * not match any concrete <code>org.springframework.dao</code> exceptions.
 * <p>
 * 
 * In mybatis 3 <code>org.apache.ibatis.exceptions.PersistenceException</code> is a <code>RuntimeException</code>,
 * but using this wrapper class to bring everything under a single hierarchy will be easier for client code to
 * handle.
 */
public class MybatisSystemException extends UncategorizedDataAccessException {

    private static final long serialVersionUID = -5284728621670758939L;

    public MybatisSystemException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
