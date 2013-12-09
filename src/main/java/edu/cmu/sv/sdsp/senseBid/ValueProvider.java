package main.java.edu.cmu.sv.sdsp.senseBid;

public interface ValueProvider  {
	
	public void onTask(String str);
	public void onCreditHist(String credit);
	public void onTaskHistory(String prevWinner);
	
//	String currentWinner;
//	String credit;
//	String prevWinner;
//	public String getCurrentWinner() {
//		return currentWinner;
//	}
//	public void setCurrentWinner(String currentWinner) {
//		this.currentWinner = currentWinner;
//	}
//	public String getCredit() {
//		return credit;
//	}
//	public void setCredit(String credit) {
//		this.credit = credit;
//	}
//	public String getPrevWinner() {
//		return prevWinner;
//	}
//	public void setPrevWinner(String prevWinner) {
//		this.prevWinner = prevWinner;
//	}
	
	

}
