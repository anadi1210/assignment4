package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BankAccount {

	protected long accountNumber;
	protected double balance;
	protected double interestRate;
	
	protected Date openedOn;
	
	//added for assignment 4
	protected List<Transaction> transactionList = new ArrayList<Transaction>();

	
	//private Date openedOn;
	
	
	public BankAccount() {
		
	}
	
	
	BankAccount(double balance, double interestRate){
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountNumber = MeritBank.getNextAccountNumber();
		
	}

	BankAccount(double balance, double interestRate, 
					Date accountOpenedOn) {
		this.balance = balance;
		this.interestRate = interestRate;
		this.openedOn = accountOpenedOn;
		this.accountNumber = MeritBank.getNextAccountNumber();
	}
	BankAccount(long accountNumber, double balance, 
			double interestRate, Date accountOpenedOn) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
		this.openedOn = accountOpenedOn;
	}
	
	
	
	
	
	
	
	
	/*
	 * Date accountOpenedOn() {
	 * 
	 * }
	 */
	
	static BankAccount readFromString(String accountData)
			throws ParseException {
		BankAccount bankAccount = null;
		try {
			
			
			
			return bankAccount;
		}catch (Exception e) {
			 throw new java.lang.NumberFormatException();
		}
	}
	
	String writeToString() {
		
		return "";
	}
	
	//added for assignment 4
	public void addTransaction(Transaction transaction) {
		
	}

	//added for assignment 4
	public List<Transaction> getTransactions(){
		
		return transactionList;
	}
	
	//added for assignment 4
	
	
	

	public Date getOpenedOn() {
		
		return openedOn;
	}

	public void setOpenedOn(Date openedOn) {
			
		this.openedOn = openedOn;
	}

	boolean withdraw(double amount) {
		
		if(amount != 0 && amount <= this.balance) {
			this.balance -= amount;
			return true;
		}
		
		return false;
	}
	boolean deposit (double amount) {
		
		if(amount > 0.0)
		{
			this.balance += amount;
			return true;
		}
		return false;
	}
	double futureValue(int years) {
		double futureBalance = 0.0;
		
		return futureBalance;
	}
	
	
	public long getAccountNumber() {
		return this.accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
}
