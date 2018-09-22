package com.outgo.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.outgo.dao.OutgoDao;
import com.outgo.domain.Status;
import com.outgo.model.Outgo;
import com.outgo.model.Outgoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OutgoRepository {

    @Autowired
    private OutgoDao outgoDao;

    public Outgo find(long id) {
        return outgoDao.selectById(id);
    }

    public Outgoes findNonProcessing() {
        return new Outgoes(outgoDao.selectByStatus(Status.NON_PROCESSING));
    }

    public void save(Outgo outgo) {
        if (outgo.getId() == 0) {
            outgoDao.insert(outgo);
        } else {
            outgo.setModified_at(Timestamp.valueOf(LocalDateTime.now()));
            outgoDao.update(outgo);
        }
    }

    public void delete(Outgo outgo) {
        outgoDao.delete(outgo);
    }
}
