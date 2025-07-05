package br.com.cdb.bancodigital_jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdb.bancodigital_jpa.dto.ClienteCriacaoDTO;
import br.com.cdb.bancodigital_jpa.entity.Cliente;
import br.com.cdb.bancodigital_jpa.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping("/add")
	public ResponseEntity<String> addCliente(@RequestBody @Valid ClienteCriacaoDTO dto) {
	    try {
	        Cliente clienteAdicionado = clienteService.salvarCliente(dto);
	        return new ResponseEntity<>(
	            "Cliente " + clienteAdicionado.getNome() + " adicionado com sucesso!",
	            HttpStatus.CREATED
	        );
	    } catch (Exception e) {
	        return new ResponseEntity<>("Erro interno: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/listAll")
	public ResponseEntity<List<Cliente>> getAllClientes() {
		List<Cliente> clientes = clienteService.getClientes();
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{idCliente}")
	public ResponseEntity<String> deletarCliente(@PathVariable Long idCliente) {
		try {
			clienteService.deletarCliente(idCliente);
			return ResponseEntity.ok("Cliente deletado com sucesso.");
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: " + e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}
	
	@GetMapping("/get/{idCliente}")
	public ResponseEntity<?> buscarCliente(@PathVariable Long idCliente){
		//Retorna <?> porque uma resposta é Cliente e a outra é String
		try {
			Cliente cliente = clienteService.getCliente(idCliente);
			return ResponseEntity.ok(cliente);
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}
	
	@PutMapping("/editar/{idCliente}")
	public ResponseEntity<String> editarCliente(@PathVariable Long idCliente, @RequestBody @Valid ClienteCriacaoDTO novosDados){
	    try {
	        Cliente clienteAtualizado = clienteService.editarCliente(idCliente, novosDados);
	        return new ResponseEntity<>("Cliente " + clienteAtualizado.getNome() + " editado com sucesso!", HttpStatus.ACCEPTED);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
	    }
	}
}
