package tk.mybatis.simple.mappertest;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.mapper.UserMapper;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/27 14:49
 * @Description:
 */
public class UserMapperTest extends BaseMapperTest {

    //查询用户表中用户id为1 的数据
    @Test
    public void testSelectById(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById((long) 1);
            Assert.assertNotNull(user);
            Assert.assertEquals("admin", user.getUserName());
        } finally {
            sqlSession.close();
        }
    }



    @Test
    public void selectByidOrUserName(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            //只查询用户名时
            SysUser query = new SysUser();
            query.setId(1L);
            query.setUserName("admin");
            SysUser user = userMapper.selectByidOrUserName(query);
            Assert.assertNotNull(user);

            //当没有工d 时
            query.setId(null);
            user = userMapper.selectByidOrUserName(query);
            Assert.assertNotNull(user);

            //当工d 和 name 都为空时
            query.setId(null);
            query.setUserName(null);
            user = userMapper.selectByidOrUserName(query);
            Assert.assertNull(user);

        } finally {
            sqlSession.close();
        }
    }

    //查询所有用户表中的数据
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = userMapper.selectAll();
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size() > 0);
        }finally {
            sqlSession.close();
        }
    }

    //内联三张表， 查询所有sys_role表中的数据是 用户表与用户角色关联表的用户id相等，角色表与用户角色关联表中的角色id相等
    @Test
    public void testSelectRoleByUserId(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
            Assert.assertNotNull(roleList);
            Assert.assertTrue(roleList.size() > 0);
        }finally {
            sqlSession.close();
        }
    }

    //插入数据
    @Test
    public void testInsert(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserEmail("test1@mybatis.tk");
            user.setUserInfo("test1 info");
            //正常情况下应该读入一张图片存入byte数组中
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            int resule = userMapper.insert(user);
            Assert.assertEquals(1, resule);
            Assert.assertNull(user.getId());
        }finally {
            //为了不影响其他测试，这里选择回滚
            // 由于默认的 sqlSessionFactory .openSession()是不自动提交的
            // 因此不手动执行 commit 也不会提交到数据库
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    //插入数据
    @Test
    public void testInsert2(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserEmail("test1@mybatis.tk");
            user.setUserInfo("test1 info");
            //正常情况下应该读入一张图片存入byte数组中
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            int resule = userMapper.insert2(user);
            Assert.assertEquals(1, resule);
            Assert.assertNotNull(user.getId());
            //因为id回写，所以id不为 null
            System.out.println(user.getId());
        }finally {
            //为了不影响其他测试，这里选择回滚
            // 由于默认的 sqlSessionFactory .openSession()是不自动提交的
            // 因此不手动执行 commit 也不会提交到数据库
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //插入数据
    @Test
    public void testInsert3(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserEmail("test1@mybatis.tk");
            user.setUserInfo("test1 info");
            //正常情况下应该读入一张图片存入byte数组中
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            int resule = userMapper.insert3(user);
            Assert.assertEquals(1, resule);
            Assert.assertNotNull(user.getId());
            //因为id回写，所以id不为 null
            System.out.println(user.getId());
        }finally {
            //为了不影响其他测试，这里选择回滚
            // 由于默认的 sqlSessionFactory .openSession()是不自动提交的
            // 因此不手动执行 commit 也不会提交到数据库
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    @Test
    public void testUpdateById(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(1L);
            Assert.assertEquals("admin", user.getUserName());
            user.setUserName("admin_test");
            user.setUserEmail("test@mybaties.tk");
            int result = userMapper.updateById(user);
            Assert.assertEquals(1, result);
            user = userMapper.selectById(1L);
            Assert.assertEquals("admin_test", user.getUserName());

        }finally {
            sqlSession.rollback();;
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByMap(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", 1L);
            map.put("user_email", "test@mybais.tk");
            map.put("user_password", "12345678");
            userMapper.updateByMap(map);
            SysUser user = userMapper.selectById(1L);

            System.out.println(user.getUserPassword());
            Assert.assertEquals("12345678", user.getUserPassword());

        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }




    @Test
    public void tetDeleteById(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = userMapper.selectById(1L);
            Assert.assertNotNull(sysUser);
            //调用方法删除，注意这里使用参数为 id
            Assert.assertEquals(1, userMapper.deleteById(1L));
            Assert.assertNull(userMapper.selectById(1L));

            SysUser sysUser1 = userMapper.selectById(1001L);
            Assert.assertNotNull(sysUser1);
            //调用方法删除，注意这里使用参数为 user
            Assert.assertEquals(1, userMapper.deleteById(sysUser1));
            Assert.assertNull(userMapper.selectById(1001L));
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    @Test
    public void testSelectRolesByUserIdAndRoleEnabled(){
        SqlSession sqlSession = getSqlSession();
        try {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> userList = userMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size() > 0);

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectRolesByUserAndRole(){
        SqlSession sqlSession = getSqlSession();

        try {

//            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//            SysUser sysUser;
//            SysRole sysRole;
//            sysUser.setId(1L);
//            sysRole.setEnabled(1);
//            List<SysRole> userList = userMapper.selectRolesByUserAndRole(sysUser, sysRole);
//            Assert.assertNotNull(userList);
//            Assert.assertTrue(userList.size() > 0);

        }finally {
            sqlSession.close();
        }
    }



    @Test
    public void selectByUser(){
        SqlSession sqlSession = getSqlSession();

        try {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser query = new SysUser();
            query.setUserName("ad");
            //There is no getter for property named 'userEmial' in 'class tk.mybatis.simple.model.SysUser'
            List<SysUser> userList = userMapper.selectByUser(query);
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size() == 0);


            query = new SysUser();
            query.setUserEmail("test@mybatis.tk");
            List<SysUser> userList1 = userMapper.selectByUser(query);
            Assert.assertNotNull(userList1);
            Assert.assertTrue(userList1.size() == 0);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectByUser1(){
        SqlSession sqlSession = getSqlSession();

        try {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser query = new SysUser();
            query.setUserName("test");
            //There is no getter for property named 'userEmial' in 'class tk.mybatis.simple.model.SysUser'
            List<SysUser> userList = userMapper.selectByUser1(query);
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size() > 0);

            query = new SysUser();
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser1(query);
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size() > 0);


            query = new SysUser();
            query.setUserName("ad");
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser1(query);
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size() == 0);


        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void selectByUser2(){
        SqlSession sqlSession = getSqlSession();

        try {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser query = new SysUser();
            query.setUserName("test");
            //There is no getter for property named 'userEmial' in 'class tk.mybatis.simple.model.SysUser'
            List<SysUser> userList = userMapper.selectByUser2(query);
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size() > 0);

            query = new SysUser();
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser2(query);
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size() > 0);


            query = new SysUser();
            query.setUserName("ad");
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser2(query);
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size() == 0);


        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByidList(){
        SqlSession sqlSession = getSqlSession();
        try {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> idList = new ArrayList<Long>();
            idList.add(1L);
            idList.add(1001L);
            //业务逻辑中必须校验 idList.size() > 0
            List<SysUser> userList = userMapper.selectByidList(idList);
            Assert.assertEquals(2, userList.size());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByidList1(){
        SqlSession sqlSession = getSqlSession();
        try {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Long[] longs = {1L, 1001L};
            //业务逻辑中必须校验 idList.size() > 0
            List<SysUser> userList = userMapper.selectByidList1(longs);
            Assert.assertEquals(2, userList.size());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    @Test
    public void testUpdateByldSelective(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(1L);
            Assert.assertEquals("admin", user.getUserName());
            user.setUserName("admin_test");
            user.setUserEmail("test@mybaties.tk");
            int result = userMapper.updateByldSelective(user);
            Assert.assertEquals(1, result);
            user = userMapper.selectById(1L);
            Assert.assertEquals("admin_test", user.getUserName());

        }finally {
            sqlSession.rollback();;
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByldSelective1(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(1L);
            Assert.assertEquals("admin", user.getUserName());
            user.setUserName("admin_test");
            user.setUserEmail("test@mybaties.tk");
            int result = userMapper.updateByldSelective1(user);
            Assert.assertEquals(1, result);
            user = userMapper.selectById(1L);
            Assert.assertEquals("admin_test", user.getUserName());

        }finally {
            sqlSession.rollback();;
            sqlSession.close();
        }
    }


    //插入数据
    @Test
    public void testInsert4(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
//            user.setUserEmail("test1@mybatis.tk");
            user.setUserInfo("test1 info");
            //正常情况下应该读入一张图片存入byte数组中
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            int resule = userMapper.insert4(user);
            Assert.assertEquals(1, resule);
            Assert.assertNotNull(user.getId());
            //因为id回写，所以id不为 null
            System.out.println(user.getId());
        }finally {
            //为了不影响其他测试，这里选择回滚
            // 由于默认的 sqlSessionFactory .openSession()是不自动提交的
            // 因此不手动执行 commit 也不会提交到数据库
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    @Test
    public void testInsertList(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = new ArrayList<SysUser>();
            for (int i = 0; i < 2; i++) {
                SysUser user = new SysUser();
                user.setUserName("test" + 1);
                user.setUserPassword("123456");
                user.setUserEmail("test@mybatis.tk");
                userList.add(user);
            }
            int result = userMapper.insertList(userList);

            List<SysUser> sysUserList = userMapper.selectAll();
            for (SysUser user : sysUserList) {
                System.out.println(user.getId() + " , " + user.getUserName());
            }
            Assert.assertEquals(2, result);


        }finally {
            //为了不影响其他测试，这里选择回滚
            // 由于默认的 sqlSessionFactory .openSession()是不自动提交的
            // 因此不手动执行 commit 也不会提交到数据库
            sqlSession.rollback();
            sqlSession.close();
        }
    }



    @Test
    public void testSelectUserAndRoleById(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> sysUserList = userMapper.selectUserAndRoleById(1L);
            Assert.assertNotNull(sysUserList);

        }finally {
            sqlSession.close();
        }
    }



    @Test
    public void testSelectUserAndRoleByidSelect(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> sysUserList = userMapper.selectUserAndRoleByidSelect(1L);
            //查询getRole的时候才调用再次查询
            System.out.println(sysUserList.get(0).getRole());
            Assert.assertNotNull(sysUserList);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserAndRoles(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> sysUserList = userMapper.selectAllUserAndRoles();
            Assert.assertNotNull(sysUserList);
            System.out.println("用户数： " + sysUserList.size());

            printList(sysUserList);
        }finally {
            sqlSession.close();
        }
    }


    public static void  printList(List<SysUser> list){
        for (SysUser user : list) {
            System.out.print("用户名： " + user.getUserName());
            for (SysRole role : user.getRoleList()) {
                System.out.print(" 角色名： " + role.getRoleName());
                for (SysPrivilege privillege : role.getPrivilegeList()) {
                    System.out.print("权限名： " + privillege.getPrivilegeName());
                }
            }
            System.out.println();
        }
    }

    @Test
    public void testSelectAllUserAndRoles1(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> sysUserList = userMapper.selectAllUserAndRoles1();
            Assert.assertNotNull(sysUserList);
            System.out.println("用户数： " + sysUserList.size());

            printList(sysUserList);
        }finally {
            sqlSession.close();
        }
    }


}
