package com.anjiplus.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/*
* 类目
* product_category
* @DynamicUpdate 自动更新时间
* @Data lombok自动引入setter getter tostring等方法
* */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    /** 类目id 主键.  GeneratedValue自增类型
     * 使用自增长主键生成策略中声明strategy = GenerationType.IDENTITY
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. 不能重复输入 */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

}
