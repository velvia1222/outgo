package com.outgo.repository;

import com.outgo.dao.OutgoDao;
import com.outgo.model.Outgo;
import com.outgo.model.Outgoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OutgoRepository {

    @Autowired
    private OutgoDao outgoDao;

    public Outgoes findNonProcessing() {
        return new Outgoes(outgoDao.selectNonProcessing());
    }

    public void save(Outgo outgo) {
        if (outgo.getId() == 0) {
            outgoDao.insert(outgo);
        } else {
            outgoDao.update(outgo);
        }
    }

    public void delete(Outgo outgo) {
        outgoDao.delete(outgo);
    }
}
