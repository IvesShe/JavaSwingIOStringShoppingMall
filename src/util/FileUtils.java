package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

	// 確保檔案存在，若不存在則創建檔案
	public static void ensureFileExists(String fileName) throws IOException {
		File file = new File(fileName);
		if (!file.exists()) {
			if (!file.createNewFile()) { // 若檔案無法創建，則拋出異常
				throw new IOException("創建檔案失敗。");
			}
		}
		
		// 若已有預設帳號就不再重複寫入
		if(AccountManager.accountExists("admin")) 
			return;
		// 寫入一筆預設的帳號密碼
        try (
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true)) // 自動關閉
        ) {
            writer.write("admin:123456");
            writer.newLine(); // 換行
        } catch (IOException e) {
//            System.out.println("寫入資料時發生錯誤：" + e.getMessage());
        }
	}
}
