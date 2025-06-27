package main.ui;
//private JPanel contentPanel;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSlider;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;

public class MainFrame {

	private JFrame frame;
	private boolean isExpanded = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tw Cen MT", Font.BOLD, 30));
		frame.getContentPane().setBackground(new Color(230, 245, 230));
		frame.setBounds(100, 100, 1177, 893);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Sidebar
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 43, 805);
		panel.setPreferredSize(new Dimension(110, 500));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnToggle = new JButton("☰");
		btnToggle.setForeground(new Color(255, 0, 128));
		btnToggle.setBackground(new Color(0, 0, 0)); 
		btnToggle.setBounds(0, 0, 43, 21);
		panel.add(btnToggle);
		
		JButton btnHome = new JButton("Home");
		btnHome.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnHome.setBounds(10, 31, 111, 42);
		btnHome.setForeground(new Color(230, 245, 230));
		btnHome.setBackground(new Color(0, 0, 0)); 
		btnHome.setVisible(false);
		panel.add(btnHome);

		JButton btnAddIncome = new JButton("Add Income");
		btnAddIncome.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnAddIncome.setBounds(10, 83, 111, 42);
		btnAddIncome.setForeground(new Color(230, 245, 230));
		btnAddIncome.setBackground(new Color(0, 0, 0)); 
		btnAddIncome.setVisible(false);
		panel.add(btnAddIncome);

		JButton btnAddExpense = new JButton("Add Expense");
		btnAddExpense.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnAddExpense.setBounds(10, 135, 111, 41);
		btnAddExpense.setForeground(new Color(230, 245, 230));
		btnAddExpense.setBackground(new Color(0, 0, 0)); 
		btnAddExpense.setVisible(false);
		panel.add(btnAddExpense);

		JButton btnViewReport = new JButton("View Report");
		btnViewReport.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnViewReport.setBounds(10, 186, 111, 41);
		btnViewReport.setForeground(new Color(230, 245, 230));
		btnViewReport.setBackground(new Color(0, 0, 0)); 
		btnViewReport.setVisible(false);
		panel.add(btnViewReport);
		
		JButton btnViewBalance = new JButton("View Balance");
		btnViewBalance.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnViewBalance.setBounds(10, 237, 111, 42);
		btnViewBalance.setForeground(new Color(230, 245, 230));
		btnViewBalance.setBackground(new Color(0, 0, 0)); 
		btnViewBalance.setVisible(false);
		panel.add(btnViewBalance);
		
		JButton btnSetLimits = new JButton("Set Limits");
		btnSetLimits.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnSetLimits.setBounds(10, 289, 111, 42);
		btnSetLimits.setForeground(new Color(230, 245, 230));
		btnSetLimits.setBackground(new Color(0, 0, 0)); 
		btnSetLimits.setVisible(false);
		panel.add(btnSetLimits);
		
		JButton btnEditDelete = new JButton("Edit/Delete");
		btnEditDelete.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnEditDelete.setBounds(10, 341, 111, 42);
		btnEditDelete.setForeground(new Color(230, 245, 230));
		btnEditDelete.setBackground(new Color(0, 0, 0)); 
		btnEditDelete.setVisible(false);
		panel.add(btnEditDelete);
		
		JButton btnReset = new JButton("Reset Data");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReset.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnReset.setBounds(10, 393, 111, 42);
		btnReset.setForeground(new Color(230, 245, 230));
		btnReset.setBackground(new Color(0, 0, 0)); 
		btnReset.setVisible(false);
		panel.add(btnReset);

		// Title Label
		JLabel titleLabel = new JLabel(" Personal ");
		titleLabel.setBackground(new Color(0, 0, 0));
		titleLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 30));
		titleLabel.setForeground(new Color(255, 0, 128));  
		titleLabel.setBounds(425, 21, 125, 36);
		frame.getContentPane().add(titleLabel);
		
		JLabel titleLabel2 = new JLabel("Budget Tracker");
		titleLabel2.setFont(new Font("Tw Cen MT", Font.BOLD, 30));
		titleLabel2.setBounds(550, 23, 195, 32);
		frame.getContentPane().add(titleLabel2);

		// Card style settings
		Font cardFont = new Font("SansSerif", Font.BOLD, 16);
		Border cardBorder = BorderFactory.createLineBorder(new Color(200, 200, 200), 1);
		
		// Card 1: Income
		JLabel incomeCard = new JLabel(
			    "<html><div style='text-align:center;'>" +
			    "<p style='margin:0; font-size:16px;'>💰</p>" +
			    "<p style='margin:0; font-size:15px;'>Income</p>" +
			    "<p style='margin:0; font-size:14px;'>₹&nbsp;20,000</p>" +
			    "</div></html>"
			);

        incomeCard.setHorizontalAlignment(SwingConstants.CENTER);
		incomeCard.setOpaque(true);
		incomeCard.setBackground(new Color(230, 245, 230)); 
		incomeCard.setFont(cardFont);
		incomeCard.setBorder(cardBorder);
		incomeCard.setBounds(180, 120, 250, 100);
		frame.getContentPane().add(incomeCard);

		// Card 2: Expenses
		JLabel expenseCard = new JLabel("<html><div style='text-align: center;'>🧾<br>Expenses<br>₹8,500</div></html>");
		expenseCard.setHorizontalAlignment(SwingConstants.CENTER);
		expenseCard.setOpaque(true);
		expenseCard.setBackground(new Color(230, 245, 230)); 
		expenseCard.setFont(cardFont);
		expenseCard.setBorder(cardBorder);
		expenseCard.setBounds(479, 120, 250, 100);
		frame.getContentPane().add(expenseCard);

		// Card 3: Balance
		JLabel balanceCard = new JLabel("<html><div style='text-align: center;'>💼<br>Balance<br> ₹11,500 </div></html>");
		balanceCard.setHorizontalAlignment(SwingConstants.CENTER);
		balanceCard.setOpaque(true);
		balanceCard.setBackground(new Color(230, 245, 230)); 
		balanceCard.setFont(cardFont);
		balanceCard.setBorder(cardBorder);
		balanceCard.setBounds(795, 120, 250, 100);
		frame.getContentPane().add(balanceCard);
		

		btnToggle.addActionListener(e -> {
			isExpanded = !isExpanded;
			btnAddIncome.setVisible(isExpanded);
			btnAddExpense.setVisible(isExpanded);
			btnViewReport.setVisible(isExpanded);
			btnViewBalance.setVisible(isExpanded);
			btnSetLimits.setVisible(isExpanded);
			btnEditDelete.setVisible(isExpanded);
			btnReset.setVisible(isExpanded);
			
			 if (isExpanded) {
			        panel.setBounds(0, 0, 131, 805);
			    } else {
			        panel.setBounds(0, 0, 43, 805);
			    }
		});
		


		// Card 4: Top Categories
		JLabel categoriesCard = new JLabel("<html><div style='text-align: center;'>🔝 Top Categories:<br> <p style='margin:10; '>- Food: ₹3,000<br>- Travel: ₹2,000</p></div></html>");
		categoriesCard.setHorizontalAlignment(SwingConstants.CENTER);
		categoriesCard.setVerticalAlignment(SwingConstants.TOP);
		categoriesCard.setOpaque(true);
		categoriesCard.setBackground(new Color(230, 245, 230));
		categoriesCard.setFont(cardFont);
		categoriesCard.setBorder(cardBorder);
		categoriesCard.setBounds(180, 270, 400, 130);  
		frame.getContentPane().add(categoriesCard);

		// Card 5: Warnings
		JLabel warningsCard = new JLabel("<html><div style='text-align: center;'>⚠️ Warnings:<br><p style='margin:10; '>- Food category limit 90% reached</p></div></html>");
		warningsCard.setHorizontalAlignment(SwingConstants.CENTER);
		warningsCard.setVerticalAlignment(SwingConstants.TOP);
		warningsCard.setOpaque(true);
		warningsCard.setBackground(new Color(230, 245, 230));
		warningsCard.setFont(cardFont);
		warningsCard.setBorder(cardBorder);
		warningsCard.setBounds(645, 270, 400, 130); 
		frame.getContentPane().add(warningsCard);

	}
	
	
}