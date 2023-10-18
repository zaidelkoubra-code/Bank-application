package com.bank.Hiberus_bank.repository;

import com.bank.Hiberus_bank.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransfRepository extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByEstado(String estado);
}
