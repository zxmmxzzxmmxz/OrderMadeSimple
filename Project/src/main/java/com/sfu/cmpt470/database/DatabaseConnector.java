package com.sfu.cmpt470.database;

import com.sfu.cmpt470.properties.Database;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;

public class DatabaseConnector {
    private Connection _connection;
    private PreparedStatement _pStatement;
    public DatabaseConnector() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        _connection = DriverManager.getConnection(Database.CONNECTION_URL, Database.getDBProperties());
    }

    public void supplyQuery(String sql) throws SQLException {
        _pStatement = _connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }

    //use with caution, since keys are not generated all the time
    public ArrayList<Long> getInsertedKeys() throws SQLException {
        ArrayList<Long> ids = new ArrayList<>();
        ResultSet rsKey = _pStatement.getGeneratedKeys();
        while(rsKey.next()) {
            ids.add(rsKey.getLong(1));
        }
        return ids;
    }

    public void setInt(int data, int index) throws SQLException {
        _pStatement.setInt(index, data);
    }

    public void setTime(OffsetDateTime time, int index) throws SQLException {
        _pStatement.setObject(index, time);
    }

    public void setString(String string, int index) throws SQLException {
        _pStatement.setString(index, string);
    }


    public void setLong(long number, int index) throws SQLException {
        _pStatement.setLong(index, number);
    }

    public void disconnect() throws SQLException {
        _connection.close();
    }

    public <T> ArrayList<T> queryList(RowMapper<T> rowMapper) throws SQLException {
        ResultSet resultSet = _pStatement.executeQuery();
        ArrayList<T> result = new ArrayList<>();

        while(resultSet.next()){
            result.add(rowMapper.mapRow(resultSet,resultSet.getRow()));
        }
        return result;
    }

    public void executeUpdate() throws SQLException {
        _pStatement.executeUpdate();
    }

    public <T> T queryOneRecord(RowMapper<T> rowMapper) throws SQLException {
        ResultSet rs = _pStatement.executeQuery();
        if(rs.next()){
            return rowMapper.mapRow(rs,rs.getRow());
        }
        throw new SQLException(String.format("shouldn't have more than one result to be returned"));
    }

}
