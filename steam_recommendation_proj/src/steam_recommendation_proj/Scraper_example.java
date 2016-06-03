package steam_recommendation_proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Scraper_example {

	public static void main(String[] args) {

		
					// test
		            // ex: Steam_review_scraper.do_steam_review_scraper_now(String appid, int review sample size , String json file output path)
					Steam_review_scraper.do_steam_review_scraper_now("282350", 250, "C:\\Users\\John-Wall\\Desktop\\Steam_game_review\\282350.json");
					
		
			

	}

}
