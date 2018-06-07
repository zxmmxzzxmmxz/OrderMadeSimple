package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.database.DatabaseConnector;

import java.sql.SQLException;

class BaseDAO<T> {
    DatabaseConnector _db;

    BaseDAO() throws SQLException, ClassNotFoundException {
        _db = new DatabaseConnector();
    }

    void disconnect() throws SQLException {
        if(_db != null){
            _db.disconnect();
        }
    }
}
