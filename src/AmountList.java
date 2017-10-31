import java.util.ArrayList;

public class AmountList {
	
	//Maintaining the current sum of the list to retrieve the sum in O(1)
	private long sum;
	private ArrayList<Long> amountList;
	
	public AmountList(){
		amountList = new ArrayList<>();
	}
	
	public long getSum(){
		return this.sum;
	}
	
	public ArrayList<Long> getAmountList(){
		return this.amountList;
	}
	
	public void setSum(long sum){
		this.sum = sum;
	}
	
	public void addTransaction(long amount){
		amountList.add(amount);
	}

}
