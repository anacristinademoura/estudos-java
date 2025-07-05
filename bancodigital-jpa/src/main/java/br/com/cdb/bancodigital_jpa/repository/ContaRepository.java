package br.com.cdb.bancodigital_jpa.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cdb.bancodigital_jpa.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta,Long>{

}
