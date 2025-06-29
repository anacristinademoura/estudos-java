package br.com.cdb.bancodigital_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdb.bancodigital_jpa.dto.ContaCriacaoDTO;
import br.com.cdb.bancodigital_jpa.dto.ContaDetalhadaDTO;
import br.com.cdb.bancodigital_jpa.entity.Conta;
import br.com.cdb.bancodigital_jpa.service.ClienteService;
import br.com.cdb.bancodigital_jpa.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	
	@PostMapping("/criar")
	public ResponseEntity<String> criarConta(@RequestBody ContaCriacaoDTO dto){
		try {
			contaService.criarConta(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body("Conta criada com sucesso!");
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}
	
	@GetMapping("/detalhes/{idConta}")
	public ResponseEntity<?> detalhesConta(@PathVariable Long idConta){
		try {
			ContaDetalhadaDTO detalhes = contaService.detalhesConta(idConta);
			return ResponseEntity.ok(detalhes);
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}
	
	
	@PostMapping("/depositar")
	public ResponseEntity<String> depositar(@RequestParam Long idConta, @RequestParam Double valor){
		try {
			contaService.depositar(idConta, valor);
			return ResponseEntity.ok("Deposito realizado com sucesso!");
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
		}
		catch(RuntimeException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
		//Envia 200 OK se der certo
		//Envia 400 e uma mensagem se der errado
	}
}
