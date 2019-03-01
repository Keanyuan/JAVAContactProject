package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tk.mybatis.simple.mappertest.BaseMapperTest;
import tk.mybatis.simple.model.Country;

import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/27 11:09
 * @Description:
 */
public class CountryMapperTest extends BaseMapperTest {

    
    @Test
    public void testSelectAll(){
        //使用时通过 SqlSessionFactory 工厂对象获取一个 SqlSessionon
        SqlSession sqlSession = getSqlSession();
        try {
            //通过 SqlSession 的 selectList 方法查找到 CountryMapper.xml 中
            // id=”selectAll”的方法，执行 SQL查询。
            List<Country> countryList = sqlSession.selectList(" tk.mybatis.simple.mapper.CountryMapper.selectAll");
            printCountryList(countryList);
        } finally {
            //关闭sqlSession
            sqlSession.close();
        }
    }
    
    private void printCountryList(List<Country> countryList){
        for (Country country : countryList ) {
            System.out .printf ("{id : %-4d, countryname: %4s, countrycode : %4s}\n",
                    country.getId(), country.getCountryname(), country.getCountrycode());
        }
    }
}
