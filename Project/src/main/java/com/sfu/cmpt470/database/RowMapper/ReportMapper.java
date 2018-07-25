package com.sfu.cmpt470.database.RowMapper;
import com.sfu.cmpt470.database.DatabaseConnector;

import java.sql.SQLException;

public class ReportMapper {
    public String getXml(String table) throws SQLException, ClassNotFoundException {
        DatabaseConnector con = new DatabaseConnector();
        String query = String.format("select * from %s", table);
        con.supplyQuery(query);
        String xml = con.queryReport();
        con.disconnect();
        return xml;
    }


}


