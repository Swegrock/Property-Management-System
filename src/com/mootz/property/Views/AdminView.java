package com.mootz.property.Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mootz.property.Controllers.AdminController;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

public class AdminView extends JFrame {

	private static final long serialVersionUID = 5L;

	private JPanel contentPane;
	
	private AdminController adminController;
	
	//private String col[] = new 
	private JTable table;
	
	public AdminView(AdminController adminController) {
		setResizable(false);
		this.adminController = adminController;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 460);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Edit Admin");
		mntmNewMenuItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        adminController.EditAdminDetails();
		    }
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Exit");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Log out");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        adminController.LogOut();
		    }
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Branch");
		btnNewButton.setBounds(12, 367, 120, 25);
		btnNewButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        adminController.AddNewBranch();
		    }
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit Branch");
		btnNewButton_1.setBounds(296, 367, 120, 25);
		btnNewButton_1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        adminController.EditBranch(table.getSelectedRow());
		    }
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete Branch");
		btnNewButton_2.setBounds(593, 367, 126, 25);
		btnNewButton_2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        adminController.DeleteBranch(table.getSelectedRow());
		    }
		});
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(12, 13, 707, 343);
		contentPane.add(scrollPane);
		
		table = new JTable(adminController.getBranchTable()){
			private static final long serialVersionUID = 8L;
			
	        public boolean editCellAt(int row, int column, java.util.EventObject e) {
	        	return false;
	        }
	    };
		scrollPane.setViewportView(table);
	}
}


