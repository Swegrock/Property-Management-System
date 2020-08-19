package com.mootz.property.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mootz.property.Controllers.BranchController;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JLabel;

public class BranchView extends JFrame {

	private static final long serialVersionUID = 6L;
	
	private JPanel contentPane;
	
	private BranchController branchController;
	private JTable table;
	
	public BranchView(BranchController branchController) {
		this.branchController = branchController;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 460);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Log out");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        branchController.LogOut();
		    }
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Sold");
		chckbxNewCheckBox.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        branchController.setShowSold(e.getStateChange() == ItemEvent.SELECTED);
		    }
		});
		chckbxNewCheckBox.setBounds(150, 9, 69, 25);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Flats");
		chckbxNewCheckBox_1.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        branchController.setShowFlats(e.getStateChange() == ItemEvent.SELECTED);
		    }
		});
		chckbxNewCheckBox_1.setBounds(85, 9, 61, 25);
		contentPane.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Houses");
		chckbxNewCheckBox_2.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        branchController.setShowHouses(e.getStateChange() == ItemEvent.SELECTED);
		    }
		});
		chckbxNewCheckBox_2.setBounds(12, 9, 69, 25);
		contentPane.add(chckbxNewCheckBox_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(12, 43, 693, 293);
		contentPane.add(scrollPane);
		
		table = new JTable(branchController.getPropertyTable()){
			private static final long serialVersionUID = 8L;
			
	        public boolean editCellAt(int row, int column, java.util.EventObject e) {
	        	return false;
	        }
	    };
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("Add Branch");
		button.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        branchController.AddNewProperty();
		    }
		});
		button.setBounds(8, 349, 120, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Edit Branch");
		button_1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        branchController.EditProperty(table.getSelectedRow());
		    }
		});
		button_1.setBounds(288, 349, 120, 25);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Delete Branch");
		button_2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        branchController.DeleteProperty(table.getSelectedRow());
		    }
		});
		button_2.setBounds(579, 349, 126, 25);
		contentPane.add(button_2);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(489, 9, 216, 22);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        textPane.setBorder(border);
		textPane.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				branchController.RefreshTable(textPane.getText());
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				branchController.RefreshTable(textPane.getText());
				
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				branchController.RefreshTable(textPane.getText());
			}
		});
		contentPane.add(textPane);
		
		JLabel lblNewLabel = new JLabel("Filter by address:");
		lblNewLabel.setBounds(378, 9, 109, 16);
		contentPane.add(lblNewLabel);
	}	
}
