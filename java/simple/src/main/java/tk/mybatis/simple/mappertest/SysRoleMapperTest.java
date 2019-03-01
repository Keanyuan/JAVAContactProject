package tk.mybatis.simple.mappertest;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.mapper.RoleMapper;
import tk.mybatis.simple.model.SysRole;

import java.util.Date;
import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/28 14:15
 * @Description:
 */
public class SysRoleMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById(){
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById(1L);
            Assert.assertNotNull(role);
            Assert.assertEquals("管理员", role.getRoleName());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById2(){
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById2(1L);
            Assert.assertNotNull(role);
            Assert.assertEquals("管理员", role.getRoleName());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectALL(){
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roleList = roleMapper.seleAll();
            Assert.assertNotNull(roleList);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = new SysRole();
            role.setId(3L);
            role.setRoleName("name");
            role.setEnabled(0);
            role.setCreateBy(1L);
            role.setCreateTime(new Date());
            int result = roleMapper.insert(role);
            SysRole role1 = roleMapper.selectById(3L);
            System.out.println(role1.getRoleName());

            Assert.assertEquals(1, result);
            Assert.assertEquals("name", role1.getRoleName());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2(){
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = new SysRole();
            role.setRoleName("name");
            role.setEnabled(0);
            role.setCreateBy(1L);
            role.setCreateTime(new Date());
            int result = roleMapper.insert2(role);
            List<SysRole> roleList = roleMapper.seleAll();
            Assert.assertEquals(1, result);
            Assert.assertEquals(3, roleList.size());
            System.out.println(roleList.get(roleList.size() - 1).getRoleName());

        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    @Test
    public void updateById(){
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById(2L);
            role.setCreateTime(new Date());
            int result = roleMapper.updateById(role);
            Assert.assertEquals(1, result);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    @Test
    public void deleteById(){
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            int result = roleMapper.deleteById(2L);
            Assert.assertEquals(1, result);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }



}
