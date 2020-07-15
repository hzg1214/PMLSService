package cn.com.eju.deal.contract.service;

import cn.com.eju.deal.achievement.dao.AchievementSettingMapper;
import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.achievement.service.AchievementService;
import cn.com.eju.deal.base.code.dao.CommonCodeMapper;
import cn.com.eju.deal.base.code.model.Code;
import cn.com.eju.deal.base.dto.file.FileDto;
import cn.com.eju.deal.base.file.service.FilesService;
import cn.com.eju.deal.base.file.util.FileAssist;
import cn.com.eju.deal.base.helper.FileHelper;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.WeiphotoHelper;
import cn.com.eju.deal.base.seqNo.api.ISeqNoAPI;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.base.ConvertTo;
import cn.com.eju.deal.common.dao.CityCascadeMapper;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.City;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.common.util.HttpClientUtil;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.contacts.dao.ContactsMapper;
import cn.com.eju.deal.contacts.model.Contacts;
import cn.com.eju.deal.contract.dao.*;
import cn.com.eju.deal.contract.model.*;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.dto.company.CompanyStoreDtoNew;
import cn.com.eju.deal.dto.contacts.ContactsDto;
import cn.com.eju.deal.dto.contract.*;
import cn.com.eju.deal.dto.store.StoreDecorationDto;
import cn.com.eju.deal.dto.store.StoreDepositDto;
import cn.com.eju.deal.dto.store.StoreDto;
//import cn.com.eju.deal.dubbo.op.api.IOPCompanyService;
import cn.com.eju.deal.fangyou.dao.FangyouAccountMapper;
import cn.com.eju.deal.fangyou.model.FYCompany;
import cn.com.eju.deal.fangyou.model.FangyouAccount;
import cn.com.eju.deal.fangyou.model.FangyouAccountLog;
import cn.com.eju.deal.fangyou.service.FangyouService;
import cn.com.eju.deal.open.dao.ContractFlowMapper;
import cn.com.eju.deal.open.dao.ContractRelatePersonMapper;
import cn.com.eju.deal.open.model.ContractFlowDto;
import cn.com.eju.deal.open.model.ContractRelatePerson;
import cn.com.eju.deal.store.dao.StoreMaintainerMapper;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.store.service.StoreDecorationService;
import cn.com.eju.deal.store.service.StoreDepositService;
import cn.com.eju.deal.store.service.StoreService;
import cn.com.eju.deal.user.api.IUserAPI;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dao.PostMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.Group;
import cn.com.eju.deal.user.model.Post;
import cn.com.eju.deal.user.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * service层
 *
 * @author qianwei
 * @date 2016年3月22日 下午7:57:09
 */
@Service("contractService")
public class ContractService extends BaseService<Contract> {
    @Resource
    private StoreService storeService;

    @Resource
    private FangyouService fangyouService;

    @Resource
    private ContractMapper contractMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ContractProductMapper contractProductMapper;

    @Resource
    private ContractStoreMapper contractStoreMapper;

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private CompanyMapper companyMapper;

    @Resource
    private FileRecordMainMapper fileRecordMainMapper;

    @Resource
    private CityCascadeMapper cityCascadeMapper;

    @Resource
    private ContractChangeMapper contractChangeMapper;

    @Resource
    private ContractChangeStoreMapper contractChangeStoreMapper;


    @Resource(name = "filesService")
    private FilesService filesService;

    @Resource(name = "contactsMapper")
    private ContactsMapper contactsMapper;

    @Resource(name = "storeMaintainerMapper")
    private StoreMaintainerMapper storeMaintainerMapper;

    @Resource(name = "groupMapper")
    private GroupMapper groupMapper;

    @Resource(name = "storeDecorationService")
    private StoreDecorationService storeDecorationService;

    @Resource
    private ISeqNoAPI seqNoAPI;

    @Resource
    private ContractRelatePersonMapper contractRelatePersonMapper;

    @Resource(name = "userAPI")
    private IUserAPI userAPI;

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource(name = "postMapper")
    private PostMapper postMapper;

    @Resource(name = "contractFlowMapper")
    private ContractFlowMapper contractFlowMapper;

    @Resource(name = "extOmsService")
    private ExtOmsService extOmsService;

    @Resource(name = "achievementService")
    private AchievementService achievementService;

    @Resource
    private AchievementSettingMapper achievementSettingMapper;

/*    @Resource
    private IOPCompanyService opCompanyService;*/
    
    @Resource
    private OPCompanyHttpService oPCompanyHttpService;


    //Add By NingChao 2017/04/07 Start
    @Resource(name = "oaAttachmentService")
    private OaAttachmentService oaAttachmentService;

    @Resource(name = "oaContractReturnMapper")
    private OaContractReturnMapper oaContractReturnMapper;

    @Resource(name = "oaContractApprovalInfoMapper")
    private OaContractApprovalInfoMapper oaContractApprovalInfoMapper;

    //Add By NingChao 2017/04/07 End

    //Add By Sucen 2017/07/17 Start
    @Resource(name = "oaFYCompanyReturnMapper")
    private OaFYCompanyReturnMapper oaFYCompanyReturnMapper;

    @Resource(name = "storeFangyouAccountMapper")
    private StoreFangyouAccountMapper storeFangyouAccountMapper;

    @Resource(name = "fangyouAccountMapper")
    private FangyouAccountMapper fangyouAccountMapper;
    //Add By Sucen 2017/07/17 Start

    @Resource
    private CitySettingMapper citySettingMapper;

    @Resource
    private CommonCodeMapper commonCodeMapper;

    @Resource
    private StoreDepositService storeDepositService;

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * 查询
     *
     * @param id
     * @return
     */

