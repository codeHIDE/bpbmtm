package com.bypay.common.ibatis3.support;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.bypay.common.ibatis3.SqlSessionTemplate;

/**
 * BeanFactory that enables injection of iBatis Mapper Interfaces.
 * 
 * @author Eduardo Macarrón
 * @since 3.0
 * @see org.springframework.orm.ibatis3.SqlSessionTemplate
 */
public class MapperFactoryBean <T> implements FactoryBean<T>, InitializingBean {

    private DataSource dataSource;

    private Class<T> mapperInterface;

    private SqlSessionFactory sqlSessionFactory;

    private SqlSessionTemplate sqlSessionTemplate;

    public MapperFactoryBean() {
        super();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(mapperInterface, "Property 'MapperInterface' is required");

        if (sqlSessionFactory == null && sqlSessionTemplate == null) {
            throw new IllegalArgumentException("Property 'sqlSessionFactory' is required");

        } else if (sqlSessionTemplate == null) {
            sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        }

        if (dataSource != null) {
            sqlSessionTemplate.setDataSource(dataSource);
        }
    }

    public T getObject() throws Exception {
        return sqlSessionTemplate.getMapper(mapperInterface);
    }

    public Class<T> getObjectType() {
        return mapperInterface;
    }

    public boolean isSingleton() {
        return true;
    }

}
