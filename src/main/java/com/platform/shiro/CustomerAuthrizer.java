package com.platform.shiro;

import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;

import com.platform.entity.BrandUserEntity;
import com.platform.entity.SysUserEntity;

public class CustomerAuthrizer extends ModularRealmAuthorizer {
	@Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        assertRealmsConfigured();
        Object primaryPrincipal = principals.getPrimaryPrincipal();
 
        for (Realm realm : getRealms()) {
            if (!(realm instanceof Authorizer)) continue;
            if (primaryPrincipal instanceof BrandUserEntity) {
                if (realm instanceof BrandAdminRealm) {
                    return ((BrandAdminRealm) realm).isPermitted(principals, permission);
                }
            }
            if (primaryPrincipal instanceof SysUserEntity) {
                if (realm instanceof UserRealm) {
                    return ((UserRealm) realm).isPermitted(principals, permission);
                }
            }
 
        }
        return false;
    }

}
