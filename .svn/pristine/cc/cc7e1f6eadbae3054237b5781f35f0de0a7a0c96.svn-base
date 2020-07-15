package cn.com.eju.deal.company.dao;

import java.util.List;

import cn.com.eju.deal.company.model.GpCompanyStore;
import cn.com.eju.deal.core.dao.IDao;

public interface GpCompanyStoreMapper extends IDao<GpCompanyStore> {

    /**
     * 获取公司门店列表
     *
     * @param companyId 公司编号
     * @return 门店编号
     */
    List<GpCompanyStore> getGpCompanyStoreList(int companyId) throws Exception;

    /**
     * 删除公司门店
     *
     * @param companyId 公司编号
     * @return 影响行数
     */
    int deleteCompanyStore(int companyId) throws Exception;
    
    /**
     * 删除关系
     *
     * @param companyId 公司编号
     * @return 影响行数
     */
    int deleteRelate(GpCompanyStore companyStore) throws Exception;
}