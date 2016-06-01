package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.net.URL;

import org.jsoup.Jsoup;

import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.jsoup.nodes.Document;



public class Steam_clean {

	public static void main(String[] args) {


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

			

			
			JSONArray game_array = new JSONArray();
			
			// 取出Iterator中的集合遊戲資料
			while (it.hasNext()) {

				count++;
				
JSONObject collection = (JSONObject) it.next();
				// Debug訊息
				System.out.println("第" + count + "款，遊戲的id為 **" + collection.get("appid").toString() + "** " + "遊戲名為 **"
						+ collection.get("name") + "** ");


				File check_file_final =new File("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\pass check2\\"+collection.get("appid").toString()+"_pass_check2.json");
				
				
				if (check_file_final.exists()) {
					
					// 建立Json Array
					
					// 建立刷新Json物件
					JSONObject review_obj = new JSONObject();
					// user_name 評論作者暱稱
					review_obj.put("appid", collection.get("appid").toString());

					// user_profile 評論作者個人檔案之url
					review_obj.put("name", collection.get("name").toString());

			
					// 寫入Json物件與JsonArray

					game_array.add(review_obj);
					
					try {
						// 建立抓取到遊戲評論的JSON檔
						FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_05_23_clean.json");
						Writer json_writer = new OutputStreamWriter(fos, "UTF8");

						// 寫入JSON物件
						json_writer.write("{" + "\"app\" :" + game_array.toJSONString() + "}");

						// 關閉寫入
						json_writer.flush();
						json_writer.close();
					} catch (Exception e) {
						System.out.println(e.toString());
					}

					
					
					
					
					
					
				}
				
				
				
				
				
				


			}
			System.out.println("恭喜!終於全部的遊戲都抓完囉!");

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
		
		
		
		
	}

}
