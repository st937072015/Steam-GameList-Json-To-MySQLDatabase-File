package steam_recommendation_proj;

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


public class Control_hub22 {

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
			
			
			Steam_review_personality_calculate2 go_calculate2 = new Steam_review_personality_calculate2();
			

			// 取出Iterator中的遊戲資料
			while (list_250_it.hasNext()) {
           
				
				JSONObject collection = (JSONObject) list_250_it.next();
				
				go_calculate2.personality_tfidf_calculate2(steam_user_list_hashmap, "C:\\Users\\John-Wall\\Desktop\\Steam_game_review\\"+collection.get("appid").toString()+".json", "steam_review", "C:\\Users\\John-Wall\\Desktop\\Steam_user_review_personality_tfidf_score_bool\\", "steam_review_personality_tfidf_bool", collection.get("appid").toString(), "C:\\Users\\John-Wall\\Desktop\\Steam_user_review_personality_tfidf_score_bool2\\", "steam_user_review_personality_tfidf_final");
				
				


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
