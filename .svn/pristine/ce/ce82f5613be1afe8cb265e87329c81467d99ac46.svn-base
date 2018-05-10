package com.bypay.common.ibatis3.transaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.logging.jdbc.ConnectionLogger;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;

public class SpringManagedTransaction implements Transaction {

    private final Connection connection;

    private final boolean shouldManageConnection;

    public SpringManagedTransaction(Connection connection) {
        Assert.notNull(connection, "Property 'connection' is required");

        this.connection = connection;

        Connection nonLoggingConnection;

        // unwrap the connection if it is a ConnectionLogger for use with Spring
        if (Proxy.isProxyClass(connection.getClass())) {
            InvocationHandler handler = Proxy.getInvocationHandler(connection);

            if (handler instanceof ConnectionLogger) {
                nonLoggingConnection = ((ConnectionLogger) handler).getConnection();
            }
            else {
                nonLoggingConnection = connection;
            }
        }
        else {
            nonLoggingConnection = connection;
        }

        // This connection could have been created with any DataSource, not just the one that is
        // registered in the iBatis Environment. So, rather than passing a DataSource into this
        // Transaction, just check the bound resources for a DataSource and use that in the call to
        // isConnectionTransactional. If there is no DataSource bound, there is no Spring
        // transaction; if this Connection does not match the one in the current transaction, this
        // is a different Spring transactional context. In either case, this Transaction should
        // manage the connection. If there is a Spring transaction in progress, this Transaction
        // will no-op all methods.
        // Note this also assumes that iBatis does not allow changing the Transaction or the
        // Connection once an SqlSession has been created, which is consistent with the default
        // implemenation.
        boolean manageConnection = true;

        for (Object o : TransactionSynchronizationManager.getResourceMap().keySet()) {
            if (o instanceof DataSource) {
                manageConnection = !DataSourceUtils.isConnectionTransactional(nonLoggingConnection, (DataSource) o);
            }
        }

        this.shouldManageConnection = manageConnection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void commit() throws SQLException {
        if (shouldManageConnection) {
            connection.commit();
        }
    }

    public void rollback() throws SQLException {
        if (shouldManageConnection) {
            connection.rollback();
        }
    }

    public void close() throws SQLException {
        if (shouldManageConnection) {
            connection.close();
        }
    }
}
