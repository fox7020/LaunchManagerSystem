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

public class OrderManager extends javax.swing.JFrame implements TableModelListener {

	String[] fields = { "�s��", "�m�W", "�~��", "�ƶq", "����" };
	String[] fields2 = { "�~��", "�ƶq", "����" };
	LinkedList<String[]> orderData, itemData;
	private myTableModel tableModel;
	private myTableModelV2 tableModelV2;

	public OrderManager() {
		initComponents();
		orderData = new LinkedList<>();
		itemData = new LinkedList<>();
		tableModel = new myTableModel(fields);
		tbData.setModel(tableModel);
		tableModelV2 = new myTableModelV2(fields2);
		tbCount.setModel(tableModelV2);
		getShopData();
		getOrderData();
	}

	private void getOrderData() {
		try {
			Properties prop = new Properties();
			prop.setProperty("user", "sa");
			prop.setProperty("password", "mj6361948");
			Connection con = DriverManager
					.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder", prop);

			PreparedStatement ppsUserOrder = con.prepareStatement("SELECT * FROM orderlist");
			ResultSet rsUserOrder = ppsUserOrder.executeQuery();
			orderData.clear();
			while (rsUserOrder.next()) {
				String[] row = new String[5];
				row[0] = rsUserOrder.getString("numbering");
				row[1] = rsUserOrder.getString("name");
				row[2] = rsUserOrder.getString("item");
				row[3] = rsUserOrder.getString("count");
				row[4] = rsUserOrder.getString("price");
				orderData.add(row);
			}
			tableModel.fireTableDataChanged();

			PreparedStatement ppsOrderCount = con
					.prepareStatement("SELECT item ,SUM(count) AS �ƶq ,SUM(price) AS �`��  FROM orderlist GROUP BY item");
			ResultSet rsOrderCount = ppsOrderCount.executeQuery();
			itemData.clear();
			while (rsOrderCount.next()) {
				String[] row1 = new String[3];
				row1[0] = rsOrderCount.getString("item");
				row1[1] = rsOrderCount.getString("�ƶq");
				row1[2] = rsOrderCount.getString("�`��");
				itemData.add(row1);
			}
			tableModelV2.fireTableDataChanged();
			checkOutMoney();

		} catch (Exception ee) {
			JOptionPane.showMessageDialog(rootPane, "SQL���o�q�楢��");
		}
	}

	private void getShopData() {
		LinkedList<String> shopList = new LinkedList<>();
		try {
			Properties prop = new Properties();
			prop.setProperty("user", "sa");
			prop.setProperty("password", "mj6361948");
			Connection con = DriverManager
					.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder", prop);
			PreparedStatement ppsShop = con.prepareStatement("SELECT shopName,note FROM shop");
			ResultSet rsShop = ppsShop.executeQuery();
			shopList.clear();
			while (rsShop.next()) {
				String shopData = rsShop.getString("shopName");
				shopList.add(shopData);
			}
			Object[] obShopArray = shopList.toArray();
			String[] shopArray = new String[obShopArray.length];
			for (int i = 0; i < obShopArray.length; i++) {
				shopArray[i] = (String) obShopArray[i];
			}
			cbShop.setModel(new DefaultComboBoxModel(shopArray));
		} catch (Exception ee) {
			JOptionPane.showMessageDialog(rootPane, "SQL���o���a����");
		}
	}

	private void checkOutMoney() {
		try {
			Properties prop = new Properties();
			prop.setProperty("user", "sa");
			prop.setProperty("password", "mj6361948");
			Connection con = DriverManager
					.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder", prop);

			PreparedStatement ppsOutMoney = con.prepareStatement("SELECT outMoney FROM shop WHERE isEat=1");

			int outMoney = 0;
			ResultSet rsMoney = ppsOutMoney.executeQuery();
			while (rsMoney.next()) {
				outMoney = Integer.parseInt(rsMoney.getString("outMoney"));
			}
			int cousumerMoney = 0;

			for (int i = 0; i < tbData.getRowCount(); i++) {
				cousumerMoney += Integer.parseInt((String) tbData.getValueAt(i, 4));
			}
			if (cousumerMoney < outMoney) {
				lblMessege.setText("���F�~�e���B");
			} else {
				lblMessege.setText("�w�F�~�e���B");
			}
		} catch (Exception ee) {
			JOptionPane.showMessageDialog(rootPane, "SQL���o���a����");
		}
	}

