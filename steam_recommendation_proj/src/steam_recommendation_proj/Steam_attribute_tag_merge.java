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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Steam_attribute_tag_merge {
	
	
public void attribute_tag_hashmap(String appid, ArrayList<String> tag_dictionary, String output_path, String output_object) {
	

	try {
             
			// 進行已過濾的遊戲id之屬性標籤json檔案讀取
			FileReader steamreader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_game_tag\\" + appid +"_attribute_tag.json" );

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(steamreader);

			// 讀取有250筆評論的steam遊戲之id物件arraylist
			JSONArray app = (JSONArray) jsonObject.get("steam_attribute_tag");

			// 將JSONArray物件創建成Iterator迭代器
			Iterator it = app.iterator();
			
			// 儲存比對標籤後之結果
			LinkedHashMap<String, Double> store_tag_equal_hashmap = new LinkedHashMap<String, Double>();
			
			// 標籤初始化
			for (int i = 0; i < tag_dictionary.size(); i++) {
				
				store_tag_equal_hashmap.put(tag_dictionary.get(i), 0.0);
				
				
			}
			
			JSONArray output_array = new JSONArray();
			
			// 取出tag屬性標籤
			while (it.hasNext()) {

				
			 JSONObject collection = (JSONObject) it.next();
			

			 
			 
			 // 比對標籤
			 for (int i = 0; i < tag_dictionary.size(); i++) {
				 
				 if (collection.get("tag").toString().equals(tag_dictionary.get(i))) {
					 
					 
					 
				
						

					store_tag_equal_hashmap.put(tag_dictionary.get(i), 1.0);
						 
					
					 
					 
					 
					System.out.println(tag_dictionary.get(i));
					 

				} 
				 
	 
			}
			 
				
			 
				
				
				
			
	}
		output_array.add(store_tag_equal_hashmap);	
			 // 輸出所有計算結果之json檔案
			 FileOutputStream fos = new FileOutputStream(output_path + appid +".json");
			 Writer json_writer = new OutputStreamWriter(fos, "UTF8");
			 
			 // 寫入JSON物件
			 json_writer.write("{" + "\"" + output_object + "\" :" + output_array.toJSONString() + "}");
			 
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
public void attribute_tag_merge(String appid, LinkedHashMap<String, LinkedHashMap<String, Double>> review_none_merge , LinkedHashMap<String, LinkedHashMap<String, Double>> store_merge_hashmap) {
	
	
           // 合併過後的hashmap
		   LinkedHashMap<String, Double> merge_hashmap = new LinkedHashMap<String, Double>();
	
	
	
	try {
		   
		
		
		


			// 人格特質分數之linkedhashmap
			LinkedHashMap<String, Double> personality_score = (LinkedHashMap<String, Double>)review_none_merge.get(appid);
			
			System.out.println(personality_score.toString());
			
			
			
			
			// 進行遊戲之tag清單json檔案讀取
			FileReader steamreader2 = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_game_tag_hashmap\\" + appid +".json" );
            
			JSONParser jsonParser = new JSONParser();
			
			JSONObject jsonObject2 = (JSONObject) jsonParser.parse(steamreader2);

			// 讀取遊戲標籤tag清單
			JSONArray app2 = (JSONArray) jsonObject2.get("steam_game_tag_hashmap");
		
			// 屬性標籤分數之hashmap
			HashMap<String, Double> tag_score = (HashMap<String, Double>)app2.get(0);
			
			
			
			
			
			
			
		// 將人格特質分數塞進儲存合併之linkedhashmap中		
		merge_hashmap.put("Extraversion", personality_score.get("0"));	

		merge_hashmap.put("Emotional stability", personality_score.get("1"));	

		merge_hashmap.put("Agreeableness", personality_score.get("2"));

		merge_hashmap.put("Conscientiousness", personality_score.get("3"));

		merge_hashmap.put("Openness to experience", personality_score.get("4"));

			
			
			// 將屬性標籤塞入塞進儲存合併之hashmap中
			for (String key : tag_score.keySet()) {
				
				
				merge_hashmap.put(key, tag_score.get(key));
				
					
				
			}
			
			
			store_merge_hashmap.put(appid, merge_hashmap);
			

			
		
			
			
			
			

			
			
			
			
		
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
