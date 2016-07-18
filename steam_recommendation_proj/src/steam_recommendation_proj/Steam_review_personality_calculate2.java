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

public void personality_tfidf_calculate2(HashMap<String, Double> steam_user_list_hashmap, String read_appid_path1, String steam_review_object1,String read_appid_path2, String steam_review_object2, String appid , String output_path, String output_object) {
	
	int review_count = 0;
	
	try {
	// 讀取遊戲評論json檔
	FileReader steam_review_read_json_reader = new FileReader(read_appid_path1);
	JSONParser steam_review_read_parser = new JSONParser();
	JSONObject steam_review_read_object = (JSONObject) steam_review_read_parser.parse(steam_review_read_json_reader);

	JSONArray steam_review_array = (JSONArray) steam_review_read_object.get(steam_review_object1);
    


	Iterator steam_review_it = steam_review_array.iterator();
	
	
	JSONArray output_array= new JSONArray();
	

	HashMap<String, Double> method2_personality_hashmap = new HashMap<String, Double>();
	
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
		
		
		FileReader steam_user_review_read_json_reader = new FileReader(read_appid_path2+steam_user_list_hashmap.get(collection.get("user_profile").toString())+".json");

		JSONParser steam_user_review_read_parser = new JSONParser();
		JSONObject steam_user_review_read_object = (JSONObject) steam_user_review_read_parser.parse(steam_user_review_read_json_reader);

		JSONArray steam_user_review_array = (JSONArray) steam_user_review_read_object.get(steam_review_object2);
		

		
		
        HashMap<String, Double> user_personality_score = (HashMap<String, Double>)steam_user_review_array.get(0);

        
        
        
        
			
        //System.out.println(user_personality_score);
        
        for (String key : user_personality_score.keySet()) {
        	
        	if (user_personality_score.get(key) == null) {
				
        		break;
			}else{
        	
        	
        	method2_personality_hashmap.put(key, method2_personality_hashmap.get(key)+user_personality_score.get(key));
        	
        	}
        	
        	
        	
			
		}
        
        
        
        
        
        
        
	
		review_count++;


	}
	
	
	// 進行相除平均
	for (String key : method2_personality_hashmap.keySet()) {
		
		
		method2_personality_hashmap.put(key, method2_personality_hashmap.get(key)/review_count);	
		
		
		
		
	}
	
	
	
	    // 輸出
		output_array.add(method2_personality_hashmap);
	
	

	 // 輸出所有計算結果之json檔案
	 FileOutputStream fos = new FileOutputStream(output_path + appid +".json");
	 Writer json_writer = new OutputStreamWriter(fos, "UTF8");
	 
	 // 寫入JSON物件
	 json_writer.write("{" + "\"" + output_object + "\" :" + output_array.toJSONString() + "}");
	 
	 // 關閉寫入
	 json_writer.flush();
	 json_writer.close();
	
	
	
	
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
