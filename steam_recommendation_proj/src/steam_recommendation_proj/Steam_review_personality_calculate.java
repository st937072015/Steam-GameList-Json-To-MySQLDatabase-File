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

public class Steam_review_personality_calculate {

public void personality_tfidf_calculate(HashMap<String, ArrayList<ArrayList<Double>>> steam_review_advance_dictionary_hashmap, String read_appid_path, String steam_review_object, String appid , String output_path, String output_object) {
	
	int review_count = 0;
	
	try {
	// 讀取遊戲評論json檔
	FileReader steam_review_read_json_reader = new FileReader(read_appid_path);
	JSONParser steam_review_read_parser = new JSONParser();
	JSONObject steam_review_read_object = (JSONObject) steam_review_read_parser.parse(steam_review_read_json_reader);

	JSONArray steam_review_array = (JSONArray) steam_review_read_object.get(steam_review_object);
    


	Iterator steam_review_it = steam_review_array.iterator();
	

	JSONArray output_array= new JSONArray();
	
	if (steam_review_array.isEmpty()) {
		System.exit(-1);
	}

	HashMap<String, Double> review_personality_tfidf_hashmap = new HashMap<String, Double>();
	
	// 先給予初始值
	review_personality_tfidf_hashmap.put("0", 0.0);
	review_personality_tfidf_hashmap.put("1", 0.0);
	review_personality_tfidf_hashmap.put("2", 0.0);
	review_personality_tfidf_hashmap.put("3", 0.0);
	review_personality_tfidf_hashmap.put("4", 0.0);
	
	
	
	
	// 取出Iterator中的評論tfidf資料
	while (steam_review_it.hasNext()) {
		
		
		
		
       
		//debug
		System.out.println("遊戲appid為:" + appid + "，第"+ (review_count+1) +"筆評論");
		
		
		JSONObject collection = (JSONObject) steam_review_it.next();
		
		
		// 從集合中取出評論tfidf計算結果之hashmap
		HashMap<String, Double> review_tfidf_hashmap = (HashMap<String, Double>) collection.get("tfidf");
		
	    
		
		
	   // 評論tfidf計算結果之hashmap開始進行人格特質arraylist相乘並相加
       for (String  key : review_tfidf_hashmap.keySet()) {
		
    	   
    	   if (review_tfidf_hashmap.get(key) == null) {
			
    		   System.out.println("空");
    		   System.exit(-1);
    		   
    		   
		}
    	   
    	   
    	   
    	   ArrayList<ArrayList<Double>> personality_arraylist = steam_review_advance_dictionary_hashmap.get(key);
    	   
    	   // 取出字詞所代表的5大人格特質之arraylist
    	   for (int i = 0; i < personality_arraylist.size(); i++) {
			
    		   
    		   
    		   // 取出各自不同子arraylist之人格特質分數
    		   ArrayList<Double> personality_arraylist_element = personality_arraylist.get(i);
    		   
    		   
    		   // 進行分數相乘並相加
    		   for (int j = 0; j < personality_arraylist_element.size(); j++) {
			   
    		   // 計算相乘結果存進hashmap中
    		   review_personality_tfidf_hashmap.put(String.valueOf(j), review_personality_tfidf_hashmap.get(String.valueOf(j)) + (personality_arraylist_element.get(j) * review_tfidf_hashmap.get(key)));
    			   
    			   
    			   
    			   
			  }
    		   
	   
    		   
    		   
		   }
    	   
	   
    	   
	   }
			

	
		review_count++;


	}
	
	
	// 進行相除平均
	for (String key : review_personality_tfidf_hashmap.keySet()) {
		
		
		review_personality_tfidf_hashmap.put(key, review_personality_tfidf_hashmap.get(key)/review_count);	
		
		
		
		
	}
	
	
	
	    // 計算相乘結果並相除之hashmap塞入JSONArray中並輸出
		output_array.add(review_personality_tfidf_hashmap);
	
	
/*
	 // 輸出所有計算結果之json檔案
	 FileOutputStream fos = new FileOutputStream(output_path + appid +".json");
	 Writer json_writer = new OutputStreamWriter(fos, "UTF8");
	 
	 // 寫入JSON物件
	 json_writer.write("{" + "\"" + output_object + "\" :" + output_array.toJSONString() + "}");
	 
	 // 關閉寫入
	 json_writer.flush();
	 json_writer.close();
	
	*/
	
	
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
