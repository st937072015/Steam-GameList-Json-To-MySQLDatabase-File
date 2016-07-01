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

public class Control_hub10 {

	public static void main(String[] args) {

		try {

			 // 讀取評論作者清單
			FileReader list_user_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_user_list\\Steam_user_list.json");
			JSONParser list_user_parser = new JSONParser();
			JSONObject list_user_read_parser = (JSONObject) list_user_parser.parse(list_user_json_reader);

			JSONArray list_user_array = (JSONArray) list_user_read_parser.get("all_user_list");

			Iterator list_user_it = list_user_array.iterator();
			

			

			// 取出Iterator中的評論作者的遊戲評論資料
			while (list_user_it.hasNext()) {
           
				
				JSONObject collection = (JSONObject) list_user_it.next();
				
			
			    
				
				
				Steam_review_tfidf go_tfidf =new Steam_review_tfidf();
				
				go_tfidf.tf_idf("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\Steam_user_review_dictionary_normal.json", "all_normal_word", "C:\\Users\\John-Wall\\Desktop\\Steam_user_review_clean\\" + collection.get("id").toString() + ".json", "steam_user_respective_review_clean", collection.get("id").toString(), "C:\\Users\\John-Wall\\Desktop\\Steam_user_review_tfidf\\", "steam_review_tfidf");

				

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
