package com.anjiplus.product.service.impl;

import com.anjiplus.product.dataobject.ProductInfo;
import com.anjiplus.product.dto.CartDTO;
import com.anjiplus.product.enums.ProductStatusEnum;
import com.anjiplus.product.enums.ResultEnum;
import com.anjiplus.product.exception.SellException;
import com.anjiplus.product.repository.ProductInfoRepository;
import com.anjiplus.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Kean
 * @Date: 2018/8/21 下午11:57
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;
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
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList){
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            //结果小于0 库存不足
            if (result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);

            repository.save(productInfo);
        }
    }
}
