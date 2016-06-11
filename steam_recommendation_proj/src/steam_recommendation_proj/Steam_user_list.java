package steam_recommendation_proj;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Steam_user_list {

	public static void main(String[] args) {



		try {

			// 讀取抓取到的評論json檔
			FileReader json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_sample_250.json");
			JSONParser parser = new JSONParser();
			JSONObject read_parser = (JSONObject) parser.parse(json_reader);

			JSONArray review_array = (JSONArray) read_parser.get("app_sample_250");

			Iterator it = review_array.iterator();
			
			Set<String> user_set = new HashSet<String>();
			
			int count = 0;
			
            int review_count = 0;
            
			// 讀取評論數至少有250筆的遊戲清單json檔
			while (it.hasNext()) {
				count++;
				JSONObject collection = (JSONObject) it.next();

				//System.out.println("第"+count+"款遊戲，"+"遊戲id為:" + collection.get("appid").toString() + "，" + "遊戲名稱為:"+ collection.get("name").toString());
				
				
				FileReader review_read_reader=new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_game_review\\"+collection.get("appid").toString()+".json");
				
				JSONParser review_read_parser = new JSONParser();
				
				JSONObject review_object_parser = (JSONObject) review_read_parser.parse(review_read_reader);
				
				JSONArray review_raed_array = (JSONArray) review_object_parser.get("steam_review");
				
				Iterator it_read_review = review_raed_array.iterator();
				
	   			
    			JSONArray output_array = new JSONArray();
				
				
				
				// 讀取所有評論資料並彙整加入到set中過濾
                while (it_read_review.hasNext()) {
                	
                	review_count++;
                	
                	JSONObject read_review_collection =(JSONObject) it_read_review.next();
                	
                	user_set.add(read_review_collection.get("user_profile").toString());
                	
                	//System.out.println("第"+review_count+"篇評論，"+"評論作者Profile為:" + read_review_collection.get("user_profile").toString());
					
                	
                	
                	
                	
				} 
                
    
                
                
                
				
				
				
				
			}
			
			// 建立Json Array
			JSONArray output_array = new JSONArray();
			int id=0;
			
			for (String element:user_set) {
				id++;
				System.out.println("第一位評論作者id為："+id + "，作者的profile為：" + element);
				// 建立刷新Json物件
				JSONObject output_obj = new JSONObject();

				output_obj.put("id", id);
				output_obj.put("user_profile", element);

				output_array.add(output_obj);
				
			}
			
			 // 建立所有評論作者清單的JSON檔
			 FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_user_list\\Steam_user_list.json");
			 Writer json_writer = new OutputStreamWriter(fos, "UTF8");
			 
			 // 寫入JSON物件
			 json_writer.write("{" + "\"all_user_list\" :" + output_array.toJSONString() + "}");
			 
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
