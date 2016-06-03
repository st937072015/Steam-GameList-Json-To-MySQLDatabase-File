package steam_recommendation_proj;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json_writer_example {

	public static void main(String[] args) {

	

		try {

			// 讀取測試json檔
			FileReader json_reader = new FileReader("C:\\Users\\John-Wall\\Desktop\\test\\420100.json");
			JSONParser parser = new JSONParser();
			JSONArray review_array = (JSONArray) parser.parse(json_reader);


			Iterator it = review_array.iterator();

			int count = 0;
			while (it.hasNext()) {
				count++;
				JSONObject collection = (JSONObject) it.next();

				System.out.println("第"+count+"篇評論的review_url為：**"+collection.get("review_url").toString()+"**，"+"**user_name為："+collection.get("user_name").toString()+"**");

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
