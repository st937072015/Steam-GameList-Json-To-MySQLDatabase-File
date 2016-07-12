package steam_recommendation_proj;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonArray;

public class Steam_attribute_tag_set {
	
	
public void attribute_tag_set(String appid, Set<String> tag_set) {
		
	try {

			// 進行已過濾的遊戲id之評論json檔案讀取
			FileReader steamreader = new FileReader("C:\\Users\\John-Wall\\Desktop\\Steam_game_tag\\" + appid +"_attribute_tag.json" );

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(steamreader);

			// 讀取有250筆評論的steam遊戲之id物件arraylist
			JSONArray app = (JSONArray) jsonObject.get("steam_attribute_tag");

			// 將JSONArray物件創建成Iterator迭代器
			Iterator it = app.iterator();
			
			// 取出tag屬性標籤
			while (it.hasNext()) {

				
			 JSONObject collection = (JSONObject) it.next();
			
			 tag_set.add(collection.get("tag").toString());
				
				
				
				
				
			
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

	public static void main(String[] args) {

	
		
	}

}
