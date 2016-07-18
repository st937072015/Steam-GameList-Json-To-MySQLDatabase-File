package steam_recommendation_proj;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Control_hub20 {

	public static void main(String[] args) {

		try {
			
			// 轉換為hashmap
			LinkedHashMap<String, String> user_list_hashmap = new LinkedHashMap<String, String>();
			

			 // 讀取使用者清單
			FileReader user_list_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_user_list\\Steam_user_list.json");
			JSONParser user_list_parser = new JSONParser();
			JSONObject user_list_read_parser = (JSONObject) user_list_parser.parse(user_list_json_reader);

			JSONArray user_list_array = (JSONArray) user_list_read_parser.get("all_user_list");

			Iterator user_list_it = user_list_array.iterator();
			

			
			

			// 取出Iterator中的遊戲資料
			while (user_list_it.hasNext()) {
           
				
				JSONObject collection = (JSONObject) user_list_it.next();
				
				user_list_hashmap.put(collection.get("user_profile").toString(), collection.get("id").toString());
				
				


			}
			

			
			 // 輸出所有計算結果之json檔案
			 FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_user_list\\Steam_user_list_hashmap.json");
			 
			 ObjectMapper om  = new ObjectMapper();
							
		     om.writeValue(new OutputStreamWriter(fos, "UTF8"), user_list_hashmap);
			
			 
			



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
