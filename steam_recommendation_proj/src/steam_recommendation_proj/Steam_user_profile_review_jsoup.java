package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Steam_user_profile_review_jsoup {

	public static void main(String[] args) {

		try {



			int count = 0;

	

			// 取出Iterator中的集合遊戲資料


				count++;		
				
				
			// 要存遊戲屬性tag用
			JSONArray tag_array = new JSONArray();

				
				// Debug訊息
				System.out.println();

			
			
                    
					// 執行jsoup動作
					URL url = new URL("http://steamcommunity.com/profiles/76561198000365424/recommended/?p=1");

					Document target_xml = Jsoup.parse(url, 20000);

					List<Element> user_profile_game_review = target_xml.select("div.review_stat > div.giantNumber.ellipsis");

					for (int i = 0; i < user_profile_game_review.size(); i++) {
                         
					    // 獲取Steam評論作者User profile 總評論數量並轉成float型態	
						float review_number=Integer.parseInt(user_profile_game_review.get(0).text());
						
						// 進行總頁數換算
						int review_all_page =(int) Math.ceil(review_number/10);
						
						// DEBUG
						System.out.println("此玩家所發過評論第" + i + "個遊戲評論內容為：" + user_profile_game_review.get(i).text()+"評論總頁數為"+review_all_page);

						
						/*
						// 建立刷新Json物件
						JSONObject tag_obj = new JSONObject();
						// 遊戲所屬的屬性標籤
						tag_obj.put("tag", game_attribute_tag_list.get(i).text());

						// 寫入Json物件與JsonArray

						tag_array.add(tag_obj);
						
						*/

					}
                   /*
					try {
						// 建立抓取到遊戲屬性tag的JSON檔
						FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_game_tag\\"
								+ collection.get("appid") + "_attribute_tag.json");
						Writer json_writer = new OutputStreamWriter(fos, "UTF8");

						// 寫入JSON物件
						json_writer.write("{" + "\"steam_attribute_tag\" :" + tag_array.toJSONString() + "}");

						// 關閉寫入
						json_writer.flush();
						json_writer.close();
					} catch (Exception e) {
						System.out.println(e.toString());
					}

				*/

			
			System.out.println("恭喜!終於全部的遊戲之屬性標籤都抓完囉!");

		} catch (

		FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (NullPointerException e) {
			System.out.println(e.toString());
		}

	}

}
