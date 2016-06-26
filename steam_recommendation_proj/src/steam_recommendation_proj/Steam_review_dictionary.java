package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Steam_review_dictionary {

	
	

	
public void produce_steam_review_dictionary_normal(String appid,Set<String> steam_review_dictionary_normal_set) {
	
	try {
	
	
	// 讀取某appid遊戲之json檔
	FileReader json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_game_review_clean\\"+appid+".json");
	JSONParser parser = new JSONParser();
	JSONObject read_parser = (JSONObject) parser.parse(json_reader);

	JSONArray gamelist_250_array = (JSONArray) read_parser.get("steam_game_review_clean");

	Iterator it = gamelist_250_array.iterator();
	
	
	
	

	// 讀取評論數至少有250筆的遊戲detail之json檔
	while (it.hasNext()) {

		JSONObject collection = (JSONObject) it.next();
		
		ArrayList<String>review_content = (ArrayList<String>)collection.get("review_content");
		
		
		// 將讀取到的字串arraylist塞入set
		for (int i = 0; i < review_content.size(); i++) {
			
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
public void produce_steam_review_dictionary_advance(String dictionary_text, JSONArray output_array) {
	
	
	
	try {
		
		
	// 讀取LIWC字典字詞分類類別之json檔
	FileReader json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\LIWC_2001_json\\LIWC_2001_classification_06_27.json");
	JSONParser parser = new JSONParser();
	JSONObject read_parser = (JSONObject) parser.parse(json_reader);

	JSONArray LIWC_array = (JSONArray) read_parser.get("LIWC_2001_classification");

	Iterator LIWC_it = LIWC_array.iterator();
	
	
	


	// 讀取評論數至少有250筆的遊戲detail之json檔
	while (LIWC_it.hasNext()) {

		JSONObject LIWC_collection = (JSONObject) LIWC_it.next();
	
		File check_contain_file=new File("C:\\Users\\John-Wall\\Desktop\\LIWC_2001_json\\contain_equal\\" + LIWC_collection.get("classification").toString() + "_contain.json");
		File check_equal_file=new File("C:\\Users\\John-Wall\\Desktop\\LIWC_2001_json\\contain_equal\\" + LIWC_collection.get("classification").toString() + "_equal.json");
		
		
		
		// contain詞庫檔
		if (check_contain_file.exists()) {
			// 讀取contain詞庫檔
			FileReader contain_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\LIWC_2001_json\\contain_equal\\" + LIWC_collection.get("classification").toString() + "_contain.json");
			JSONParser contain_parser = new JSONParser();
			JSONObject contain_read_parser = (JSONObject) contain_parser.parse(contain_json_reader);

			JSONArray contain_array = (JSONArray) contain_read_parser.get("contain");

			Iterator contain_it = contain_array.iterator();
			
				while (contain_it.hasNext()) {
				
					JSONObject contain_collection = (JSONObject) contain_it.next();
					if (dictionary_text.contains(contain_collection.get("word").toString())){
												
						// 建立刷新Json物件
						JSONObject output_obj = new JSONObject();
						System.out.println(LIWC_collection.get("id"));
						output_obj.put("id", LIWC_collection.get("id"));
						output_obj.put("word", dictionary_text);
						output_obj.put("classification", LIWC_collection.get("classification"));
						output_obj.put("personality", LIWC_collection.get("personality"));

						output_array.add(output_obj);
						
					}
				
				
				
			}
			
			
			
			
			
		
		// equal詞庫檔	
		}else if (check_equal_file.exists()) {
			// 讀取equal詞庫檔
			FileReader equal_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\LIWC_2001_json\\contain_equal\\" + LIWC_collection.get("classification").toString() + "_equal.json");
			JSONParser equal_parser = new JSONParser();
			JSONObject equal_read_parser = (JSONObject) equal_parser.parse(equal_json_reader);

			JSONArray equal_array = (JSONArray) equal_read_parser.get("equal");

			Iterator equal_it = equal_array.iterator();
			
				while (equal_it.hasNext()) {
				
					JSONObject equal_collection = (JSONObject) equal_it.next();
					if (dictionary_text.equals(equal_collection.get("word").toString())){
												
						// 建立刷新Json物件
						JSONObject output_obj = new JSONObject();
						
						output_obj.put("id", LIWC_collection.get("id"));
						output_obj.put("word", dictionary_text);
						output_obj.put("classification", LIWC_collection.get("classification"));
						output_obj.put("personality", LIWC_collection.get("personality"));

						output_array.add(output_obj);
						
					}
				
				
				
			}
			
		// 若字詞什麼都不是		
		}else {
			
			
		    // 建立完全無關之人格特質分數權重之arraylist
		    ArrayList<Double>personality_none_arraylist=new ArrayList<Double>();
		    personality_none_arraylist.add(0.00);
		    personality_none_arraylist.add(0.00);
		    personality_none_arraylist.add(0.00);
		    personality_none_arraylist.add(0.00);
		    personality_none_arraylist.add(0.00);
			
			// 建立刷新Json物件
			JSONObject output_obj = new JSONObject();
			
			output_obj.put("id", "none");
			output_obj.put("word", dictionary_text);
			output_obj.put("classification", "none");
			output_obj.put("personality", personality_none_arraylist);

			output_array.add(output_obj);
			
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
