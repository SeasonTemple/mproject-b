package com.seasontemple.mproject.web.shiro.config;

import com.seasontemple.mproject.web.shiro.cache.CustomCacheManager;
import com.seasontemple.mproject.web.shiro.jwt.JwtDefaultSubjectFactory;
import com.seasontemple.mproject.web.shiro.jwt.JwtFilter;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Season Temple
 * @program: mproject
 * @description: shiro配置类
 * @create: 2020/03/29 23:02:33
 */
@Configuration
public class ShiroConfig {

    /**
     * 告诉shiro不要使用默认的DefaultSubject创建对象，因为不能创建Session
     */
    @Bean
    public SubjectFactory subjectFactory() {
        return new JwtDefaultSubjectFactory();
    }

    @Bean
    public Realm realm() {
        ShiroRealm shiroRealm = new ShiroRealm();
//        shiroRealm.setCredentialsMatcher(customCredentialsMatcher());
        /* 允许认证缓存 */
//        shiroRealm.setAuthenticationCachingEnabled(true);
//        shiroRealm.setAuthenticationCacheName("authenticationCache");
        /* 允许授权缓存 */
        shiroRealm.setAuthorizationCachingEnabled(true);
//        shiroRealm.setAuthorizationCacheName("authorizationCache");
        shiroRealm.setCachingEnabled(true);
        shiroRealm.setCacheManager(new CustomCacheManager());
        return shiroRealm;
    }

    @Bean(name = "credentialsMatcher")
    public CustomCredentialsMatcher customCredentialsMatcher() {
        /* HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        // storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);*/
        return new CustomCredentialsMatcher();
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关闭 ShiroDAO 功能
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        // 不需要将 Shiro Session 中的东西存到任何地方（包括 Http Session 中）
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        //禁止Subject的getSession方法
        securityManager.setSubjectFactory(subjectFactory());
        //指定使用自定义Cache缓存
        securityManager.setRealm(realm());
        securityManager.setCacheManager(customCacheManager());
        return securityManager;
    }

    @Bean
    public CustomCacheManager customCacheManager() {
        return new CustomCacheManager();
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setLoginUrl("/login");
        shiroFilter.setUnauthorizedUrl("/unauthorized");
        /**
         * 添加jwt过滤器，并在下面注册
         * 也就是将jwtFilter注册到shiro的Filter中
         * 指定除了login和logout之外的请求都先经过jwtFilter
         */
        Map<String, Filter> filterMap = new HashMap<>(16);
        //这个地方其实另外两个filter可以不设置，默认就是
//        filterMap.put("anon", new AnonymousFilter());
        filterMap.put("jwt", jwtFilterBean());
//        filterMap.put("logout", new LogoutFilter());
        shiroFilter.setFilters(filterMap);
        shiroFilter.setSecurityManager(securityManager());
        // 拦截器
        Map<String, String> filterRuleMap = new LinkedHashMap<>();
        //Swagger接口文档
        filterRuleMap.put("/csrf/**", "anon");
        filterRuleMap.put("/v2/api-docs", "anon");
        filterRuleMap.put("/webjars/**", "anon");
        filterRuleMap.put("/swagger-resources/**", "anon");
        filterRuleMap.put("/swagger-ui.html", "anon");
        filterRuleMap.put("/doc.html", "anon");

        // 公开接口
        filterRuleMap.put("/css/**", "anon");
        filterRuleMap.put("/js/**", "anon");
        filterRuleMap.put("/img/**", "anon");
        filterRuleMap.put("/druid/**", "anon");
        filterRuleMap.put("/uLogin", "anon");
        filterRuleMap.put("/getVCode", "anon");
        filterRuleMap.put("/sso", "anon");
        filterRuleMap.put("/login", "anon");
        filterRuleMap.put("/logout", "logout");
        filterRuleMap.put("/userRole", "jwt[[QUERY]]");
        filterRuleMap.put("/**", "jwt[USER,CUSTOM,ADMIN]");
        shiroFilter.setFilterChainDefinitionMap(filterRuleMap);

        return shiroFilter;
    }

    @Bean("jwtFilter")
    public JwtFilter jwtFilterBean() {
        return new JwtFilter();
    }

    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /*@Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }*/
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

}
