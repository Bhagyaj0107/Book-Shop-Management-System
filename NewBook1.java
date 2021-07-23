import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

	public class NewBook1 extends JFrame implements ActionListener {
		
		JFrame f;
		JLabel l0,l1,l2,l3,l4;
		JTextField t1,t2,t3;
		JComboBox cmb;
		JButton b1,b2,b3,b4;
		
		JPanel panel;
	    JTable table;
	    DefaultTableModel model;
	    JScrollPane scrollpane;
		
	    NewBook1(){
			
			f=new JFrame("Book Registration");
			f.setVisible(true);
			f.setSize(500,400);
			f.setLayout(null);
			
			l0=new JLabel("Book Shop");
			l0.setFont(new Font("Times New Roman", Font.BOLD, 30));
			l0.setBounds(130, 4, 139, 41);
			f.add(l0);
			
			l1=new JLabel("Book Id");
			l1.setBounds(50, 50, 200, 30);
			f.add(l1);
			
			l2=new JLabel("Book Name ");
			l2.setBounds(50, 100, 200, 30);
			f.add(l2);
			
			l3=new JLabel("Edition");
			l3.setBounds(50, 150, 200, 30);
			f.add(l3);
			
			l4=new JLabel("Price");
			l4.setBounds(50, 200, 200, 30);
			f.add(l4);
			
			t1=new JTextField();
			t1.setBounds(150, 50, 200, 30);
			f.add(t1);
			
			t2=new JTextField();
			t2.setBounds(150, 100, 200, 30);
			f.add(t2);
			
			t3=new JTextField();
			t3.setBounds(150, 200, 200, 30);
			f.add(t3);
			
			String std[]={"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th"};        
	        cmb=new JComboBox(std); 
	        cmb.setBounds(150,150, 200, 30);
	        f.add(cmb);
	        
	        b1=new JButton ("Save");
	        b1.setBounds(50, 250, 100, 30);
	        f.add(b1);
	        b1.addActionListener(this);
	        
	        b2=new JButton ("Reset");
	        b2.setBounds(180, 250, 100, 30);
	        f.add(b2);
	        b2.addActionListener(this);
	        
	        b3=new JButton ("Delete");
	        b3.setBounds(310, 250, 100, 30);
	        f.add(b3);
	        b3.addActionListener(this);
	          	        
		} // end of NewBook1()

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==b1) {
				
				try {
				//Creating Connection Object
	            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/minipro","root","Momdad@143");
	            
	          //Prepared Statement
	            PreparedStatement Pstatement=connection.prepareStatement("insert into tblbook values(?,?,?,?)"); // inserting data into table named student..7-label so 7-?

	          //Specifying the values of it's parameter
	            Pstatement.setString(1,t1.getText());
	            Pstatement.setString(2,t2.getText());  
	            Pstatement.setString(3,cmb.getSelectedItem().toString());
	            Pstatement.setString(4,t3.getText());
	            
	            
	          //Executing MySQL Update Query

	            int i = Pstatement.executeUpdate();

	            if(i==1){

	             JOptionPane.showMessageDialog(panel, "Data Inserted Successfully");

	            }     
	                        
	                        
				} catch (Exception e1) {
	            System.out.println(e1);
	            }

			}
			
			if(e.getSource()==b2) {
				
				//Clearing Fields
	            t1.setText("");
	            cmb.setSelectedItem("Male");
	            t2.setText("");
	            t3.setText(""); 	
			}
			
			//if(e.getSource()==b3) {
				
			b3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae){

				String value1=t1.getText();
				Connection con = null;
						 String url = "jdbc:mysql://localhost:3306/";
						 String db = "minipro";
						 String driver = "com.mysql.cj.jdbc.Driver";
						 String user = "root";
						 String pass = "Momdad@143";
				try{
				Class.forName(driver);
				con = DriverManager.getConnection(url+db, user, pass);
				PreparedStatement st=con.prepareStatement("DELETE FROM tblbook WHERE Book ID = ?");
				st.setString(1,value1);
				st.executeUpdate();
				JOptionPane.showMessageDialog(panel,"Record is deleted successfully.");
				con.close();
				}
				catch(Exception exp3)
				{
				JOptionPane.showMessageDialog(panel,"Error in deleting record.");
				}
				}
				});
			
			
		}//end of actionperform
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			new NewBook1();
		}//end of main method

		
	}// end of class
