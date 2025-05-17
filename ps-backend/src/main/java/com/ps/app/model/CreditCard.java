package com.ps.app.model;

import com.ps.common.PSConstants;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name = PSConstants.CC_DB_TABLE_NAME)
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	public CreditCard() {
		super();
	}

	@NotBlank(message = PSConstants._ERR_NAME)
	private String name;

	private String cardNumber;

	private double balance = 0;

	private int limit;

	public CreditCard(int id, String name, String cardNumber, double balance, int limit) {
		super();
		Id = id;
		this.name = name;
		this.cardNumber = cardNumber;
		this.balance = balance;
		this.limit = limit;
	}

	public CreditCard(String name, String cardNumber, double balance, int limit) {
		super();

		this.name = name;
		this.cardNumber = cardNumber;
		this.balance = balance;
		this.limit = limit;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber.replaceAll(" ", "");
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "CreditCard [Id=" + Id + ", name=" + name + ", cardNumber=" + cardNumber + ", balance=" + balance
				+ ", limit=" + limit + "]";
	}

}
