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
import java.util.LinkedHashMap;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.server.handler.MaximizeWindow;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Control_hub12 {

	public static void main(String[] args) {

		try {

			


            

			// 儲存idf結果
			LinkedHashMap<String, Double> review_content_idf_hashmap = new LinkedHashMap<String, Double>();
			
			
			
			// 計算全部review數量
			int review_all_count = 0;
			
			
			Steam_user_review_tfidf go_tfidf =new Steam_user_review_tfidf();
			
			
			// 取出Iterator中的評論作者的遊戲評論資料
		
            for (int i = 1; i < 328973; i++) {
				
			
				

					
			    
				
				
				
				
				review_all_count = review_all_count + go_tfidf.idf("C:\\Users\\John-Wall\\Desktop\\Steam_user_review_tfidf\\" + String.valueOf(i) + ".json", "steam_review_tfidf", String.valueOf(i), review_content_idf_hashmap);

				
               }
			
			
			// debug
			System.out.println(review_all_count);
		       
			for (String key : review_content_idf_hashmap.keySet()) {
				
				
				
				review_content_idf_hashmap.put(key, Math.log(review_all_count / review_content_idf_hashmap.get(key)));
				
				
				
				
			}
			
	
			
			
			

			
			/*
			 // 建立idf分數參考字典
			 FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\steam_user_review_idf.json");
			 Writer json_writer = new OutputStreamWriter(fos, "UTF8");

			 // 寫入JSON物件
			 json_writer.write("{" + "\"steam_user_review_idf\" :" + review_content_idf_array.toJSONString() + "}");
			 
			 // 關閉寫入
			 json_writer.flush();
			 json_writer.close();
			*/
			
			// 改使用jackson json lib 轉換大型json檔案(**simplejson較不適合大型json檔案之轉換**)
			ObjectMapper om  = new ObjectMapper();
			
			om.writeValue(new File("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\steam_user_review_idf.json"), review_content_idf_hashmap);
			
	


		} catch (

		FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (NullPointerException e) {
			System.out.println(e.toString());
		}
		
		
	}

}
