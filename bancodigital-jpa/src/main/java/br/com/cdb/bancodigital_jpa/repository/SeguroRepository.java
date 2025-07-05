package br.com.cdb.bancodigital_jpa.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cdb.bancodigital_jpa.entity.Seguro;

@Repository
public interface SeguroRepository extends JpaRepository<Seguro, Long> {
	List<Seguro> findByCartaoId(Long idCartao);
}