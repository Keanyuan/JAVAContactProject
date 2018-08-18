package com.anjiplus.sell.dataobject.mapper;

import com.anjiplus.sell.dataobject.ProductCategory;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Kean
 * @Date: 2018/8/17 下午11:09
 * @Description:
 */
public interface ProductCategoryMapper {


    @Insert("insert into product_category(category_name, category_type) values (#{category_name, jdbcType=VARCHAR}, #{category_type, jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);



    @Insert("insert into product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")

    })
    ProductCategory findByCatrgoryType(Integer categoryType);



    @Select("select * from product_category where category_name = #{categoryName}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")

    })
    List<ProductCategory> findByCatrgoryName(String categoryName);

    @Update("update product_category set category_name= #{categoryName} where category_type= #{categoryType}")
    int updateByCatrgoryType(@Param("categoryName") String categoryName, @Param("catrgoryType") Integer catrgoryType);


    @Update("update product_category set category_name= #{categoryName} where category_type= #{categoryType}")
    int updateByObject(ProductCategory productCategory);


    @Delete("delete from product_category where category_type= #{categoryType}")
    int deleteByCatrgoryType(Integer catrgoryType);


    //声明
    ProductCategory selectfindByCatrgoryType(Integer categoryType);
}
