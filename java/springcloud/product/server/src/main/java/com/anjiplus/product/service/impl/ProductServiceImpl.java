package com.anjiplus.product.service.impl;

import com.anjiplus.product.common.DecreaseStockInput;
import com.anjiplus.product.common.ProductInfoOutput;
import com.anjiplus.product.dataobject.ProductInfo;
import com.anjiplus.product.dto.CartDTO;
import com.anjiplus.product.enums.ProductStatusEnum;
import com.anjiplus.product.enums.ResultEnum;
import com.anjiplus.product.exception.SellException;
import com.anjiplus.product.repository.ProductInfoRepository;
import com.anjiplus.product.service.ProductService;
import com.anjiplus.product.utils.JsonUtil;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Kean
 * @Date: 2018/8/21 下午11:57
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;

    @Autowired
    private AmqpTemplate amqpTemplate;


    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    //根据订单ID list 查询
    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return repository.findByProductIdIn(productIdList);
    }

    /*减库存*/
    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);



        //发送MQ扣库存消息
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e -> {
            ProductInfoOutput output = new ProductInfoOutput();
            BeanUtils.copyProperties(e, output);
            return output;
        }).collect(Collectors.toList());

        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput: decreaseStockInputList){
            ProductInfo productInfo = repository.findById(decreaseStockInput.getProductId()).get();
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            //结果小于0 库存不足
            if (result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);

            repository.save(productInfo);

            productInfoList.add(productInfo);
        }

        return productInfoList;
    }



}
