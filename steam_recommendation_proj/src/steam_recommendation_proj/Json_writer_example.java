package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json_writer_example {

	public static void main(String[] args) {



		try {
			
			int review_count = 0;
			
			int game_count = 0;
			
			for (int i = 1; i < 328973; i++) {
				
			

			// 讀取測試json檔
			FileReader json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_user_review\\" + String.valueOf(i) + ".json");
			JSONParser parser = new JSONParser();
			JSONObject read_parser = (JSONObject) parser.parse(json_reader);
			
			game_count++;

			JSONArray review_array = (JSONArray) read_parser.get("steam_user_respective_review");
			Iterator it = review_array.iterator();

			while (it.hasNext()) {

				JSONObject collection = (JSONObject) it.next();
                 
				
				
				
				System.out.println("第"+game_count+"款遊戲，"+"評論作者id為:"+String.valueOf(i));
				
				System.out.println("評論內容為" + collection.get("review_content").toString());
				
				
				
			
				review_count++;	
				
				
			}
			
			
			}
			
			System.out.println("評論總數為：" + review_count + "筆");
			

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
