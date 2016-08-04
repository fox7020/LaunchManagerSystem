import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class IDManager extends javax.swing.JFrame implements TableModelListener {

	String[] fields = {"�m�W","ID","Passwod","�l�B"};
	public LinkedList<String[]> userData;
	private myTableModel tableModel;
    public IDManager() {
        initComponents();
        userData = new LinkedList<>();
		tableModel = new myTableModel(fields);
		tbData.setModel(tableModel);
		getUserData();
    }
    
    private void getUserData(){
    	try {
    		Properties prop = new Properties();
			prop.setProperty("user", "sa");
			prop.setProperty("password", "mj6361948");
			Connection con = DriverManager.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder",prop);
			PreparedStatement pps = con.prepareStatement("SELECT name,id,password,money FROM orderuser");
			ResultSet rs = pps.executeQuery();
			userData.clear();
			while(rs.next()){
				String[] row = new String[4];
				row[0] = rs.getString("name");
				row[1] = rs.getString("id");
				row[2] = rs.getString("password");
				row[3] = rs.getString("money");
				userData.add(row);
				tableModel.fireTableDataChanged();
			}
    
    	}
    	catch (Exception ee) {
    		JOptionPane.showMessageDialog(rootPane, "SQL���o�ϥΪ̸�ƥ���");
		}
    }
    
    class myTableModel extends DefaultTableModel{
    	public myTableModel(String[] fields){
    		super(fields,0);
    	}
    	
    	 @Override
         public int getRowCount() {
            return userData.size();
         }

         @Override
         public int getColumnCount() {
           return fields.length;
         }
         
         @Override
         public void fireTableCellUpdated(int row, int column) {
         	
         	super.fireTableCellUpdated(row, column);
         	System.out.println("fireTableCell");
         }
         
         @Override
         public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
         	
         	//super.setValueAt(aValue, rowIndex, columnIndex);
         	System.out.println(rowIndex + "x" + columnIndex + "x" + aValue);
         }

         @Override
         public Object getValueAt(int rowIndex, int columnIndex) {
             return userData.get(rowIndex)[columnIndex];
         }
         
          @Override
         public boolean isCellEditable(int row, int col){
            return false;
             
         }
    }
    
    @SuppressWarnings("unchecked")
                   
    private void initComponents() {

    	 jLabel1 = new javax.swing.JLabel();
         txtQuery = new javax.swing.JTextField();
         btnQuery = new javax.swing.JButton();
         jPanel1 = new javax.swing.JPanel();
         btnDeleteID = new javax.swing.JButton();
         btnDeposit = new javax.swing.JButton();
         btnPassword = new javax.swing.JButton();
         btnList = new javax.swing.JButton();
         jScrollPane1 = new javax.swing.JScrollPane();
         tbData = new javax.swing.JTable();

         setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
         setTitle("�q�\�t��-�b���޲z");
         setResizable(false);

         jLabel1.setFont(new java.awt.Font("�s�ө���", 0, 36)); // NOI18N
         jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
         jLabel1.setText("�q�\�t��-�b���޲z");

         btnQuery.setText("�j�M");
         btnQuery.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 btnQueryActionPerformed(evt);
             }
         });

         btnDeleteID.setFont(new java.awt.Font("�s�ө���", 1, 18)); // NOI18N
         btnDeleteID.setText("�R���b��");
         btnDeleteID.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 btnDeleteIDActionPerformed(evt);
             }
         });

         btnDeposit.setFont(new java.awt.Font("�s�ө���", 1, 15)); // NOI18N
         btnDeposit.setText("�x��");
         btnDeposit.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 btnDepositActionPerformed(evt);
             }
         });

         btnPassword.setFont(new java.awt.Font("�s�ө���", 1, 18)); // NOI18N
         btnPassword.setText("�󴫱K�X");
         btnPassword.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 btnPasswordActionPerformed(evt);
             }
         });

         btnList.setFont(new java.awt.Font("�s�ө���", 1, 18)); // NOI18N
         btnList.setText("�������");
         btnList.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 btnListActionPerformed(evt);
             }
         });

         javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
         jPanel1.setLayout(jPanel1Layout);
         jPanel1Layout.setHorizontalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(jPanel1Layout.createSequentialGroup()
                 .addComponent(btnDeleteID)
                 .addGap(18, 18, 18)
                 .addComponent(btnDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addGap(18, 18, 18)
                 .addComponent(btnPassword)
                 .addGap(18, 18, 18)
                 .addComponent(btnList)
                 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
         );
         jPanel1Layout.setVerticalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                 .addGap(0, 1, Short.MAX_VALUE)
                 .addComponent(btnDeleteID, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
             .addComponent(btnList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
             .addComponent(btnPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
             .addComponent(btnDeposit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         );

         tbData.setModel(new javax.swing.table.DefaultTableModel(
             new Object [][] {
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null}
             },
             new String [] {
                 "Title 1", "Title 2", "Title 3", "Title 4"
             }
         ));
         jScrollPane1.setViewportView(tbData);

         javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
         getContentPane().setLayout(layout);
         layout.setHorizontalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addGroup(layout.createSequentialGroup()
                         .addGap(24, 24, 24)
                         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                             .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                             .addGroup(layout.createSequentialGroup()
                                 .addComponent(txtQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                 .addComponent(btnQuery))
                             .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE))))
                 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
         );
         layout.setVerticalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addContainerGap()
                 .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(txtQuery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(btnQuery))
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                 .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addGap(18, 18, 18)
                 .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                 .addContainerGap())
         );
        pack();
    }// </editor-fold>                        

    private void btnQueryActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String userQuery = "%"+txtQuery.getText()+"%";
        try{
        	Properties prop = new Properties();
			prop.setProperty("user", "sa");
			prop.setProperty("password", "mj6361948");
			Connection con = DriverManager.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder",prop);
			PreparedStatement ppsQuery = con.prepareStatement("SELECT * FROM orderuser WHERE id LIKE ? OR name LIKE ?");
			ppsQuery.setString(1, userQuery);
			ppsQuery.setString(2, userQuery);
			ResultSet rsQuery = ppsQuery.executeQuery();
			userData.clear();
			while(rsQuery.next()){
				String[] row = new String[4];
				row[0] = rsQuery.getString("name");
				row[1] = rsQuery.getString("id");
				row[2] = rsQuery.getString("password");
				row[3] = rsQuery.getString("money");
				userData.add(row);
				tableModel.fireTableDataChanged();
			} 	
        }
        catch(Exception ee){
        	JOptionPane.showMessageDialog(rootPane, "SQL�s������");
        }
        
        
    }                                        

    private void btnDepositActionPerformed(java.awt.event.ActionEvent evt) {  
    	try{
    		String data = (String)tbData.getValueAt(tbData.getSelectedRow(),1);
    		int addCount = Integer.parseInt(JOptionPane.showInputDialog("�п�J�x�Ȫ��B"));
    		
			Properties prop = new Properties();
			prop.setProperty("user", "sa");
			prop.setProperty("password", "mj6361948");
			Connection con = DriverManager.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder",prop);
			PreparedStatement pps = con.prepareStatement("SELECT money FROM orderuser WHERE id =" + data);
			ResultSet rs = pps.executeQuery();
			int dbMoney = 0;
			while(rs.next()){
				dbMoney = Integer.parseInt(rs.getString("money"));
			}
			dbMoney += addCount;
			PreparedStatement addMoney = con.prepareStatement("UPDATE orderuser SET money = ? WHERE id =?" );
			addMoney.setString(1, Integer.toString(dbMoney));
			addMoney.setString(2, data);
			addMoney.executeUpdate();
			getUserData();
    		
    	}
    	catch (Exception ee){
    		JOptionPane.showMessageDialog(jPanel1, "��J�榡���~��SQL�s�u����" + ee.toString());
    	}
    }                                          

    private void btnPasswordActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String password = JOptionPane.showInputDialog("�п�J�s�K�X");
        String data = (String)tbData.getValueAt(tbData.getSelectedRow(),1);
        if(password != null && data != null){
        	try{
        		Properties prop = new Properties();
    			prop.setProperty("user", "sa");
    			prop.setProperty("password", "mj6361948");
    			Connection con = DriverManager.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder",prop);
     			PreparedStatement pps = con.prepareStatement("UPDATE orderuser SET password =? WHERE id =" + data);
     			pps.setString(1, password);
     			pps.executeUpdate();
     			getUserData();
        }
        	catch(Exception ee){
        		JOptionPane.showMessageDialog(rootPane, "��sSQL����");
        	}
        
    }                                           
    }
    
    private void btnDeleteIDActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String data = (String)tbData.getValueAt(tbData.getSelectedRow(),1);
        if(data != null){
        	 try{
        		 Properties prop = new Properties();
     			prop.setProperty("user", "sa");
     			prop.setProperty("password", "mj6361948");
     			Connection con = DriverManager.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder",prop);
     			PreparedStatement pps = con.prepareStatement("DELETE  FROM orderuser WHERE id =" + data);
     			pps.executeUpdate();
     			getUserData();
             }
             catch(Exception ee){
            	 JOptionPane.showMessageDialog(rootPane, "��sSQL����");
             }
        }
        else{
        	JOptionPane.showConfirmDialog(jPanel1, "�S����ƥi�H�R��");
        }
       
    } 
    
    private void btnListActionPerformed(java.awt.event.ActionEvent evt) {
    	txtQuery.setText("");
    	getUserData();
    }                                       

    public static void main(String args[]) {
        
    }
       
    private javax.swing.JButton btnDeleteID;
    private javax.swing.JButton btnDeposit;
    private javax.swing.JButton btnList;
    private javax.swing.JButton btnPassword;
    private javax.swing.JButton btnQuery;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbData;
    private javax.swing.JTextField txtQuery;
   
    @Override
	public void tableChanged(TableModelEvent e) {}
}
