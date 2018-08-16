package com.anjiplus.sell.form;

import lombok.Data;

/**
 * @Auther: Kean
 * @Date: 2018/8/16 下午11:46
 * @Description:
 */
@Data
public class CategoryForm {
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
