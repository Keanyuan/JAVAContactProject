package tk.mybatis.simple.mappertest;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.mapper.PrivilegeMapper;
import tk.mybatis.simple.model.SysPrivilege;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/28 16:04
 * @Description:
 */
public class PrivilegeMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById(){
        SqlSession sqlSession = getSqlSession();
        try {
            PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
            SysPrivilege privilege = privilegeMapper.selectById(1L);
            Assert.assertNotNull(privilege);
            Assert.assertEquals("用户管理", privilege.getPrivilegeName());
        }finally {
            sqlSession.close();
        }
    }
}
