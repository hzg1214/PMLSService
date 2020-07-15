package cn.com.eju.deal.houseLinkage.totalPackage.dao;


import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.scene.commission.ImportDto;
import cn.com.eju.deal.houseLinkage.totalPackage.model.TotalPackageData;

import java.util.List;
import java.util.Map;

public interface TotalPackageDataMapper extends IDao{
    List<ImportDto> getLnkImportByMap(Map<String,Object> parm);

    Integer createLnkImport(ImportDto importDto);

    Integer updateLnkImport(ImportDto importDto);

    List<TotalPackageData> getRevenuesForHandle();

    Integer handleYJSR(Map<String, Object> param);

    Integer handleYSSR(Map<String, Object> param);

    Integer handleYJFY(Map<String, Object> param);

    Integer handleSJFY(Map<String, Object> param);

    Integer handleYJDY(Map<String, Object> param);

    Integer handleSJDY(Map<String, Object> param);

    Integer handleYJSS(Map<String, Object> param);

    List<Map<String,Object>> getYJSSForHandle();

    Integer updateYJSS(Integer id);

}