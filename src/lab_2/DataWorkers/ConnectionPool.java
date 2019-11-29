package lab_2.DataWorkers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private String url;
    private String user;
    private String password;
    private List<Connection> availableConns = new ArrayList<>();
    private List<Connection> usedConns = new ArrayList<>();

    public ConnectionPool(String url, String driver, String user, String password, int initConnCnt) {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.url = url;
        this.user = user;
        this.password = password;

        for (int i = 0; i < initConnCnt; i++) {
            availableConns.add(getConnection());
        }
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public  Connection retrieve() throws SQLException {
        Connection newConn = null;
        if (availableConns.size() == 0) {
            newConn = getConnection();
        } else {
            newConn = (Connection) availableConns.get(availableConns.size() - 1);
            availableConns.remove(newConn);
        }
        usedConns.add(newConn);
        return newConn;
    }

    public  void putback(Connection c) throws NullPointerException {
        if (c != null) {
            if (usedConns.remove(c)) {
                availableConns.add(c);
            } else {
                throw new NullPointerException("Connection not in the usedConns");
            }
        }
    }
}

