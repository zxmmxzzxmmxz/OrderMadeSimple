package com.sfu.cmpt470.database;

import java.sql.ResultSet;
import java.sql.SQLException;

interface RowMapper {
    Object mapRow(ResultSet rs, int rowNum) throws SQLException;
}
