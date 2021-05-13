package com.meritamerica.assignment4;

import java.text.ParseException;

public class CDOffering {

	private int term;
	private double interestRate;
	
	protected static CDOffering[] cdOfferingArray = new CDOffering[0];
	public CDOffering() {
		
	}
	
	CDOffering(int term, double interestRate) {
		this.term = term;
		this.interestRate = interestRate;
	}
	
	static CDOffering readFromString(String cdOfferingDataString){
		int counter=0;
		try {
			counter++;		
			String[] dataSplit = cdOfferingDataString.split(",");
			int term = Integer.parseInt(dataSplit[0]);
			double interestRate = Double.parseDouble(dataSplit[1]);
			CDOffering offering = new CDOffering(term,interestRate);
			setCDOfferingArray(offering);
			//System.out.println("CDOffering call number : "+counter);
			
			return offering;
		}catch(Exception e) {
			throw new java.lang.NumberFormatException();
		}
		
	}
	
	static void setCDOfferingArray(CDOffering offering) {
		CDOffering[] temp = new CDOffering[cdOfferingArray.length + 1];
		for(int i = 0; i< cdOfferingArray.length;i++) {
			temp[i] = cdOfferingArray[i];
		}
		temp[temp.length -1] = offering;
		cdOfferingArray = temp;
		MeritBank.setCDOfferings(cdOfferingArray);
	}
	
	static void clearCDOfferingArray() {
		cdOfferingArray = new CDOffering[0];
	}
	
	
	String writeToString() {
		
		return "";
	}
	
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	

}
