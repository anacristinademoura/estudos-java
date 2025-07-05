package br.com.cdb.bancodigital_jpa.service;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cdb.bancodigital_jpa.dto.AlterarSenhaCartaoDTO;
import br.com.cdb.bancodigital_jpa.dto.CartaoCriacaoDTO;
import br.com.cdb.bancodigital_jpa.dto.CartaoDetalhadoDTO;
import br.com.cdb.bancodigital_jpa.dto.PagamentoCartaoDTO;
import br.com.cdb.bancodigital_jpa.entity.Cartao;
import br.com.cdb.bancodigital_jpa.entity.Cliente;
import br.com.cdb.bancodigital_jpa.entity.Conta;
import br.com.cdb.bancodigital_jpa.enums.TipoCartao;
import br.com.cdb.bancodigital_jpa.repository.CartaoRepository;
import br.com.cdb.bancodigital_jpa.repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
    private ContaRepository contaRepository;

    public void emitirCartao(CartaoCriacaoDTO dto) {
        Conta conta = contaRepository.findById(dto.getIdConta())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada."));

        Cliente cliente = conta.getCliente();

        Cartao cartao = new Cartao();
        cartao.setConta(conta);
        cartao.setNumero(gerarNumeroCartao());
        cartao.setSenha(dto.getSenha());
        cartao.setTipo(dto.getTipo());
        cartao.setAtivo(true);
        cartao.setValidade(LocalDate.now().plusYears(5));
        
        if (dto.getTipo() == TipoCartao.CREDITO) {
            double limite = switch (cliente.getTipoCliente()) {
                case COMUM -> 1000.0;
                case SUPER -> 5000.0;
                case PREMIUM -> 10000.0;
            };
            cartao.setLimite(limite);
            cartao.setSaldoFatura(0.0);
        } else {
            cartao.setLimiteDiario(1000.0); // padrão, pode ser alterado depois
        }
        
        if (dto.getSenha() == null || dto.getSenha().isBlank()) {
            throw new IllegalArgumentException("Senha do cartão é obrigatória.");
        }

        cartaoRepository.save(cartao);
    }

    private String gerarNumeroCartao() {
        Random geradorAleatorio = new Random();
        StringBuilder numeroCartao = new StringBuilder();
        for (int i = 0; i < 16; i++) { //número aleatório com 16 digitos
            numeroCartao.append(geradorAleatorio.nextInt(10)); //de 0 a 9 apenas
        }
        return numeroCartao.toString();
    }
    
    public CartaoDetalhadoDTO detalhesCartao(Long id) {
        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cartão não encontrado"));

        Cliente cliente = cartao.getConta().getCliente();

        return new CartaoDetalhadoDTO(
                cartao.getId(),
                cartao.getNumero(),
                cartao.getTipo().name(),
                cartao.isAtivo(),
                cartao.getValidade(),
                cartao.getTipo() == TipoCartao.CREDITO ? cartao.getLimite() : null,
                cartao.getTipo() == TipoCartao.DEBITO ? cartao.getLimiteDiario() : null,
                cliente.getNome()
        );
    }
    
    @Transactional
    //Transacional garante que todas as operações dentro do método sejam executadas 
    //como uma única transação no banco de dados.
    public void realizarPagamento(Long idCartao, PagamentoCartaoDTO dto) {
        Cartao cartao = cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new EntityNotFoundException("Cartão não encontrado"));

        if (!cartao.isAtivo()) {
            throw new IllegalStateException("Cartão inativo.");
        }

        if (!cartao.getSenha().equals(dto.getSenha())) {
            throw new SecurityException("Senha incorreta.");
        }

        double valor = dto.getValor();

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do pagamento deve ser maior que zero.");
        }

        Conta conta = cartao.getConta();

        if (cartao.getTipo() == TipoCartao.CREDITO) {
            if (cartao.getValorFatura() + valor > cartao.getLimite()) {
                throw new IllegalStateException("Limite de crédito excedido.");
            }
            cartao.setValorFatura(cartao.getValorFatura() + valor);
        } else if (cartao.getTipo() == TipoCartao.DEBITO) {
            if (valor > conta.getSaldo()) {
                throw new IllegalStateException("Saldo insuficiente na conta.");
            }
            if (valor > cartao.getLimiteDiario()) {
                throw new IllegalStateException("Valor excede o limite diário do cartão de débito.");
            }

            conta.setSaldo(conta.getSaldo() - valor);
        }

        cartaoRepository.save(cartao);
    }
    
    public void atualizarStatusCartao(Long idCartao, boolean novoStatus) {
        Cartao cartao = cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new EntityNotFoundException("Cartão não encontrado"));

        cartao.setAtivo(novoStatus);
        cartaoRepository.save(cartao);
    }
    
    public void alterarSenha(Long idCartao, AlterarSenhaCartaoDTO dto) {
        Cartao cartao = cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new EntityNotFoundException("Cartão não encontrado."));

        if (cartao.getSenha() == null || !cartao.getSenha().equals(dto.getSenhaAtual())) {
            throw new SecurityException("Senha incorreta.");
        }

        if (dto.getNovaSenha() == null || dto.getNovaSenha().isEmpty()) {
            throw new IllegalArgumentException("A nova senha não pode ser vazia.");
        }

        cartao.setSenha(dto.getNovaSenha());
        cartaoRepository.save(cartao);
    }
    
    public void alterarLimiteCartaoCredito(Long idCartao, Double novoLimite) {
        Cartao cartao = cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new EntityNotFoundException("Cartão não encontrado."));

        if (cartao.getTipo() != TipoCartao.CREDITO) {
            throw new IllegalArgumentException("Apenas cartões de crédito podem ter limite alterado.");
        }

        if (novoLimite == null || novoLimite <= 0) {
            throw new IllegalArgumentException("O limite deve ser maior que zero.");
        }

        Cliente cliente = cartao.getConta().getCliente();
        double limiteMaximo;

        switch (cliente.getTipoCliente()) {
            case COMUM:
                limiteMaximo = 1000.0;
                break;
            case SUPER:
                limiteMaximo = 5000.0;
                break;
            case PREMIUM:
                limiteMaximo = 10000.0;
                break;
            default:
                throw new IllegalStateException("Classificação de cliente inválida.");
        }

        if (novoLimite > limiteMaximo) {
            throw new IllegalArgumentException("Limite excede o máximo permitido para sua classificação.");
        }

        cartao.setLimite(novoLimite);
        cartaoRepository.save(cartao);
    }
    
    public double consultarFatura(Long idCartao) {
        Cartao cartao = cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new EntityNotFoundException("Cartão não encontrado"));

        if (cartao.getTipo() != TipoCartao.CREDITO) {
            throw new IllegalArgumentException("Apenas cartões de crédito possuem fatura.");
        }

        return cartao.getValorFatura();
    }
    
    @Transactional
    public void pagarFatura(Long idCartao) {
        Cartao cartao = cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new EntityNotFoundException("Cartão não encontrado"));

        if (cartao.getTipo() != TipoCartao.CREDITO) {
            throw new IllegalArgumentException("Este cartão não possui fatura.");
        }

        double valor = cartao.getValorFatura();
        if (valor <= 0) {
            throw new IllegalArgumentException("Não há fatura em aberto.");
        }

        Conta conta = cartao.getConta();
        if (conta.getSaldo() < valor) {
            throw new IllegalArgumentException("Saldo insuficiente na conta para pagar a fatura.");
        }

        conta.setSaldo(conta.getSaldo() - valor);
        cartao.setValorFatura(0.0);

        // Atualiza ambos por causa do cascade na Conta → Cartao
        contaRepository.save(conta);
        cartaoRepository.save(cartao);
    }
    
    public void alterarLimiteDiario(Long idCartao, Double novoLimite) {
        Cartao cartao = cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new EntityNotFoundException("Cartão não encontrado."));

        if (novoLimite == null || novoLimite <= 0) {
            throw new IllegalArgumentException("O limite diário deve ser maior que zero.");
        }

        if (cartao.getTipo() != TipoCartao.DEBITO) {
            throw new IllegalArgumentException("Apenas cartões de débito podem ter limite diário alterado.");
        }

        cartao.setLimiteDiario(novoLimite);
        cartaoRepository.save(cartao);
    }


}
