package cn.com.eju.pmls.channelBusiness.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.channelBusiness.dao.BusinessManagerMapper;
import cn.com.eju.pmls.channelBusiness.dao.ChannelBrandMapper;
import cn.com.eju.pmls.channelBusiness.model.BusinessManagerDto;
import cn.com.eju.pmls.channelBusiness.model.ChannelBrandDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("channelBrandService")
public class ChannelBrandService  extends BaseService {
    @Resource
    private ChannelBrandMapper channelBrandMapper;
    @Resource
    private BusinessManagerMapper businessManagerMapper;
    //获取渠道品牌列表
    public ResultData getChannelBrandList(Map<String, Object> queryParam) {
        ResultData<List<ChannelBrandDto>> resultData = new ResultData<List<ChannelBrandDto>>();
        List<ChannelBrandDto> list = channelBrandMapper.getChannelBrandList(queryParam);

        List<ChannelBrandDto> newList=new ArrayList<ChannelBrandDto>();
        /*for(ChannelBrandDto brand:list){
            brand.setName(brand.getBrandName());
            if(brand.getParentId()==0){
                newList.add(brand);
            }
        }*/
        for(ChannelBrandDto brand:list){
            brand.setName(brand.getBrandName());
            recursionTree(brand,newList);
        }

        resultData.setReturnData(newList);
        return resultData;
    }
    //获取渠道品牌信息
    public ResultData getBrandInfo(Map<String, Object> queryParam) {
        ResultData<ChannelBrandDto> resultData = new ResultData<ChannelBrandDto>();
        ChannelBrandDto channelBrandDto = channelBrandMapper.getBrandInfo(queryParam);
        resultData.setReturnData(channelBrandDto);
        return resultData;
    }
    //新增渠道品牌
    @Transactional(rollbackFor=Exception.class)
    public ResultData addBrand(ChannelBrandDto dto) throws Exception{
        ResultData resultData = new ResultData();
        //校验渠道品牌名称是否存在
        int resultNum=channelBrandMapper.checkBrandInfo(dto);
        if(resultNum>0){
            resultData.setFail("该渠道名称已存在，请修改后在提交");
            return resultData;
        }

        int count = channelBrandMapper.addBrand(dto);
        int updateResult=0;
        if(dto.getId()!=null && dto.getId()!=0){
            if(dto.getParentId()==0){
                dto.setOrgId("/"+dto.getId()+"/");
            }else{
                Map req=new HashMap();
                req.put("id",dto.getParentId());
                ChannelBrandDto channelBrandDto=channelBrandMapper.getBrandInfo(req);
                dto.setOrgId(channelBrandDto.getOrgId()+dto.getId()+"/");
            }
            updateResult=channelBrandMapper.updateBrand(dto);
        }
        if(count>0 && updateResult>0){
            resultData.setSuccess("新增成功");
        }else{
            resultData.setFail("新增失败");
        }
        return resultData;
    }
    //修改渠道品牌
    @Transactional(rollbackFor=Exception.class)
    public ResultData updateBrand(ChannelBrandDto dto) throws Exception{
        ResultData resultData = new ResultData();
        //校验渠道品牌名称是否存在
        int resultNum=channelBrandMapper.checkBrandInfo(dto);
        if(resultNum>0){
            resultData.setFail("该渠道名称已存在，请修改后在提交");
            return resultData;
        }

        int count = channelBrandMapper.updateBrand(dto);

        BusinessManagerDto businessManagerDto=new BusinessManagerDto();
        businessManagerDto.setBrandId(dto.getId().toString());
        businessManagerDto.setBrandName(dto.getBrandName());
        businessManagerDto.setOperationType("3");
        businessManagerMapper.execCompany(businessManagerDto);//执行存储过程同步友房通数据

        if(count>0){
            resultData.setSuccess("修改成功");
        }else{
            resultData.setFail("修改失败");
        }
        return resultData;
    }
    //删除渠道品牌
    @Transactional(rollbackFor=Exception.class)
    public ResultData deleteBrand(ChannelBrandDto dto) throws Exception{
        ResultData resultData = new ResultData();
        //校验渠道品牌是否有对应的渠道，如果有不让删除
        int num=channelBrandMapper.checkBusiness(dto);
        if(num>0){
            resultData.setFail("该渠道品牌已有渠道数据，不允许删除！");
            return resultData;
        }

        int count = channelBrandMapper.deleteBrand(dto);
        if(count>0){
            resultData.setSuccess("删除成功");
        }else{
            resultData.setFail("删除失败");
        }
        return resultData;
    }

    //递归生成树结构json数据
    private void recursionTree(ChannelBrandDto menu,List<ChannelBrandDto> newList){
        //重新组织结果list
        if(menu.getParentId()==0){
            newList.add(menu);
        }else{
            boolean flag=false;
            for(ChannelBrandDto brand:newList){
                if(menu.getParentId().equals(brand.getId())){
                    brand.getChildren().add(menu);
                    flag=true;
                }
            }
            if(!flag){
                for(ChannelBrandDto brand:newList){
                    recursionTree(menu,brand.getChildren());
                }
            }
        }

    }
}
