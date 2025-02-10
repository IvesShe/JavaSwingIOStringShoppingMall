package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

import util.*;
import model.*;


public class AddOrderUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textMeal1;
	private JTextField textMeal2;
	private static String appTitle = "麥當當購物";
	private JTextField textReceivedAmount;
	private String outputText;
	
	Order o = null;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddOrderUI frame = new AddOrderUI();
					frame.setVisible(true);			
//					Clock.startAutoUpdateClock(lblTimer);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 處理確認訂單
	 */
	private void handlerOK(JTextArea output,JCheckBox vipMember){		
		String Name = textName.getText();
		String mealNo1Price = textMeal1.getText();
		String mealNo2Price = textMeal2.getText();
		output.setText("");

		if (Name.isEmpty()) {
			output.setText("名字不能為空，請重新輸入。");
			JOptionPane.showMessageDialog(null, "名字不能為空，請重新輸入。", "錯誤", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 空值自動補0
		if (mealNo1Price.isEmpty()) {
			mealNo1Price = "0";
			textMeal1.setText("0");
		}
		if (mealNo2Price.isEmpty()) {
			mealNo2Price = "0";
			textMeal2.setText("0");
		}

		if (!Tool.isNumeric(mealNo1Price) || !Tool.isNumeric(mealNo2Price)) {
			output.setText("數量不能輸入非數字或小於0，請重新輸入。");
			JOptionPane.showMessageDialog(null, "數量不能輸入非數字或小於0，請重新輸入。", "錯誤", JOptionPane.ERROR_MESSAGE);
			return;
		}

		o = new Order(Name, Integer.parseInt(mealNo1Price),
				Integer.parseInt(mealNo2Price));
		String showOrder = o.showOrder(vipMember.isSelected());
		outputText = showOrder;
		output.setText(showOrder);	
	}
	

	/**
	 * Create the frame.
	 */
	public AddOrderUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1218, 604);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle(appTitle);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 64));
		panel.setBounds(20, -25, 1192, 117);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel(appTitle);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 32));
		lblNewLabel.setBounds(436, 38, 322, 64);
		panel.add(lblNewLabel);
		
				
		JLabel lblTimer = new JLabel("");
		lblTimer.setBounds(704, 49, 425, 38);
		panel.add(lblTimer);
		lblTimer.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimer.setForeground(new Color(255, 255, 255));
		lblTimer.setFont(new Font("新細明體", Font.BOLD, 20));

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(128, 128, 64));
		panel_1.setBounds(43, 87, 420, 455);
		contentPane.add(panel_1);

		JLabel lblNewLabel_1 = new JLabel("姓名：");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("新細明體", Font.BOLD, 30));
		lblNewLabel_1.setBounds(30, 34, 105, 38);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_2 = new JLabel("2號餐：");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("新細明體", Font.BOLD, 30));
		lblNewLabel_1_2.setBounds(30, 234, 133, 38);
		panel_1.add(lblNewLabel_1_2);

		textName = new JTextField();
		textName.setHorizontalAlignment(SwingConstants.CENTER);
		textName.setFont(new Font("新細明體", Font.BOLD, 30));
		textName.setBounds(150, 22, 190, 60);
		panel_1.add(textName);
		textName.setColumns(10);

		textMeal1 = new JTextField();
		textMeal1.setHorizontalAlignment(SwingConstants.CENTER);
		textMeal1.setFont(new Font("新細明體", Font.BOLD, 30));
		textMeal1.setColumns(10);
		textMeal1.setBounds(236, 113, 100, 60);
		panel_1.add(textMeal1);

		textMeal2 = new JTextField();
		textMeal2.setHorizontalAlignment(SwingConstants.CENTER);
		textMeal2.setFont(new Font("新細明體", Font.BOLD, 30));
		textMeal2.setColumns(10);
		textMeal2.setBounds(236, 225, 95, 60);
		panel_1.add(textMeal2);

		JLabel lblNewLabel_1_2_1 = new JLabel("1號餐：");
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setFont(new Font("新細明體", Font.BOLD, 30));
		lblNewLabel_1_2_1.setBounds(30, 123, 132, 38);
		panel_1.add(lblNewLabel_1_2_1);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(128, 128, 64));
		panel_1_1.setBounds(513, 87, 678, 389);
		contentPane.add(panel_1_1);

		JLabel lblMealNo1Price = new JLabel(Order.getMealNo1Price() + "/份");
		lblMealNo1Price.setForeground(new Color(0, 0, 255));
		lblMealNo1Price.setFont(new Font("新細明體", Font.BOLD, 20));
		lblMealNo1Price.setBounds(32, 155, 105, 38);
		panel_1.add(lblMealNo1Price);

		JLabel lblMealNo2Price = new JLabel(Order.getMealNo2Price() + "/份");
		lblMealNo2Price.setForeground(new Color(0, 0, 255));
		lblMealNo2Price.setFont(new Font("新細明體", Font.BOLD, 20));
		lblMealNo2Price.setBounds(30, 264, 105, 38);
		panel_1.add(lblMealNo2Price);
		
		JTextArea textAreaOutput = new JTextArea();
		textAreaOutput.setBounds(8, 24, 506, 340);
		panel_1_1.add(textAreaOutput);
		
		// 是否為VIP會員 按鍵
		JCheckBox vipMember = new JCheckBox("是否為VIP會員");
		vipMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 若訂單已成立，數據有變動，作即時更新
				if(!textAreaOutput.getText().isEmpty()) {
					handlerOK(textAreaOutput,vipMember);	
				}
			}
		});
		vipMember.setBackground(new Color(128, 128, 64));
		vipMember.setForeground(new Color(255, 0, 0));
		vipMember.setFont(new Font("新細明體", Font.BOLD, 30));
		vipMember.setBounds(32, 322, 272, 53);
		panel_1.add(vipMember);

		textReceivedAmount = new JTextField();
		textReceivedAmount.setHorizontalAlignment(SwingConstants.CENTER);
		textReceivedAmount.setFont(new Font("新細明體", Font.BOLD, 30));
		textReceivedAmount.setColumns(10);
		textReceivedAmount.setBounds(726, 469, 286, 60);
		contentPane.add(textReceivedAmount);

		JLabel lblNewLabel_1_2_2 = new JLabel("請輸入收款金額：");
		lblNewLabel_1_2_2.setForeground(Color.WHITE);
		lblNewLabel_1_2_2.setFont(new Font("新細明體", Font.BOLD, 30));
		lblNewLabel_1_2_2.setBounds(474, 483, 290, 38);
		contentPane.add(lblNewLabel_1_2_2);

		// 1號餐+ 按鍵
		JButton btnMeal1Plus = new JButton("+");
		btnMeal1Plus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String mealNo1Price = textMeal1.getText();
				// 空值自動補0、非數字或小於0也補0
				if (mealNo1Price.isEmpty() || !Tool.isNumeric(mealNo1Price)) {
					mealNo1Price = "0";
					textMeal1.setText("0");
				}
				textMeal1.setText(Integer.parseInt(mealNo1Price) + 1 + "");
				
				// 若訂單已成立，數據有變動，作即時更新
				if(!textAreaOutput.getText().isEmpty()) {
					handlerOK(textAreaOutput,vipMember);	
				}
			}
		});
		btnMeal1Plus.setFont(new Font("新細明體", Font.BOLD, 20));
		btnMeal1Plus.setBounds(347, 120, 56, 52);
		panel_1.add(btnMeal1Plus);

		// 1號餐- 按鍵
		JButton btnMeal1Sub = new JButton("-");
		btnMeal1Sub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String mealNo1Price = textMeal1.getText();
				// 空值自動補0、非數字或小於0也補0
				if (mealNo1Price.isEmpty() || !Tool.isNumeric(mealNo1Price)) {
					mealNo1Price = "0";
					textMeal1.setText("0");
				}
				Integer count = Integer.parseInt(mealNo1Price) - 1;
				count = count < 0 ? 0 : count;
				textMeal1.setText(count + "");
				
				// 若訂單已成立，數據有變動，作即時更新
				if(!textAreaOutput.getText().isEmpty()) {
					handlerOK(textAreaOutput,vipMember);	
				}
			}
		});
		btnMeal1Sub.setFont(new Font("新細明體", Font.BOLD, 20));
		btnMeal1Sub.setBounds(168, 119, 56, 52);
		panel_1.add(btnMeal1Sub);
		
		// 2號餐+ 按鍵
		JButton btnMeal2Plus = new JButton("+");
		btnMeal2Plus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String mealNo2Price = textMeal2.getText();
				// 空值自動補0、非數字或小於0也補0
				if (mealNo2Price.isEmpty() || !Tool.isNumeric(mealNo2Price)) {
					mealNo2Price = "0";
					textMeal2.setText("0");
				}
				// 小於0設為0
				textMeal2.setText(Integer.parseInt(mealNo2Price) + 1 + "");

				// 若訂單已成立，數據有變動，作即時更新
				if(!textAreaOutput.getText().isEmpty()) {
					handlerOK(textAreaOutput,vipMember);	
				}
			}
		});
		btnMeal2Plus.setFont(new Font("新細明體", Font.BOLD, 20));
		btnMeal2Plus.setBounds(341, 229, 56, 52);
		panel_1.add(btnMeal2Plus);
		
		// 2號餐- 按鍵
		JButton btnMeal2Sub = new JButton("-");
		btnMeal2Sub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String mealNo2Price = textMeal2.getText();
				// 空值自動補0、非數字或小於0也補0
				if (mealNo2Price.isEmpty() || !Tool.isNumeric(mealNo2Price)) {
					mealNo2Price = "0";
					textMeal2.setText("0");
				}
				// 小於0設為0
				Integer count = Integer.parseInt(mealNo2Price) - 1;
				count = count < 0 ? 0 : count;
				textMeal2.setText(count + "");

				// 若訂單已成立，數據有變動，作即時更新
				if(!textAreaOutput.getText().isEmpty()) {
					handlerOK(textAreaOutput,vipMember);	
				}
			}
			
		});
		btnMeal2Sub.setFont(new Font("新細明體", Font.BOLD, 20));
		btnMeal2Sub.setBounds(170, 228, 56, 52);
		panel_1.add(btnMeal2Sub);
		
		

		// 確定 按鍵
		JButton btnOK = new JButton("確定");
		btnOK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				handlerOK(textAreaOutput,vipMember);			
			}
		});
		btnOK.setFont(new Font("新細明體", Font.BOLD, 20));
		btnOK.setBounds(530, 36, 118, 52);
		panel_1_1.add(btnOK);

		// 清除 按鍵
		JButton btnReset = new JButton("清除");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textName.setText("");
				textMeal1.setText("");
				textMeal2.setText("");
				textReceivedAmount.setText("");
				textAreaOutput.setText("");
			}
		});
		btnReset.setFont(new Font("新細明體", Font.BOLD, 20));
		btnReset.setBounds(530, 99, 118, 52);
		panel_1_1.add(btnReset);

		// 列印 按鍵
		JButton btnPrint = new JButton("列印");
		btnPrint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					textAreaOutput.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPrint.setFont(new Font("新細明體", Font.BOLD, 20));
		btnPrint.setBounds(528, 162, 118, 52);
		panel_1_1.add(btnPrint);

		// 離開 按鍵
		JButton btnExit = new JButton("離開");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("新細明體", Font.BOLD, 20));
		btnExit.setBounds(530, 232, 118, 52);
		panel_1_1.add(btnExit);		
		
		// 找零 按鍵
		JButton btnChange = new JButton("找零");
		btnChange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String receivedAmount = textReceivedAmount.getText();
				int sum = vipMember.isSelected()? (int)(o.getSum() * 0.9):o.getSum();
				
				if (receivedAmount.isEmpty()) {
					JOptionPane.showMessageDialog(null, "收款金額不能為空，請重新輸入。", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (textAreaOutput.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "訂單尚未成立，請先建立訂單。", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!Tool.isNumeric(receivedAmount)) {
					JOptionPane.showMessageDialog(null, "不能輸入非數字或小於0，請重新輸入。", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (Integer.parseInt(receivedAmount) < sum) {
					JOptionPane.showMessageDialog(null, "收款金額小於訂單金額，請收足款項。", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				String changeNumber = Tool.changeCalculator(Integer.parseInt(receivedAmount),sum);
				textAreaOutput.setText("");
				textAreaOutput.setText(outputText+"\n"+changeNumber);
			}
		});
		btnChange.setFont(new Font("新細明體", Font.BOLD, 20));
		btnChange.setBounds(1042, 475, 118, 52);
		contentPane.add(btnChange);
		
		// 登出 按鍵
		JButton btnLogout = new JButton("登出");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginUI frame = new LoginUI();
				frame.setVisible(true);
				dispose();
			}
		});
		btnLogout.setFont(new Font("新細明體", Font.BOLD, 20));
		btnLogout.setBounds(530, 307, 118, 52);
		panel_1_1.add(btnLogout);
		
		// 啟動clock
		Clock.startAutoUpdateClock(lblTimer);
	}
}
