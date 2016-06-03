package steam_recommendation_proj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Run_window_cmd {

	public static void main(String[] args) {

		try {

			// 進行json檔案讀取
			FileReader steamreader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_05_23_clean.json");

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(steamreader);

			// 讀取steam遊戲清單(app)之陣列
			JSONArray app = (JSONArray) jsonObject.get("app");

			// 將JSONArray物件創建成Iterator迭代器
			Iterator it = app.iterator();

			int count = 0;

			// 取出Iterator中的集合遊戲資料
			while (it.hasNext()) {

				count++;
				JSONObject collection = (JSONObject) it.next();

				// Debug訊息
				System.out.print("-----------------------------------------------------------------------------------");
				System.out.println("第" + count + "款，遊戲的id為 **" + collection.get("appid").toString() + "** " + "遊戲名為 **"
						+ collection.get("name") + "** ");
				
                // 檢查是否已經有抓完評論
				File check_file = new File("C:\\Users\\John-Wall\\Desktop\\Steam_game_review\\"+collection.get("appid").toString()+".json");
				
				
				if (check_file.exists()) {
					
					
				// Debug訊息
				System.out.println("第" + count + "款，遊戲的id為 **" + collection.get("appid").toString() + "** " + "遊戲名為 **"
							+ collection.get("name") + "** "+"此遊戲已經抓過評論囉!!!!!!!!!!");
				System.out.print("-----------------------------------------------------------------------------------");	
					
					
				}else {
					
					
					
					
				
						
						
						Process run_cmd =Runtime.getRuntime().exec("python D:\\Python\\Steamnew_2016_05_06\\steam-scraper\\steam_scraper.py --appid " + collection.get("appid").toString() + " -o C:\\Users\\John-Wall\\Desktop\\Steam_game_review\\" + collection.get("appid").toString() + ".json --sample 3");
                        run_cmd.waitFor();
						
						BufferedReader reader = new BufferedReader(new InputStreamReader(run_cmd.getInputStream())); 
						String line;
						while ( (line =reader.readLine())!=null) {
							
							
							
							 System.out.println(line);
							 
							 
							 
						}
						
						System.out.println("第" + count + "款，遊戲的id為 **" + collection.get("appid").toString() + "** " + "遊戲名為 **"
								+ collection.get("name") + "** "+"此遊戲的指定數量評論已抓完!");
		
					
					
					
					
		
					
				}
				
				
				
				
				

			}
			System.out.println("恭喜!終於全部的遊戲都抓完囉!");

		} catch (

		FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (ParseException e) {
			System.out.println(e.toString());
		} catch (NullPointerException e) {
			System.out.println(e.toString());
		}catch (InterruptedException e) {
			
			System.out.println(e);
			
		}

	}

}
