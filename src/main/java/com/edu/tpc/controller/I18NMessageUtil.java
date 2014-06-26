/*
 * Date: 1/7/12
 * Author: Jaikishan
 */

package com.edu.tpc.controller;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component("i18nService")
public class I18NMessageUtil
{
    private MessageSource amessageSource;

    public MessageSource getAmessageSource() {
        return amessageSource;
    }

    @Autowired
    public void setAmessageSource(MessageSource amessageSource) { 
        this.amessageSource = amessageSource;
    }
    
    public String getMessage(String key)
    {
        Locale locale = LocaleContextHolder.getLocale();
        return amessageSource.getMessage(key, new Object[0], locale);
    }
    public String getMessage(String key,Object[]para)
    {
        Locale locale = LocaleContextHolder.getLocale();
        return amessageSource.getMessage(key, para, locale);
    }
}
