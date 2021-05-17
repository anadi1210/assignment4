package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SavingsAccount extends BankAccount{

	static int counter=0;
	protected static double savingsInterestRate = 0.01;
	public SavingsAccount() {
		super();
	}
	
	SavingsAccount(double openingBalance){
		this(openingBalance,savingsInterestRate);
	}
	
	SavingsAccount(double balance, double interestRate){
		super(balance,interestRate);
	}
	
	SavingsAccount(double balance, double interestRate, 
			Date accountOpenedOn) {
		super(balance,interestRate,accountOpenedOn);
	}
	SavingsAccount(long accountNumber, double balance, 
			double interestRate, Date accountOpenedOn) {
		super(accountNumber,balance,interestRate,accountOpenedOn);
	}
		
	static SavingsAccount readFromString(String accountData) throws ParseException{
		int counter=0;
		try {
			counter++;		
			String[] dataSplit = accountData.split(",");
			
			
			long accountNumber = Long.parseLong(dataSplit[0]);
			double balance = Double.parseDouble(dataSplit[1]);
			double interestRate = Double.parseDouble(dataSplit[2]);
			String dateString = dataSplit[3];
			
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	    	Date date = dateFormatter.parse(dateString);
			//dateFormatter.format(date);
			
	    	SavingsAccount savingsAccount = new SavingsAccount(accountNumber,balance,interestRate,date);
			//System.out.println("SavingsAccount call number : "+counter);
			//System.out.println("Date : "+dateFormatter.format(date));
			return savingsAccount;
		}catch(Exception e) {
			throw new java.lang.NumberFormatException();
		}
		
		
	}


	// This method  will receive the amount to be withdrawn and will return boolean value as output
	boolean withdraw(double amount) {
		return super.withdraw(amount);
	}
	
	// This method  will receive the amount to be deposited and will return boolean value as output.
	boolean deposit(double amount) {
		return super.deposit(amount);
	}
	
	@Override
	double futureValue(int years) {
		double futureAmount = 0;
		futureAmount = this.getBalance() * Math.pow((1+savingsInterestRate), years);
		return futureAmount;
	}
	
}