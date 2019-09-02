package org.mybatis.generator.ui.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.ui.model.DatabaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbUtils {
    private static final Logger LOG = LoggerFactory.getLogger(DbUtils.class);
    private static final int DB_CONNECTION_TIMEOUTS_SECONDS = 1;

    public static Connection getConnection(DatabaseConfig config) throws Exception {
        String url = getConnectionUrlWithSchema(config);

        Properties props = new Properties();
        props.setProperty("user", config.getUsername());
        props.setProperty("password", config.getPassword());

        // 1s数据库连接超时
        DriverManager.setLoginTimeout(DB_CONNECTION_TIMEOUTS_SECONDS);

        Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        Connection connection = driver.connect(url, props);
        LOG.info("getConnection, connection url: {}", url);
        return connection;
    }

    public static List<String> getTableNames(DatabaseConfig config) throws Exception {
        String url = getConnectionUrlWithSchema(config);
        LOG.info("getTableNames, connection url: {}", url);
        Connection connection = getConnection(config);
        try {
            List<String> tables = new ArrayList<>();
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(config.getSchema(), null, "%", new String[]{"TABLE", "VIEW"});
            while (rs.next()) {
                tables.add(rs.getString(3));
            }
            return tables;
        } finally {
            connection.close();
        }
    }

    public static String getConnectionUrlWithSchema(DatabaseConfig dbConfig)
            throws ClassNotFoundException {
        String connectionUrl = String.format(
                "jdbc:mysql://%s:%s/%s?allowMultiQueries=true&characterEncoding=UTF-8&autoReconnect=true&rewriteBatchedStatements=true",
                dbConfig.getHost(), dbConfig.getPort(), dbConfig.getSchema());
        LOG.info("getConnectionUrlWithSchema, connection url: {}", connectionUrl);
        return connectionUrl;
    }
}
