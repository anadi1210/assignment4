package com.meritamerica.assignment4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransferTransaction extends Transaction {

	private static List<Transaction> transferTrList = new ArrayList<Transaction>();
	
	public TransferTransaction() {
		// TODO Auto-generated constructor stub
	}
	
	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount){
		super(sourceAccount,targetAccount,amount);
	}

	@Override
	public void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		// TODO Auto-generated method stub
		
	}
	
	public static void setTransferTransaction(Transaction transaction) {
		transferTrList.add(transaction);
	}
}
