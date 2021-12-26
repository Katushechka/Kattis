import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class TrackingEmployees {
	static String filename = "file/log.txt";	
	
	public static void main(String[] args) throws IOException  {
		Hashtable <String, String> hashTab = new Hashtable<String, String>();
		
		try (BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(filename),"ISO-8859-1"))) {			
				String row = br.readLine();
				String type;
				String name;
				while(row != null) {
					
					for(int i = 0; i<row.length(); i++) {
						if(row.charAt(i) == ' ') {
							type = row.substring(0, i);
							name = row.substring(i+1, row.length());																				
							
							// Write to HashTable for the first time
					        if (!hashTab.containsKey(name) && !type.equals("exit")) {
					        	hashTab.put(name, type);
					        	System.out.println(name + " " + "entered");
					        	break;
					        }
					        
					        // Key does not exist, but user exited
							if (!hashTab.containsKey(name) && type.equals("exit")) {
					        	System.out.println(name + " " + "exited" + " (ANOMALY)");
					        	break;
					        }
					        
					       // If key exist 
					        else {
					        	String action = hashTab.get(name);		//entry or exit
					        	
					        	// if the key exist and if valus different -> write new value
					        	if(!action.equals(type)) {				
					        		hashTab.put(name, type);
					        		if(type.equals("entry")){
					        			System.out.println(name + " " + "entered");
					        		}
					        		else {
					        			System.out.println(name + " " + "exited");
					        		}

					        		
					        	}
					        	// if the key exist and if valus same -> ANOMALY
					        	else {
					        		if(type.equals("entry")){
					        			System.out.println(name + " " + "entered (ANOMALY)");
					        		}
					        		else {
					        			System.out.println(name + " " + "exited (ANOMALY)");
					        		}
					        	}					        	
					        }					        						
						}
					}
					row = br.readLine();
				}		
		}	
	}
}
