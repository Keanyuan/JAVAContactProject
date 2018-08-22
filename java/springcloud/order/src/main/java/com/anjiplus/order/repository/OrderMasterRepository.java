package com.anjiplus.order.repository;

import com.anjiplus.order.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/14 10:58
 * @Description: dao层 订单列表接口
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