    //Mod By NingChao 2017/04/07 Start
    //public ContractInfoDto getById(int id)
    public ContractInfoDto getById(int id, int storeId)
    //Mod By NingChao 2017/04/07 End
            throws Exception {
        ContractInfoDto contractInfoDto = new ContractInfoDto();

        // 获取合同信息
        Contract contractMdl = contractMapper.getById(id);

        String accountProvinceNo = contractMdl.getAccountProvinceNo();
        if (!StringUtil.isEmpty(accountProvinceNo)) {
            List<City> accountCityList = cityCascadeMapper.queryCityList(accountProvinceNo);
            contractInfoDto.setAccountCityList(accountCityList);
        }

        //Model转换Dto
        ContractDto ctaDto = new ContractDto();
        BeanUtils.copyProperties(contractMdl, ctaDto);
        // 保证金状态
        BigDecimal totalDepositFee = contractMdl.getTotleDepositFee();
        BigDecimal arrialDepositFee = contractMdl.getArrivalDepositFee();
        if (null != totalDepositFee && null != arrialDepositFee && 0 != arrialDepositFee.compareTo(BigDecimal.ZERO)) {
            if (totalDepositFee.compareTo(arrialDepositFee) != 0) {
                ctaDto.setDepositFeeStatus("部分到账");
            } else {
                ctaDto.setDepositFeeStatus("已到账");
            }
        } else {
            ctaDto.setDepositFeeStatus("未到账");
        }
        Boolean isChanged = contractMdl.getIsChanged();
        if (isChanged) {
            ctaDto.setIsChangedStr("是");
        } else {
            ctaDto.setIsChangedStr("否");
        }
        //合同类型名称（同 合作协议书）
        ctaDto.setContractTypeName(SystemParam.getDicValueByDicCode(contractMdl.getContractType() + ""));
        //OA审核类型名称
        ctaDto.setOaApproveTypeName(SystemParam.getDicValueByDicCode(contractMdl.getOaApproveType()));

        //保证金退款状态名称
        ctaDto.setRefundStateName(SystemParam.getDicValueByDicCode(contractMdl.getRefundState()));

        //住所的
        String cityName = SystemParam.getCityNameByCityNo(contractMdl.getCityNo());
        String districtName = SystemParam.getDistrictNameByDistrictNo(contractMdl.getDistrictNo());
        String areaName = SystemParam.getAreaNameByAreaNo(contractMdl.getAreaNo());

        //公司注册地址
        String cityNameCompany = SystemParam.getCityNameByCityNo(contractMdl.getPartyBcityNo());
        String districtNameCompany = SystemParam.getDistrictNameByDistrictNo(contractMdl.getPartyBdistrictNo());
        String areaNameCompany = SystemParam.getAreaNameByAreaNo(contractMdl.getPartyBareaNo());

        //TODO公司地址不能为空 (公司注册地址地址)
        String partyAddressDetail = cityNameCompany + districtNameCompany + areaNameCompany + contractMdl.getPartyBAddress();
        ctaDto.setPartyAddressDetail(partyAddressDetail);

        //甲方住所详细地址（甲方收件地址）
        String recipientsAddressDetail = cityName + districtName + areaName + contractMdl.getRecipientsAddress();
        ctaDto.setRecipientsAddressDetail(recipientsAddressDetail);

        //合作模式名称
        Code code = commonCodeMapper.queryByDicCode(contractMdl.getContractType().toString());
        if (null != code) {
            ctaDto.setContractTypeName(code.getDicValue());
        }

        // 获取门店信息
        String oldStoreIdArray = "";
        List<StoreDto> storeList = new ArrayList<StoreDto>();
        List<StoreDto> storeMdlList = contractMapper.selectStoreByContractId(contractMdl.getId());
        for (int i = 0; i < storeMdlList.size(); i++) {

            StoreDto dbStore = storeMdlList.get(i);
//Add By GuoPengFei 2017/05/25 合规性 start
            ContractStore cStore = contractStoreMapper.selStoreByContractIdStoreId(id, dbStore.getStoreId());
            dbStore.setABTypeStore(cStore.getABTypeStore());
            dbStore.setBTypeStore(cStore.getBTypeStore());
            dbStore.setBTypeStoreName(cStore.getBTypeStoreName());
//Add By GuoPengFei 2017/05/25 合规性 start            

            //Add By NingChao 2017/04/07 Start
            //续签场合，取得续签的门店信息
            if (storeId != 0 && storeId != dbStore.getStoreId()) {
                continue;
            }
            //Add By NingChao 2017/04/07 End
            // 合同中门店的状态
            if (dbStore.getStoreState() == null || dbStore.getStoreState().intValue() == 0) {
                dbStore.setStoreStateName("正常");
            } else if (dbStore.getStoreState().intValue() == 1) {
                dbStore.setStoreStateName("变更中");
            } else {
                dbStore.setStoreStateName("终止");
            }

            //合同门店中的退款状态
            dbStore.setRefundStateName(SystemParam.getDicValueByDicCode(dbStore.getRefundState()));

            storeList.add(dbStore);
            oldStoreIdArray = oldStoreIdArray + String.valueOf(dbStore.getStoreId()) + ",";
        }

        // 获取产品信息
        List<ProductDto> pdtList = new ArrayList<ProductDto>();
        int level = 1;
        List<Product> pdtMdlList = productMapper.selectProductByContractId(contractMdl.getId());
        for (int i = 0; i < pdtMdlList.size(); i++) {
            ProductDto dto = new ProductDto();
            BeanUtils.copyProperties(pdtMdlList.get(i), dto);
            dto.setLevel(level);
            dto.setProductId(i + 1);
            pdtList.add(dto);
            List<Product> parentMdlList = productMapper.selectProductByParentId(pdtMdlList.get(i).getId());
            for (int j = 0; j < parentMdlList.size(); j++) {
                if (j == 0) {
                    level = level + 1;
                }
                ProductDto pdto = new ProductDto();
                BeanUtils.copyProperties(parentMdlList.get(j), pdto);
                pdto.setLevel(level);
                pdto.setProductId(i + 1);
                pdtList.add(pdto);
                addParentProduct(parentMdlList, j, level, pdtList);
                if (j == parentMdlList.size() - 1 || parentMdlList.size() == 1) {
                    level = level - 1;
                }
            }
        }

        // 获取文件信息
        String fileRecordMainIds = "";
        // 营业证
        List<ContractFileDto> fileBusinessList = new ArrayList<ContractFileDto>();
        FileRecordMain businessFile = new FileRecordMain();
        businessFile.setRefId(contractMdl.getId());
        businessFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList = fileRecordMainMapper.getBusinessByContractId(businessFile);
        fileRecordMainIds = pushFileRecord(fileMdlList, fileRecordMainIds, fileBusinessList);
        //Add By NingChao 2017/04/07 Start
        String fileRecordMainIds_Renew = "";
        String fileTypeId_Renew = "";
        ArrayList<String> strOldFileList = new ArrayList<String>();
        if (storeId > 0) {
            strOldFileList = saveFileRecordMainByRenew(fileMdlList, fileBusinessList);
            fileRecordMainIds_Renew = strOldFileList.get(0);
            fileTypeId_Renew = strOldFileList.get(1);
        }
        //Add By NingChao 2017/04/07 End        
        // 身份证
        List<ContractFileDto> fileCardList = new ArrayList<ContractFileDto>();
        FileRecordMain cardFile = new FileRecordMain();
        cardFile.setRefId(contractMdl.getId());
        cardFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList2 = fileRecordMainMapper.getCardByContractId(cardFile);
        fileRecordMainIds = pushFileRecord(fileMdlList2, fileRecordMainIds, fileCardList);
        //Add By NingChao 2017/04/07 Start
        if (storeId > 0) {
            strOldFileList = saveFileRecordMainByRenew(fileMdlList2, fileCardList);
            fileRecordMainIds_Renew += strOldFileList.get(0);
            fileTypeId_Renew += strOldFileList.get(1);
        }
        //Add By NingChao 2017/04/07 End
        // 合同照片
        List<ContractFileDto> filePhotoList = new ArrayList<ContractFileDto>();
        FileRecordMain photoFile = new FileRecordMain();
        //Add By NingChao 2017/04/07 Start
        if (storeId == 0) {
            //Add By NingChao 2017/04/07 End
            photoFile.setRefId(contractMdl.getId());
            photoFile.setIsDelete(false);
            List<FileRecordMain> fileMdlList3 = fileRecordMainMapper.getPhotoByContractId(photoFile);
            fileRecordMainIds = pushFileRecord(fileMdlList3, fileRecordMainIds, filePhotoList);
        }
        // 门店照片
        List<ContractFileDto> fileStoreList = new ArrayList<ContractFileDto>();
        FileRecordMain storeFile = new FileRecordMain();
        storeFile.setRefId(contractMdl.getId());
        storeFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList4 = fileRecordMainMapper.getStoreByContractId(storeFile);
        fileRecordMainIds = pushFileRecord(fileMdlList4, fileRecordMainIds, fileStoreList);
        //Add By NingChao 2017/04/07 Start
        if (storeId > 0) {
            strOldFileList = saveFileRecordMainByRenew(fileMdlList4, fileStoreList);
            fileRecordMainIds_Renew += strOldFileList.get(0);
            fileTypeId_Renew += strOldFileList.get(1);
        }
        //Add By NingChao 2017/04/07 End
        // 房友系统申请安装单
        List<ContractFileDto> fileInstallList = new ArrayList<ContractFileDto>();
        FileRecordMain installFile = new FileRecordMain();
        installFile.setRefId(contractMdl.getId());
        installFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList5 = fileRecordMainMapper.getInstallByContractId(installFile);
        fileRecordMainIds = pushFileRecord(fileMdlList5, fileRecordMainIds, fileInstallList);
        //Add By NingChao 2017/04/07 Start
        if (storeId > 0) {
            strOldFileList = saveFileRecordMainByRenew(fileMdlList5, fileInstallList);
            fileRecordMainIds_Renew += strOldFileList.get(0);
            fileTypeId_Renew += strOldFileList.get(1);
        }

        // 重要提示函
        List<ContractFileDto> fileNoticeList = new ArrayList<ContractFileDto>();
        FileRecordMain noticeFile = new FileRecordMain();
        noticeFile.setRefId(contractMdl.getId());
        noticeFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList7 = fileRecordMainMapper.getNoticeByContractId(noticeFile);
        fileRecordMainIds = pushFileRecord(fileMdlList7, fileRecordMainIds, fileNoticeList);

        if (storeId > 0) {
            strOldFileList = saveFileRecordMainByRenew(fileMdlList7, fileNoticeList);
            fileRecordMainIds_Renew += strOldFileList.get(0);
            fileTypeId_Renew += strOldFileList.get(1);
        }
        // 合同补充协议
        List<ContractFileDto> fileComplementList = new ArrayList<ContractFileDto>();
        FileRecordMain complementFile = new FileRecordMain();
        complementFile.setRefId(contractMdl.getId());
        complementFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList8 = fileRecordMainMapper.getComplementByContractId(complementFile);
        fileRecordMainIds = pushFileRecord(fileMdlList8, fileRecordMainIds, fileComplementList);

        if (storeId > 0) {
            strOldFileList = saveFileRecordMainByRenew(fileMdlList8, fileComplementList);
            fileRecordMainIds_Renew += strOldFileList.get(0);
            fileTypeId_Renew += strOldFileList.get(1);
        }

        //Add By NingChao 2017/04/07 End
        // 其他文件
        List<ContractFileDto> fileOtherList = new ArrayList<ContractFileDto>();
        FileRecordMain otherFile = new FileRecordMain();
        otherFile.setRefId(contractMdl.getId());
        otherFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList6 = fileRecordMainMapper.getOtherByContractId(otherFile);
        fileRecordMainIds = pushFileRecord(fileMdlList6, fileRecordMainIds, fileOtherList);
        //Add By NingChao 2017/04/07 Start
        if (storeId > 0) {
            strOldFileList = saveFileRecordMainByRenew(fileMdlList6, fileOtherList);
            fileRecordMainIds_Renew += strOldFileList.get(0);
            fileTypeId_Renew += strOldFileList.get(1);
        }
        //Add By NingChao 2017/04/07 End
        // 返回值
        // 返回值
        contractInfoDto.setContract(ctaDto);
        contractInfoDto.setProductDetails(pdtList);
        contractInfoDto.setStoreDetails(storeList);
        contractInfoDto.setFileRecordMainBusiness(fileBusinessList);
        contractInfoDto.setFileRecordMainCard(fileCardList);
        contractInfoDto.setFileRecordMainPhoto(filePhotoList);
        contractInfoDto.setFileRecordMainStore(fileStoreList);
        contractInfoDto.setFileRecordMainInstall(fileInstallList);
        contractInfoDto.setFileRecordMainNotice(fileNoticeList);
        contractInfoDto.setFileRecordMainComplement(fileComplementList);
        contractInfoDto.setFileRecordMainOther(fileOtherList);

        if (StringUtil.isNotEmpty(oldStoreIdArray) && oldStoreIdArray.length() > 0) {
            oldStoreIdArray = oldStoreIdArray.substring(0, oldStoreIdArray.length() - 1);
        }

        contractInfoDto.setOldStoreIdArray(oldStoreIdArray);
        //Add By NingChao 2017/04/07 Start
        if (storeId > 0) {
            if (StringUtil.isNotEmpty(fileRecordMainIds_Renew) && fileRecordMainIds_Renew.length() > 0) {
                fileRecordMainIds_Renew = fileRecordMainIds_Renew.substring(0, fileRecordMainIds_Renew.length() - 1);
                fileTypeId_Renew = fileTypeId_Renew.substring(0, fileTypeId_Renew.length() - 1);
            }

            contractInfoDto.setFileRecordMainIds(fileRecordMainIds_Renew);
            contractInfoDto.setFileTypeIds(fileTypeId_Renew);
        } else {
            //Add By NingChao 2017/04/07 End
            if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0) {
                fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
            }

            contractInfoDto.setFileRecordMainIds(fileRecordMainIds);
        }
        return contractInfoDto;
    }

    //Add By NingChao 2017/04/07 Start
    /*
     * 续签时原合同带过来的文件保存
     * fileMdlList:文件集合
     */
    private ArrayList<String> saveFileRecordMainByRenew(List<FileRecordMain> fileMdlList, List<ContractFileDto> fileList) {
        ArrayList<String> arrayList = new ArrayList<String>();
        String strfileRecordMainIds = "";
        String strfileTypeId = "";
        if (null != fileMdlList && fileMdlList.size() > 0) {
            fileList.clear();
            for (FileRecordMain fileRecordMain : fileMdlList) {
                FileRecordMain fileRecordMainMdl = new FileRecordMain();
                fileRecordMainMdl.setCompanyId(fileRecordMain.getCompanyId());
                //fileRecordMainMdl.setRefId("");
                fileRecordMainMdl.setFileId(fileRecordMain.getFileId());
                fileRecordMainMdl.setFileTypeId(fileRecordMain.getFileTypeId());
                fileRecordMainMdl.setFileFullName(fileRecordMain.getFileFullName());
                fileRecordMainMdl.setFileSourceId(fileRecordMain.getFileSourceId());
                fileRecordMainMdl.setRemark(fileRecordMain.getRemark());
                fileRecordMainMdl.setDateCreate(new Date());
                fileRecordMainMdl.setUserCreate(fileRecordMain.getUserCreate());
                fileRecordMainMdl.setFileNo(fileRecordMain.getFileNo());
                fileRecordMainMdl.setIsDelete(false);

                fileRecordMainMdl.setSellFileNo(fileRecordMain.getSellFileNo());
                fileRecordMainMdl.setFileUrl(fileRecordMain.getFileUrl());
                fileRecordMainMdl.setUrl50(fileRecordMain.getUrl50());
                fileRecordMainMdl.setFileSuffix(fileRecordMain.getFileSuffix());
                fileRecordMainMdl.setFileAbbrUrl(fileRecordMain.getFileAbbrUrl());
                fileRecordMainMdl.setSfImage(fileRecordMain.getSfImage());
                int count = fileRecordMainMapper.create(fileRecordMainMdl);
                if (count > 0) {
                    strfileRecordMainIds = strfileRecordMainIds + fileRecordMainMdl.getFileRecordMainId().toString() + ",";
                    strfileTypeId = strfileTypeId + fileRecordMainMdl.getFileTypeId().toString() + ",";
                }


                ContractFileDto contractFileDto = new ContractFileDto();
                //对文件数据进行组装处理
                try {
                    handleFileRecordMain(contractFileDto, fileRecordMainMdl);
                    //重新组装返回list
                    fileList.add(contractFileDto);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
        arrayList.add(strfileRecordMainIds);
        arrayList.add(strfileTypeId);
        return arrayList;
    }
//Add By NingChao 2017/04/07 End

    /**
     * 图片信息
     *
     * @param
     * @return
     */
    public String pushFileRecord(List<FileRecordMain> fileMdlList, String fileRecordMainIds, List<ContractFileDto> fileList)
            throws Exception {
        if (null != fileMdlList && fileMdlList.size() > 0) {
            for (int i = 0; i < fileMdlList.size(); i++) {
                ContractFileDto contractFileDto = new ContractFileDto();
                FileRecordMain fileRecordMain = fileMdlList.get(i);
                //对文件数据进行组装处理
                handleFileRecordMain(contractFileDto, fileRecordMain);

                //重新组装返回list
                fileList.add(contractFileDto);
                fileRecordMainIds = fileRecordMainIds + contractFileDto.getFileRecordMainId() + ",";

            }
        }
        return fileRecordMainIds;
    }

    /**
     * 对文件数据进行组装处理
     *
     * @param contractFileDto
     * @param fileRecordMain
     * @throws Exception
     */
    private void handleFileRecordMain(ContractFileDto contractFileDto, FileRecordMain fileRecordMain)
            throws Exception {
        //获取图片路径
        contractFileDto.setFileAbbrUrl(fileRecordMain.getFileAbbrUrl());
        contractFileDto.setFileName(fileRecordMain.getFileFullName());
        contractFileDto.setFileRecordMainId(fileRecordMain.getFileRecordMainId().toString());
        contractFileDto.setFileUrl(fileRecordMain.getFileUrl());
        contractFileDto.setUrl50(fileRecordMain.getUrl50());
    }

    /**
     * 对文件数据进行组装处理
     *
     * @param contractFileDto
     * @param fileRecordMain
     * @throws Exception
     */
    private void handleFile(ContractFileDto contractFileDto, FileRecordMain fileRecordMain)
            throws Exception {
        // 1、先从关系表中取，如果取不到 ，则通过fileNo 去渠道系统主表 拿到渠道及fileCode，去相应文件系统取出文件地址；
        // 获取文件渠道系统配置（外部文件系统的配置）
        Map<?, ?> mapTemp = getChannelConfig();

        //取关系表中的文件Code
        String fileId = fileRecordMain.getFileId();

        //关系主键Id
        String fileRecordMainId = fileRecordMain.getFileRecordMainId().toString();

        if (StringUtil.isNotEmpty(fileId)) {
            // 查询路径
            String queryUrl = (String) mapTemp.get("CRIC_queryfile_path");
            // 下载路径
            String downloadUrl = (String) mapTemp.get("CRIC_downloadfile_path");
            // 授权号
            String permitCode = (String) mapTemp.get("CRIC_file_permit_code");

            //获取图片路径
            String fileUrl = FileHelper.getFilePath(fileId, queryUrl, downloadUrl, permitCode);

            Map<String, Object> handleMap = new HashMap<>();
            handleMap.put("width", "100");
            handleMap.put("height", "100");
            handleMap.put("waterPic", "0");// 无水印图片
            handleMap.put("waterPosition", "0");//  3:左下角
            handleMap.put("cutType", "0");//0-不裁剪
            String fileAbbrUrl =
                    FileHelper.getFilePath(fileRecordMain.getFileId(), FileAssist.UPLOAD_FILE_IS_HANDLE_YES, queryUrl, downloadUrl, permitCode, handleMap);
            contractFileDto.setFileAbbrUrl(fileAbbrUrl);
            contractFileDto.setFileName(fileRecordMain.getFileFullName());
            contractFileDto.setFileRecordMainId(fileRecordMainId);
            contractFileDto.setFileUrl(fileUrl);

        } else {
            //取出fileNo
            String fileNo = fileRecordMain.getFileNo();

            //调用渠道系统，获取文件记录信息
            ResultData<?> reback = filesService.getByFileNo(fileNo);
            Map<?, ?> mapTemppp = (Map<?, ?>) reback.getReturnData();
            if (null != mapTemppp) {
                String fileAbbrUrl = null;
                String fileUrl = null;

                FileDto fileDto = MapToEntityConvertUtil.convert(mapTemppp, FileDto.class, "");

                //fileCode
                String fileCode = fileDto.getFileCode();
                //渠道Code
                String channelCode = fileDto.getChannelCode();
                if ("CRIC".equals(channelCode)) {
                    // 查询路径
                    String queryUrl = (String) mapTemp.get("CRIC_queryfile_path");
                    // 下载路径
                    String downloadUrl = (String) mapTemp.get("CRIC_downloadfile_path");
                    // 授权号
                    String permitCode = (String) mapTemp.get("CRIC_file_permit_code");

                    //获取图片路径
                    fileUrl = FileHelper.getFilePath(fileCode, queryUrl, downloadUrl, permitCode);
                    Map<String, Object> handleMap = new HashMap<>();
                    handleMap.put("width", "100");
                    handleMap.put("height", "100");
                    handleMap.put("waterPic", "0");// 无水印图片
                    handleMap.put("waterPosition", "0");//  3:左下角
                    handleMap.put("cutType", "0");//0-不裁剪
                    fileAbbrUrl =
                            FileHelper.getFilePath(fileCode, FileAssist.UPLOAD_FILE_IS_HANDLE_YES, queryUrl, downloadUrl, permitCode, handleMap);

                } else if ("weiphoto".equals(channelCode)) {
                    // 查询路径
                    String fileViewUrl = (String) mapTemp.get("wp_view_url");

                    fileAbbrUrl = WeiphotoHelper.getFilePath(fileViewUrl, fileCode, "200");
                    fileUrl = WeiphotoHelper.getFilePath(fileViewUrl, fileCode, "5000");

                }

                //设值
                contractFileDto.setFileAbbrUrl(fileAbbrUrl);//缩略图地址
                contractFileDto.setFileRecordMainId(fileRecordMainId);
                contractFileDto.setFileUrl(fileUrl);//原图地址

                contractFileDto.setFileName(fileRecordMain.getFileFullName());//原图名称

            }
        }

    }

    /**
     * 递归产品树
     *
     * @param
     * @return
     */
    private List<ProductDto> addParentProduct(List<Product> parentMdlList, int j, int level, List<ProductDto> pdtList)
            throws Exception {
        List<Product> parentMdlList2 = productMapper.selectProductByParentId(parentMdlList.get(j).getId());
        for (int k = 0; k < parentMdlList2.size(); k++) {
            if (k == 0) {
                level = level + 1;
            }
            ProductDto pdto2 = new ProductDto();
            BeanUtils.copyProperties(parentMdlList2.get(k), pdto2);
            pdto2.setLevel(level);
            pdtList.add(pdto2);
            if (k == parentMdlList2.size() - 1 || parentMdlList2.size() == 1) {
                level = level - 1;
            }
        }
        return pdtList;
    }

    /**
     * 获取产品信息
     *
     * @return
     */

    public ContractInfoDto getProductInfo()
            throws Exception {
        ContractInfoDto contractInfoDto = new ContractInfoDto();

        // 获取产品信息
        List<ProductDto> pdtList = new ArrayList<ProductDto>();
        int level = 1;
        List<Product> pdtMdlList = productMapper.selectProductInfo();
        for (int i = 0; i < pdtMdlList.size(); i++) {
            ProductDto dto = new ProductDto();
            BeanUtils.copyProperties(pdtMdlList.get(i), dto);
            dto.setLevel(level);
            dto.setProductId(i + 1);
            pdtList.add(dto);
            List<Product> parentMdlList = productMapper.selectProductByParentId(pdtMdlList.get(i).getId());
            for (int j = 0; j < parentMdlList.size(); j++) {
                if (j == 0) {
                    level = level + 1;
                }
                ProductDto pdto = new ProductDto();
                BeanUtils.copyProperties(parentMdlList.get(j), pdto);
                pdto.setLevel(level);
                pdto.setProductId(i + 1);
                pdtList.add(pdto);
                addParentProduct(parentMdlList, j, level, pdtList);
                if (j == parentMdlList.size() - 1 || parentMdlList.size() == 1) {
                    level = level - 1;
                }
            }
        }

        // 返回值
        contractInfoDto.setProductDetails(pdtList);

        return contractInfoDto;
    }

    /**
     * 查询-list
     * @return
     */
    public ResultData<List<ContractSearchResultDto>> queryList(Map<?, ?> param)
            throws Exception {
        //构建返回
        ResultData<List<ContractSearchResultDto>> resultData = new ResultData<List<ContractSearchResultDto>>();
        //查询
        final List<ContractSearchResult> craList = contractMapper.selectContractList(param);
        //转换
        List<ContractSearchResultDto> contractDtoList = convertContractSearchResultData(craList);
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(contractDtoList);
        return resultData;
    }

    /**
     * 验证处理 ()
     * check门店已签约类型  （）
     * （
     * 签A：check门店是否都未签
     * 签B：
     * 1、合作模式选B-->所有门店的签约类型必须是“未签”
     * 2、合作模式选A转B-->1.所有门店的签约类型必须是“A版”且审核通过；2.原A门店对应的合同状态不是"变更中"的;3.门店必须是未进行业绩撤销的
     * ）
     *
     * @param queryParam（contractType:合同类型 、storeIdArray:门店ID集合）
     * @return
     */

    public ResultData<Integer> checkStoreLock(Map<?, ?> param)
            throws Exception {
        //Add 2017/04/10 cning 续签保存 ---->
        String originalContractNo = (String) param.get("originalContractNo");
        //Add 2017/04/10 cning 续签保存 <----
        //构建返回
        ResultData<Integer> resultData = new ResultData<Integer>();

        //合作协议书类型
        String contractTypeStr = (String) param.get("contractType");
        Integer contractType = Integer.valueOf(contractTypeStr);

        //门店list（未签）
        List<Store> signedNo = new ArrayList<Store>();

        //门店list（签A）
        List<Store> signedA = new ArrayList<Store>();

        // 合同list（合同状态是:审核通过）
        List<Contract> auditPass = new ArrayList<Contract>();

        // 合同list（合同状态是"审核通过"以外的场合）
        List<Contract> notAuditPass = new ArrayList<Contract>();

        // 门店合同list（storeState是"变更中"的场合）
        List<ContractStore> isChange = new ArrayList<ContractStore>();

        // 门店合同list（storeState是"变更中"以外的场合）
        List<ContractStore> isnotChange = new ArrayList<ContractStore>();

        //合同到期日期
        Date dateLifeEnd = null;

        //门店list（签B）
        List<Store> signedB = new ArrayList<Store>();

        //门店撤销状态List（未撤销）
        List<Store> isnotCancel = new ArrayList<Store>();

        //门店撤销状态List（已撤销）
        List<Store> isCancel = new ArrayList<Store>();

        //门店list（签A2B）
        List<Store> signedA2B = new ArrayList<Store>();

        //门店list（签授牌）
        List<Store> signedSP = new ArrayList<Store>();

        // 门店ID
        String storeIds = (String) param.get("storeIdArray");
        Contract contractcancel = new Contract();

        //region CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同  2019-7-30 add by huangmc begin
        // 定义所有的A类合同都过期
        boolean allTypeAOverDue = true;
        // 定义所有的授权类合同都过期
        boolean allTypeSPOverDue = true;
        // 定义所有的B类都已撤销
        boolean allTypeBCancel = true;
        // 定义所有的A转B类都已撤销
        boolean allTypeA2BCancel = true;
        // 当前时间
        Date currect = new Date();
        //endregion

        if (StringUtil.isNotEmpty(storeIds)) {
            Store store = new Store();
            Contract contract = new Contract();
            ContractStore contractStore = new ContractStore();

            // 门店签约类型
            Integer contractTypeTemp = null;
            // 合同状态
            Integer contractStatus = null;
            // 门店合同状态
            Integer storeState = null;
            //门店撤销状态
            String cancelState = "";

            String[] arrays = storeIds.split(",");

            for (int i = 0; i < arrays.length; i++) {
                // 根据门店ID查询门店信息
                store = storeMapper.getById(Integer.valueOf(arrays[i]));
                //Add By NingChao 2017/04/07 Start
                //取得该门店的最新取消的合同
                if (null == originalContractNo) {
                    contractcancel = contractMapper.getByRenewStoreId(Integer.valueOf(arrays[i]));
                }
                //Add By NingChao 2017/04/07 End

                // 根据门店ID查询最新的合同信息
                contract = contractMapper.getByStoreId(Integer.valueOf(arrays[i]));

                if (null != contract) {
                    // 根据storeId、contractId查询合同门店关联信息
                    Map<String, Object> queryParam = new HashMap<String, Object>();
                    queryParam.put("storeId", Integer.valueOf(arrays[i]));
                    queryParam.put("contractId", contract.getId());
                    // 查询操作
                    contractStore = contractStoreMapper.getContractStore(queryParam);
                    storeState = contractStore.getStoreState();
                    //门店撤消状态
                    cancelState = contractStore.getIsCancel();

                    //合同状态
                    contractStatus = contract.getContractStatus();

                    dateLifeEnd = contract.getDateLifeEnd();

                }

                //region 门店的签约类型 的判断处理
                // 门店签约类型
                contractTypeTemp = store.getContractType();
                // 门店的签约类型是“未签”的场合
                if (null == contractTypeTemp || 0 == contractTypeTemp || DictionaryConstants.CONTRACT_STATUS_STOP.equals(contractStatus)) {
                    signedNo.add(store);
                }
                // 门店的签约类型是“A版”的场合
                else if (DictionaryConstants.CONTRACT_TPYE_A.equals(contractTypeTemp)) {
                    // 合同状态是"审核通过"的场合
                    if (DictionaryConstants.CONTRACT_STATUS_AUDIT_PASS.equals(contractStatus)) {
                        auditPass.add(contract);
                    } else {
                        notAuditPass.add(contract);
                    }

                    // 门店合同状态是"变更中"的场合
                    if (DictionaryConstants.STORESTATE_CHANGE.equals(storeState)) {
                        isChange.add(contractStore);
                    } else {
                        isnotChange.add(contractStore);
                    }

                    //门店的撤消状态
                    if (DictionaryConstants.STORESTATE_ISCANCEL_ISCANCEL.equals(cancelState) || DictionaryConstants.STORESTATE_ISCANCEL_ISCANCELLING.equals(cancelState)) {
                        isCancel.add(store);
                    } else {
                        isnotCancel.add(store);
                    }

                    signedA.add(store);

                    // Start 2019-7-30 add by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
                    // 如果当前时间小于等于合同结束日期
                    if(currect.compareTo(dateLifeEnd) <= 0){
                        // 不是所有的A类合同都过期了
                        allTypeAOverDue = false;
                    }
                    // end 2019-7-30 add by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
                }
                //门店的签约类型是“B版”的场合
                else if (DictionaryConstants.CONTRACT_TPYE_B.equals(contractTypeTemp)) {
                    signedB.add(store);

                    // Start 2019-7-30 add by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
                    // 如果当前时间小于等于合同结束日期
                    if(contractcancel==null){
                        // 不是所有的B类合同都撤销了
                        allTypeBCancel = false;
                    }
                    // end 2019-7-30 add by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
                }
                //门店的签约类型是“A转B版”的场合
                else if (DictionaryConstants.CONTRACT_TYPE_A_2_B.equals(contractTypeTemp)) {
                    signedA2B.add(store);

                    // Start 2019-7-30 add by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
                    // 如果当前时间小于等于合同结束日期
                    if(contractcancel==null){
                        // 不是所有的A转B类合同都撤销了
                        allTypeA2BCancel = false;
                    }
                    // end 2019-7-30 add by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
                }
                //门店的签约类型是“授牌”的场合
                else if (DictionaryConstants.CONTRACT_TYPE_SP.equals(contractTypeTemp)) {
                    signedSP.add(store);

                    // Start 2019-7-30 add by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
                    // 如果当前时间小于等于合同结束日期
                    if(currect.compareTo(dateLifeEnd) <= 0){
                        // 不是所有的授牌类合同都过期了
                        allTypeSPOverDue = false;
                    }
                    // end 2019-7-30 add by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同

                }
                //门店的签约类型是“未签”的场合
                else {
                    signedNo.add(store);
                }
                //endregion

            }
        }

        //Add 2017/04/10 cning 续签保存 ---->
        if (null == originalContractNo) {
            //Add 2017/04/10 cning 续签保存 <----
            //协议书类型为A
            if (DictionaryConstants.CONTRACT_TPYE_A.equals(contractType)) {
                //门店都必须为未签
                if (!signedA.isEmpty() || !signedB.isEmpty() || !signedA2B.isEmpty() || signedNo.isEmpty() || !signedSP.isEmpty()) {
                    resultData.setFail("协议书类型签A的，门店只能都是未签约的！");
                    return resultData;
                }
            }
            //协议书类型为B
            else if (DictionaryConstants.CONTRACT_TPYE_B.equals(contractType)) {

                //region Start modify by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同

                //门店都必须为"未签"
                //Mod By Guopengfei 最新合同为取消的B类合同时，门店可以新签 start
                //if (!signedA.isEmpty() || !signedB.isEmpty() || !signedA2B.isEmpty() || signedNo.isEmpty())
                //if ((!signedA.isEmpty() || !signedB.isEmpty() || !signedA2B.isEmpty() || signedNo.isEmpty()) && contractcancel == null)
//            	if ((!signedB.isEmpty() || !signedA2B.isEmpty() ) && contractcancel == null)
//            	//Mod By Guopengfei 最新合同未取消的B类合同时，门店可以新签 end
//            	{
//	                resultData.setFail("协议书类型签B的，门店只能都是未签约的！");
//	                return resultData;
//            	}


//            	if(!signedA.isEmpty()||!signedSP.isEmpty())
//            	{
//            		Date currect = new Date();
//                	int rtn = currect.compareTo(dateLifeEnd);
//                	if((!signedA.isEmpty()) && (rtn <= 0 ))
//                	{
//    	                resultData.setFail("协议书类型签B的，门店只能都是未签约的！");
//    	                return resultData;
//                	}
//            	}

                // 原合同签署的是B类 且存在不是撤销的 或者是 A转B类 且存在不是撤销的
                //  或者是A类且存在非过期的，或者是授牌类且存在非过期的
                if ((!signedB.isEmpty() && allTypeBCancel == false )
                        || (!signedA2B.isEmpty() && allTypeA2BCancel == false)
                        || (!signedA.isEmpty() && allTypeAOverDue == false)
                        || (!signedSP.isEmpty() && allTypeSPOverDue== false )) {
                    resultData.setFail("协议书类型签B的，门店只能都是未签约的！");
                    return resultData;
                }

                //endregion  End modify by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
            }
            //协议书类型为C
            else if (DictionaryConstants.CONTRACT_TYPE_C.equals(contractType)) {
                //合作模式为

            }
            //协议书类型为A2B
            else if (DictionaryConstants.CONTRACT_TYPE_A_2_B.equals(contractType)) {
                // 门店都必须为已签A
                if (signedA.isEmpty() || !signedB.isEmpty() || !signedNo.isEmpty() || !signedSP.isEmpty()) {
                    resultData.setFail("协议书类型签A转B的，门店必须都是A版并且审核通过！");
                    return resultData;
                } else {
                    // 该门店对应的合同状态不是"审核通过"的场合
                    if (auditPass.isEmpty() || !notAuditPass.isEmpty()) {
                        resultData.setFail("协议书类型签A转B的，门店必须都是A版并且审核通过！");
                        return resultData;
                    }
                    //该门店必须是未撤销的
                    if (isnotCancel.isEmpty() || !isCancel.isEmpty()) {
                        resultData.setFail("协议书类型签A转B的，门店必须都是A版并且审核通过,且未进行业绩撤销的！");
                        return resultData;
                    }
                }

                // 合同状态为"变更中"的场合
                if (!isChange.isEmpty() || isnotChange.isEmpty()) {
                    resultData.setFail("门店合同变更中，不可提交合同！");
                    return resultData;
                }

            }
            //协议书类型为授牌
            else if (DictionaryConstants.CONTRACT_TYPE_SP.equals(contractType)) {
//                //门店都必须为未签
//                if (!signedB.isEmpty() || !signedA2B.isEmpty() || signedNo.isEmpty() || !signedSP.isEmpty()) {
//                    resultData.setFail("协议书类型签授牌的，门店只能都是未签约的！");
//                    return resultData;
//                }
//                if (!signedA.isEmpty()) {
//                    Date currect = new Date();
//                    int rtn = currect.compareTo(dateLifeEnd);
//                    if ((!signedA.isEmpty()) && (rtn <= 0)) {
//                        resultData.setFail("协议书类型签授牌的，门店只能都是未签约的！");
//                        return resultData;
//                    }
//                }

                if ((!signedA.isEmpty() && allTypeAOverDue ==false)
                        || !signedB.isEmpty() || !signedA2B.isEmpty()
                        || (signedA.isEmpty() && signedNo.isEmpty()) || !signedSP.isEmpty()) {
                    resultData.setFail("协议书类型签授牌的，门店只能都是未签约的！");
                    return resultData;
                }

            }

        }
        return resultData;
    }

    /**
     * 根据协议书编号查询合同
     *
     * @return
     */
    public ResultData<List<Contract>> getContractByAgreementNo(String agreementNo) throws Exception {
        ResultData<List<Contract>> resultData = new ResultData<List<Contract>>();

        List<Contract> list = contractMapper.getContractByAgreementNo(agreementNo);
        resultData.setReturnData(list);
        return resultData;
    }

    //Add By cning 2017/07/13 Start

    /**
     * 验证处理--该公司的所有合同是否有审核中或者审核通过的，并且仍在有效期内,如果有，不允许修改公司信息
     */
    public ResultData<List<Map<String, Object>>> checkCompanyContract(Integer companyId)
            throws Exception {
        //构建返回
        ResultData<List<Map<String, Object>>> resultData = new ResultData<>();

        List<Map<String, Object>> list = contractMapper.checkCompanyId(companyId);
        resultData.setReturnData(list);
        return resultData;
    }

    /**
     * 根据公司ID查询合同状态为未签，审核未通过的合同
     */
    public ResultData<List<Contract>> getContractByCompanyId(Integer companyId)
            throws Exception {
        //构建返回
        ResultData<List<Contract>> resultData = new ResultData<List<Contract>>();

        List<Contract> list = contractMapper.getContractByCompanyId(companyId);
        resultData.setReturnData(list);
        return resultData;
    }
    //Add By cning 2017/07/13 End

    /**
     * 创建房友账号
     */

    public ResultData<Integer> createFangyou(Map<?, ?> param)
            throws Exception {
        //构建返回
        ResultData<Integer> resultData = new ResultData<Integer>();

        if (null == param.get("companyId")) {
            resultData.setFail();
            resultData.setReturnMsg("公司id不能为空");
            return resultData;
        }
        if (null == param.get("contractId")) {
            resultData.setFail();
            resultData.setReturnMsg("合同id不能为空");
            return resultData;
        }
        Integer companyId = Integer.valueOf((String) param.get("companyId"));// 公司ID

        Integer contractId = Integer.valueOf((String) param.get("contractId"));
        List<ContractStore> contractStoreList = contractStoreMapper.selStoreByContractId(contractId);
        if (null == contractStoreList || contractStoreList.isEmpty()) {
            resultData.setFail();
            resultData.setReturnMsg("该合同无门店信息");
            return resultData;
        }

        Company company = new Company();
        company = companyMapper.getById(companyId);
        String result = createFangyou(company, contractStoreList.get(0).getStoreId());
        Map<String, Object> rspMap = new HashMap<String, Object>();
        rspMap = JsonUtil.parseToObject(result, Map.class);
        String dataCode = rspMap.get("returnCode").toString();
        String msg = (String) rspMap.get("returnMsg");

        logger.info("createFangyou reback info :  dataCode=" + dataCode + ";msg=" + msg);

        if ("200".equals(dataCode)) { // 创建成功的话  记录创建时间到公司表
            company.setOpenTime(new Date());
            companyMapper.update(company);
        } else {
            resultData.setFail();
            resultData.setReturnMsg("创建房友账号：调用接口失败！错误信息：" + msg);
        }
        return resultData;
    }

    /**
     * 创建, 1、合同状态为草签状态；2、建立合同门店关系；3、更新门店签约类型，只有B合同更新为已锁定；4、 更新公司为已签约、并更新其协议书类型
     *
     * @param param
     * @return
     */

    public ResultData<Integer> create(ContractInfoDto contractInfoDto)
            throws Exception {
        //构建返回
        ResultData<Integer> resultData = new ResultData<Integer>();
        Contract contract = new Contract();
        //获取公司编号
        String companyNo = contractInfoDto.getContract().getCompanyNo();
        //赋值
        BeanUtils.copyProperties(contractInfoDto.getContract(), contract);
        Integer contractB2A = contract.getContractB2A();
        //是否乙转甲（ 20201:是 20202:否）
        if (null != contractB2A && 20201 == contractB2A) {
            // 根据storeId查询合同变更 没有变更记录不让草签
            List<StoreDto> stores = contractInfoDto.getStoreDetails();
            if (null != stores && !stores.isEmpty()) {
                // 乙转甲时 只有一个门店
                StoreDto store = contractInfoDto.getStoreDetails().get(0);
                Boolean hasChg = contractChangeStoreMapper.getChgByStoreId(store.getStoreId());
                if (!hasChg) {
                    resultData.setReturnData(0);
                    resultData.setFail();
                    resultData.setReturnMsg("必须要有乙类转甲类（签约主体）变更记录才能做乙类转甲类的合同新签！");
                    return resultData;
                }
            }
        }
        // 合同状态为待审状态
        if (contract.getContractStatus() == null) {
            contract.setContractStatus(DictionaryConstants.CONTRACT_STATUS_PENDING);
        }

        contract.setDateCreate(new Date());
        // 业绩归属人
        String expdPsonId = contract.getExpandingPersonnelId();
        // 业绩归属人所属中心
        // Integer centerId = groupMapper.getCenterByUser(expdPsonId,SystemParam.getWebConfigValue("centerConfig"));
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userCode", expdPsonId);
        param.put(Constant.SQL_UN_CONTROL, false);
        Group group = groupMapper.selectByUserCode(param);
        // 业绩归属人部门
        String pfmcAtbtDepmt = group.getOrgIdStr();
        contract.setPfmcAtbtDepmt(pfmcAtbtDepmt);
        contract.setDepositFeeState(DictionaryConstants.DEPOSITFEESTATE_INIT);
        //Add 2017/04/10 cning 合同有效标识追加 Start
        Date date = new Date();
        long nd = date.getTime();
        long ds = contract.getDateLifeStart().getTime();
        long de = contract.getDateLifeEnd().getTime();
        if (nd >= ds && ds <= de) {
            contract.setValid(DictionaryConstants.VALID_TYPE_SX);//有效标识
        } else if (nd > de) {
            contract.setValid(DictionaryConstants.VALID_TYPE_GQ);//过期标识
        } else {
            contract.setValid(DictionaryConstants.VALID_TYPE_DSX);//待生效标识
        }
        //续签状态，总保证金等于门店保证金
        if (null != contract.getOriginalContractNo()) {
            contract.setTotleDepositFee(contract.getDepositFee());
        }

        //协议书编号小写转大写
        contract.setAgreementNo(contract.getAgreementNo().toUpperCase());

        String cityNoString = null;
        if (contract.getCenterId() != null) {
            cityNoString = groupMapper.getCityNoByGroupId(contract.getCenterId());
            if (StringUtil.isNotEmpty(cityNoString)) {
                contract.setAcCityNo(cityNoString);

                //根据业绩城市编号查询其核算主体
                List<Contract> accountProjectList = contractMapper.queryAccountProject(cityNoString);
                if (accountProjectList.size() > 0 && null != accountProjectList) {
                    String accountProject = accountProjectList.get(0).getAccountProject();
                    String accountProjectNo = accountProjectList.get(0).getAccountProjectNo();
                    //合同新增时候保存其核算主体及其编号，核算主体及其编号
                    if (accountProject != "") {
                        contract.setAccountProject(accountProject);
                    }
                    if (accountProjectNo != "") {
                        contract.setAccountProjectNo(accountProjectNo);
                    }
                }
            }
            //
        }

        int count = contractMapper.create(contract);
        resultData.setReturnData(count);

        //合同签约类型
        Integer contractType = contract.getContractType();

        //门店锁定状态(0:未锁定、1:锁定)
        Integer storeStatus = 0;

        //只有B类合同才锁定门店
        if (DictionaryConstants.CONTRACT_TPYE_B == contractType || DictionaryConstants.COOP_TYPE_A_2_B == contractType) {
            storeStatus = 1;
        }

        // 联系人信息
        List<ContactsDto> contactsDtoList = contractInfoDto.getContactsDtoList();
        if (null != contactsDtoList && !contactsDtoList.isEmpty()) {
            for (ContactsDto contactsDto : contactsDtoList) {
                Store store = storeMapper.getById(contactsDto.getStoreId());
                String storeManager = store.getStoreManager();
                String storeManagerPhone = store.getStoreManagerPhone();
                if (!contactsDto.getName().equals(storeManager) || !contactsDto.getMobilePhone().equals(storeManagerPhone)) {
                    Contacts contacts = new Contacts();
                    BeanUtils.copyProperties(contactsDto, contacts);
                    contactsMapper.create(contacts);

                    //更新门店负责人、负责人电话
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("storeId", contactsDto.getStoreId());
                    map.put("storeManager", contactsDto.getName());
                    map.put("storeManagerPhone", contactsDto.getMobilePhone());
                    storeMapper.updateStoreManager(map);
                }
            }
        }

        // 建立门店合同关系
        List<StoreDto> storeList = contractInfoDto.getStoreDetails();
        if (null != storeList && !storeList.isEmpty()) {
            for (StoreDto storeDto : storeList) {
                Store dbStore = storeMapper.getById(storeDto.getStoreId());
                // 建立门店合同关系
                ContractStore contractStore = new ContractStore();
                contractStore.setContractId(contract.getId());
                contractStore.setStoreId(storeDto.getStoreId());
                contractStore.setIsDelete(false);
                // 合同下的门店状态 0:正常 , 1:变更中, 2:终止
                contractStore.setStoreState(0);
                contractStore.setAddressDetail(dbStore.getAddressDetail());
                //Add By GuoPengFei 2017/05/25 合规性 start
                contractStore.setABTypeStore(storeDto.getABTypeStore());
                contractStore.setBTypeStore(storeDto.getBTypeStore());
//Add By GuoPengFei 2017/05/25 合规性 end                

                contractStoreMapper.create(contractStore);

                //门店资质等级跟合同保持一致
                dbStore.setABTypeStore(storeDto.getABTypeStore());
                dbStore.setBTypeStore(storeDto.getBTypeStore());

                dbStore.setStoreStatus(storeStatus);
                dbStore.setContractType(contractType);

                //设置门店的房友账号并插入日志
                FangyouAccountLog fangyouAccountLog = new FangyouAccountLog();
                fangyouAccountLog.setStoreId(dbStore.getStoreId());
                fangyouAccountLog.setStoreNo(dbStore.getStoreNo());
                fangyouAccountLog.setDateCreate(new Date());
                String oldFyAcount = dbStore.getFyAccount();
                if (null == oldFyAcount || "".equals(oldFyAcount)) {
                    String description = "门店关联房友账号" + companyNo;
                    fangyouAccountLog.setUserIdCreate(null);
                    fangyouAccountLog.setUserCode(null);
                    fangyouAccountLog.setUserName("Admin");
                    fangyouAccountLog.setDescription(description);
                    fangyouAccountLog.setNewFyAccount(companyNo);
                    //fangyouAccountLog.setOldFyAccount(oldFyAcount);
                    fangyouAccountMapper.insertfangyouAccountLog(fangyouAccountLog);
                    dbStore.setFyAccount(companyNo);
                } else {
                    //原来已有账号和公司编号相同不做处理
                    if (!oldFyAcount.equals(companyNo)) {
                        String description = "门店关联房友账号" + oldFyAcount + "-->" + companyNo;
                        fangyouAccountLog.setUserIdCreate(contract.getUserCreate());
                        fangyouAccountLog.setUserCode(null);
                        fangyouAccountLog.setUserName("Admin");
                        fangyouAccountLog.setDescription(description);
                        fangyouAccountLog.setNewFyAccount(companyNo);
                        fangyouAccountLog.setOldFyAccount(oldFyAcount);
                        fangyouAccountMapper.insertfangyouAccountLog(fangyouAccountLog);
                        dbStore.setFyAccount(companyNo);
                    }
                }
                storeMapper.update(dbStore);

                //创建或更新门店保证金
                StoreDepositDto deposit = new StoreDepositDto();
                deposit.setStoreNo(dbStore.getStoreNo());
                deposit.setTmpAmount(contract.getDepositFee());
                deposit.setUserIdCreate(contract.getUserCreate());
                storeDepositService.createOrUpdate(deposit);
            }
        }

        // 更新公司为已签约、并更新其协议书类型
        Company dbCompany = companyMapper.getById(contractInfoDto.getContract().getCompanyId());
        dbCompany.setContractType(contractType);
        dbCompany.setCompanyStatus(DictionaryConstants.DIC_CODE_COMPANY_STATUS_Y);
        companyMapper.update(dbCompany);

        //Add By NingChao 2017/04/07 Start
        if (null != contract.getOriginalContractNo()) {
            if (contractInfoDto.getOldFileRecordMain() != null && contractInfoDto.getOldFileRecordMain().size() > 0) {
                for (int i = 0; i < contractInfoDto.getOldFileRecordMain().size(); i++) {
                    FileRecordMain fileRecordMain = new FileRecordMain();
                    fileRecordMain.setFileRecordMainId(Integer.valueOf(contractInfoDto.getOldFileRecordMain().get(i).getFileRecordMainId()));
                    fileRecordMain.setRefId(null);
                    fileRecordMain.setIsDelete(true);
                    fileRecordMainMapper.update(fileRecordMain);
                }
            }
        }
        //Add By NingChao 2017/04/07 End

        // 关联相关文件(RefId)
        if (contractInfoDto.getFileRecordMain() != null && contractInfoDto.getFileRecordMain().size() > 0) {
            for (int i = 0; i < contractInfoDto.getFileRecordMain().size(); i++) {
                if (StringUtil.isNotEmpty(contractInfoDto.getFileRecordMain().get(i).getFileRecordMainId())) {
                    FileRecordMain fileRecordMain = new FileRecordMain();
                    fileRecordMain.setFileRecordMainId(Integer.valueOf(contractInfoDto.getFileRecordMain().get(i).getFileRecordMainId()));
                    fileRecordMain.setRefId(contract.getId());
                    fileRecordMain.setIsDelete(false);
                    fileRecordMainMapper.update(fileRecordMain);
                }
            }
        }

        //乙转甲时复制图片到合同
        if (contractInfoDto.getContract().getContractChangeId() != null && contractInfoDto.getContractCreateType() == null) {
            Map<String, Integer> map = new HashMap<>();
            map.put("contractChangeId", contractInfoDto.getContract().getContractChangeId());
            map.put("contractId", contract.getId());
            fileRecordMainMapper.createFromB2A(map);
        }

        //三方变更：合同照片取三方变更协议、门店照片取原合同照片
        if (contractInfoDto.getContractCreateType() != null && "3".equals(contractInfoDto.getContractCreateType())) {
            Map<String, Integer> map = new HashMap<>();
            map.put("contractChangeId", contractInfoDto.getContract().getContractChangeId());
            map.put("contractId", contract.getId());
            map.put("oldContractId", contractInfoDto.getOldContractId());
            fileRecordMainMapper.createFromThreePart(map);
        }

        try {
            Boolean isSaved = achievementService.saveContractAchievementInfo(DictionaryConstants.ACHIEVETYPE_STORE, contract.getExpandingPersonnelId(), contract.getExpandingPersonnel(),
                    contract.getId(), contract.getCenterId(), contract.getAccountProject(), contract.getAccountProjectNo());
            if (!isSaved) {
                logger.error("achievement", "achievementService", "saveAchievementInfo", null, null, null,
                        "合同新增-保存业绩归属信息失败！contractId：" + contract.getId(), null);
            }
        } catch (Exception e) {
            logger.error("achievement", "achievementService", "saveAchievementInfo", null, null, null,
                    "合同新增-保存业绩归属信息失败！contractId：" + contract.getId(), e);
        }

        // CRM草签合同向OP发起待开通房友账号接口
        try {
            Company com = companyMapper.getById(contractInfoDto.getContract().getCompanyId());
            if (null != com) {

                try {
                	String opDubboOpen = SystemParam.getWebConfigValue("opDubboOpen");
                	if("1".equals(opDubboOpen)) {
                		//opCompanyService.noticeOPCompany(com.getCompanyNo());
                	}else {
                		oPCompanyHttpService.noticeOPCompany(companyNo);
                	}
                } catch (Exception e) {
                    logger.error("Contract", "ContractService", "create", null, null, null, "17版CRM草签合同向OP发起待开通房友账号接口失败！CompanyId：" + contractInfoDto.getContract().getCompanyId(), e);
                }

                if ("1".equals(SystemParam.getWebConfigValue("opUrl18Flag"))) {
                    //18版OP
                    String url18 = SystemParam.getWebConfigValue("opUrl18") + "accounts/apply/{companyNo}";
                    String jsonData = "{\"companyNo\":" + com.getCompanyNo() + "}";
                    // logger.info("CRM草签合同向OP发起待开通房友账号接口: #####请求#url="+url18+"##data="+ jsonData);
                    String opResult = HttpClientUtil.jsonGet(url18, com.getCompanyNo());
                    logger.error("Contract", "ContractService", "create", null, null, null, "CRM草签合同向18 OP发起待开通房友账号接口成功！Company：" + com.getCompanyNo() + "  opResult: " + opResult, null);

//                   //op返回值
//                   Map<String,Object> opMap = (Map<String,Object>) JsonUtil.parseToObject(opResult,  Map.class);
//                   logger.info("CRM草签合同向OP18发起待开通房友账号接口返回码："+opMap.get("returnCode").toString()+",返回信息："+opMap.get("returnMsg").toString());
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("Contract", "ContractService", "create", null, null, null, "CRM草签合同向OP发起待开通房友账号接口失败！CompanyId：" + contractInfoDto.getContract().getCompanyId(), e);
        }

        Integer originalContractdistinction = contractInfoDto.getContract().getOriginalContractdistinction();
        String originalContractNo = contractInfoDto.getContract().getOriginalContractNo();


        if (originalContractdistinction != null && originalContractdistinction == 18602) {//续签
            Date dateLifeStart = contractInfoDto.getContract().getDateLifeStart();
            Date currDate = new Date();
            if (!dateLifeStart.after(currDate)) {
                Contract contractOriginal = contractMapper.getContractByNo(originalContractNo);
                if (contractOriginal != null && contractOriginal.getId() != null) {
                    Map<String, Object> reqMap = new HashMap<String, Object>();
                    reqMap.put("contractId", contractOriginal.getId());
                    contractStoreMapper.updateFlag(reqMap);
                }
            }
        } else {
            if (null != storeList && !storeList.isEmpty()) {
                for (StoreDto storeDto : storeList) {
                    Map<String, Object> reqMap = new HashMap<>();
                    reqMap.put("storeId", storeDto.getStoreId());
                    reqMap.put("contractId", contract.getId());
                    reqMap.put("companyId", contractInfoDto.getContract().getCompanyId());
                    Integer contractIdRef = contractStoreMapper.getContractStoreContractId(reqMap);
                    if (contractIdRef != null && contractIdRef > 0) {
                        ContractStore contractStore = new ContractStore();
                        contractStore.setContractId(contractIdRef);
                        contractStore.setStoreId(storeDto.getStoreId());
                        contractStore.setFlag(1);
                        contractStoreMapper.update(contractStore);
                    }
                }
            }
        }

        return resultData;

    }

    /**
     * 开通房友账户
     *
     * @param company 公司信息
     */
    private String createFangyou(Company company, int storeId)
            throws Exception {
        //房友公司信息
        FYCompany fyCompany = new FYCompany();

        CompanyDto companyDto = ConvertTo.CompanyDto(company);

        fyCompany.setCompanyId(company.getFangyouCompanyId());
        fyCompany.setCompanyName(companyDto.getCompanyName());
        fyCompany.setCompanyNo(companyDto.getCompanyNo());

        fyCompany.setCityNo(companyDto.getCityNo());
        fyCompany.setCityName(companyDto.getCityName());
        fyCompany.setDistrictNo(companyDto.getDistrictNo());
        fyCompany.setDistrictName(companyDto.getDistrictName());
        fyCompany.setAreaNo(companyDto.getAreaNo());
        fyCompany.setAreaName(companyDto.getAreaName());
        fyCompany.setAddress(companyDto.getAddress());

        ResultData<StoreDto> storeDtoResultData = storeService.getStrById(storeId, null);

        //获取公司下边任意一家门店的经纬度（其实要的是公司的，因无法获取，只能取其门店的）
        fyCompany.setX(storeDtoResultData.getReturnData().getLatitude() + "");
        fyCompany.setY(storeDtoResultData.getReturnData().getLongitude() + "");

        return fangyouService.createFangyou(fyCompany);
    }

    /**
     * 更新：    1、解除合同门店的关系并变更其签约类型、
     */

    public ResultData<Integer> update(ContractInfoDto contractInfoDto)
            throws Exception {
        //构建返回
        ResultData<Integer> resultData = new ResultData<Integer>();
        Contract contract = new Contract();

        //赋值
        BeanUtils.copyProperties(contractInfoDto.getContract(), contract);

        //查询数据库中的合同信息
        Contract dbContract = contractMapper.getById(contractInfoDto.getContract().getId());

        //版本号是否一致的check
        Integer dbContractVersion = dbContract.getContractVersion();
        Integer contractVersion = contract.getContractVersion();
        if (dbContractVersion.equals(contractVersion)) {
            contractVersion++;
            contract.setContractVersion(contractVersion);
        } else {
            String strMsg = "该合同信息微信端已修改，请返回刷新后重新进行编辑！";
            resultData.setReturnData(0);
            resultData.setFail();
            resultData.setReturnMsg(strMsg);
            return resultData;
        }

        //协议书编号重复check
        ResultData<List<Contract>> resultDataList = this.getContractByAgreementNo(contract.getAgreementNo());
        List<Contract> list = resultDataList.getReturnData();
        if (list.size() > 0) {
            String strContractNo = "";
            int flag = 0;
            for (Contract info : list) {
                if (info.getContractNo().equals(contract.getContractNo())) {
                    continue;
                }
                //审核中或审核通过
                if (10402 == info.getContractStatus() || 10403 == info.getContractStatus()) {
                    strContractNo += info.getContractNo() + ",";
                    flag = 1;
                }
                //草签或审核未通过
                if (10401 == info.getContractStatus() || 10404 == info.getContractStatus()) {
                    strContractNo += info.getContractNo() + ",";
                    flag = 2;
                }
            }

            if (strContractNo.length() > 0) {
                strContractNo = strContractNo.substring(0, strContractNo.length() - 1);
            }

            if (flag == 1) {
                String strMsg = "系统中存在相同协议书编号的合同，合同编号为" + strContractNo + "。请勿重复提交！";
                resultData.setReturnData(0);
                resultData.setFail();
                resultData.setReturnMsg(strMsg);
                return resultData;
            } else if (flag == 2) {
                String strMsg = "系统中存在相同协议书编号的草签合同，合同编号为" + strContractNo + "。如有必要请作废后再操作，请勿重复提交！";
                resultData.setReturnData(0);
                resultData.setFail();
                resultData.setReturnMsg(strMsg);
                return resultData;
            }
        }

        Integer contractB2A = contract.getContractB2A();
        //是否乙转甲（ 20201:是 20202:否）
        if (null != contractB2A && 20201 == contractB2A) {
            // 根据storeId查询合同变更 没有变更记录不让草签
            List<StoreDto> stores = contractInfoDto.getStoreDetails();
            if (null != stores && !stores.isEmpty()) {
                // 乙转甲时 只有一个门店
                StoreDto store = contractInfoDto.getStoreDetails().get(0);
                Boolean hasChg = contractChangeStoreMapper.getChgByStoreId(store.getStoreId());
                if (!hasChg) {
                    resultData.setReturnData(0);
                    resultData.setFail();
                    resultData.setReturnMsg("必须要有乙类转甲类（签约主体）变更记录才能做乙类转甲类的合同新签！");
                    return resultData;
                }
            }
        }

        // 业绩归属人
        String expdPsonId = contract.getExpandingPersonnelId();
        if (null != expdPsonId) {
            // 业绩归属人所属中心
            //Integer centerId = groupMapper.getCenterByUser(expdPsonId,SystemParam.getWebConfigValue("centerConfig"));
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("userCode", expdPsonId);
            param.put(Constant.SQL_UN_CONTROL, false);
            Group group = groupMapper.selectByUserCode(param);
            // 业绩归属人部门
            String pfmcAtbtDepmt = group.getOrgIdStr();
            contract.setPfmcAtbtDepmt(pfmcAtbtDepmt);
	        /*if(null !=centerId){
	        	contract.setCenterId(centerId);
	        }*/
        }
        // 更新合同信息
        BeanUtils.copyProperties(contract, dbContract);

        //协议书编号小写转大写
        contract.setAgreementNo(contract.getAgreementNo().toUpperCase());

        int count = contractMapper.update(contract);
        resultData.setReturnData(count);
        // 更新门店 、门店合同
        List<ContractStore> contractStoreList = contractStoreMapper.selStoreByContractId(contract.getId());
        if (null != contractStoreList && !contractStoreList.isEmpty()) {

            for (int i = 0; i < contractStoreList.size(); i++) {
                //更新掉原有门店的状态及协议书类型
                Store dbStore = storeMapper.getById(contractStoreList.get(i).getStoreId());
                dbStore.setStoreStatus(0);
                dbStore.setContractType(0);
                storeMapper.update(dbStore);

            }
        }

        //解除合同门店的关系
        contractStoreMapper.deleteByContractId(contract.getId());

        //合同签约类型
        Integer contractType = contract.getContractType();

        //门店锁定状态(0:未锁定、1:锁定)
        Integer storeStatus = 0;

        //只有B类合同才锁定门店
        if (DictionaryConstants.CONTRACT_TPYE_B == contractType || DictionaryConstants.COOP_TYPE_A_2_B == contractType) {
            storeStatus = 1;
        }

        // 建立门店合同关系
        List<StoreDto> storeList = contractInfoDto.getStoreDetails();

        if (null != storeList && !storeList.isEmpty()) {
            for (StoreDto storeDto : storeList) {
                // 建立门店合同关系
                Store dbStore = storeMapper.getById(storeDto.getStoreId());
                ContractStore contractStore = new ContractStore();
                contractStore.setContractId(contract.getId());
                contractStore.setStoreId(storeDto.getStoreId());
                contractStore.setIsDelete(false);
                contractStore.setStoreState(0);
                contractStore.setAddressDetail(dbStore.getAddressDetail());
                contractStore.setABTypeStore(storeDto.getABTypeStore());
                contractStore.setBTypeStore(storeDto.getBTypeStore());
                contractStoreMapper.create(contractStore);

                //创建或更新门店保证金
                StoreDepositDto deposit = new StoreDepositDto();
                deposit.setStoreNo(dbStore.getStoreNo());
                deposit.setTmpAmount(contract.getDepositFee());
                deposit.setUserIdCreate(contract.getUserCreate());
                storeDepositService.createOrUpdate(deposit);

                //更新门店签约类型，只有B合同更新为已锁定
                dbStore.setStoreStatus(storeStatus);
                dbStore.setContractType(contractType);
                storeMapper.update(dbStore);
//                Store store = new Store();
//                store.setStoreId(storeDto.getStoreId());
//                store.setStoreStatus(storeStatus);
//                store.setContractType(contractType);
//                storeMapper.update(store);

            }
        }

        // 关联相关文件(RefId)
        List<ContractFileDto> contractFiles = contractInfoDto.getOldFileRecordMain();
        if (null != contractFiles && !contractFiles.isEmpty()) {
            for (int i = 0; i < contractFiles.size(); i++) {
                String fileId = contractFiles.get(i).getFileRecordMainId();
                if (!"null".equals(fileId)) {
                    FileRecordMain fileRecordMain = new FileRecordMain();
                    fileRecordMain.setFileRecordMainId(Integer.valueOf(fileId));
                    fileRecordMain.setRefId(null);
                    fileRecordMain.setIsDelete(true);
                    fileRecordMainMapper.update(fileRecordMain);
                }
            }
        }
        List<ContractFileDto> newcontractFiles = contractInfoDto.getFileRecordMain();
        if (null != newcontractFiles && !newcontractFiles.isEmpty()) {
            for (int i = 0; i < newcontractFiles.size(); i++) {
                //2017/06/29 cning Mod Start
                //String fileId = contractFiles.get(i).getFileRecordMainId();
                String fileId = newcontractFiles.get(i).getFileRecordMainId();
                //2017/06/29 cning Mod End
                if (!"null".equals(fileId) && StringUtil.isNotEmpty(fileId)) {
                    FileRecordMain fileRecordMain = new FileRecordMain();
                    fileRecordMain.setFileRecordMainId(Integer.valueOf(fileId));
                    fileRecordMain.setRefId(contract.getId());
                    fileRecordMain.setIsDelete(false);
                    fileRecordMainMapper.update(fileRecordMain);
                }
            }
        }
        return resultData;
    }

    /**
     * 更新：    1、修改合同信息时，修改未签，审核未通过的合同的公司信息
     *
     * @param param
     * @return
     */

    public ResultData<Integer> updateByContractId(ContractInfoDto contractInfoDto)
            throws Exception {
        //构建返回
        ResultData<Integer> resultData = new ResultData<Integer>();
        Contract contract = new Contract();

        //赋值
        BeanUtils.copyProperties(contractInfoDto.getContract(), contract);

        int count = contractMapper.update(contract);
        resultData.setReturnData(count);

        return resultData;
    }

    /**
     * 合同作废，门店状态设置为未锁定、签约类型显示为空
     *
     * @param id
     * @param updateId 更新人
     * @return
     */

    public void invalid(int id, int updateId)
            throws Exception {
        //        Contract contractMdl = contractMapper.getById(id);

        // 解除合同锁定客户
        //        Company company = new Company();
        //        company.setId(contractMdl.getCompanyId());
        //        company.setCompanyStatus(DictionaryConstants.DIC_CODE_COMPANY_STATUS_N);
        //        int count = companyMapper.update(company);

        //  合同作废，门店状态设置为未锁定、签约类型显示为空
        List<ContractStore> contractStoreList = contractStoreMapper.selStoreByContractId(id);
        for (int i = 0; i < contractStoreList.size(); i++) {
            Store store = new Store();
            store.setStoreId(contractStoreList.get(i).getStoreId());
            //TODO如果签了A，后来签了B，把A作废
            store.setStoreStatus(0);
            store.setContractType(0);
            storeMapper.update(store);
        }

        // 解除相关表
        //contractStoreMapper.delStoreByContractId(id);
        //contractProductMapper.delProductByContractId(id);

        // 删除文件
        //        fileRecordMainMapper.deleteByContractIdStatus(id);
        //        fileRecordMainMapper.updateStatusByContractId(id);

        //        return count;
    }

    /**
     * 审核
     *
     * @param param
     * @return
     */

    public int audit(ContractInfoDto contractInfoDto)
            throws Exception {
        Contract contract = new Contract();

        //赋值
        BeanUtils.copyProperties(contractInfoDto.getContract(), contract);

        // 合同状态为审核中
        contract.setContractStatus(DictionaryConstants.CONTRACT_STATUS_AUDITING);

        // 获取合同信息
        Contract contractMdl = contractMapper.getById(contract.getId());

        //合同签约类型
        Integer contractType = contractMdl.getContractType();

        //门店锁定状态(0:未锁定、1:锁定)
        Integer storeStatus = 0;

        //只有B类合同才锁定门店
        if (DictionaryConstants.CONTRACT_TPYE_B.equals(contractType) || DictionaryConstants.COOP_TYPE_A_2_B == contractType) {
            storeStatus = 1;
        }

        // 建立门店合同关系
        List<StoreDto> storeList = contractInfoDto.getStoreDetails();

        if (null != storeList && !storeList.isEmpty()) {
            for (StoreDto storeDto : storeList) {
                //更新门店签约类型，只有B合同更新为已锁定
                Store store = new Store();
                store.setStoreId(storeDto.getStoreId());
                store.setStoreStatus(storeStatus);
                store.setContractType(contractType);
                storeMapper.update(store);
            }
        }

        // 锁定客户(公司更新为已签约)
        Company company = new Company();
        company.setId(contractInfoDto.getContract().getCompanyId());
        company.setContractType(contractMdl.getContractType());
        company.setCompanyStatus(DictionaryConstants.DIC_CODE_COMPANY_STATUS_Y);
        companyMapper.update(company);
        if (DictionaryConstants.CONTRACT_TYPE_D.equals(contractType)) {
            //D类合同  状态为审核通过
            contract.setContractStatus(DictionaryConstants.CONTRACT_STATUS_AUDIT_PASS);
        }
        // 更新合同状态
        int count = contractMapper.update(contract);
        contractStoreMapper.updateByContractId(contract.getId());
        return count;
    }

    /**
     * 根据storeId和companyId获取合同信息
     *
     * @param queryParam
     * @return
     */

    public ResultData<Contract> queryContractTypeByStoreCompanyId(Map<?, ?> param)
            throws Exception {
        //构建返回
        ResultData<Contract> resultData = new ResultData<Contract>();
        //查询
        List<Contract> contractList = contractMapper.queryContractTypeByStoreCompanyId(param);
        if (contractList != null && contractList.size() > 0) {
            resultData.setReturnData(contractList.get(0));
        }
        return resultData;
    }

    /**
     * 对象转换MO--DTO
     *
     * @param craList
     * @return List<ContractSearchResultDto>
     */
    private List<ContractSearchResultDto> convertContractSearchResultData(List<ContractSearchResult> craList)
            throws Exception {
        List<ContractSearchResultDto> contractDtoList = new ArrayList<ContractSearchResultDto>();

        if (null != craList && !craList.isEmpty()) {
            ContractSearchResultDto craDto = null;
            for (ContractSearchResult cra : craList) {
                craDto = new ContractSearchResultDto();
                BeanUtils.copyProperties(cra, craDto);
                Integer storeState = cra.getStoreState();
                if (null != storeState) {
                    if (cra.getStoreState().intValue() == 0) {
                        craDto.setStoreStateName("正常");
                    } else if (cra.getStoreState().intValue() == 1) {
                        craDto.setStoreStateName("变更中");
                    } else {
                        craDto.setStoreStateName("终止");
                    }
                }
                Boolean isChanged = cra.getIsChanged();
                if (isChanged != null && isChanged) {
                    craDto.setIsChangedStr("是");
                } else {
                    craDto.setIsChangedStr("否");
                }
                //保证金撤销
                String refundState = cra.getRefundState();
                if (StringUtils.isNotBlank(refundState))
                    craDto.setRefundStateName(SystemParam.getDicValueByDicCode(refundState));
                //Add By tong 2017/04/07 Start
                if (null != cra.getValid()) {
                    String valid = cra.getValid().toString();
                    craDto.setValidName(SystemParam.getDicValueByDicCode(valid));
                }

                Code code = commonCodeMapper.queryByDicCode(cra.getContractType().toString());
                if (null != code) {
                    craDto.setContractTypeName(code.getDicValue());
                }
                //Add By tong 2017/04/07 End
                contractDtoList.add(craDto);
            }
        }
        return contractDtoList;
    }

    /**
     * OA审核后　变更合同状态
     *
     * @param param
     * @return
     */
    public ResultData<String> changeStatus(Map<?, ?> reqMap)
            throws Exception {

        //构建返回
        ResultData<String> resultData = new ResultData<String>();

        int auditStatus = (Integer) reqMap.get("status");
        int id = (Integer) reqMap.get("id");

        Contract contract = new Contract();
        contract.setId(id);

        //审核通过
        if (DictionaryConstants.CONTRACT_STATUS_AUDIT_PASS == auditStatus) {
            //变更本地合同状态为审核通过
            contract.setContractStatus(DictionaryConstants.CONTRACT_STATUS_AUDIT_PASS);
            //审核未通过
        } else if (DictionaryConstants.CONTRACT_STATUS_AUDIT_NO_PASS == auditStatus) {
            // 变更本地合同状态为审核未通过
            contract.setContractStatus(DictionaryConstants.CONTRACT_STATUS_AUDIT_NO_PASS);
            //其它状态
        } else {

        }
        // 更新合同状态
        contractMapper.update(contract);
        return resultData;
    }

    /**
     * 更新合同状态
     *
     * @param Contract contract)
     * @return
     */

    public int updateById(Contract contract)
            throws Exception {
        // 更新合同状态
        int count = contractMapper.update(contract);
        // 合同作废后 解除门店合同关系
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("contractId", contract.getId());
        contractStoreMapper.updateFlag(param);
        return count;
    }

    /**
     * 更新FlowId OA专用
     *
     * @param param
     * @return
     */

    public int updateFlowIdById(ContractInfoDto contractInfoDto)
            throws Exception {
        Contract contract = new Contract();
        //赋值
        BeanUtils.copyProperties(contractInfoDto.getContract(), contract);
        // 更新合同FlowId
        int count = contractMapper.updateFlowIdById(contract);
        return count;
    }

    /**
     * 更新合同状态 OA专用
     *
     * @param param
     * @return
     */
    public int updateContractStatusByFlowId(ContractInfoDto contractInfoDto)
            throws Exception {
        Contract contract = new Contract();
        //赋值
        BeanUtils.copyProperties(contractInfoDto.getContract(), contract);
        // 更新合同状态
        int count = contractMapper.updateContractStatusByFlowId(contract);

        //公司业务类型
        if (contract.getBizType() != null) {
            Integer companyId = contract.getCompanyId();
            if (companyId == null) {
                Contract dbContract = contractMapper.getByFlowId(contract.getFlowId());
                companyId = dbContract.getCompanyId();
            }
            Company company = new Company();
            company.setId(companyId);
            company.setBizType(contract.getBizType());
            companyMapper.update(company);
        }

        // -------------------- 审核通过的场合、向OMS插入审批流程记录 start --------------------//
        // 获取审核结果(0:审核通过、5:撤销、15:终止 )
        //Mod By GUOPENGFEI 2017/04/24 Start
        //if (contractInfoDto.getAuditRst() == 0)
        if (contractInfoDto.getContract().getContractStatus().equals(DictionaryConstants.CONTRACT_STATUS_AUDIT_PASS))//通过审核
//Mod By GUOPENGFEI 2017/04/24 End        	
        {
            //原合同的保证金转出，新合同的保证金转入
            Map<String, Object> record = new HashMap<>();
            record.put("flowId", contract.getFlowId());
            storeDepositService.transferDeposit(record);

            // 新增合同操作流程信息部分
            //this.createContractFlow(contract.getFlowId());
        }
        // -------------------- 审核通过的场合、向OMS插入审批流程记录 end --------------------//

        return count;
    }

    /**
     * 定时批量更新状态
     *
     * @param reqMap
     * @return
     */
    public void updateStatusByParam(Map<?, ?> reqMap)
            throws Exception {
        // 更新合同状态
        contractMapper.updateParam(reqMap);

        // -------------------- 审核通过的场合、向OMS插入审批流程记录 start --------------------//
        // 4.遍历flowId、根据flowId查询合同信息并插入合同操作流程信息
        String[] flowIdArr = (String[]) reqMap.get("flowIdList");
        for (int i = 0; i < flowIdArr.length; i++) {
            // 新增合同操作流程信息
            this.createContractFlow(flowIdArr[i]);
        }
        // -------------------- 审核通过的场合、向OMS插入审批流程记录 end --------------------//

    }

    public Contract getByFlowId(String flowId)
            throws Exception {
        Contract contract = contractMapper.getByFlowId(flowId);
        return contract;
    }

    public Integer getContractsByCompanyId(Integer companyId)
            throws Exception {
        return contractMapper.getContractsByCompanyId(companyId);
    }

    /**
     * 根据公司名称查询该公司是否存在意向合同
     *
     * @param companyName 公司名称
     * @return
     */
    public Contract getContractInfo(String companyName)
            throws Exception {
        return contractMapper.getContractInfo(companyName);
    }

    /**
     * 根据门店Id查询签约的合同
     *
     * @param storeId 门店Id
     * @return
     */
    public ResultData<List<ContractSearchResultDto>> getSignHisByStoreId(Map<?, ?> param)
            throws Exception {
        //构建返回
        ResultData<List<ContractSearchResultDto>> resultData = new ResultData<List<ContractSearchResultDto>>();
        //查询
        final List<ContractSearchResult> craList = contractMapper.getSignHisByStoreId(param);
        //转换
        List<ContractSearchResultDto> contractDtoList = convertContractSearchResultData(craList);
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(contractDtoList);
        return resultData;
    }

    /**
     * 定时拉取OA审核状态，审核成功，创建房友账号
     *
     * @param flowIdList
     * @return
     * @throws Exception
     */
    public ResultData<String> createFY(List<String> flowIdList)
            throws Exception {

        //构建返回
        ResultData<String> resultData = new ResultData<String>();

        Map<String, Object> reqMap;

        Contract contract;

        for (String flowId : flowIdList) {
            reqMap = new HashMap<String, Object>();

            //根据flowId，拿到companyId、contractId
            contract = contractMapper.getByFlowId(flowId);
            reqMap.put("companyId", contract.getCompanyId().toString());
            reqMap.put("contractId", contract.getId().toString());

            //TODO 查询是否开通后再开账号  ，此处是需判重的，注掉的代码有错误，待处理
            boolean isHas = false;
            //            Integer companyId = contract.getCompanyId();
            //            if (null != companyId) {
            //                String fyInfoStr = fangyouService.getEmployee(companyId);
            //                ResultData<List<FYEmp>> resData = JsonUtil.parseToObject(fyInfoStr, ResultData.class);
            //                if (null != resData.getReturnData()) {
            //                    isHas = true;
            //                }
            //            }
            if (!isHas) {
                //创建房友账号
                createFangyou(reqMap);
            } else {
                resultData.setReturnMsg("房友账号已存在！");
            }
        }

        return resultData;
    }

    /**
     * 根据公司Id查询审核通过的合同
     *
     * @param companyId 公司Id
     * @return
     */
    public ResultData<ContractDto> getAuditpassByCompanyId(Integer companyId)
            throws Exception {
        ResultData<ContractDto> resultData = new ResultData<ContractDto>();
        ContractDto contactDto = new ContractDto();
        List<Contract> contractList = contractMapper.getAuditpassByCompanyId(companyId);
        if (null != contractList && !contractList.isEmpty()) {
            Contract contract = contractList.get(0);
            BeanUtils.copyProperties(contract, contactDto);
            resultData.setReturnData(contactDto);
        }
        return resultData;
    }

    /**
     * 查询当前User及其下属User的合同列表信息（提供给CRM微信端）
     *
     * @param param
     * @return
     */
    public ResultData<List<ContractSearchResultDto>> queryContractList(Map<?, ?> param)
            throws Exception {
        // 构建返回
        ResultData<List<ContractSearchResultDto>> resultData = new ResultData<List<ContractSearchResultDto>>();

        // 查询
        final List<ContractSearchResult> craList = contractMapper.getByUserId(param);

        List<ContractSearchResultDto> contractDtoList = new ArrayList<ContractSearchResultDto>();

        // 转换
        if (null != craList && !craList.isEmpty()) {
            ContractSearchResultDto craDto = null;
            for (ContractSearchResult cra : craList) {
                craDto = new ContractSearchResultDto();
                // 审核状态 (10401: 草签，10402: 审核中，10403: 审核通过，10404: 审核未通过，10405: 作废)
                craDto.setContractStatusName(SystemParam.getDicValueByDicCode(String.valueOf(cra.getContractStatus())));
                BeanUtils.copyProperties(cra, craDto);
                contractDtoList.add(craDto);
            }
        }

        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(contractDtoList);
        return resultData;
    }

    //更新合同门店关系 表
    public Integer updateContractStore(ContractStore contractStore)
            throws Exception {
        Integer num = contractStoreMapper.update(contractStore);
        return num;
    }

    // 根据flowID查询合同中的门店
    public ResultData<List<ContractStore>> getContractStoresByFlowId(String flowId)
            throws Exception {
        ResultData<List<ContractStore>> resultData = new ResultData<List<ContractStore>>();
        List<ContractStore> contractStoreList = contractStoreMapper.getContractStoresByFlowId(flowId);
        resultData.setReturnData(contractStoreList);
        return resultData;
    }

    // 根据合同flowID查询合同中的门店
    public ResultData<List<ContractStore>> getContractStoresByContractFlowId(String flowId)
            throws Exception {
        ResultData<List<ContractStore>> resultData = new ResultData<List<ContractStore>>();
        List<ContractStore> contractStoreList = contractStoreMapper.getContractStoresByContractFlowId(flowId);
        resultData.setReturnData(contractStoreList);
        return resultData;
    }

    /**
     * 根据变更记录表的flowId 更新 合同状态
     */
    public Integer updateCtrctStateByChgFlowId(ContractChange contractChange)
            throws Exception {
        Integer num = contractMapper.updateCtrctStateByChgFlowId(contractChange);
        return num;
    }

    /**
     * 根据合同变更表的FlowId 更新 合同表的 合同状态
     *
     * @param contractStopId 合同变更ID
     * @return
     * @throws Exception
     */
    public int updateCtrctStatusByChgFlowId(ContractChange contractChange)
            throws Exception {
        // 更新合同状态
        int count = contractMapper.updateCtrctStatusByChgFlowId(contractChange);
        return count;
    }

    /**
     * 根据合同ID查询合同信息
     *
     * @param contractId 合同ID
     * @return
     * @throws Exception
     */
    public ResultData<ContractDto> getContractById(int contractId)
            throws Exception {

        // 构建返回
        ResultData<ContractDto> resultData = new ResultData<ContractDto>();

        // 查询操作
        Contract contract = contractMapper.getContractById(contractId);

        ContractDto dto = new ContractDto();
        // Model转换Dto
        BeanUtils.copyProperties(contract, dto);

        if (null != dto) {
            resultData.setReturnData(dto);
        }
        return resultData;
    }

    /**
     * 根据合同ID查询分账信息
     *
     * @param contractId 合同ID
     * @return
     * @throws Exception
     */
    public ContractInfoDto getSplitInfo(int contractId)
            throws Exception {
        ContractInfoDto contractInfoDto = new ContractInfoDto();
        // 获取合同信息
        Contract contract = contractMapper.getById(contractId);
        ContractDto ctaDto = new ContractDto();
        BeanUtils.copyProperties(contract, ctaDto);
        contractInfoDto.setContract(ctaDto);

        List<StoreDto> storeMdlList = contractMapper.selectStoreByContractId(contractId);
        contractInfoDto.setStoreDetails(storeMdlList);
        return contractInfoDto;
    }

    /**
     * 设置业绩关联人员信息
     * 当前拓展人员需求只有四级
     * (即：1.拓展专员、
     * 2.拓展专员的上级是拓展经理、
     * 3.拓展经理的上级是区域总监、
     * 4.区域总监的上级是事业部总经理 )
     *
     * @param contract 合同信息
     * @return
     * @throws Exception
     */
    private ContractRelatePerson setRelatePerson(Contract contract)
            throws Exception {
        ContractRelatePerson relatePerson = new ContractRelatePerson();
        // 合同ID
        relatePerson.setContractId(contract.getId());
        // 业绩归属拓展人工号
        String expandingPersonnelId = contract.getExpandingPersonnelId();

        // 查询当前【业绩归属拓展人】的所属组和所属岗的信息
        // 1.根据userId查询用户信息
        User currentUser = userMapper.getUserByCode(expandingPersonnelId);

        relatePerson.setExpandingPersonnel(currentUser.getUserName());
        relatePerson.setExpandingPersonnelId(currentUser.getUserCode());
        // ------------------- 可能多岗位的场合 start -------------- //
        // 有可能存在多岗因此需要用List接收
        List<Post> currentPostList = null;
        // 2.查询拓展人员所属岗位信息(可能存在多岗的场合，即用List集合接收)
        currentPostList = postMapper.getExpandPostByUserId(currentUser.getUserId());
        Group currentGroup = null;
        // 岗位类型(18:拓展总监   20:拓展经理    21:拓展专员   22:事业部总经理)
        int currentPostType = 0;
        if (currentPostList != null && !currentPostList.isEmpty()) {
            // 多岗的场合
            for (Post currentPost : currentPostList) {
                String currentPostGroupId = null;
                if (currentPost.getGroupId() != null) {
                    currentPostGroupId = String.valueOf(currentPost.getGroupId());
                }
                String currentUserGgroupId = null;
                if (currentUser.getGroupId() != null) {
                    currentUserGgroupId = String.valueOf(currentUser.getGroupId());
                }

                // 当前岗对应的组
                if (currentPostGroupId.equals(currentUserGgroupId)) {
                    // 3.查询所属组信息
                    currentGroup = groupMapper.selectByPrimaryKey(currentPost.getGroupId());
                    // 设置当前业绩归属拓展人员信息
                    this.setRelatePersonInfo(currentUser, currentGroup, currentPost, relatePerson);
                    // 获取当前拓展人员的岗位类型
                    currentPostType = currentPost.getTypeId();
                }
            }
        }
        // ------------------- 可能多岗位的场合 end -------------- //

        // =============== 查询当前拓展人员的上级信息 start ================ //
        // 当前归属拓展人所属组为【拓展组】的场合
        if (currentGroup != null) {
            if (currentGroup.getTypeId() == 4) {
                // 拓展经理的场合 (20:拓展经理   21:拓展专员)
                if (currentPostType == 20) {
                    //  1. 查找【区域总监】所属组（固定在【拓展经理】的基础上往跳1级）
                    Group leadersGroup = groupMapper.selectByGroupId(currentGroup.getParentId());
                    // 非空判断
                    if (null != leadersGroup.getGroupManagerId() && StringUtil.isNotEmpty(leadersGroup.getGroupManagerId())) {
                        int userId = Integer.parseInt(leadersGroup.getGroupManagerId());
                        // 设置【区域总监】的信息
                        this.setLeaderInfo(userId, relatePerson);
                    } else {
                        // 上级领导人不存在的场合、就只存它上级所在组的信息
                        this.setPerformTeamInfo(leadersGroup, relatePerson);
                    }

                    //  2. 查找【事业部总经理】所属组（固定在【区域总监】的基础上往跳2级）
                    Group firstLeadersGroup = groupMapper.selectByGroupId(leadersGroup.getParentId());
                    Group secondLeadersGroup = groupMapper.selectByGroupId(firstLeadersGroup.getParentId());
                    // 非空判断
                    if (null != secondLeadersGroup.getGroupManagerId() && StringUtil.isNotEmpty(secondLeadersGroup.getGroupManagerId())) {
                        int userId = Integer.parseInt(secondLeadersGroup.getGroupManagerId());
                        // 设置【事业部总经理】的信息
                        this.setLeaderInfo(userId, relatePerson);
                    } else {
                        // 上级领导人不存在的场合、就只存它上级所在组的信息
                        this.setPerformTeamInfo(secondLeadersGroup, relatePerson);
                    }

                    // 拓展专员的场合
                } else {
                    // 1. 查找【拓展经理】所属组（根据【拓展专员】的GroupManagerId找到他的上级）
                    if (null != currentGroup.getGroupManagerId() && StringUtil.isNotEmpty(currentGroup.getGroupManagerId())) {
                        int userId = Integer.parseInt(currentGroup.getGroupManagerId());
                        // 设置【拓展经理】的信息
                        this.setLeaderInfo(userId, relatePerson);
                    } else {
                        // 上级领导人不存在的场合、就只存它上级所在组的信息 (该处拓展经理同专员所属同一个组)
                        this.setPerformTeamInfo(currentGroup, relatePerson);
                    }

                    //  2. 查找【区域总监】所属组（固定在【拓展经理】的基础上往上跳1级）
                    Group leadersGroup = groupMapper.selectByGroupId(currentGroup.getParentId());
                    // 非空判断
                    if (null != leadersGroup.getGroupManagerId() && StringUtil.isNotEmpty(leadersGroup.getGroupManagerId())) {
                        int userId = Integer.parseInt(leadersGroup.getGroupManagerId());
                        // 设置【区域总监】的信息
                        this.setLeaderInfo(userId, relatePerson);
                    } else {
                        // 上级领导人不存在的场合、就只存它上级所在组的信息
                        this.setPerformTeamInfo(leadersGroup, relatePerson);
                    }

                    //  3. 查找【事业部总经理】所属组（固定在【区域总监】的基础上往上跳2级）
                    Group firstLeadersGroup = groupMapper.selectByGroupId(leadersGroup.getParentId());
                    Group secondLeadersGroup = groupMapper.selectByGroupId(firstLeadersGroup.getParentId());
                    // 非空判断
                    if (null != secondLeadersGroup.getGroupManagerId() && StringUtil.isNotEmpty(secondLeadersGroup.getGroupManagerId())) {
                        int userId = Integer.parseInt(secondLeadersGroup.getGroupManagerId());
                        // 设置【事业部总经理】的信息
                        this.setLeaderInfo(userId, relatePerson);
                    } else {
                        // 上级领导人不存在的场合、就只存它上级所在组的信息
                        this.setPerformTeamInfo(secondLeadersGroup, relatePerson);
                    }
                }

                // 当前归属拓展人所属组为【拓展事业部】的场合
            } else if (currentGroup.getTypeId() == 8) {

                //  查找【事业部总经理】所属组（固定在【区域总监】的基础上往上跳2级）
                Group firstLeadersGroup = groupMapper.selectByGroupId(currentGroup.getParentId());
                Group secondLeadersGroup = groupMapper.selectByGroupId(firstLeadersGroup.getParentId());
                // 非空判断
                if (null != secondLeadersGroup.getGroupManagerId() && StringUtil.isNotEmpty(secondLeadersGroup.getGroupManagerId())) {
                    int userId = Integer.parseInt(secondLeadersGroup.getGroupManagerId());
                    // 设置【事业部总经理】的信息
                    this.setLeaderInfo(userId, relatePerson);
                } else {
                    // 上级领导人不存在的场合、就只存它上级所在组的信息
                    this.setPerformTeamInfo(secondLeadersGroup, relatePerson);
                }
            }
            // =============== 查询当前拓展人员的上级信息 end ================ //
        }
        return relatePerson;
    }

    /**
     * 设置业绩归属拓展人员（上级）信息
     *
     * @param userId
     * @param relatePerson
     */
    private void setLeaderInfo(Integer userId, ContractRelatePerson relatePerson)
            throws Exception {
        // 1.查找上级领导用户信息
        User leadershipUser = userMapper.getUserByUserId(userId);

        // ------------------- 可能多岗位的场合 start -------------- //
        // 多岗的场合
        List<Post> leadershipPostList = null;
        // 2.查询拓展人员所属岗位信息(可能存在多岗的场合，即用List集合接收)
        leadershipPostList = postMapper.getExpandPostByUserId(leadershipUser.getUserId());
        Group leadershipGroup = new Group();
        if (leadershipPostList != null) {
            // 多岗的场合
            for (Post leadershipPost : leadershipPostList) {
                String leadershipPostGroupId = null;
                if (leadershipPost.getGroupId() != null) {
                    leadershipPostGroupId = String.valueOf(leadershipPost.getGroupId());
                }
                String leadershipUserGgroupId = null;
                if (leadershipUser.getGroupId() != null) {
                    leadershipUserGgroupId = String.valueOf(leadershipUser.getGroupId());
                }

                // 当前岗对应的组
                if (leadershipPostGroupId.equals(leadershipUserGgroupId)) {
                    // 3.查询所属组信息
                    leadershipGroup = groupMapper.selectByPrimaryKey(leadershipPost.getGroupId());
                    // 设置当前业绩归属拓展人员信息
                    this.setRelatePersonInfo(leadershipUser, leadershipGroup, leadershipPost, relatePerson);
                }
            }
        }
        // ------------------- 可能多岗位的场合 end -------------- //
    }

    /**
     * 设置业绩归属拓展人员信息（包含拓展专员、拓展经理、区域总监、事业部总经理）
     *
     * @param paramUser  用户信息
     * @param paramGroup 组信息
     * @param paramPost  岗位信息
     * @return
     * @throws Exception
     */
    private void setRelatePersonInfo(User paramUser, Group paramGroup, Post paramPost, ContractRelatePerson relatePerson)
            throws Exception {
        // 所属组为【拓展组】的场合（专员和拓展经理都属于拓展组）
        if (paramGroup.getTypeId() == 4) {
            // 拓展经理的场合 (20:拓展经理   21:拓展专员)
            if (paramPost.getTypeId() == 20) {
                // 拓展经理工号
                relatePerson.setExpandLeaderId(paramUser.getUserCode());
                // 拓展经理姓名
                relatePerson.setExpandLeader(paramUser.getUserName());
                // 拓展经理所属组
                relatePerson.setPerformTeam3(paramGroup.getGroupName());
                // 拓展经理所属岗位
                relatePerson.setPostTypeName3(paramPost.getPostName());
                // 拓展经理所属组Id
                relatePerson.setExpandLeaderGroupId(paramGroup.getGroupId());
                // 拓展经理所属岗位Id
                relatePerson.setExpandLeaderPostId(paramPost.getPostId());

                // 拓展专员的场合
            } else {
                // 业绩拓展专员工号
                relatePerson.setExpandingPersonnelId(paramUser.getUserCode());
                // 业绩拓展专员姓名
                relatePerson.setExpandingPersonnel(paramUser.getUserName());
                // 业绩拓展专员所属组（业绩归属团队四）
                relatePerson.setPerformTeam4(paramGroup.getGroupName());
                // 业绩拓展专员所属岗位
                relatePerson.setPostTypeName4(paramPost.getPostName());
                // 拓展专员所属组Id
                relatePerson.setExpandingPersonnelGroupId(paramGroup.getGroupId());
                // 拓展专员所属岗位Id
                relatePerson.setExpandingPersonnelPostId(paramPost.getPostId());
            }

            // 所属组为【拓展事业部】的场合
        } else if (paramGroup.getTypeId() == 8) {
            // 区域总监工号
            relatePerson.setRegionalDirectorId(paramUser.getUserCode());
            // 区域总监姓名
            relatePerson.setRegionalDirector(paramUser.getUserName());
            // 区域总监所属组
            relatePerson.setPerformTeam2(paramGroup.getGroupName());
            // 区域总监所属岗位
            relatePerson.setPostTypeName2(paramPost.getPostName());
            // 区域总监所属组Id
            relatePerson.setRegionalDirectorGroupId(paramGroup.getGroupId());
            // 区域总监所属岗位Id
            relatePerson.setRegionalDirectorPostId(paramPost.getPostId());

            // 所属组为【事业部】的场合
        } else if (paramGroup.getTypeId() == 7) {
            // 事业部总经理工号
            relatePerson.setBusinessManagerId(paramUser.getUserCode());
            // 事业部总经理姓名
            relatePerson.setBusinessManager(paramUser.getUserName());
            // 事业部总经理所属组
            relatePerson.setPerformTeam1(paramGroup.getGroupName());
            // 事业部总经理所属岗位
            relatePerson.setPostTypeName1(paramPost.getPostName());
            // 事业部总经理所属组Id
            relatePerson.setBusinessManagerGroupId(paramGroup.getGroupId());
            // 事业部总经理所属岗位Id
            relatePerson.setBusinessManagerPostId(paramPost.getPostId());
        }
    }

    /**
     * 设置业绩归属团队信息（包含拓展专员、拓展经理、区域总监、事业部总经理）
     *
     * @param paramGroup   组信息
     * @param relatePerson 业绩关联人员信息
     * @throws Exception
     */
    private void setPerformTeamInfo(Group paramGroup, ContractRelatePerson relatePerson)
            throws Exception {
        // 所属组为【拓展组】的场合（专员和拓展经理都属于拓展组）
        if (paramGroup.getTypeId() == 4) {
            // 拓展专员和拓展经理的场合
            // 拓展经理所属组 (业绩归属团队三)
            relatePerson.setPerformTeam3(paramGroup.getGroupName());
            // 拓展经理所属组Id
            relatePerson.setExpandLeaderGroupId(paramGroup.getGroupId());
            // 业绩拓展专员所属组（业绩归属团队四）
            relatePerson.setPerformTeam4(paramGroup.getGroupName());
            // 拓展专员所属组Id
            relatePerson.setExpandingPersonnelGroupId(paramGroup.getGroupId());

            // 所属组为【拓展事业部】的场合
        } else if (paramGroup.getTypeId() == 8) {
            // 区域总监所属组 (业绩归属团队二)
            relatePerson.setPerformTeam2(paramGroup.getGroupName());
            // 区域总监所属组Id
            relatePerson.setRegionalDirectorGroupId(paramGroup.getGroupId());

            // 所属组为【事业部】的场合
        } else if (paramGroup.getTypeId() == 7) {
            // 事业部总经理所属组 (业绩归属团队一)
            relatePerson.setPerformTeam1(paramGroup.getGroupName());
            // 事业部总经理所属组Id
            relatePerson.setBusinessManagerGroupId(paramGroup.getGroupId());
        }
    }

    /**
     * 设置合同审批流审批信息
     *
     * @param contract 合同信息
     * @return
     * @throws Exception
     */
    private ContractFlowDto setContractFlow(Contract contract)
            throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        ContractFlowDto contractFlowDto = new ContractFlowDto();
        // 合同编号
        contractFlowDto.setContractNo(contract.getContractNo());
        // 合同类型
        contractFlowDto.setContractType(SystemParam.getDicValueByDicCode(contract.getContractType() + ""));
        // 合同状态
        contractFlowDto.setContractState(SystemParam.getDicValueByDicCode(contract.getContractStatus() + ""));
        // 公司名称
        contractFlowDto.setCompanyName(contract.getPartyB());
        // 创建人
        contractFlowDto.setUserIdCreate(contract.getUserCreate());
        // 创建时间
        //contractFlowDto.setDateCreate(new Date());
        // 删除标识(false:未删除 true:已删除)
        contractFlowDto.setDelFlag(false);
        // 记录数量
        contractFlowDto.setRecordNum("1");
        // 业绩节点确认时间
        contractFlowDto.setPerformDateStr(sdf.format(new Date()));
        return contractFlowDto;
    }

    /**
     * 新增合同操作流程信息 (供OMS-业绩查看用)
     *
     * @param flowId
     * @throws Exception
     */
    private void createContractFlow(String flowId)
            throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        Contract contract = new Contract();
        // 1.根据flowId获取合同信息
        contract = contractMapper.getByFlowId(flowId);

        // 合同是非C版的场合
        if (contract.getContractType() != DictionaryConstants.CONTRACT_TYPE_C) {
            // 2.获取门店信息
            List<ContractStore> storeList = contractStoreMapper.selStoreByContractId(contract.getId());

            // 3.设置合同审批流审批信息
            ContractFlowDto contractFlowDto = this.setContractFlow(contract);

            // 非空判断
            if (null != storeList && !storeList.isEmpty()) {
                for (ContractStore contractStore : storeList) {
                    List<ContractFlowDto> dtoList = new ArrayList<ContractFlowDto>();
                    // 4.合同是【A转B版】的场合、插入原A版合同信息（记录数量为"-1"）
                    if (contract.getContractType() == DictionaryConstants.CONTRACT_TYPE_A_2_B) {
                        Map<String, Object> queryParam = new HashMap<String, Object>();
                        queryParam.put("storeId", contractStore.getStoreId());
                        queryParam.put("contractId", contractStore.getContractId());
                        // 根据门店ID、合同ID查询审核通过"-1"的记录
                        ContractFlowDto dto = contractFlowMapper.getContractFlowByStoreId(queryParam);
                        dto.setPerformDateStr(sdf.format(dto.getPerformDate()));
                        dto.setPerformDate(null);
                        // 非空判断
                        if (null != dto) {
                            dtoList.add(dto);
                        }
                    }

                    // 5.根据门店ID查询门店信息
                    Store store = storeMapper.getById(contractStore.getStoreId());
                    // 门店ID
                    contractFlowDto.setStoreNo(store.getStoreNo());
                    // 门店名称
                    contractFlowDto.setStoreName(store.getName());
                    // 门店地址
                    contractFlowDto.setStoreAddressDetail(store.getAddressDetail());
                    // 城市编号
                    contractFlowDto.setCityNo(store.getCityNo());
                    dtoList.add(contractFlowDto);

                    // 非空判断
                    if (null != dtoList) {

                        // 6.新增--合同审批流审批信息（调用OMS接口）
                        extOmsService.insertContractFlowDto(dtoList);
                    }
                }
            }
        }
    }

    /**
     * 新增门店装修信息（定时任务和手动获取状态公用）
     *
     * @param flowId
     * @return
     * @throws Exception
     */
    public ResultData<List<StoreDecorationDto>> insertNewDecorationRecord(Map<?, ?> reqMap)
            throws Exception {
        //更新门店装修信息到Store_Decoration表中
        ResultData<List<StoreDecorationDto>> resultData = new ResultData<List<StoreDecorationDto>>();

        //1、根据flowIdList获取到合同和门店信息List
        List<StoreDecorationDto> list = contractMapper.getPassContractList(reqMap);
        //获取创建时间
        Date dateCreate = (Date) reqMap.get("dateUpdate");

        //2、数据转换保存在门店装修表中
        List<StoreDecorationDto> insertList = new ArrayList<StoreDecorationDto>();
        if (null != list && !list.isEmpty()) {
            for (StoreDecorationDto storeDecorationDto : list) {
                //ADD By GUOPENGFEI 2017/04/07 start
                if (storeDecorationDto.getOriginalContractdistinction() == 18602) {//续签合同不做装修申请
                    continue;
                }
                //ADD By GUOPENGFEI 2017/04/07 end
                storeDecorationDto.setDateCreate(dateCreate);
                storeDecorationDto.setUpdateDate(dateCreate);
                storeDecorationDto.setDelFlag(false);
                ResultData<String> back = seqNoAPI.getSeqNoByTypeCode("TYPE_STOREDECORATION");
                String decorationNo = back.getReturnData();
                storeDecorationDto.setDecorationNo(decorationNo);
                storeDecorationDto.setDecorationStatus(16301);
                insertList.add(storeDecorationDto);
            }
            if (!insertList.isEmpty()) {
                // 批量插入到门店装修表
                int count1 = storeDecorationService.insertBatch(insertList);
                if (count1 <= 0) {
                    resultData.setFail();
                    resultData.setReturnMsg("批量插入到门店装修表出错");
                }
            } else {
                resultData.setFail();
                resultData.setReturnMsg("批量插入到门店装修表准备数据出错");
            }
            //3、返回门店数据调用OMS接口
            resultData.setReturnData(insertList);
            return resultData;
        } else {
            resultData.setFail();
            resultData.setReturnMsg("批量获取门店信息出错");
            return resultData;
        }

    }

    /**
     * 根据OA flowId 查询保证金不等于0的合同
     *
     * @param flowId
     * @return
     */
    public Contract getDepoistNozeroCtrctByFlowId(String flowId)
            throws Exception {
        Contract contract = contractMapper.getDepoistNozeroCtrctByFlowId(flowId);
        return contract;
    }


    //##################### 以下是定时获取OA审核状态专用方法 start (2016-10-09) ########################//

    /**
     * 查询合同列表信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    public List<ContractSearchResultDto> getContractList(Map<?, ?> param)
            throws Exception {
        // 构建返回
        List<ContractSearchResultDto> contractDtoList = new ArrayList<ContractSearchResultDto>();
        // 查询操作
        List<ContractSearchResultDto> craList = contractMapper.getContractCompanyList(param);
//        final List<ContractSearchResult> craList = contractMapper.selectContractList(param);
//        // 转换
//        contractDtoList = convertContractSearchResultData(craList);
        return contractDtoList;
    }

    //##################### 以上是定时获取OA审核状态专用方法 end (2016-10-09)########################//

    /**
     * 获取合同门店关联表的业绩撤销状态
     *
     * @param contractId
     * @return
     * @throws Exception
     */
    public ResultData<String> getContractStoreIsCancel(Integer contractId) throws Exception {
        ResultData<String> resultData = new ResultData<String>();
        String cancelState = "N";
        List<ContractStore> list = contractStoreMapper.selStoreByContractId(contractId);

        //判断门店撤销状态
        if (null == list || list.isEmpty()) {
            resultData.setFail("获取合同门店关联表的业绩撤销状态失败！");
            return resultData;
        } else {
            for (ContractStore cs : list) {
                if (cs.getIsCancel().equals(DictionaryConstants.STORESTATE_ISCANCEL_ISCANCEL)) {
                    cancelState = "Y";
                    break;
                }
            }
        }
        resultData.setReturnData(cancelState);
        return resultData;
    }

    /**
     * 获取合同剩余未分账保证金-用于校验
     *
     * @param contractId
     * @return
     * @throws Exception
     */
    public ResultData<String> checkRestDeposit(Integer contractId) throws Exception {
        ResultData<String> resultData = new ResultData<String>();
        Map<String, Object> map = new HashMap<>();
        map.put("contractId", contractId);
        Map<String, BigDecimal> mop = contractMapper.checkRestDeposit(map);
        resultData.setReturnData(String.valueOf(mop.get("restSplitDepositFee")));
        return resultData;
    }


    //Add by WangLei 2017/04/07 Start

    /**
     * 获取原合同信息
     *
     * @param storeId
     * @param contractId
     * @return
     */
    public ContractStore getOrgContractInfo(Integer storeId, String originalContractNo) throws Exception {
        ResultData<String> resultData = new ResultData<String>();
        Map<String, Object> map = new HashMap<>();
        map.put("storeId", storeId);
        map.put("originalContractNo", originalContractNo);

        ContractStore contractStore = contractStoreMapper.getOrgContractInfo(map);

        return contractStore;
    }
    //Add by WangLei 2017/04/07 End

    //Add By NingChao 2017/04/07 Start

    /**
     * 修改门店带续签状态为正常
     *
     * @param storeId 门店ID
     * @return
     * @throws Exception
     */
    public void updateRenewFlag(Integer storeId)
            throws Exception {
        try {
            storeService.updateRenewFlag(storeId);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    //Add By NingChao 2017/04/07 End

//Add By GUOPENGFEI 2017/04/24 start

    /**
     * 取得OA审批结果信息
     *
     * @return OA审批结果信息
     * @throws Exception
     */
    public List<OaContractReturn> getOAContractReturn() throws Exception {
        // 查询操作
        final List<OaContractReturn> oaResult = oaContractReturnMapper.getOAContractReturn();
        return oaResult;

    }

    /**
     * 更新OA审批结果
     *
     * @param oAContractReturn
     * @return 更新数
     * @throws Exception
     */
    public Integer updateOAContractReturn(OaContractReturn oAContractReturn) throws Exception {

        Integer rtn = oaContractReturnMapper.updateOAContractReturn(oAContractReturn);
        return rtn;
    }

    /**
     * 获取OA审批意见
     *
     * @param flowId
     * @return
     * @throws Exception
     */
    public List<OaContractApprovalInfoDto> getOaOpinions(String flowId)
            throws Exception {
        List<OaContractApprovalInfoDto> dto = new ArrayList<OaContractApprovalInfoDto>();
        final List<OaContractApprovalInfo> oAOptionslst = oaContractApprovalInfoMapper.GetOaContractApprovalInfo(flowId);
        for (OaContractApprovalInfo val : oAOptionslst) {
            OaContractApprovalInfoDto tmp = new OaContractApprovalInfoDto();
            tmp.setContent(val.getContent());
            tmp.setCreate_date(val.getCreate_date());
            tmp.setEmpname(val.getEmpname());
            tmp.setEmpnumber(val.getEmpnumber());
            dto.add(tmp);
        }
        return dto;
    }
    //Add By GUOPENGFEI 2017/04/24 end

    //Add By GuoPengFei 2017/05/25 合规性 start

    /**
     * 根据门店ID取得该门店审核通过的B和A2B合同信息
     *
     * @param storeid 门店ID
     * @return
     * @throws Exception
     */
    public ResultData<Contract> getContractByStoreId(Integer storeid) throws Exception {
        // 构建返回
        ResultData<Contract> resultData = new ResultData<Contract>();

        Contract data = contractMapper.getContractByStoreId(storeid);

        resultData.setReturnData(data);
        return resultData;
    }

    /**
     * 根据合同No查找该合同门店的乙转甲变更单号
     *
     * @param contractNo 合同编号
     * @return
     * @throws Exception
     */
    public ResultData<ContractChangeDto> getcontractB2AChangeNo(String contractNo) throws Exception {
        // 构建返回
        ResultData<ContractChangeDto> resultData = new ResultData<ContractChangeDto>();

        ContractChangeDto data = contractChangeMapper.getcontractB2AChangeNo(contractNo);

        resultData.setReturnData(data);
        return resultData;
    }

    /**
     * 根据flowId 更新 合同里的 公司地址
     *
     * @param param
     * @return
     */
    public int updateContractCompanyAdressByFlowId(ContractInfoDto contractInfoDto)
            throws Exception {
        Contract contract = new Contract();
        //赋值
        BeanUtils.copyProperties(contractInfoDto.getContract(), contract);

        // 更新合同状态
        int count = contractMapper.updateContractCompanyAdressByFlowId(contract);

        return count;
    }
    //Add By GuoPengFei 2017/05/25 合规性 end

    //Add By Sucen 2017/07/17 start

    /**
     * 取得OA房友公司返回结果信息
     *
     * @return OA房友公司返回结果信息
     * @throws Exception
     */
    public List<OaFYCompanyReturn> getOaFYCompanyReturn() throws Exception {
        // 查询操作
        final List<OaFYCompanyReturn> oaResult = oaFYCompanyReturnMapper.getOAFYCompanyReturn();
        return oaResult;

    }

    /**
     * 取得门店房友账号关联信息
     *
     * @return 门店房友账号关联信息
     * @throws Exception
     */
    public StoreFangyouAccount getStoreFangyouAccount(String fangyouNo, String storeNo) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fangyouNo", fangyouNo);
        map.put("storeNo", storeNo);

        // 查询操作
        final StoreFangyouAccount oaResult = storeFangyouAccountMapper.getStoreFangyouAccount(map);
        return oaResult;

    }

    /**
     * 更新OA房友公司返回的未提交原因
     *
     * @param oAContractReturn
     * @return 更新数
     * @throws Exception
     */
    public Integer updateOAFYCompanyReason(OaFYCompanyReturn oAFYCompanyReturn) throws Exception {

        Integer rtn = oaFYCompanyReturnMapper.updateOAFYCompanyReason(oAFYCompanyReturn);
        return rtn;
    }

    /**
     * 更新OA房友公司返回的提交状态
     *
     * @return 更新数
     * @throws Exception
     */
    public Integer updateOAFYCompanyHasDeal() throws Exception {
        Integer rtn = oaFYCompanyReturnMapper.updateOAFYCompanyHasDeal();
        return rtn;
    }

    /**
     * 新建门店房友账号关联表数据
     *
     * @param createStoreFangyouAccount
     * @return 新建数
     * @throws Exception
     */
    public Integer createStoreFangyouAccount(StoreFangyouAccount storeFangyouAccount) throws Exception {
        Integer rtn = storeFangyouAccountMapper.createStoreFangyouAccount(storeFangyouAccount);
        return rtn;
    }

    /**
     * 新建门店房友账号绑定/解绑日志
     *
     * @param createStoreFangyouAccount
     * @return 新建数
     * @throws Exception
     */
    public Integer createLogStoreFangyouAccount(FangyouAccount fangFangyouAccount) throws Exception {
        Integer rtn = fangyouAccountMapper.create(fangFangyouAccount);
        return rtn;
    }

    /**
     * 更新门店房友账号关联的开通绑定状态
     *
     * @param storeFangyouAccount
     * @return 更新数
     * @throws Exception
     */
    public Integer updateStoreFangyouAccountStatus(StoreFangyouAccount storeFangyouAccount) throws Exception {
        Integer rtn = storeFangyouAccountMapper.updateStoreFangyouAccountStatus(storeFangyouAccount);
        return rtn;

    }

    /**
     * 取得合同门店信息
     *
     * @return 合同门店信息
     * @throws Exception
     */
    public List<ContractStore> getContractStoreByContractId(Integer contractId) throws Exception {
        // 查询操作
        final List<ContractStore> oaResult = contractStoreMapper.getContractStoreByContractId(contractId);
        return oaResult;

    }
    //Add By Sucen 2017/07/17 end

    public Contract getContractByNo(String contractNo) throws Exception {
        return contractMapper.getContractByNo(contractNo);
    }

    public void uploadAdditional(Integer contractId, String fileRecordMainIds) {

        //删除原有文件
        Map<String, Object> map = new HashMap<>();
        map.put("contractId", contractId);
        map.put("fileTypeId", 1026);
        map.put("fileSourceId", 1);

        fileRecordMainMapper.deleteByCondition(map);

        //更新最新文件
        if (StringUtils.isNotEmpty(fileRecordMainIds) && !"-1".equals(fileRecordMainIds)) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("contractId", contractId);

            String[] array = fileRecordMainIds.split(",");
            List<Integer> ids = new ArrayList<>();
            for (String id : array) {
                ids.add(Integer.valueOf(id));
            }
            map1.put("ids", ids);

            fileRecordMainMapper.updateByCondition(map1);
        }
    }

    public List<Contract> queryConfirmContractByCompanyId(Integer companyId) {
        return contractMapper.queryConfirmContractByCompanyId(companyId);
    }

    public Contract selectNewestContract(Integer storeId) {
        return contractMapper.selectNewestContract(storeId);
    }

    public List<Contract> selectNewestContractByCompanyId(Integer companyId) {
        return contractMapper.selectNewestContractByCompanyId(companyId);
    }

    /**
     * 运营变更合同状态
     *
     * @param param
     * @return
     */

    public int operateChangeCt(ContractInfoDto contractInfoDto)
            throws Exception {
        Contract contract = new Contract();
        //赋值
        Integer id = contractInfoDto.getContract().getId();
        //String contractNo = contractInfoDto.getContract().getContractNo();
        contract.setId(id);
        // 合同状态变为审核不通过
        contract.setContractStatus(DictionaryConstants.CONTRACT_STATUS_AUDIT_NO_PASS);
        contract.setDateUpdate(new Date());
        // 更新合同状态
        int count = contractMapper.update(contract);
        if (count > 0) {
            contractMapper.insertContractReturn(id);
        }
        return count;
    }

    /**
     * 根据门店和合同查询已解除关联的门店公司
     *
     * @param storeId 门店Id
     * @return
     */
    public ResultData<List<CompanyStoreDtoNew>> getIsRelieveCompany(Map<?, ?> param)
            throws Exception {
        //构建返回
        ResultData<List<CompanyStoreDtoNew>> resultData = new ResultData<List<CompanyStoreDtoNew>>();
        //查询
        final List<CompanyStoreDtoNew> craList = contractMapper.getIsRelieveCompany(param);
        //转换
        resultData.setReturnData(craList);
        return resultData;
    }


    /**
     * 更新门店续签状态
     * @param contractId
     * @return
     */

    public int updateStoreReNewFlag(Integer contractId) throws Exception {

        List<ContractStore> list = contractStoreMapper.selStoreByContractId(contractId);
        int count = 0;
        for (ContractStore contractStore : list) {
            if(contractStore==null || contractStore.getStoreId()==null){
                continue;
            }
            Integer renewFlag = storeMapper.selectUpdateStoreReNewFlag(contractStore.getStoreId());

            Integer neededRenew = 18501;
            if (renewFlag == null) {
                renewFlag = 18301;
            }

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("neededRenew", neededRenew);
            param.put("renewFlag", renewFlag);
            param.put("storeId", contractStore.getStoreId());

            count = storeMapper.updateStoreReNewFlag(param);
        }
        return count;
    }



    /**
     * 查询合同到期时间
     * @param storeId
     * @return
     */
    public Contract selectDateLifeEnd(Integer storeId){
        Contract contract = contractMapper.selectDateLifeEnd(storeId);
        return contract;
    }
}