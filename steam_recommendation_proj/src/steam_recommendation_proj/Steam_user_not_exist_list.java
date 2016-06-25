package steam_recommendation_proj;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Steam_user_not_exist_list {

	public static void main(String[] args) {



		try {

            
			FileReader json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_user_list\\Steam_user_not_exist_list.json");
			JSONParser parser = new JSONParser();
			JSONObject read_parser = (JSONObject) parser.parse(json_reader);

			JSONArray user_list_array = (JSONArray) read_parser.get("all_not_exist_user_list");

			Iterator it = user_list_array.iterator();
			

			
			int count = 0;
			
            int review_count = 0;
            
       
			
			// 讀取評論數至少有250筆的遊戲清單json檔
			while (it.hasNext()) {
				count++;
				JSONObject collection = (JSONObject) it.next();
                // 建立Json Array
			    JSONArray user_not_exist_review_array = new JSONArray();
                FileReader game_list_reader =new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_sample_250.json");

				JSONParser game_list_parser = new JSONParser();
				JSONObject game_list_read_parser =(JSONObject)game_list_parser.parse(game_list_reader);
				
				JSONArray game_list_array =(JSONArray) game_list_read_parser.get("app_sample_250");
				
				Iterator game_list_it =game_list_array.iterator();
				
				
				while (game_list_it.hasNext()) {
					
					JSONObject game_list_collection = (JSONObject) game_list_it.next();
					
	                FileReader game_review_reader =new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_game_review\\"+game_list_collection.get("appid").toString()+".json");

					JSONParser game_review_parser = new JSONParser();
					JSONObject game_review_read_parser =(JSONObject)game_review_parser.parse(game_review_reader);
					
					JSONArray game_review_array =(JSONArray) game_review_read_parser.get("steam_review");
					
					Iterator game_review_it =game_review_array.iterator();
					while (game_review_it.hasNext()) {
					
						
						JSONObject game_review_collection = (JSONObject) game_review_it.next();
						
						
						if (collection.get("user_profile").toString().equals(game_review_collection.get("user_profile"))) {
							
							System.out.println(game_review_collection.get("user_profile"));
							
							JSONObject user_not_exist_review_obj = new JSONObject();

							user_not_exist_review_obj.put("review_content", game_review_collection.get("review_content"));

							user_not_exist_review_array.add(user_not_exist_review_obj);
							
							
							
							
						}
						
						
						
						
						
					}
					
					
					
					
					
					
					
					
					
				}
				
               

				
		
			    			 // 建立所有評論作者清單的JSON檔
			 FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_not_exist_user_review\\"+collection.get("id").toString()+".json");
			 Writer json_writer = new OutputStreamWriter(fos, "UTF8");
			 
			 // 寫入JSON物件
			 json_writer.write("{" + "\"steam_user_respective_review\" :" + user_not_exist_review_array.toJSONString() + "}");
			 
			 // 關閉寫入
			 json_writer.flush();
			 json_writer.close();
    
                
                
                
				
				
				
				
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
