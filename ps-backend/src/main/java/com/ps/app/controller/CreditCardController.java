package com.ps.app.controller;

import java.util.List;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ps.app.model.CreditCard;
import com.ps.app.service.CreditCardService;
import com.ps.common.CreditCardsValidation;
import com.ps.common.PSConstants;
import com.ps.common.ResponseMessage;

import jakarta.validation.Valid;

/**
 * this class is main controller which recieve request To add new credit cards
 * where details provided in json To get all cards already saved
 */

@RestController
@RequestMapping(PSConstants.CREDITCARDS_APIENPOINTS)
public class CreditCardController {

	private Logger logger = LoggerFactory.getLogger(CreditCardController.class);

	@Autowired
	private CreditCardService creditCardService;

	@PostMapping(PSConstants.ADD_CREDITCARDS_APIENPOINTS)

	public ResponseEntity<ResponseMessage> addNewCreditCard(@Valid @RequestBody CreditCard creditCard,
			BindingResult bindResult) {
		String methodName = "addNewCreditCard";
		logger.trace("entry", methodName, Level.INFO);
		ResponseMessage message = null;

		try {

			if (bindResult.hasErrors()) {
				message = new ResponseMessage(bindResult.getAllErrors().get(0).getCode(),
						bindResult.getAllErrors().get(0).getDefaultMessage());

				return new ResponseEntity<ResponseMessage>(message, HttpStatus.BAD_REQUEST);

			} else {
				message = CreditCardsValidation.validateCreditCard(creditCard);
				if (null != message) {
					logger.trace("error", methodName, Level.WARNING);

					return new ResponseEntity<ResponseMessage>(message, HttpStatus.BAD_REQUEST);
				}
				CreditCard savedCard = creditCardService.addCreditCard(creditCard);
				if (null == savedCard) {
					message = new ResponseMessage(PSConstants.FAILED, PSConstants._ERR_CARD_ALREADY_ADDED);
					logger.trace("error", methodName, Level.WARNING);

					return new ResponseEntity<ResponseMessage>(message, HttpStatus.BAD_REQUEST);
				}
				message = new ResponseMessage(PSConstants.SUCCESS, PSConstants.CARD_ADDED_SUCCESS);
				logger.trace("exit", methodName, Level.INFO);
				return new ResponseEntity<ResponseMessage>(message, HttpStatus.OK);
			}
		} catch (Exception e) {
			message = new ResponseMessage(PSConstants.FAILED, PSConstants._ERR_CARD_ADDED_FAILED);
			logger.trace("error", methodName, Level.SEVERE);
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(PSConstants.GETALL_API_ENDPOINT)
	@ResponseBody
	public ResponseEntity<List<CreditCard>> getAllCreditCards() {
		String methodName = "getAllCreditCards";
		logger.trace("entry", methodName, Level.INFO);
		List<CreditCard> crditCardList = creditCardService.getAllCreditCard();
		logger.trace("exit", methodName, Level.INFO);

		return new ResponseEntity<List<CreditCard>>(crditCardList, HttpStatus.OK);

	}

}
