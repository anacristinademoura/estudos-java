package br.com.cdb.bancodigital_jpa.dto;

import br.com.cdb.bancodigital_jpa.enums.TipoCartao;
import jakarta.validation.constraints.NotBlank;

public class CartaoCriacaoDTO {
	private Long idConta;
    private TipoCartao tipo;
    @NotBlank
    private String senha;

    public Long getIdConta() { return idConta; }
    public void setIdConta(Long idConta) { this.idConta = idConta; }

    public TipoCartao getTipo() { return tipo; }
    public void setTipo(TipoCartao tipo) { this.tipo = tipo; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
