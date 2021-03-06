/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.GoogleAPI.getCalendarService;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;


/**
 *
 * @author jarrydbaillie
 */
public class DeleteFrame extends javax.swing.JFrame {

    Connection conn = MySqlConnect.ConnectDB();
    PreparedStatement pst = null;
    ResultSet res = null;
    RotateME rot;
    public DeleteFrame(RotateME r) {
        initComponents();
        myInitComponents();
        rot = r;
    }
    
    public void refresh()
    {
        rot.refreshTable();
    }

    
    protected void myInitComponents() {
       

        txtBarcode1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
            
                String code = txtBarcode1.getText();
            
            String sql = "SELECT productID, prodName FROM tblProducts WHERE productID LIKE ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, code);
            res = pst.executeQuery();
                
            if(res.next())
                {
                    lblProduct.setText(res.getString("prodName"));
                    
                }else{
                lblProduct.setText("");
            }
            }
                catch(SQLException err)
                {
                
                }
            }
        });
        
        txtBarcode2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
            
                String code = txtBarcode2.getText();
            
            String sql = "SELECT productID, prodName FROM tblProducts WHERE productID LIKE ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, code);
            res = pst.executeQuery();
                
            if(res.next())
                {
                    lblProduct.setText(res.getString("prodName"));
                    
                }else{
                lblProduct.setText("");
            }
            }
                catch(SQLException err)
                {
                
                }
            }
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        txtBarcode2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        checkProduct = new javax.swing.JCheckBox();
        checkBatch = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        txtBarcode1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBatch = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        lblProduct = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        txtBarcode2.setEnabled(false);
        txtBarcode2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarcode2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 3, 18)); // NOI18N
        jLabel1.setText("Delete Item");

        checkProduct.setText("Product");
        checkProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkProductActionPerformed(evt);
            }
        });

        checkBatch.setSelected(true);
        checkBatch.setText("Batch");
        checkBatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBatchActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 3, 9)); // NOI18N
        jLabel2.setText("Warning! Deleting a product will delete all batches that the product belongs to!");

        txtBarcode1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarcode1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Product Barcode");

        jLabel4.setText("Product Barcode");

        txtBatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBatchActionPerformed(evt);
            }
        });

        jLabel5.setText("Batch #");

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lblProduct.setBackground(new java.awt.Color(0, 204, 0));
        lblProduct.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        lblProduct.setForeground(new java.awt.Color(153, 0, 255));
        lblProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(179, 179, 179))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(checkBatch)
                                .addGap(199, 199, 199))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(checkProduct)
                                .addGap(185, 185, 185))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(46, 46, 46))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBarcode2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBarcode1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(15, 15, 15)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnDelete)
                        .addGap(186, 186, 186))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(77, 77, 77)
                            .addComponent(lblProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkBatch)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(txtBarcode1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkProduct)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBarcode2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(11, 11, 11)
                .addComponent(btnDelete)
                .addGap(3, 3, 3)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtBarcode2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarcode2ActionPerformed
        
    }//GEN-LAST:event_txtBarcode2ActionPerformed

    private void txtBarcode1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarcode1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBarcode1ActionPerformed

    private void checkBatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBatchActionPerformed
        if (checkBatch.isSelected())
        {
            checkProduct.setSelected(false);
            txtBarcode2.setText("");
            txtBarcode2.setEnabled(true);
            txtBarcode1.setEnabled(true);
            txtBatch.setEnabled(true);
        }else{
            
            txtBarcode2.setEnabled(true);
        }
    }//GEN-LAST:event_checkBatchActionPerformed

    private void checkProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkProductActionPerformed
        if (checkProduct.isSelected())
        {
            checkBatch.setSelected(false);
            txtBarcode1.setText("");
            txtBarcode1.setEnabled(false);
            txtBatch.setText("");
            txtBatch.setEnabled(false);
            txtBarcode2.setEnabled(true);
        }else{
            
            txtBarcode1.setEnabled(true);
            txtBarcode1.setEnabled(true);
        }
    }//GEN-LAST:event_checkProductActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed


        if (checkBatch.isSelected())
        {
            System.out.println("in");
            String prodid = txtBarcode1.getText();
            String batch = txtBatch.getText();

            try {
                String sql = "DELETE FROM tblBatchProducts WHERE tblBatchProducts.productID = ? AND tblBatchProducts.batchID LIKE ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, prodid);
                pst.setString(2, "%-"+batch);
                pst.executeUpdate();
                
                
                
            } catch (SQLException e) {
                
                System.out.println("batchProduct error");
                System.out.println(e);
            }
            

            
            try {
                String sql = "DELETE FROM tblBatches WHERE tblBatches.batchID LIKE ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, prodid+"-"+batch);
                
                
                if(pst.executeUpdate()==0)
                {
                   
                    JOptionPane.showMessageDialog(null, "No batch found, make sure the barcode/batch number is correct.", "No Batch", JOptionPane.ERROR_MESSAGE, null);
                    return;
                }else{
                    
                    JOptionPane.showMessageDialog(null, "Product successfully deleted", "Success", JOptionPane.INFORMATION_MESSAGE, null);
                }
              
            } catch (SQLException e) {
                
                System.out.println("batch error");
                System.out.println(e);
                
            }
            
            com.google.api.services.calendar.Calendar service = null;
            GoogleAPI calendar = new GoogleAPI();
            try {
                service = getCalendarService();
            } catch (IOException e) {
                System.out.println(e);
            }

            try {
                calendar.deleteEvent(service, res.getString("prodName") + " is Expiring.", res.getString("prodName")+" ("+txtBarcode1.getText()+") " +"Batch Number " + txtBatch.getText() + " is expiring today");
            } catch (Exception e) {

            }
            
        }else
        {
            String prodid = txtBarcode2.getText();

            try {
                String sql = "DELETE FROM tblBatchProducts WHERE tblBatchProducts.productID = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, prodid);
                pst.executeUpdate();
                
                
                
                
            } catch (SQLException e) {
                System.out.println("batchProduct error");
                System.out.println(e);
                
            }
            
      
            
            try {
                String sql = "DELETE FROM tblProducts WHERE tblProducts.productID = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, prodid);
                
                
               if(pst.executeUpdate()==0)
                {
                   
                    JOptionPane.showMessageDialog(null, "No product found, make sure the barcode number is correct.", "No product", JOptionPane.ERROR_MESSAGE, null);
                }else{
                    
                    JOptionPane.showMessageDialog(null, "Product successfully deleted", "Success", JOptionPane.INFORMATION_MESSAGE, null);
                }
                
            } catch (SQLException e) {
                
                System.out.println("product error");
                System.out.println(e);
                
            }
            
            
            try {
                String sql = "DELETE FROM tblBatches WHERE tblBatches.batchID LIKE ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, prodid+"-%");
                pst.executeUpdate();
                
              
            } catch (SQLException e) {
                
                System.out.println("batch error");
                System.out.println(e);
                
            }
            
            com.google.api.services.calendar.Calendar service = null;
            GoogleAPI calendar = new GoogleAPI();
            try {
                service = getCalendarService();
            } catch (IOException e) {
                System.out.println(e);
            }

            try {
                calendar.deleteEventProd(service, res.getString("prodName") + " is Expiring.", res.getString("prodName")+" ("+txtBarcode2.getText()+") " +"Batch Number ");
            } catch (Exception e) {

            }

        }
        
        
        
        txtBarcode1.setText("");
        txtBarcode2.setText("");
        txtBatch.setText("");
        lblProduct.setText("");
        refresh();
        
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtBatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBatchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBatchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DeleteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JCheckBox checkBatch;
    private javax.swing.JCheckBox checkProduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblProduct;
    private javax.swing.JTextField txtBarcode1;
    private javax.swing.JTextField txtBarcode2;
    private javax.swing.JTextField txtBatch;
    // End of variables declaration//GEN-END:variables
}
