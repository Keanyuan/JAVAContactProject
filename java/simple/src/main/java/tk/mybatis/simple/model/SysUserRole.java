package tk.mybatis.simple.model;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/27 13:56
 * @Description:
 */
public class SysUserRole {
    /**
     * 用户ID
     */
    private Long userId;


    /**
     * 角色ID
     */
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
