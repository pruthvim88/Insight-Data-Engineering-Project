import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.TreeMap;

public class MedianValByDate {
	
	public static TreeMap<String,TreeMap<Date,AmountList>> mapByDate = new TreeMap<>();
	
    protected void writeToOuptutFile(String[] input) throws ParseException{
		
		String dateStr = input[13];
		DateFormat df = new SimpleDateFormat("MMddyyyy");
		Date date = df.parse(dateStr);
		
		int transAmount = Integer.parseInt(input[14]);
		
		if(mapByDate.containsKey(input[0])){
			
			TreeMap<Date,AmountList> dateMap = mapByDate.get(input[0]);
			
			if(dateMap.containsKey(date)){
				
				AmountList amountList = dateMap.get(date);
				amountList.addTransaction(transAmount);
				amountList.setSum(amountList.getSum()+transAmount);
			
				dateMap.put(date,amountList);
				
			}
			
			else{
							
				AmountList amountList = new AmountList();
				amountList.addTransaction(transAmount);
				amountList.setSum(transAmount);
						
				dateMap.put(date,amountList);		
				mapByDate.put(input[0], dateMap);
				
			}
		}
		
		else{
						
			TreeMap<Date,AmountList> dateMap = new TreeMap<>();
			
			AmountList amountList = new AmountList();
			amountList.addTransaction(transAmount);
			amountList.setSum(transAmount);
			
			dateMap.put(date,amountList);		
			mapByDate.put(input[0], dateMap);
			
		}

	}
    
    protected void getSortedDatesList(TreeMap<String,TreeMap<Date,AmountList>> mapByDate, PrintWriter pwDate){
		
		for(String id:mapByDate.keySet()){
			
			TreeMap<Date,AmountList> zipMap = mapByDate.get(id);
			
			for(Date date:zipMap.keySet()){
				
				StringBuilder outputLine = new StringBuilder(id);
				outputLine.append("|");
				
				DateFormat df = new SimpleDateFormat("MMddyyyy");

				String reportDate = df.format(date);
				
				outputLine.append(reportDate);
				outputLine.append("|");
				
				AmountList amountList = zipMap.get(date);
				
				ArrayList<Long> list = amountList.getAmountList();
				Collections.sort(list);
				
				int listSize = list.size();
				Long medAmount = 0L;
				
				if(listSize%2==0){
					medAmount = Math.round((double)(list.get(listSize/2)+list.get((listSize/2)-1))/2);
				}
				else{
				    medAmount = list.get(listSize/2);	
				}
				
				outputLine.append(medAmount);
				outputLine.append("|");
				
				outputLine.append(listSize);
				outputLine.append("|");
				
				outputLine.append(amountList.getSum());
				pwDate.println(outputLine);
	
			}	
		}
		
	}

}
