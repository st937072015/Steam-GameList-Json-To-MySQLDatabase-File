package steam_recommendation_proj;

public class Big_string_convert {
	
	
	public String go_big_string_convert(String word_target) {
		
		// 定義需要取代的正規表達式字元(全形轉半形)
		
		// 全形字串陣列
		String c1[] ={"ａ","ｂ","ｃ","ｄ","ｅ","ｆ","ｇ","ｈ","ｉ","ｊ","ｋ","ｌ","ｍ","ｎ","ｏ","ｐ","ｑ","ｒ","ｓ","ｔ","ｕ","ｖ","ｗ","ｘ","ｙ","ｚ"};
		
		// 半形字串陣列
		String c2[] ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		
		for (int i = 0; i < c1.length; i++) {
			
			// 進行取代
			word_target = word_target.replaceAll(c1[i], c2[i]);
			
			
			
		}
		
	
		return word_target;
	}
	
	
	

	public static void main(String[] args) {
		/*Big_string_convert a =new Big_string_convert();
        
		
		System.out.println(a.go_big_string_convert("you are a genius. five　ｙｏｕ　ａｒｅ＠ 1"));
		*/
		
		
	}

}
