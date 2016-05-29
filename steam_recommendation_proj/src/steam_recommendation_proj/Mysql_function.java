package steam_recommendation_proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Mysql_function {

	// java連繫Mysql之物件
	private Connection consql = null;
	private PreparedStatement pre = null;
	private Statement statsql = null;
	private ResultSet res = null;

	// 建立連繫Mysql之建構子
	public Mysql_function() {
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

	// 建立新表格之function
	// *create_command為自訂創建資料表之SQL語法字串*
	public void Create_table(String create_command) {
		try {
			statsql = consql.createStatement();
			statsql.executeUpdate(create_command);

		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			Close();
		}
	}

	// 取出資料庫已存好的Steam遊戲資料
	// *select_command為自訂選取資料表之SQL語法字串*
	public void Select_table(String select_command) {
		try {
			statsql = consql.createStatement();
			res = statsql.executeQuery(select_command);

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {

			Close();

		}

	}

	// 讀取Steam遊戲清單json檔中的遊戲id與遊戲名稱並插入到Mysql中
	// *appid為Steam遊戲之id編號字串* *game_name為Steam遊戲之遊戲名稱字串*
	// *insert_command為自訂插入新多筆資料之SQL語法字串*
	public void Insert_data(String appid, String game_name, String insert_command) {
		try {
			pre = consql.prepareStatement(insert_command);
			pre.setString(1, appid);
			pre.setString(2, game_name);

			pre.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			Close();
		}

	}

	// 資料庫完整使用完後關閉所有物件
	private void Close() {

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

	public static void main(String[] args) {

	}

}
