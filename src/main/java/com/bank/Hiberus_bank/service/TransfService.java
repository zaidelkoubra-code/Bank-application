package com.bank.Hiberus_bank.service;

import com.bank.Hiberus_bank.entity.Nomina;
import com.bank.Hiberus_bank.entity.Trabajador;
import com.bank.Hiberus_bank.entity.Transferencia;
import com.bank.Hiberus_bank.repository.TrRepository;
import com.bank.Hiberus_bank.repository.TransfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bank.Hiberus_bank.FailedTransactionException;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransfService {

    @Autowired
    TransfRepository transfRepository;
    @Autowired
    TrRepository trRepository;

    public void hacerTransferencia(Long remitenteId, Long receptorId, Double importe) {
        Trabajador remitente = trRepository.findById(remitenteId).orElse(null);
        Trabajador receptor = trRepository.findById(receptorId).orElse(null);

        if (remitente == null || receptor == null) {
            throw new FailedTransactionException("Remitente o receptor no existe");
        }

        if ("Antonio".equals(remitente.getNombre()) || "Antonio".equals(receptor.getNombre())) {
            Transferencia transferencia = new Transferencia();
            transferencia.setRemitenteId(remitenteId);
            transferencia.setReceptorId(receptorId);
            transferencia.setFechaTransf(LocalDate.now());
            transferencia.setImporte(importe);
            transferencia.setEstado("Rechazado");
            transfRepository.save(transferencia);
        } else if (importe % 10 != 0) {
            Transferencia transferencia = new Transferencia();
            transferencia.setRemitenteId(remitenteId);
            transferencia.setReceptorId(receptorId);
            transferencia.setFechaTransf(LocalDate.now());
            transferencia.setImporte(importe);
            transferencia.setEstado("Rechazado");
            transfRepository.save(transferencia);
        } else if (remitente.getSaldo() >= importe) {
            remitente.setSaldo(remitente.getSaldo() - importe);
            receptor.setSaldo(receptor.getSaldo() + importe);
            remitente.setTransfEmitExito(remitente.getTransfEmitExito() + 1);
            receptor.setTransfRecebExito(receptor.getTransfRecebExito() + 1);
            trRepository.save(remitente);
            trRepository.save(receptor);

            Transferencia transferencia = new Transferencia();
            transferencia.setRemitenteId(remitenteId);
            transferencia.setReceptorId(receptorId);
            transferencia.setFechaTransf(LocalDate.now());
            transferencia.setImporte(importe);
            transferencia.setEstado("Acceptado");
            transfRepository.save(transferencia);
        } else {
            throw new FailedTransactionException("La transacción ha fallado. Inténtalo de nuevo.");
        }
    }
    public List<Transferencia> getAllTransferencias() {
        return transfRepository.findAll();
    }
    public List<Transferencia> getTransferenciasrechazadas() {
        return transfRepository.findByEstado("rechazado");
    }
}



