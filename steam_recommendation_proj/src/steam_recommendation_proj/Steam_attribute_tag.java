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
import org.openqa.jetty.html.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.Selenium;

import org.openqa.selenium.WebElement;

public class Steam_attribute_tag {



	public static void main(String[] args) {
		// 呼叫寫好的Mysql之function
		Mysql_function gosql = new Mysql_function();
		 
		    WebDriver firefoxdrive = new FirefoxDriver();
        
			firefoxdrive.get("http://store.steampowered.com/app/271590/");
			
			firefoxdrive.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			// 選取介面語言點擊
			firefoxdrive.findElement(By.id("language_pulldown")).click();
			firefoxdrive.findElement(By.linkText("English（英文）")).click();
			
			WebDriverWait wait = new WebDriverWait(firefoxdrive,10);

           wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//span[@id='language_pulldown']"), firefoxdrive.findElement(By.xpath("//span[@id='language_pulldown']")).getText()));
			
			System.out.println(firefoxdrive.findElement(By.xpath("//span[@id='language_pulldown']")).getText());
			
			System.out.println(firefoxdrive.findElement(By.xpath("//a[@class='app_tag']")).getText());
			
			//int tag_count = firefoxdrive.findElement(By.xpath("")).size();
            //String get_tag_content = firefoxdrive.findElement(By.xpath("")).getText();
			
			firefoxdrive.close();
			
			
			System.out.println("");
			
			
			/*
		try {

			// 進行json檔案讀取
			FileReader steamreader = new FileReader("C:\\Users\\John-Wall\\Desktop\\SteamGameList_2016_05_23.json");

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(steamreader);

			// 讀取steam遊戲清單(app)之陣列
			JSONArray app = (JSONArray) jsonObject.get("app");

			// 將JSONArray物件創建成Iterator迭代器
			Iterator it = app.iterator();

			int count = 0;
             
			
			
			
			
			// 取出Iterator中的集合遊戲資料
			while (it.hasNext()) {

				count++;
				JSONObject collection = (JSONObject) it.next();
				
				WebDriver firefoxdrive = new FirefoxDriver();
				

                
				
		        
				firefoxdrive.get("http://store.steampowered.com/app/"+collection.get("appid").toString()+"/");
				
				firefoxdrive.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				
				// 選取介面語言點擊
				firefoxdrive.findElement(By.id("language_pulldown")).click();
				firefoxdrive.findElement(By.linkText("English（英文）")).click();
				

				
				firefoxdrive.close();
			}
			System.out.println("恭喜!全部插入完成!");

		} catch (

		FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (ParseException e) {
			System.out.println(e.toString());
		} catch (NullPointerException e) {
			System.out.println(e.toString());
		}
*/
	}

}
