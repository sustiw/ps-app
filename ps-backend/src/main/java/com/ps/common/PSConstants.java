package com.ps.common;

public interface PSConstants {
	
	
	//DB
	public final String CC_DB_TABLE_NAME="credit_card";
	
	
	//End points
	public final String CREDITCARDS_APIENPOINTS="/creditCards";

	public final String ADD_CREDITCARDS_APIENPOINTS="/add";
	public final String GETALL_API_ENDPOINT="/getAll";
	
	public final int CARD_MAX_LENGTH=19;
	public final int CARD_MIN_LENGTH=10;

	
	//response
	public final String SUCCESS="success";
	public final String FAILED="failed";

	//response Message
	public final String CARD_ADDED_SUCCESS="credit card added successfully";
	public final String _ERR_CARD_ADDED_FAILED="credit card not added";
	public final String _ERR_CARD_ALREADY_ADDED="credit card already added with same name or different name";

	public final String _ERR_CARD_NON_NUMERIC="credit card should be numeric only";
	public final String _ERR_CARD_EMPTY="credit card is empty";
	public final String _ERR_CARD_MAX_LENGTH="credit card length is more then 19";
	public final String _ERR_CARD_MIN_LENGTH="credit card length is less then 10";
	public final String _ERR_CARD_LUHN_CHECK_FAILED="credit card number passed in not valid";
	public final String _ERR_NAME="name can not be empty";

	
}
