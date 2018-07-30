package com.anjiPlus.beanannotation.multibean;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
public class BeanImplTwo implements BeanInterface {
}
