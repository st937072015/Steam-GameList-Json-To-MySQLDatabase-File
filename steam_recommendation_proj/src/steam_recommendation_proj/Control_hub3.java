package steam_recommendation_proj;

public class Control_hub3 {

	public static void main(String[] args) {

	  
       Lucene_simple_analyzer go_analyzer= new Lucene_simple_analyzer();
		
		for (int i = 0; i < go_analyzer.use_simpleanalyzer("you are a genius. five").size(); i++) {
			
			System.out.println(go_analyzer.use_simpleanalyzer("you are a genius. five").get(i));
			
		}	
		
		
		
	}

}
