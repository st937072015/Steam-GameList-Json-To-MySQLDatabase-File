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


public class Control_hub23_2 {

	public static void main(String[] args) {

		
		    // 馬瑞斯法計算txt檔案5大人格特質分數功能
			PersonalityRecognizer p = new PersonalityRecognizer(new File("C:\\Users\\John-Wall\\Desktop\\PersonalityRecognizer\\PersonalityRecognizer.properties"));
		    
		    
			
		
			for (int i = 32984; i < 328973; i++) {
           
				
	            // 排除無效評論作者
				File check_file =new File("D:\\steam_personality\\Mairesse\\txt\\user_review\\" + String.valueOf(i) + "\\1.txt");
				
				if (check_file.exists()) {
					
					// debug
					System.out.println("遊戲appid為:" + String.valueOf(i));
					
					// computeScoresOverCorpus(第1個參數為要計算的txt資料夾檔案目標路徑, (第2個參數為設定預測模型 model index *0為LinearRegression 1為M5P 2為M5P-R 3為SVM*), 第2個參數為計算完畢後arff檔案輸出路徑)
					p.computeScoresOverCorpus(new File("D:\\steam_personality\\Mairesse\\txt\\user_review\\" + String.valueOf(i)), p.loadWekaModels(2, false, true), new File("D:\\steam_personality\\Mairesse\\arff\\user_review_arff\\" + String.valueOf(i) + ".arff"));	
					
					
				}else{
					
					
					// debug
					System.out.println("遊戲appid為:" + String.valueOf(i) + "為空值評錄作者!!");
					
					
				}
				
				
				
				


			}
			
           
			
		
			
			 
			



		
	}

}
