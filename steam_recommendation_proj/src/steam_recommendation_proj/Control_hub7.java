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
import org.openqa.selenium.remote.server.handler.MaximizeWindow;

public class Control_hub7 {

	public static void main(String[] args) {

		try {

			 // 讀取遊戲清單
			FileReader normal_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\Steam_review_dictionary_normal.json");
			JSONParser normal_parser = new JSONParser();
			JSONObject normal_read_parser = (JSONObject) normal_parser.parse(normal_json_reader);

			JSONArray normal_array = (JSONArray) normal_read_parser.get("all_normal_word");

			Iterator normal_it = normal_array.iterator();
			
			
			
			JSONArray output_array= new JSONArray();
			

			// 取出Iterator中的遊戲資料
			while (normal_it.hasNext()) {
           
				
				JSONObject collection = (JSONObject) normal_it.next(); 
				
				
				//System.out.println(collection.get("word").toString());
				
				ArrayList id_arraylist = new ArrayList();
				ArrayList<String> classification = new ArrayList<String>();
				ArrayList<ArrayList<Double>> personality_arraylist = new ArrayList<ArrayList<Double>>();
				
				
				Steam_review_dictionary normal =new Steam_review_dictionary();

				normal.produce_steam_review_dictionary_advance(collection.get("word").toString(), id_arraylist, classification, personality_arraylist);
				
				// 建立刷新Json物件
				JSONObject word_obj = new JSONObject();
				
				
				
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
				
               
				word_obj.put("word", collection.get("word").toString());
				//word_obj.put("id", id_arraylist);
				//word_obj.put("classification", classification);
				
               
				System.out.println(collection.get("word").toString());
				//System.out.println(id_arraylist);
				//System.out.println(classification);
				System.out.println(personality_arraylist);
               
				word_obj.put("personality", personality_arraylist);
				output_array.add(word_obj);
				

			}
			
			
			

		
			
			 // 建立第一種對映方法之字典
			 FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\Steam_review_dictionary_advance.json");
			 Writer json_writer = new OutputStreamWriter(fos, "UTF8");
			 
			 // 寫入JSON物件
			 json_writer.write("{" + "\"all_advance_word\" :" + output_array.toJSONString() + "}");
			 
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

}
