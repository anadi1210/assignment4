package com.meritamerica.assignment4;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MeritBank {

	private static CDOffering[] cdOffering;
	//private static AccountHolder[] accountHolders = new AccountHolder[0];
	private static List<AccountHolder> accHolderList = new ArrayList<AccountHolder>();
	private static long nextAccountNumber;
	//static int inputLineNum=0;
	

	
	
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
		System.out.println("Account holder list size : "+accHolderList.size());
	}
	
	static void resetPreviousData() {
		clearCDOfferings();
		CDOffering.clearCDOfferingArray();
		//accountHolders = new AccountHolder[0];
		accHolderList.clear();
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
					System.out.println("CD Offering ["+ i +"] created");
				}
				
				// reading line number 6
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
								accountHolder.addCheckingAccount(checkingAccount.getBalance());
								
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
										dataSplit = line.split(",");
										
										long srcAcc = Long.parseLong(dataSplit[0]);
										long tarAcc = Long.parseLong(dataSplit[1]);
										double amount = Double.parseDouble(dataSplit[2]);
										
										SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
								    	Date date = dateFormatter.parse(dataSplit[3]);
										
								    	System.out.println("point 1");
								    	// deposit/withdrawal 
								    	if(srcAcc == -1) {
								    		
								    		if(amount > 0) {
								    			System.out.println("point 2");
								    			DepositTransaction depositTr = new DepositTransaction(checkingAccount, amount);
								    			System.out.println("point 3");
								    			MeritBank.processTransaction(depositTr);
								    			
								    		}else if(amount < 0) {
								    			WithdrawTransaction withdrawTr =  new WithdrawTransaction(checkingAccount, amount);
								    			MeritBank.processTransaction(withdrawTr);
								    		}else {
								    			System.out.println("Amount 0 can not be deposited or withdraw");
								    		}
								    	}
								    	// transfer
								    	else {
								    		
								    	}
								    	System.out.println("last");
									}
								}
							}
						}
					
							
							
							
					
					
					
					
					
					
					
					
					
				}
				
				
			}
			
			reader.close();
			System.out.println("lasttttt");
			return true;
		}catch(NumberFormatException e) {
			System.out.println("Number Format exception : Message -- :" +e.getMessage() );
			return false;
		}
				
		catch(Exception e) {
			//System.out.println(e.getLocalizedMessage());
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
		
		if(transaction.getAmount() < 0 ) {
			throw new NegativeAmountException();
		}
		
		if(transaction.getAmount() > transaction.getTargetAccount().getBalance()) {
			throw new ExceedsAvailableBalanceException();
		}
		
		if(transaction.getAmount() >= 1000) {
			throw new ExceedsFraudSuspicionLimitException();
		}
		
		
		
		return false;
		
	}
	//added for assignment 4
	public static FraudQueue getFraudQueue() {
		
		return null;
		
	}
	//added for assignment 4
	public static BankAccount getBankAccount(long accountId) {
		
		return null; // if account not found
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
		return (AccountHolder[]) (accHolderList.toArray());
	}

	static void setNextAccountNumber(long nextAccountNumber) {
		//System.out.println("nextAccountNumber before setting : "+nextAccountNumber);
		MeritBank.nextAccountNumber = nextAccountNumber++;
		System.out.println("next account number set");
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
