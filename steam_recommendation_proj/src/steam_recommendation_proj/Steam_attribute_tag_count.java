package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Steam_attribute_tag_count {

	public static void main(String[] args) {



		try {
			
			// 儲存2050款遊戲之屬性tag數量
			LinkedHashMap<String, Integer> tag_count = new LinkedHashMap<String, Integer>();
			
			
			// 讀取遊戲appid清單json檔
			FileReader gamelist_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_sample_250.json");
			JSONParser gamelist_parser = new JSONParser();
			JSONObject gamelist_read_parser = (JSONObject) gamelist_parser.parse(gamelist_json_reader);
			


			JSONArray gamelist_array = (JSONArray) gamelist_read_parser.get("app_sample_250");
			Iterator gamelist_it = gamelist_array.iterator();
			
			
	
			
			while (gamelist_it.hasNext()) {
				
			
				
				JSONObject gamelist_collection = (JSONObject) gamelist_it.next();
				
				

			// 讀取遊戲屬性tag之json檔
			FileReader tag_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_game_tag\\" + gamelist_collection.get("appid").toString() + "_attribute_tag.json");
			JSONParser tag_parser = new JSONParser();
			JSONObject tag_read_parser = (JSONObject) tag_parser.parse(tag_json_reader);
			


			JSONArray tag_array = (JSONArray) tag_read_parser.get("steam_attribute_tag");
			Iterator tag_it = tag_array .iterator();

			while (tag_it.hasNext()) {

				JSONObject tag_collection = (JSONObject) tag_it.next();
                 
				System.out.println(tag_collection.get("tag").toString());
				
				// 判斷屬性標籤名字count是否已經初始化
				if (tag_count.get(tag_collection.get("tag").toString()) == null) {
					
					
					tag_count.put(tag_collection.get("tag").toString(), 0);
					
					tag_count.put(tag_collection.get("tag").toString(), tag_count.get(tag_collection.get("tag").toString()) + 1);
					
				}else{
					
						
					tag_count.put(tag_collection.get("tag").toString(), tag_count.get(tag_collection.get("tag").toString()) + 1);
					
				}
				
				
				
			
	
				
				
			}
			
			
			}
			
			
			
			
			
	        // 匯出計算過後之linkedhashmap之json檔
			
			ObjectMapper om = new ObjectMapper();
			
			om.writeValue(new File("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\Steam_attribute_tag_count.json"), tag_count);
			

			

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
