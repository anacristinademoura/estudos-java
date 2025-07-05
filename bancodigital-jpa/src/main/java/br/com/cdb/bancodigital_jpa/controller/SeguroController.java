package br.com.cdb.bancodigital_jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.cdb.bancodigital_jpa.dto.SeguroCriacaoDTO;
import br.com.cdb.bancodigital_jpa.entity.Seguro;
import br.com.cdb.bancodigital_jpa.service.SeguroService;

@RestController
@RequestMapping("/seguro")
public class SeguroController {

	@Autowired
	private SeguroService seguroService;

	@PostMapping("/cartao/{idCartao}")
	public ResponseEntity<?> criarSeguro(@PathVariable Long idCartao, @RequestBody SeguroCriacaoDTO dto) {
		try {
			Seguro novoSeguro = seguroService.criarSeguro(idCartao, dto);
			return ResponseEntity.ok(novoSeguro);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}

	@GetMapping("/cartao/{idCartao}")
	public ResponseEntity<?> listarSeguros(@PathVariable Long idCartao) {
		try {
			List<Seguro> seguros = seguroService.listarSegurosPorCartao(idCartao);
			return ResponseEntity.ok(seguros);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> detalharSeguro(@PathVariable Long id) {
		try {
			Seguro seguro = seguroService.detalharSeguro(id);
			return ResponseEntity.ok(seguro);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}

	@PutMapping("/{id}/cancelar")
	public ResponseEntity<String> cancelarSeguro(@PathVariable Long id) {
		try {
			seguroService.cancelarSeguro(id);
			return ResponseEntity.ok("Seguro cancelado com sucesso.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}
}
