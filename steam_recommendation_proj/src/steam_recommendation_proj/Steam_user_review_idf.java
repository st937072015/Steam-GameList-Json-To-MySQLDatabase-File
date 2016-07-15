package steam_recommendation_proj;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonObject;

public class Steam_user_review_idf {

public int idf(String read_appid_path, String steam_review_object, String appid , LinkedHashMap<String, Double> review_content_idf_hashmap) {
	
	int review_count = 0;
	
	try {
	// 讀取遊戲評論json檔
	FileReader steam_review_read_json_reader = new FileReader(read_appid_path);
	JSONParser steam_review_read_parser = new JSONParser();
	JSONObject steam_review_read_object = (JSONObject) steam_review_read_parser.parse(steam_review_read_json_reader);

	JSONArray steam_review_array = (JSONArray) steam_review_read_object.get(steam_review_object);
    


	Iterator steam_review_it = steam_review_array.iterator();
	
	
	JSONArray output_array= new JSONArray();
	
	
	
	// 取出Iterator中的評論資料
	while (steam_review_it.hasNext()) {
       
		//debug
		System.out.println("遊戲appid為:" + appid + "，第"+ (review_count+1) +"筆評論");
		
		review_count++;
		
		JSONObject collection = (JSONObject) steam_review_it.next();
		
		// 從集合中取出評論之斷詞arraylist
		ArrayList<HashMap<String,Double>> review_content_arraylist = (ArrayList<HashMap<String,Double>>) collection.get("review_tfidf");
		
	    
		
		HashMap<String,Double> review_content_count_hashmap = review_content_arraylist.get(0);
	    
		

			
	    // 計算idf	
		for (String key : review_content_count_hashmap.keySet()) {
			
			if (review_content_idf_hashmap.get(key) == null) {
				
				
				
				review_content_idf_hashmap.put(key, 0.0);
				

				
				review_content_idf_hashmap.put(key, review_content_idf_hashmap.get(key) + 1.0);
				
				
			}else {
				

				review_content_idf_hashmap.put(key, review_content_idf_hashmap.get(key) + 1.0);
				
				
			}
			
	
			
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
	
   return review_count;
	
	
}	
	

  

	
	
	public static void main(String[] args) {

	}

}
