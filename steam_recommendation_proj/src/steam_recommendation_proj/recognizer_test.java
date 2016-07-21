package steam_recommendation_proj;

import java.io.File;

import weka.classifiers.Classifier;

public class recognizer_test {

	public static void main(String[] args) {
		
		/*
		// 馬瑞斯法計算txt檔案5大人格特質分數功能
		PersonalityRecognizer p = new PersonalityRecognizer(new File("C:\\Users\\John-Wall\\Desktop\\PersonalityRecognizer\\PersonalityRecognizer.properties"));
		
		// computeScoresOverCorpus(第1個參數為要計算的txt資料夾檔案目標路徑, (第2個參數為設定預測模型 model index *0為LinearRegression 1為M5P 2為M5P-R 3為SVM*), 第2個參數為計算完畢後arff檔案輸出路徑)
		p.computeScoresOverCorpus(new File("D:\\steam_personality\\Mairesse\\txt\\review\\10"), p.loadWekaModels(2, false, true), new File("C:\\Users\\John-Wall\\Desktop\\PersonalityRecognizer\\examples\\ex.arff"));
		
		*/
		
		/*
		// 將arff檔轉換為csv檔
		Mairesse_calculate mc = new Mairesse_calculate();
				
		mc.convert_arff_to_csv("C:\\Users\\John-Wall\\Desktop\\PersonalityRecognizer\\examples\\", "C:\\Users\\John-Wall\\Desktop\\", "ex");
		*/
		
		/*
		// 將csv檔轉換為json檔
		Mairesse_calculate mc = new Mairesse_calculate();
		
		mc.convert_csv_to_json("C:\\Users\\John-Wall\\Desktop\\", "ex", "C:\\Users\\John-Wall\\Desktop\\");
        */
		
		
		
	}

}
