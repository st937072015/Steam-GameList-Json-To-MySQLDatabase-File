package steam_recommendation_proj;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Steam_GameList_To_Mysql {

	public static void main(String[] args) {
		// 呼叫寫好的Mysql之function
		Mysql_function gosql = new Mysql_function();

		try {

			// 進行json檔案讀取
			FileReader steamreader = new FileReader("C:\\Users\\John-Wall\\Desktop\\SteamGameList_2016_05_23.json");

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(steamreader);

			// 讀取steam遊戲清單(app)之陣列
			JSONArray app = (JSONArray) jsonObject.get("app");

			// 將JSONArray物件創建成Iterator迭代器
			Iterator it = app.iterator();

			// 呼叫建立自訂table之function
			gosql.Create_table("CREATE TABLE game_info(" + " appid INTEGER(20) " + ", name VARCHAR(100) "
					+ ", PRIMARY KEY (appid) " + ")");

			int count = 0;

			// 取出Iterator中的集合遊戲資料
			while (it.hasNext()) {

				count++;
				JSONObject collection = (JSONObject) it.next();

				// 呼叫建立自訂插入新資料之function
				// 再將取出的集合資料新增到資料庫中
				gosql.Insert_data(collection.get("appid").toString(), collection.get("name").toString(),
						"INSERT INTO game_info(appid, name) VALUES(?, ?)");
				// Debug訊息
				System.out.println("第" + count + "款，遊戲的id為 **" + collection.get("appid").toString() + "** " + "遊戲名為 **"
						+ collection.get("name") + "** ");

			}
			System.out.println("恭喜!全部插入完成!");

			/*
			 * // 印出確認讀檔程式資訊沒問題 FileWriter steamwriter = new FileWriter(
			 * "C:\\Users\\John-Wall\\Desktop\\SteamGameList_2016_05_23_test.txt"
			 * );
			 */

			/*
			 * // 印出結果並觀察陣列中的每款遊戲資訊 for (int i = 0; i < app.size(); i++) {
			 * 
			 * System.out.println("第" + i + "款遊戲的詳細資料為：" + app.get(i));
			 * 
			 * // 寫入文字檔 steamwriter.write("第" + i + "款遊戲的詳細資料為：" + app.get(i) +
			 * "\r\n");
			 * 
			 * }
			 * 
			 * // 關閉寫入 
			 * steamwriter.flush();
			 * steamwriter.close();
			 */

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
