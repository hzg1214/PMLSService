package cn.com.eju.deal.company.dao;

import cn.com.eju.deal.company.model.CompanyStore;
import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.store.StoreDto;

import java.util.List;
import java.util.Map;

public interface CompanyStoreMapper extends IDao<CompanyStore> {

    /**
     * 获取公司门店列表
     *
     * @param companyId 公司编号
     * @return 门店编号
     */
    List<CompanyStore> getCompanyStoreList(int companyId) throws Exception;

    /**
     * 获取公司门店列表
     *
     * @param companyId 公司编号
     * @return 门店编号
     */
    List<StoreDto> getCompanyStoreListNew(Map<?, ?> param) throws Exception;

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
    int deleteRelate(CompanyStore companyStore) throws Exception;
}