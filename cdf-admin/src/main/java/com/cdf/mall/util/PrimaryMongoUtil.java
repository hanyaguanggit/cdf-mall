package com.cdf.mall.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @Description mongodb 主数据库操作类
 * @Author hanyaguang
 * @Date 2022/2/18 13:49
 * @Version 1.0
 */
@Component
public class PrimaryMongoUtil {
    private static Logger logger = LoggerFactory.getLogger(PrimaryMongoUtil.class);

    public static PrimaryMongoUtil mongodbUtils;

    @PostConstruct
    public void init() {
        mongodbUtils = this;
        mongodbUtils.mongoTemplate = this.mongoTemplate;
    }

    @Resource(name = "primaryMongoTemplate")
    private MongoTemplate mongoTemplate;

    /**
     * 保存数据对象，集合为数据对象中@Document 注解所配置的collection
     *
     * @param obj
     *            数据对象
     */
    public static void save(Object obj) {
        mongodbUtils.mongoTemplate.save(obj);
    }

    /**
     * 指定集合保存数据对象
     *
     * @param obj
     *            数据对象
     * @param collectionName
     *            集合名
     */
    public static void save(Object obj, String collectionName) {

        mongodbUtils.mongoTemplate.save(obj, collectionName);
    }

    /**
     * 根据数据对象中的id删除数据，集合为数据对象中@Document 注解所配置的collection
     *
     * @param obj
     *            数据对象
     */
    public static void remove(Object obj) {

        mongodbUtils.mongoTemplate.remove(obj);
    }

    /**
     * 指定集合 根据数据对象中的id删除数据
     *
     * @param obj
     *            数据对象
     * @param collectionName
     *            集合名
     */
    public static void remove(Object obj, String collectionName) {

        mongodbUtils.mongoTemplate.remove(obj, collectionName);
    }

    /**
     * 根据key，value到指定集合删除数据
     *
     * @param key
     *            键
     * @param value
     *            值
     * @param collectionName
     *            集合名
     */
    public static void removeById(String key, Object value, String collectionName) {

        Criteria criteria = Criteria.where(key).is(value);
        criteria.and(key).is(value);
        Query query = Query.query(criteria);
        mongodbUtils.mongoTemplate.remove(query, collectionName);
    }

    /**
     * 指定集合 修改数据，且仅修改找到的第一条数据
     *
     * @param accordingKey
     *            修改条件 key
     * @param accordingValue
     *            修改条件 value
     * @param data
     *            修改内容 对象
     * @param collectionName
     *            集合名
     */
    public static void updateFirst(String accordingKey, Object accordingValue, Object data,
                                   String collectionName) {

        Criteria criteria = Criteria.where(accordingKey).is(accordingValue);
        Query query = Query.query(criteria);
        Update update = new Update();
        Class cls = data.getClass();
        Field[] fields = cls.getDeclaredFields();
        try {
            for (Field f : fields){
                f.setAccessible(true);
                if (f.get(data) != null) {
                    update.set(f.getName(), f.get(data));
                }
            }
        } catch (IllegalAccessException e) {
            logger.error("遍历修改实体对象报错", e);
        }
        mongodbUtils.mongoTemplate.updateFirst(query, update, collectionName);
    }

    /**
     * 指定集合 修改数据，且修改所找到的所有数据
     *
     * @param accordingKey
     *            修改条件 key
     * @param accordingValue
     *            修改条件 value
     * @param data
     *            修改内容 对象
     * @param collectionName
     *            集合名
     */
    public static void updateMulti(String accordingKey, Object accordingValue, Object data,
                                   String collectionName) {

        Criteria criteria = Criteria.where(accordingKey).is(accordingValue);
        Query query = Query.query(criteria);
        Update update = new Update();
        Class cls = data.getClass();
        Field[] fields = cls.getDeclaredFields();
        try {
            for (Field f : fields){
                f.setAccessible(true);
                if (f.get(data) != null) {
                    update.set(f.getName(), f.get(data));
                }
            }
        } catch (IllegalAccessException e) {
            logger.error("指定集合 修改数据，且修改所找到的所有数据，遍历报错", e);
        }
        mongodbUtils.mongoTemplate.updateMulti(query, update, collectionName);
    }

