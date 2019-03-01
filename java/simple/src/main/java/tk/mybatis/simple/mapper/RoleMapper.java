package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.*;
import tk.mybatis.simple.model.SysRole;

import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/27 14:12
 * @Description:
 */
public interface RoleMapper {

    //定义一个Results
    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time")
    })
//    @Select({"select id, role_name roleName, enabled, create_by createBy, create_time createTime from sys_role where id = #{id}"})
    @Select({
            "select id, role_name, enabled, create_by, create_time ",
            "from sys_role ",
            "where id = #{id}"
    })
    SysRole selectById(Long id);


    //xml中的resultMap元素有一个对应的 Java注解@Results，使用这个注解来实现属性 映射，新增 一个 selectById2 方法
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select("select id, role_name, enabled, create_by, create_time from sys_role where id = #{id}")
    SysRole selectById2(Long id);


    //引用这个@ Results。
    @ResultMap("roleResultMap")
    @Select("select * from sys_role")
    List<SysRole> seleAll();


    /**
     * 通过的查询
     * @param id
     * @return
     */
    SysRole selectRoleById(Long id);


    @Insert("insert into sys_role(id, role_name, enabled, create_by, create_time) values(#{id}, #{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType = TIMESTAMP})")
    int insert(SysRole sysRole);

    //自增主键
    @Insert("insert into sys_role(role_name, enabled, create_by, create_time) values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType = TIMESTAMP})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert2(SysRole sysRole);

    @Update({"update sys_role set role_name = #{roleName}, enabled = #{enabled}, create_by = #{createBy}, create_time = #{createTime, jdbcType = TIMESTAMP} where id = #{id}"})
    int updateById(SysRole sysRole);

    @Delete("delete from sys_role where id = #{id}")
    int deleteById(Long id);


}


