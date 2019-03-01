package tk.mybatis.simple.mappertest;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import test.dao.CountryMapper;
import test.model.Country;
import test.model.CountryExample;

import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2019/3/1 14:16
 * @Description:
 */
public class CountryMapperTest extends BaseMapperTest {



    @Test
    public void countryTest(){
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            CountryExample countryExample = new CountryExample();
            countryExample.setDistinct(true);
            CountryExample.Criteria criteria = countryExample.createCriteria();
            // id >= 1
            criteria.andIdGreaterThanOrEqualTo(1);
            //id < 4
            criteria.andIdLessThan(4);

            // 添加通配符
            criteria.andCountrycodeLike("%U%");

            CountryExample.Criteria or = countryExample.or();
            or.andCountrynameEqualTo("中国");

            List<Country> countryList = countryMapper.selectByExample(countryExample);
            System.out.println(countryList.size());

        }finally {
            sqlSession.close();
        }
    }
}
