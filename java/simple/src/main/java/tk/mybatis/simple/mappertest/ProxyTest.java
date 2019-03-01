package tk.mybatis.simple.mappertest;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tk.mybatis.simple.mapper.UserMapper;
import tk.mybatis.simple.model.SysUser;
import tk.mybatis.simple.proxy.MyMapperProxy;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/28 13:52
 * @Description:
 */
public class ProxyTest extends BaseMapperTest{

    @Test
    public void proxtTest(){
        SqlSession sqlSession = getSqlSession();
        MyMapperProxy userMapperPoxy = new MyMapperProxy(UserMapper.class, sqlSession);
        UserMapper userMapper = (UserMapper)Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{UserMapper.class},
                userMapperPoxy);
        List<SysUser> user = userMapper.selectAll();
    }
}
