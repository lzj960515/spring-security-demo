/**
 * <P>Title:RedisUtil</P>
 */
package com.lzj.my.spring.security.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
* @ClassName: RedisUtil
* @Description: TODO
* @author xlh
* @date 2018年11月20日 上午8:33:14
*
*/
@Component
public class RedisUtil {
    private static final Map<String, Map<String,String>> redisMap = new ConcurrentHashMap<>(16);
    /**
     * 设置缓存
     * @param key    缓存key
     * @param value  缓存value
     */
    public void hset(String key, String fieId,String value) {
        Map<String, String> map = redisMap.get(key);
        if(map==null){
            map = new HashMap<>(2);
            map.put(fieId,value);
            redisMap.put(key,map);
        }else{
            map.put(fieId,value);
        }
    }

    /**
     * 获取缓存
     * @param key    缓存key
     * @param fieId  二级key
     */
    public String hget(String key, String fieId) {
        return redisMap.get(key).get(fieId);
    }
}
