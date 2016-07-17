package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.server.handler.MaximizeWindow;

public class Control_hub16_2 {

	public static void main(String[] args) {

		try {

					
			
			// 讀取advance字典檔
        	FileReader advance_dictionary_read_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\Steam_user_review_dictionary_advance_bool.json");
        	JSONParser advance_dictionary_read_parser = new JSONParser();
        	JSONObject advance_dictionary_read_object = (JSONObject) advance_dictionary_read_parser.parse(advance_dictionary_read_json_reader);

        	
        	JSONArray tfidf_dictionary_array = (JSONArray) advance_dictionary_read_object.get("all_advance_word_bool");
        	
        	HashMap<String, ArrayList<ArrayList<Double>>> steam_review_advance_dictionary_hashmap = (HashMap<String, ArrayList<ArrayList<Double>>>)tfidf_dictionary_array.get(0);
        	
        	
        	
        	
         
        	
        	/*
        	
        	for(Iterator iterator = steam_review_idf_dictionary_object.keySet().iterator(); iterator.hasNext();) {
        		
        		String key = (String) iterator.next();
        	   
        	    System.out.println("key:"+key+"value:"+(double)steam_review_idf_dictionary_object.get(key));
        	    
        	    
        	    
        	}
			*/
        	
      
        	

			Steam_review_personality_calculate go_personality_tfidf = new Steam_review_personality_calculate();
			
			


			for (int i = 1; i < 328973; i++) {
				
			
           
				
				go_personality_tfidf.personality_tfidf_calculate(steam_review_advance_dictionary_hashmap, "C:\\Users\\John-Wall\\Desktop\\Steam_user_review_tfidf_join\\" + String.valueOf(i) + ".json", "steam_user_review_tfidf_join", String.valueOf(i), "C:\\Users\\John-Wall\\Desktop\\Steam_user_review_personality_tfidf_score_bool\\", "steam_review_personality_tfidf_bool");
			    
				


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

}
