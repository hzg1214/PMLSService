package cn.com.eju.deal.contract.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.contract.model.ContractChange;
import cn.com.eju.deal.contract.service.ContractChangeNewService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.contract.ContractChangeDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("contractChangeNew")
public class ContractChangeNewController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private ContractChangeNewService contractChangeNewService;

    @RequestMapping(value = "/queryContractChangeNewList/{contractId}", method = RequestMethod.GET)
    public String queryContractChangeNewList(@PathVariable Integer contractId) {
        ResultData<List<ContractChange>> resultData = new ResultData<>();

        try{
            List<ContractChange> list = contractChangeNewService.queryContractChangeNewList(contractId);

            if(!CollectionUtils.isEmpty(list)){
                resultData.setReturnData(list);
                resultData.setTotalCount(list.size()+"");
            }
        }catch (Exception e){
            resultData.setFail("合同变更查询失败！");
            logger.error("contractChangeNew", "ContractChangeNewController",
                    "queryContractChangeNewList", "contractId="+contractId, -1, "", "合同变更查询失败！", e);
        }

        return resultData.toString();
    }

    @RequestMapping(value = "/getContractAndStoreInfo/{storeId}/{contractId}", method = RequestMethod.GET)
    public String getContractAndStoreInfo(@PathVariable Integer storeId,@PathVariable Integer contractId){
        ResultData<ContractInfoDto> resultData = new ResultData<>();

        try{
            ContractInfoDto contractInfoDto = contractChangeNewService.getContractAndStoreInfo(storeId,contractId);
            resultData.setReturnData(contractInfoDto);
        }catch (Exception e){
            resultData.setFail("合同及门店信息查询失败！");
            logger.error("contractChangeNew", "ContractChangeNewController",
                    "getContractAndStoreInfo", "contractId="+contractId+",storeId="+storeId, -1, "", "合同及门店信息查询失败！", e);
        }

        return resultData.toString();
    }

    @RequestMapping(value = "/saveContractChange",method = RequestMethod.POST)
    public ResultData<?> saveContractChange(@RequestBody String jsonDto){
        ResultData<?> resultData = new ResultData<>();
        ContractChangeDto contractChangeDto = JsonUtil.parseToObject(jsonDto, ContractChangeDto.class);

        try{
            resultData = contractChangeNewService.saveContractChange(contractChangeDto);
        }catch (Exception e){
            resultData.setFail("保存三方变更失败！");
            logger.error("contractChangeNew", "ContractChangeNewController",
                    "saveContractChange", jsonDto, -1, "", "保存三方变更失败！", e);
        }

        return resultData;
    }

    @RequestMapping(value = "/findContractChangeNewById/{id}", method = RequestMethod.GET)
    public String findContractChangeNewById(@PathVariable Integer id){
        ResultData<ContractChangeDto> resultData = new ResultData<>();

        try{
            ContractChangeDto contractChangeDto = contractChangeNewService.findContractChangeNewById(id);
            resultData.setReturnData(contractChangeDto);
        }catch (Exception e){
            resultData.setFail("查询三方变更失败");
            logger.error("contractChangeNew", "ContractChangeNewController",
                    "findContractChangeNewById", "id="+id, -1, "", "查询三方变更失败！", e);
        }

        return resultData.toString();
    }
}
