package com.sportteam.spring.model;

public enum UserRole {
    ADMIN, USER;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}
