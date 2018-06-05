package com.sfu.cmpt470.database;

import com.sfu.cmpt470.properties.Database;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnector {
    private Connection _connection;
    private PreparedStatement _pStatement;
    public DatabaseConnector() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        _connection = DriverManager.getConnection(Database.CONNECTION_URL, Database.getDBProperties());
    }

    public void supplyQuery(String sql) throws SQLException {
        _pStatement = _connection.prepareStatement(sql);
    }

    public void setInt(int data, int index) throws SQLException {
        _pStatement.setInt(index, data);
    }

    public void disconnect() throws SQLException {
        _connection.close();
    }

    public ArrayList<Object> executeQueryList(RowMapper rowMapper) throws SQLException {
        ResultSet resultSet = _pStatement.executeQuery();
        ArrayList<Object> result = new ArrayList<>();

        while(resultSet.next()){
            result.add(rowMapper.mapRow(resultSet,resultSet.getRow()));
        }
        return result;
    }

}
