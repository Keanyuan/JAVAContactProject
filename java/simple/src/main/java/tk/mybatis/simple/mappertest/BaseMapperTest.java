package tk.mybatis.simple.mappertest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.Reader;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/27 14:37
 * @Description:
 */
public class BaseMapperTest {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init(){
        try {
            //通过 Resources 工具类将 mybatis-config.xml 配置文件读入 Reader
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            //再通过 SqlSessionFactoryBuilder 建造类使用 Reader 创建 SqlSessionFactory 工厂对象。
            //在创建 SqlSessionFactory 对象的过程中 ， 首先解析 mybatis-config.xml 配置 文件，
            // 读取配置文件中的 mappers 配置后会读取全部的 Mapper.xml 进行具体方法的解析，
            // 在这些解析完成后， SqlSessionFactory 就包含了所有的属性配置和执行 SQL 的信息。
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SqlSession getSqlSession(){
        return  sqlSessionFactory.openSession();
    }
}
