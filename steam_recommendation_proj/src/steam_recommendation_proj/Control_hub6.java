package steam_recommendation_proj;

import java.io.File;
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
import org.openqa.selenium.remote.server.handler.MaximizeWindow;

public class Control_hub6 {

	public static void main(String[] args) {

		try {

			 // 讀取遊戲清單
			FileReader json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_sample_250.json");
			JSONParser parser = new JSONParser();
			JSONObject read_parser = (JSONObject) parser.parse(json_reader);

			JSONArray gamelist_250_array = (JSONArray) read_parser.get("app_sample_250");

			Iterator it = gamelist_250_array.iterator();
			
			JSONArray output_array= new JSONArray();
			
			Set<String> steam_review_dictionary_normal_set = new HashSet<String>();

			// 取出Iterator中的遊戲資料
			while (it.hasNext()) {

				
				JSONObject collection = (JSONObject) it.next();
				Steam_review_dictionary normal =new Steam_review_dictionary();

				normal.produce_steam_review_dictionary_normal(collection.get("appid").toString(), steam_review_dictionary_normal_set);
				
				
				

			}
			
			
			
			for (String element:steam_review_dictionary_normal_set) {
				
				System.out.println(element);
				
				// 建立刷新Json物件
				JSONObject output_obj = new JSONObject();
				output_obj.put("word", element);
				output_array.add(output_obj);
				
				
				
			}
		
			
			 // 建立第一種對映方法之字典
			 FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\Steam_review_dictionary_normal.json");
			 Writer json_writer = new OutputStreamWriter(fos, "UTF8");
			 
			 // 寫入JSON物件
			 json_writer.write("{" + "\"all_normal_word\" :" + output_array.toJSONString() + "}");
			 
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
