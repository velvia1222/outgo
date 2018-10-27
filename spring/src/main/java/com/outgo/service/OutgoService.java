package com.outgo.service;

import java.util.List;
import java.util.stream.Collectors;

import com.outgo.domain.Status;
import com.outgo.model.Outgo;
import com.outgo.repository.OutgoRepository;
import com.outgo.form.OutgoIdsForm;
import com.outgo.form.OutgoForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutgoService {
    @Autowired
    private OutgoRepository outgoRepository;

    public List<OutgoForm> findNonProcessing() {
        return outgoRepository.findNonProcessing().asList().stream()
                .map(outgo -> new OutgoForm(outgo))
                .collect(Collectors.toList());
    }

    public void register(OutgoForm outgoForm) {
        outgoForm.setStatus(Status.NON_PROCESSING.getValue());
        outgoRepository.save(outgoForm.toOutgo());
    }

    public void update(OutgoForm outgoForm) {
        outgoRepository.save(outgoForm.toOutgo());
    }

    public void delete(OutgoIdsForm outgoIdsForm) {
        for (Long id : outgoIdsForm.getIds()) {
            Outgo outgo = outgoRepository.find(id);
            outgoRepository.delete(outgo);
        }
    }

    public void pay(OutgoIdsForm outgoIdsForm) {
        for (Long id : outgoIdsForm.getIds()) {
            Outgo outgo = outgoRepository.find(id);
            outgo.setStatus(Status.DONE);
            outgoRepository.save(outgo);
        }
    }
}
