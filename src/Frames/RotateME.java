package Frames;

//import static Frames.GoogleAPI.getCalendarService;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.print.*;
import java.text.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.net.URL;
import org.joda.time.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.lang.Object;
import java.net.URLConnection;
/**
 *
 * @author jarrydbaillie
 */
public class RotateME extends javax.swing.JFrame {

    Connection conn = MySqlConnect.ConnectDB();
    PreparedStatement pst = null;
    ResultSet res = null;
    static Date date;
    static String time ="";
    static int stopped;
    String[] columnNames = {"Barcode", "Product Name", "Batch Number", "Date of Arrival", "Expiry Date"};
    
    
    
    public RotateME() {
        setVisible(true);
        initComponents();
        showTableData(); 
        getEmailData();
        
        
    }
    
    

    
 public void getEmailData()
    {
        String sql = "SELECT * FROM tblEmailInfo ORDER BY id DESC LIMIT 1";
        
        
        try{
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            res.next();
            
            time = res.getString("sendTime");
            stopped = res.getInt("stopped");
            
        }catch (SQLException e)
        {
            System.out.println(e);
        }
        

    }
    
   public static void sendEmail()
   {
      
           MailApp m = new MailApp();
       
       
   }

    public void showTableData() 
    {
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setModel(model);
//        com.google.api.services.calendar.Calendar service = null;
//        GoogleAPI calendar = new GoogleAPI();
//        try{
//            service = getCalendarService();
//        }catch (IOException e)
//        {
//            System.out.println(e);
//        }

        String barcode = "";
        String name = "";
        String batch = "";
        String dateIn = "";
        String dateOut = "";
        String sql = "SELECT * FROM "
                + "tblBatches a INNER JOIN "
                + "tblBatchProducts b on a.batchID = b.batchID "
                + "INNER JOIN tblProducts c "
                + "on b.productID = c.productID";
        try {
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            DateTime startDate = new DateTime();
            CustomRenderer colouringTable = new CustomRenderer();
            while(res.next())
            {
                
                barcode = res.getString("productID");
                name = res.getString("ProdName");
                String batchid= res.getString("batchID");
                String arr2[] = batchid.split("-");
                batch = arr2[1];
                dateIn = res.getString("dateArrived");
                dateOut = res.getString("dateExpired");
                
                String array[] = dateOut.split("-");
                int year = Integer.parseInt(array[0]);
                int month = Integer.parseInt(array[1]);
                int day = Integer.parseInt(array[2]);
                DateTime endDate = new DateTime(year, month, day, 0, 0, 0, 0);
                

                Days d = Days.daysBetween(startDate, endDate);
                int days = d.getDays();
                
                if (days < -7)
                {
                    System.out.println("Week past expiry, record not added.");
                }
                else if (days<2)
                {
                    model.addRow(new Object[]{barcode, name, batch, dateIn, dateOut});
                    //colouringTable.setColors(Color.red);  
                }else if (days<5)
                {
                    model.addRow(new Object[]{barcode, name, batch, dateIn, dateOut});
                    //colouringTable.setColors(Color.ORANGE); 
                }else{
                    model.addRow(new Object[]{barcode, name, batch, dateIn, dateOut});
                    //colouringTable.setColors(Color.GREEN);
                }
            }
            
            table.getColumnModel().getColumn(4).setCellRenderer(colouringTable);
        } catch (Exception e) {

        }
    }
    
    
    
   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnFindbatch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnPrint = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        backgroundLbl = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jButton3.setText("Notifications");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RotateME");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImages(null);
        setMaximumSize(new java.awt.Dimension(970, 500));
        setMinimumSize(new java.awt.Dimension(940, 480));
        setPreferredSize(new java.awt.Dimension(950, 475));
        setResizable(false);
        getContentPane().setLayout(null);

        btnAdd.setText("Add Batch");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdd);
        btnAdd.setBounds(70, 90, 140, 40);

        btnFindbatch.setText("Find Batch");
        btnFindbatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindbatchActionPerformed(evt);
            }
        });
        getContentPane().add(btnFindbatch);
        btnFindbatch.setBounds(70, 140, 140, 40);

        table.setAutoCreateRowSorter(true);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Barcode", "Product Name", "Batch Number", "Date Recieved", "Expiry Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table.setRowMargin(0);
        table.setSelectionBackground(new java.awt.Color(153, 51, 255));
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(290, 21, 640, 410);

        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrint);
        btnPrint.setBounds(70, 190, 140, 40);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SystemImages/rotatemelogo2.png"))); // NOI18N
        getContentPane().add(lblLogo);
        lblLogo.setBounds(29, 21, 241, 42);

        btnRefresh.setBackground(new java.awt.Color(255, 255, 255));
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SystemImages/info-512.png"))); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        getContentPane().add(btnRefresh);
        btnRefresh.setBounds(20, 390, 40, 40);

        btnRemove.setText("Remove Item");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemove);
        btnRemove.setBounds(70, 240, 140, 40);

        jButton1.setText("Notifications");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(70, 290, 140, 40);

        backgroundLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SystemImages/back.png"))); // NOI18N
        getContentPane().add(backgroundLbl);
        backgroundLbl.setBounds(0, -40, 950, 500);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        AddItem add = new AddItem(this);
        add.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
       
        infoFrame i = new infoFrame();
        i.setVisible(true);
        
    }//GEN-LAST:event_btnRefreshActionPerformed

    
    public void refreshTable()
    {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        showTableData();
    }
    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        
        DeleteFrame d = new DeleteFrame(this);
        d.setVisible(true);
        
        
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnFindbatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindbatchActionPerformed
        SearchDialogue s = new SearchDialogue();
        s.setVisible(true);
    }//GEN-LAST:event_btnFindbatchActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        
        PrintSettings p = new PrintSettings();
        p.setVisible(true);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       emailSettings e = new emailSettings(this);
       e.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
      
    public static void main(String args[]) {
        
     
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RotateME().setVisible(true);
        try 
{
        URL url = new URL("http://www.google.com");
 
        URLConnection connection = url.openConnection();
        connection.connect();   
 
        System.out.println("Internet Connected");   
            
        }catch (Exception e){
     
        JOptionPane.showMessageDialog(null, "RotateME requires an internet connection to sync batches with Google Calendar.\nPlease note"
                + " that all batches added now will not be added on Google Calendar.","No Internet Connection", JOptionPane.ERROR_MESSAGE);
                                                            
} 
            }
                
            });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLbl;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnFindbatch;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
