package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Control_hub26_3 {

	public static void main(String[] args) {

		try {
			
			 // 讀取遊戲清單
			FileReader list_250_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_sample_250.json");
			JSONParser list_250_parser = new JSONParser();
			JSONObject list_250_read_parser = (JSONObject) list_250_parser.parse(list_250_json_reader);

			JSONArray list_250_array = (JSONArray) list_250_read_parser.get("app_sample_250");

			Iterator list_250_it = list_250_array.iterator();
			

			
			// 讀取使用者清單
			FileReader user_list_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_user_list\\Steam_user_list_hashmap.json");
			JSONParser user_list_parser = new JSONParser();
			JSONObject user_list_read_parser = (JSONObject) user_list_parser.parse(user_list_json_reader);

			JSONArray user_list_array = (JSONArray) user_list_read_parser.get("steam_user_list_hashmap");

			HashMap<String, Double> steam_user_list_hashmap = (HashMap<String, Double>)user_list_array.get(0);
			
			
			// 讀取第一次做完平均的評論作者之5大人格特質分數
			ObjectMapper om  = new ObjectMapper();
			
			LinkedHashMap<String, LinkedHashMap<String, Double>> user_personality_tfidf_hashmap = om.readValue(new File("D:\\steam_personality\\Mairesse\\Mairesse_第二種人格特質評論對映方法_僅平均評論作者第一次版本.json"), LinkedHashMap.class);
			
			
			// 儲存第2次作平均的評論作者之5大人格特質
			LinkedHashMap<String, LinkedHashMap<String, Double>> output_hashmap = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
			
			
			Steam_review_personality_calculate2 go_calculate2 = new Steam_review_personality_calculate2();
			

			// 取出Iterator中的遊戲資料
			while (list_250_it.hasNext()) {
           
				
				JSONObject collection = (JSONObject) list_250_it.next();
				
				go_calculate2.personality_tfidf_calculate2(steam_user_list_hashmap, "C:\\Users\\John-Wall\\Desktop\\Steam_game_review\\"+collection.get("appid").toString()+".json", "steam_review", user_personality_tfidf_hashmap, collection.get("appid").toString(), output_hashmap);
				
				


			}
			
           
			
			
			 // 輸出所有計算結果之json檔案
							
		     om.writeValue(new File("D:\\steam_personality\\Mairesse\\Mairesse_第二種人格特質評論對映方法_平均評論作者第二次未與屬性標籤合併版本.json"), output_hashmap);
			
			 
			



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
