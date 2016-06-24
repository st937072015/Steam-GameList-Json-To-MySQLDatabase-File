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

public class Control_hub3 {

	public static void main(String[] args) {

		try {

			// 進行已過濾的遊戲id之json檔案讀取
			FileReader steamreader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_sample_250.json");

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(steamreader);

			// 讀取有250筆評論的steam遊戲之id arraylist
			JSONArray app = (JSONArray) jsonObject.get("app_sample_250");

			// 將JSONArray物件創建成Iterator迭代器
			Iterator it = app.iterator();
			

			int count = 0;

			// 取出Iterator中的集合使用者資料
			while (it.hasNext()) {

				count++;
				JSONObject collection = (JSONObject) it.next();

				// Debug訊息
				System.out.println("第" + count + "遊戲的id為 **" + collection.get("appid").toString() + "** " + "遊戲名稱為 **"
						+ collection.get("name").toString() + "** ");
				
                // 檢查遊戲評論淨化後輸出的檔案是否已經存在
				File check_file = new File("C:\\Users\\John-Wall\\Desktop\\Steam_game_review_clean\\"+collection.get("appid").toString()+".json");
				
				
				if (!check_file.exists()) {
					
					

					
					Steam_game_review_clean clean = new Steam_game_review_clean();
					
					clean.do_game_review_clean(collection.get("appid").toString());
					
				}else{
					
					// Debug訊息
					System.out.println("第" + count + "遊戲的id為 **" + collection.get("appid").toString() + "** " + "遊戲名稱為 **"
								+ collection.get("name").toString() + "** "+"，此遊戲的評論已經淨化過囉！");
					System.out.println("-----------------------------------------");
					
				}
				
				
				
				
				

			}


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
