package uz.micros.jstore.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DbManager {
    private static Connection connect(){
        System.out.println("JDBC Connection Test");

        Connection res = null;

        try {
            Class.forName("org.postgresql.Driver");
            //Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

        try {
             res = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/jstore",
                    "postgres", "dev1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static List<Map<String, Object>>  runQuery(String query) {
        Connection conn = connect();
        List<Map<String, Object>> list = null;
        String err = null;

        if (conn != null) {
            try{
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);

                list = parseResultSet(rs);
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }

        return list;
    }

    private static List<Map<String, Object>> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> rows = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            Map<String, Object> columns = new LinkedHashMap<String, Object>();

            for (int i = 1; i <= columnCount; i++) {
                columns.put(metaData.getColumnLabel(i), resultSet.getObject(i));
            }

            rows.add(columns);
        }

        return rows;
    }
}
