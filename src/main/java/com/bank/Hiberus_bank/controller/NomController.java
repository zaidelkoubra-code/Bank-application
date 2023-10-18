package com.bank.Hiberus_bank.controller;

import com.bank.Hiberus_bank.entity.Nomina;
import com.bank.Hiberus_bank.entity.Trabajador;
import com.bank.Hiberus_bank.service.NomService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/nominas")
public class NomController {
    @Autowired
    NomService nomService;
    public NomController(NomService nomService) {
        this.nomService = nomService;
    }

    @PostMapping("/{id}")
    public void pagarNomina(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @PathVariable Long id){
        final String autorizacion="Gandalf";

        if (!Objects.equals(authorizationHeader, autorizacion)){
            throw new RuntimeException("not autorized");
        }
        nomService.pagarNomina(id);
    }

    @PostMapping()
    public void pagarNominas(@RequestHeader(value = "Authorization", required = false) String authorizationHeader){
        final String autorizacion="Gandalf";

        if (!Objects.equals(authorizationHeader, autorizacion)){
            throw new RuntimeException("not autorized");
        }
        nomService.pagarNominas();
    }
    @GetMapping
    public ResponseEntity<List<Nomina>> getAllNominas() {
        List<Nomina> nominas = nomService.getAllNominas();
        return new ResponseEntity<>(nominas, HttpStatus.OK);
    }


}