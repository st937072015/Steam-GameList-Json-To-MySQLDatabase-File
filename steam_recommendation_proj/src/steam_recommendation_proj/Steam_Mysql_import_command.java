package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class  Steam_Mysql_import_command {
	
	// java連繫Mysql之物件
	private static  Connection consql = null;
	private static PreparedStatement pre = null;
	private static Statement statsql = null;
	private static ResultSet res = null;
	
	// 將不同種人格特質對映方式計算結果之json檔案資料讀出
	private static ObjectMapper om  = new ObjectMapper();
	
	// 建立連繫Mysql之建構子
	public Steam_Mysql_import_command() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

			// "jdbc:mysql://localhost/資料庫名稱?useUnicode=true&characterEncoding=UTF-8",
			// "資料庫帳號","資料庫密碼"
			consql = DriverManager.getConnection(
					"jdbc:mysql://localhost/steam_recommendation?useUnicode=true&characterEncoding=UTF-8", "root",
					"5566898");

		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
	
		
	
	public static void main(String[] args) {
		Steam_Mysql_import_command go_sql = new Steam_Mysql_import_command();
		
		

		
		
   try {
	   
	   
	    /* 第一種人格特質評論對映方法_未與屬性標籤合併版本 */
	   
	    // Rock_第一種人格特質評論對映方法_未與屬性標籤合併版本
		LinkedHashMap<String, LinkedHashMap<String, Double>> Rock_第一種人格特質評論對映方法_未與屬性標籤合併版本 = om.readValue(new File("D:\\steam_personality\\Rock\\Rock_第一種人格特質評論對映方法_未與屬性標籤合併版本.json"), LinkedHashMap.class);
	   
		// Chang_第一種人格特質評論對映方法_未與屬性標籤合併版本
		LinkedHashMap<String, LinkedHashMap<String, Double>> Chang_第一種人格特質評論對映方法_未與屬性標籤合併版本 = om.readValue(new File("D:\\steam_personality\\Chang\\Chang_第一種人格特質評論對映方法_未與屬性標籤合併版本.json"), LinkedHashMap.class);
		
		// Mairesse_第一種人格特質評論對映方法_未與屬性標籤合併版本
		LinkedHashMap<String, LinkedHashMap<String, Double>> Mairesse_第一種人格特質評論對映方法_未與屬性標籤合併版本 = om.readValue(new File("D:\\steam_personality\\Mairesse\\Mairesse_第一種人格特質評論對映方法_未與屬性標籤合併版本.json"), LinkedHashMap.class);
		
		
		
		 /* 第二種人格特質評論對映方法_平均評論作者第二次未與屬性標籤合併版本 */
		   
	    // Rock_第二種人格特質評論對映方法_平均評論作者第二次未與屬性標籤合併版本
		LinkedHashMap<String, LinkedHashMap<String, Double>> Rock_第二種人格特質評論對映方法_平均評論作者第二次未與屬性標籤合併版本 = om.readValue(new File("D:\\steam_personality\\Rock\\Rock_第二種人格特質評論對映方法_平均評論作者第二次未與屬性標籤合併版本.json"), LinkedHashMap.class);
	   
		// Chang_第二種人格特質評論對映方法_平均評論作者第二次未與屬性標籤合併版本
		LinkedHashMap<String, LinkedHashMap<String, Double>> Chang_第二種人格特質評論對映方法_平均評論作者第二次未與屬性標籤合併版本 = om.readValue(new File("D:\\steam_personality\\Chang\\Chang_第二種人格特質評論對映方法_平均評論作者第二次未與屬性標籤合併版本.json"), LinkedHashMap.class);
		
		// Mairesse_第二種人格特質評論對映方法_平均評論作者第二次未與屬性標籤合併版本
		LinkedHashMap<String, LinkedHashMap<String, Double>> Mairesse_第二種人格特質評論對映方法_平均評論作者第二次未與屬性標籤合併版本 = om.readValue(new File("D:\\steam_personality\\Mairesse\\Mairesse_第二種人格特質評論對映方法_平均評論作者第二次未與屬性標籤合併版本.json"), LinkedHashMap.class);
		
		
		
		 /* 第一種人格特質評論對映方法_已與屬性標籤合併版本 */
		   
	    // Rock_第一種人格特質評論對映方法_已與屬性標籤合併版本
		LinkedHashMap<String, LinkedHashMap<String, Double>> Rock_第一種人格特質評論對映方法_已與屬性標籤合併版本 = om.readValue(new File("D:\\steam_personality\\Rock\\Rock_第一種人格特質評論對映方法_已與屬性標籤合併版本.json"), LinkedHashMap.class);
	   
		// Chang_第一種人格特質評論對映方法_已與屬性標籤合併版本
		LinkedHashMap<String, LinkedHashMap<String, Double>> Chang_第一種人格特質評論對映方法_已與屬性標籤合併版本 = om.readValue(new File("D:\\steam_personality\\Chang\\Chang_第一種人格特質評論對映方法_已與屬性標籤合併版本.json"), LinkedHashMap.class);
		
		// Mairesse_第一種人格特質評論對映方法_已與屬性標籤合併版本
		LinkedHashMap<String, LinkedHashMap<String, Double>> Mairesse_第一種人格特質評論對映方法_已與屬性標籤合併版本 = om.readValue(new File("D:\\steam_personality\\Mairesse\\Mairesse_第一種人格特質評論對映方法_已與屬性標籤合併版本.json"), LinkedHashMap.class);
		
		
		
		
		 /* 第二種人格特質評論對映方法_平均評論作者第二次已與屬性標籤合併版本 */
		   
	    // Rock_第二種人格特質評論對映方法_平均評論作者第二次已與屬性標籤合併版本
		LinkedHashMap<String, LinkedHashMap<String, Double>> Rock_第二種人格特質評論對映方法_平均評論作者第二次已與屬性標籤合併版本 = om.readValue(new File("D:\\steam_personality\\Rock\\Rock_第二種人格特質評論對映方法_平均評論作者第二次已與屬性標籤合併版本.json"), LinkedHashMap.class);
	   
		// Chang_第二種人格特質評論對映方法_平均評論作者第二次已與屬性標籤合併版本
		LinkedHashMap<String, LinkedHashMap<String, Double>> Chang_第二種人格特質評論對映方法_平均評論作者第二次已與屬性標籤合併版本 = om.readValue(new File("D:\\steam_personality\\Chang\\Chang_第二種人格特質評論對映方法_平均評論作者第二次已與屬性標籤合併版本.json"), LinkedHashMap.class);
		
		// Mairesse_第二種人格特質評論對映方法_平均評論作者第二次已與屬性標籤合併版本
		LinkedHashMap<String, LinkedHashMap<String, Double>> Mairesse_第二種人格特質評論對映方法_平均評論作者第二次已與屬性標籤合併版本 = om.readValue(new File("D:\\steam_personality\\Mairesse\\Mairesse_第二種人格特質評論對映方法_平均評論作者第二次已與屬性標籤合併版本.json"), LinkedHashMap.class);
		
		


			// 進行json檔案讀取
			FileReader steamreader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_sample_250.json");

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(steamreader);

			// 讀取steam遊戲清單(app)之陣列
			JSONArray app = (JSONArray) jsonObject.get("app_sample_250");

			// Iterator迭代器進行迭代
			Iterator it = app.iterator();

			
			int count = 1 ;

			// 取出Iterator中的集合遊戲資料
			while (it.hasNext()) {

				JSONObject collection = (JSONObject) it.next();

				
				
				// Debug訊息
				System.out.println("第" + count + "款，遊戲的id為 **" + collection.get("appid").toString() + "** " + "遊戲名為 **"
						+ collection.get("name").toString() + "** ");
				/*
				// 倒入計算後人格特質json文字資料至資料庫
				go_sql.game_data_import(collection.get("appid").toString(), collection.get("name").toString(),
						                Rock_第一種人格特質評論對映方法_未與屬性標籤合併版本,
						                Chang_第一種人格特質評論對映方法_未與屬性標籤合併版本,
						                Mairesse_第一種人格特質評論對映方法_未與屬性標籤合併版本,
						                Rock_第二種人格特質評論對映方法_平均評論作者第二次未與屬性標籤合併版本,
						                Chang_第二種人格特質評論對映方法_平均評論作者第二次未與屬性標籤合併版本,
						                Mairesse_第二種人格特質評論對映方法_平均評論作者第二次未與屬性標籤合併版本,
						                Rock_第一種人格特質評論對映方法_已與屬性標籤合併版本,
						                Chang_第一種人格特質評論對映方法_已與屬性標籤合併版本,
						                Mairesse_第一種人格特質評論對映方法_已與屬性標籤合併版本,
						                Rock_第二種人格特質評論對映方法_平均評論作者第二次已與屬性標籤合併版本,
						                Chang_第二種人格特質評論對映方法_平均評論作者第二次已與屬性標籤合併版本,
						                Mairesse_第二種人格特質評論對映方法_平均評論作者第二次已與屬性標籤合併版本);
						                
						                */
				
				
				 
				// 倒入屬性標籤資料至資料庫
				go_sql.game_tag_import(collection.get("appid").toString(), collection.get("name").toString());
				
				
				

				count++;

			}
			System.out.println("恭喜!全部插入完成!");


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
	
	// 資料庫完整使用完後關閉所有物件
	public static void Close() {

		try {

			if (statsql != null) {
				statsql.close();
				statsql = null;
			}
			if (pre != null) {
				pre.close();
				pre = null;
			}
			if (res != null) {

				res.close();
				res = null;

			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		}

	}
	
	
	
	
	
	// 將遊戲資料倒入資料庫指令
	public void game_data_import(String appid,String gamename,
			LinkedHashMap<String, LinkedHashMap<String, Double>> a,
			LinkedHashMap<String, LinkedHashMap<String, Double>> b,
			LinkedHashMap<String, LinkedHashMap<String, Double>> c,
			LinkedHashMap<String, LinkedHashMap<String, Double>> d,
			LinkedHashMap<String, LinkedHashMap<String, Double>> e,
			LinkedHashMap<String, LinkedHashMap<String, Double>> f,
			LinkedHashMap<String, LinkedHashMap<String, Double>> g,
			LinkedHashMap<String, LinkedHashMap<String, Double>> h,
			LinkedHashMap<String, LinkedHashMap<String, Double>> i,
			LinkedHashMap<String, LinkedHashMap<String, Double>> j,
			LinkedHashMap<String, LinkedHashMap<String, Double>> k,
			LinkedHashMap<String, LinkedHashMap<String, Double>> l) {
		
		try {
		
			
	    /*
	    // 插入遊戲基本資料至資料庫
		pre = consql.prepareStatement("INSERT INTO game_schema_table("
				                     + "game_appid, gamename)"
				                     + "VALUES(?, ?)");
		pre.setInt(1, Integer.parseInt(appid));
		pre.setString(2, gamename);
		*/
		
		
		
		
		
		/*
	    // 插入第一種人格特質評論對映方法_未與屬性標籤合併版本資料至資料庫
		pre = consql.prepareStatement("INSERT INTO none_review_merge("
				                     + "game_appid,"
				                     + "rock_none_review_merge,"
				                     + "chang_none_review_merge,"
				                     + "mairesse_none_review_merge)"
				                     + "VALUES(?, ?, ?, ?)");
		pre.setInt(1, Integer.parseInt(appid));
		pre.setString(2, om.writeValueAsString(a.get(appid)));
		pre.setString(3, om.writeValueAsString(b.get(appid)));
		pre.setString(4, om.writeValueAsString(c.get(appid)));
		*/
		
		
		
		
		
		
		/*
	    // 插入第二種人格特質評論對映方法_平均評論作者第二次未與屬性標籤合併版本資料至資料庫
		pre = consql.prepareStatement("INSERT INTO none_user_merge("
				                     + "game_appid,"
				                     + "rock_none_user_merge,"
				                     + "chang_none_user_merge,"
				                     + "mairesse_none_user_merge)"
				                     + "VALUES(?, ?, ?, ?)");
		pre.setInt(1, Integer.parseInt(appid));
		pre.setString(2, om.writeValueAsString(d.get(appid)));
		pre.setString(3, om.writeValueAsString(e.get(appid)));
		pre.setString(4, om.writeValueAsString(f.get(appid)));
		*/
		
		
		
		
		/*
	    // 插入第一種人格特質評論對映方法_已與屬性標籤合併版本資料至資料庫
		pre = consql.prepareStatement("INSERT INTO review_merge("
				                     + "game_appid,"
				                     + "rock_review_merge,"
				                     + "chang_review_merge,"
				                     + "mairesse_review_merge)"
				                     + "VALUES(?, ?, ?, ?)");
		pre.setInt(1, Integer.parseInt(appid));
		pre.setString(2, om.writeValueAsString(g.get(appid)));
		pre.setString(3, om.writeValueAsString(h.get(appid)));
		pre.setString(4, om.writeValueAsString(i.get(appid)));
		*/
		
		
		
	    // 插入第二種人格特質評論對映方法_平均評論作者第二次已與屬性標籤合併版本資料至資料庫
		pre = consql.prepareStatement("INSERT INTO user_merge("
				                     + "game_appid,"
				                     + "rock_user_merge,"
				                     + "chang_user_merge,"
				                     + "mairesse_user_merge)"
				                     + "VALUES(?, ?, ?, ?)");
		pre.setInt(1, Integer.parseInt(appid));
		pre.setString(2, om.writeValueAsString(j.get(appid)));
		pre.setString(3, om.writeValueAsString(k.get(appid)));
		pre.setString(4, om.writeValueAsString(l.get(appid)));
		

		pre.executeUpdate();
		
		
		
		} catch (NullPointerException ex) {
		  System.out.println(ex.toString());
		} catch (SQLException ex) {
		  System.out.println(ex.toString());
		} catch (JsonProcessingException ex) {
		  System.out.println(ex.toString());
		} finally {
		  Close();
		}
		
		
		
		
		
		
	}
	
	// 取出每款遊戲的標籤資料並倒入資料庫
	public void game_tag_import(String appid, String name) {
		

		
		try {
		// 進行json檔案讀取
		FileReader steamreader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_game_tag\\" + appid + "_attribute_tag.json");

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(steamreader);

		// 讀取steam遊戲tag清單(app)之陣列
		JSONArray app = (JSONArray) jsonObject.get("steam_attribute_tag");
		
		

		

		// Iterator迭代器進行迭代
		Iterator it = app.iterator();
		
		int count = 1;
		
		while (it.hasNext()) {
		
			
			JSONObject collection = (JSONObject) it.next();
			
			
			// Debug訊息
			System.out.println("第"+ count +"個標籤為: " + collection.get("tag").toString());
			
			
		    // 插入遊戲屬性標籤資料至資料庫
			pre = consql.prepareStatement("INSERT INTO game_tag_table(game_appid, gamename,tag_name) VALUES(?, ?, ?)");
			pre.setInt(1, Integer.parseInt(appid));
			pre.setString(2, name);
			pre.setString(3, collection.get("tag").toString());

			pre.executeUpdate();

            			
			count++;
			
			
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
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			Close();
		}
					
					
					
					
		
		
	
}
	
	
	
	

}
