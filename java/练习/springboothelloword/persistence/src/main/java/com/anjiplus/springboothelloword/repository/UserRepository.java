package com.anjiplus.springboothelloword.repository;

import com.anjiplus.springboothelloword.user.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: Kean
 * @Date: 2018/8/22 下午7:14
 * @Description:
 * {@link User} {@link Repository}
 */
@Repository
public class UserRepository {


    private final ConcurrentMap<Integer, User> repository = new ConcurrentHashMap<>();

    private final static AtomicInteger idGenerator = new AtomicInteger();

    public boolean save(User user){
        //id 从1 开始
        Integer id = idGenerator.incrementAndGet();
        user.setId(id);
        return repository.put(id, user) == null;
    }

    //返回用户列表
    public Collection<User> findAll(){
        return repository.values();
    }

}
