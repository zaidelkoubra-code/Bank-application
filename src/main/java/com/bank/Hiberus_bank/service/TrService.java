package com.bank.Hiberus_bank.service;

import com.bank.Hiberus_bank.entity.Trabajador;
import com.bank.Hiberus_bank.repository.TrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrService {

    @Autowired
    TrRepository trRepository;

    public TrService(TrRepository trRepository) {
        this.trRepository = trRepository;
    }

    public void altatr(Trabajador trabajador) {
        trRepository.save(trabajador);
    }

    public void bajatr(Long id) {
        trRepository.deleteById(id);
    }

    public void aumentarSalario(Long id, double importe) {
        Trabajador trabajador = trRepository.findById(id).orElse(null);
        if (trabajador != null) {
            trabajador.setSalariobruto(trabajador.getSalariobruto() + importe);
            trRepository.save(trabajador);
        }
    }

    public List<Trabajador> getAllTrabajadores() {
        return trRepository.findAll();
    }



}
