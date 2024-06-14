/**
 *   Copyright 2019 Yanzheng (https://github.com/micyo202). All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.lion.auth.service;

import com.lion.auth.entity.SysRole;
import com.lion.auth.entity.SysUser;
import com.lion.auth.manager.UpmsManager;
import com.lion.common.exception.LionException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * CustomUserDetailsService
 * 用户授权认证实现类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/04/10
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UpmsManager upmsManager;

    /**
     * 角色前缀
     */
    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    public UserDetails loadUserByUsername(String username) {

        // 获取用户信息
        SysUser sysUser = upmsManager.getUserByUsername(username);

        if (ObjectUtils.isEmpty(sysUser)) {
            throw new LionException("用户'" + username + "'不存在");
        }

        // 获取角色信息
        List<SysRole> sysRoles = upmsManager.getRoleByUserId(sysUser.getId());
        if (ObjectUtils.isEmpty(sysRoles)) {
            throw new LionException("用户'" + username + "'没有对应的角色信息，请配置");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet();
        sysRoles.stream().forEach(sysRole -> {
            // 角色必须是 ROLE_ 开头，可以在数据库中设置（这里在程序中设置）
            grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + sysRole.getCode().toUpperCase()));

            // 获取菜单列表
            /*
            List<SysMenu> sysMenus = upmsManager.getMenuByRoleId(sysRole.getId());
            if (ObjectUtils.isEmpty(sysMenus)) {
                throw new LionException("用户'" + username + "'所在角色没有菜单信息，请配置");
            } else {
                sysMenus.stream().forEach(sysMenu -> grantedAuthorities.add(new SimpleGrantedAuthority(sysMenu.getCode())));
            }
            */
        });

        return new org.springframework.security.core.userdetails.User(
                sysUser.getUsername(),
                sysUser.getPassword(),
                sysUser.getEnabled(),
                sysUser.getAccountNonExpired(),
                sysUser.getCredentialsNonExpired(),
                sysUser.getAccountNonLocked(),
                grantedAuthorities);
    }
}