	class myTableModel extends DefaultTableModel {
		public myTableModel(String[] fields) {
			super(fields, 0);
		}

		@Override
		public int getRowCount() {
			return orderData.size();
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
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return orderData.get(rowIndex)[columnIndex];
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}
	}

	class myTableModelV2 extends DefaultTableModel {
		public myTableModelV2(String[] fields2) {
			super(fields2, 0);
		}

		@Override
		public int getRowCount() {
			return itemData.size();
		}

		@Override
		public int getColumnCount() {
			return fields2.length;
		}

		@Override
		public void fireTableCellUpdated(int row, int column) {

			super.fireTableCellUpdated(row, column);
			System.out.println("fireTableCell");
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return itemData.get(rowIndex)[columnIndex];
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbShop = new javax.swing.JComboBox<>();
        btnShopRandom = new javax.swing.JButton();
        btnShop = new javax.swing.JButton();
        txtQuery = new javax.swing.JTextField();
        btnList = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnQuery = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblMessege = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbData = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCount = new javax.swing.JTable();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("�q�\�t��-�q��޲z");
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("�s�ө���", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("�q�\�޲z�t��-�q��޲z");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("�s�ө���", 0, 18)); // NOI18N
        jLabel2.setText("���驱�a�G");

        cbShop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnShopRandom.setText("�H��");
        btnShopRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShopRandomActionPerformed(evt);
            }
        });

        btnShop.setText("�T�w");
        btnShop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShopActionPerformed(evt);
            }
        });

        btnList.setText("�Ҧ��q����");
        btnList.setToolTipText("");
        btnList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListActionPerformed(evt);
            }
        });

        btnDelete.setText("�R��");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnQuery.setFont(new java.awt.Font("�s�ө���", 0, 24)); // NOI18N
        btnQuery.setText("�j�M");
        btnQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQueryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(btnShopRandom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbShop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShop))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuery)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbShop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnShopRandom)
                            .addComponent(btnShop)
                            .addComponent(btnList)
                            .addComponent(btnDelete))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnQuery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        lblMessege.setFont(new java.awt.Font("�s�ө���", 0, 18)); // NOI18N
        lblMessege.setText("Messger");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMessege, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMessege, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
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

        jTabbedPane1.addTab("tab1", jScrollPane1);

        tbCount.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbCount);

        jTabbedPane1.addTab("tab2", jScrollPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("�ԲӸ��");

		pack();
	}

	private void btnShopRandomActionPerformed(java.awt.event.ActionEvent evt) {
		int randomShop = (int) (Math.random() * cbShop.getItemCount());
		cbShop.setSelectedIndex(randomShop);
	}

	private void btnShopActionPerformed(java.awt.event.ActionEvent evt) {
		String shop = (String) cbShop.getSelectedItem();
		try {
			Properties prop = new Properties();
			prop.setProperty("user", "sa");
			prop.setProperty("password", "mj6361948");
			Connection con = DriverManager
					.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder", prop);
			PreparedStatement ppsNote = con.prepareStatement("SELECT outMoney,note FROM shop Where shopName=?");
			ppsNote.setString(1, shop);
			ResultSet rsNote = ppsNote.executeQuery();
			while (rsNote.next()) {
				String note = "�~�e���B:" + rsNote.getString("outMoney") + ";" + rsNote.getString("note");
				JOptionPane.showMessageDialog(rootPane, note);
			}
			ppsNote = con.prepareStatement("TRUNCATE TABLE orderlist");
			ppsNote.execute();
			ppsNote = con.prepareStatement("UPDATE shop SET isEat = 0 ");
			ppsNote.executeUpdate();
			ppsNote = con.prepareStatement("UPDATE shop SET isEat = 1 WHERE shopName = ? ");
			ppsNote.setString(1, shop);
			ppsNote.executeUpdate();
			getOrderData();

		} catch (Exception ee) {
			JOptionPane.showMessageDialog(rootPane, "SQL��s����");
		}
	}

	private void btnListActionPerformed(java.awt.event.ActionEvent evt) {
		getOrderData();
		txtQuery.setText("");
	}

	private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
		String data = (String) tbData.getValueAt(tbData.getSelectedRow(), 0);
		String orderPrice = (String) tbData.getValueAt(tbData.getSelectedRow(), 4);
		orderPrice = orderPrice.trim();
		String userMoneyDB = "";
		try {
			Properties prop = new Properties();
			prop.setProperty("user", "sa");
			prop.setProperty("password", "mj6361948");
			Connection con = DriverManager
					.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder", prop);

			PreparedStatement ppsDel = con.prepareStatement("DELETE FROM orderlist Where numbering =?");
			ppsDel.setString(1, data);
			ppsDel.executeUpdate();

			PreparedStatement ppsMoney = con.prepareStatement("SELECT money FROM orderuser WHERE name =?");
			String name = (String) tbData.getValueAt(tbData.getSelectedRow(), 1);
			ppsMoney.setString(1, name);
			ResultSet rsMoney = ppsMoney.executeQuery();
			while (rsMoney.next()) {
				userMoneyDB = rsMoney.getString("money");
			}
			userMoneyDB = userMoneyDB.trim();
			int money = Integer.parseInt(orderPrice) + Integer.parseInt(userMoneyDB);
			PreparedStatement ppsUpMoney = con.prepareStatement("UPDATE orderuser SET money = ? WHERE name = ?");
			ppsUpMoney.setString(1, Integer.toString(money));
			ppsUpMoney.setString(2, name);
			ppsUpMoney.executeUpdate();
			getOrderData();
		} catch (Exception ee) {
			JOptionPane.showMessageDialog(rootPane, "SQL��s����");
		}
	}

	private void btnQueryActionPerformed(java.awt.event.ActionEvent evt) {
		
		try {
			Properties prop = new Properties();
			prop.setProperty("user", "sa");
			prop.setProperty("password", "mj6361948");
			Connection con = DriverManager
					.getConnection("jdbc:sqlserver://59.127.112.147:1433;" + "databaseName=launchorder", prop);
			PreparedStatement ppsQuery = con
					.prepareStatement("SELECT * FROM orderlist WHERE name LIKE ? OR item LIKE ?");
			String userQuery = "%" + txtQuery.getText() + "%";
			ppsQuery.setString(1, userQuery);
			ppsQuery.setString(2, userQuery);
			ResultSet rsQuery = ppsQuery.executeQuery();
			orderData.clear();
			while (rsQuery.next()) {
				String row2[] = new String[5];
				row2[0] = rsQuery.getString("numbering");
				row2[1] = rsQuery.getString("name");
				row2[2] = rsQuery.getString("item");
				row2[3] = rsQuery.getString("count");
				row2[4] = rsQuery.getString("price");
				orderData.add(row2);
			}
			tableModel.fireTableDataChanged();

		} catch (Exception ee) {
			JOptionPane.showMessageDialog(rootPane, "SQL���o��ƥ���");
		}

	}

	public static void main(String args[]) {

	}

	private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnList;
    private javax.swing.JButton btnQuery;
    private javax.swing.JButton btnShop;
    private javax.swing.JButton btnShopRandom;
    private javax.swing.JComboBox<String> cbShop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblMessege;
    private javax.swing.JTable tbCount;
    private javax.swing.JTable tbData;
    private javax.swing.JTextField txtQuery;
	@Override
	public void tableChanged(TableModelEvent e) {
	}
}
