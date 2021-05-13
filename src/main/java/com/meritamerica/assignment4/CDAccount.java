package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class CDAccount extends BankAccount{

	protected int term;

	static int counter;
	
	CDOffering cdOffering;
	
	public CDAccount() {
		super();
	}
	
	CDAccount(CDOffering offering, double balance){
		this.cdOffering = offering;
		super.balance = balance;
		super.interestRate = offering.getInterestRate();
		this.term = offering.getTerm();
	}
	
	
	CDAccount(long accountNumber,double balance,
			CDOffering offering,Date accountOpenedOn){
		
		super(accountNumber,balance,offering.getInterestRate(),accountOpenedOn);
		this.term = offering.getTerm();
		
	}
	
	

	
	static CDAccount readFromString(String accountData){
		try {
			counter++;		
			String[] dataSplit = accountData.split(",");
			
			
			long accountNumber = Long.parseLong(dataSplit[0]);
			double balance = Double.parseDouble(dataSplit[1]);
			double interestRate = Double.parseDouble(dataSplit[2]);
			String dateString = dataSplit[3];
			int term = Integer.parseInt(dataSplit[4]);
			
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	    	Date date = dateFormatter.parse(dateString);
			
	    	CDOffering cdOffering = new CDOffering(term, interestRate);
			
	    	CDAccount cdAccount = new CDAccount(accountNumber,balance,cdOffering,date);
			//System.out.println("CDAccount call number : "+counter);
			
			return cdAccount;
		}catch(Exception e) {
			throw new java.lang.NumberFormatException();
		}
	}
	
	
	
	@Override
	boolean deposit(double amount) {
		
		return false;
	}
	
	@Override
	boolean withdraw(double amount) {
		
		return false;
	}
	
	

	Date getStartDate(){
		
		return null;
	}
	
	double futureValue() {
		double futureAmount = 0;
		futureAmount = this.getBalance() * Math.pow((1+this.getInterestRate()), this.getTerm());
		return futureAmount;
		
	}
	
	
	  public int getTerm() { return term; }
	  
	  public void setTerm(int term) { this.term = term; }
	 

}
