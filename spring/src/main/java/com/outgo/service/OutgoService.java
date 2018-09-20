package com.outgo.service;

import java.util.List;
import java.util.stream.Collectors;

import com.outgo.domain.Status;
import com.outgo.model.Outgo;
import com.outgo.repository.OutgoRepository;
import com.outgo.resource.OutgoIdsResource;
import com.outgo.resource.OutgoResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutgoService {
    @Autowired
    private OutgoRepository outgoRepository;

    public List<OutgoResource> findNonProcessing() {
        return outgoRepository.findNonProcessing().asList().stream()
                .map(outgo -> new OutgoResource(outgo))
                .collect(Collectors.toList());
    }

    public void register(OutgoResource outgoResource) {
        outgoResource.setStatus(Status.NON_PROCESSING.getValue());
        outgoRepository.save(outgoResource.toOutgo());
    }

    public void update(OutgoResource outgoResource) {
        outgoRepository.save(outgoResource.toOutgo());
    }

    public void delete(OutgoIdsResource outgoIdsResource) {
        for (Long id : outgoIdsResource.getIds()) {
            Outgo outgo = outgoRepository.find(id);
            outgoRepository.delete(outgo);
        }
    }

    public void pay(OutgoIdsResource outgoIdsResource) {
        for (Long id : outgoIdsResource.getIds()) {
            Outgo outgo = outgoRepository.find(id);
            outgo.setStatus(Status.DONE);
            outgoRepository.save(outgo);
        }
    }
}
