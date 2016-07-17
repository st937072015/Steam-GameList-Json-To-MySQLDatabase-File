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
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.server.handler.MaximizeWindow;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Control_hub7_2 {

	public static void main(String[] args) {

		try {

			 // 讀取遊戲清單
			FileReader normal_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\Steam_review_dictionary_normal.json");
			JSONParser normal_parser = new JSONParser();
			JSONObject normal_read_parser = (JSONObject) normal_parser.parse(normal_json_reader);

			JSONArray normal_array = (JSONArray) normal_read_parser.get("all_normal_word");

			// 取得一般字典檔之hashmap
			HashMap<String, String> normal_hashmap = (HashMap<String, String>) normal_array.get(0);
			
			
			
		
			
			// 讀取LIWC字典字詞分類類別之json檔
			FileReader json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\LIWC_2001_json\\LIWC_2001_classification_bool.json");
			JSONParser parser = new JSONParser();
			JSONObject read_parser = (JSONObject) parser.parse(json_reader);

			JSONArray LIWC_array = (JSONArray) read_parser.get("LIWC_2001_classification");
			
			
			// 儲存字詞對映之人格特質比重分數之hashmap
			HashMap<String, ArrayList<ArrayList<Double>>> word_hashmap = new HashMap<String, ArrayList<ArrayList<Double>>>();
		
				
			

			
			
           	// 取出字詞
			 for (String key: normal_hashmap.keySet()) {
				
                 
				
				// Debug
				System.out.println("key:" + key + "，" + "key值:" + normal_hashmap.get(key));
				
				ArrayList id_arraylist = new ArrayList();
				ArrayList<String> classification = new ArrayList<String>();
				ArrayList<ArrayList<Double>> personality_arraylist = new ArrayList<ArrayList<Double>>();
				
				
				Steam_review_dictionary normal =new Steam_review_dictionary();

				normal.produce_steam_review_dictionary_advance(LIWC_array, normal_hashmap.get(key), id_arraylist, classification, personality_arraylist);
				
	
				
				
				
               // 若人格特質arraylist為空 代表為完全無關字詞
               if (personality_arraylist.isEmpty()) {
            	   
	           // 加入完全無關之字詞類別id
	           // id_arraylist.add(9999);
	
	          // 加入完全無關之字詞類別
	          // classification.add("none");
	
	
	          // 建立完全無關之人格特質分數權重之arraylist
              ArrayList<Double>personality_none_arraylist=new ArrayList<Double>();
              personality_none_arraylist.add(0.0);
              personality_none_arraylist.add(0.0);
              personality_none_arraylist.add(0.0);
              personality_none_arraylist.add(0.0);
              personality_none_arraylist.add(0.0);
    
              personality_arraylist.add(personality_none_arraylist);
             }
			 	
               
               word_hashmap.put(key, personality_arraylist);


			}
			
			
			

		
			/*
			 // 建立第一種對映方法之字典
			 FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\Steam_review_dictionary_advance.json");
			 Writer json_writer = new OutputStreamWriter(fos, "UTF8");
			 
			 // 寫入JSON物件
			 json_writer.write("{" + "\"all_advance_word\" :" + output_array.toJSONString() + "}");
			 
			 // 關閉寫入
			 json_writer.flush();
			 json_writer.close();
			*/
			 
			// 改使用jackson json lib 轉換大型json檔案(**simplejson較不適合大型json檔案之轉換**)
			ObjectMapper om  = new ObjectMapper();
				
			om.writeValue(new File("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\Steam_review_dictionary_advance_bool.json"), word_hashmap);
			 
			 
			


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
