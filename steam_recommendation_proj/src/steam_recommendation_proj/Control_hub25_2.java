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


public class Control_hub25_2 {

	public static void main(String[] args) {

		try {
			

		    
			// 將arff檔轉換為csv檔
			Mairesse_calculate mc = new Mairesse_calculate();
		    
		    
			
			  for (int i = 1; i < 328973; i++) {

				
				// 排除無效評論作者
				File check_file =new File("D:\\steam_personality\\Mairesse\\csv\\user_review_csv\\" + String.valueOf(i) + ".csv");
				
				if (check_file.exists()) {
				
				// debug
				System.out.println("遊戲appid為:" + String.valueOf(i));				
			
				mc.convert_csv_to_json("D:\\steam_personality\\Mairesse\\csv\\user_review_csv\\", String.valueOf(i), "D:\\steam_personality\\Mairesse\\json\\user_review_json\\");
				
				
				}else{
						
					// debug
					System.out.println("遊戲appid為:" + String.valueOf(i) + "為空值評論遊戲!!");
					
					
				}


			}
			
           
			
		
			
			 
			



		} catch (NullPointerException e) {
			System.out.println(e.toString());
		}
		
		
	}

}
