package steam_recommendation_proj;

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
public class Steam_game_review_clean {
	public void do_game_review_clean(String read_analyze_file_path, String output_analyze_file_path, String read_analyze_file_json_object, String analyze_collection_text_name,String write_analyze_file_json_object,  String debug_message) {

		try {


			
			// 進行要進行文字分析之json檔案讀取
			FileReader steamreader = new FileReader(read_analyze_file_path);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(steamreader);

			// 讀取所有文字資料
			JSONArray game_review = (JSONArray) jsonObject.get(read_analyze_file_json_object);
			
		

			// 將JSONArray物件創建成Iterator迭代器
			Iterator it = game_review.iterator();
			
			
			
			// 建立Json Array
			JSONArray game_review_array = new JSONArray();

			int count = 0;
			while (it.hasNext()) {

				count++;
				
				JSONObject collection = (JSONObject) it.next();
                
			    // 建立儲存斷詞淨化後評論的arraylist
			    ArrayList<String>store_arraylist=new ArrayList<String>();
			
				// 進行字串全形轉換為半形與移除阿拉伯數字
				Big_string_convert convert = new Big_string_convert();
				
				String review_content = convert.go_big_string_convert(collection.get(analyze_collection_text_name).toString());
				 
			   // 進行將特殊符號加入到arraylist
			   Sign_add_and_clean sign_add = new Sign_add_and_clean();
			   sign_add.string_arraylist_add_sign(review_content, ",", store_arraylist);
			   sign_add.string_arraylist_add_sign(review_content, "!", store_arraylist);
			   sign_add.string_arraylist_add_sign(review_content, "?", store_arraylist);
			   sign_add.string_arraylist_add_sign(review_content, ".", store_arraylist);
				 
				 
				 
			   // 進行斷詞
	           Lucene_standard_analyzer analyzer = new Lucene_standard_analyzer();
				
			   analyzer.use_standardanalyzer(review_content, store_arraylist);
				

            	   
            	   
				JSONObject game_review_obj = new JSONObject();

			   // 塞入斷詞後的字詞array資料
			   game_review_obj.put(analyze_collection_text_name, store_arraylist);
			   game_review_array.add(game_review_obj);
			
			   

			}

			// 建立淨化分析後的文字資料JSON檔
			FileOutputStream fos = new FileOutputStream(output_analyze_file_path);
			Writer json_writer = new OutputStreamWriter(fos, "UTF8");

			// 寫入JSON物件
			json_writer.write("{" + "\"" + write_analyze_file_json_object + "\" :" + game_review_array.toJSONString() + "}");

			// 關閉寫入
			json_writer.flush();
			json_writer.close();

			System.out.println(debug_message);
			System.out.println("-----------------------------------------");
			

			
			
		} catch (

		FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (NullPointerException e) {
			System.out.println(e.toString());
		} catch (ParseException e) {
			System.out.println(e.toString());
		}
		

	}

	public static void main(String[] args) {

	}

}
