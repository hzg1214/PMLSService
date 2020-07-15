package cn.com.eju.deal.NoSigned.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.NoSigned.model.NosignDetail;



public interface NoSignDetailMapper {
	
	 /**
     * 根据Id查询未签门店信息
    * @param contractNo
    * @return
     */
    List<NosignDetail> getById(Map<String, Object> reqMap) throws Exception;

}
