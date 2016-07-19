package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Control_hub22_2 {

	public static void main(String[] args) {

		

			

		      Mairesse_calculate go_calculate = new Mairesse_calculate();
			


	            for (int i = 1; i < 328973; i++) {
					
				
           

				
				go_calculate.json_convert_text_file("C:\\Users\\John-Wall\\Desktop\\Steam_user_review\\", "steam_user_respective_review", String.valueOf(i), "D:\\steam_personality\\Mairesse\\user_review\\");
				
				

              }
			
			
           
			
		
			
			 
			

		
		
	}

}
