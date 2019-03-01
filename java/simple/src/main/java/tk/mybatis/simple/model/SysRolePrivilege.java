package tk.mybatis.simple.model;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/27 14:05
 * @Description: 角色权限关联表
 */
public class SysRolePrivilege {
    /**
     * 角色ID
     */
    private Long roleId;


    /**
     * 权限ID
     */
    private Long privilegeId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }
}
