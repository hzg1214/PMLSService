package cn.com.eju.deal.houseLinkage.estate.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import cn.com.eju.deal.houseLinkage.estate.dao.LnkEstateIncomeplanMapper;
import cn.com.eju.deal.houseLinkage.estate.model.LnkEstateIncomeplan;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateChangeDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateReleaseCityDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateReleaseDto;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateChangeMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.EstatetReleaseMapper;
import cn.com.eju.deal.houseLinkage.estate.model.EstateChange;

@Service("estatetReleaseService")
public class EstateReleaseService extends BaseService {
	 @Resource
	 private EstatetReleaseMapper estatetReleaseMapper;
	 @Resource
	 private EstateChangeMapper estateChangeMapper;


	 public ResultData<List<EstateReleaseCityDto>> queryCityListByEstateId(String estateId) throws Exception
     {
        ResultData<List<EstateReleaseCityDto>> resultData = new ResultData<>();
        List<EstateReleaseCityDto> releaseCityList = this.estatetReleaseMapper.queryCityListByEstateId(estateId);
        if(releaseCityList !=null && releaseCityList.size()>0){
        	resultData.setTotalCount(String.valueOf(releaseCityList.size()));
            resultData.setReturnData(releaseCityList);
        }else {
            resultData.setFail();
        }
        return resultData;
    }
	public int update(EstateReleaseDto estateReleaseDto) throws Exception{
		int count = 0;
		if("1".equals(estateReleaseDto.getFlag())){
			this.estatetReleaseMapper.deleteEstatetReleaseByEstateId(estateReleaseDto.getEstateId());
		}
 		List<EstateReleaseCityDto> releaseCitylist = estateReleaseDto.getEstateReleaseDtoList();
 		if(releaseCitylist !=null && releaseCitylist.size() >0){
 			count = this.estatetReleaseMapper.batchInsertEstatetRelease(releaseCitylist);
 		}
	    if (estateReleaseDto.getEstateChangeDetails().size() > 0){
	    	EstateChangeDto estateChangeDto = estateReleaseDto.getEstateChangeDetails().get(0);
	    	EstateChange estateChange = new EstateChange();
	    	BeanUtils.copyProperties(estateChangeDto, estateChange);
	    	this.estateChangeMapper.create(estateChange);
        } 
	    return count;
	}

}

