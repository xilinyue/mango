package com.jun.my_mango.admin.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description: 方便redis取值的内容
 * @author: Liusu
 * @date: 2022年11月12日10:09
 */
@Service
public class RedisService {
    @Autowired
    public RedisTemplate redisTemplate;

    public <T> T getStringValue(Object key, Class<T> beanClass) {
        if (key == null) return null;
        Object o = redisTemplate.opsForValue().get(key);
        return o == null ? null : JSON.parseObject(o.toString(), beanClass);
    }
}