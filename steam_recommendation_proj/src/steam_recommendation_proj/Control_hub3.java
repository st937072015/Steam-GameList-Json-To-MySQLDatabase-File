package steam_recommendation_proj;

public class Control_hub3 {

	public static void main(String[] args) {

	  
       Lucene_simple_analyzer go_analyzer= new Lucene_simple_analyzer();
		
       
       if (go_analyzer.use_simpleanalyzer("it is a worker").isEmpty()) {
		
    	   System.out.print("空值");
    	   
    	   
	}else{
       
       
       
       
		for (int i = 0; i < go_analyzer.use_simpleanalyzer("it is a worker").size(); i++) {
			
			System.out.println(go_analyzer.use_simpleanalyzer("it is a worker").get(i));
			
		}	
		
		
		}
	}

}
