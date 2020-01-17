package com.anjiplus.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    @Test
    public void contextLoads() {
    }

}
/*
*
*
*
<select id="queryUserSimplyInfoById"
          resultType="com.anji.plus.mirror.model.po.SysUserPO"
          parameterType="java.lang.String">
    select
    id,
    username,
    nickname
    from
    sys_user
    where
	  id = #{id,jdbcType=VARCHAR}
  </select>

  //    通过Id查询用户信息
    List<SysUserPO> queryUserSimplyInfoById(String id);



* */
