package com.artem.library.model;

import lombok.Getter;

@Getter
public enum Permission {

    BOOKS_READ("books:read"),
    BOOKS_WRITE("books:write"),
    CLIENTS_READ("clients:read"),
    CLIENTS_WRITE("clients:write"),
    EMPLOYEES_READ("employees:read"),
    EMPLOYEES_WRITE("employees:write");
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }
}
