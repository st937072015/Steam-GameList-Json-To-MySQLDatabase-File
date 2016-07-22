package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class test {

	public static void main(String[] args) {

		try {
		
					
			
			// 讀取第一次做完平均的評論作者之5大人格特質分數
			ObjectMapper om  = new ObjectMapper();
			
			LinkedHashMap<String, LinkedHashMap<String, Double>> user_personality_tfidf_hashmap = om.readValue(new File("D:\\steam_personality\\Rock\\Rock_第一種人格特質評論對映方法_未與屬性標籤合併版本.json"), LinkedHashMap.class);
			
			JSONObject a;
		
			for (String key : user_personality_tfidf_hashmap.keySet()) {
				
				
				
				System.out.println(key + om.writeValueAsString(user_personality_tfidf_hashmap.get(key)));
				
				
				
			}

			
           
			
		
			



		} catch (

		FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (NullPointerException e) {
			System.out.println(e.toString());
		}
		
		
	}

}
