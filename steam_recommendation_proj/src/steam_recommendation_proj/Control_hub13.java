package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Control_hub13 {

	public static void main(String[] args) {

		try {

			// 進行已過濾的遊戲id之json檔案讀取
			FileReader steamreader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_valid\\SteamGameList_2016_06_11_sample_250.json");

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(steamreader);

			// 讀取有250筆評論的steam遊戲之id物件arraylist
			JSONArray app = (JSONArray) jsonObject.get("app_sample_250");

			// 將JSONArray物件創建成Iterator迭代器
			Iterator it = app.iterator();
			
			
			// 過濾重複屬性標籤
			Set<String> tag_set = new HashSet<String>();
			
            Steam_attribute_tag_set  steam_attribute_tag_set = new Steam_attribute_tag_set();
			
			int count = 0;

			// 取出Iterator中的集合遊戲資料
			while (it.hasNext()) {

				count++;
				JSONObject collection = (JSONObject) it.next();

				steam_attribute_tag_set.attribute_tag_set(collection.get("appid").toString(), tag_set);
				
				// Debug訊息
				System.out.println("第" + count + "遊戲的id為 **" + collection.get("appid").toString() + "** " + "遊戲名稱為 **"
						+ collection.get("name").toString() + "** ");
				
				

			}
			
			
			// 轉換tag之set的array
			ArrayList<String> tag_convert = new ArrayList<String>(tag_set);
			
			// 轉換為JSONArray
			JSONArray tag_convert_json = new JSONArray();
			for (int i = 0; i < tag_convert.size(); i++) {
				
				
				tag_convert_json.add(tag_convert.get(i));
				
				
			}
			
			// 建立屬性標籤字典
			FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\Steam_review_dictionary\\Steam_attribute_tag_dictionary.json");
			Writer json_writer = new OutputStreamWriter(fos, "UTF8");
			 
			// 寫入JSON物件
			json_writer.write("{" + "\"attribute_tag_dictionary\" :" + (tag_convert_json).toJSONString() + "}");
			 
			// 關閉寫入
			json_writer.flush();
			json_writer.close();
			
			


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
