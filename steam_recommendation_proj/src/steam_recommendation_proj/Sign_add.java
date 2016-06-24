package steam_recommendation_proj;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

public class Sign_add {

	
	public void string_arraylist_add_sign(String text_content, String text_check_target, ArrayList<String> text_arraylist) {
		
		
		

	    // 找尋目標字串中所存在的目標字詞數量
		int text_count =StringUtils.countMatches(text_content, text_check_target);
		
		
		
		// 目標字詞數量判定完後再將確定數量的目標字詞塞入字串arraylist
		for (int i = 0; i < text_count ; i++) {
			
			text_arraylist.add(text_check_target);

		}
		
		
		
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		/*Sign_add a = new Sign_add();
	  a.string_arraylist_add_sign("you are a yellow", "y", null);
      */
	}

}
