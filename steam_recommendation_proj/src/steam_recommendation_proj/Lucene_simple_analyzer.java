package steam_recommendation_proj;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class Lucene_simple_analyzer {

	// 建構lucene simpleAnalyzer斷詞 之function (function回傳為字串型態的arraylist)
	public ArrayList<String> use_simpleanalyzer(String text_content) {

		// 使用arraylist儲存斷詞之結果
		ArrayList<String> text_token_store = new ArrayList<String>();

		try {

			Analyzer analyzer = new SimpleAnalyzer();

			// tokenstream (自訂文字涵蓋範圍之名稱, 要進行斷詞的文字字串)
			TokenStream tokenstream = analyzer.tokenStream("text_content", new StringReader(text_content));

			CharTermAttribute chartermattribute = tokenstream.addAttribute(CharTermAttribute.class);

			tokenstream.reset();

			while (tokenstream.incrementToken()) {
				// System.out.println(chartermattribute);
				text_token_store.add(chartermattribute.toString());
				
				
				
				
			}

			// 關閉釋放
			tokenstream.close();
			analyzer.close();

		} catch (IOException e) {

			System.out.println(e);
		}

		// 回傳已經存好的arraylist所有斷詞之結果
		return text_token_store;

	}

	public static void main(String[] args) {

	}

}
