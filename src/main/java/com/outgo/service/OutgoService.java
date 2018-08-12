package com.outgo.service;

import java.util.List;
import java.util.stream.Collectors;

import com.outgo.repository.OutgoRepository;
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

    public void save(OutgoResource outgoResource) {
        outgoRepository.save(outgoResource.toOutgo());
    }

    public void delete(OutgoResource outgoResource) {
        outgoRepository.delete(outgoResource.toOutgo());
    }
}
