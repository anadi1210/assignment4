package com.meritamerica.assignment4;

import java.util.Date;

public abstract class Transaction {

	protected BankAccount sourceAccount;
	protected BankAccount targetAccount;
	protected double amount;
	
	protected Date transactionDate;
	protected boolean processedByFraudTeam;
	protected String rejectionReason;
	
	
	 
	public Transaction() {
		
	}
	
	public Transaction(BankAccount targetAccount2, double amount2) {
		this.sourceAccount = targetAccount2;
		this.targetAccount = targetAccount2;
		this.amount = amount2;
	}

	public Transaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
	}
	
	
	
	
	public abstract void process() throws NegativeAmountException, 
									ExceedsAvailableBalanceException, 	
									ExceedsFraudSuspicionLimitException;

	
	public static Transaction readFromString(String transactionDataString) {
		
		return null;
	}

	
	public String writeToString() {
		return "";
	}
	
	
	public BankAccount getSourceAccount() { return sourceAccount; } 
	public void setSourceAccount(BankAccount sourceAccount) { this.sourceAccount = sourceAccount; } 
	public BankAccount getTargetAccount() { return targetAccount; } 
	public void setTargetAccount(BankAccount targetAccount) { this.targetAccount = targetAccount; }
	public double getAmount() { return amount; } 
	public void setAmount(double amount) { this.amount = amount; }
	public Date getTransactionDate() { return transactionDate; } 
	public void setTransactionDate(Date transactionDate) { this.transactionDate = transactionDate; } 
	public boolean isProcessedByFraudTeam() { return  processedByFraudTeam; } 
	public void setProcessedByFraudTeam(boolean processedByFraudTeam) { this.processedByFraudTeam = processedByFraudTeam; }
	public String getRejectionReason() { return rejectionReason; } 
	public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
	 	
	
}
