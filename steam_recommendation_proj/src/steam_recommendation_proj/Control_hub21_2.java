package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Iterator;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Control_hub21_2 {

	public static void main(String[] args) {

		try {

			 // 讀取遊戲清單
			FileReader list_250_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_sample_250.json");
			JSONParser list_250_parser = new JSONParser();
			JSONObject list_250_read_parser = (JSONObject) list_250_parser.parse(list_250_json_reader);

			JSONArray list_250_array = (JSONArray) list_250_read_parser.get("app_sample_250");

			Iterator list_250_it = list_250_array.iterator();
			
			
			
			
			// 讀取要合併之人格特質相乘之hashmap
			
			ObjectMapper om = new ObjectMapper();
			
			
			
			LinkedHashMap<String, LinkedHashMap<String, Double>> review_none_merge = om.readValue(new File("D:\\steam_personality\\Chang\\Chang_第二種人格特質評論對映方法_平均評論作者第二次未與屬性標籤合併版本.json"), LinkedHashMap.class);
			
	        
			
			
			// 儲存所有遊戲之合併過後之linkedhashmap
			LinkedHashMap<String, LinkedHashMap<String, Double>> store_merge_hashmap = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
			
			
			

			Steam_attribute_tag_merge go_tag_merge = new Steam_attribute_tag_merge();
			
			

			// 取出Iterator中的遊戲資料
			while (list_250_it.hasNext()) {
           
				
				JSONObject collection = (JSONObject) list_250_it.next();
				
				
				go_tag_merge.attribute_tag_merge(collection.get("appid").toString(), review_none_merge, store_merge_hashmap);
				


			}

			
			 // 輸出所有合併結果之json檔案
						
		     om.writeValue(new File("D:\\steam_personality\\Chang\\Chang_第二種人格特質評論對映方法_平均評論作者第二次已與屬性標籤合併版本.json"), store_merge_hashmap);
			



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
