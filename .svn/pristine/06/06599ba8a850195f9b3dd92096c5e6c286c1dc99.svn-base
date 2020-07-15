package cn.com.eju.deal.common.dao;

import java.util.List;

import cn.com.eju.deal.common.model.Area;
import cn.com.eju.deal.common.model.City;
import cn.com.eju.deal.common.model.District;
import cn.com.eju.deal.common.model.Province;
import org.apache.ibatis.annotations.Param;

/** 
* @ClassName: CityCascadeMapper 
* @Description: 城市级联dao
* @author 陆海丹 
* @date 2016年3月28日 上午9:42:13 
*  
*/
public interface CityCascadeMapper
{
    /** 
    * @Title: queryProvinceList 
    * @Description: 查询省份列表
    * @return     
    */
    List<Province> queryProvinceList() throws Exception;
    /** 
    * @Title: queryCityList 
    * @Description: 根据省份编号查询城市列表
    * @param provinceNo 省份编号
    * @return     
    */
    List<City> queryCityList(String provinceNo) throws Exception;
    /**
    * @Title: queryCityList
    * @Description: 根据用户id查询城市列表
    * @param userId 用户id
    * @return
    */
    List<City> queryCityListByUserId(String userId) throws Exception;
    /** 
    * @Title: queryDistrictList 
    * @Description: 根据城市编号查询区域列表
    * @param cityNo 城市编号
    * @return     
    */
    List<District> queryDistrictList(String cityNo) throws Exception;
    /** 
    * @Title: queryAreaList 
    * @Description: 根据区域编号查询板块列表
    * @param districtNo 区域编号
    * @return     
    */
    List<Area> queryAreaList(String districtNo) throws Exception;
    /** 
     * @Title: queryCity
     * @Description: 根据城市编号查询城市
     * @param cityNo 城市编号
     * @return     
     */
     List<City> queryCity(String cityNo) throws Exception;
     /** 
     * @Title: queryDistrict
     * @Description: 根据区域编号查询区域
     * @param districtNo 区域编号
     * @return     
     */
     List<District> queryDistrict(String districtNo) throws Exception;
     /** 
     * @Title: queryArea
     * @Description: 根据板块编号查询板块
     * @param areaNo 板块编号
     * @return     
     */
     List<Area> queryArea(String areaNo) throws Exception;
     
     List<City> queryCityListByIsService()throws Exception;
     
     List<City> queryCityListByIsLnkService()throws Exception;
     List<City> queryCitySettingsList()throws Exception;
     List<City> queryCityNameByCityNo(String cityNo)throws Exception;

    List<City> queryCityListByPlace() throws Exception;

    List<City> queryCityListByAffiliation(@Param("userId") Integer userId) throws Exception;
}
