package com.bank.Hiberus_bank.controller;

import com.bank.Hiberus_bank.entity.Nomina;
import com.bank.Hiberus_bank.entity.Transferencia;
import com.bank.Hiberus_bank.service.NomService;
import com.bank.Hiberus_bank.service.TransfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransfController {

    @Autowired
    TransfService transfService;
    public TransfController(TransfService transfService) {
        this.transfService = transfService;
    }

    @PostMapping()
    public void HacerTransferencia(@RequestParam Long remitenteId, @RequestParam Long receptorId, @RequestParam Double importe){
        transfService.hacerTransferencia(remitenteId,receptorId,importe);
    }
    @GetMapping("/all")

    public ResponseEntity<List<Transferencia>> getAllTransferencias() {
        List<Transferencia> transferencias = transfService.getAllTransferencias();
        return new ResponseEntity<>(transferencias, HttpStatus.OK);
    }

    @GetMapping("/rechazadas")
    public ResponseEntity<List<Transferencia>> getTransferenciasrechazadas() {
        List<Transferencia> transferencias = transfService.getTransferenciasrechazadas();
        return new ResponseEntity<>(transferencias, HttpStatus.OK);
    }

}
