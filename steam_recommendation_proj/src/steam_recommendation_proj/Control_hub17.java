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

public class Control_hub17 {

	public static void main(String[] args) {

		try {

			 // 讀取遊戲清單
			FileReader list_250_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_sample_250.json");
			JSONParser list_250_parser = new JSONParser();
			JSONObject list_250_read_parser = (JSONObject) list_250_parser.parse(list_250_json_reader);

			JSONArray list_250_array = (JSONArray) list_250_read_parser.get("app_sample_250");

			Iterator list_250_it = list_250_array.iterator();
			
			
			
			
			
			// 讀取tag字典檔
        	FileReader tag_dictionary_read_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\Steam_attribute_tag_dictionary.json");
        	JSONParser tag_dictionary_read_parser = new JSONParser();
        	JSONObject tag_dictionary_read_object = (JSONObject) tag_dictionary_read_parser.parse(tag_dictionary_read_json_reader);

        	
        	ArrayList<String> tag_dictionary_array = (JSONArray) tag_dictionary_read_object.get("attribute_tag_dictionary");
        	

        	
        	
        	
        	

      
        	

			Steam_attribute_tag_merge go_tag_merge = new Steam_attribute_tag_merge();
			
			

			// 取出Iterator中的遊戲資料
			while (list_250_it.hasNext()) {
           
				
				JSONObject collection = (JSONObject) list_250_it.next();
				
				
				go_tag_merge.attribute_tag_hashmap(collection.get("appid").toString(), tag_dictionary_array, "C:\\Users\\John-Wall\\Desktop\\Steam_game_tag_hashmap\\", "steam_game_tag_hashmap");
				
				
				
				
				

			

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
