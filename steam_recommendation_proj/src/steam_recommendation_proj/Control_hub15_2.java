package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.server.handler.MaximizeWindow;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Control_hub15_2 {

	public static void main(String[] args) {

		try {

			 // 讀取遊戲清單
			FileReader list_250_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_sample_250.json");
			JSONParser list_250_parser = new JSONParser();
			JSONObject list_250_read_parser = (JSONObject) list_250_parser.parse(list_250_json_reader);

			JSONArray list_250_array = (JSONArray) list_250_read_parser.get("app_sample_250");

			Iterator list_250_it = list_250_array.iterator();
			
			
			
			
			
			// 讀取advance字典檔
        	FileReader advance_dictionary_read_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\Steam_review_dictionary_advance_bool.json");
        	JSONParser advance_dictionary_read_parser = new JSONParser();
        	JSONObject advance_dictionary_read_object = (JSONObject) advance_dictionary_read_parser.parse(advance_dictionary_read_json_reader);

        	
        	JSONArray tfidf_dictionary_array = (JSONArray) advance_dictionary_read_object.get("all_advance_word_bool");
        	
        	HashMap<String, ArrayList<ArrayList<Double>>> steam_review_advance_dictionary_hashmap = (HashMap<String, ArrayList<ArrayList<Double>>>)tfidf_dictionary_array.get(0);
        	
        	
        	// 儲存計算結果
        	LinkedHashMap<String, LinkedHashMap<String, Double>> output_map = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
        	
         
        	
        	/*
        	
        	for(Iterator iterator = steam_review_idf_dictionary_object.keySet().iterator(); iterator.hasNext();) {
        		
        		String key = (String) iterator.next();
        	   
        	    System.out.println("key:"+key+"value:"+(double)steam_review_idf_dictionary_object.get(key));
        	    
        	    
        	    
        	}
			*/
        	
      
        	

			Steam_review_personality_calculate go_personality_tfidf = new Steam_review_personality_calculate();
			
			

			// 取出Iterator中的遊戲資料
			while (list_250_it.hasNext()) {
           
				
				JSONObject collection = (JSONObject) list_250_it.next();
				
				go_personality_tfidf.personality_tfidf_calculate(steam_review_advance_dictionary_hashmap, "C:\\Users\\John-Wall\\Desktop\\Steam_review_tfidf_join\\" + collection.get("appid").toString() + ".json", "steam_game_review_tfidf_join", collection.get("appid").toString(),output_map);
			    
				
				
				
				
				

			

			}

			
			
			
			// json檔案計算結果輸出
			ObjectMapper om  = new ObjectMapper();
									
			om.writeValue(new File("D:\\steam_personality\\Chang_第一種人格特質評論對映方法_未與屬性標籤合併版本.json"), output_map);



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
