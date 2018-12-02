/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author jarrydbaillie
 */
public class MySqlConnect {
    Connection conn = null;
    public static Connection ConnectDB(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/firstDB","root","root");
            System.out.println("Connection to database successful");
            return conn;
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
}
