package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Steam_game_list_clean_finish {

	public static void main(String[] args) {

		/*try {

			// 匯出json檔
			// 建立Json Array
			JSONArray review_array = new JSONArray();

			for (int i = 0; i < 3; i++) {

				// 建立刷新Json物件
				JSONObject review_obj = new JSONObject();

				review_obj.put("姓名", "太神啦");
				review_obj.put("人格特質", "神經質");

				review_array.add(review_obj);
			}

			 // 建立抓取到遊戲評論的JSON檔
			 FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\test.json");
			 Writer json_writer = new OutputStreamWriter(fos, "UTF8");

			 // 寫入JSON物件
			 json_writer.write("{" + "\"app\" :" + review_array.toJSONString() + "}");
			 
			 // 關閉寫入
			 json_writer.flush();
			 json_writer.close();

			

		} catch (IOException e) {
			System.out.println(e.toString());
		}*/

		try {

			// 讀取測試json檔
			FileReader json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_clean.json");
			JSONParser parser = new JSONParser();
			JSONObject read_parser = (JSONObject) parser.parse(json_reader);
			
			JSONArray review_array = (JSONArray) read_parser.get("app");
            JSONArray review_array_output = new JSONArray();
			Iterator it = review_array.iterator();

			int count = 0;
			while (it.hasNext()) {
				count++;
				JSONObject collection = (JSONObject) it.next();

				/*System.out.println("遊戲id為:" + collection.get("appid").toString() + "，" + "遊戲名稱為:"
						+ collection.get("name").toString());*/
				
				 
				//檢查已確定有250筆評論的遊戲json檔案是否存在
				File check_file = new File("C:\\Users\\John-Wall\\Desktop\\Steam_game_review\\"+collection.get("appid").toString()+".json");
				if (check_file.exists()) {
					
					
					    
					    // 匯出json檔			
						// 建立刷新Json物件
						JSONObject review_obj = new JSONObject();

						review_obj.put("appid", collection.get("appid").toString());
						review_obj.put("name", collection.get("name").toString());

						review_array_output.add(review_obj);


					 // 建立抓取到遊戲評論的JSON檔
					 FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_sample_250.json");
					 Writer json_writer = new OutputStreamWriter(fos, "UTF8");

					 // 寫入JSON物件
					 json_writer.write("{" + "\"app_sample_250\" :" + review_array_output.toJSONString() + "}");
					 
					 // 關閉寫入
					 json_writer.flush();
					 json_writer.close();
					
					
					
					
					
					
				}else {
					
					System.out.println("遊戲id為:" + collection.get("appid").toString() + "，" + "遊戲名稱為:"
							+ collection.get("name").toString()+"，此遊戲評論數沒250!");
					
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				

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
