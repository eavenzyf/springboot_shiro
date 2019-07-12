package com.pm.springboot_shiro.config;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * @Classname ShiroConfig
 * @Description TODO
 * @Date 2019/7/12 8:32
 * @Created by Eaven
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroBean = new ShiroFilterFactoryBean();
        //必须设置SecurityManager
        shiroBean.setSecurityManager(securityManager);
        //设置登录界面的url
        shiroBean.setLoginUrl("/");
        //设置登录成功后跳转的url
        shiroBean.setSuccessUrl("/back/home");
        //设置无权限时跳转的url
        shiroBean.setUnauthorizedUrl("/");
        //拦截器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //可以直接访问的连接,不拦截
        filterChainDefinitionMap.put("/back/login","anon");
        //filterChainDefinitionMap.put("/static/**","anon");
        //静态资源访问一定要跟下面一样，而不能跟上面一样
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/assets/**","anon");
        filterChainDefinitionMap.put("/font/**","anon");
        filterChainDefinitionMap.put("/images/**","anon");
        filterChainDefinitionMap.put("/lay/**","anon");
        filterChainDefinitionMap.put("/front/**","anon");
        //退出过滤器，shiro帮我们实现
        filterChainDefinitionMap.put("/logout","logout");
        //其余接口一律拦截，需要认证
        filterChainDefinitionMap.put("/**","authc");
        shiroBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroBean;
    }
    @Bean
    public SecurityManager securityManager(CustomRealm customRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        return securityManager;
    }
    @Bean
    public CustomRealm customRealm(){
        CustomRealm customRealm = new CustomRealm();
        return customRealm;
    }
}
