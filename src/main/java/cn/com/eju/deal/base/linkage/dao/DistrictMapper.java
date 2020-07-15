package cn.com.eju.deal.base.linkage.dao;

import cn.com.eju.deal.base.linkage.dto.CityBrandDto;
import cn.com.eju.deal.base.linkage.model.District;
import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.model.sweepStreets.CompanyNewDto;

import java.util.List;

public interface DistrictMapper extends IDao<District>
{
    
    /** 
    * 根据城市CityNo获取其行政区List
    * @return
    */
    List<District> getDistrictByCityNo(String cityNo);
    List<CityBrandDto> getBrandListByCityNo(String cityNo);
    int getIsShowQRCode(String cityNo);
    
    District getDistrictByDistrictNo(String districtNo);
    int checkCityNoDistrictNo(CompanyNewDto dto);
}