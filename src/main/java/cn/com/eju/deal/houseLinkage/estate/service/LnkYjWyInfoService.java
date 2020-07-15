package cn.com.eju.deal.houseLinkage.estate.service;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.houseLinkage.estate.dao.LnkYjWyInfoMapper;
import cn.com.eju.deal.houseLinkage.estate.model.LnkYjWyInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("lnkYjWyInfoService")
public class LnkYjWyInfoService {

    @Resource
    LnkYjWyInfoMapper lnkYjWyInfoMapper;


    public ResultData<List<LnkYjWyInfo>> getWyInfoList() {
        ResultData<List<LnkYjWyInfo>> resultData = new ResultData<>();
        List<LnkYjWyInfo> list = lnkYjWyInfoMapper.getWyInfoList();
        resultData.setReturnData(list);
        return resultData;
    }
}
