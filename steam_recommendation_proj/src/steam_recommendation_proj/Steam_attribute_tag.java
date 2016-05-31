package steam_recommendation_proj;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Steam_attribute_tag {

	public static void main(String[] args) {
		// 呼叫寫好的Mysql之function
		// Mysql_function gosql = new Mysql_function();

		// 開啟火狐瀏覽器
		WebDriver firefoxdrive = new FirefoxDriver();

		firefoxdrive.get("http://store.steampowered.com/app/208650/");

		// 取得目前url網址字串
		String url = firefoxdrive.getCurrentUrl();

		// 判斷是否有出現年齡檢查畫面
		if (url.contains("agecheck")) {

			// 自動點擊送出合法年齡
			firefoxdrive.findElement(By.xpath("//select[@id='ageYear']/option[@value='1986']")).click();
			firefoxdrive.findElement(By.xpath("//form[@id='agecheck_form']/a")).click();
		}

		// 選取介面語言點擊
		firefoxdrive.findElement(By.id("language_pulldown")).click();
		firefoxdrive.findElement(By.linkText("English（英文）")).click();

		// 設定必要出現之網頁tag等待秒數警告
		WebDriverWait wait = new WebDriverWait(firefoxdrive, 5);

		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//span[@id='language_pulldown']"),
				firefoxdrive.findElement(By.xpath("//span[@id='language_pulldown']")).getText()));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='app_tags popular_tags]")));

		// 點擊展開遊戲所屬之全部屬性標籤之清單
		firefoxdrive.findElement(By.xpath("//div[@class='app_tag add_button']")).click();

		// 將全部找到的屬性tag網頁標籤內容全塞進List
		List<WebElement> get_alltag = firefoxdrive.findElements(By.xpath(
				"html/body/div/div/div/div/div/div/div/div/div[@class='app_tag_control popular']/a[@class='app_tag']"));

		// 計算抓到的遊戲屬性標籤總數量
		int c = firefoxdrive
				.findElements(By
						.xpath("html/body/div/div/div/div/div/div/div/div/div[@class='app_tag_control popular']/a[@class='app_tag']"))
				.size();

		System.out.println("一共有" + c + "個屬性標籤");

		int alltag_count = 0;

		// 提取遊戲的每個屬性標籤文字內容
		for (WebElement count : get_alltag) {
			alltag_count++;
			System.out.println("第" + alltag_count + "個屬性標籤，" + "屬性為" + count.getText());

		}

		firefoxdrive.close();

	}

}
