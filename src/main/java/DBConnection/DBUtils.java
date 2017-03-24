/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import Models.FormGroupModel;
import Models.FormModel;
import Providers.FormProvider;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * Video:  https://www.youtube.com/watch?v=DN3VAZdhLik
 */
public class DBUtils {
    public static Connection getConnection(){
        if(connection != null){
            return connection;
        }else{
            try {
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/approval_enterprise";
                String user = "root";
                String password = "root";
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
    
    public static PreparedStatement getPreparedStatement(String sql){
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = getConnection();
            ps = con.prepareStatement(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
        return ps;
    }
}
