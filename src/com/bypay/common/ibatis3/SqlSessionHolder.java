package com.bypay.common.ibatis3;

import org.apache.ibatis.session.SqlSession;

import org.springframework.transaction.support.ResourceHolderSupport;

public final class SqlSessionHolder extends ResourceHolderSupport {

    private final SqlSession session;

    public SqlSessionHolder(SqlSession session) {
        assert session != null;
        this.session = session;
    }

    public SqlSession getSqlSession() {
        return session;
    }

}
