import javax.swing.JOptionPane;

public class AdminLogin extends javax.swing.JFrame {

	IDManager  idMana =  new IDManager();
	OrderManager orderMana = new OrderManager();
	ShopManager shopMana = new ShopManager();
	static AdminLogin adminLogin = new AdminLogin(); 
    public AdminLogin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

    	 jPanel1 = new javax.swing.JPanel();
         jLabel1 = new javax.swing.JLabel();
         jPanel2 = new javax.swing.JPanel();
         jLabel2 = new javax.swing.JLabel();
         jLabel3 = new javax.swing.JLabel();
         txtPassword = new javax.swing.JPasswordField();
         btnLogin = new javax.swing.JButton();
         jLabel4 = new javax.swing.JLabel();
         cbLogin = new javax.swing.JComboBox<>();

         setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
         setTitle("�굦�| �q�\�t�� �n�J�D����");
         setBackground(new java.awt.Color(0, 255, 255));
         setResizable(false);

         jPanel1.setBackground(new java.awt.Color(255, 204, 204));

         jLabel1.setFont(new java.awt.Font("�s�ө���", 0, 24)); // NOI18N
         jLabel1.setText("�w�\�޲z�t�� �n�J�D����");

         javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
         jPanel1.setLayout(jPanel1Layout);
         jPanel1Layout.setHorizontalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(jPanel1Layout.createSequentialGroup()
                 .addGap(241, 241, 241)
                 .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
         );
         jPanel1Layout.setVerticalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(jPanel1Layout.createSequentialGroup()
                 .addContainerGap()
                 .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                 .addContainerGap())
         );

         jPanel2.setBackground(new java.awt.Color(255, 153, 153));

         jLabel2.setFont(new java.awt.Font("�s�ө���", 0, 18)); // NOI18N
         jLabel2.setText("�b���G");

         jLabel3.setFont(new java.awt.Font("�s�ө���", 0, 18)); // NOI18N
         jLabel3.setText("�K�X�G");

         btnLogin.setText("�n�J");
         btnLogin.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 btnLoginActionPerformed(evt);
             }
         });

         jLabel4.setFont(new java.awt.Font("�s�ө���", 1, 18)); // NOI18N
         jLabel4.setText("Admin");

         cbLogin.setFont(new java.awt.Font("�s�ө���", 0, 18)); // NOI18N
         cbLogin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "�b���޲z", "�q��޲z", "���a�޲z" }));

         javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
         jPanel2.setLayout(jPanel2Layout);
         jPanel2Layout.setHorizontalGroup(
             jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(jPanel2Layout.createSequentialGroup()
                 .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addGroup(jPanel2Layout.createSequentialGroup()
                         .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                             .addGroup(jPanel2Layout.createSequentialGroup()
                                 .addGap(166, 166, 166)
                                 .addComponent(jLabel2))
                             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                 .addContainerGap()
                                 .addComponent(jLabel3)))
                         .addGap(18, 18, 18)
                         .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                             .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                     .addGroup(jPanel2Layout.createSequentialGroup()
                         .addGap(287, 287, 287)
                         .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                     .addGroup(jPanel2Layout.createSequentialGroup()
                         .addGap(333, 333, 333)
                         .addComponent(cbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                 .addContainerGap(229, Short.MAX_VALUE))
         );
         jPanel2Layout.setVerticalGroup(
             jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(jPanel2Layout.createSequentialGroup()
                 .addGap(49, 49, 49)
                 .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jLabel2)
                     .addComponent(jLabel4))
                 .addGap(64, 64, 64)
                 .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jLabel3))
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                 .addComponent(cbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addGap(18, 18, 18)
                 .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addGap(30, 30, 30))
         );

         javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
         getContentPane().setLayout(layout);
         layout.setHorizontalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                 .addContainerGap()
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                 .addContainerGap())
         );
         layout.setVerticalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addContainerGap()
                 .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                 .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                 .addContainerGap())
         );


        pack();
    }// </editor-fold>                        
    
    public void openOrderManager(boolean isOpen){
    	orderMana.setVisible(isOpen);
    }
    
    public void openIDManager(boolean isOpen){
    	idMana.setVisible(isOpen);
    }
    
    public void openShopManager(boolean isOpen){
    	shopMana.setVisible(isOpen);
    }
    
    
    
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	char[]cpass = txtPassword.getPassword();
    	String correctPass = "";
    	for(int i=0; i<cpass.length; i++){
    		correctPass += cpass[i];
    	}
    	if(correctPass.equals("P@ssw0rd")){
    		if(cbLogin.getSelectedItem().toString().equals("�b���޲z")){
    			openIDManager(true);
    			adminLogin.setVisible(false);
    		}
    		else if (cbLogin.getSelectedItem().toString().equals("�q��޲z")){
    			openOrderManager(true);
    			adminLogin.setVisible(false);
    		}
    		else if (cbLogin.getSelectedItem().toString().equals("���a�޲z")){
    			openShopManager(true);
    			adminLogin.setVisible(false);
    		}
    	}
    	else{
			JOptionPane.showMessageDialog(rootPane, "�K�X���~");
		}
    }                                        

    public static void main(String args[]) {
    	adminLogin.setVisible(true);
    }
       
    private javax.swing.JButton btnLogin;
    private javax.swing.JComboBox<String> cbLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField txtPassword;
       
}
