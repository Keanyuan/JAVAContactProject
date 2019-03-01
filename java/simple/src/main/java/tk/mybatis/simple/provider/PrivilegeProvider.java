package tk.mybatis.simple.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/28 15:58
 * @Description:
 */
public class PrivilegeProvider {

    public String selectById(final Long id){
        return "select id, privilege_name, privilege_url from sys_privilege where id = #{id}";
//        return new SQL(){
//            {
//                SELECT("id, privilege_name, privilege_url");
//                FROM("sys_privilege");
//                WHERE("id = #{id}");
//            }
//        }.toString();
    }
}
