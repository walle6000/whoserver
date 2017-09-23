package io.swagger.service.common;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import io.swagger.common.CacheType;
import io.swagger.utils.JSONUtil;



@Service
public class RedisService {
	@Autowired  
    private RedisTemplate<String, ?> redisTemplate;
	
	
    public boolean set(final String key, final String value) {  
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            @Override  
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                connection.set(serializer.serialize(key), serializer.serialize(value));  
                return true;  
            }  
        });  
        return result;  
    }  
  
    public String get(final String key){  
        String result = redisTemplate.execute(new RedisCallback<String>() {  
            @Override  
            public String doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                byte[] value =  connection.get(serializer.serialize(key));  
                return serializer.deserialize(value);  
            }  
        });  
        return result;  
    }  
    
    public boolean plus1(final String key){  
    	boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            @Override  
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                byte[] value =  connection.get(serializer.serialize(key));
                Integer iValue = Integer.valueOf(serializer.deserialize(value));
                iValue++;                
                connection.set(serializer.serialize(key), serializer.serialize(iValue.toString()));  
                return true;
            }  
        });  
    	return result;
    } 
    
    public Long incrBy(final String key,final Long step){  
    	Long result = redisTemplate.execute(new RedisCallback<Long>() {  
            @Override  
            public Long doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                Long value =  connection.incrBy(serializer.serialize(key), step);
                return value;
            }  
        });  
    	return result;
    } 
  
    public boolean set(final String key, final String value, final long expire) {
    	boolean result = this.set(key, value);
    	if(result){
    		this.expire(key, expire);
    	}
    	return result;
    }
    
    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);  
    }  
  
    public <T> boolean setList(String key, List<T> list) {
        String value = JSONUtil.toJson(list);  
        return set(key,value);  
    }  
    
    public <T> boolean setList(String key, List<T> list,long expire) {
        String value = JSONUtil.toJson(list);  
        boolean result = set(key,value);
    	if(result){
    		this.expire(key, expire);
    	}
    	 return result;
    }  
  
    public <T> List<T> getList(String key,Class<T> clz) {
        String json = get(key);  
        if(json!=null){ 
            List<T> list = JSONUtil.toList(json, clz);  
            return list;  
        }  
        return null;  
    }
    
    public <T> boolean setObjct(String key, T object){
    	String value = JSONUtil.toJson(object);
    	 return set(key,value);
    }
    
    public <T> boolean setObjct(String key, T object,long expire){
    	String value = JSONUtil.toJson(object);
    	boolean result = set(key,value);
    	if(result){
    		this.expire(key, expire);
    	}
    	 return result;
    }
    
    public <T> T getObject(String key, Class<T> clz){
    	String json = get(key);
    	if(json!=null){
    		 T object = JSONUtil.toBean(json, clz);
    		 return object;
    	}
    	return null;
    }
    
	public void remove(String key) {
		redisTemplate.delete(key);
	}
  
    public long lpush(final String key, Object obj) {  
        final String value = JSONUtil.toJson(obj);  
        long result = redisTemplate.execute(new RedisCallback<Long>() {  
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                long count = connection.lPush(serializer.serialize(key), serializer.serialize(value));  
                return count;  
            }  
        });  
        return result;  
    }  
  
    public long rpush(final String key, Object obj) {  
        final String value = JSONUtil.toJson(obj);  
        long result = redisTemplate.execute(new RedisCallback<Long>() {  
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                long count = connection.rPush(serializer.serialize(key), serializer.serialize(value));  
                return count;  
            }  
        });  
        return result;  
    }  
    
    public long sadd(final String key, Object obj) {  
        final String value = JSONUtil.toJson(obj);  
        long result = redisTemplate.execute(new RedisCallback<Long>() {  
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                long count = connection.sAdd(serializer.serialize(key), serializer.serialize(value));  
                return count;  
            }  
        });  
        return result;  
    }
    
    public Boolean setContain(final String key, Object obj) {  
        final String value = JSONUtil.toJson(obj);  
        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                Boolean isContain = connection.sIsMember(serializer.serialize(key), serializer.serialize(value));  
                return isContain;  
            }  
        });  
        return result;  
    }
    
    public <T> List<T> getObjectFromSet(String key, Class<T> clz) {
		List<T> result = redisTemplate.execute(new RedisCallback<List<T>>() {  
            @Override
            public List<T> doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                Set<byte[]> res =  connection.sMembers(serializer.serialize(key));
                List<T> result = res.parallelStream().map(s -> JSONUtil.toBean(serializer.deserialize(s), clz)).collect(Collectors.toList());
                return result;  
            }  
        });  
        return result;
	}
    
    public long sRem(final String key, Object obj) {  
        final String value = JSONUtil.toJson(obj);  
        long result = redisTemplate.execute(new RedisCallback<Long>() {  
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                long count = connection.sRem(serializer.serialize(key), serializer.serialize(value));  
                return count;  
            }  
        });  
        return result;  
    }
    
    public String lpop(final String key) {  
        String result = redisTemplate.execute(new RedisCallback<String>() {  
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                byte[] res =  connection.lPop(serializer.serialize(key));  
                return serializer.deserialize(res);  
            }  
        });  
        return result;  
    }
    
    
    public Long setCount(String key) {
		Long result = redisTemplate.execute(new RedisCallback<Long>() {  
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                Long res =  connection.sCard(serializer.serialize(key));  
                return res;  
            }  
        });  
        return result;
	}
    
	public Long listCount(String key) {
		Long result = redisTemplate.execute(new RedisCallback<Long>() {  
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                Long res =  connection.lLen(serializer.serialize(key));  
                return res;  
            }  
        });  
        return result;
	}
	
	public <T> List<T> getObjectFromList(String key,Long startIndex,Long endIndex, Class<T> clz) {
		List<T> result = redisTemplate.execute(new RedisCallback<List<T>>() {  
            @Override
            public List<T> doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                List<byte[]> res =  connection.lRange(serializer.serialize(key), startIndex, endIndex);
                List<T> result = res.parallelStream().map(s -> JSONUtil.toBean(serializer.deserialize(s), clz)).collect(Collectors.toList());
                return result;  
            }  
        });  
        return result;
	}
    
    /**
	 * 获取MD5后的缓存key
	 * @param cacheType
	 * @param cacheKeyElements
	 * @return
	 */
	public String getMD5CacheKey(CacheType cacheType, Object... cacheKeyElements) {
		StringBuilder keySb = new StringBuilder();
		keySb.append(cacheType.name());
		for (Object keyElement : cacheKeyElements) {
			keySb.append(".").append(keyElement);
		}
		return DigestUtils.md5Hex(keySb.toString());
	}
    
}
