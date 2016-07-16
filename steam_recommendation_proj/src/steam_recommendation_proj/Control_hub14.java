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
import java.util.LinkedHashMap;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.server.handler.MaximizeWindow;

public class Control_hub14 {

	public static void main(String[] args) {

		try {


			
			
			
			
			
			// 讀取idf字典檔
        	FileReader tfidf_dictionary_read_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\steam_user_review_idf.json");
        	JSONParser tfidf_dictionary_read_parser = new JSONParser();
        	JSONObject tfidf_dictionary_read_object = (JSONObject) tfidf_dictionary_read_parser.parse(tfidf_dictionary_read_json_reader);

        	
        	JSONArray tfidf_dictionary_array = (JSONArray) tfidf_dictionary_read_object.get("steam_user_review_idf");
        	
        	JSONObject steam_review_idf_dictionary_object = (JSONObject)tfidf_dictionary_array.get(0);
        	
        	
        	
        	
         
        	
        	/*
        	
        	for(Iterator iterator = steam_review_idf_dictionary_object.keySet().iterator(); iterator.hasNext();) {
        		
        		String key = (String) iterator.next();
        	   
        	    System.out.println("key:"+key+"value:"+(double)steam_review_idf_dictionary_object.get(key));
        	    
        	    
        	    
        	}
			*/
        	
      
        	

			Steam_review_tfidf go_tfidf =new Steam_review_tfidf();
			
			

			for (int i = 1; i < 328973; i++) {
				
			
           
				
			
				
			
			    
				
				
				
				go_tfidf.tfidf_join(String.valueOf(i), "C:\\Users\\John-Wall\\Desktop\\Steam_user_review_tfidf\\" + String.valueOf(i) +".json", "steam_review_tfidf", steam_review_idf_dictionary_object,"C:\\Users\\John-Wall\\Desktop\\Steam_user_review_tfidf_join\\", "steam_user_review_tfidf_join" );
				

			

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
