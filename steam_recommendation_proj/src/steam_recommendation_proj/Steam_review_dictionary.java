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

public class Steam_review_dictionary {

	
	

	
public void produce_steam_review_dictionary_normal(String read_path, String read_object, Set<String> steam_review_dictionary_normal_set) {
	
	try {
	
	
	// 讀取某appid遊戲或評論作者id之json檔
	FileReader json_reader = new FileReader(read_path);
	JSONParser parser = new JSONParser();
	JSONObject read_parser = (JSONObject) parser.parse(json_reader);

	JSONArray list_250_array = (JSONArray) read_parser.get(read_object);

	Iterator it = list_250_array.iterator();
	
	
	
	

	// 讀取detail之json檔
	while (it.hasNext()) {

		JSONObject collection = (JSONObject) it.next();
		
		ArrayList<String>review_content = (ArrayList<String>)collection.get("review_content");

		
		// 將讀取到的字串arraylist塞入set
		for (int i = 0; i < review_content.size(); i++) {
			
			System.out.println(review_content.get(i));
			steam_review_dictionary_normal_set.add(review_content.get(i));
				
			
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

// 判斷字典中字詞之id與5大人格特質之皮爾森相關系數比重
public void produce_steam_review_dictionary_advance(JSONArray LIWC_array,String dictionary_text,ArrayList id_arraylist,ArrayList<String> classification, ArrayList<ArrayList<Double>> personality_arraylist) {
	
	
	
	try {
		
		


	Iterator LIWC_it = LIWC_array.iterator();
	



	// 讀取評論數至少有250筆的遊戲detail之json檔
	while (LIWC_it.hasNext()) {

		JSONObject LIWC_collection = (JSONObject) LIWC_it.next();
	
		File check_contain_file=new File("C:\\Users\\John-Wall\\Desktop\\LIWC_2001_json\\contain_equal\\" + LIWC_collection.get("classification").toString() + "_contain.json");
		File check_equal_file=new File("C:\\Users\\John-Wall\\Desktop\\LIWC_2001_json\\contain_equal\\" + LIWC_collection.get("classification").toString() + "_equal.json");
		
		

	
	
		
		// 若只有contain詞庫檔存在
		if (check_contain_file.exists() && !check_equal_file.exists()) {
			// 讀取contain詞庫檔
			FileReader contain_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\LIWC_2001_json\\contain_equal\\" + LIWC_collection.get("classification").toString() + "_contain.json");
			JSONParser contain_parser = new JSONParser();
			JSONObject contain_read_parser = (JSONObject) contain_parser.parse(contain_json_reader);

			JSONArray contain_array = (JSONArray) contain_read_parser.get("contain");

			Iterator contain_it = contain_array.iterator();
			    
			    // contain字典運算部分
				while (contain_it.hasNext()) {
				
					JSONObject contain_collection = (JSONObject) contain_it.next();
					
		
					if (dictionary_text.contains(contain_collection.get("word").toString())){
												
					
						
						//id_arraylist.add(LIWC_collection.get("id"));
						//classification.add(LIWC_collection.get("classification").toString());
						personality_arraylist.add((ArrayList<Double>) LIWC_collection.get("personality"));
						
						
						break;
						
				
						
						
					}
		
				
				

	
					
					
				
			}
			
	
			
			
		
		// 若只有equal詞庫檔存在	
		}else if (check_equal_file.exists() && !check_contain_file.exists()) {
			// 讀取equal詞庫檔
			FileReader equal_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\LIWC_2001_json\\contain_equal\\" + LIWC_collection.get("classification").toString() + "_equal.json");
			JSONParser equal_parser = new JSONParser();
			JSONObject equal_read_parser = (JSONObject) equal_parser.parse(equal_json_reader);

			JSONArray equal_array = (JSONArray) equal_read_parser.get("equal");

			Iterator equal_it = equal_array.iterator();

			
			
			    // equal字典運算部分
				while (equal_it.hasNext()) {
				
					JSONObject equal_collection = (JSONObject) equal_it.next();
					if (dictionary_text.equals(equal_collection.get("word").toString())){
												
						//id_arraylist.add(LIWC_collection.get("id"));
						//classification.add(LIWC_collection.get("classification").toString());
						personality_arraylist.add((ArrayList<Double>) LIWC_collection.get("personality"));
						
						
						break;
			
				
						
						
					}

				
				
				
			}
			
		// 若兩種模式的詞庫檔都存在		
		}else if (check_contain_file.exists() && check_equal_file.exists()) {
			
			// 讀取contain詞庫檔
			FileReader contain_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\LIWC_2001_json\\contain_equal\\" + LIWC_collection.get("classification").toString() + "_contain.json");
			JSONParser contain_parser = new JSONParser();
			JSONObject contain_read_parser = (JSONObject) contain_parser.parse(contain_json_reader);

			JSONArray contain_array = (JSONArray) contain_read_parser.get("contain");

			Iterator contain_it = contain_array.iterator();
			
			
			// 讀取equal詞庫檔
			FileReader equal_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\LIWC_2001_json\\contain_equal\\" + LIWC_collection.get("classification").toString() + "_equal.json");
			JSONParser equal_parser = new JSONParser();
			JSONObject equal_read_parser = (JSONObject) equal_parser.parse(equal_json_reader);

			JSONArray equal_array = (JSONArray) equal_read_parser.get("equal");

			Iterator equal_it = equal_array.iterator();
			
			
			// 判斷兩種字典是否有匹配到符合的字詞種類
			boolean put_none = false;

			    // contain字典運算部分
				while (contain_it.hasNext()) {
				
					JSONObject contain_collection = (JSONObject) contain_it.next();
					
		
					if (dictionary_text.contains(contain_collection.get("word").toString())){
												

						
						
						
						//id_arraylist.add(LIWC_collection.get("id"));
						//classification.add(LIWC_collection.get("classification").toString());
						personality_arraylist.add((ArrayList<Double>) LIWC_collection.get("personality"));
						
						 
						break;

					}

                 }	
			
			
				
				
				    // equal字典運算部分
				    while (equal_it.hasNext()) {
					
					JSONObject equal_collection = (JSONObject) equal_it.next();
					if (dictionary_text.equals(equal_collection.get("word").toString())){
									
						// 有匹配到所以設定為true
						put_none = true;
						
						//id_arraylist.add(LIWC_collection.get("id"));
						//classification.add(LIWC_collection.get("classification").toString());
						personality_arraylist.add((ArrayList<Double>) LIWC_collection.get("personality"));
						
						
						break;
				
						
					}

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
	
	
	
	
	
	
	
}
	
	
	public static void main(String[] args) {

	}

}
