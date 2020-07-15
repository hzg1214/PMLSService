package cn.com.eju.deal.frameContract.dao;

import java.util.List;

import cn.com.eju.deal.frameContract.model.OaLnkFrameContractReturn;

public interface OaLnkFrameContractReturnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaLnkFrameContractReturn record);

    int insertSelective(OaLnkFrameContractReturn record);

    OaLnkFrameContractReturn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OaLnkFrameContractReturn record);

    int updateByPrimaryKey(OaLnkFrameContractReturn record);
    
    /** 
     * 取得未处理的OA审批结果
     * @return
     */
     public List<OaLnkFrameContractReturn> getOaLnkFrameContractReturn()throws Exception;
      /**
       * 更新OA审批结果
       */
     public Integer updateOaLnkFrameContractReturn(OaLnkFrameContractReturn oaLnkFrameContractReturn) throws Exception;
      
}