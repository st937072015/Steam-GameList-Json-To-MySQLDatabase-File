package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
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
			
			System.out.println(row_Map);
			
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

}
