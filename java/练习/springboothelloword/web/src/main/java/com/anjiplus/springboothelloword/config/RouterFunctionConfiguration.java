package com.anjiplus.springboothelloword.config;

import com.anjiplus.springboothelloword.repository.UserRepository;
import com.anjiplus.springboothelloword.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.util.Collection;

/**
 * @Auther: Kean
 * @Date: 2018/8/22 下午7:39
 * @Description: 路由器函数
 */
@Configuration
public class RouterFunctionConfiguration {

    @Bean
    @Autowired
    public RouterFunction<ServerResponse> persionFindAll(UserRepository userRepository){
        return RouterFunctions.route(RequestPredicates.GET("/presion/find/all"),
                request -> {
                    Collection<User> users = userRepository.findAll();
                    Flux<User> userFlux = Flux.fromIterable(users);
                    return ServerResponse.ok().body(userFlux, User.class);
                });
    }
}
