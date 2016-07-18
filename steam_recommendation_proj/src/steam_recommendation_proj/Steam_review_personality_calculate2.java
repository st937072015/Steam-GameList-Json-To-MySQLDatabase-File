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

public class Steam_review_personality_calculate2 {

public void personality_tfidf_calculate2(HashMap<String, Double> steam_user_list_hashmap, String read_appid_path1, String steam_review_object1, LinkedHashMap<String, LinkedHashMap<String, Double>> user_personality_tfidf_hashmap, String appid, LinkedHashMap<String, LinkedHashMap<String, Double>> output_hashmap) {
	
	int review_count = 0;
	
	try {
	// 讀取遊戲評論json檔
	FileReader steam_review_read_json_reader = new FileReader(read_appid_path1);
	JSONParser steam_review_read_parser = new JSONParser();
	JSONObject steam_review_read_object = (JSONObject) steam_review_read_parser.parse(steam_review_read_json_reader);

	JSONArray steam_review_array = (JSONArray) steam_review_read_object.get(steam_review_object1);
    


	Iterator steam_review_it = steam_review_array.iterator();
	
	

  // 每款遊戲所儲存評論作者所代表的5大人格特質分數之hashmap	
  LinkedHashMap<String, Double> method2_personality_hashmap = new LinkedHashMap<String, Double>();
	
	
	// 先給予初始值
	method2_personality_hashmap.put("0", 0.0);
	method2_personality_hashmap.put("1", 0.0);
	method2_personality_hashmap.put("2", 0.0);
	method2_personality_hashmap.put("3", 0.0);
	method2_personality_hashmap.put("4", 0.0);
	
	
	
	
	// 取出Iterator中的評論tfidf資料
	while (steam_review_it.hasNext()) {
       
		//debug
		System.out.println("遊戲appid為:" + appid + "，第"+ (review_count+1) +"筆評論");
		
		
		JSONObject collection = (JSONObject) steam_review_it.next();
		
		//System.out.println(read_appid_path2+steam_user_list_hashmap.get(collection.get("user_profile").toString())+".json");
		
		
		// 取得評論作者所屬5大人格特質分數
		LinkedHashMap<String, Double> review_user_personality = user_personality_tfidf_hashmap.get(steam_user_list_hashmap.get(collection.get("user_profile").toString()));

		
     
        
        
        
        
			
        //System.out.println(user_personality_score);
        
        for (String key : review_user_personality.keySet()) {
        	

        	
        	
        	method2_personality_hashmap.put(key, method2_personality_hashmap.get(key) + review_user_personality.get(key));
        	
        	
        	
        	
			
		}
        
        
        
        
        
        
        
	
		review_count++;


	}
	
	
	// 進行相除平均
	for (String key : method2_personality_hashmap.keySet()) {
		
		
		method2_personality_hashmap.put(key, method2_personality_hashmap.get(key)/review_count);	
		
		
		
		
	}
	
	
	output_hashmap.put(appid, method2_personality_hashmap);
	
	
	
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
	
	
	public static void main(String[] args) {

	}

}
