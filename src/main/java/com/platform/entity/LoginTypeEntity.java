package com.platform.entity;

public enum LoginTypeEntity {

	BRANDADMIN("BrandAdmin"),  ADMIN("UserRealm");

    private String type;

    private LoginTypeEntity(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }
}
