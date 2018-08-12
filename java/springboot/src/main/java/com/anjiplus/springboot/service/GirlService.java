package com.anjiplus.springboot.service;

import com.anjiplus.springboot.Interface.GirlRepository;
import com.anjiplus.springboot.dao.Girl;
import com.anjiplus.springboot.enums.ResultEnum;
import com.anjiplus.springboot.exception.GirlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertTwo(){
        Girl girla = new Girl();
        girla.setCupSize("A");
        girla.setAge(20);
        girlRepository.save(girla);

        Girl girlb = new Girl();
        girlb.setCupSize("B");
        girlb.setAge(21);
        girlRepository.save(girlb);
    }



    public void getAge(Integer id) throws Exception {
        Girl girl = girlRepository.findById(id).get();
        Integer age = girl.getAge();
        if (age < 10){
            throw new GirlException(ResultEnum.PRIMARY_SHOOL);
        } else if (age > 10 && age < 16){
            throw new GirlException(ResultEnum.MIDDLE_SHOOL);
        }

    }

//    查询一个女生的信息
    public Girl findOne(Integer id){
        return girlRepository.findById(id).get();
    }
}
