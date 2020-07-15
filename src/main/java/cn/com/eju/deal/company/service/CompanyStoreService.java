package cn.com.eju.deal.company.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.company.dao.CompanyStoreMapper;
import cn.com.eju.deal.company.dao.GpCompanyStoreMapper;
import cn.com.eju.deal.company.model.CompanyStore;
import cn.com.eju.deal.company.model.GpCompanyStore;
import cn.com.eju.deal.dto.company.CompanyStoreDto;
import cn.com.eju.deal.dto.company.GpCompanyStoreDto;

/**   
* 公司门店关系
* @author 张文辉
* @date 2016年7月4日 下午5:23:42
*/
@Service("companyStoreService")
public class CompanyStoreService extends BaseService<CompanyStore>
{
    
    @Resource
    private CompanyStoreMapper companyStoreMapper;

    @Resource
    private GpCompanyStoreMapper gpcompanyStoreMapper;
    /** 
    * 查询
    * @param id
    * @return
    */
    public int createCompanyStore(CompanyStore companyStore)
        throws Exception
    {
        List<CompanyStore> companyStoreList = new ArrayList<CompanyStore>();
        companyStoreList.add(companyStore);
        int num = companyStoreMapper.batchCreate(companyStoreList);
        return num;
    }
    
    /** 
     * 删除
     * @param storeId companyId
     * @return
     */
     public int deleteCompanyStore(CompanyStoreDto companyStoreDto) throws Exception {
         CompanyStore companyStore = new CompanyStore();
         BeanUtils.copyProperties(companyStoreDto, companyStore);
         return companyStoreMapper.deleteRelate(companyStore);
     }
     
    
     /**
      * 获取公司门店列表
      *
      * @param companyId 公司编号
      * @return 门店编号
      */
     public List<CompanyStore> getCompanyStoreList(int companyId) throws Exception
     {
    	 return companyStoreMapper.getCompanyStoreList(companyId);
     }
     
     
     

     /**
      * 获取公司门店列表
      *
      * @param companyId 公司编号
      * @return 门店编号
      */
     public List<GpCompanyStore> getGpCompanyStoreList(int companyId) throws Exception
     {
    	 return gpcompanyStoreMapper.getGpCompanyStoreList(companyId);
     }
     
     
     /** 
      * 查询
      * @param id
      * @return
      */
      public int createGpCompanyStore(GpCompanyStore companyStore)
          throws Exception
      {
          List<GpCompanyStore> companyStoreList = new ArrayList<GpCompanyStore>();
          companyStoreList.add(companyStore);
          int num = gpcompanyStoreMapper.batchCreate(companyStoreList);
          return num;
      }
      
     /** 
      * 删除
      * @param storeId companyId
      * @return
      */
      public int deletegpCompanyStore(GpCompanyStoreDto companyStoreDto) throws Exception {
          GpCompanyStore companyStore = new GpCompanyStore();
          BeanUtils.copyProperties(companyStoreDto, companyStore);
          return gpcompanyStoreMapper.deleteRelate(companyStore);
      }
    
}
