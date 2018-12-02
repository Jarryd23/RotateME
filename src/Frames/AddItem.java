/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.GoogleAPI.getCalendarService;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author jarrydbaillie
 */
public class AddItem extends javax.swing.JFrame {
    Connection conn = MySqlConnect.ConnectDB();
    PreparedStatement pst = null;
    ResultSet res = null;
    RotateME rot = null;
    
    public AddItem(RotateME r) {
        initComponents();
        myInitComponents();
        rot = r;
        txtDateIn.getEditor().setEditable(false);
        txtDateOut.getEditor().setEditable(false);
    }
    
    
public void refresh()
{
    rot.refreshTable();
}
   
protected void myInitComponents() {

    
        txtBarcode.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
            
                String code = txtBarcode.getText();
            
            String sql = "SELECT productID, prodName FROM tblProducts WHERE productID LIKE ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, code);
            res = pst.executeQuery();
                
            if(res.next())
                {
                    txtName.setText(res.getString("prodName"));
                    txtName.setEditable(false);
                }else{
                txtName.setText("");
                txtName.setEditable(true);
            }
            }
                catch(SQLException err)
                {
                
                }
            }
        });
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        txtBarcode = new javax.swing.JTextField();
        lblBarcode = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblProduct = new javax.swing.JLabel();
        lblDate1 = new javax.swing.JLabel();
        lblDate2 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        txtDateIn = new org.jdesktop.swingx.JXDatePicker();
        txtDateOut = new org.jdesktop.swingx.JXDatePicker();
        imgContainer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setMaximumSize(new java.awt.Dimension(474, 440));
        setMinimumSize(new java.awt.Dimension(474, 440));
        setResizable(false);
        setSize(new java.awt.Dimension(474, 440));
        getContentPane().setLayout(null);

        title.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        title.setText("Add New Batch");
        getContentPane().add(title);
        title.setBounds(140, 20, 180, 30);

        txtBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarcodeActionPerformed(evt);
            }
        });
        getContentPane().add(txtBarcode);
        txtBarcode.setBounds(141, 92, 170, 37);

        lblBarcode.setText("Barcode Number");
        getContentPane().add(lblBarcode);
        lblBarcode.setBounds(169, 70, 103, 16);
        getContentPane().add(txtName);
        txtName.setBounds(141, 157, 170, 37);

        lblProduct.setText("Product Name");
        getContentPane().add(lblProduct);
        lblProduct.setBounds(177, 135, 88, 16);

        lblDate1.setText("Date of Arrival");
        getContentPane().add(lblDate1);
        lblDate1.setBounds(178, 212, 91, 16);

        lblDate2.setText("Date of Expiration");
        getContentPane().add(lblDate2);
        lblDate2.setBounds(167, 272, 114, 16);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdd);
        btnAdd.setBounds(180, 350, 90, 40);

        txtDateIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateInActionPerformed(evt);
            }
        });
        getContentPane().add(txtDateIn);
        txtDateIn.setBounds(150, 240, 154, 26);
        getContentPane().add(txtDateOut);
        txtDateOut.setBounds(150, 300, 154, 26);

        imgContainer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SystemImages/back.png"))); // NOI18N
        getContentPane().add(imgContainer);
        imgContainer.setBounds(0, -70, 480, 540);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBarcodeActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        
                
        
                
        
        if (txtBarcode.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Barcode field cannot be empty!");
            return;
        }
        
        if (txtBarcode.getText().contains("-"))
        {
            JOptionPane.showMessageDialog(null, "Barcode cannot contain dashes (-)");
            return;
        }
        
        if (txtName.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Product Name field cannot be empty!");
            return;
        }
        if (txtDateIn.getDate()==null)
        {
            JOptionPane.showMessageDialog(null, "Please Specify an arrival date");
            return;
        }
        if (txtDateOut.getDate()==null)
        {
            JOptionPane.showMessageDialog(null, "Please Specify an expiry date");
            return;
        }
        
        
                DateTime startDate = new DateTime();
                java.util.Date expiry = txtDateOut.getDate();
                DateTime endDate = new DateTime(expiry);
                Days d = Days.daysBetween(startDate, endDate);
                int days = d.getDays();
                
        if (days<0)
        {
            int option = JOptionPane.showConfirmDialog(null, "This batch has expired, are you sure you want to add it?");
            
            if (option==JOptionPane.NO_OPTION||option==JOptionPane.CANCEL_OPTION)
            {
                return;
            }
                
        }
        
        int batchNum = 0;    
        String sql = "SELECT batchID FROM tblBatches WHERE batchID LIKE ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%"+txtBarcode.getText()+"-"+"%");
            res = pst.executeQuery();
            
            if (!res.first())
            {
                batchNum = 1;
                System.out.println("******** first batch ********");
                
                
                String sql1 = "INSERT INTO tblProducts (productID, prodName) VALUES (?,?);";
        
                    try{
           
            
                         pst = conn.prepareStatement(sql1);
                         pst.setString(1, txtBarcode.getText());
                         pst.setString(2, txtName.getText());
                         pst.executeUpdate();
            
                         System.out.println("product table affected");
            
                         }catch (SQLException e)
                         {
                             System.out.println(e);
                         }
                    
                
                    
              String sql2 = "INSERT INTO tblBatches (batchID, dateArrived, dateExpired) VALUES (?,?,?);";
                try {
                    java.util.Date jdate = txtDateIn.getDate();
                    java.sql.Date sdate = new java.sql.Date(jdate.getTime());
                    java.util.Date jdate2 = txtDateOut.getDate();
                    java.sql.Date sdate2 = new java.sql.Date(jdate2.getTime());

                    pst = conn.prepareStatement(sql2);
                    pst.setString(1, txtBarcode.getText() + "-" + batchNum);
                    pst.setDate(2, sdate);
                    pst.setDate(3, sdate2);
                    pst.executeUpdate();
                    System.out.println("batch table affected");

                } catch (SQLException e) {
                    System.out.println(e);
                }
                
                
                
                String sql3 = "INSERT INTO tblBatchProducts (batchID, productID) VALUES (?,?);";
                try {
                    
                    pst = conn.prepareStatement(sql3);
                    pst.setString(1, txtBarcode.getText() + "-" + batchNum);
                    pst.setString(2, txtBarcode.getText());
                    pst.executeUpdate();
                    System.out.println("batchProducts table affected");

                } catch (SQLException e) {
                    System.out.println(e);
                }
                
                
                
                
                
            }else{
                res.first();
                System.out.println("******** Not first batch ********");
                List<String> arr =new ArrayList<>();
               
                
                do
                {
                    arr.add(res.getString("batchID"));
                }while(res.next());
                
                int biggest = 0;
                for(int i=0;i<arr.size();i++)
                {
                    String s[] = arr.get(i).toString().split("-");
                    int trailingNum = Integer.parseInt(s[1]);
                    if (trailingNum>biggest)
                    {
                        biggest = trailingNum;
                    }
                    
                }
                
                batchNum = biggest+1;
                System.out.println("BATCH "+batchNum);
                String sql2 = "INSERT INTO tblBatches (batchID, dateArrived, dateExpired) VALUES (?,?,?);";
                try {
                    java.util.Date jdate = txtDateIn.getDate();
                    java.sql.Date sdate = new java.sql.Date(jdate.getTime());
                    java.util.Date jdate2 = txtDateOut.getDate();
                    java.sql.Date sdate2 = new java.sql.Date(jdate2.getTime());

                    pst = conn.prepareStatement(sql2);
                    pst.setString(1, txtBarcode.getText() + "-" + batchNum);
                    pst.setDate(2, sdate);
                    pst.setDate(3, sdate2);
                    pst.executeUpdate();
                    System.out.println("batch table affected");

                } catch (SQLException e) {
                    System.out.println("batch table error: "+e);
                }
                
                
                
                String sql3 = "INSERT INTO tblBatchProducts (batchID, productID) VALUES (?,?);";
                try {

                    pst = conn.prepareStatement(sql3);
                    pst.setString(1, txtBarcode.getText() + "-" + batchNum);
                    pst.setString(2, txtBarcode.getText());
                    pst.executeUpdate();
                    System.out.println("batchProducts table affected");

                } catch (SQLException e) {
                    System.out.println("batchProduct table error: "+e);
                }
               
               }
            
            
            
          }catch (SQLException e)
           {
             System.out.println(e);
           }
        
                GoogleAPI calendar = new GoogleAPI();
                com.google.api.services.calendar.Calendar service = null;
                try {
                    service = getCalendarService();
                } catch (IOException e) {
                    System.out.println(e);
                }
                java.util.Date jdate2 = txtDateOut.getDate();
                java.sql.Date dateformatted = new java.sql.Date(jdate2.getTime());
                calendar.addEvent(txtBarcode.getText(),txtName.getText(), Integer.toString(batchNum), dateformatted.toString(), service);
        
        
        txtBarcode.setText("");
        txtName.setText("");
        txtBarcode.requestFocus();
        refresh();
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtDateInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateInActionPerformed


    
    public static void main(String args[]) {
    
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JLabel imgContainer;
    private javax.swing.JLabel lblBarcode;
    private javax.swing.JLabel lblDate1;
    private javax.swing.JLabel lblDate2;
    private javax.swing.JLabel lblProduct;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtBarcode;
    private org.jdesktop.swingx.JXDatePicker txtDateIn;
    private org.jdesktop.swingx.JXDatePicker txtDateOut;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
