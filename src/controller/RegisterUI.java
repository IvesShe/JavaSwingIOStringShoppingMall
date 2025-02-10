package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.SwingConstants;

import util.*;

public class RegisterUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsername;
	private JTextField textPassword;
	private static String appTitle = "麥當當購物";
	private JTextField textVerificationCode;
	private String verificationCode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUI frame = new RegisterUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 898, 602);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle(appTitle + " 註冊");

		setContentPane(contentPane);//
		contentPane.setLayout(null);

		// 確保檔案存在
		try {
			FileUtils.ensureFileExists(AccountManager.FILE_NAME);
		} catch (IOException e) {
			System.out.println("初始化檔案時發生錯誤：" + e.getMessage());
			return;
		}

		// 產生驗證碼
		verificationCode = Tool.generateRandomCode();

		JLabel lblNewLabel = new JLabel("帳號：");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 40));
		lblNewLabel.setBounds(155, 85, 130, 68);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("密碼：");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("新細明體", Font.BOLD, 40));
		lblNewLabel_1.setBounds(157, 202, 130, 68);
		contentPane.add(lblNewLabel_1);

		textUsername = new JTextField();
		textUsername.setFont(new Font("新細明體", Font.BOLD, 40));
		textUsername.setBounds(317, 82, 315, 66);
		contentPane.add(textUsername);
		textUsername.setColumns(10);

		textPassword = new JTextField();
		textPassword.setFont(new Font("新細明體", Font.BOLD, 40));
		textPassword.setColumns(10);
		textPassword.setBounds(318, 199, 315, 66);
		contentPane.add(textPassword);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(128, 128, 64));
		panel.setBounds(0, 1, 854, 108);
		contentPane.add(panel);

		JLabel lblNewLabel_2 = new JLabel("麥當當購物 註冊畫面");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("新細明體", Font.BOLD, 30));
		lblNewLabel_2.setBounds(227, 10, 577, 64);
		panel.add(lblNewLabel_2);
		
		JLabel lblTimer = new JLabel("");
		lblTimer.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimer.setForeground(Color.WHITE);
		lblTimer.setFont(new Font("新細明體", Font.BOLD, 20));
		lblTimer.setBounds(537, 22, 425, 38);
		panel.add(lblTimer);

		JButton btnNewButton = new JButton("註冊");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = textUsername.getText();
				String password = textPassword.getText();
				String inputVerificationCode = textVerificationCode.getText();

				// 輸入不能為空
				if (username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "輸入值不能為空，請重新輸入。", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// 檢查驗證碼(忽略大小寫)
				if (!inputVerificationCode.equalsIgnoreCase(verificationCode)) {
					// 驗證碼錯誤
					JOptionPane.showMessageDialog(null, inputVerificationCode+"驗證碼錯誤，請重新輸入。"+verificationCode, "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// 使用正則檢查帳號是否符合規則
				String valMessage = Tool.validateUsername(username);
				if(!valMessage.equals("true")) {
					JOptionPane.showMessageDialog(null, valMessage, "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					if (!AccountManager.addAccount(username, password)) {
						JOptionPane.showMessageDialog(null, "帳號已存在，請重新輸入。", "錯誤", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						JOptionPane.showMessageDialog(null, username + "註冊成功，將前往登入畫面。", "完成",
								JOptionPane.INFORMATION_MESSAGE);
						new LoginUI().setVisible(true);
						dispose(); // 關閉登入視窗
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("新細明體", Font.BOLD, 40));
		btnNewButton.setBounds(110, 408, 214, 107);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("清除");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textUsername.setText("");
				textPassword.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("新細明體", Font.BOLD, 40));
		btnNewButton_1.setBounds(381, 408, 214, 107);
		contentPane.add(btnNewButton_1);

		JButton btnLogin = new JButton("前往登入");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new LoginUI().setVisible(true);
				dispose(); // 關閉登入視窗
			}
		});
		btnLogin.setFont(new Font("新細明體", Font.BOLD, 30));
		btnLogin.setBounds(646, 445, 177, 71);
		contentPane.add(btnLogin);

		JLabel lblNewLabel_1_1 = new JLabel("驗證碼：");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("新細明體", Font.BOLD, 40));
		lblNewLabel_1_1.setBounds(155, 316, 175, 68);
		contentPane.add(lblNewLabel_1_1);

		textVerificationCode = new JTextField();
		textVerificationCode.setFont(new Font("新細明體", Font.BOLD, 40));
		textVerificationCode.setColumns(10);
		textVerificationCode.setBounds(351, 313, 280, 66);
		contentPane.add(textVerificationCode);

		// 驗證碼
		JLabel lblVerificationCode = new JLabel(verificationCode);
		lblVerificationCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 產生新的驗證碼
				verificationCode = Tool.generateRandomCode();
				lblVerificationCode.setText(verificationCode);
			}
		});
		lblVerificationCode.setForeground(new Color(255, 128, 0));
		lblVerificationCode.setFont(new Font("新細明體", Font.BOLD, 40));
		lblVerificationCode.setBounds(666, 315, 201, 68);
		contentPane.add(lblVerificationCode);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("點擊驗證碼可產生新的驗證碼，不分大小寫");
		lblNewLabel_1_1_1.setForeground(new Color(255, 128, 0));
		lblNewLabel_1_1_1.setFont(new Font("新細明體", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(233, 515, 414, 50);
		contentPane.add(lblNewLabel_1_1_1);
		
		// 啟動clock
		Clock.startAutoUpdateClock(lblTimer);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("帳號首字母需英文，長度最少5個字、最長12個字，只能包含英文字母和數字");
		lblNewLabel_1_1_1_1.setForeground(new Color(255, 128, 0));
		lblNewLabel_1_1_1_1.setFont(new Font("新細明體", Font.BOLD, 20));
		lblNewLabel_1_1_1_1.setBounds(147, 149, 726, 50);
		contentPane.add(lblNewLabel_1_1_1_1);

	}
}
