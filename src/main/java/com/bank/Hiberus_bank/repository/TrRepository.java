package com.bank.Hiberus_bank.repository;

import com.bank.Hiberus_bank.entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TrRepository extends JpaRepository<Trabajador, Long> {

}
