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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Control_hub25 {

	public static void main(String[] args) {

		try {
			
			 // 讀取遊戲清單
			FileReader list_250_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_sample_250.json");
			JSONParser list_250_parser = new JSONParser();
			JSONObject list_250_read_parser = (JSONObject) list_250_parser.parse(list_250_json_reader);

			JSONArray list_250_array = (JSONArray) list_250_read_parser.get("app_sample_250");

			Iterator list_250_it = list_250_array.iterator();
		    
			// 將arff檔轉換為csv檔
			Mairesse_calculate mc = new Mairesse_calculate();
		    
		    
			
			// 取出Iterator中的遊戲資料
			while (list_250_it.hasNext()) {
           
				
				JSONObject collection = (JSONObject) list_250_it.next();
				
				// 排除無效評論作者
				File check_file =new File("D:\\steam_personality\\Mairesse\\csv\\review_csv\\" + collection.get("appid").toString() + ".csv");
				
				if (check_file.exists()) {
				
				// debug
				System.out.println("遊戲appid為:" + collection.get("appid").toString());				
			
				mc.convert_csv_to_json("D:\\steam_personality\\Mairesse\\csv\\review_csv\\", collection.get("appid").toString(), "D:\\steam_personality\\Mairesse\\json\\review_json\\");
				
				
				}else{
						
					// debug
					System.out.println("遊戲appid為:" + collection.get("appid").toString() + "為空值評論遊戲!!");
					
					
				}


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
