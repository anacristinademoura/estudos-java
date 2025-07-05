package br.com.cdb.bancodigital_jpa.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cdb.bancodigital_jpa.dto.SeguroCriacaoDTO;
import br.com.cdb.bancodigital_jpa.entity.Cartao;
import br.com.cdb.bancodigital_jpa.entity.Seguro;
import br.com.cdb.bancodigital_jpa.repository.CartaoRepository;
import br.com.cdb.bancodigital_jpa.repository.SeguroRepository;

@Service
public class SeguroService {

    @Autowired
    private SeguroRepository seguroRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    public Seguro criarSeguro(Long idCartao, SeguroCriacaoDTO dto) {
        Cartao cartao = cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));

        validarDTO(dto);

        Seguro seguro = new Seguro();
        seguro.setCartao(cartao);
        seguro.setTipo(dto.getTipo());
        seguro.setDataInicio(dto.getDataInicio());
        seguro.setDataFim(dto.getDataFim());
        seguro.setValor(dto.getValor());
        seguro.setAtivo(true);

        return seguroRepository.save(seguro);
    }

    private void validarDTO(SeguroCriacaoDTO dto) {
        if (dto.getTipo() == null || dto.getTipo().isBlank()) {
            throw new IllegalArgumentException("Tipo do seguro é obrigatório.");
        }
        if (dto.getValor() <= 0) {
            throw new IllegalArgumentException("Valor do seguro deve ser maior que zero.");
        }
        if (dto.getDataInicio() == null || dto.getDataFim() == null) {
            throw new IllegalArgumentException("Datas de início e fim são obrigatórias.");
        }
        if (dto.getDataFim().isBefore(dto.getDataInicio())) {
            throw new IllegalArgumentException("Data fim deve ser posterior à data início.");
        }
        if (dto.getDataInicio().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data início não pode ser anterior a hoje.");
        }
    }

    public List<Seguro> listarSegurosPorCartao(Long idCartao) {
		return seguroRepository.findByCartaoId(idCartao);
    }

    public Seguro detalharSeguro(Long idSeguro) {
        return seguroRepository.findById(idSeguro)
                .orElseThrow(() -> new RuntimeException("Seguro não encontrado"));
    }

    public void cancelarSeguro(Long idSeguro) {
        Seguro seguro = seguroRepository.findById(idSeguro)
                .orElseThrow(() -> new RuntimeException("Seguro não encontrado"));
        if (!seguro.isAtivo()) {
            throw new IllegalArgumentException("Seguro já está cancelado.");
        }
        seguro.setAtivo(false);
        seguroRepository.save(seguro);
    }
}
