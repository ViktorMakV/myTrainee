package com.service;

import com.dao.BookDAOImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.BasicConfigurator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Viktor Makarov
 */
@Slf4j
public class ConnectionPool {
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static final BasicDataSource DATA_SOURCE = new BasicDataSource();

    static {
        URL = "jdbc:h2:mem:default";
        USER = "sa";
        PASSWORD = "";
        initDB();
        configDataSource();

        //This configures logger.
        BasicConfigurator.configure();

        //Invoke hibernate
        new BookDAOImpl(new AuthorServiceImpl());
    }

    private static void configDataSource() {
        DATA_SOURCE.setUrl(URL);
        DATA_SOURCE.setUsername(USER);
        DATA_SOURCE.setPassword(PASSWORD);
        DATA_SOURCE.setMinIdle(5);
        DATA_SOURCE.setMaxIdle(10);
        DATA_SOURCE.setMaxOpenPreparedStatements(100);
    }

    private static void initDB() {
        String INIT = "jdbc:h2:mem:default;INIT=runscript from " +
                "'classpath:create.sql'\\;" +
                "runscript from " +
                "'classpath:init.sql';DB_CLOSE_DELAY=1000";
        try {
            Class.forName("org.h2.Driver");
            DriverManager.getConnection(INIT, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            log.debug("Driver class not found.");
        } catch (SQLException e) {
            log.debug("Failed to init database.");
        }
    }

    public static Connection getConnection() {
        try {
            return DATA_SOURCE.getConnection();
        } catch (SQLException e) {
            log.debug("Exception while getting connection");
        }
        return null;
    }
}
