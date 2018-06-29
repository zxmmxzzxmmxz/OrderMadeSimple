package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.database.DatabaseConnector;

import java.sql.SQLException;

class BaseDAO {
    DatabaseConnector _db;

    BaseDAO(DatabaseConnector connector) throws SQLException, ClassNotFoundException {
        _db = connector;
    }

}
