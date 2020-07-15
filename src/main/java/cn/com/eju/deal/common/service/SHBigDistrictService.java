package cn.com.eju.deal.common.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.common.dao.SHBigDistrictMapper;
import cn.com.eju.deal.common.model.SHBigDistrict;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.common.SHBigDistrictDto;

/**   
* 上海大区的service
* @author 张文辉
* @date 2016年7月11日 上午10:28:01
*/
@Service("sHBigDistrictService")
public class SHBigDistrictService
{
    @Resource
    private SHBigDistrictMapper sHDistrictMapper;
    
    /**
     * 根据默认岗位Id 查询上海各事业部区域信息
     * @param selectedPostId
     * @return SHBigDistrictDto
     */
    public ResultData<List<SHBigDistrictDto>> getBigDistrictBySelectedPostId(Integer selectedPostId)
    {
        ResultData<List<SHBigDistrictDto>> resultData = new ResultData<List<SHBigDistrictDto>>();
        
        List<SHBigDistrict> bigDistrictList = sHDistrictMapper.getBigDistrictBySelectedPostId(selectedPostId);
        if (null != bigDistrictList && !bigDistrictList.isEmpty())
        {
            List<SHBigDistrictDto> bigDistrictDtoList = new ArrayList<SHBigDistrictDto>();
            for (SHBigDistrict sHBigDistrict : bigDistrictList)
            {
                SHBigDistrictDto bigDistrictDto = new SHBigDistrictDto();
                BeanUtils.copyProperties(sHBigDistrict, bigDistrictDto);
                bigDistrictDtoList.add(bigDistrictDto);
            }
            resultData.setReturnData(bigDistrictDtoList);
            resultData.setTotalCount(bigDistrictDtoList.size()+"");
        }
        return resultData;
    }
}
