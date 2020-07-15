package cn.com.eju.deal.service.cashierInfo;

import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dataSource.DbcontextHolder;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.mapper.cashierInfo.CashierInfoMapper;
import cn.com.eju.deal.mapper.signContract.SignContractMapper;
import cn.com.eju.deal.model.signContract.ContractAuditRecordDto;
import cn.com.eju.deal.model.signContract.ContractNewDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu on 2017/4/11.
 */
@Service("cashierInfoService")
public class CashierInfoService {

    @Resource
    private CashierInfoMapper cashierInfoMapper;

    @Resource
    private FileRecordMainMapper fileRecordMainMapper;

    @Resource
    private SignContractMapper signContractMapper;

    public ResultData<List<ContractNewDto>> getCashierInfoListData(ContractNewDto dto) {
        ResultData<List<ContractNewDto>> resultData = new ResultData<List<ContractNewDto>>();
        List<ContractNewDto> list = cashierInfoMapper.getCashierInfoListData(dto);
        resultData.setTotalCount("0");
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
            resultData.setTotalCount(list.get(0).getDataCount() + "");
        }
        return resultData;
    }

    public ResultData<List<ContractNewDto>> getCashierInfoById(ContractNewDto dto) throws Exception {
        ResultData<List<ContractNewDto>> resultData = new ResultData<List<ContractNewDto>>();
        List<ContractNewDto> list = cashierInfoMapper.getCashierInfoById(dto);

        if (list != null && list.size() > 0) {
            if (null != list.get(0).getFlowId() && !list.get(0).getFlowId().isEmpty()) {
                ContractAuditRecordDto contractAuditRecordDto = new ContractAuditRecordDto();
                contractAuditRecordDto.setFlowId(list.get(0).getFlowId());
                //设置OA数据源
                DbcontextHolder.setDbType("dataSourceOA");
                List<ContractAuditRecordDto> contractAuditRecordDtoList = signContractMapper.getContractAuditRecordList(contractAuditRecordDto);
                //还原数据源
                DbcontextHolder.setDbType("dataSourceMain");

                list.get(0).setContractAuditRecordDtoList(contractAuditRecordDtoList);
            }

            if ("银行转账".equals(list.get(0).getCollectionMethod())) {
                List<ContractFileDto> fileBondList = new ArrayList<>();
                FileRecordMain fileBond = new FileRecordMain();
                fileBond.setRefId(dto.getCashierContractId());
                fileBond.setIsDelete(false);
                List<FileRecordMain> fileMdlList = fileRecordMainMapper.getBondByCashierId(fileBond);
                pushFileRecord(fileMdlList, fileBondList);
                list.get(0).setFileRecordMainBond(fileBondList);
            }

            resultData.setReturnData(list);
        }

        return resultData;
    }

    /**
     * 图片信息
     *
     * @param
     * @return
     */
    private void pushFileRecord(List<FileRecordMain> fileMdlList, List<ContractFileDto> fileList) {
        if (null != fileMdlList && fileMdlList.size() > 0) {
            for (int i = 0; i < fileMdlList.size(); i++) {
                ContractFileDto contractFileDto = new ContractFileDto();
                FileRecordMain fileRecordMain = fileMdlList.get(i);
                //对文件数据进行组装处理
                handleFileRecordMain(contractFileDto, fileRecordMain);
                //重新组装返回list
                fileList.add(contractFileDto);
            }
        }
    }

    /**
     * 对文件数据进行组装处理
     *
     * @param contractFileDto
     * @param fileRecordMain
     * @throws Exception
     */
    private void handleFileRecordMain(ContractFileDto contractFileDto, FileRecordMain fileRecordMain) {
        //获取图片路径
        contractFileDto.setFileAbbrUrl(fileRecordMain.getFileAbbrUrl());
        contractFileDto.setFileName(fileRecordMain.getFileFullName());
        contractFileDto.setFileRecordMainId(fileRecordMain.getFileRecordMainId().toString());
        contractFileDto.setFileUrl(fileRecordMain.getFileUrl());
        contractFileDto.setUrl50(fileRecordMain.getUrl50());
    }
}
