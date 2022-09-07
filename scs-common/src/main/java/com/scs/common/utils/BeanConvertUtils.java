package com.scs.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public class BeanConvertUtils {
    private BeanConvertUtils() {
    }

    private static final Logger logger = LoggerFactory.getLogger(BeanConvertUtils.class);

    /**
     * @param source      Object
     * @param targetClass Class
     * @param <T>         T
     * @return T
     */
    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        T doInstance = null;
        try {
            doInstance = targetClass.newInstance();
            BeanUtils.copyProperties(source, doInstance);
        } catch (Exception e) {
            logger.error("copyProperties error:{}", e);
        }
        return doInstance;
    }

}
