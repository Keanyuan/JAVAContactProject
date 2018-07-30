package com.anjiPlus.beanannotation.multibean;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//使数组有序
@Order(value = 2)
@Component
public class BeanImplOne implements BeanInterface {
}
