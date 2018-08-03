package com.anjiplus.springboot.service;

import com.anjiplus.springboot.Interface.GirlRepository;
import com.anjiplus.springboot.dao.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void insertTwo(){
        Girl girla = new Girl();
        girla.setCupSize("A");
        girla.setAge(20);
        girlRepository.save(girla);

        Girl girlb = new Girl();
        girlb.setCupSize("BBB");
        girlb.setAge(21);
        girlRepository.save(girlb);
    }
}
