package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json_writer_example {

	public static void main(String[] args) {

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
			File file = new File("C:\\Users\\John-Wall\\Desktop\\test.json");
			file.createNewFile();
			FileWriter json_writer = new FileWriter(file);

			// 寫入JSON物件
			json_writer.write("{" + "\"app\" :" + review_array.toJSONString() + "}");

			// 關閉寫入
			json_writer.flush();
			json_writer.close();

		} catch (IOException e) {
			System.out.println(e.toString());
		}

		try {

			// 讀取測試json檔
			FileReader json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_game\\282350.json");
			JSONParser parser = new JSONParser();
			JSONObject read_parser = (JSONObject) parser.parse(json_reader);

			JSONArray review_array = (JSONArray) read_parser.get("steam_review");

			Iterator it = review_array.iterator();

			int count = 0;
			while (it.hasNext()) {
				count++;
				JSONObject collection = (JSONObject) it.next();

				System.out.println("user_profile為:" + collection.get("user_profile").toString() + "，" + "review_url:"
						+ collection.get("review_url").toString());

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
