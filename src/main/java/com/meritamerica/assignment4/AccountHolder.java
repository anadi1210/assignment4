package com.meritamerica.assignment4;

import java.util.Arrays;

public class AccountHolder implements Comparable<AccountHolder>{

	protected String firstName;
	protected String middleName;
	protected String lastName;
	protected String SSN;
	
	protected CheckingAccount[] checkingAccountArray = new CheckingAccount[0];
	protected SavingsAccount[] savingsAccountArray = new SavingsAccount[0];
	protected CDAccount[] cdAccountArray = new CDAccount[0];
	protected AccountHolder[] accounHolders;
	protected CheckingAccount checkingAccount;
	protected SavingsAccount savingsAccount;
	protected CDAccount cdAccount;
	
	static int counter=0;
	public AccountHolder() {
	
	}

	AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.SSN = ssn;
	}


	
	  @Override 
	  public int compareTo(AccountHolder otherAccountHolder) {
	 // if(this.ba) 
		  return 0; 
	  }
	 

	

	static AccountHolder readFromString(String accountHolderData) throws Exception{
		
		try {
			counter++;		
			String[] dataSplit = accountHolderData.split(",");
			String fName = dataSplit[0];
			String mName = dataSplit[1];
			String lName = dataSplit[2];
			String ssn = dataSplit[3];
			AccountHolder accountHolder = new AccountHolder(fName,mName,lName,ssn);
			System.out.println("Account holder added -- details : "+accountHolder.firstName +" "+ accountHolder.lastName + " "+ accountHolder.SSN);
			MeritBank.addAccountHolder(accountHolder);
			//System.out.println("account holder call number : "+counter);
			
			return accountHolder;
		}catch(Exception e) {
			throw new java.lang.NumberFormatException();
		}
		
		
	}
	
	/*
	 * Updated for Assignment 4
	 * 	1. If combined balance limit is exceeded, throw ExceedsCombinedBalanceLimitException
		2. Should also add a deposit transaction with the opening balance
	 */
	  CheckingAccount addCheckingAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException{ 
		  checkingAccount = new CheckingAccount(openingBalance);
		  addCheckingAccount(checkingAccount);
		  return checkingAccount;
	  
	  }

	  	/*
		 * Updated for Assignment 4
		 * 	1. If combined balance limit is exceeded, throw ExceedsCombinedBalanceLimitException
			2. Should also add a deposit transaction with the opening balance
		 */
	  CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) throws ExceedsCombinedBalanceLimitException{
		double checkingAndSavingsBalance  = getCheckingBalance() + getSavingsBalance() + checkingAccount.getBalance();
		if(checkingAndSavingsBalance < 250000) {
			
			CheckingAccount[] temp = new CheckingAccount[this.checkingAccountArray.length + 1];
			for(int i = 0; i<checkingAccountArray.length; i++)
				temp[i] = this.checkingAccountArray[i];
			temp[temp.length - 1] = checkingAccount;
			this.checkingAccountArray = temp;
			return checkingAccount;
		}
		else {
			System.out.println("Checking Account can not be created, total account balance exceeds $250,000");
			throw new ExceedsCombinedBalanceLimitException();
			//return null;
		}
	}
	CheckingAccount[] getCheckingAccounts() {
		
		return checkingAccountArray;
		
	}

	int getNumberOfCheckingAccounts() {
		return checkingAccountArray.length;
	}
	double getCheckingBalance() {
		double checkingBalance = 0.0;
		
		if(checkingAccountArray != null) {
			for(int i = 0 ; i < checkingAccountArray.length ; i++) {
				checkingBalance += checkingAccountArray[i].getBalance();
				
			}
		}
		return checkingBalance;
	}
	
	/*
	 * Updated for Assignment 4
	 * 	1. If combined balance limit is exceeded, throw ExceedsCombinedBalanceLimitException
		2. Should also add a deposit transaction with the opening balance
	 */
	SavingsAccount addSavingsAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException{
		savingsAccount =  new SavingsAccount(openingBalance);
		addSavingsAccount(savingsAccount); 
		return	savingsAccount;

	}

	/*
	 * Updated for Assignment 4
	 * 	1. If combined balance limit is exceeded, throw ExceedsCombinedBalanceLimitException
		2. Should also add a deposit transaction with the opening balance
	 */
	SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) throws ExceedsCombinedBalanceLimitException{
		if((getCheckingBalance() + getSavingsBalance() + savingsAccount.getBalance()) < 250000) {
			SavingsAccount[] temp = new SavingsAccount[this.savingsAccountArray.length + 1];
			for(int i = 0; i<savingsAccountArray.length; i++)
				temp[i] =this.savingsAccountArray[i];
			
			temp[temp.length - 1] = savingsAccount;
			this.savingsAccountArray = temp;
			return savingsAccount;
		}
		else {
			System.out.println("Savings Account can not be created, total account balance exceeds $250,000");
			throw new ExceedsCombinedBalanceLimitException();
			//return null;
		}
	}
	SavingsAccount[] getSavingsAccounts() {
		
		return savingsAccountArray;
	}
	int getNumberOfSavingsAccounts() {
		return savingsAccountArray.length;
	}
	double getSavingsBalance() {
		double savingsBalance = 0.0;
		if(savingsAccountArray != null) {
			for(int i = 0 ; i < savingsAccountArray.length ; i++) {
				savingsBalance += savingsAccountArray[i].getBalance();
				
			}
		}
		return savingsBalance;
	}
	
	/*
	 * Updated for Assignment 4
		1. Should add a deposit transaction with the opening balance
	 */
	CDAccount addCDAccount(CDOffering offering, double openingBalance) {
		cdAccount = new CDAccount(offering, openingBalance);
		addCDAccount(cdAccount);
		return cdAccount;
	}
	
	/*
	 * Updated for Assignment 4
	 *
		1. Should add a deposit transaction with the opening balance
	 */
	CDAccount addCDAccount(CDAccount cdAccount) {
		
		CDAccount[] temp = new CDAccount[this.cdAccountArray.length + 1];
		for(int i = 0; i<cdAccountArray.length; i++)
			temp[i] = this.cdAccountArray[i];
		temp[temp.length - 1] = cdAccount;
		this.cdAccountArray = temp;
		
		return cdAccount;
		
	}
	CDAccount[] getCDAccounts() {
		
		return cdAccountArray;
	}
	int getNumberOfCDAccounts() {
		return cdAccountArray.length;
	}
	double getCDBalance() {
		double cdBalance = 0.0;
		
		if(cdAccountArray != null) {
			for(int i = 0 ; i < cdAccountArray.length ; i++) {
				cdBalance += cdAccountArray[i].getBalance();
				
			}
		}
		return cdBalance;
	}
	double getCombinedBalance() {
		double combinedBalance =
					getCheckingBalance() + getSavingsBalance() + getCDBalance();
		
		return combinedBalance;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String ssn) {
		this.SSN = ssn;
	}


}