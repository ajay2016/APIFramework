package com.automation.APIautomationFramework.pojo;

public class PurchaseUnits {
	
	private Amount amount;
	
	
	
	//purchase units contains currency-code and value
	public PurchaseUnits(String currency_code, String value) {	
		
		//get from Amount object.
		this.amount = new Amount(currency_code, value);
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}
	
	

}
