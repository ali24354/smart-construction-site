package com.scs.common.exception;

import com.scs.common.utils.SpringUtils;
import com.scs.common.utils.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;


public interface ExceptionResolvable {

    Integer getCode();

    String getMessage();

    default String getMessage(String message) {
        MessageSource messageSource = SpringUtils.getBean(ReloadableResourceBundleMessageSource.class);
        Locale locale = LocaleContextHolder.getLocale();
        String localeMessage = messageSource.getMessage(message, null, "", locale);
        if (StringUtils.isEmpty(localeMessage)) {
            localeMessage = message;
        }
        return localeMessage;
    }
}
