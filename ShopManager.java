import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Properties;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class ShopManager extends javax.swing.JFrame implements TableModelListener{
	LinkedList<String> shopFromDb;
	LinkedList<String[]>itemData;
	String[] fields = {"店家","產品","價格"};
	private myTableModel tableModel;
    public ShopManager() {
        initComponents();
        shopFromDb = new LinkedList<>();
        itemData = new LinkedList<>();
        tableModel = new myTableModel(fields);
		tbData.setModel(tableModel);
        cbShop.setModel(new DefaultComboBoxModel(getShopName()));
        cbShop.setSelectedIndex(0);
        showTableItem();
        setShopDetail();
    }
    
    private Object[] getShopName(){
    	
		Object[] shopArray = null;
		shopFromDb.clear();
    	try{
    		Properties prop = new Properties();
    		prop.setProperty("user", "sa");
    		prop.setProperty("password", "mj6361948");
    		Connection con = DriverManager.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder",prop);
			PreparedStatement ppsShopName = con.prepareStatement("SELECT shopName FROM shop");
			ResultSet rsShopName = ppsShopName.executeQuery();
			while(rsShopName.next()){
				String data = rsShopName.getString("shopName");
				shopFromDb.add(data);
			}
			shopArray = new Object[shopFromDb.size()];
			shopArray = shopFromDb.toArray();
    	}
    	catch(Exception ee){
    		JOptionPane.showMessageDialog(rootPane, "SQL連線失敗");
    	}
    	return shopArray;
    }
   
    private void showTableItem(){
    	itemData.clear();
    	try{
    		Properties prop = new Properties();
    		prop.setProperty("user", "sa");
    		prop.setProperty("password", "mj6361948");
    		Connection con = DriverManager.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder",prop);
    		PreparedStatement ppsItem = con.prepareStatement("SELECT shopName,price,itemName FROM itemlist WHERE shopName=?");
    		ppsItem.setString(1, cbShop.getSelectedItem().toString());
    		ResultSet rsItem = ppsItem.executeQuery();
    		while(rsItem.next()){
    			String[] row = new String[3];
    			row[0] = rsItem.getString("shopName");
    			row[1] = rsItem.getString("itemName");
    			row[2] = rsItem.getString("price");
    			itemData.add(row);
    		}
    		tableModel.fireTableDataChanged();
    	}
    	catch(Exception ee){
    		JOptionPane.showMessageDialog(rootPane, "SQL取得資料失敗");
    	}
    }
    
    private void setShopDetail(){
    	String shopName = (String) cbShop.getSelectedItem();
    	String phone ="";
    	String price ="";
    	String note = "";
    	try{
    		Properties prop = new Properties();
    		prop.setProperty("user", "sa");
    		prop.setProperty("password", "mj6361948");
    		Connection con = DriverManager.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder",prop);
    		PreparedStatement getShopDetail = con.prepareStatement("SELECT phone,outMoney,note FROM shop WHERE shopName=?");
    		getShopDetail.setString(1, shopName);
    		ResultSet rsShopDetail = getShopDetail.executeQuery();
    		while(rsShopDetail.next()){
    			phone = rsShopDetail.getString("phone");
    			price = rsShopDetail.getString("outMoney");
    			note = rsShopDetail.getString("note");
    		}
    		txtShopName.setText(shopName);
    		txtPhone.setText(phone);
    		txtPrice.setText(price);
    		txtNote.setText(note);
    		
    	}
    	catch(Exception ee){
    		JOptionPane.showMessageDialog(rootPane, "SQL取得資料失敗");
    	}
    }
    
    @SuppressWarnings("unchecked")         
    private void initComponents() {

    	 jScrollPane3 = new javax.swing.JScrollPane();
         jTable1 = new javax.swing.JTable();
         jPanel1 = new javax.swing.JPanel();
         jLabel1 = new javax.swing.JLabel();
         jLabel2 = new javax.swing.JLabel();
         txtPrice = new javax.swing.JTextField();
         txtPhone = new javax.swing.JTextField();
         jLabel3 = new javax.swing.JLabel();
         jLabel4 = new javax.swing.JLabel();
         txtNote = new javax.swing.JTextField();
         txtShopName = new javax.swing.JTextField();
         btnAddNewShop = new javax.swing.JButton();
         btnDelShop = new javax.swing.JButton();
         btnEditShop = new javax.swing.JButton();
         jPanel2 = new javax.swing.JPanel();
         jLabel5 = new javax.swing.JLabel();
         jLabel6 = new javax.swing.JLabel();
         txtItem = new javax.swing.JTextField();
         jLabel7 = new javax.swing.JLabel();
         spPrice = new javax.swing.JSpinner();
         btnAddNewItem = new javax.swing.JButton();
         btnDelItem = new javax.swing.JButton();
         btnEditItem = new javax.swing.JButton();
         cbShop = new javax.swing.JComboBox<>();
         jScrollPane2 = new javax.swing.JScrollPane();
         tbData = new javax.swing.JTable();

         jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
         jScrollPane3.setViewportView(jTable1);

         setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
         setTitle("訂餐系統-商家管理");
         setPreferredSize(new java.awt.Dimension(800, 543));
         setResizable(false);
         setSize(new java.awt.Dimension(657, 5));
         
         jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("商家資訊"));

         jLabel1.setFont(new java.awt.Font("新細明體", 1, 18)); // NOI18N
         jLabel1.setText("店家：");

         jLabel2.setFont(new java.awt.Font("新細明體", 1, 18)); // NOI18N
         jLabel2.setText("電話：");

         txtPrice.setFont(new java.awt.Font("新細明體", 0, 18)); // NOI18N

         txtPhone.setFont(new java.awt.Font("新細明體", 0, 18)); // NOI18N

         jLabel3.setFont(new java.awt.Font("新細明體", 1, 18)); // NOI18N
         jLabel3.setText("金額：");

         jLabel4.setFont(new java.awt.Font("新細明體", 1, 18)); // NOI18N
         jLabel4.setText("備註：");

         txtNote.setFont(new java.awt.Font("新細明體", 0, 18)); // NOI18N

         txtShopName.setFont(new java.awt.Font("新細明體", 0, 18)); // NOI18N

         btnAddNewShop.setFont(new java.awt.Font("新細明體", 1, 18)); // NOI18N
         btnAddNewShop.setText("新增");
         btnAddNewShop.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 btnAddNewShopActionPerformed(evt);
             }
         });

         btnDelShop.setFont(new java.awt.Font("新細明體", 1, 18)); // NOI18N
         btnDelShop.setText("刪除");
         btnDelShop.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 btnDelShopActionPerformed(evt);
             }
         });

         btnEditShop.setFont(new java.awt.Font("新細明體", 1, 18)); // NOI18N
         btnEditShop.setText("修改");
         btnEditShop.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 btnEditShopActionPerformed(evt);
             }
         });

         javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
         jPanel1.setLayout(jPanel1Layout);
         jPanel1Layout.setHorizontalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(jPanel1Layout.createSequentialGroup()
                 .addContainerGap()
                 .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addGroup(jPanel1Layout.createSequentialGroup()
                         .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                             .addComponent(jLabel1)
                             .addComponent(jLabel2))
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                         .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                             .addComponent(txtShopName, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                             .addComponent(txtPhone))
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                         .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                             .addGroup(jPanel1Layout.createSequentialGroup()
                                 .addComponent(jLabel3)
                                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                 .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                             .addGroup(jPanel1Layout.createSequentialGroup()
                                 .addComponent(jLabel4)
                                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                 .addComponent(txtNote))))
                     .addGroup(jPanel1Layout.createSequentialGroup()
                         .addGap(0, 0, Short.MAX_VALUE)
                         .addComponent(btnAddNewShop)
                         .addGap(18, 18, 18)
                         .addComponent(btnDelShop)
                         .addGap(18, 18, 18)
                         .addComponent(btnEditShop)))
                 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
         );
         jPanel1Layout.setVerticalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(jPanel1Layout.createSequentialGroup()
                 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                 .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(jLabel1)
                     .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel3)
                         .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(txtShopName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                 .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jLabel2)
                     .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jLabel4)
                     .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                 .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(btnDelShop)
                     .addComponent(btnEditShop)
                     .addComponent(btnAddNewShop)))
         );

         jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("產品資訊"));

         jLabel5.setFont(new java.awt.Font("新細明體", 1, 18)); // NOI18N
         jLabel5.setText("店家：");

         jLabel6.setFont(new java.awt.Font("新細明體", 1, 18)); // NOI18N
         jLabel6.setText("產品：");

         txtItem.setFont(new java.awt.Font("新細明體", 0, 18)); // NOI18N

         jLabel7.setFont(new java.awt.Font("新細明體", 1, 18)); // NOI18N
         jLabel7.setText("價格：");
         jLabel7.setToolTipText("");

         btnAddNewItem.setFont(new java.awt.Font("新細明體", 1, 18)); // NOI18N
         btnAddNewItem.setText("新增");
         btnAddNewItem.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 btnAddNewItemActionPerformed(evt);
             }
         });

         btnDelItem.setFont(new java.awt.Font("新細明體", 1, 18)); // NOI18N
         btnDelItem.setText("刪除");
         btnDelItem.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 btnDelItemActionPerformed(evt);
             }
         });

         btnEditItem.setFont(new java.awt.Font("新細明體", 1, 18)); // NOI18N
         btnEditItem.setText("修改");
         btnEditItem.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 btnEditItemActionPerformed(evt);
             }
         });

         cbShop.setFont(new java.awt.Font("新細明體", 0, 18)); // NOI18N
         cbShop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
         cbShop.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 cbShopActionPerformed(evt);
             }
         });

         javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
         jPanel2.setLayout(jPanel2Layout);
         jPanel2Layout.setHorizontalGroup(
             jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(jPanel2Layout.createSequentialGroup()
                 .addContainerGap()
                 .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addGroup(jPanel2Layout.createSequentialGroup()
                         .addComponent(jLabel5)
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                         .addComponent(cbShop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jLabel7)
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                         .addComponent(spPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                     .addGroup(jPanel2Layout.createSequentialGroup()
                         .addComponent(jLabel6)
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                         .addComponent(txtItem)))
                 .addGap(62, 62, 62)
                 .addComponent(btnAddNewItem)
                 .addGap(24, 24, 24)
                 .addComponent(btnDelItem)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                 .addComponent(btnEditItem)
                 .addGap(18, 18, 18))
         );
         jPanel2Layout.setVerticalGroup(
             jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(jPanel2Layout.createSequentialGroup()
                 .addContainerGap()
                 .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel5)
                         .addComponent(cbShop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                     .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel7)
                         .addComponent(spPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                 .addGap(18, 18, 18)
                 .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jLabel6)
                     .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                 .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(btnDelItem)
                     .addComponent(btnEditItem)
                     .addComponent(btnAddNewItem))
                 .addContainerGap())
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
         tbData.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                 tbDataMouseClicked(evt);
             }
         });
         jScrollPane2.setViewportView(tbData);

         javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
         getContentPane().setLayout(layout);
         layout.setHorizontalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addContainerGap()
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addComponent(jScrollPane2))
                 .addContainerGap())
         );
         layout.setVerticalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addContainerGap()
                 .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addGap(18, 18, 18)
                 .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addGap(18, 18, 18)
                 .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                 .addContainerGap())
         );

         jPanel1.getAccessibleContext().setAccessibleName("店家輸入區");

        pack();
    }// </editor-fold>                        
    
    class myTableModel extends DefaultTableModel{
    	public myTableModel(String[] fields){
    		super(fields,0);
    	}
    	
    	 @Override
         public int getRowCount() {
            return itemData.size();
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
         	//System.out.println(rowIndex + "x" + columnIndex + "x" + aValue);
         }

         @Override
         public Object getValueAt(int rowIndex, int columnIndex) {
             return itemData.get(rowIndex)[columnIndex];
         }
         
          @Override
         public boolean isCellEditable(int row, int col){
            return false;
             
         }
    }
    
    private void btnAddNewShopActionPerformed(java.awt.event.ActionEvent evt) {                                              
        String shopName =txtShopName.getText();
        String outMoney = txtPrice.getText();
        String phone = txtPhone.getText();
        String note = txtNote.getText();
        try{
        	Properties prop = new Properties();
    		prop.setProperty("user", "sa");
    		prop.setProperty("password", "mj6361948");
    		Connection con = DriverManager.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder",prop);
    		PreparedStatement ppsAddShop = con.prepareStatement("INSERT INTO shop(shopNAME,phone,outMoney,note) VALUES(?,?,?,?)");
    		ppsAddShop.setString(1, shopName);
    		ppsAddShop.setString(2, phone);
    		ppsAddShop.setString(3, outMoney);
    		ppsAddShop.setString(4, note);
    		ppsAddShop.execute();
    		cbShop.setModel(new DefaultComboBoxModel(getShopName()));
            cbShop.setSelectedIndex(0);
        }
        catch(Exception ee){
        	JOptionPane.showMessageDialog(rootPane, "SQL更新失敗");
        }
    }                                             

    private void btnDelShopActionPerformed(java.awt.event.ActionEvent evt) {                                           
        String shopName = txtShopName.getText();
        try{
        	Properties prop = new Properties();
    		prop.setProperty("user", "sa");
    		prop.setProperty("password", "mj6361948");
    		Connection con = DriverManager.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder",prop);
    		PreparedStatement delShop = con.prepareStatement("DELETE FROM shop WHERE shopName=?");
    		delShop.setString(1, shopName);
    		delShop.executeUpdate();
    		delShop = con.prepareStatement("DELETE FROM itemlist WHERE ShopName=?");
    		delShop.setString(1, shopName);
    		delShop.executeUpdate();
    		cbShop.setModel(new DefaultComboBoxModel(getShopName()));
            cbShop.setSelectedIndex(0);
            showTableItem();
        }
        catch(Exception ee){
        	JOptionPane.showMessageDialog(rootPane, "SQL更新失敗");
        }
    }                                          

    private void btnEditShopActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String shopName = txtShopName.getText();
        String outMoney = txtPrice.getText();
        String phone = txtPhone.getText();
        String note = txtNote.getText();
        
        try{
        	Properties prop = new Properties();
    		prop.setProperty("user", "sa");
    		prop.setProperty("password", "mj6361948");
    		Connection con = DriverManager.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder",prop);
    		PreparedStatement ppsEditShop = con.prepareStatement("UPDATE shop SET shopName=?,outMoney=?,phone=?,note=? WHERE shopName=?");
    		ppsEditShop.setString(1, shopName);
    		ppsEditShop.setString(2, outMoney);
    		ppsEditShop.setString(3, phone);
    		ppsEditShop.setString(4, note);
    		ppsEditShop.setString(5, (String)cbShop.getSelectedItem());
    		ppsEditShop.executeUpdate();
    		ppsEditShop = con.prepareStatement("UPDATE itemlist SET shopName =? WHERE shopName = ?");
    		ppsEditShop.setString(1, shopName);
    		ppsEditShop.setString(2, (String)cbShop.getSelectedItem());
    		ppsEditShop.executeUpdate();
    		cbShop.setModel(new DefaultComboBoxModel(getShopName()));
            cbShop.setSelectedIndex(0);
            showTableItem();
        }
        catch(Exception ee){
        	JOptionPane.showMessageDialog(rootPane, "SQL更新失敗");
        }
    }                                           

    private void btnAddNewItemActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	
    	try{
        	Properties prop = new Properties();
    		prop.setProperty("user", "sa");
    		prop.setProperty("password", "mj6361948");
    		Connection con = DriverManager.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder",prop);
    		PreparedStatement ppsAddItem = con.prepareStatement("INSERT itemlist(itemName,price,shopName) VALUES(?,?,?)");
    		int price = (int) spPrice.getValue();
    		String item = txtItem.getText();
    		String shop = (String) cbShop.getSelectedItem();
    		String strPrice = Integer.toString(price);
    		ppsAddItem.setString(1,item);
    		ppsAddItem.setString(2,strPrice);
    		ppsAddItem.setString(3,shop);
    		ppsAddItem.execute();
    		showTableItem();
        }
        catch(Exception ee){
        	JOptionPane.showMessageDialog(rootPane, "SQL更新失敗");
        }
    }                                             

    private void btnDelItemActionPerformed(java.awt.event.ActionEvent evt) {                                           
        try{
        	Properties prop = new Properties();
    		prop.setProperty("user", "sa");
    		prop.setProperty("password", "mj6361948");
    		Connection con = DriverManager.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder",prop);
    		PreparedStatement ppsDelItem = con.prepareStatement("DELETE FROM itemlist WHERE shopName=? AND itemName=?");
    		ppsDelItem.setString(1, (String)cbShop.getSelectedItem());
    		ppsDelItem.setString(2,txtItem.getText());
    		ppsDelItem.executeUpdate();
    		showTableItem();
    		txtItem.setText("");
    		spPrice.setValue(0);
        }
        catch(Exception ee){
        	JOptionPane.showMessageDialog(rootPane, "SQL更新失敗");
        }
    }                                          

    private void btnEditItemActionPerformed(java.awt.event.ActionEvent evt) {                                            
    	String shop = (String) tbData.getValueAt(tbData.getSelectedRow(), 0);
    	String item = (String) tbData.getValueAt(tbData.getSelectedRow(), 1);
    	String price = (String) tbData.getValueAt(tbData.getSelectedRow(),2);
    	String newItem = txtItem.getText();
    	String newPrice = Integer.toString((int) spPrice.getValue());
    	try{
    		Properties prop = new Properties();
    		prop.setProperty("user", "sa");
    		prop.setProperty("password", "mj6361948");
    		Connection con = DriverManager.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder",prop);
    		PreparedStatement ppsEditItem = con.prepareStatement("UPDATE itemlist SET itemName=?,price=? WHERE shopName=? AND itemName=? AND price=?");
    		ppsEditItem.setString(1, newItem);
    		ppsEditItem.setString(2, newPrice);
    		ppsEditItem.setString(3, shop);
    		ppsEditItem.setString(4, item);
    		ppsEditItem.setString(5, price);
    		ppsEditItem.executeUpdate();
    		showTableItem();
    		txtItem.setText("");
    		spPrice.setValue(0);
    		
    	}
    	catch(Exception ee){
    		JOptionPane.showMessageDialog(rootPane, "SQL更新失敗");
    	}
    	
    }
    
    private void cbShopActionPerformed(java.awt.event.ActionEvent evt) {                                       
    	showTableItem();
    	setShopDetail();
    }        
    
    private void tbDataMouseClicked(java.awt.event.MouseEvent evt) {                                    
    	String item = (String) tbData.getValueAt(tbData.getSelectedRow(), 1);
        String strPrice = (String) tbData.getValueAt(tbData.getSelectedRow(),2);
        int price = Integer.parseInt(strPrice);
        spPrice.setValue(price);
        txtItem.setText(item);
    }                                   

    public static void main(String args[]) {}

    private javax.swing.JButton btnAddNewItem;
    private javax.swing.JButton btnAddNewShop;
    private javax.swing.JButton btnDelItem;
    private javax.swing.JButton btnDelShop;
    private javax.swing.JButton btnEditItem;
    private javax.swing.JButton btnEditShop;
    private javax.swing.JComboBox<String> cbShop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JSpinner spPrice;
    private javax.swing.JTable tbData;
    private javax.swing.JTextField txtItem;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtShopName;
   
    public void tableChanged(TableModelEvent e) {}
}
