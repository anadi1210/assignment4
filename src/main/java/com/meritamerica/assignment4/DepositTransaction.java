package com.meritamerica.assignment4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DepositTransaction extends Transaction {

	private static List<Transaction> depositTrList = new ArrayList<Transaction>();
	
	public DepositTransaction() {
		// TODO Auto-generated constructor stub
	}
	
	DepositTransaction(BankAccount targetAccount, double amount){
		super(targetAccount,amount);
		
	}

	@Override
	public void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		// TODO Auto-generated method stub
		
	}
	
	//@Override
	public static Transaction readFromString(String transactionDataString) {
		
		return null;
	}
	
	public static void setDepositTransaction(Transaction transaction) {
		depositTrList.add(transaction);
	}
	
}
