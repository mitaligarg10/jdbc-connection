package com.stackroute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;


public class JdbcDaoImpl {
    public Circle getCircle(int circleId) {
        Connection conn1 = null;
        Circle circle = null;

        try {
            String url1 = "jdbc:mysql://localhost:3306/jdbcdemo";
            String user = "root";
            String password = "Root@123";

            conn1 = DriverManager.getConnection(url1, user, password);
            PreparedStatement ps = conn1.prepareStatement("SELECT * FROM CIRCLE where id = ?");
            ps.setInt(1, circleId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                circle = new Circle(circleId, rs.getString("name"));
            }
            rs.close();
            ps.close();
            return circle;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn1.close();
            } catch (SQLException e) {
            }
        }
    }
}
