package com.seasontemple.mproject.web.shiro;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 使用token，关闭session
 * @create: 2020/03/29 21:21:45
 */
public class JwtDefaultSubjectFactory extends DefaultWebSubjectFactory {

    private static final Log log = LogFactory.get();
    @Override
    public Subject createSubject(SubjectContext context) {
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}