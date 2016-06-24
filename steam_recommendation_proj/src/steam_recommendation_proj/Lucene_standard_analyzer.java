package steam_recommendation_proj;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class Lucene_standard_analyzer {

	// 建構lucene standardAnalyzer斷詞 之function (function回傳為字串型態的arraylist)
	public void use_standardanalyzer(String text_content, ArrayList<String> add_text_token  ) {

		

		try {

			Analyzer analyzer = new StandardAnalyzer();

			// tokenstream (自訂文字涵蓋範圍之名稱, 要進行斷詞的文字字串)
			TokenStream tokenstream = analyzer.tokenStream("text_content", new StringReader(text_content));

			CharTermAttribute chartermattribute = tokenstream.addAttribute(CharTermAttribute.class);

			tokenstream.reset();

			while (tokenstream.incrementToken()) {
				// System.out.println(chartermattribute);
				
				// 將所有斷詞之結果塞入到指定的字串arraylist
				add_text_token.add(chartermattribute.toString());
				
				
				
				
			}

			// 關閉釋放
			tokenstream.close();
			analyzer.close();

		} catch (IOException e) {

			System.out.println(e);
		}



	}

	public static void main(String[] args) {

	}

}
