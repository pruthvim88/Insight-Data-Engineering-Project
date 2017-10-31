import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

public class Driver {
	
	public static void main(String [] args){
		
		String file = args[0];

		try {
			
			PrintWriter pwZip = new PrintWriter(new File(args[1]));
			PrintWriter pwDate = new PrintWriter(new File(args[2]));
			
			try {
				
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				
				MedianValByZip medZip = new MedianValByZip();
				MedianValByDate medDate = new MedianValByDate();
				
				try {
					
					String currLine;
					String[] input = null;
					
					while((currLine=br.readLine()) != null){
						
						input = currLine.split("\\|");
						
						if(input[15].equals("")){
							
							if(input[10].length()>=5 && input[10].substring(0,5).matches("\\d+")){
				
								String zipLine = medZip.writeToOuptutFile(input);
								pwZip.println(zipLine);
								
							}
						
							try {
								//Check if the date is valid and the dates are not in the future
								if(!input[13].equals("") && input[13].length()==8 && Integer.parseInt(input[13].substring(4, 8))<2018)
									medDate.writeToOuptutFile(input);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								System.out.println("Invalid Date!!");
							}
							
						}		
						
					}
					
					medDate.getSortedDatesList(MedianValByDate.mapByDate, pwDate);
					
					fr.close();
					br.close();
					pwZip.close();
					pwDate.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.out.println("Input file not found!!!");
			}
			
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			System.out.println("Output file not found!!!");
		}
		
	}
	
	
}
