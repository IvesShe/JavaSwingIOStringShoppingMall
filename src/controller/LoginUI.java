package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import util.*;
import javax.swing.SwingConstants;

public class LoginUI extends JFrame {

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
					LoginUI frame = new LoginUI();
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
	public LoginUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 897, 652);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle(appTitle + " 登入");

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
		lblNewLabel.setBounds(141, 86, 130, 68);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("密碼：");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("新細明體", Font.BOLD, 40));
		lblNewLabel_1.setBounds(141, 203, 130, 68);
		contentPane.add(lblNewLabel_1);

		textUsername = new JTextField();
		textUsername.setFont(new Font("新細明體", Font.BOLD, 40));
		textUsername.setBounds(294, 82, 315, 66);
		contentPane.add(textUsername);
		textUsername.setColumns(10);

		textPassword = new JTextField();
		textPassword.setFont(new Font("新細明體", Font.BOLD, 40));
		textPassword.setColumns(10);
		textPassword.setBounds(295, 199, 315, 66);
		contentPane.add(textPassword);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(128, 128, 64));
		panel.setBounds(0, 1, 854, 108);
		contentPane.add(panel);

		JLabel lblNewLabel_2 = new JLabel("麥當當購物 登入畫面");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("新細明體", Font.BOLD, 30));
		lblNewLabel_2.setBounds(219, 0, 305, 64);
		panel.add(lblNewLabel_2);
		
		JLabel lblTimer = new JLabel("");
		lblTimer.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimer.setForeground(Color.WHITE);
		lblTimer.setFont(new Font("新細明體", Font.BOLD, 20));
		lblTimer.setBounds(529, 12, 425, 38);
		panel.add(lblTimer);

		JButton btnNewButton = new JButton("登入");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = textUsername.getText();
				String password = textPassword.getText();
				String inputVerificationCode = textVerificationCode.getText();

				// 輸入不能為空
				if (username.isEmpty() || password.isEmpty() || inputVerificationCode.isEmpty()) {
					JOptionPane.showMessageDialog(null, "輸入值不能為空，請重新輸入。", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					// 檢查驗證碼(忽略大小寫)
					if (!inputVerificationCode.equalsIgnoreCase(verificationCode)) {
						// 驗證碼錯誤
						JOptionPane.showMessageDialog(null, "驗證碼錯誤，請重新輸入。", "錯誤", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (AccountManager.login(username, password)) {
						// 登入成功
						new AddOrderUI().setVisible(true);
						dispose(); // 關閉登入視窗
					} else {
						// 登入失敗
						JOptionPane.showMessageDialog(null, "帳號或密碼錯誤，請重新輸入。", "錯誤", JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("新細明體", Font.BOLD, 40));
		btnNewButton.setBounds(119, 422, 214, 107);
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
		btnNewButton_1.setBounds(390, 422, 214, 107);
		contentPane.add(btnNewButton_1);

		JButton btnRegister = new JButton("前往註冊");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new RegisterUI().setVisible(true);
				dispose(); // 關閉登入視窗
			}
		});
		btnRegister.setFont(new Font("新細明體", Font.BOLD, 30));
		btnRegister.setBounds(654, 454, 182, 75);
		contentPane.add(btnRegister);

		JLabel lblNewLabel_1_1 = new JLabel("驗證碼：");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("新細明體", Font.BOLD, 40));
		lblNewLabel_1_1.setBounds(142, 328, 175, 68);
		contentPane.add(lblNewLabel_1_1);

		textVerificationCode = new JTextField();
		textVerificationCode.setFont(new Font("新細明體", Font.BOLD, 40));
		textVerificationCode.setColumns(10);
		textVerificationCode.setBounds(331, 324, 280, 66);
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
		lblVerificationCode.setBounds(646, 326, 201, 68);
		contentPane.add(lblVerificationCode);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("點擊驗證碼可產生新的驗證碼，不分大小寫");
		lblNewLabel_1_1_1.setForeground(new Color(255, 128, 0));
		lblNewLabel_1_1_1.setFont(new Font("新細明體", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(216, 540, 414, 68);
		contentPane.add(lblNewLabel_1_1_1);
		
		// 啟動clock
		Clock.startAutoUpdateClock(lblTimer);

	}
}
