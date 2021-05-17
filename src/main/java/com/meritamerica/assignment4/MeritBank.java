package com.meritamerica.assignment4;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MeritBank {

	private static CDOffering[] cdOffering;
	//private static AccountHolder[] accountHolders = new AccountHolder[0];
	private static List<AccountHolder> accHolderList = new ArrayList<AccountHolder>();
	private static long nextAccountNumber;
	private static List<BankAccount> bankAccountList = new ArrayList<BankAccount>();
	//static int inputLineNum=0;
	
	private static ArrayList<String> transactionList = new ArrayList<String>();
	private static ArrayList<String> fraudList = new ArrayList<String>();
	
	/*
	 * static void addAccountHolder(AccountHolder accountHolder) { 
	 * AccountHolder[] temp = new AccountHolder[accountHolders.length + 1]; 
	 * for(int i = 0; i <accountHolders.length;i++) 
	 * 		temp[i] = accountHolders[i];
	 *  temp[temp.length - 1] = accountHolder;
	 *  accountHolders = temp;
	 * System.out.println("Number of account holders : "+accountHolders.length); }
	 */

	static void addAccountHolder(AccountHolder accountHolder) {
		accHolderList.add(accountHolder);
		//System.out.println("Account holder list size : "+accHolderList.size());
	}
	
	static void resetPreviousData() {
		clearCDOfferings();
		CDOffering.clearCDOfferingArray();
		//accountHolders = new AccountHolder[0];
		accHolderList.clear();
		transactionList.clear();
		fraudList.clear();
		bankAccountList.clear();
	}

	//static String[] readNextLine()
 	
	
	/*
	 * Updated for assignment 4
	 * --> Should also read BankAccount transactions and the FraudQueue
	 */
	static boolean readFromFile(String fileName) {
		int inputLineNum=0;
		
		resetPreviousData();
		try {
			
			File file = new File(fileName);
			Scanner reader = new Scanner(file);
			String line;
			String[] dataSplit;
			
			while(true) {
				
				// reading line number 1
				if(reader.hasNextLine() == false) {break;}
				line = reader.nextLine(); 
				dataSplit = line.split(",");
				long accNum = Long.parseLong(dataSplit[0]);
				MeritBank.setNextAccountNumber(accNum);
				
				// reading line number 2
				if(reader.hasNextLine() == false) {break;}
				line = reader.nextLine(); 
				dataSplit = line.split(",");
				int noOfCdOfferings = Integer.parseInt(dataSplit[0]);
				
				// reading all the CD Offerings (3 lines in this case)
				for(int i=0;i<noOfCdOfferings;i++) {
					if(reader.hasNextLine() == false) {break;}
					line = reader.nextLine();
					CDOffering offering = CDOffering.readFromString(line);
					//System.out.println("CD Offering ["+ i +"] created");
				}
				
				// reading line number 6 --> Number of Account holders
				if(reader.hasNextLine() == false) {break;}
				line = reader.nextLine(); 
				dataSplit = line.split(",");
				int noOfAccountHolders = Integer.parseInt(dataSplit[0]);
				
				// Reading rest of the lines starting with account holder 1 details
				for(int i=0;i<noOfAccountHolders;i++) {
					
					if(reader.hasNextLine() == false) {break;}
					line = reader.nextLine();
					AccountHolder accountHolder = AccountHolder.readFromString(line);
					
						// reading no of checking accounts for current account holder
						if(reader.hasNextLine() == false) {break;}
						line = reader.nextLine();
						dataSplit = line.split(",");
						int noOfCheckingAccount = Integer.parseInt(dataSplit[0]); 
						
						// creating checking accounts for current account holder
						if(noOfCheckingAccount > 0) {
							for(int j=0;j<noOfCheckingAccount;j++) {
								if(reader.hasNextLine() == false) {break;}
								line = reader.nextLine();
								CheckingAccount checkingAccount = CheckingAccount.readFromString(line);
								accountHolder.addCheckingAccount(checkingAccount);
								bankAccountList.add(checkingAccount);
								
								// reading no of transactions for current checking account
								if(reader.hasNextLine() == false) {break;}
								line = reader.nextLine();
								dataSplit = line.split(",");
								int noOfCheckingAccountTransaction = Integer.parseInt(dataSplit[0]); 
								
								// creating transaction for current checking account
								if(noOfCheckingAccountTransaction > 0) {
									for(int k = 0; k < noOfCheckingAccountTransaction; k++) {
										if(reader.hasNextLine() == false) {break;}
										line = reader.nextLine();
										transactionList.add(line);
								    	//System.out.println("last");
									}
								}
							}
						}
						
						// reading no of savings accounts for current account holder	
						if(reader.hasNextLine() == false) {break;}
						line = reader.nextLine();
						dataSplit = line.split(",");
						int noOfSavingsAccount = Integer.parseInt(dataSplit[0]); 	
							
						// creating Savings accounts for current account holder
						if(noOfSavingsAccount > 0) {
							for(int j=0;j<noOfSavingsAccount;j++) {
								if(reader.hasNextLine() == false) {break;}
								line = reader.nextLine();
								SavingsAccount savingsAccount = SavingsAccount.readFromString(line);
								accountHolder.addSavingsAccount(savingsAccount);
								bankAccountList.add(savingsAccount);
								
								// reading no of transactions for current savings account
								if(reader.hasNextLine() == false) {break;}
								line = reader.nextLine();
								dataSplit = line.split(",");
								int noOfsavingsAccountTransaction = Integer.parseInt(dataSplit[0]);
								
								// creating transaction for current checking account
								if(noOfsavingsAccountTransaction > 0) {
									for(int k = 0; k < noOfsavingsAccountTransaction; k++) {
										if(reader.hasNextLine() == false) {break;}
										line = reader.nextLine();
										transactionList.add(line);
									}
								}
					
							}
						}
					
						// reading no of CD accounts for current account holder	
						if(reader.hasNextLine() == false) {break;}
						line = reader.nextLine();
						dataSplit = line.split(",");
						int noOfCDAccount = Integer.parseInt(dataSplit[0]); 	
							
						// creating CD accounts for current account holder
						if(noOfCDAccount > 0) {
							for(int j=0;j<noOfCDAccount;j++) {
								if(reader.hasNextLine() == false) {break;}
								line = reader.nextLine();
								CDAccount cdAccount = CDAccount.readFromString(line);
								accountHolder.addCDAccount(cdAccount);
								bankAccountList.add(cdAccount);
								
								// reading no of transactions for current CD account
								if(reader.hasNextLine() == false) {break;}
								line = reader.nextLine();
								dataSplit = line.split(",");
								int noOfCDAccountTransaction = Integer.parseInt(dataSplit[0]);
								
								// creating transaction for current checking account
								if(noOfCDAccountTransaction > 0) {
									for(int k = 0; k < noOfCDAccountTransaction; k++) {
										if(reader.hasNextLine() == false) {break;}
										line = reader.nextLine();
										transactionList.add(line);
									}
								}
					
							}
						}
				}
				// reading no of Fraud Queue transactions	
				if(reader.hasNextLine() == false) {break;}
				line = reader.nextLine();
				dataSplit = line.split(",");
				int noOfFraudQTransaction = Integer.parseInt(dataSplit[0]); 
				
				for(int j = 0;j<noOfFraudQTransaction;j++) {
					if(reader.hasNextLine() == false) {break;}
					line = reader.nextLine();
					fraudList.add(line);
				}
				
				//System.out.println("transactionList size : "+transactionList.size());
				for(int i = 0; i< transactionList.size(); i++) {
					String transactionLine = transactionList.get(i);
					
					dataSplit = transactionLine.split(",");
					
					long srcAccNum = Long.parseLong(dataSplit[0]);
					long tarAccNum = Long.parseLong(dataSplit[1]);
					double amount = Double.parseDouble(dataSplit[2]);
					
					SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			    	Date date = dateFormatter.parse(dataSplit[3]);
			    	
			    	// deposit/withdrawal 
			    	BankAccount sourceAccount = null;
			    	BankAccount targetaccount =null ;
			    	if(srcAccNum == -1) {
			    		targetaccount = getBankAccount(tarAccNum);
		    			sourceAccount = targetaccount;
			    		if(amount > 0) {
			    			
			    			DepositTransaction depositTr = new DepositTransaction(targetaccount, amount);
			    						    			
			    			DepositTransaction.setDepositTransaction(depositTr);
			    			try {
			    				MeritBank.processTransaction(depositTr);
			    			}catch(Exception e) {
			    				System.out.println("Exception caught in deposit");
			    			}
			    			
			    		}else if(amount < 0) {
			    			WithdrawTransaction withdrawTr =  new WithdrawTransaction(targetaccount, amount);
			    			
			    			WithdrawTransaction.setWithdrawTransaction(withdrawTr);
			    			try {
			    				withdrawTr.setAmount(Math.abs(amount));
			    				MeritBank.processTransaction(withdrawTr);
			    			}catch(Exception e) {
			    				System.out.println("Exception caught in withdraw");
			    			}
			    		}else {
			    			System.out.println("Amount 0 can not be deposited or withdraw");
			    		}
			    	}
			    	// transfer
			    	else {
			    		sourceAccount = getBankAccount(srcAccNum);
			    		targetaccount = getBankAccount(tarAccNum);
			    		
			    		TransferTransaction transferTr = new TransferTransaction(sourceAccount,targetaccount, amount);
			    		
			    		
			    		TransferTransaction.setTransferTransaction(transferTr);
			    		try {
			    			MeritBank.processTransaction(transferTr);
			    		}catch(Exception e){
			    			System.out.println("Exception caught in transfer");
			    		}
			    	}
					
				}
				
			}
			
		//	System.out.println("--------------Account Balances---------------");
			
		//	for(BankAccount ba : bankAccountList) {
		//		System.out.println(ba.getBalance());
		//	}
					
			
		//	System.out.println("--------------Account Balances---------------");
			
			reader.close();
			//System.out.println("lasttttt");
			return true;
		}catch(NumberFormatException e) {
			System.out.println("Number Format exception : Message -- :" +e.getMessage() );
			return false;
		}
				
		catch(Exception e) {
			System.out.println("Ecxception : "+e.getMessage());
			e.printStackTrace();
			return false;
		}finally {
			
		}
	}

	//added for assignment 4
	public static double recursiveFutureValue(double amount, int years, double interestRate) {
		return 0;
		
	}
	//added for assignment 4
	public static boolean processTransaction(Transaction transaction)
			throws NegativeAmountException, ExceedsAvailableBalanceException, 
						ExceedsFraudSuspicionLimitException {
		
		//System.out.println("inside process transaction");
		//System.out.println("checking amount : "+ transaction.getAmount());
		if(transaction.getAmount() < 0 ) {
			throw new NegativeAmountException();
		}
		
		
		  if(transaction.getAmount() > 1000) {
			  throw new ExceedsFraudSuspicionLimitException(); 
		  }
		 
		if(transaction instanceof DepositTransaction) {
			//System.out.println("Inside Deposit process transaction");
			//BankAccount.addTransaction(transaction);
			DepositTransaction.setDepositTransaction(transaction);
			
			BankAccount acc = transaction.getTargetAccount();
			
			acc.deposit(transaction.getAmount());
			
			return true;
		}
		if(transaction instanceof WithdrawTransaction) {
			if(transaction.getAmount() > transaction.getSourceAccount().getBalance()) {
			  throw new ExceedsAvailableBalanceException();
			}
			 
			WithdrawTransaction.setWithdrawTransaction(transaction);
			
			BankAccount acc = transaction.getTargetAccount();
			double withAmount = Math.abs(transaction.getAmount());
			acc.withdraw(withAmount);
			
			//System.out.println("Inside withdrawal process transaction");
			return true;
		}
		if(transaction instanceof TransferTransaction) {
			  if(transaction.getAmount() > transaction.getSourceAccount().getBalance()) {
				  throw new ExceedsAvailableBalanceException(); 
			  }
			TransferTransaction.setTransferTransaction(transaction);
			BankAccount srcAccount = transaction.getSourceAccount();
			BankAccount tarAccount = transaction.getTargetAccount();
			
			double transferAmount = Math.abs(transaction.getAmount());
			
			srcAccount.withdraw(transferAmount);
			tarAccount.deposit(transferAmount);
			
			//System.out.println("inside transfer");
			return true;
		}
		
		return false;
		
	}
	//added for assignment 4
	public static FraudQueue getFraudQueue() {
		
		return null;
		
	}
	//added for assignment 4
	public static BankAccount getBankAccount(long accountId) {
		BankAccount ba = bankAccountList.get((int) accountId-1);
		return ba; // if account not found
	}


	/*
	 * Updated for assignment 4
	 * --> Should also write BankAccount transactions and the FraudQueue
	 */
	static boolean writeToFile(String fileName) {
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.write("please write text");

			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	static AccountHolder[] sortAccountHolders() {
		
		/*
		 * for(int i = 0; i< accountHolders.length;i++) { for(int j = i+1;
		 * j<accountHolders.length;j++) { AccountHolder temp;
		 * if(accountHolders[i].getCombinedBalance() >
		 * accountHolders[j].getCombinedBalance()) { temp = accountHolders[i];
		 * accountHolders[i] = accountHolders[j]; accountHolders[j] = temp; } } }
		 */
		Collections.sort(accHolderList);
		
		AccountHolder[] accHolderArray = new AccountHolder[accHolderList.size()];
		accHolderList.toArray(accHolderArray);
		
		return accHolderArray;
	}

	static void setNextAccountNumber(long nextAccountNumber) {
		//System.out.println("nextAccountNumber before setting : "+nextAccountNumber);
		MeritBank.nextAccountNumber = nextAccountNumber++;
		//System.out.println("next account number set");
		//System.out.println("nextAccountNumber after setting : "+nextAccountNumber);
	}


	static AccountHolder[] getAccountHolders() {
		return (AccountHolder[]) (accHolderList.toArray());
	}


	static CDOffering[] getCDOfferings() {
		return cdOffering;
	}


	static CDOffering getBestCDOffering(double depositAmount) {
		if (cdOffering != null) {
			double bestCDoffering = 0.0;
			int index = 0;
			for(int i = 0;i<cdOffering.length;i++) {
				double currentValue = futureValue(depositAmount, cdOffering[i].getInterestRate(), cdOffering[i].getTerm());

				if(currentValue > bestCDoffering) {
					bestCDoffering = currentValue;
					index = i;
				}
			}
			return cdOffering[index];
		}
		else
			return null;
	}


	static CDOffering getSecondBestCDOffering(double depositAmount) {
		if(cdOffering != null) {
			int index = 0;
			double best = 0.0;
			double secondBest = 0.0;
			double[] currentValue = new double[cdOffering.length];
			for(int i = 0;i<cdOffering.length;i++) {
				currentValue[i] = futureValue(depositAmount, cdOffering[i].getInterestRate(), cdOffering[i].getTerm());
				if(currentValue[i] > best) {
					secondBest = best;
					best = currentValue[i];
				}else if(currentValue[i] > secondBest) {
					secondBest = currentValue[i];
				}
			}
			for(int i = 0; i<currentValue.length;i++) {
				if(currentValue[i] == secondBest)
					index = i;
			}
			return cdOffering[index];
		}
		else
			return null;
	}



	static void clearCDOfferings() {
		cdOffering = null;
	}



	static void setCDOfferings(CDOffering[] offerings) {
		if(offerings != null) {
			cdOffering = new CDOffering[offerings.length];
			for(int i = 0 ; i < offerings.length ; i++) {
				cdOffering[i] = new CDOffering(offerings[i].getTerm(), offerings[i].getInterestRate());
			}
		}
	}

	static long getNextAccountNumber() {
		return nextAccountNumber;
	}


	static double totalBalances() {

		double totalBalance = 0.0;
		for(int i = 0; i<accHolderList.size();i++) {
			totalBalance += 	accHolderList.get(i).getCheckingBalance() + 
								accHolderList.get(i).getCDBalance() + 
								accHolderList.get(i).getSavingsBalance();
		}
		return totalBalance;
	}


	static double futureValue(double presentValue, double interestRate, int term) {
		double futureAmount = presentValue * (Math.pow(1 + interestRate,term));
		return futureAmount;
	}

}
