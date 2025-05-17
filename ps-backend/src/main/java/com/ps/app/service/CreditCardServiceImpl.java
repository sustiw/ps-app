package com.ps.app.service;

import java.util.List;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.app.dao.CreditCardDaoService;
import com.ps.app.model.CreditCard;

/**
 * This class will connect to Database to perform db transaction. Save new
 * credit card with name, limit and credit card number Fetch all credit cards
 * saved in current credit_cards table
 */

@Service
public class CreditCardServiceImpl implements CreditCardService {
	private Logger logger = LoggerFactory.getLogger(CreditCardServiceImpl.class);

	@Autowired
	private CreditCardDaoService creditCardDao;

	/**
	 * This method is used for saving new credit cards .
	 */
	@Override
	public CreditCard addCreditCard(CreditCard creditCard) {
		String methodName = "addCreditCard";
		logger.trace("entry", methodName, Level.INFO);
		List<CreditCard> listOfCreditCard = creditCardDao.findAll();
		for (CreditCard cc : listOfCreditCard) {
			if (cc.getCardNumber().trim().equalsIgnoreCase(creditCard.getCardNumber().trim())) {
				return null;
			}
		}
		CreditCard savedCreditCard = creditCardDao.save(creditCard);
		logger.trace("exit", methodName, Level.INFO);

		return savedCreditCard;
	}

	/**
	 * this method will fetch all saved credit cards.
	 */

	@Override
	public List<CreditCard> getAllCreditCard() {
		String methodName = "getAllCreditCard";
		logger.trace(methodName, Level.INFO);
		return creditCardDao.findAll();
	}

}
