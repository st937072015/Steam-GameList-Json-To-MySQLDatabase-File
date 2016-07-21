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


public class Control_hub26_2 {

	public static void main(String[] args) {

		try {
			
			// 若為無效評論作者	
			LinkedHashMap<String, Double> review_score_hashmap = new LinkedHashMap<String, Double>();
			
			// linkedhashmap初始化
			review_score_hashmap.put("Extraversion", 0.0);
			review_score_hashmap.put("'Emotional stability'", 0.0);
			review_score_hashmap.put("Agreeableness", 0.0);
			review_score_hashmap.put("Conscientiousness", 0.0);
			review_score_hashmap.put("'Openness to experience'", 0.0);

			
			// 儲存計算結果
        	LinkedHashMap<String, LinkedHashMap<String, Double>> output_map = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
		    
			// 將arff檔轉換為csv檔
			Mairesse_calculate mc = new Mairesse_calculate();
		    
		    
			
			   for (int i = 1; i < 328973; i++) {
           
				
				// 排除無效評論作者
				File check_file =new File("D:\\steam_personality\\Mairesse\\json\\user_review_json\\" + String.valueOf(i) + ".json");
				
				if (check_file.exists()) {						
			    
			    // 計算5大人格平均分數
				mc.review_score_export("D:\\steam_personality\\Mairesse\\json\\user_review_json\\", String.valueOf(i), output_map);
				
				
				}else{
						
					// debug
					System.out.println("遊戲appid為:" + String.valueOf(i) + "為空值評論遊戲!!");
					
					output_map.put(String.valueOf(i), review_score_hashmap);
					
					
					
				}


			}
			
           	
			// json檔案計算結果輸出
			ObjectMapper om  = new ObjectMapper();
												
			om.writeValue(new File("D:\\steam_personality\\Mairesse\\Mairesse_第二種人格特質評論對映方法_僅平均評論作者第一次版本.json"), output_map);
			
			 
			



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
