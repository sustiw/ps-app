package com.ps.app.service;

import java.util.List;

import com.ps.app.model.CreditCard;

public interface CreditCardService {

	public CreditCard addCreditCard(CreditCard creditCard);

	public List<CreditCard> getAllCreditCard();

}
