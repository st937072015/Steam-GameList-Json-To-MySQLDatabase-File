package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.server.handler.MaximizeWindow;

public class Control_hub9 {

	public static void main(String[] args) {

		try {

			 // 讀取遊戲清單
			FileReader list_250_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_sample_250.json");
			JSONParser list_250_parser = new JSONParser();
			JSONObject list_250_read_parser = (JSONObject) list_250_parser.parse(list_250_json_reader);

			JSONArray list_250_array = (JSONArray) list_250_read_parser.get("app_sample_250");

			Iterator list_250_it = list_250_array.iterator();
			
			JSONArray review_content_idf_array = new JSONArray();
			
			// 儲存idf結果
			LinkedHashMap<Integer, Double> review_content_idf_hashmap = new LinkedHashMap<Integer, Double>();
			
			
			// 計算全部review數量
			int review_all_count = 0;
			

			// 取出Iterator中的遊戲資料
			while (list_250_it.hasNext()) {
           
				
				JSONObject collection = (JSONObject) list_250_it.next();
				
			
			    
				
				
				Steam_review_tfidf go_tfidf =new Steam_review_tfidf();
				
				review_all_count = review_all_count + go_tfidf.tf_idf("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\Steam_review_dictionary_normal.json", "all_normal_word", "C:\\Users\\John-Wall\\Desktop\\Steam_game_review_clean\\" + collection.get("appid").toString() + ".json", "steam_game_review_clean", collection.get("appid").toString(), "C:\\Users\\John-Wall\\Desktop\\Steam_review_tfidf\\", "steam_review_tfidf", review_content_idf_hashmap);

			

			}
			
			// debug
			System.out.println(review_all_count);
	       
			for (int key : review_content_idf_hashmap.keySet()) {
				
				
				
				review_content_idf_hashmap.put(key, Math.log(review_all_count / review_content_idf_hashmap.get(key)));
				
				
				
				
			}
			
			review_content_idf_array.add(review_content_idf_hashmap);
			
			
			 // 建立idf分數參考字典
			 FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\steam_game_review_idf.json");
			 Writer json_writer = new OutputStreamWriter(fos, "UTF8");

			 // 寫入JSON物件
			 json_writer.write("{" + "\"steam_review_idf\" :" + review_content_idf_array.toJSONString() + "}");
			 
			 // 關閉寫入
			 json_writer.flush();
			 json_writer.close();
			


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
