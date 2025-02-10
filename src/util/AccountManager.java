package util;

import java.io.*;

public class AccountManager {

	public static final String FILE_NAME = "accounts.txt";

	// 新增帳號和密碼
	public static boolean addAccount(String username, String password) throws IOException {
		// 檢查帳號是否已存在
		if (accountExists(username)) {
//			System.out.println("帳號已存在！");
			return false;
		}

		// 使用 try-with-resources 保證流會自動關閉
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
			writer.write(username + ":" + password);
			writer.newLine(); // 寫入換行符號
			return true;
		}
	}

	// 檢查帳號是否已存在
	public static boolean accountExists(String username) throws IOException {
		// 使用 try-with-resources 保證流會自動關閉
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith(username + ":")) { // 檢查檔案內是否已存在此帳號
					return true;
				}
			}
		}
		return false;
	}

	// 驗證帳號和密碼（登入功能）
	public static boolean login(String username, String password) throws IOException {
		// 使用 try-with-resources 保證流會自動關閉
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] account = line.split(":");
				if (account.length == 2 && account[0].equals(username) && account[1].equals(password)) {
					return true; // 登入成功
				}
			}
		}
		return false; // 登入失敗
	}
}
