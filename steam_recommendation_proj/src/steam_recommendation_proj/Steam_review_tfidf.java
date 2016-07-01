package steam_recommendation_proj;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Steam_review_tfidf {

public void tf_idf(String dictionary_read_path, String dictionary_object, String appid_path, String steam_review_object, String appid) {
	
	
	try {
	// 讀取遊戲評論json檔
	FileReader steam_review_read_json_reader = new FileReader(appid_path);
	JSONParser steam_review_read_parser = new JSONParser();
	JSONObject steam_review_read_object = (JSONObject) steam_review_read_parser.parse(steam_review_read_json_reader);

	JSONArray steam_review_array = (JSONArray) steam_review_read_object.get(steam_review_object);

	Iterator steam_review_it = steam_review_array.iterator();
	
	JSONArray output_array= new JSONArray();
	
	int review_count = 1;
	
	// 取出Iterator中的評論資料
	while (steam_review_it.hasNext()) {
       
		//debug
		System.out.println("遊戲appid為:" + appid + "，第"+review_count+"筆評論");
		
		
		JSONObject collection = (JSONObject) steam_review_it.next();
		
		// 從集合中取出評論之斷詞arraylist
		ArrayList<String> review_content_arraylist = (ArrayList<String>) collection.get("review_content");
		
	    
		
		
	    // 執行計算字詞出現次數功能
		LinkedHashMap<Integer, Double> review_content_count_hashmap = Steam_review_tfidf.text_count(dictionary_read_path, dictionary_object, review_content_arraylist);
		
		// 執行計算tf值功能
		LinkedHashMap<Integer, Double> review_content_tf_hashmap =  Steam_review_tfidf.tf(review_content_count_hashmap, review_content_arraylist);
	    


		
		
		// 每一筆評論儲存計算後的hashmap到arraylist中
		
		ArrayList<LinkedHashMap<Integer, Double>> store_calculate_arraylist = new ArrayList<LinkedHashMap<Integer, Double>>();
		
		store_calculate_arraylist.add(review_content_count_hashmap);
		store_calculate_arraylist.add(review_content_tf_hashmap);
       
		
		// 建立刷新Json物件
		JSONObject tfidf_obj = new JSONObject();
		
		
		tfidf_obj.put("review_tfidf", store_calculate_arraylist);
		
		
		output_array.add(tfidf_obj);
		review_count++;


	}
	

	 // 輸出所有計算結果之json檔案
	 FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_review_tfidf\\"+ appid +".json");
	 Writer json_writer = new OutputStreamWriter(fos, "UTF8");
	 
	 // 寫入JSON物件
	 json_writer.write("{" + "\"steam_review_tfidf\" :" + output_array.toJSONString() + "}");
	 
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
	

  // 計算字詞出現次數	
  public static LinkedHashMap<Integer, Double> text_count(String dictionary_read_path, String dictionary_object, ArrayList<String> review_content_arraylist) {
	
	// 儲存字詞之count結果hashmap
	LinkedHashMap<Integer, Double> review_content_count_hashmap = new LinkedHashMap<Integer, Double>();

	
	try {
	
	
	// 讀取normal字典檔
	FileReader dictionary_read_json_reader = new FileReader(dictionary_read_path);
	JSONParser dictionary_read_parser = new JSONParser();
	JSONObject dictionary_read_object = (JSONObject) dictionary_read_parser.parse(dictionary_read_json_reader);

	JSONArray dictionary_normal_array = (JSONArray) dictionary_read_object.get(dictionary_object);

	Iterator dictionary_normal_it = dictionary_normal_array.iterator();
	
	

	

	
	// 取得字典比對之index
	int dictionary_word_index= 0;
	

	// 讀取normal字典檔之json檔
	while (dictionary_normal_it.hasNext()) {

		JSONObject dictionary_normal_collection = (JSONObject) dictionary_normal_it.next();
		
		// debug
		//System.out.println(dictionary_word_index);
		
		// 取出評論中斷詞的每個字詞
		for (int i = 0; i < review_content_arraylist.size(); i++) {
			
			    // 與normal字典進行字詞比對count
				if (review_content_arraylist.get(i).equals(dictionary_normal_collection.get("word").toString())) {
			        
					
					// 若比對到的字詞尚未初始化就進行初始化
					if (review_content_count_hashmap.get(dictionary_word_index) == null) {
						
						review_content_count_hashmap.put(dictionary_word_index, 0.0);
						
						// 若比對到count就加入匹配字詞之index
						review_content_count_hashmap.put(dictionary_word_index, review_content_count_hashmap.get(dictionary_word_index) + 1.0);
						
					}else{
						
					    // 若比對到count就加入匹配字詞之index
						review_content_count_hashmap.put(dictionary_word_index, review_content_count_hashmap.get(dictionary_word_index) + 1.0);
						
						
					}
			
	
					
		         }
				
				
				
	    // 字典比對之index累加		
		dictionary_word_index ++;
				
				
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
	
	return review_content_count_hashmap;

}

public static LinkedHashMap<Integer, Double> tf(LinkedHashMap<Integer, Double> review_content_count_hashmap, ArrayList<String> review_content_arraylist) {
	
	
	// 儲存tf值運算後的hashmap
	LinkedHashMap<Integer, Double> review_content_tf_hashmap = new LinkedHashMap<Integer, Double>();
	
	
	// 將key值與value存入set中
	Set review_content_count_set = (Set) review_content_count_hashmap.entrySet();
	
	
	// 進行迭代
	Iterator review_content_count_iterator = review_content_count_set.iterator();
	
	
	// 計算評論tf值
    while (review_content_count_iterator.hasNext()) {
		
    	 Map.Entry review_content_count_entry = (Map.Entry) review_content_count_iterator.next();
    	
    	
    	 review_content_tf_hashmap.put((int) review_content_count_entry.getKey(), (double)review_content_count_entry.getValue()/(double)review_content_arraylist.size());
    	

	}
    
	
	
	
	
	
	
	
	return review_content_tf_hashmap;
	
}

	
	
	public static void main(String[] args) {

	}

}
