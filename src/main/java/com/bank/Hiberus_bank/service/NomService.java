package com.bank.Hiberus_bank.service;

import com.bank.Hiberus_bank.entity.Nomina;
import com.bank.Hiberus_bank.entity.Trabajador;
import com.bank.Hiberus_bank.repository.NomRepository;
import com.bank.Hiberus_bank.repository.TrRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NomService {
    @Autowired
    NomRepository nominaRepository;
    TrRepository trRepository1;

    public NomService(NomRepository nominaRepository, TrRepository trRepository1) {this.nominaRepository = nominaRepository; this.trRepository1= trRepository1; }


    public void pagarNomina(Long trabajadorId) {
        LocalDate fechaPago = LocalDate.now();
        Optional<Trabajador> trabajadorOptional = trRepository1.findById(trabajadorId);

        @NotNull
        Trabajador trabajador = trabajadorOptional.get();

        double salarioBruto = trabajador.getSalariobruto();
        double importeNeto = salarioBruto * 0.9475;

        Nomina nomina = new Nomina();
        nomina.setTrabajadorId(trabajadorId);
        nomina.setFechaPago(fechaPago);
        nomina.setImporteNeto(importeNeto);
        nominaRepository.save(nomina);

        trabajador.setSaldo(trabajador.getSaldo() + importeNeto);
        trRepository1.save(trabajador);

    }

    public void pagarNominas() {
        LocalDate fechaPago = LocalDate.now();
        List<Trabajador> trabajadores = trRepository1.findAll();

        for (Trabajador trabajador : trabajadores) {
            double salarioBruto = trabajador.getSalariobruto();
            double importeNeto = salarioBruto * 0.9475;
            trabajador.setCobrosNomina(trabajador.getCobrosNomina() + 1);

            Nomina nomina = new Nomina();
            nomina.setFechaPago(fechaPago);
            nomina.setTrabajadorId(trabajador.getId());
            nomina.setImporteNeto(importeNeto);
            nominaRepository.save(nomina);

            trabajador.setSaldo(trabajador.getSaldo() + importeNeto);
            trRepository1.save(trabajador);
        }
    }
    public List<Nomina> getAllNominas() {
        return nominaRepository.findAll();
    }

    }


