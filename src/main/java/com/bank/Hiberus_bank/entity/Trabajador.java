package com.bank.Hiberus_bank.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trabajador {
        @Id
        @SequenceGenerator(name = "tr_id_sequence", sequenceName = "tr_id_sequence")
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tr_id_sequence")
        private Long id;
        private String nombre;
        private String apellidos;
        private double salariobruto;
        private double saldo;
        private long cobrosNomina;
        private long transfEmitExito;
        private long transfRecebExito;

        public void setSalariobruto(double salariobruto) {
            this.salariobruto = salariobruto;
        }


        public double getSalariobruto() {
                return salariobruto;
        }

        public void setSaldo(double saldo) {
            this.saldo = saldo;
        }


        public double getSaldo() { return saldo; }

}
