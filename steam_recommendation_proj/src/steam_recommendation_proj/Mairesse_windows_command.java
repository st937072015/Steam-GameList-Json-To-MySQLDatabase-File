package steam_recommendation_proj;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.jdt.internal.compiler.batch.Main;

public class Mairesse_windows_command {
	public static void main(String[] args) {
    	StringBuilder command = new StringBuilder();
    	
    	command.append("@echo off\r\n");
    	command.append("set JDK_PATH=\"C:\\Program Files\\Java\\jdk1.8.0_45\"\r\n");
    	command.append("set WEKA=\"C:\\Users\\John-Wall\\Desktop\\weka-3-4\\weka.jar\"\r\n");
    	command.append("set COMMONS_CLI=\"C:\\Users\\John-Wall\\Desktop\\PersonalityRecognizer\\lib\\commons-cli-1.0.jar\"\r\n");
    	command.append("set JMRC=\"C:\\Users\\John-Wall\\Desktop\\PersonalityRecognizer\\lib\\jmrc.jar\"\r\n");
    	command.append("set LIBS=%WEKA%;%COMMONS_CLI%;%JMRC%;%CD%;bin\\\r\n");
    	command.append("%JDK_PATH%\\bin\\java -Xmx512m -classpath %LIBS% recognizer.PersonalityRecognizer %1 %2 %3 %4 %5 %6 %7 %8 %9\r\n");
    	command.append("PersonalityRecognizer -i examples -d -t 2 -m 4 -a aa.arff");
    	
    	System.out.println(command.toString());
    	
    	
		
		
        try {
        	
            int run_count = 0;
        	
           while(true){
        	
            if (run_count == 1) {
				break;
			}
        	   
        	File dir = new File("C:\\Users\\John-Wall\\Desktop\\PersonalityRecognizer");
        	Process go_process = Runtime.getRuntime().exec("C:\\Users\\John-Wall\\Desktop\\PersonalityRecognizer\\PersonalityRecognizer.bat",null,dir);
            
        	Thread.sleep(5000);
        	
        	go_process.destroy();
        	
        	run_count++;
        	
        	
        	
        	
           }
			
			
			
		}catch(IOException e) {
			System.out.println(e);
			
		} 
        catch(InterruptedException e) {
        	
           System.out.println(e);} 
           
           
           
	}
}
