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

public class Set_array_json_example {

	public static void main(String[] args) {

		
		String ex[] ={"楊新章","黃梓瑞","楊新章","神經質","神經質","史密斯"};
		
		Set<String> user_profile_index = new HashSet<String>();
		
		for (int i = 0; i < ex.length; i++) {
			
			user_profile_index.add(ex[i]);
			System.out.println(ex[i]);
			
		}
		Iterator it = user_profile_index.iterator();
		
		while (it.hasNext()) {
			System.out.println(it.next());
			
		}
			
		
		
		
		
		
		
		/*try {

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
		 FileOutputStream fos = new FileOutputStream("C:\\Users\\John-Wall\\Desktop\\test.json");
		 Writer json_writer = new OutputStreamWriter(fos, "UTF8");

		 // 寫入JSON物件
		 json_writer.write("{" + "\"app\" :" + review_array.toJSONString() + "}");
		 
		 // 關閉寫入
		 json_writer.flush();
		 json_writer.close();

		

	} catch (IOException e) {
		System.out.println(e.toString());
	}*/
			
			
		
		
		
		
		
		
	}

}
