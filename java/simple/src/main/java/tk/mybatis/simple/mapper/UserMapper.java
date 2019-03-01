package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/27 14:12
 * @Description:
 */
public interface UserMapper {

    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    SysUser selectById(Long id);

    /**
     * 根据用户id或用户名查询
     * @param sysUser
     * @return
     */
    SysUser selectByidOrUserName(SysUser sysUser);


    /**
     * 查询全部用户
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 根据动态条件查询用户信息
     * @return
     */
    List<SysUser> selectByUser(SysUser sysUser);

    /**
     * 根据动态条件查询用户信息 SQL查询添加判断条件
     * @return
     */
    List<SysUser> selectByUser1(SysUser sysUser);

    /**
     * 根据动态条件查询用户信息 SQL查询添加判断条件 添加where
     * @return
     */
    List<SysUser> selectByUser2(SysUser sysUser);


    /**
     * 根据用户id集合查询
     * @param idList 集合
     * @return
     */
    List<SysUser> selectByidList(List<Long> idList);


    /**
     * 根据用户id集合查询
     * @param idArray 数组
     * @return
     */
    List<SysUser> selectByidList1(Long[] idArray);


    /**
     * 根据用户id获取角色信息
     * @param userId
     * @return
     */
    List<SysRole> selectRolesByUserId(Long userId);


    /**
     * 查询接口 关联角色表 关联的嵌套结果映射。
     * @param id
     * @return
     */
    List<SysUser> selectUserAndRoleById(Long id);


    /**
     * 查询接口 关联角色表 关联的嵌套结果映射
     * @param id
     * @return
     */
    List<SysUser> selectUserAndRoleByidSelect(Long id);


    /**
     * 获取所有的用户以及对应的所有角色
     * @return
     */
    List<SysUser> selectAllUserAndRoles();


    /**
     * 获取所有的用户以及对应的所有角色 双层嵌套
     * @return
     */
    List<SysUser> selectAllUserAndRoles1();
    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    int insert(SysUser sysUser);


    /**
     * 新增用户 自动添加id
     * @param sysUser
     * @return
     */
    int insert2(SysUser sysUser);


    /**
     * 新增用户 使用selectKey
     * @param sysUser
     * @return
     */
    int insert3(SysUser sysUser);

    /**
     * 新增用户 添加if语句
     * @param sysUser
     * @return
     */
    int insert4(SysUser sysUser);


    /**
     * foreach 实现批量插入
     * @param userList
     * @return
     */
    int insertList(List<SysUser> userList);


    /**
     * 根据主键更新
     * @param sysUser
     * @return
     */
    int updateById(SysUser sysUser);


    /**
     * 根据主键更新
     * @param sysUser
     * @return
     */
    int updateByldSelective(SysUser sysUser);

    /**
     * 根据主键更新 添加 <set></set>
     * @param sysUser
     * @return
     */
    int updateByldSelective1(SysUser sysUser);


    /**
     * 通过map更新列 ---  佛reach实现动态UPDATE
     * @param map
     * @return
     */
    int updateByMap(Map<String, Object> map);


    /**
     * 通过主键删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 通过主键删除
     * @param user
     * @return
     */
    int deleteById(SysUser user);


    /**
     *
     * @param userId
     * @param enabled
     * @return
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId")Long userId, @Param("enabled") Integer enabled);

    /**
     * 根据用户id和角色的enabled状态获取用户角色
     * @param user
     * @param role
     * @return
     */
    List<SysRole> selectRolesByUserAndRole(@Param("user") SysUser user, @Param("role") SysRole role);








}
