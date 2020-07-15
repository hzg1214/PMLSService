/**
 * Copyright (c) 2017, www.ehousechina.com.
 * Project Name:PMLSService
 * File Name:OmsInteractiveService.java
 * Package Name:cn.com.eju.deal.common.service
 * Date:2017年10月30日下午3:42:59
 */
package cn.com.eju.deal.common.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.dao.OmsInteractiveMapper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.dto.contract.DepositDto;
import cn.com.eju.deal.dto.contract.PerformNodeRecordDto;
import cn.com.eju.deal.dto.store.DecorationDto;
import cn.com.eju.deal.model.cashier.CashierDto;
import cn.com.eju.deal.open.model.ContractFlowDto;

/**
 * ClassName: OmsInteractiveService <br/>
 * Description: 与OMS系统对接服务类 <br/>
 * 
 * @author yinkun
 * @date: 2017年10月30日 下午3:42:59 <br/>
 * @version V1.0
 */
@Service
public class OmsInteractiveService {
    
    @Resource
    private OmsInteractiveMapper mapper;
    
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * 【描述】: 批量新增合同流程
     *
     * @author yinkun
     * @date: 2017年10月30日 下午4:28:53
     * @param dtoList
     */
    public ResultData<?> batchCreateContractFlow(List<ContractFlowDto> dtoList) {
        ResultData<?> resultData = new ResultData<>();
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
            for(ContractFlowDto k : dtoList) {
                if (k.getPerformDateStr() != null) {
                    k.setPerformDate(sdf.parse(k.getPerformDateStr()));
                }
            };
            mapper.batchContractFlow(dtoList);
        }catch (Exception e) {
            resultData.setFail();
            logger.error("OmsInteract", "OmsInteractiveService", "batchCreateContractFlow",
                    "input param: jsonDto=" + dtoList, 0, "", "批量新增异常", e);
        }
        return resultData;
    }
    
    /**
     * 【描述】: 批量新增装修
     *
     * @author yinkun
     * @date: 2017年10月30日 下午4:40:02
     * @param dtoList
     */
    public ResultData<?> batchCreateDecoration(List<DecorationDto> dtoList) {
        ResultData<?> resultData = new ResultData<>();
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
            for(DecorationDto k : dtoList) {
                if (k.getDateLifeStartStr() != null) {
                    k.setDateLifeStart(sdf.parse(k.getDateLifeStartStr()));
                }
                if (k.getDateLifeEndStr() != null) {
                    k.setDateLifeEnd(sdf.parse(k.getDateLifeEndStr()));
                }
            };
            mapper.batchCreateDecoration(dtoList);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("OmsInteract", "OmsInteractiveService", "batchCreateDecoration",
                    "input param: jsonDto=" + dtoList, 0, "", "批量新增异常", e);
        }
        return resultData;
    }
    
    /**
     * 【描述】: 批量更新合同状态
     *
     * @author yinkun
     * @date: 2017年10月30日 下午4:49:07
     * @param dtoList
     */
    public ResultData<?> batchUpdateContractState(List<DepositDto> dtoList) {
        ResultData<?> resultData = new ResultData<>();
        try {
            mapper.batchUpdateContractState(dtoList);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("OmsInteract", "OmsInteractiveService", "batchUpdateContractState",
                    "input depositDtoJson:depositDtoJson=" + dtoList, 0, "", "更新对象异常", e);
        }
        return resultData;
    }
    
    /**
     * 【描述】: 新增保证金
     *
     * @author yinkun
     * @date: 2017年10月30日 下午4:55:37
     * @param dto
     * @return
     */
    public ResultData<?> createDeposit(DepositDto dto) {
        ResultData<?> resultData = new ResultData<>();
        try {
            //赋值
            if (dto.getItemAmount() == null) {
                resultData.setFail();
                return resultData;
            }
            dto.setUncollectAmount(dto.getItemAmount());
            dto.setReceivedAmount(BigDecimal.ZERO);
            dto.setReceiveState(10101);
            Date date = new Date();
            dto.setDateCreate(date);
            dto.setDateUpdate(date);
            dto.setbDelFlag(new Boolean(false));

            int count = mapper.createDeposit(dto);
            if (count <= 0) {
                resultData.setFail();
            }
        } catch (Exception e) {
            logger.error("OmsInteract", "OmsInteractiveService", "createDeposit",
                    "input jsonDto:jsonDto=" + dto, 0, "", "创建对象异常", e);

            resultData.setFail();
        }
        return resultData;
    }
    
    /**
     * 【描述】: 新增合同业绩节点
     *
     * @author yinkun
     * @date: 2017年10月30日 下午5:18:29
     * @param dto
     * @return
     */
    public ResultData<?> createPerformNodeRecord(PerformNodeRecordDto dto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try {
            //赋值
            Date date = new Date();
            dto.setDateCreate(date);
            dto.setDateUpdate(date);
            dto.setDelFlag(new Boolean(false));
            if (dto.getDepositArrivalDateStr() != null) {
                dto.setDepositArrivalDate(sdf.parse(dto.getDepositArrivalDateStr()));
            }
            if (dto.getOaApprovalDateStr() != null) {
                dto.setOaApprovalDate(sdf.parse(dto.getOaApprovalDateStr()));
            }
            int count = mapper.createPerformNodeRecord(dto);
            if (count <= 0) {
                resultData.setFail();
            }
        } catch (Exception e) {
            logger.error("OmsInteract", "OmsInteractiveService", "createPerformNodeRecord",
                    "input jsonDto:jsonDto=" + dto, 0, "", "创建对象异常", e);

            resultData.setFail();
        }
        return resultData;
    }
    
    /**
     * 【描述】: 新增合同业绩节点
     *
     * @author yinkun
     * @date: 2017年10月30日 下午5:18:47
     * @param dto
     * @return
     */
    public ResultData<?> updatePerformNodeRecord(PerformNodeRecordDto dto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        // 构建返回
        ResultData<?> resultData = new ResultData<>();
        try {
            //赋值
            dto.setDateUpdate(new Date());
            dto.setDelFlag(new Boolean(false));
            if (dto.getDepositArrivalDateStr() != null) {
                dto.setDepositArrivalDate(sdf.parse(dto.getDepositArrivalDateStr()));
            }
            if (dto.getOaApprovalDateStr() != null) {
                dto.setOaApprovalDate(sdf.parse(dto.getOaApprovalDateStr()));
            }
            int count = mapper.updatePerformNodeRecord(dto);
            if (count <= 0) {
                resultData.setFail();
            } else {
                resultData.setTotalCount(String.valueOf(count));
                resultData.setReturnCode(ReturnCode.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("OmsInteract", "OmsInteractiveService", "updatePerformNodeRecord",
                    "input param: jsonDto=" + dto, 0, "", "单个更新异常", e);
            resultData.setFail();
        }

        return resultData;
    }
    
    public ResultData<?> updateOmsDecoration(DecorationDto dto) {
        // 构建返回
        ResultData<?> resultData = new ResultData<>();
        try {
            int count = mapper.updateOmsDecoration(dto);
            if (count <= 0) {
                resultData.setFail();
            } else {
                resultData.setTotalCount(String.valueOf(count));
                resultData.setReturnCode(ReturnCode.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("OmsInteract", "OmsInteractiveService", "updateOmsDecoration",
                    "input param: jsonDto=" + dto, 0, "", "单个更新异常", e);
            resultData.setFail();
        }

        return resultData;
    }
    
    public int getDecCountByMap(DecorationDto dto) {
        // 构建返回
    	 int count = 0;
        try {
            count = mapper.getDecCountByMap(dto);
            
        } catch (Exception e) {
            logger.error("OmsInteract", "OmsInteractiveService", "getDecCountByMap",
                    "input param: jsonDto=" + dto, 0, "", "获取已装修流程中的数量方法异常", e);            
        }
        return count;
    }
    
    public CashierDto getPayFeeByPaySeq(String paySeq) {
        return mapper.getPayFeeByPaySeq(paySeq);
    }
}

