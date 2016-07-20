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


public class Control_hub23 {

	public static void main(String[] args) {

		try {
			
			 // 讀取遊戲清單
			FileReader list_250_json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_sample_250.json");
			JSONParser list_250_parser = new JSONParser();
			JSONObject list_250_read_parser = (JSONObject) list_250_parser.parse(list_250_json_reader);

			JSONArray list_250_array = (JSONArray) list_250_read_parser.get("app_sample_250");

			Iterator list_250_it = list_250_array.iterator();
		    
		    // 馬瑞斯法計算txt檔案5大人格特質分數功能
			PersonalityRecognizer p = new PersonalityRecognizer(new File("C:\\Users\\John-Wall\\Desktop\\PersonalityRecognizer\\PersonalityRecognizer.properties"));
		    
		    
			
			// 取出Iterator中的遊戲資料
			while (list_250_it.hasNext()) {
           
				
				JSONObject collection = (JSONObject) list_250_it.next();
				
				// debug
				System.out.println("遊戲appid為:" + collection.get("appid").toString());
				
				// computeScoresOverCorpus(第1個參數為要計算的txt資料夾檔案目標路徑, (第2個參數為設定預測模型 model index *0為LinearRegression 1為M5P 2為M5P-R 3為SVM*), 第2個參數為計算完畢後arff檔案輸出路徑)
				p.computeScoresOverCorpus(new File("D:\\steam_personality\\Mairesse\\txt\\review\\" + collection.get("appid").toString()), p.loadWekaModels(2, false, true), new File("D:\\steam_personality\\Mairesse\\arff\\review_arff\\" + collection.get("appid").toString() + ".arff"));
				
				


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
