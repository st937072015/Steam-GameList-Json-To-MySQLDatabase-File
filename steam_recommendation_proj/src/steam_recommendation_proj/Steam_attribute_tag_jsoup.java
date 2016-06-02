package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Steam_attribute_tag_jsoup {

	public static void main(String[] args) {

		try {

			// 進行有效遊戲的json檔案讀取
			FileReader steamreader = new FileReader(
					"C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_05_23_clean.json");

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(steamreader);

			// 讀取steam遊戲清單(app)之陣列
			JSONArray app = (JSONArray) jsonObject.get("app");

			// 將JSONArray物件創建成Iterator迭代器
			Iterator it = app.iterator();

			int count = 0;

			// 要存遊戲屬性tag用
			JSONArray tag_array = new JSONArray();

			// 取出Iterator中的集合遊戲資料
			while (it.hasNext()) {

				count++;

				JSONObject collection = (JSONObject) it.next();
				// Debug訊息
				System.out.println("第" + count + "款，遊戲的id為 **" + collection.get("appid").toString() + "** " + "遊戲名為 **"
						+ collection.get("name").toString() + "** ");

				File check_tag_file = new File(
						"C:\\Users\\John-Wall\\Desktop\\" + collection.get("appid").toString() + "_attribute_tag.json");

				if (!check_tag_file.exists()) {
                    
					// 執行jsoup動作
					URL url = new URL("http://store.steampowered.com/app/" + collection.get("appid").toString() + "/");

					Document target_xml = Jsoup.parse(url, 20000);

					List<Element> game_attribute_tag_list = target_xml.select("a.app_tag");

					for (int i = 0; i < game_attribute_tag_list.size(); i++) {

						// DEBUG
						System.out.println("第" + i + "個遊戲屬性標籤為：" + game_attribute_tag_list.get(i).text());

						// 建立刷新Json物件
						JSONObject tag_obj = new JSONObject();
						// 遊戲所屬的屬性標籤
						tag_obj.put("tag", game_attribute_tag_list.get(i).text());

						// 寫入Json物件與JsonArray

						tag_array.add(tag_obj);

					}

					try {
						// 建立抓取到遊戲屬性tag的JSON檔
						FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_game_tag\\"
								+ collection.get("appid") + "_attribute_tag.json");
						Writer json_writer = new OutputStreamWriter(fos, "UTF8");

						// 寫入JSON物件
						json_writer.write("{" + "\"steam_attribute_tag\" :" + tag_array.toJSONString() + "}");

						// 關閉寫入
						json_writer.flush();
						json_writer.close();
					} catch (Exception e) {
						System.out.println(e.toString());
					}

				}

			}
			System.out.println("恭喜!終於全部的遊戲之屬性標籤都抓完囉!");

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
