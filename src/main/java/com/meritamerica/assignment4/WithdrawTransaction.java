package com.meritamerica.assignment4;

public class WithdrawTransaction extends Transaction {

	public WithdrawTransaction() {
		// TODO Auto-generated constructor stub
	}
	
	WithdrawTransaction(BankAccount targetAccount, double amount){
		
	}

	@Override
	public void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		// TODO Auto-generated method stub
		
	}
}