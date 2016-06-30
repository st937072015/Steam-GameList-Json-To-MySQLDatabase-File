package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Steam_review_tfidf {

public void tf_idf(ArrayList<String>review_content) {
	
}	
	

// 計算字詞出現次數	
public void text_count(String dictionary_read_path, String read_object, ArrayList<String> review_content_arraylist) {
	
	try {
	
	
	// 讀取normal字典檔
	FileReader dictionary_read_json_reader = new FileReader(dictionary_read_path);
	JSONParser dictionary_read_parser = new JSONParser();
	JSONObject dictionary_read_object = (JSONObject) dictionary_read_parser.parse(dictionary_read_json_reader);

	JSONArray dictionary_normal_array = (JSONArray) dictionary_read_object.get(read_object);

	Iterator dictionary_normal_it = dictionary_normal_array.iterator();
	
	
	// 儲存字詞之count結果arraylist
	ArrayList<Integer> review_content_count_arraylist =new ArrayList<Integer>();
	
	for (int i = 0; i < dictionary_normal_array.size(); i++) {
		
		// debug
		System.out.println("字典字詞總size為:"+dictionary_normal_array.size()+"個字");
		
		review_content_count_arraylist.add(0);
		
		
		
	}
	
	// 取得字典比對之index
	int dictionary_word_index= 0;
	

	// 讀取normal字典檔之json檔
	while (dictionary_normal_it.hasNext()) {

		JSONObject dictionary_normal_collection = (JSONObject) dictionary_normal_it.next();
		
		
		// 取出評論中斷詞的每個字詞
		for (int i = 0; i < review_content_arraylist.size(); i++) {
			
			    // 與normal字典進行字詞比對count
				if (review_content_arraylist.equals(dictionary_normal_collection.get("word").toString())) {
			
					// 若比對到count就加1
					review_content_count_arraylist.set(dictionary_word_index, review_content_count_arraylist.get(dictionary_word_index)+1);
					
					
					
					
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

}



	
	
	public static void main(String[] args) {

	}

}
