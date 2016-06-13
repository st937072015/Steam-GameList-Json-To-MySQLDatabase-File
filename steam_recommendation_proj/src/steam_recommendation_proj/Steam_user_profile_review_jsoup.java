package steam_recommendation_proj;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Steam_user_profile_review_jsoup {
	public void do_scraper_user_profile_review(String user_profile_index, String user_profile_json_output_path) {

		try {

			// 執行jsoup動作先取得使用者的總評論數量
			URL url = new URL(user_profile_index + "recommended/?p=1");

			Document target_xml = Jsoup.parse(url, 20000);

			List<Element> user_profile_game_review_count = target_xml
					.select("div.review_stat > div.giantNumber.ellipsis");
			List<Element> user_profile_review_valid = target_xml
					.select("div.rightcol > div.content");

			if (user_profile_game_review_count.size() > 0 && user_profile_review_valid.size()== 0) {

			

			// 儲存所有頁數評論元素的list
			List<Element> user_profile_game_all_review = new ArrayList<Element>();

			// 獲取Steam評論作者User profile 總評論數量並轉成float型態
			float review_number = Integer.parseInt(user_profile_game_review_count.get(0).text());

			// 進行使用者評論總頁數換算
			int review_all_page = (int) Math.ceil(review_number / 10);

			for (int j = 1; j < review_all_page + 1; j++) {

				// 執行jsoup動作取得每頁的所有評論內容
				URL url_review = new URL(user_profile_index + "recommended/?p=" + j);

				Document review_target_xml = Jsoup.parse(url_review, 20000);

				// 取得特定頁數的所有評論元素並先暫存進list中
				List<Element> get_page_review = review_target_xml.select("div.rightcol > div.content");

				// 將暫存在list中的所有評論元素取出並塞進儲存所有頁數評論元素的list
				for (int k = 0; k < get_page_review.size(); k++) {

					user_profile_game_all_review.add(get_page_review.get(k));

				}

			}
			// 建立Json Array
			JSONArray user_review_array = new JSONArray();

			int count = 0;
			for (int i = 0; i < user_profile_game_all_review.size(); i++) {

				count++;

				System.out.println("第" + count + "筆評論，" + "評論內容為：" + user_profile_game_all_review.get(i).text());
				// 建立刷新Json物件
				JSONObject user_review_obj = new JSONObject();

				// 塞入抓取到的評論
				user_review_obj.put("review_content", user_profile_game_all_review.get(i).text());
				user_review_array.add(user_review_obj);

			}

			// 建立抓取到遊戲評論的JSON檔
			FileOutputStream fos = new FileOutputStream(user_profile_json_output_path);
			Writer json_writer = new OutputStreamWriter(fos, "UTF8");

			// 寫入JSON物件
			json_writer.write("{" + "\"steam_user_respective_review\" :" + user_review_array.toJSONString() + "}");

			// 關閉寫入
			json_writer.flush();
			json_writer.close();

			System.out.println("恭喜!終於所指定的Steam使用者所屬之全部的遊戲評論都抓完囉!");
			System.out.println("-----------------------------------------");
			
			
			}else{
				
				System.out.println("此評論使用者已經變為無效評論使用者了!");
				System.out.println("-----------------------------------------");
				
			}
			
			
		} catch (

		FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (NullPointerException e) {
			System.out.println(e.toString());
		}
		

	}

	public static void main(String[] args) {

	}

}
