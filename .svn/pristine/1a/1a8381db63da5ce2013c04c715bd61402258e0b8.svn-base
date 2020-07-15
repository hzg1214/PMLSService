package cn.com.eju.deal.oa.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dataSource.DbcontextHolder;
import cn.com.eju.deal.dto.oa.OaOperatorDto;
import cn.com.eju.deal.mapper.signContract.SignContractMapper;
import cn.com.eju.deal.model.signContract.ContractAuditRecordDto;
import cn.com.eju.deal.oa.dao.OaOperatorMapper;
import cn.com.eju.deal.oa.model.OaOperator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**   
* service层
* @author li_xiaodong
* @date 2016年2月2日 下午7:57:09
*/
@Service("oaOperatorService")
public class OaOperatorService extends BaseService
{
    
    @Resource
    private OaOperatorMapper oaOperatorMapper;

    @Resource
    private SignContractMapper signContractMapper;
    
    /** 
    * 查询
    * @param id
    * @return
    */
    
    public OaOperator getById(int id)
        throws Exception
    {
        OaOperator oaOperator = oaOperatorMapper.getById(id);
        return oaOperator;
    }
    
    /** 
     * 查询
     * @param id
     * @return
     */
    
    public OaOperator getByUserCode(String operCode)
        throws Exception
    {
        OaOperator oaOperator = oaOperatorMapper.getByUserCode(operCode);
        return oaOperator;
    }
    
    /** 
     * 查询-list
     * @param queryParam
     * @return
     */
    
    public ResultData<List<OaOperatorDto>> queryList(Map<?, ?> param)
        throws Exception
    {
        
        //构建返回
        ResultData<List<OaOperatorDto>> resultData = new ResultData<List<OaOperatorDto>>();
        
        //查询
        final List<OaOperator> moList = oaOperatorMapper.queryList(param);
        
        //转换
        List<OaOperatorDto> dtoList = convertData(moList);
        
        resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
        
        resultData.setReturnData(dtoList);
        
        return resultData;
    }
    
    /** 
     * 创建
     * @param param
     * @return
     */
    
    public int create(OaOperator oaOperator)
        throws Exception
    {
        int count = oaOperatorMapper.create(oaOperator);
        return count;
    }
    
    /** 
     * 更新
     * @param param
     * @return
     */
    
    public int update(OaOperator oaOperator)
        throws Exception
    {
        int count = oaOperatorMapper.update(oaOperator);
        return count;
    }
    
    /** 
    * 删除
    * @param id 
    * @param updateId 更新人
    * @param updateTime 更新时间
    * @return
    */
    
    public int delete(int id)
        throws Exception
    {
        int count = oaOperatorMapper.deleteById(id);
        return count;
    }
    
    /** 
     * 对象转换MO--DTO
     * @param stuList
     * @return List<StudentDto>
     */
    private List<OaOperatorDto> convertData(List<OaOperator> moList)
        throws Exception
    {
        List<OaOperatorDto> dtoList = new ArrayList<OaOperatorDto>();
        
        if (null != moList && !moList.isEmpty())
        {
            OaOperatorDto dto = null;
            for (OaOperator mo : moList)
            {
                dto = new OaOperatorDto();
                BeanUtils.copyProperties(mo, dto);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    /**
     * 查询oa审核原因
     * @param flowId
     * @return
     */
    public List<ContractAuditRecordDto> getOAAuditInfo(String flowId) {

        List<ContractAuditRecordDto> list = null;

        try{
            ContractAuditRecordDto contractAuditRecordDto = new ContractAuditRecordDto();
            contractAuditRecordDto.setFlowId(flowId);
            //设置OA数据源
            DbcontextHolder.setDbType("dataSourceOA");
            list = signContractMapper.getContractAuditRecordList(contractAuditRecordDto);
            //还原数据源
            DbcontextHolder.setDbType("dataSourceMain");
        }catch (Exception e){
            DbcontextHolder.setDbType("dataSourceMain");
        }

        return list;
    }
}
