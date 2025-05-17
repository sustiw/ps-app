package com.ps.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;

import com.ps.app.model.CreditCard;
import com.ps.common.PSConstants;
import com.ps.common.ResponseMessage;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class CreditCardControllerTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private CreditCardController CreditcardController;

	@Autowired
	private CreditCardController CreditcardController1;
	@Resource
	private Validator validator;


	@Test
	public void TestAddNewCreditCardSuccess() {

		CreditCard testCard = new CreditCard("test1", "173778869372005", 0, 1000);
		MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/creditCards/add");
		BindingResult bindResult = bindAndValidate(mockRequest, testCard);
		ResponseEntity<ResponseMessage> response = CreditcardController.addNewCreditCard(testCard, bindResult);
		ResponseMessage responsebody = (ResponseMessage) response.getBody();
		assertEquals(PSConstants.CARD_ADDED_SUCCESS, responsebody.getDetails());
	}

	@Test
	public void TestAddNewCreditCardAlreadyPresent() {
		CreditCard testCard1 = new CreditCard("test1", "177738869372005", 0, 1000);
		MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/creditCards/add");
		BindingResult bindResult = bindAndValidate(mockRequest, testCard1);
		CreditcardController.addNewCreditCard(testCard1, bindResult);

		CreditCard testCard2 = new CreditCard("test1", "177738869372005", 0, 1000);
		MockHttpServletRequest mockRequest2 = new MockHttpServletRequest("POST", "/creditCards/add");
		BindingResult bindResult2 = bindAndValidate(mockRequest, testCard1);
		ResponseEntity response = CreditcardController.addNewCreditCard(testCard1, bindResult);

		ResponseMessage responsebody = (ResponseMessage) response.getBody();
		assertEquals(PSConstants._ERR_CARD_ALREADY_ADDED, responsebody.getDetails());
	}

	@Test
	public void TestAddNewCreditCardInvlidCardLunh10() {
		CreditCard testCard = new CreditCard("test2", "173778869372004", 0, 1000);
		MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/creditCards/add");
		BindingResult bindResult = bindAndValidate(mockRequest, testCard);
		ResponseEntity response = CreditcardController.addNewCreditCard(testCard, bindResult);
		ResponseMessage responsebody = (ResponseMessage) response.getBody();
		assertEquals(PSConstants._ERR_CARD_LUHN_CHECK_FAILED, responsebody.getDetails());
	}

	@Test
	public void TestAddNewCreditCardEmptyCard() {
		CreditCard testCard = new CreditCard("test3", "", 0, 1000);
		MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/creditCards/add");
		BindingResult bindResult = bindAndValidate(mockRequest, testCard);
		ResponseEntity response = CreditcardController.addNewCreditCard(testCard, bindResult);
		ResponseMessage responsebody = (ResponseMessage) response.getBody();
		assertEquals(PSConstants._ERR_CARD_EMPTY, responsebody.getDetails());
	}

	@Test
	public void TestAddNewCreditCardMinLengthFailed() {
		CreditCard testCard = new CreditCard("test3", "17377886", 0, 1000);
		MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/creditCards/add");
		BindingResult bindResult = bindAndValidate(mockRequest, testCard);
		ResponseEntity response = CreditcardController.addNewCreditCard(testCard, bindResult);
		ResponseMessage responsebody = (ResponseMessage) response.getBody();
		assertEquals(PSConstants._ERR_CARD_MIN_LENGTH, responsebody.getDetails());
	}

	@Test
	public void TestAddNewCreditCardEmptyName() {
		CreditCard testCard = new CreditCard("", "173778869372005", 0, 1000);
		MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/creditCards/add");
		BindingResult bindResult = bindAndValidate(mockRequest, testCard);
		ResponseEntity response = CreditcardController.addNewCreditCard(testCard, bindResult);
		ResponseMessage responsebody = (ResponseMessage) response.getBody();
		assertEquals(PSConstants._ERR_NAME, responsebody.getDetails());
	}

	@Test
	public void TestAddNewCreditCardMorethen19Char() {
		CreditCard testCard = new CreditCard("test5", "173778869372004343424", 0, 1000);
		MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/creditCards/add");
		BindingResult bindResult = bindAndValidate(mockRequest, testCard);
		ResponseEntity response = CreditcardController.addNewCreditCard(testCard, bindResult);
		ResponseMessage responsebody = (ResponseMessage) response.getBody();
		assertEquals(PSConstants._ERR_CARD_MAX_LENGTH, responsebody.getDetails());
	}

	@Test
	public void TestAddNewCreditCardAlphaNumericCard() {
		CreditCard testCard = new CreditCard("test6", "17377886abcd4", 0, 1000);
		MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/creditCards/add");
		BindingResult bindResult = bindAndValidate(mockRequest, testCard);
		ResponseEntity response = CreditcardController.addNewCreditCard(testCard, bindResult);
		ResponseMessage responsebody = (ResponseMessage) response.getBody();
		assertEquals(PSConstants._ERR_CARD_NON_NUMERIC, responsebody.getDetails());
	}

	@Test
	public void TestAddNewCreditCardthrowServerError() {
		CreditCard testCard = new CreditCard("test6", "17377886abcd4", 0, 1000);

		ResponseEntity response = CreditcardController.addNewCreditCard(testCard, null);
		ResponseMessage responsebody = (ResponseMessage) response.getBody();
		assertEquals(PSConstants._ERR_CARD_ADDED_FAILED, responsebody.getDetails());
	}

	@Test
	public void TestGetAllCreditCardEmptyList() {

		ResponseEntity response = CreditcardController1.getAllCreditCards();
		List<CreditCard> crditCardList = (List) response.getBody();
		assertEquals(true, crditCardList.size() == 0);
	}

	@Test
	public void TestGetAllCreditCardSaved() {
		CreditCard testCard1 = new CreditCard("test1", "173788769372005", 0, 1000);
		MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/creditCards/add");
		BindingResult bindResult = bindAndValidate(mockRequest, testCard1);
		CreditcardController.addNewCreditCard(testCard1, bindResult);

		ResponseEntity response = CreditcardController.getAllCreditCards();
		List<CreditCard> crditCardList = (List) response.getBody();
		assertEquals(true, crditCardList.size() > 0);
	}

	private BindingResult bindAndValidate(HttpServletRequest request, Object formObject) {
		WebDataBinder binder = new WebDataBinder(formObject);
		binder.setValidator(validator);
		binder.bind(new MutablePropertyValues(request.getParameterMap()));
		binder.getValidator().validate(binder.getTarget(), binder.getBindingResult());
		return binder.getBindingResult();
	}

}
