package com.meritamerica.assignment4;

public class TransferTransaction extends Transaction {

	public TransferTransaction() {
		// TODO Auto-generated constructor stub
	}
	
	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount){
		
	}

	@Override
	public void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		// TODO Auto-generated method stub
		
	}
}
