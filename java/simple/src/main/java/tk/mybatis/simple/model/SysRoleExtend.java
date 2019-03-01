package tk.mybatis.simple.model;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/28 09:24
 * @Description:
 */
public class SysRoleExtend extends SysRole {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
