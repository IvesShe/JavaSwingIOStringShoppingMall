package util;

import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Tool {
	// 利用正則判斷是否為數字
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	// 找零計算
	public static String changeCalculator(int receivedAmount, int orderAmount) {

		// 計算找零金額
		int change = receivedAmount - orderAmount;

		// 設定各面額的數值
		int[] denominations = { 1000, 500, 100, 50, 10, 5, 1 };

		// 使用 StringBuilder 收集輸出的結果
		StringBuilder result = new StringBuilder();

		// 收集找零金額
		result.append(String.format("找零金額: %,d元\n", change));

		// 計算並收集每個面額的張數
		for (int denomination : denominations) {
			int count = change / denomination; // 計算該面額需要的張數
			change = change % denomination; // 計算剩餘的找零金額

			if (count > 0) {
				// 收集每個面額的張數
				String unit = (denomination == 50 || denomination == 10 || denomination == 5 || denomination == 1) ? "個": "張";
				result.append(String.format("%-6d元: %2d %s\n", denomination, count, unit));
			}
		}
		return result.toString();

	}
	
	// 生成一個隨機的4位驗證碼
    public static String generateRandomCode() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder verificationCode = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(characters.length());
            verificationCode.append(characters.charAt(index));
        }

        return verificationCode.toString();
    }
    
    // 利用正則判斷所註冊的帳號
    public static String validateUsername(String username) {
        // 長度檢查
        if (username.length() < 5) {
            return "帳號長度太短，最少需要 5 個字元。";
        }
        if (username.length() > 12) {
            return "帳號長度太長，最多可以有 12 個字元。";
        }

        // 使用正則表達式檢查格式
        String regex = "^[A-Za-z][A-Za-z0-9]{4,11}$";  // 首字母大寫，後面可以是英文字母和數字，總長度 5 到 12 個字元
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);

        if (!matcher.matches()) {
            return "帳號格式錯誤：首字母必須英文，並且只能包含英文字母和數字。";
        }

        return "true";
    }

}
