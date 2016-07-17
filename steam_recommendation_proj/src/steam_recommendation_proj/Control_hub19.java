package steam_recommendation_proj;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Iterator;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Control_hub19 {

	public static void main(String[] args) {

		try {

			 // 讀取遊戲清單
			FileReader list_250_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_sample_250.json");
			JSONParser list_250_parser = new JSONParser();
			JSONObject list_250_read_parser = (JSONObject) list_250_parser.parse(list_250_json_reader);

			JSONArray list_250_array = (JSONArray) list_250_read_parser.get("app_sample_250");

			Iterator list_250_it = list_250_array.iterator();
			
	

			Steam_attribute_tag_merge go_tag_merge = new Steam_attribute_tag_merge();
			
			

			// 取出Iterator中的遊戲資料
			while (list_250_it.hasNext()) {
           
				
				JSONObject collection = (JSONObject) list_250_it.next();
				
				
				go_tag_merge.attribute_tag_merge(collection.get("appid").toString(), "C:\\Users\\John-Wall\\Desktop\\Steam_review_personality_tfidf_score_bool\\", "steam_review_personality_tfidf_bool","C:\\Users\\John-Wall\\Desktop\\Chang_method1\\");
				


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
