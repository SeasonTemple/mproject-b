package com.seasontemple.mproject.utils.email;

import cn.hutool.extra.mail.MailException;
import cn.hutool.extra.mail.MailUtil;
import com.seasontemple.mproject.utils.exception.CustomException;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 邮件发送工具类
 */
public class EmailSender {

    public static void send(String to, String subject, String content) {
        try {
            MailUtil.send(to, subject, content, false);
        } catch (MailException e) {
            throw new CustomException("邮件发送失败：" + e.getMessage());
        }
    }
}
