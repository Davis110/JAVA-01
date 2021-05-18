package com.algorithm.data.source;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.*;

/**
 * @author wei.huang
 * @version Id: DynamicDataSource.java, v 0.1 2021年04月20日  13:50 wei.huang Exp $
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public static List<String> slaves;

    public static String masterName;

    private static final HashSet<String> All_DATA_KEY = new HashSet<>();

    private static final ThreadLocal<String> dataContextHolder = new ThreadLocal<>();

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources, String masterName, String slaves) {
        this.masterName = masterName;
        this.slaves = Arrays.asList(slaves.split(","));
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
        initAllDataKey(masterName);
    }

    private void initAllDataKey(String masterName) {
        All_DATA_KEY.add(masterName);
        slaves.stream().forEach(slave -> All_DATA_KEY.add(slave));
    }

    /**
     * @return
     */
    @Override protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    /**
     * 获取数据源
     *
     * @return
     */
    public static String getDataSource() {
        return dataContextHolder.get();
    }

    /**
     * 获取但钱线程数据源
     *
     * @param key
     */
    public static void setDataSource(String key) throws Exception {
        if (!All_DATA_KEY.contains(key)) {
            throw new Exception("当前数据源不存在：" + key);
        }
        dataContextHolder.set(key);
    }

    /**
     * 清除当前线程的数据源
     */
    public static void clearDataSource() {
        dataContextHolder.remove();
    }

    /**
     * 节点路由
     *
     * @return
     */
    public static String slavesLoadbance() {
        //通过随机获取数组中数据库的名称来随机分配要使用的数据库
        int num = new Random().nextInt(slaves.size());
        System.out.println("随机数：" + num);
        return slaves.get(num);

    }
}
