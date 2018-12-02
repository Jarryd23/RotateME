package Frames;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.text.*;
import java.util.Date;
public class MailApp extends JFrame
{
 Connection conn = MySqlConnect.ConnectDB();
 PreparedStatement pst = null;
 ResultSet res = null;
 String message = "";
 String to;
 int days;
    public MailApp()
    {
        
     send();
        
    }
    
    public void send() 
    {
        
        String sql = "SELECT * FROM tblEmailInfo ORDER BY id DESC LIMIT 1";
        try{
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            
            if (!res.next())
        {
            System.out.println("nothing");
            return;
        }else{
                
                to = res.getString("address");
                days = res.getInt("timeLimit");
                System.out.println(days);
            }
            
        }catch (SQLException e)
        {
            
        }
        
        
        
        try{
            String sql2 =  "SELECT * FROM "
                + "tblBatches a INNER JOIN "
                + "tblBatchProducts b on a.batchID = b.batchID "
                + "INNER JOIN tblProducts c "
                + "on b.productID = c.productID WHERE NOW() > dateExpired - INTERVAL "+days+" DAY";
            
            pst = conn.prepareStatement(sql2);
            res = pst.executeQuery();
            
            while(res.next())
            {
                String batch = res.getString("batchID");
                String[] arr = batch.split("-");
                String batchFinal = arr[1];
               
                message+= res.getString("ProdName")+ " | Batch #"+batchFinal+" | "+res.getString("dateExpired")+"\n";
            }
            
        }catch (SQLException e)
        {
            System.out.println(e);
        }
        
        
       
        java.util.Date d = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("The current date is "+sdf.format(d));
    
    
      
                
                String user = "rotatememail@gmail.com";
                String pass = "rotateME23";
                 
                SendMail.send(to,"RotateME Batch Report - "+sdf.format(d), "Following products expiring in the next "+days+" days: \n\n"+message, user, pass);
     }  
}
    