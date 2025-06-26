package main.ui;

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
		frame.getContentPane().setBackground(new Color(64, 0, 0));
		frame.setBounds(100, 100, 1177, 624);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Sidebar
		JPanel panel = new JPanel();
		panel.setBackground(new Color(64, 0, 0));
		panel.setBounds(0, 0, 104, 577);
		panel.setPreferredSize(new Dimension(110, 500));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnToggle = new JButton("☰");
		btnToggle.setBounds(0, 0, 43, 21);
		panel.add(btnToggle);

		JButton btnAddIncome = new JButton("Add Income");
		btnAddIncome.setBounds(0, 32, 95, 21);
		btnAddIncome.setVisible(false);
		panel.add(btnAddIncome);

		JButton btnAddExpense = new JButton("Add Expense");
		btnAddExpense.setBounds(0, 67, 95, 21);
		btnAddExpense.setVisible(false);
		panel.add(btnAddExpense);

		JButton btnViewReport = new JButton("View Report");
		btnViewReport.setBounds(0, 101, 95, 21);
		btnViewReport.setVisible(false);
		panel.add(btnViewReport);

		// Title Label
		JLabel titleLabel = new JLabel("Welcome To Your Personal Budget Tracker");
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		titleLabel.setForeground(new Color(255, 255, 255));  // Dark neutral
		titleLabel.setBounds(387, 21, 400, 36);
		frame.getContentPane().add(titleLabel);

		// Card style settings
		Font cardFont = new Font("SansSerif", Font.BOLD, 16);
		Border cardBorder = BorderFactory.createLineBorder(new Color(200, 200, 200), 1);
		
		// Card 1: Income
		JLabel incomeCard = new JLabel("<html><div style='text-align: center;'>💰<br>Income<br>₹20,000</div></html>");
		incomeCard.setHorizontalAlignment(SwingConstants.CENTER);
		incomeCard.setOpaque(true);
		incomeCard.setBackground(new Color(240, 240, 240)); // light grey
		incomeCard.setFont(cardFont);
		incomeCard.setBorder(cardBorder);
		incomeCard.setBounds(124, 120, 300, 100);
		frame.getContentPane().add(incomeCard);

		// Card 2: Expenses
		JLabel expenseCard = new JLabel("<html><div style='text-align: center;'>🧾<br>Expenses<br>₹8,500</div></html>");
		expenseCard.setHorizontalAlignment(SwingConstants.CENTER);
		expenseCard.setOpaque(true);
		expenseCard.setBackground(new Color(245, 245, 245)); // slightly off-white
		expenseCard.setFont(cardFont);
		expenseCard.setBorder(cardBorder);
		expenseCard.setBounds(450, 120, 300, 100);
		frame.getContentPane().add(expenseCard);

		// Card 3: Balance
		JLabel balanceCard = new JLabel("<html><div style='text-align: center;'>💼<br>Balance<br>₹11,500</div></html>");
		balanceCard.setHorizontalAlignment(SwingConstants.CENTER);
		balanceCard.setOpaque(true);
		balanceCard.setBackground(new Color(230, 230, 230)); // soft grey-blue tone
		balanceCard.setFont(cardFont);
		balanceCard.setBorder(cardBorder);
		balanceCard.setBounds(782, 120, 300, 100);
		frame.getContentPane().add(balanceCard);

		btnToggle.addActionListener(e -> {
			isExpanded = !isExpanded;
			btnAddIncome.setVisible(isExpanded);
			btnAddExpense.setVisible(isExpanded);
			btnViewReport.setVisible(isExpanded);
		});
	}
}