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



public class Steam_clean {

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

			// 讀取Steam總遊戲清單json檔
			FileReader json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\SteamGameList_2016_05_23_valid_raw.json");
			JSONParser parser = new JSONParser();
			JSONObject read_parser = (JSONObject) parser.parse(json_reader);

			// 讀取steam遊戲清單(app)之陣列
			JSONArray app = (JSONArray) read_parser.get("app");

			// 將JSONArray物件創建成Iterator迭代器
			Iterator it = app.iterator();



			int count = 0;
			while (it.hasNext()) {
				
				JSONObject collection = (JSONObject) it.next();

				/*
				// Debug訊息
				System.out.println("第" + count + "款，遊戲的id為 **" + collection.get("appid").toString() + "** " + "遊戲名為 **"
						+ collection.get("name").toString() + "** ");
						
						*/
				
				// 檢查是否為有效遊戲
				File check_file_pass =new File("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\pass\\"+collection.get("appid").toString()+"_pass.json");
				
				
				if ((check_file_pass.exists())&&(collection.get("name").toString().contains("DLC")||collection.get("name").toString().contains("Soundtrack")||(collection.get("name").toString().contains("Demo")&&!collection.get("name").toString().contains("Democ")&&!collection.get("name").toString().contains("Demoi")&&!collection.get("name").toString().contains("Demol")&&!collection.get("name").toString().contains("Demon"))||collection.get("name").toString().contains("Manual")||collection.get("name").toString().contains("Art Book"))) {
					count++;
					
					// Debug
					System.out.println("第"+count+"款遊戲，"+"此遊戲為無效遊戲!!!"+"因為此遊戲id為："+collection.get("appid").toString()+"此遊戲名稱為："+collection.get("name").toString());
					
					check_file_pass.delete();
					
					
				}
				
				/*
				// 進行判斷是否為有效遊戲
				else {
					
				
				
				
				
				
				URL url =new URL("http://store.steampowered.com/app/"+collection.get("appid").toString()+"/");
				
				
				Document target_xml= Jsoup.parse(url,20000);
				
				Elements ReviewsTab_all_btn =target_xml.select("div#ReviewsTab_all");
				


				if (ReviewsTab_all_btn.size()==0) {
					
					
				// Debug
				System.out.println("此遊戲"+collection.get("appid").toString()+"為Steam商城的無效遊戲!!因為沒有Most Helpful之按鈕");
				
				 // 建立無效遊戲的JSON檔
				 FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\fail\\"+collection.get("appid").toString()+"_fail.json");
				 Writer json_writer = new OutputStreamWriter(fos, "UTF8");

				 // 寫入JSON物件
				 json_writer.write("{" + "\"steam_valid\" :[fail]}");
				 
				 // 關閉寫入
				 json_writer.flush();
				 json_writer.close();
					
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
				

					 // 建立有效遊戲的JSON檔
					 FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\pass\\"+collection.get("appid").toString()+"_pass.json");
					 Writer json_writer = new OutputStreamWriter(fos, "UTF8");

					 // 寫入JSON物件
					 json_writer.write("{" + "\"steam_valid\" :" + review_array.toJSONString() + "}");
					 
					 // 關閉寫入
					 json_writer.flush();
					 json_writer.close();
					
					
					
					
					
					
				}
			}*/

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