    /**
     * 根据条件查询出所有结果集 集合为数据对象中@Document 注解所配置的collection
     *
     * @param obj
     *            数据对象
     * @param data
     *            查询条件 对象
     * @return
     */
    public static List<? extends Object> find(Object obj, Object data) {
        Criteria criteria = null;
        Class cls = data.getClass();
        Field[] fields = cls.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++){
                Field f = fields[i];
                f.setAccessible(true);
                if (f.get(data) != null) {
                    if (i == 0) {
                        criteria = Criteria.where(f.getName()).is(f.get(data));
                    } else {
                        criteria.and(f.getName()).is(f.get(data));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            logger.error("根据条件查询出所有结果集，遍历报错", e);
        }
        Query query = Query.query(criteria);
        List<? extends Object> resultList = mongodbUtils.mongoTemplate.find(query, obj.getClass());
        return resultList;
    }

    /**
     * 指定集合 根据条件查询出所有结果集
     *
     * @param obj
     *            数据对象
     * @param findKeys
     *            查询条件 key
     * @param findValues
     *            查询条件 value
     * @param collectionName
     *            集合名
     * @return
     */
    public static List<? extends Object> find(Object obj, String[] findKeys, Object[] findValues, String collectionName) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        List<? extends Object> resultList = mongodbUtils.mongoTemplate.find(query, obj.getClass(), collectionName);
        return resultList;
    }

    /**
     * 指定集合 根据条件查询出所有结果集 并排倒序
     *
     * @param obj
     *            数据对象
     * @param findKeys
     *            查询条件 key
     * @param findValues
     *            查询条件 value
     * @param collectionName
     *            集合名
     * @param sort
     *            排序字段
     * @return
     */
    /*public static List<? extends Object> find(Object obj, String[] findKeys, Object[] findValues, String collectionName ,String sort) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        query.with(new Sort(Sort.Direction.DESC, sort));
        List<? extends Object> resultList = mongodbUtils.mongoTemplate.find(query, obj.getClass(), collectionName);
        return resultList;
    }*/

    /**
     * 根据条件查询出符合的第一条数据 集合为数据对象中 @Document 注解所配置的collection
     *
     * @param obj
     *            数据对象
     * @param findKeys
     *            查询条件 key
     * @param findValues
     *            查询条件 value
     * @return
     */
    public static Object findOne(Object obj, String[] findKeys, Object[] findValues) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        Object resultObj = mongodbUtils.mongoTemplate.findOne(query, obj.getClass());
        return resultObj;
    }

    /**
     * 指定集合 根据条件查询出符合的第一条数据
     *
     * @param obj
     *            数据对象
     * @param findKeys
     *            查询条件 key
     * @param findValues
     *            查询条件 value
     * @param collectionName
     *            集合名
     * @return
     */
    public static Object findOne(Object obj, String[] findKeys, Object[] findValues, String collectionName) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        Object resultObj = mongodbUtils.mongoTemplate.findOne(query, obj.getClass(), collectionName);
        return resultObj;
    }

    /**
     * 查询出所有结果集 集合为数据对象中 @Document 注解所配置的collection
     *
     * @param obj
     *            数据对象
     * @return
     */
    public static List<? extends Object> findAll(Object obj) {

        List<? extends Object> resultList = mongodbUtils.mongoTemplate.findAll(obj.getClass());
        return resultList;
    }

    /**
     * 查询出所有结果集 集合为数据对象中 @Document 注解所配置的collection
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>  List<T> findAll(Class<T> clazz){
        List<T> resultList = mongodbUtils.mongoTemplate.findAll(clazz);
        return resultList;
    }

    /**
     * 指定集合 查询出所有结果集
     *
     * @param obj
     *            数据对象
     * @param collectionName
     *            集合名
     * @return
     */
    public static List<? extends Object> findAll(Object obj, String collectionName) {

        List<? extends Object> resultList = mongodbUtils.mongoTemplate.findAll(obj.getClass(), collectionName);
        return resultList;
    }

    /**
     * 指定集合 查询出所有结果集
     * @param clazz
     * @param collectionName
     * @param <T>
     * @return
     */
    public static <T> List<T> findAll(Class<T> clazz, String collectionName) {
        List<T> resultList = mongodbUtils.mongoTemplate.findAll(clazz, collectionName);
        return resultList;
    }
}
