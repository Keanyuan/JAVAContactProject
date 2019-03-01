package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.provider.PrivilegeProvider;

import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/27 14:12
 * @Description: @SelectProvider 、@ InsertProvider 、@ UpdateProvider
 * 和@ DeleteProvider 。 实现查询、插入、更新、删除操作。
 */
public interface PrivilegeMapper {

    @SelectProvider(type = PrivilegeProvider.class, method = "selectById")
    SysPrivilege selectById(Long id);
}
