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
	public void do_game_review_clean(String appid) {

		try {


			
			// 進行已過濾的遊戲id之json檔案讀取
			FileReader steamreader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_game_review_clean\\" + appid + ".json");

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(steamreader);

			// 讀取有250筆評論的steam遊戲之所有評論
			JSONArray game_review = (JSONArray) jsonObject.get("steam_review");
			
			// 建立儲存斷詞淨化後評論的arraylist
			ArrayList<String>store_arraylist=new ArrayList<String>();

			// 將JSONArray物件創建成Iterator迭代器
			Iterator it = game_review.iterator();
			
			
			
			// 建立Json Array
			JSONArray game_review_array = new JSONArray();

			int count = 0;
			while (it.hasNext()) {

				count++;
				
				JSONObject collection = (JSONObject) it.next();
                
				
			
				// 進行字串全形轉換為半形
				Big_string_convert convert = new Big_string_convert();
				
				String review_content = convert.go_big_string_convert(collection.get("review_content").toString());
				 
			   // 進行將特殊符號加入到arraylist
			   Sign_add sign_add = new Sign_add();
			   sign_add.string_arraylist_add_sign(review_content, ",", store_arraylist);
			   sign_add.string_arraylist_add_sign(review_content, "!", store_arraylist);
			   sign_add.string_arraylist_add_sign(review_content, "?", store_arraylist);
			   sign_add.string_arraylist_add_sign(review_content, ".", store_arraylist);
				 
				 
				 
			   // 進行斷詞
	           Lucene_standard_analyzer analyzer = new Lucene_standard_analyzer();
				
			   analyzer.use_standardanalyzer(review_content, store_arraylist);
				
			   // 若斷詞後的評論arraylist不為空
               if (!store_arraylist.isEmpty()) {
            	   
				JSONObject game_review_obj = new JSONObject();

			   // 塞入抓取到的評論
			   game_review_obj.put("review_content", store_arraylist);
			   game_review_array.add(game_review_obj);
			}
			   

			}

			// 建立淨化後遊戲評論的JSON檔
			FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_game_review_clean\\"+appid+".json");
			Writer json_writer = new OutputStreamWriter(fos, "UTF8");

			// 寫入JSON物件
			json_writer.write("{" + "\"steam_game_review_clean\" :" + game_review_array.toJSONString() + "}");

			// 關閉寫入
			json_writer.flush();
			json_writer.close();

			System.out.println("恭喜!遊戲id為："+appid+"的遊戲淨化完囉!");
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
