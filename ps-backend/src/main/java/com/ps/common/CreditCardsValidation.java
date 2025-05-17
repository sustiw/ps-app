package com.ps.common;

import org.springframework.util.StringUtils;

import com.ps.app.model.CreditCard;

/**
 * This class is used for validating credit card
 * Card should be numeric only
 * Card should not be empty
 * Card min length set to 10
 * Card max length set to 19
 * Card checked using Lunh10 
 */
public class CreditCardsValidation {

	/**
	 * this method validiating credit card number
	 * @param creditCard
	 * @return
	 */
	public static ResponseMessage validateCreditCard(CreditCard creditCard) {
		ResponseMessage responseMessage = null;

		String creditCardNumber = creditCard.getCardNumber();

		if (StringUtils.isEmpty(creditCardNumber)) {
			responseMessage = new ResponseMessage(PSConstants.FAILED, PSConstants._ERR_CARD_EMPTY);
		} else if (!creditCardNumber.matches("[0-9]+")) {
			responseMessage = new ResponseMessage(PSConstants.FAILED, PSConstants._ERR_CARD_NON_NUMERIC);
		} else if (creditCardNumber.length() < PSConstants.CARD_MIN_LENGTH) {
			responseMessage = new ResponseMessage(PSConstants.FAILED, PSConstants._ERR_CARD_MIN_LENGTH);
		} else if (creditCardNumber.length() > PSConstants.CARD_MAX_LENGTH) {
			responseMessage = new ResponseMessage(PSConstants.FAILED, PSConstants._ERR_CARD_MAX_LENGTH);
		} else if (!lunh10Check(creditCardNumber))
			responseMessage = new ResponseMessage(PSConstants.FAILED, PSConstants._ERR_CARD_LUHN_CHECK_FAILED);
		return responseMessage;

	}

	/**
	 * this method is verify
	 * @param cardNumber
	 * @return
	 */
	private static boolean lunh10Check(String cardNumber) {
		if (cardNumber.length() > 0) {
			int sum = 0;
			boolean check = false;
			for (int i = cardNumber.length() - 1; i >= 0; i--) {
				if (!check) {
					char ch = cardNumber.charAt(i);
					sum = sum + Integer.parseInt(String.valueOf(ch));
					check = true;
					continue;
				}

				else {
					int a = Integer.parseInt(String.valueOf(cardNumber.charAt(i)));
					int c = a * 2;
					if (c >= 10) {
						int d = 0;
						for (int j = String.valueOf(c).length() - 1; j >= 0; j--) {
							char ch = String.valueOf(c).charAt(j);
							d = d + Integer.parseInt(String.valueOf(ch));
						}
						sum = sum + d;

					} else {
						sum = sum + c;
					}
					check = false;
					continue;
				}
			}

			if (sum % 10 == 0) {
				return true;
			}
		}
		return false;
	}

}
