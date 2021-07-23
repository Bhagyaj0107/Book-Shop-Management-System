
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;

	public class SearchBook implements ActionListener{
		JFrame frame, frame1;
		JTextField textfield;
		JLabel label;
		JButton button;
		JPanel panel;
		static JTable table;

		String driverName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/minipro";
		String userName = "root";
		String password = "Momdad@143";
		String[] columnNames = {"Book Id", "Book Name", "Edition", "Price"};

		public void createUI()
		{
			frame = new JFrame("Database Search Result");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(null);
			
			textfield = new JTextField();
			textfield.setBounds(135,30,120,30); 
			
			label = new JLabel("Enter your Book ID");
			label.setBounds(10, 30, 130, 20);
			
			button = new JButton("search");
			button.setBounds(70,100,100,30);
			button.addActionListener(this);
			
			frame.add(textfield);
			frame.add(label);
			frame.add(button);
			frame.setVisible(true);
			frame.setSize(300, 200); 

		} 
		
		public void actionPerformed(ActionEvent ae)
		{
			button = (JButton)ae.getSource();
			System.out.println("Showing Table Data.......");
			showTableData(); 
		} 

		public void showTableData()
		{
			
			frame1 = new JFrame("Database Search Result");
			frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame1.setLayout(new BorderLayout()); 
			//TableModel tm = new TableModel();
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(columnNames);
			//DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames()); 
			//table = new JTable(model);
			table = new JTable();
			table.setModel(model); 
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.setFillsViewportHeight(true);
			JScrollPane scroll = new JScrollPane(table);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
			String textvalue = textfield.getText();
			String id= "";
			String name= "";
			String edi = "";
			String prc = "";
			try
			{ 
				Class.forName(driverName); 
				Connection con = DriverManager.getConnection(url, userName, password);
				String sql = "select * from tblbook";
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				int i =0;
				while(rs.next())
				{
					id = rs.getString("Book Id");
					name = rs.getString("Book Name");
					edi = rs.getString("Edition");
					prc= rs.getString("Price"); 
					model.addRow(new Object[]{id, name, edi,prc});
					
				}
				if(i <1)
				{
					JOptionPane.showMessageDialog(null,"Showing Table Data.");
				}
				if(i ==1)
				{
					System.out.println(i+" Record Found");
				}
				else
				{
					System.out.println(i+" Records Found");
				}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
						JOptionPane.ERROR_MESSAGE);
			}
			frame1.add(scroll);
			frame1.setVisible(true);
			frame1.setSize(400,300);
		}

		public static void main(String args[])
		{
			SearchBook sr = new SearchBook();
			sr.createUI(); 
		}
	}
