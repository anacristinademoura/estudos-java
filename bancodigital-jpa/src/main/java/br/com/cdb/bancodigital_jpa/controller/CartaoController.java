package br.com.cdb.bancodigital_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdb.bancodigital_jpa.dto.AlterarLimiteCartaoDTO;
import br.com.cdb.bancodigital_jpa.dto.AlterarSenhaCartaoDTO;
import br.com.cdb.bancodigital_jpa.dto.AtualizarStatusCartaoDTO;
import br.com.cdb.bancodigital_jpa.dto.CartaoCriacaoDTO;
import br.com.cdb.bancodigital_jpa.dto.CartaoDetalhadoDTO;
import br.com.cdb.bancodigital_jpa.dto.PagamentoCartaoDTO;
import br.com.cdb.bancodigital_jpa.service.CartaoService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

	@Autowired
	private CartaoService cartaoService;

	@PostMapping("/emitir")
	public ResponseEntity<String> emitirCartao(@RequestBody CartaoCriacaoDTO dto) {
		try {
			cartaoService.emitirCartao(dto);
			return ResponseEntity.ok("Cartão emitido com sucesso.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> consultarCartao(@PathVariable Long id) {
		try {
			CartaoDetalhadoDTO dto = cartaoService.detalhesCartao(id);
			return ResponseEntity.ok(dto);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}

	@PostMapping("/{id}/pagamento")
	public ResponseEntity<String> realizarPagamento(@PathVariable Long id, @RequestBody PagamentoCartaoDTO dto) {
		try {
			cartaoService.realizarPagamento(id, dto);
			return ResponseEntity.ok("Pagamento realizado com sucesso.");
		} catch (IllegalArgumentException | IllegalStateException | SecurityException e) {
			// | multi-catch captura múltiplas exceções diferentes em um único bloco
			return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<String> atualizarStatusCartao(@PathVariable Long id,
			@RequestBody AtualizarStatusCartaoDTO dto) {
		try {
			cartaoService.atualizarStatusCartao(id, dto.isAtivo());
			return ResponseEntity.ok("Status do cartão atualizado com sucesso.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}

	@PutMapping("/{id}/senha")
	public ResponseEntity<String> alterarSenhaCartao(@PathVariable Long id, @RequestBody AlterarSenhaCartaoDTO dto) {
		try {
			cartaoService.alterarSenha(id, dto);
			return ResponseEntity.ok("Senha alterada com sucesso.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
		}
	}

	@PutMapping("/{id}/limite")
	public ResponseEntity<String> alterarLimiteCartaoCredito(@PathVariable Long id,
			@RequestBody AlterarLimiteCartaoDTO dto) {
		try {
			cartaoService.alterarLimiteCartaoCredito(id, dto.getNovoLimite());
			return ResponseEntity.ok("Limite de crédito alterado com sucesso.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
		}
	}

	@GetMapping("/{id}/fatura")
	public ResponseEntity<?> consultarFatura(@PathVariable Long id) {
		try {
			double valor = cartaoService.consultarFatura(id);
			return ResponseEntity.ok(valor);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
		}
	}

	@PostMapping("/{id}/fatura/pagar")
	public ResponseEntity<String> pagarFatura(@PathVariable Long id) {
		try {
			cartaoService.pagarFatura(id);
			return ResponseEntity.ok("Fatura paga com sucesso.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}

	@PutMapping("/{id}/limite-diario")
	public ResponseEntity<String> alterarLimiteDiario(@PathVariable Long id, @RequestBody AlterarLimiteCartaoDTO dto) {
	    try {
	        cartaoService.alterarLimiteDiario(id, dto.getNovoLimite());
	        return ResponseEntity.ok("Limite diário atualizado com sucesso.");
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    } catch (EntityNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}

}
