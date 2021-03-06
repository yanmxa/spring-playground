package com.nood.hrm.security.user;

import com.nood.hrm.model.Permission;
import com.nood.hrm.model.Role;
import com.nood.hrm.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class LoginUser implements UserDetails {

    private List<Permission> permissions;
    private List<Role> roles;
    private String userId;
    private String departmentId;
    private String password;
    private String userName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.parallelStream()
                .filter(p -> !StringUtils.isEmpty(p.getPermission()))
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * 是否过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 是否锁定
     *
     * @return
     */
//    @Override
//    public boolean isAccountNonLocked() {
//        return getStatus() != Status.LOCKED;
//    }

    /**
     * 凭证是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


    /**
     * 判断是否为admin用户
     *
     * @return
     */
//    public boolean isAdmin() {
//        return isAdmin(this.get.intValue());
//    }

    public static boolean isAdmin(Integer userId) {
        return userId != null && 1 == userId;
    }
}
