package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Control_hub {

	public static void main(String[] args) {

		try {

			// 進行json檔案讀取
			FileReader steamreader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_05_23_clean.json");

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

				// Debug訊息
				System.out.println("第" + count + "款，遊戲的id為 **" + collection.get("appid").toString() + "** " + "遊戲名為 **"
						+ collection.get("name").toString() + "** ");
				
                // 檢查是否已經有抓完評論
				File check_file = new File("C:\\Users\\John-Wall\\Desktop\\Steam_game_review\\"+collection.get("appid").toString()+".json");
				
				
				if (check_file.exists()) {
					
					
				// Debug訊息
				System.out.println("第" + count + "款，遊戲的id為 **" + collection.get("appid").toString() + "** " + "遊戲名為 **"
							+ collection.get("name").toString() + "** "+"此遊戲已經抓過評論囉!!!!!!!!!!");
					
					
					
				}else {
					
					
					Steam_review_scraper.do_steam_review_scraper_now(collection.get("appid").toString(), 250,
							"C:\\Users\\John-Wall\\Desktop\\Steam_game_review\\" + collection.get("appid").toString() + ".json");
					
					// 等待3秒讀取
					try {
						Thread.sleep(3000);
					} catch (Exception e) {

						System.out.println(e);

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
