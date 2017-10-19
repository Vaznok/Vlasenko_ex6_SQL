package tasks.anagramm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {
	public static String readString() throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String inputString = reader.readLine();	    
	        return inputString;	        
		} 
	}
}
