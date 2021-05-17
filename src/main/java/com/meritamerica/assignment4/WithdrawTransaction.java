package com.meritamerica.assignment4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WithdrawTransaction extends Transaction {

	private static List<Transaction> withdrawTrList = new ArrayList<Transaction>();
	
	public WithdrawTransaction() {
		// TODO Auto-generated constructor stub
	}
	
	WithdrawTransaction(BankAccount targetAccount, double amount){
		super(targetAccount,amount);
	}

	@Override
	public void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		// TODO Auto-generated method stub
		
	}
	
	public static void setWithdrawTransaction(Transaction transaction) {
		withdrawTrList.add(transaction);
	}
}
