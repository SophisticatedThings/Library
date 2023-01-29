package com.artem.library.model;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum Role {
    ADMIN(Set.of( Permission.CLIENTS_READ,Permission.EMPLOYEES_READ,Permission.EMPLOYEES_WRITE)),
    MANAGER(Set.of(Permission.EMPLOYEES_READ,Permission.EMPLOYEES_WRITE)),
    LIBRARIAN(Set.of(Permission.BOOKS_READ, Permission.BOOKS_WRITE, Permission.CLIENTS_WRITE,Permission.CLIENTS_READ));
    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){

        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

    }
}
