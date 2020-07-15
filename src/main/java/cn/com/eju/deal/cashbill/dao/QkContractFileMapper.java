package cn.com.eju.deal.cashbill.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.cashbill.model.QkContractFile;

public interface QkContractFileMapper {
	
	int deleteByPrimaryKey(Integer id);
	
	QkContractFile selectByPrimaryKey(Integer id);

    int insert(QkContractFile record);

    int insertSelective(QkContractFile record);
    
    //根据frameOaNo查询V_FCJY_LDKJ_File 联动框架
    List<QkContractFile> selLDKJFileByFrameOaNo(Map<String,Object> map);

    //根据flowId查询V_FCJY_ZJHZ_File _File 中介合作
    List<QkContractFile> selZJHZFileByFlowId(Map<String,Object> map);
}