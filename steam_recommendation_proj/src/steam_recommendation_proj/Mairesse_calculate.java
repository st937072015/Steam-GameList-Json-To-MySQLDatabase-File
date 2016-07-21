package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.CSVSaver;

public class Mairesse_calculate {
    // json評錀文字內容轉txt檔
	public void json_convert_text_file(String read_path, String read_object, String appid, String output_path) {

		try {

			// 讀取遊戲評論json檔
			FileReader review_json_reader = new FileReader(read_path + appid + ".json");

			JSONParser review_parser = new JSONParser();

			JSONObject review_read_parser = (JSONObject) review_parser.parse(review_json_reader);

			JSONArray review_array = (JSONArray) review_read_parser.get(read_object);

			Iterator review_it = review_array.iterator();

			// 創建每款遊戲之轉檔過後存放之資料夾
			File create_review_folder = new File(output_path + appid);

			create_review_folder.mkdir();

			// 評論之count
			int review_count = 1;

			while (review_it.hasNext()) {

				JSONObject collection = (JSONObject) review_it.next();

				// debug
				System.out.println("遊戲appid為:" + appid + "，第" + (review_count) + "筆評論");

				// 輸出所有計算結果之json檔案
				FileOutputStream fos = new FileOutputStream(output_path + appid + "//"+ review_count + ".txt");
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
    // arff檔轉csv檔
	public void convert_arff_to_csv(String arff_input_path, String csv_output_path , String appid) {

		// 讀取arff檔案物件
		ArffLoader arff_Loader = new ArffLoader();

		// 轉檔儲存為csv檔案物件
		CSVSaver csv_saver = new CSVSaver();

		try {

			arff_Loader.setSource(new File(arff_input_path + appid +".arff"));

			// 取得dataset之資料
			Instances data_content = arff_Loader.getDataSet();

			// 寫入儲存為csv檔案
			csv_saver.setInstances(data_content);

			csv_saver.setFile(new File(csv_output_path + appid +".csv"));

			csv_saver.writeBatch();

		} catch (IOException e) {

			System.out.println(e);
		}

	}
// csv檔轉json檔
public void convert_csv_to_json(String read_csv_path, String appid, String output_path){
	
	CsvMapper mapper = new CsvMapper();
	
	CsvSchema schema = CsvSchema.emptySchema().withHeader();
	
	JSONArray output = new JSONArray();
	
	try {
		File csvFile = new File(read_csv_path + appid + ".csv");
		
		// 進行跌代
		MappingIterator<Map<String,String>> it = mapper.readerFor(Map.class).with(schema).readValues(csvFile);
		
		while (it.hasNext()) {
			
			Map<String,String> row_Map = it.next();
			
			//System.out.println(row_Map);
			
			output.add(row_Map);
			
			
		}
		
		
		 // 建立處理過後的JSON檔
		 FileOutputStream fos = new FileOutputStream(output_path + appid + ".json");
		 Writer json_writer = new OutputStreamWriter(fos, "UTF8");

		 // 寫入JSON物件
		 json_writer.write("{" + "\"mairesse_review\" :" + output.toJSONString() + "}");
		 
		 // 關閉寫入
		 json_writer.flush();
		 json_writer.close();
		
		
		
		
	} catch (JsonProcessingException e) {
		System.out.println(e);
	} catch (IOException e) {
		System.out.println(e);
	} 
	
	
	
	
}
// 第一種對映方法之人格特質平均分數json檔輸出
public void review_score_export(String read_path, String appid, LinkedHashMap<String, LinkedHashMap<String, Double>> output_map) {
	
	
	// 每款遊戲之儲存5大人格特質分數之linkedhashmap
	LinkedHashMap<String, Double> review_score_hashmap = new LinkedHashMap<String, Double>();
	
	// linkedhashmap初始化
	review_score_hashmap.put("Extraversion", 0.0);
	review_score_hashmap.put("'Emotional stability'", 0.0);
	review_score_hashmap.put("Agreeableness", 0.0);
	review_score_hashmap.put("Conscientiousness", 0.0);
	review_score_hashmap.put("'Openness to experience'", 0.0);
	
	try {
	
	 // 讀取遊戲清單
	FileReader review_json_reader = new FileReader(read_path + appid + ".json");
	JSONParser review_parser = new JSONParser();
	JSONObject review_read_parser = (JSONObject) review_parser.parse(review_json_reader);

	JSONArray review_array = (JSONArray) review_read_parser.get("mairesse_review");

	Iterator review_it = review_array.iterator();
	
	int review_count = 0;
	
	
	// 取出Iterator中的遊戲資料
	while (review_it.hasNext()) {
		
					
	 JSONObject collection = (JSONObject) review_it.next();
	 
	//debug
	System.out.println("遊戲appid為:" + appid + "，第"+ (review_count+1) +"筆評論");
	 
	 // 判斷分數是否為?
	 if (!collection.get("Extraversion").equals("?")) {
		 
		 review_score_hashmap.put("Extraversion", review_score_hashmap.get("Extraversion") + Double.parseDouble(collection.get("Extraversion").toString())); 
		
	 }
	 
	// 判斷分數是否為?
	 if (!collection.get("'Emotional stability'").equals("?")) {
		
		 review_score_hashmap.put("'Emotional stability'", review_score_hashmap.get("'Emotional stability'") + Double.parseDouble(collection.get("'Emotional stability'").toString())); 
		 
	 }
	 
	// 判斷分數是否為?
	 if (!collection.get("Agreeableness").equals("?")) {
		 
		 review_score_hashmap.put("Agreeableness", review_score_hashmap.get("Agreeableness") + Double.parseDouble(collection.get("Agreeableness").toString())); 
			
	 }
	 
	// 判斷分數是否為?
	 if (!collection.get("Conscientiousness").equals("?")) {
		 
		 review_score_hashmap.put("Conscientiousness", review_score_hashmap.get("Conscientiousness") + Double.parseDouble(collection.get("Conscientiousness").toString())); 
			
	 }
	 
	// 判斷分數是否為?
	 if (!collection.get("'Openness to experience'").equals("?")) {
		 
		 review_score_hashmap.put("'Openness to experience'", review_score_hashmap.get("'Openness to experience'") + Double.parseDouble(collection.get("'Openness to experience'").toString())); 
			
	 }
	 
	
	 // 計算合法評論總數量
	 if (!collection.get("Extraversion").equals("?") && !collection.get("'Emotional stability'").equals("?") && !collection.get("Agreeableness").equals("?") && !collection.get("Conscientiousness").equals("?") && !collection.get("'Openness to experience'").equals("?")) {
		
		 review_count++;
		 
	}
	 
	 
	 
	
	}
	
	// 進行分數平均
	for (String key : review_score_hashmap.keySet()) {
		
		review_score_hashmap.put(key, review_score_hashmap.get(key) / review_count);
			
	}
	
	// 儲存每一款遊戲算完的平均5大人格分數之linkedhashmap
	output_map.put(appid, review_score_hashmap);
	
	
	
	
	
	
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
