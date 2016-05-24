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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Steam_GameList_To_Mysql {
	// steam遊戲清單json檔案絕對路徑
	private static final String steam_game_list_filepath = "C:\\Users\\John-Wall\\Desktop\\SteamGameList_2016_05_23.json";

	// java連繫Mysql之物件
	private Connection consql = null;
	private Statement statsql = null;
	private ResultSet res = null;

	// java對Mysql下指令之字串變數
	private String cretable = null;
	private String insetable = null;

	public static void main(String[] args) {

		// 呼叫寫好的Mysql之function
		Mysql_function gosql = new Mysql_function();

		gosql.Create_table("CREATE TABLE game_info(" + " id INTEGER " + ", appid VARCHAR(20) " + ", name VARCHAR(50) " + ")");

		/*
		 * try { // 進行json檔案讀取 FileReader steamreader = new
		 * FileReader(steam_game_list_filepath);
		 * 
		 * // 印出確認讀檔程式資訊沒問題 FileWriter steamwriter = new FileWriter(
		 * "C:\\Users\\John-Wall\\Desktop\\SteamGameList_2016_05_23_test.txt");
		 * 
		 * JSONParser jsonParser = new JSONParser(); JSONObject jsonObject =
		 * (JSONObject) jsonParser.parse(steamreader);
		 * 
		 * // 讀取steam遊戲清單(app)之陣列 JSONArray app = (JSONArray)
		 * jsonObject.get("app");
		 * 
		 * // 印出結果並觀察陣列中的每款遊戲資訊 for (int i = 0; i < app.size(); i++) {
		 * 
		 * System.out.println("第" + i + "款遊戲的詳細資料為：" + app.get(i));
		 * 
		 * // 寫入文字檔 steamwriter.write("第" + i + "款遊戲的詳細資料為：" + app.get(i) +
		 * "\r\n");
		 * 
		 * }
		 * 
		 * // 關閉寫入 steamwriter.close();
		 * 
		 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); } catch (ParseException e) {
		 * e.printStackTrace(); } catch (NullPointerException e) {
		 * e.printStackTrace(); }
		 */
	}

}
