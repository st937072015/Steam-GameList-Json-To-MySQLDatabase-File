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
		//Mysql_function gosql = new Mysql_function();
		 
		
		
		    // 開啟火狐瀏覽器
		    WebDriver firefoxdrive = new FirefoxDriver();
            
			firefoxdrive.get("http://store.steampowered.com/app/282350/");
			
			
	
			
			
			// 選取介面語言點擊
			firefoxdrive.findElement(By.id("language_pulldown")).click();
			firefoxdrive.findElement(By.linkText("English（英文）")).click();
			
		   // 設定必要出現之網頁tag等待秒數警告
		   WebDriverWait wait = new WebDriverWait(firefoxdrive,5);

           wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//span[@id='language_pulldown']"), firefoxdrive.findElement(By.xpath("//span[@id='language_pulldown']")).getText()));
           wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='app_tags popular_tags]")));
           
           // 點擊展開遊戲所屬之全部屬性標籤之清單
           firefoxdrive.findElement(By.xpath("//div[@class='app_tag add_button']")).click();
           
           // 將全部找到的屬性tag網頁標籤內容全塞進List
   		   List<WebElement> get_alltag = firefoxdrive.findElements(By.xpath("html/body/div/div/div/div/div/div/div/div/div[@class='app_tag_control popular']/a[@class='app_tag']"));
   		
   		   // 計算抓到的遊戲屬性標籤總數量
   		int c = firefoxdrive.findElements(By.xpath("html/body/div/div/div/div/div/div/div/div/div[@class='app_tag_control popular']/a[@class='app_tag']")).size();
   		
   		System.out.println("一共有"+c+"個屬性標籤");
   		
		
   		int alltag_count=0;
   		
   		// 提取遊戲的每個屬性標籤文字內容
		for(WebElement  count :get_alltag){
			alltag_count++;
			System.out.println("第"+alltag_count+"個屬性標籤，"+"屬性為"+count.getText());
			
			
			
		}
		
           
           
           
           
			//System.out.println(firefoxdrive.findElement(By.xpath("//span[@id='language_pulldown']")).getText());
			
			//System.out.println(firefoxdrive.findElement(By.xpath("//a[@class='app_tag']")).getText());
			
			//int tag_count = firefoxdrive.findElement(By.xpath("")).size();
            //String get_tag_content = firefoxdrive.findElement(By.xpath("")).getText();
		
			// 抓取完畢後關閉火狐瀏覽器
			firefoxdrive.close();
			
			

			
			
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
