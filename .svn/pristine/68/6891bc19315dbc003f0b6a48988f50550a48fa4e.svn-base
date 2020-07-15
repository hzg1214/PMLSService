package cn.com.eju.deal.common.base;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.core.helper.SpringConfigHelper;

/**
 * Created by Sky on 2016/3/29.
 * 获取Mapper对象
 */
public class MapperFactory {
    /**
     * 获取dao层Mapper
     *
     * @param mapperName mapper名称
     * @return 返回mapper对象
     */
    public static <T> T getMapper(String mapperName, Class<T> toClass) {
        IDao<?> daoMapper = SpringConfigHelper.getDaoBeanByDaoClassName(mapperName);

        T mapper = (T) daoMapper;

        return mapper;
    }


    /**
     * 获取ben文件
     *
     * @param serviceName ben名称
     * @param toClass     目标类型
     * @param <T>         类型的Type
     * @return 改类型的值
     */
    public static <T> T getService(String serviceName, Class<T> toClass) {
        return SpringConfigHelper.getBean(serviceName, toClass);
    }
}
