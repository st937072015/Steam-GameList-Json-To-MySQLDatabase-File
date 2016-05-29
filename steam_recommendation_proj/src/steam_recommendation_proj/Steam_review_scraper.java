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
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Steam_review_scraper {



	public static void main(String[] args) {

		
		
		    // 開啟火狐瀏覽器
		    WebDriver firefoxdrive = new FirefoxDriver();
            
			firefoxdrive.get("http://store.steampowered.com/app/431621/");
			
			// 取得目前url網址字串
			String url = firefoxdrive.getCurrentUrl();
			
			// 判斷是否有出現年齡檢查畫面
			// 以及判斷是否為在Steam商場的有效真實遊戲
	        if (url.contains("agecheck")) {
	        	
	        // 自動點擊送出合法年齡	
	        firefoxdrive.findElement(By.xpath("//select[@id='ageYear']/option[@value='1986']")).click();
	        firefoxdrive.findElement(By.xpath("//form[@id='agecheck_form']/a")).click();	
			}
	        else if (url.equals("http://store.steampowered.com/")) {
				
	        	// Debug
	        	System.out.println("此遊戲為Steam商城的無效遊戲!!因為網址自動導向到Steam首頁");
	        	
	        	// 直接關閉火狐瀏覽器
				firefoxdrive.close();
	        	
			}
			
			
			// 選取介面語言點擊
			firefoxdrive.findElement(By.id("language_pulldown")).click();
			firefoxdrive.findElement(By.linkText("English（英文）")).click();
			
			// 等待5秒讀取
	        try {
	          	 Thread.sleep(5000);
	        } catch(Exception e) {
	          	    	
	        System.out.println(e);
	          	   
	        }
		   
           
	       // 若Most Helpful 與 Load More按鈕不存在就直接歸類為無效遊戲
	        if ((firefoxdrive.findElements(By.id("ReviewsTab_all")).size() == 0 && firefoxdrive.findElements(By.xpath("//a[@class='btnv6_blue_blue_innerfade btn_medium']")).size() == 0)||(firefoxdrive.findElements(By.id("ReviewsTab_all")).size()==0) ) {
				
	        	// Debug
	        	System.out.println("此遊戲為Steam商城的無效遊戲!!因為沒有Most Helpful與Load More之按鈕");
	        	
	        	// 直接關閉火狐瀏覽器
				firefoxdrive.close();	
	        	
	        	
	         // 若確認沒問題則開始執行評論腳本	
			}else{
	        
	        
           // 點擊Most Helpful之遊戲評論
           firefoxdrive.findElement(By.id("ReviewsTab_all")).click();
           
           // 等待5秒讀取
           try {
       	        Thread.sleep(5000);
       	    } catch(Exception e) {
       	    	
       	    System.out.println(e);
       	   
       	    }
           
           
           // 若評論內容標籤不存在就直接歸類為無效遊戲
           if (firefoxdrive.findElements(By.xpath("html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='content']")).size()==0) {
           	    // Debug
	        	System.out.println("此遊戲為Steam商城的無效遊戲!!因為根本沒評論");
	        	
	        	// 直接關閉火狐瀏覽器
				firefoxdrive.close();	
		   }
           
           
           // 等待標籤讀取之物件(設定10秒)
           WebDriverWait wait = new WebDriverWait(firefoxdrive, 10);
           
           // 判斷抓到評論數量計數器
           int count = 0;
           
           // debug從get_allreview_content取出的評論內容index(第幾筆)
           int allreview_content_count=0;
           
          // 開始執行評論需求數量判斷處理 
          while (true) {
			
		

      	   count = firefoxdrive.findElements(By.xpath("html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='content']")).size();
        	   
      	   
      	   
      	       // 等待Load More按鈕出現
      	       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btnv6_blue_blue_innerfade btn_medium']")));
        	   
      	       // 確訂出現後再進行點擊
      	       firefoxdrive.findElement(By.xpath("//a[@class='btnv6_blue_blue_innerfade btn_medium']")).click();
        	   
        	   
        	   
      	       // 等待5秒讀取
        	   try {
          	        Thread.sleep(5000);
          	    } catch(Exception e) {
          	    	
          	    System.out.println(e);
          	   
          	    }
        	   
        	   
        	   
        	   System.out.println("目前抓到一共"+count+"個評論");
        	   
        	   if (count == 55) {
        		   
        		   
        		   List<WebElement> get_allreview_content = firefoxdrive.findElements(By.xpath("html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='content']"));
        		   
        		   
        		   
        		   System.out.println("目前抓到一共"+count+"個評論");
        		   
        			for(WebElement  webele_count :get_allreview_content){
       			allreview_content_count++;
       			System.out.println("第"+allreview_content_count+"個遊戲評論，"+"評論內容為"+webele_count.getText());
       			
       			
       			
       		}
        		   
        		   
        		   
        		   
        		   break;
				
			}else if (count > 55) {
				
				List<WebElement> get_allreview_content = firefoxdrive.findElements(By.xpath("html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='content']"));
				System.out.println("一開始Size為:"+get_allreview_content.size()+"，count為"+count);
				int count_more =get_allreview_content.size();
				
				while (true) {
					
					count_more--;
					get_allreview_content.remove(count_more);
					System.out.println("目前減掉Size為:"+get_allreview_content.size());
					
					if (count_more == 55) {
						
						
						for(WebElement  webele_count :get_allreview_content){
			       			allreview_content_count++;
			       			System.out.println("第"+allreview_content_count+"個遊戲評論，"+"評論內容為"+webele_count.getText());
			       			
			       			
			       			
			       		}
						
						
						break;
						
						
					}
					
					
					
					
				}
				
				break;
				
			}else if (count < 55 && firefoxdrive.findElements(By.className("no_more_reviews")).size() > 0 && firefoxdrive.findElements(By.xpath("//a[@class='btnv6_blue_blue_innerfade btn_medium']")).size() == 0) {
				
	       	        // Debug
		        	System.out.println("此遊戲為Steam商城的無效遊戲!!因為評論數不足");
		        	
		        	// 直接關閉火狐瀏覽器
					firefoxdrive.close();	
				
				
				break;
				
			}
        	   
        	   
        	  
        	  
	}
           
        	   
			}
        	   
			
		
           
           // 將全部找到的屬性tag網頁標籤內容全塞進List
   		   //List<WebElement> get_allreview_content = firefoxdrive.findElements(By.xpath("html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='content']"));
   		
   		   // 計算抓到的遊戲屬性標籤總數量
   		//int c = firefoxdrive.findElements(By.xpath("html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='content']")).size();
   		
   		//System.out.println("一共有"+c+"個遊戲評論");
   		
		
   		//int allreview_content_count=0;
   		
   		// 提取遊戲的每個屬性標籤文字內容
		//for(WebElement  count :get_allreview_content){
			//allreview_content_count++;
			//System.out.println("第"+allreview_content_count+"個遊戲評論，"+"評論內容為"+count.getText());
			
			
			
		//}
		
           
           
           
           
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
