package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;

import java.net.URL;

import org.jsoup.Jsoup;

import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.jsoup.nodes.Document;



public class Steam_check_valid {

	public static void main(String[] args) {
/*
		try {

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
		}
*/
		try {

			// 讀取測試json檔
			FileReader json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\SteamGameList_2016_05_23_valid_raw.json");
			JSONParser parser = new JSONParser();
			JSONObject read_parser = (JSONObject) parser.parse(json_reader);

			// 讀取steam遊戲清單(app)之陣列
			JSONArray app = (JSONArray) read_parser.get("app");

			// 將JSONArray物件創建成Iterator迭代器
			Iterator it = app.iterator();



			int count = 0;
			while (it.hasNext()) {
				count++;
				JSONObject collection = (JSONObject) it.next();

				// Debug訊息
				System.out.println("第" + count + "款，遊戲的id為 **" + collection.get("appid").toString() + "** " + "遊戲名為 **"
						+ collection.get("name").toString() + "** ");
				
				
				File check_file =new File("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\"+collection.get("appid").toString()+".json");
				if (check_file.exists()) {
					
					// Debug
					System.out.println("此遊戲"+collection.get("appid").toString()+"已經判斷過啦");
					
					
				}else {
					
				
				
				
				
				
				URL url =new URL("http://store.steampowered.com/app/"+collection.get("appid").toString()+"/");
				
				
				Document target_xml= Jsoup.parse(url,20000);
				
				Elements ReviewsTab_all_btn =target_xml.select("div#ReviewsTab_all");
				


				if (ReviewsTab_all_btn.size()==0) {
					
					
				// Debug
				System.out.println("此遊戲"+collection.get("appid").toString()+"為Steam商城的無效遊戲!!因為沒有Most Helpful之按鈕");
					
				}else if (ReviewsTab_all_btn.size()>0) {
					
					
					
				
					// Debug
					System.out.println("此遊戲"+collection.get("appid").toString()+"為Steam商城的有效遊戲!!可以使用!!");
					
					// 匯出json檔
					// 建立Json Array
					JSONArray review_array = new JSONArray();

					

						// 建立刷新Json物件
						JSONObject review_obj = new JSONObject();

						review_obj.put("appid", collection.get("appid").toString());
						review_obj.put("name", collection.get("name").toString());

						review_array.add(review_obj);
				

					 // 建立抓取到遊戲評論的JSON檔
					 FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\"+collection.get("appid").toString()+".json");
					 Writer json_writer = new OutputStreamWriter(fos, "UTF8");

					 // 寫入JSON物件
					 json_writer.write("{" + "\"steam_valid\" :" + review_array.toJSONString() + "}");
					 
					 // 關閉寫入
					 json_writer.flush();
					 json_writer.close();
					
					
					
					
					
					
				}
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
