package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Control_hub2 {

	public static void main(String[] args) {

		try {

			// 進行已過濾的評論使用者json檔案讀取
			FileReader steamreader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_user_list\\Steam_user_list.json");

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(steamreader);

			// 讀取steam所有評論使用者(all_user_list)之陣列
			JSONArray app = (JSONArray) jsonObject.get("all_user_list");

			// 將JSONArray物件創建成Iterator迭代器
			Iterator it = app.iterator();

			int count = 0;

			// 取出Iterator中的集合使用者資料
			while (it.hasNext()) {

				count++;
				JSONObject collection = (JSONObject) it.next();

				// Debug訊息
				System.out.println("第" + count + "評論使用者，使用者的id為 **" + collection.get("id").toString() + "** " + "使用者的Profile為 **"
						+ collection.get("user_profile").toString() + "** ");
				
                // 檢查是否已經有抓完評論
				File check_file = new File("C:\\Users\\John-Wall\\Desktop\\Steam_user_review\\"+collection.get("id").toString()+".json");
				
				
				if (check_file.exists()) {
					
					
				// Debug訊息
				System.out.println("第" + count + "評論使用者，使用者的id為 **" + collection.get("id").toString() + "** " + "使用者的Profile為 **"
						+ collection.get("user_profile").toString() + "** "+"此評論使用者已經抓過評論囉!!!!!!!!!!");
				System.out.println("-----------------------------------------");
					
					
					
				}else {
					
					
					Steam_user_profile_review_jsoup do_user_profile_review =new Steam_user_profile_review_jsoup();
					
					
					
					
					
					
					do_user_profile_review.do_scraper_user_profile_review(collection.get("user_profile").toString(), "C:\\Users\\John-Wall\\Desktop\\Steam_user_review\\"+collection.get("id").toString()+".json");
					
					

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
