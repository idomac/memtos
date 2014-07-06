package com.quanix.memtos.server.ehcache;

import com.quanix.memtos.server.testcase.TransactionalTestCase;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

/**
 * created by lihaoquan
 */
@ContextConfiguration(locations = {"/applicationContext.xml", "/spring-functions/applicationContext-ehcache.xml" })
public class EhcacheTest extends TransactionalTestCase {

    private static final String CACHE_NAME = "dataCache";

    @Autowired
    @Qualifier(value = "dataEhcacheManager")
    private CacheManager cacheManager;

    private Cache cache;

    @Test
    public void demo() {
        cache = cacheManager.getCache(CACHE_NAME);

        String key = "foo";
        String value = "boo";

        put(key, value);
        Object result = get(key);

        System.out.println("result="+result.toString());

    }


    public Object get(String key) {
        Element element = cache.get(key);
        return element.getObjectValue();
    }

    public void put(String key, Object value) {
        Element element = new Element(key, value);
        cache.put(element);
    }
}
