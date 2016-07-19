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

public class Mairesse_calculate {
	
public void json_convert_text_file(String read_path, String read_object, String appid, String output_path){
	
	 

	try {
		
		// 讀取遊戲評論json檔
		FileReader review_json_reader = new FileReader(read_path + appid + ".json");
		
		JSONParser review_parser = new JSONParser();
		
		JSONObject review_read_parser = (JSONObject) review_parser.parse(review_json_reader);

		JSONArray review_array = (JSONArray) review_read_parser.get(read_object);

		Iterator review_it = review_array.iterator();
		
		int review_count = 1;
		
		while(review_it.hasNext()){
			
			JSONObject collection = (JSONObject) review_it.next();
			
			
			
			//debug
			System.out.println("遊戲appid為:" + appid + "，第"+ (review_count) +"筆評論");
			
			
			
			 // 輸出所有計算結果之json檔案
			 FileOutputStream fos = new FileOutputStream(output_path + "appid_" + appid + "review_"+ review_count +".txt");
			 Writer json_writer = new OutputStreamWriter(fos, "UTF8");
			 
			 // 寫入JSON物件
			 json_writer.write(collection.get("review_content").toString());
			 
			 // 關閉寫入
			 json_writer.flush();
			 json_writer.close();
			
			 review_count++;
			 
			
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
