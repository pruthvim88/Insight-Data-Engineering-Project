import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MedianValByZip {
	
	public static HashMap<String,HashMap<String,AmountList>> mapByZip = new HashMap<>();
	
	protected String writeToOuptutFile(String[] input){
	
		StringBuilder outputLine = new StringBuilder(input[0]);
		outputLine.append("|");
		
		String zipCode = input[10].substring(0, 5);
		outputLine.append(zipCode);
		outputLine.append("|");
		
		int transAmount = Integer.parseInt(input[14]);
		
		if(mapByZip.containsKey(input[0])){
			
			HashMap<String,AmountList> zipMap = mapByZip.get(input[0]);
			
			if(zipMap.containsKey(zipCode)){
				
				AmountList amountList = zipMap.get(zipCode);
				amountList.addTransaction(transAmount);
				amountList.setSum(amountList.getSum()+transAmount);
				
				ArrayList<Long> list = amountList.getAmountList();
				Collections.sort(list);
				
				int listSize = list.size();
				long medAmount = 0;
				
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
				
				zipMap.put(zipCode,amountList);
				
			}
			
			else{
							
				AmountList amountList = new AmountList();
				amountList.addTransaction(transAmount);
				amountList.setSum(transAmount);
				
				outputLine.append(transAmount);
				outputLine.append("|");
				
				outputLine.append(1);
				outputLine.append("|");
				
				outputLine.append(transAmount);
				
				zipMap.put(zipCode,amountList);		
				mapByZip.put(input[0], zipMap);
				
			}
		}
		
		else{
						
			HashMap<String,AmountList> zipMap = new HashMap<>();
			
			AmountList amountList = new AmountList();
			amountList.addTransaction(transAmount);
			amountList.setSum(transAmount);
			
			outputLine.append(transAmount);
			outputLine.append("|");
			
			outputLine.append(1);
			outputLine.append("|");
			
			outputLine.append(transAmount);
			
			zipMap.put(zipCode,amountList);		
			mapByZip.put(input[0], zipMap);
			
		}
		
		return outputLine.toString();
		
	}

}
