package steam_recommendation_proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mysql_function {

	// java連繫Mysql之物件
	private Connection consql = null;
	private PreparedStatement pre = null;
	private ResultSet res = null;
	private Statement statsql = null;

	// java對Mysql下指令之字串變數
	private String cretable = null;
	private String insetable = null;

	// 建立連繫Mysql之建構子
	public Mysql_function() {
		try {

			Class.forName("com.mysql.jdbc.Driver");
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

	// 讀取Steam遊戲清單json檔中的遊戲id與遊戲名稱並插入到Mysql中
	public void Insert_data(String appid, String game_name, String insert_command) {
		try {
			pre = consql.prepareStatement(insert_command);
			pre.setString(1, appid);
			pre.setString(1, game_name);

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
				pre.cancel();
				pre = null;
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		}

	}

	public static void main(String[] args) {

	}

}
