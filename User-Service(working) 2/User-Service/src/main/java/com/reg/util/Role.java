package com.reg.util;

public enum Role {
	ADMIN,
    USER;
    
    public static Role[] getAllRoles() {
        return Role.values();
    }
}
