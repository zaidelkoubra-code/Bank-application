package com.bank.Hiberus_bank.controller;

import com.bank.Hiberus_bank.service.TrService;
import com.bank.Hiberus_bank.entity.Trabajador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trabajadores")
public class TrController {
    @Autowired
    TrService trService;

    public TrController(TrService trService) {
        this.trService = trService;
    }

    @PostMapping
    public void altatr(@RequestBody Trabajador trabajador) {
        trService.altatr(trabajador);
    }

    @DeleteMapping("/{id}")
    public void bajatr(@PathVariable Long id) {
        trService.bajatr(id);
    }

    @PutMapping("/{id}/salario")
    public void aumentarSalario(@PathVariable Long id, @RequestParam double importe){
        trService.aumentarSalario(id, importe);
    }
    @GetMapping
    public ResponseEntity<List<Trabajador>> getAllTrabajadores() {
        List<Trabajador> trabajadores = trService.getAllTrabajadores();
        return new ResponseEntity<>(trabajadores, HttpStatus.OK);
    }
}
