package com.ps.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ps.app.model.CreditCard;

/**
 * this interface is DAO service which connect with db and help to provide all
 * db operations.
 */

public interface CreditCardDaoService extends JpaRepository<CreditCard, Long> {

}
