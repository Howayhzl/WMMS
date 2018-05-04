package com.ncms.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.ncms.comm.base.loginInfo.SysUserVO;
import com.ncms.service.user.SysUserService;

/**
 * Created by Administrator on 2017/8/2.
 */
public class ShiroRealm extends AuthorizingRealm{

    @Autowired
    private SysUserService userService;

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //存放登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        
        String username = token.getUsername();
	    String password = new String((char[])token.getCredentials()); 	//得到密码
	    
        SysUserVO user = userService.login(username);
        /**检测是否有此用户 **/
	    if(user == null){
	    	throw new UnknownAccountException();//没有找到账号异常
	    }
	    /**检验账号是否被锁定 **/
	    if("9".equals(user.getUserState())){
	    	throw new LockedAccountException();//抛出账号锁定异常
	    }
	    /**AuthenticatingRealm使用CredentialsMatcher进行密码匹配**/
	    if(null != username && null != password){
            return new SimpleAuthenticationInfo(user, user.getUserPassword(), getName());
	    }else{
	    	return null;
	    }
    }

    /**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法(non-Javadoc)
	 * @see AuthorizingRealm#doGetAuthorizationInfo(PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//		String username = (String)pc.getPrimaryPrincipal();
//	    authorizationInfo.setRoles(userService.getRoles(username));
//	    authorizationInfo.setStringPermissions(userService.getPermissions(username));
	    return authorizationInfo;
	}
	
	 @Override
	 public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		 super.clearCachedAuthorizationInfo(principals);
	 }

	 @Override
	 public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
	     super.clearCachedAuthenticationInfo(principals);
	 }

	 @Override
	 public void clearCache(PrincipalCollection principals) {
	      super.clearCache(principals);
	 }

}
