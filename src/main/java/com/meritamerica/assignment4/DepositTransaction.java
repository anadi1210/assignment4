package com.meritamerica.assignment4;

public class DepositTransaction extends Transaction {

	
	
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
	
	
}
