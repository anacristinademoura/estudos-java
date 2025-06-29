package br.com.cdb.bancodigital_jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cdb.bancodigital_jpa.repository.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository cartaoRepository;
}
