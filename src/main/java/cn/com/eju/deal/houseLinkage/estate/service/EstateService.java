package cn.com.eju.deal.houseLinkage.estate.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import cn.com.eju.deal.base.dto.file.FileDto;
import cn.com.eju.deal.base.file.service.FilesService;
import cn.com.eju.deal.base.file.util.FileAssist;
import cn.com.eju.deal.base.helper.FileHelper;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.WeiphotoHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateChangeDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateInfoDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateRuleDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateSearchResultDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateSupportDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateTypeDto;
import cn.com.eju.deal.dto.houseLinkage.estate.LnkEstateIncomeplanDto;
import cn.com.eju.deal.dto.houseLinkage.estate.LnkYjPlanCompanyDto;
import cn.com.eju.deal.dto.houseLinkage.estate.LnkYjPlanDetailDto;
import cn.com.eju.deal.dto.houseLinkage.estate.LnkYjPlanDto;
import cn.com.eju.deal.dto.houseLinkage.estate.LnkYjPlanWyDto;
import cn.com.eju.deal.dto.houseLinkage.estate.MattressControlRuleDetailDto;
import cn.com.eju.deal.dto.houseLinkage.estate.MattressControlRuleDto;
import cn.com.eju.deal.dto.houseLinkage.estate.PhotoDto;
import cn.com.eju.deal.dto.user.UserDto;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateChangeMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateRuleMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateSupportMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateTypeMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.LnkEstateIncomeplanMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.LnkYjPlanCompanyMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.LnkYjPlanDetailMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.LnkYjPlanMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.LnkYjWyInfoMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.LnkYjWyMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.PhotoMapper;
import cn.com.eju.deal.houseLinkage.estate.model.BigCutomer;
import cn.com.eju.deal.houseLinkage.estate.model.Estate;
import cn.com.eju.deal.houseLinkage.estate.model.EstateChange;
import cn.com.eju.deal.houseLinkage.estate.model.EstateContractDto;
import cn.com.eju.deal.houseLinkage.estate.model.EstateDyRecord;
import cn.com.eju.deal.houseLinkage.estate.model.EstateRule;
import cn.com.eju.deal.houseLinkage.estate.model.EstateSearchResult;
import cn.com.eju.deal.houseLinkage.estate.model.EstateSupport;
import cn.com.eju.deal.houseLinkage.estate.model.EstateType;
import cn.com.eju.deal.houseLinkage.estate.model.LnkYjPlan;
import cn.com.eju.deal.houseLinkage.estate.model.LnkYjPlanCompany;
import cn.com.eju.deal.houseLinkage.estate.model.LnkYjPlanDetail;
import cn.com.eju.deal.houseLinkage.estate.model.LnkYjWy;
import cn.com.eju.deal.houseLinkage.estate.model.LnkYjWyInfo;
import cn.com.eju.deal.houseLinkage.estate.model.MattressNail;
import cn.com.eju.deal.houseLinkage.estate.model.Photo;
import cn.com.eju.deal.houseLinkage.report.dao.ReportMapper;
import cn.com.eju.deal.houseLinkage.report.model.Report;
import cn.com.eju.deal.reportbase.util.DateUtil;
import cn.com.eju.deal.user.api.impl.UserAPIImpl;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.Group;
import cn.com.eju.deal.user.model.User;
import cn.com.eju.pmls.cooperation.dao.CooperationMapper;
import cn.com.eju.pmls.developer.dao.DeveloperMapper;
import cn.com.eju.pmls.developer.dto.DeveloperDto;

/**
 * service层
 *
 * @author qianwei
 * @date 2016年3月22日 下午7:57:09
 */
@Service("estatetService")
public class EstateService extends BaseService
{
	private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private EstateMapper estateMapper;

    @Resource
    private EstateRuleMapper estateRuleMapper;

    @Resource
    private EstateSupportMapper estateSupportMapper;

    @Resource
    private EstateTypeMapper estateTypeMapper;

    @Resource
    private PhotoMapper photoMapper;

    @Resource
    private UserAPIImpl userAPIImpl;

    @Resource
    private GroupMapper groupMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private EstateChangeMapper estateChangeMapper;

    @Resource(name = "filesService")
    private FilesService filesService;

    @Resource
    private ContractMapper contractMapper;

    @Resource
    private CooperationMapper cooperationMapper;

    @Resource
    private LnkEstateIncomeplanMapper lnkEstateIncomeplanMapper;
    
    @Resource
    private LnkYjPlanMapper lnkYjPlanMapper;

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private LnkYjPlanDetailMapper lnkYjPlanDetailMapper;
    
    @Resource
    private LnkYjWyMapper lnkYjWyMapper;
    
    @Resource
    private LnkYjPlanCompanyMapper lnkYjPlanCompanyMapper;
    
    @Resource
    private LnkYjWyInfoMapper lnkYjWyInfoMapper;

    @Resource
    private DeveloperMapper developerMapper;


    /**
     * 查询
     *
     * @param id
     * @return
     */
    public EstateInfoDto getById(int id)
        throws Exception
    {
        EstateInfoDto estateInfoDto = new EstateInfoDto();
        Map<String, Object> param = new HashMap<>();
        //获取楼盘基本信息
        EstateDto estateDto = new EstateDto();
        Estate estate =new Estate();
        if(id==-999999){//获取创建日期最早的未审核的项目
            estate = estateMapper.getLastProject();
        }else{
            estate = this.estateMapper.getById(id);
        }
        if (null != estate)
        {
            BeanUtils.copyProperties(estate, estateDto);
            Integer custodyFlg = estateDto.getCustodyFlg();
            //是可垫佣甲方 获取垫佣甲方简称
            if(custodyFlg !=null && custodyFlg ==1) {
            	param.put("mattressNailId", estateDto.getMattressNailId());
            	MattressNail mattressNail = estateMapper.getMattressNailByMattressNailId(param);
            	if(!StringUtils.isEmpty(mattressNail) ) {
            		estateDto.setMattressNailName(mattressNail.getName());
            	}
            }
            estateDto.setSceneDeptNm(this.getGroupNmByDepId(estateDto.getSceneDeptId()));
            estateDto.setSceneEmpName(this.getNameByUserId(estateDto.getSceneEmpId()));

            if(estateDto.getVendibilityAmount()!=null){
                BigDecimal vendibilityAmount = new BigDecimal("0.00");
                BigDecimal baseAccount = new BigDecimal("100000000");
                vendibilityAmount = estateDto.getVendibilityAmount().divide(baseAccount).setScale(2, BigDecimal.ROUND_HALF_UP);
                estateDto.setVendibilityAmount(vendibilityAmount);
            }
            if(estateDto.getMonthRoughAmount()!=null){
                BigDecimal monthRoughAmount = new BigDecimal("0.00");
                BigDecimal baseAccount = new BigDecimal("100000000");
                monthRoughAmount = estateDto.getMonthRoughAmount().divide(baseAccount).setScale(2, BigDecimal.ROUND_HALF_UP);
                estateDto.setMonthRoughAmount(monthRoughAmount);
            }

            estateInfoDto.setEstate(estateDto);
            if (StringUtil.isNotEmpty(estate.getProjectNo())) {
                param.put("estateId", estate.getEstateId());
                param.put("estateId2", estate.getId());
                param.put("projectNo", estate.getProjectNo());
                //收入类合同
                List<EstateContractDto> incomeContractList = estateRuleMapper.querySrlHtRecord(param);
                if(null!=incomeContractList && !incomeContractList.isEmpty()){
                    estateInfoDto.setIncomeContractList(incomeContractList);
                }
                //进场确认函合同
                List<EstateContractDto> jcqrhList = estateRuleMapper.queryJCQRHRecord(param);
                if(null!=jcqrhList && !jcqrhList.isEmpty()){
                    estateInfoDto.setJcqrhContractList(jcqrhList);
                }
                //add by haidan 2019/7/15垫佣记录
                List<EstateDyRecord> estateDyRecords = estateRuleMapper.queryDyRecord(param);
                if (null != estateDyRecords && !estateDyRecords.isEmpty()) {
                    EstateDyRecord estateDyRecord =estateDyRecords.get(0);
                    estateDto.setDyAmount(estateDyRecord.getDyAmount());
                    estateDto.setDyStartDate(estateDyRecord.getDyStartDate());
                    estateDto.setDyEndDate(estateDyRecord.getDyEndDate());
                    estateInfoDto.setEstate(estateDto);
                    estateInfoDto.setEstateDyRecords(estateDyRecords);
                }

            }
            //获取收入方案
            List<LnkEstateIncomeplanDto> incomeplanList = lnkEstateIncomeplanMapper.queryPlanListByProjectNo(param);
            if (null != incomeplanList && incomeplanList.size()>0){
                estateInfoDto.setIncomeplanList(incomeplanList);
            }

        }

        //获取变更历史
        List<EstateChange> estateChangeList = this.estateChangeMapper.getByEstateId(param);
        if (null != estateChangeList && estateChangeList.size()>0){
            List<EstateChangeDto> estateChangeDtoList = new ArrayList<>();
            for (int i=0;i<estateChangeList.size();i++){
            	EstateChange ec = estateChangeList.get(i);
            	EstateChangeDto ecDto = new EstateChangeDto();
            	BeanUtils.copyProperties(ec, ecDto);
            	ecDto.setOrderId(i+1);
            	estateChangeDtoList.add(ecDto);
            }
            estateInfoDto.setEstateChangeDetails(estateChangeDtoList);
        }
        return estateInfoDto;
    }

    /**
    * @Title: getEstateDetailById
    * @Description: 根据自增编号获取楼盘基本信息和楼盘详情
    * @param id
    * @return
    */
    public EstateDto getEstateDetailById(int id)
        throws Exception
    {
        EstateDto estateDto = new EstateDto();
        Estate estate = this.estateMapper.getById(id);
        if (null != estate)
        {
            BeanUtils.copyProperties(estate, estateDto);
            Integer custodyFlg = estateDto.getCustodyFlg();
            //是可垫佣甲方 获取垫佣甲方简称
            if(custodyFlg !=null ) {
            	Map<String,Object> param = new HashMap<>();
            	param.put("mattressNailId", estateDto.getMattressNailId());
            	MattressNail mattressNail = estateMapper.selectMattressNail(param);
            	if(!StringUtils.isEmpty(mattressNail)) {
            		estateDto.setMattressNailName(mattressNail.getName());
            	}
            }
            String areaNm = SystemParam.getAreaNameByAreaNo(estateDto.getAreaId());
            estateDto.setAreaNm(areaNm);
            estateDto.setSceneEmpName(this.getNameByUserId(estateDto.getSceneEmpId()));

            DeveloperDto developerDto = new DeveloperDto();//历史开发商信息
            if(StringUtil.isNotEmpty(estate.getDevCompanyId())){
                Map<String, Object> queryParam = new HashMap<>();
                queryParam.put("developerId",estate.getDevCompanyId());
                List<DeveloperDto> list = developerMapper.getDeveloperList(queryParam);
                if(!CollectionUtils.isEmpty(list)){
                    developerDto = list.get(0);
                }
            }
            estateDto.setDeveloperDto(developerDto);

            if(estateDto.getVendibilityAmount()!=null){
                BigDecimal vendibilityAmount = new BigDecimal("0.00");
                BigDecimal baseAccount = new BigDecimal("100000000");
                vendibilityAmount = estateDto.getVendibilityAmount().divide(baseAccount).setScale(2, BigDecimal.ROUND_HALF_UP);
                estateDto.setVendibilityAmount(vendibilityAmount);
            }
            if(estateDto.getMonthRoughAmount()!=null){
                BigDecimal monthRoughAmount = new BigDecimal("0.00");
                BigDecimal baseAccount = new BigDecimal("100000000");
                monthRoughAmount = estateDto.getMonthRoughAmount().divide(baseAccount).setScale(2, BigDecimal.ROUND_HALF_UP);
                estateDto.setMonthRoughAmount(monthRoughAmount);
            }

        }
        return estateDto;
    }

    /**
    * @Title: getEstateRuleByEstateId
    * @Description: 根据楼盘编号获取联动规则
    * @param param
    * @return
    */
    public ResultData<EstateRuleDto> getEstateRuleByEstateId(Map<?, ?> param)
        throws Exception
    {
        ResultData<EstateRuleDto> resultData = new ResultData<>();
        List<EstateRuleDto> estateRuleDetails = new ArrayList<>();
        List<EstateRule> estateRules = this.estateRuleMapper.queryList(param);
        if (null != estateRules && !estateRules.isEmpty())
        {
            for (EstateRule estateRule : estateRules)
            {
                EstateRuleDto estateRuleDto = new EstateRuleDto();
                BeanUtils.copyProperties(estateRule, estateRuleDto);
                estateRuleDetails.add(estateRuleDto);
            }
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(estateRuleDetails.get(0));
        }
        return resultData;
    }

    /**
    * @Title: getEstateTypeByEstateId
    * @Description: 根据楼盘编号获取在售户型
    * @param param
    * @return
    */
    public ResultData<List<EstateTypeDto>> getEstateTypeByEstateId(Map<?, ?> param)
        throws Exception
    {
        ResultData<List<EstateTypeDto>> resultData = new ResultData<>();
        List<EstateTypeDto> estateTypeDtos = new ArrayList<>();
        List<EstateType> estateTypes = this.estateTypeMapper.queryList(param);
        Map<String, Object> phoMap = new HashMap<>();
        if (null != estateTypes && !estateTypes.isEmpty())
        {
            for (EstateType estateType : estateTypes)
            {
                EstateTypeDto estateTypeDto = new EstateTypeDto();
                BeanUtils.copyProperties(estateType, estateTypeDto);
                //图片
                phoMap = new HashMap<>();
                phoMap.put("estateId", estateTypeDto.getEstateId());
                phoMap.put("typeId", estateTypeDto.getTypeId());
                List<PhotoDto> photoDtos = new ArrayList<>();
                List<Photo> photos = photoMapper.queryList(phoMap);
                for (Photo photo : photos)
                {
                    PhotoDto photoDto = new PhotoDto();
                    BeanUtils.copyProperties(photo, photoDto);
//                    String fileNo = photoDto.getFileNo();
//                    String photoId = handleFile(fileNo);

                    //photoDto.setPhotoPath(FileHelper.getFilePath(photo.getPhotoId()));
                    //对文件数据进行组装处理
//                    handleFile(photoDto);
//                    photoDto.setPhotoId(photoId);
                    photoDtos.add(photoDto);
                }
                if (!photoDtos.isEmpty())
                {
                    estateTypeDto.setHouseImgs(photoDtos);
                }

                //户型图
                phoMap = new HashMap<>();
                phoMap.put("estateId", estateTypeDto.getEstateId());
                phoMap.put("typeId", estateTypeDto.getTypeId());
                phoMap.put("photoKbn", DictionaryConstants.HOUSE_TYPE_IMG);
                List<String> houseTypeImgs = this.getImgPath(phoMap);
                estateTypeDto.setHouseTypeImgs(houseTypeImgs);
                //样板间
                phoMap = new HashMap<>();
                phoMap.put("estateId", estateTypeDto.getEstateId());
                phoMap.put("typeId", estateTypeDto.getTypeId());
                phoMap.put("photoKbn", DictionaryConstants.HOUSE_TEMPLATE_IMG);
                List<String> houseTemplateImgs = this.getImgPath(phoMap);
                estateTypeDto.setHouseTemplateImgs(houseTemplateImgs);

                estateTypeDtos.add(estateTypeDto);
            }
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(estateTypeDtos);
        }
        return resultData;
    }

    /**
    * @Title: getEstateSupportByEstateId
    * @Description: 根据楼盘编号获取周边配套
    * @param param
    * @return
    */
    public ResultData<List<EstateSupportDto>> getEstateSupportByEstateId(Map<?, ?> param)
        throws Exception
    {
        ResultData<List<EstateSupportDto>> resultData = new ResultData<>();
        List<EstateSupportDto> estateSupportDtos = new ArrayList<>();
        List<EstateSupport> estateSupports = this.estateSupportMapper.queryList(param);
        if (null != estateSupports && !estateSupports.isEmpty())
        {
            for (EstateSupport estateSupport : estateSupports)
            {
                EstateSupportDto estateSupportDto = new EstateSupportDto();
                BeanUtils.copyProperties(estateSupport, estateSupportDto);
                estateSupportDtos.add(estateSupportDto);
            }
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(estateSupportDtos);
        }
        return resultData;
    }

    /**
    * @Title: getPhotoByEstateId
    * @Description: 根据楼盘编号获取楼盘图片
    * @param param
    * @return
    */
    public ResultData<List<PhotoDto>> getPhotoByEstateId(Map<?, ?> param)
        throws Exception
    {
        ResultData<List<PhotoDto>> resultData = new ResultData<>();
        List<PhotoDto> photoDtos = new ArrayList<>();
        List<Photo> photos = this.photoMapper.queryList(param);
        if (null != photos && !photos.isEmpty())
        {
            for (Photo photo : photos)
            {
                PhotoDto photoDto = new PhotoDto();
                BeanUtils.copyProperties(photo, photoDto);
//                String fileNo = photoDto.getFileNo();
//                String photoId = handleFile(fileNo);
//
//                //判定渠道，取文件绝对地址
//                handleFile(photoDto);
//                photoDto.setPhotoId(photoId);
                photoDtos.add(photoDto);
            }
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(photoDtos);
        }
        return resultData;
    }

    /**
     * 查询-list
     * @param param
     * @return
     * @throws Exception
     */
    public ResultData<List<EstateSearchResultDto>> queryList(Map<?, ?> param)
        throws Exception
    {
        //构建返回
        ResultData<List<EstateSearchResultDto>> resultData = new ResultData<List<EstateSearchResultDto>>();
        //查询
        final List<EstateSearchResult> craList = estateMapper.selectEstateList(param);
        //转换
        List<EstateSearchResultDto> estateDtoList = convertEstateSearchResultData(craList);
        resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(estateDtoList);
        return resultData;
    }

    /**
     * 创建
     * @param estateInfoDto
     * @return
     * @throws Exception
     */
    public int create(EstateInfoDto estateInfoDto) throws Exception{
        //楼盘信息
        Estate estate = new Estate();
        String bigCustomerFlag = estateInfoDto.getEstate().getBigCustomerFlag() +"";
        BeanUtils.copyProperties(estateInfoDto.getEstate(), estate);

        if(StringUtil.isNotEmpty(estateInfoDto.getEstate().getDevCompanyId())
                && "22601".equals(bigCustomerFlag)){
            Integer devCompanyId =Integer.parseInt(estateInfoDto.getEstate().getDevCompanyId());
            DeveloperDto developerDtoDb = developerMapper.getDeveloperInfoById(devCompanyId);
            if(StringUtil.isNotEmpty(developerDtoDb.getBigCustomerId())){
                estate.setBigCustomerId(Integer.parseInt(developerDtoDb.getBigCustomerId()));
                //estateInfoDto.getEstate().setBigCustomerId(Integer.parseInt(developerDtoDb.getBigCustomerId()));
            }
        }

        //检查项目是否重复
        if (!this.checkAdd(estate))
        {
            return -2;
        }
        //户型字段 接口查询用
        String estateTypeSearch = "";
        List<EstateTypeDto> typeDtos = estateInfoDto.getEstateTypeDetails();
        for (EstateTypeDto estateTypeDto : typeDtos)
        {
            if (StringUtil.isNotEmpty(estateTypeDto.getCountF()))
            {
                if (StringUtil.isEmpty(estateTypeSearch))
                {
                    estateTypeSearch = estateTypeDto.getTypeId() + ":" + estateTypeDto.getCountF();
                }
                else
                {
                    estateTypeSearch =
                        estateTypeSearch + "," + estateTypeDto.getTypeId() + ":" + estateTypeDto.getCountF();
                }
            }
        }
        if (StringUtil.isNotEmpty(estateTypeSearch))
        {
            estate.setEstateTypeSearch(estateTypeSearch);
        }

        //根据业绩城市编号查询其核算主体
        /*List<Contract> accountProjectList = contractMapper.queryAccountProject(estate.getCityNo());
        if(accountProjectList.size() > 0 && null != accountProjectList){
            String accountProject = accountProjectList.get(0).getAccountProject();
            String accountProjectNo = accountProjectList.get(0).getAccountProjectNo();
            if(accountProject != ""){
                estate.setAccountProject(accountProject);
            }
            if (accountProjectNo != "") {
                estate.setAccountProjectNo(accountProjectNo);
            }
        }*/

        int estateCount = this.estateMapper.create(estate);
        if (estateCount > 0)
        {
            // 楼盘规则列表
            List<EstateRuleDto> estateRuleDetails = estateInfoDto.getEstateRuleDetails();
            for (EstateRuleDto estateRuleDto : estateRuleDetails)
            {
                EstateRule estateRule = new EstateRule();
                BeanUtils.copyProperties(estateRuleDto, estateRule);
                // 默认设置为30天
                estateRule.setRelationProtectPeriod(new BigDecimal("30"));
                //佣金排序字段 查询用
                if(estateRule.getCommissionKbn() != null) {
                    if (estateRule.getCommissionKbn() == DictionaryConstants.COMMISSION_YUAN)
                    {
                        estateRule.setCommissionSort(estateRule.getCommission());
                    }
                    else if (estateRule.getCommissionKbn() == DictionaryConstants.COMMISSION_PERCENTAGE)
                    {
                        if (null != estate.getSalePriceUnitMax())
                        {//单位是万元
                            BigDecimal commission =
                                    estate.getSalePriceUnitMax()
                                    .multiply(estateRule.getCommission().divide(new BigDecimal(100)))
                                    .multiply(new BigDecimal(10000));
                            estateRule.setCommissionSort(commission);
                        }
                    }
                }

                this.estateRuleMapper.create(estateRule);
            }
            //楼盘户型列表
            List<EstateTypeDto> estateTypeDtos = estateInfoDto.getEstateTypeDetails();
            for (EstateTypeDto estateTypeDto : estateTypeDtos)
            {
                EstateType estateType = new EstateType();
                BeanUtils.copyProperties(estateTypeDto, estateType);
                this.estateTypeMapper.create(estateType);
            }
            // 周边配套列表
            List<EstateSupportDto> estateSupportDtos = estateInfoDto.getEstateSupportDetails();
            if (null != estateSupportDtos && !estateSupportDtos.isEmpty())
            {
                for (EstateSupportDto estateSupportDto : estateSupportDtos)
                {
                    EstateSupport estateSupport = new EstateSupport();
                    BeanUtils.copyProperties(estateSupportDto, estateSupport);
                    this.estateSupportMapper.create(estateSupport);
                }
            }
            // 图片文件列表
            List<PhotoDto> photoDtos = estateInfoDto.getFilePhoto();
            if (null != photoDtos && !photoDtos.isEmpty())
            {
                for (PhotoDto photoDto : photoDtos)
                {
                    Photo photo = new Photo();
                    BeanUtils.copyProperties(photoDto, photo);
                    this.photoMapper.create(photo);
                }
            }
        }

        return estateCount;
    }

    /**
     * 更新
     * @param estateInfoDto
     * @return
     */
    public int update(EstateInfoDto estateInfoDto)
        throws Exception
    {
        //更新楼盘基本信息和楼盘详情
        EstateDto estateDto = estateInfoDto.getEstate();
        Estate estate = new Estate();
        String bigCustomerFlag = estateDto.getBigCustomerFlag() +"";
        BeanUtils.copyProperties(estateDto, estate);
        if(StringUtil.isNotEmpty(estateInfoDto.getEstate().getDevCompanyId())
                && "22601".equals(bigCustomerFlag)){
            Integer devCompanyId =Integer.parseInt(estateInfoDto.getEstate().getDevCompanyId());
            DeveloperDto developerDtoDb = developerMapper.getDeveloperInfoById(devCompanyId);
            if(StringUtil.isNotEmpty(developerDtoDb.getBigCustomerId())){
                estate.setBigCustomerId(Integer.parseInt(developerDtoDb.getBigCustomerId()));
                //estateInfoDto.getEstate().setBigCustomerId(Integer.parseInt(developerDtoDb.getBigCustomerId()));
            }
        }

        //检查项目是否重复
        if (!this.checkAdd(estate))
        {
            return -2;
        }
        //户型字段 接口查询用
        String estateTypeSearch = "";
        Map<String, Object> typeMap = new HashMap<>();
        List<EstateType> estateTypes = new ArrayList<>();
        if (null != estate.getEstateId())
        {
            typeMap.put("estateId", estate.getEstateId());
            estateTypes = this.estateTypeMapper.queryList(typeMap);
        }
        else if (null != estate.getId())
        {
            Estate est = this.estateMapper.getById(estate.getId());
            typeMap.put("estateId", est.getEstateId());
            estateTypes = this.estateTypeMapper.queryList(typeMap);
        }
        if (null != estateTypes && !estateTypes.isEmpty())
        {
            for (EstateType estateType : estateTypes)
            {
                if (StringUtil.isNotEmpty(estateType.getCountF()))
                {
                    if (StringUtil.isEmpty(estateTypeSearch))
                    {
                        estateTypeSearch = estateType.getTypeId() + ":" + estateType.getCountF();
                    }
                    else
                    {
                        estateTypeSearch =
                            estateTypeSearch + "," + estateType.getTypeId() + ":" + estateType.getCountF();
                    }
                }
            }
        }
        estate.setEstateTypeSearch(estateTypeSearch);
        int count = this.estateMapper.update(estate);
        if("isUpt".equals(estateInfoDto.getOperationType())){
            int count2 = this.estateMapper.updateDeveloperInfo(estate);
        }
        if (count > 0)
        {

            //更新楼盘联动规则
            List<EstateRuleDto> estateRuleDtos = estateInfoDto.getEstateRuleDetails();
            if (null != estateRuleDtos && !estateRuleDtos.isEmpty())
            {
                EstateRule estateRule = new EstateRule();
                for (EstateRuleDto estateRuleDto : estateRuleDtos)
                {
                    estateRule = new EstateRule();
                    BeanUtils.copyProperties(estateRuleDto, estateRule);
                    //佣金排序字段 查询用
                    if(estateRule.getCommissionKbn() != null) {
                        if (estateRule.getCommissionKbn() == DictionaryConstants.COMMISSION_YUAN)
                        {
                            estateRule.setCommissionSort(estateRule.getCommission());
                        }
                        else if (estateRule.getCommissionKbn() == DictionaryConstants.COMMISSION_PERCENTAGE)
                        {
                            if (null != estate.getSalePriceUnitMax())
                            {
                                BigDecimal commission =
                                        estate.getSalePriceUnitMax()
                                        .multiply(estateRule.getCommission().divide(new BigDecimal(100)))
                                        .multiply(new BigDecimal(10000));
                                estateRule.setCommissionSort(commission);
                            }
                        }
                    }

                    if(estateRule.getId().equals(Integer.valueOf(-9999))){
                        this.estateRuleMapper.create(estateRule);
                    }else {
                        this.estateRuleMapper.update(estateRule);
                    }
                }
            }
            //在售户型的默认已经更新好了

            //更新楼盘相册
            List<PhotoDto> photoDtos = estateInfoDto.getFilePhoto();
            if (null != photoDtos && !photoDtos.isEmpty())
            {
                //先将原来的楼盘图片删掉
                Map<String, Object> phoMap = new HashMap<>();
                phoMap.put("estateId", estate.getEstateId());
                List<Integer> photoKbnList = new ArrayList<>();
                photoKbnList.add(DictionaryConstants.ESTATE_DESIGN_IMG);
                photoKbnList.add(DictionaryConstants.ESTATE_DISTRICT_IMG);
                photoKbnList.add(DictionaryConstants.ESTATE_MAP_IMG);
                photoKbnList.add(DictionaryConstants.ESTATE_REAL_IMG);
                photoKbnList.add(DictionaryConstants.ESTATE_TEMPLATE_IMG);
                if (null != photoKbnList && !photoKbnList.isEmpty())
                {
                    Integer[] arr = (Integer[])photoKbnList.toArray(new Integer[photoKbnList.size()]);
                    phoMap.put("photoKbnList", arr);
                }
                List<Photo> photos = photoMapper.queryList(phoMap);
                for (Photo photo : photos)
                {
                    this.photoMapper.deleteById(photo.getId());
                }

                //TODO 考虑文件渠道系统的删除

                // 图片文件列表
                for (PhotoDto photoDto : photoDtos)
                {

                    Photo photo = new Photo();
                    BeanUtils.copyProperties(photoDto, photo);
                    //接入渠道系统，处理photoId
                    handlePhotoId(photo);

                    this.photoMapper.create(photo);
                }
            }

            //更新楼盘周边配套
            List<EstateSupportDto> estateSupportDtos = estateInfoDto.getEstateSupportDetails();
            if (null != estateSupportDtos && !estateSupportDtos.isEmpty())
            {
                //先将原来的周边删掉
                Map<String, Object> supMap = new HashMap<>();
                supMap.put("estateId", estate.getEstateId());
                List<EstateSupport> estateSupports = this.estateSupportMapper.queryList(supMap);
                if (null != estateSupports && !estateSupports.isEmpty())
                {
                    for (EstateSupport estateSupport : estateSupports)
                    {
                        this.estateSupportMapper.deleteById(estateSupport.getId());
                    }
                }

                // 周边配套列表
                for (EstateSupportDto estateSupportDto : estateSupportDtos)
                {
                    EstateSupport estateSupport = new EstateSupport();
                    BeanUtils.copyProperties(estateSupportDto, estateSupport);
                    this.estateSupportMapper.create(estateSupport);
                }
            }

            // TODO 如果有变更操作对象，则记录变更操作
            if (estateInfoDto.getEstateChangeDetails().size() > 0){
                for (EstateChangeDto estateChangeDto:estateInfoDto.getEstateChangeDetails()) {
                    EstateChange estateChange = new EstateChange();
                    BeanUtils.copyProperties(estateChangeDto, estateChange);
                    this.estateChangeMapper.create(estateChange);
                }
            }

            if(estate.getAuditStatus()!=null && estate.getAuditStatus() == 12903){
                this.estateMapper.syncEstate(estate);
            }
        }
        return count;
    }

    /**
     * 删除
     *
     * @param id
     * @param updateId   更新人
     * @return
     */
    public int delete(int id, int updateId)
        throws Exception
    {

        // 合同为作废状态
        int count = 0;//contractMapper.deleteById(id, updateId);

        return count;
    }

    /**
    * @Title: createEstateType
    * @Description: 新增在售户型
    * @param estateInfoDto
    * @throws Exception
    */
    public void createEstateType(EstateInfoDto estateInfoDto)
        throws Exception
    {
        String estateId = "";
        //楼盘户型列表
        List<EstateTypeDto> estateTypeDtos = estateInfoDto.getEstateTypeDetails();
        for (EstateTypeDto estateTypeDto : estateTypeDtos)
        {
            EstateType estateType = new EstateType();
            BeanUtils.copyProperties(estateTypeDto, estateType);
            this.estateTypeMapper.create(estateType);
            estateId = estateTypeDto.getEstateId();
        }
        // 图片文件列表
        List<PhotoDto> photoDtos = estateInfoDto.getFilePhoto();
        for (PhotoDto photoDto : photoDtos)
        {
            Photo photo = new Photo();
            BeanUtils.copyProperties(photoDto, photo);
            //            if (null != photo.getPhotoId())
            //            {
            //                String photoPath = FileHelper.getFilePath(photo.getPhotoId());
            //                photo.setPhotoPath(photoPath);
            //            }

            //接入渠道系统，处理photoId
//            handlePhotoId(photo);

            this.photoMapper.create(photo);
        }
        //户型字段 接口查询用
        Map<String, Object> estateMap = new HashMap<>();
        if (StringUtil.isNotEmpty(estateId))
        {
            estateMap.put("estateId", estateId);
            List<Estate> estates = this.estateMapper.queryList(estateMap);
            if (null != estates && !estates.isEmpty())
            {
                Estate estate = estates.get(0);
                String estateTypeSearch = "";
                Map<String, Object> typeMap = new HashMap<>();
                typeMap.put("estateId", estate.getEstateId());
                List<EstateType> estateTypes = this.estateTypeMapper.queryList(typeMap);
                for (EstateType estateType : estateTypes)
                {
                    if (StringUtil.isNotEmpty(estateType.getCountF()))
                    {
                        if (StringUtil.isEmpty(estateTypeSearch))
                        {
                            estateTypeSearch = estateType.getTypeId() + ":" + estateType.getCountF();
                        }
                        else
                        {
                            estateTypeSearch =
                                estateTypeSearch + "," + estateType.getTypeId() + ":" + estateType.getCountF();
                        }
                    }
                }
                Estate estateNew = new Estate();
                estateNew.setId(estate.getId());
                estateNew.setEstateTypeSearch(estateTypeSearch);
                this.estateMapper.update(estateNew);
            }
        }
    }

    /**
    * @Title: getEstateTypeById
    * @Description: 根据编号获取在售户型
    * @param typeId
    * @return
    */
    public ResultData<EstateTypeDto> getEstateTypeById(int typeId)
        throws Exception
    {
        ResultData<EstateTypeDto> resultData = new ResultData<>();
        EstateType estateType = this.estateTypeMapper.getById(typeId);
        Map<String, Object> phoMap = new HashMap<>();
        EstateTypeDto estateTypeDto = new EstateTypeDto();
        BeanUtils.copyProperties(estateType, estateTypeDto);
//        String houseTypeImgIds = "";//在售户型户型图
//        String houseTemplateImgIds = "";//在售户型样板间
        String coverImgId = "";//户型封面图片ID
        //图片
        phoMap = new HashMap<>();
        phoMap.put("estateId", estateTypeDto.getEstateId());
        phoMap.put("typeId", estateTypeDto.getTypeId());
        List<PhotoDto> photoDtos = new ArrayList<>();
        List<Photo> photos = photoMapper.queryList(phoMap);
        for (Photo photo : photos)
        {
            PhotoDto photoDto = new PhotoDto();
            BeanUtils.copyProperties(photo, photoDto);

            //String photoId = photoDto.getPhotoId().trim();
//            String fileNo = photoDto.getFileNo();
//            String photoId = handleFile(fileNo);

            if (photoDto.getCoverFlg().equals("Y"))
            {
                coverImgId = photoDto.getSellFileNo();
            }
//            if (photoDto.getPhotoKbn() == DictionaryConstants.HOUSE_TYPE_IMG)
//            {
//                if (houseTypeImgIds.equals(""))
//                {
//                    houseTypeImgIds = photoId;
//                }
//                else
//                {
//                    houseTypeImgIds = houseTypeImgIds + "," + photoId;
//                }
//            }
//            else if (photoDto.getPhotoKbn() == DictionaryConstants.HOUSE_TEMPLATE_IMG)
//            {
//                if (houseTemplateImgIds.equals(""))
//                {
//                    houseTemplateImgIds = photoId;
//                }
//                else
//                {
//                    houseTemplateImgIds = houseTemplateImgIds + "," + photoId;
//                }
//            }

            //photoDto.setPhotoPath(FileHelper.getFilePath(photo.getPhotoId()));
            //对文件数据进行组装处理
//            handleFile(photoDto);
            //赋值photoid
//            photoDto.setPhotoId(photoId);
            photoDtos.add(photoDto);
        }
//        estateTypeDto.setHouseTypeImgIds(houseTypeImgIds);
//        estateTypeDto.setHouseTemplateImgIds(houseTemplateImgIds);
        estateTypeDto.setCoverImgId(coverImgId);
        if (!photoDtos.isEmpty())
        {
            estateTypeDto.setHouseImgs(photoDtos);
        }

        resultData.setReturnData(estateTypeDto);
        return resultData;
    }

    /**
    * @Title: editEstateType
    * @Description: 编辑在售户型
    * @param estateInfoDto
    * @throws Exception
    */
    public void editEstateType(EstateInfoDto estateInfoDto)
        throws Exception
    {
        String estateId = "";
        //楼盘户型列表
        List<EstateTypeDto> estateTypeDtos = estateInfoDto.getEstateTypeDetails();
        for (EstateTypeDto estateTypeDto : estateTypeDtos)
        {
            EstateType estateType = new EstateType();
            BeanUtils.copyProperties(estateTypeDto, estateType);
            this.estateTypeMapper.update(estateType);
            estateId = estateTypeDto.getEstateId();

            //先将原来的图片删掉
            Map<String, Object> phoMap = new HashMap<>();
            phoMap.put("estateId", estateTypeDto.getEstateId());
            phoMap.put("typeId", estateTypeDto.getTypeId());

            List<Integer> photoKbnList = new ArrayList<>();
            photoKbnList.add(DictionaryConstants.HOUSE_TYPE_IMG);
            photoKbnList.add(DictionaryConstants.HOUSE_TEMPLATE_IMG);
            if (null != photoKbnList && !photoKbnList.isEmpty())
            {
                Integer[] arr = (Integer[])photoKbnList.toArray(new Integer[photoKbnList.size()]);
                phoMap.put("photoKbnList", arr);
            }

            List<Photo> photos = photoMapper.queryList(phoMap);
            for (Photo photo : photos)
            {
                this.photoMapper.deleteById(photo.getId());
            }
        }

        // 图片文件列表
        List<PhotoDto> photoDtos = estateInfoDto.getFilePhoto();

        if (null != photoDtos && !photoDtos.isEmpty())
        {
            String photoId = null;
            String[] attach = null;

            for (PhotoDto photoDto : photoDtos)
            {
                Photo photo = new Photo();
                BeanUtils.copyProperties(photoDto, photo);
                handlePhotoId(photo);

                this.photoMapper.create(photo);
            }
        }

        //户型字段 接口查询用
        Map<String, Object> estateMap = new HashMap<>();
        if (StringUtil.isNotEmpty(estateId))
        {
            estateMap.put("estateId", estateId);
            List<Estate> estates = this.estateMapper.queryList(estateMap);
            if (null != estates && !estates.isEmpty())
            {
                Estate estate = estates.get(0);
                String estateTypeSearch = "";
                Map<String, Object> typeMap = new HashMap<>();
                typeMap.put("estateId", estate.getEstateId());
                List<EstateType> estateTypes = this.estateTypeMapper.queryList(typeMap);
                for (EstateType estateType : estateTypes)
                {
                    if (StringUtil.isNotEmpty(estateType.getCountF()))
                    {
                        if (StringUtil.isEmpty(estateTypeSearch))
                        {
                            estateTypeSearch = estateType.getTypeId() + ":" + estateType.getCountF();
                        }
                        else
                        {
                            estateTypeSearch =
                                estateTypeSearch + "," + estateType.getTypeId() + ":" + estateType.getCountF();
                        }
                    }
                }
                Estate estateNew = new Estate();
                estateNew.setId(estate.getId());
                estateNew.setEstateTypeSearch(estateTypeSearch);
                this.estateMapper.update(estateNew);
            }
        }
    }

    /**
    * @Title: delEstateType
    * @Description: 删除在售户型
    * @param id
    * @param updateId
    * @return
    */
    public int delEstateType(int id, int updateId)
        throws Exception
    {
        EstateType estateType = this.estateTypeMapper.getById(id);
        String estateId = "";
        if (null != estateType)
        {
            estateId = estateType.getEstateId();
        }
        int count = estateTypeMapper.deleteById(id, updateId);
        if (count > 0)
        {
            //户型字段 接口查询用
            Map<String, Object> estateMap = new HashMap<>();
            if (StringUtil.isNotEmpty(estateId))
            {
                estateMap.put("estateId", estateId);
                List<Estate> estates = this.estateMapper.queryList(estateMap);
                if (null != estates && !estates.isEmpty())
                {
                    Estate estate = estates.get(0);
                    String estateTypeSearch = "";
                    Map<String, Object> typeMap = new HashMap<>();
                    typeMap.put("estateId", estate.getEstateId());
                    List<EstateType> estateTypes = this.estateTypeMapper.queryList(typeMap);
                    for (EstateType item : estateTypes)
                    {
                        if (StringUtil.isNotEmpty(item.getCountF()))
                        {
                            if (StringUtil.isEmpty(estateTypeSearch))
                            {
                                estateTypeSearch = item.getTypeId() + ":" + item.getCountF();
                            }
                            else
                            {
                                estateTypeSearch = estateTypeSearch + "," + item.getTypeId() + ":" + item.getCountF();
                            }
                        }
                    }
                    Estate estateNew = new Estate();
                    estateNew.setId(estate.getId());
                    estateNew.setEstateTypeSearch(estateTypeSearch);
                    this.estateMapper.update(estateNew);
                }
            }
        }
        return count;
    }

    /**
    * @Title: getHouseLinkUserList
    * @Description: 获取新房联动的案场负责人
    * @param cityNo
    * @return
    */
    public ResultData<List<UserDto>> getHouseLinkUserList(String cityNo)
        throws Exception
    {
        ResultData<List<UserDto>> resultData = new ResultData<>();
        List<UserDto> userDtos = new ArrayList<>();
        List<User> userList = this.userMapper.getHouseLinkUserList(cityNo);
        for (User user : userList)
        {
            UserDto userDto = new UserDto();
            userDto.setUserId(user.getUserId());
            userDto.setUserCode(user.getUserCode());
            userDto.setUserName(user.getUserName());
            userDto.setGroupName(user.getGroupName());
            userDto.setCenterId(user.getCenterId());
            userDto.setCenterName(user.getCenterName());
            userDtos.add(userDto);
        }
        if (null != userDtos && !userDtos.isEmpty())
        {
            resultData.setReturnData(userDtos);
            resultData.setTotalCount(userDtos.size() + "");
        }
        return resultData;
    }
    public ResultData<List<UserDto>> getHouseLinkUserList2(Map<?, ?> param)
    		throws Exception
    {
    	ResultData<List<UserDto>> resultData = new ResultData<>();
    	List<UserDto> userDtos = new ArrayList<>();
    	List<User> userList = this.userMapper.getHouseLinkUserList2(param);
    	for (User user : userList)
    	{
    		UserDto userDto = new UserDto();
    		userDto.setUserId(user.getUserId());
    		userDto.setUserCode(user.getUserCode());
    		userDto.setUserName(user.getUserName());
    		userDto.setGroupName(user.getGroupName());
    		userDto.setCenterId(user.getCenterId());
    		userDto.setCenterName(user.getCenterName());
    		userDto.setCellphone(user.getCellphone());
    		userDtos.add(userDto);
    	}
    	if (null != userDtos && !userDtos.isEmpty())
    	{
    		resultData.setReturnData(userDtos);
    		//resultData.setTotalCount(userDtos.size() + "");
    		resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
    	}
    	return resultData;
    }

    /*******************************private function*****************************/

    /**
     * 对象转换MO--DTO
     *
     * @param craList
     * @return List<EstateSearchResultDto>
     */
    private List<EstateSearchResultDto> convertEstateSearchResultData(List<EstateSearchResult> craList)
        throws Exception
    {
        List<EstateSearchResultDto> estateDtoList = new ArrayList<EstateSearchResultDto>();

        if (null != craList && !craList.isEmpty())
        {
            EstateSearchResultDto craDto = null;
            for (EstateSearchResult cra : craList)
            {
                craDto = new EstateSearchResultDto();
                BeanUtils.copyProperties(cra, craDto);
                estateDtoList.add(craDto);
            }
        }
        return estateDtoList;
    }

    /**
     * @Title: getNameByUserId
     * @Description: 跟进用户编号获取用户姓名
     * @param userId
     * @return
     */
    private String getNameByUserId(Integer userId)
        throws Exception
    {
        String userName = "";
        if (null != userId)
        {
            ResultData<User> userData = this.userAPIImpl.getUserById(userId);
            if (userData.getReturnCode() == ReturnCode.SUCCESS)
            {
                userName = userData.getReturnData().getUserName();
            }
        }
        return userName;
    }

    /**
    * @Title: getGroupNmByDepId
    * @Description: 根据
    * @param depId
    * @return
    */
    private String getGroupNmByDepId(Integer depId)
        throws Exception
    {
        String groupNm = "";
        if (null != depId)
        {
            Group group = this.groupMapper.selectByGroupId(depId);
            if (null != group)
            {
                groupNm = group.getGroupName();
            }
        }
        return groupNm;
    }

    /**
    * @Title: getImgPath
    * @Description: 获取图片路径
    * @param param
    * @return
    */
    private List<String> getImgPath(Map<String, Object> param)
        throws Exception
    {
        List<String> imgList = new ArrayList<>();
        List<Photo> housePhos = this.photoMapper.queryList(param);

        if (null != housePhos && !housePhos.isEmpty())
        {
            String imgPath;
            PhotoDto photoDto;

            for (Photo photo : housePhos)
            {
                imgPath = photo.getFileAbbrUrl();
                //String imgPath = FileHelper.getFilePath(photo.getPhotoId());
//
//                photoDto = new PhotoDto();
//                BeanUtils.copyProperties(photo, photoDto);
//                String fileNo = photoDto.getFileNo();
//                String photoId = handleFile(fileNo);
//                handleFile(photoDto);
//                photoDto.setPhotoId(photoId);
//                imgPath = photoDto.getPhotoPath();
//
                if (StringUtil.isNotEmpty(imgPath))
                {
                    imgList.add(imgPath);
                }
            }
        }
        return imgList;
    }

    /**
    * @Title: checkAdd
    * @Description: 验证是否可以添加
    * @param estate
    * @return
    */
    private Boolean checkAdd(Estate estate)
        throws Exception
    {
        if(StringUtil.isEmpty(estate.getCityNo())||StringUtil.isEmpty(estate.getDistrictId())
                ||StringUtil.isEmpty(estate.getAddress().trim())||StringUtil.isEmpty(estate.getPartnerNm().trim())) {
            return true;
        }
        Boolean canAdd = true;
        Map<String, Object> param = new HashMap<>();
        param.put("cityNo", estate.getCityNo());
        param.put("estateNm", estate.getEstateNm());
//        param.put("districtId", estate.getDistrictId());
//        param.put("areaId", estate.getAreaId());
//        param.put("address", estate.getAddress().trim());
        param.put("partnerNm", estate.getPartnerNm().trim());
        List<Estate> estates = this.estateMapper.queryList(param);
        if (null != estates && !estates.isEmpty())
        {
            if(estate.getId() == null) {
                canAdd = false;
            }else {
                if(estates.size() > 1) {
                    canAdd = false;
                }else {
                    if(!estates.get(0).getId().equals(estate.getId())) {
                        canAdd = false;
                    }
                }
            }
        }
        return canAdd;
    }

    /*******************************private function*****************************/

    /**
    * 接入渠道系统，处理photoId
    * @param photo
    */
    private void handlePhotoId(Photo photo)
    {
        String photoId;
        String[] attach;
        photoId = photo.getPhotoId();
        if (StringUtil.isNotEmpty(photoId))
        {

            attach = photoId.split("\\|");
            if (null != attach && 0 != attach.length)
            {
                //photo.setPhotoId(attach[0]);
                photo.setPhotoId(null);
                photo.setFileNo(attach[1]);
            }

        }
    }

    /**
    * 对文件数据进行组装处理
    * @param photoDto
    * @throws Exception
    */
    private void handleFile(PhotoDto photoDto)
        throws Exception
    {
        // 1、先从关系表中取，如果取不到 ，则通过fileNo 去渠道系统主表 拿到渠道及fileCode，去相应文件系统取出文件地址；
        // 获取文件渠道系统配置（外部文件系统的配置）
        Map<?, ?> mapTemp = getChannelConfig();

        //取关系表中的文件Code
        String fileId = photoDto.getPhotoId();

        String fileUrl = null;

        if (StringUtil.isNotEmpty(fileId))
        {
            // 查询路径
            String queryUrl = (String)mapTemp.get("CRIC_queryfile_path");
            // 下载路径
            String downloadUrl = (String)mapTemp.get("CRIC_downloadfile_path");
            // 授权号
            String permitCode = (String)mapTemp.get("CRIC_file_permit_code");
            //获取图片路径
            fileUrl = FileHelper.getFilePath(fileId, queryUrl, downloadUrl, permitCode);
        }
        else
        {
            //取出fileNo
            String fileNo = photoDto.getFileNo();

            //调用渠道系统，获取文件记录信息
            ResultData<?> reback = filesService.getByFileNo(fileNo);
            Map<?, ?> mapTemppp = (Map<?, ?>)reback.getReturnData();
            if (null != mapTemppp)
            {

                FileDto fileDto = MapToEntityConvertUtil.convert(mapTemppp, FileDto.class, "");

                //fileCode
                String fileCode = fileDto.getFileCode();
                //渠道Code
                String channelCode = fileDto.getChannelCode();
                if ("CRIC".equals(channelCode))
                {
                    // 查询路径
                    String queryUrl = (String)mapTemp.get("CRIC_queryfile_path");
                    // 下载路径
                    String downloadUrl = (String)mapTemp.get("CRIC_downloadfile_path");
                    // 授权号
                    String permitCode = (String)mapTemp.get("CRIC_file_permit_code");

                    //获取图片路径
                    //fileUrl = FileHelper.getFilePath(fileCode);

                    Map<String, Object> handleMap = new HashMap<>();
                    handleMap.put("width", "100");
                    handleMap.put("height", "100");
                    handleMap.put("waterPic", "0");// 无水印图片
                    handleMap.put("waterPosition", "0");//  3:左下角
                    handleMap.put("cutType", "0");//0-不裁剪
                    fileUrl =
                        FileHelper.getFilePath(fileCode,
                            FileAssist.UPLOAD_FILE_IS_HANDLE_YES,
                            queryUrl,
                            downloadUrl,
                            permitCode,
                            handleMap);

                }
                else if ("weiphoto".equals(channelCode))
                {

                    // 查询路径
                    String fileViewUrl = (String)mapTemp.get("wp_view_url");

                    fileUrl = WeiphotoHelper.getFilePath(fileViewUrl, fileCode, "200");

                }

            }
        }

        //获取图片路径
        photoDto.setPhotoPath(fileUrl);
    }

    /**
    * 对文件数据进行组装处理
    * @param fileNo
    * @throws Exception
    */
    private String handleFile(String fileNo)
        throws Exception
    {
        //TODO 1、先从关系表中取，如果取不到 ，则通过fileNo 去渠道系统主表 拿到渠道及fileCode，去相应文件系统取出文件地址；

        String fileId = null;

        if (StringUtil.isNotEmpty(fileNo))
        {

            //调用渠道系统，获取文件记录信息
            ResultData<?> reback = filesService.getByFileNo(fileNo);
            Map<?, ?> mapTemppp = (Map<?, ?>)reback.getReturnData();
            if (null != mapTemppp)
            {

                FileDto fileDto = MapToEntityConvertUtil.convert(mapTemppp, FileDto.class, "");

                //fileCode
                String fileCode = fileDto.getFileCode();

                fileId = fileCode + "|" + fileNo;

            }
        }

        return fileId;
    }

    /**
     * 查询归属项目部
     * @return
     * @throws Exception
     */
    public List<EstateDto> getProjectDepartment(Map<String, Object> param)
    {
            List<EstateDto> estateDtoList = new ArrayList<EstateDto>();
            try {
				estateDtoList = estateMapper.getProjectDepartment(param);
			} catch (Exception e) {
				e.printStackTrace();
			}
            return estateDtoList;
     }

    public List<Estate> queryByOpEstateId(String opEstateId) {

        return estateMapper.queryByOpEstateId(opEstateId);

    }


    public List<Map<String,String>> queryCountryList() {
        return estateMapper.queryCountryList();
    }
    /** 大客户集合
    * @return
    */
   public ResultData<List<BigCutomer>> getBigCustomerList() throws Exception {
       ResultData<List<BigCutomer>> resultData = new ResultData<List<BigCutomer>>();
       resultData.setFail();
       List<BigCutomer> bigCutomerList = estateMapper.getBigCustomerList();
       if (bigCutomerList != null && bigCutomerList.size() > 0) {
           resultData.setReturnData(bigCutomerList);
           resultData.setSuccess();
       }
       return resultData;
   }

   /** 垫佣甲方列表
    * @return
    */
   public ResultData<List<MattressNail>> getMattressNail() throws Exception {
	   ResultData<List<MattressNail>> resultData = new ResultData<List<MattressNail>>();
	   resultData.setFail();
	   List<MattressNail> mattressNailList = estateMapper.getMattressNail();
	   if (mattressNailList != null && mattressNailList.size() > 0) {
		   resultData.setReturnData(mattressNailList);
		   resultData.setSuccess();
	   }
	   return resultData;
   }

    public List<String> getEstateNmList(Map<String, Object> param) {
        return estateMapper.getEstateNmList(param);
    }
    
    /**
     * desc:项目-佣金方案列表
     * 2020年2月28日
     */
    public ResultData getYjPlanByProjectNo(Map<String, Object> param) throws Exception {
        ResultData<List<LnkYjPlanDto>> resultData = new ResultData<>();
        resultData.setFail();
        List<LnkYjPlanDto> lnkYjPlanDtoList = lnkYjPlanMapper.getYjPlanByProjectNo(param);
        // 拼接方案描述
        if(lnkYjPlanDtoList !=null && lnkYjPlanDtoList.size()>0) {
        	for (LnkYjPlanDto lnkYjPlanDto : lnkYjPlanDtoList) {
        		//1、拼接---经纪公司
        		setCompanyNameStr(lnkYjPlanDto);
        		
        		//2、拼接---描述 
        		setPlanInfoDes(lnkYjPlanDto);
        		
			}
        	resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
        }
        resultData.setReturnData(lnkYjPlanDtoList);
        resultData.setSuccess();
        return resultData;
    }

    /**
     * 
     * desc:拼接经纪公司
     * 2020年3月5日
     */
    private void setCompanyNameStr(LnkYjPlanDto lnkYjPlanDto) {
    	String companyNameStr = "";//经纪公司
		List<LnkYjPlanCompanyDto> yjPlanCompanyList = lnkYjPlanDto.getYjPlanCompanyList();
		if(yjPlanCompanyList != null && yjPlanCompanyList.size()>0) {
			for (LnkYjPlanCompanyDto lnkYjPlanCompanyDto : yjPlanCompanyList) {
				String companyName = lnkYjPlanCompanyDto.getCompanyName();
				if(StringUtil.isEmpty(companyName)) {
					continue;
				}
				companyNameStr+=companyName+ "</br>";
			}
		}
		if(!StringUtil.isEmpty(companyNameStr)) {
			lnkYjPlanDto.setCompanyNameStr(companyNameStr);
		}
	}

	/**
     * 
     * desc:拼接描述
     * 2020年3月5日
     */
    private void setPlanInfoDes(LnkYjPlanDto lnkYjPlanDto) {
    	//1>物业类型
		String planInfoDes = "<b>物业类型:</b>";
		List<LnkYjPlanWyDto> yjPlanWyList = lnkYjPlanDto.getYjPlanWyList();
		if(yjPlanWyList != null && yjPlanWyList.size()>0) {
			for (LnkYjPlanWyDto lnkYjPlanWyDto : yjPlanWyList) {
				String wyTypName = lnkYjPlanWyDto.getWyTypName();
				if(StringUtil.isEmpty(wyTypName)) {
					continue;
				}
				planInfoDes+=wyTypName+ ",";
			}
		}
		if("<b>物业类型:</b>".equals(planInfoDes)) {
			planInfoDes = planInfoDes + "</br>";
		}else {
			planInfoDes = planInfoDes.substring(0, planInfoDes.length()-1) + "</br>";
		}
		//2>期间
		planInfoDes = planInfoDes+"<b>期间:</b>" +lnkYjPlanDto.getPeriodTypeName()+"在"+ DateUtil.getDateTime("yyyy-MM-dd", lnkYjPlanDto.getPeriodBeginDate()) + " 至  "
				+ DateUtil.getDateTime("yyyy-MM-dd", lnkYjPlanDto.getPeriodEndDate())+ "</br>";
		//3>公式
		List<LnkYjPlanDetailDto> yjPlanDetailList = lnkYjPlanDto.getYjPlanDetailList();
		String equationStr = "<b>公式:</b>";
		if(yjPlanDetailList != null && yjPlanDetailList.size()>0) {
			int tCount = 1;//每套总价
			int jCount = 1;//每套总面积
			BigDecimal baseAccount = new BigDecimal("10000");
			for (LnkYjPlanDetailDto lnkYjPlanDetailDto : yjPlanDetailList) {
				String equationType = lnkYjPlanDetailDto.getEquationType();//28101每套  28102每平米 28103每套总价 28104每套总面积（㎡） 
				BigDecimal conditionBegin = lnkYjPlanDetailDto.getConditionBegin();//条件开始
				BigDecimal conditionEnd = lnkYjPlanDetailDto.getConditionEnd();//条件未端
				BigDecimal fixAmount = lnkYjPlanDetailDto.getFixAmount();//固定
				BigDecimal totalPercentage = lnkYjPlanDetailDto.getTotalPercentage();//总价
				if("28101".equals(equationType)) {//每套
					if(fixAmount == null || BigDecimal.ZERO.compareTo(fixAmount)==0) {//固定为空
						equationStr +="每套,"+"总价*"+totalPercentage.setScale(2,BigDecimal.ROUND_HALF_UP)+"%</br>";
					}else if(totalPercentage == null || BigDecimal.ZERO.compareTo(totalPercentage)==0){//总价为空
						equationStr +="每套,固定"+fixAmount+"元</br>";
					}else {
						equationStr +="每套,固定"+fixAmount+"元+总价*"+totalPercentage.setScale(2,BigDecimal.ROUND_HALF_UP)+"%</br>";
					}
				}else if("28102".equals(equationType)) {//每平米
					equationStr +="每平米,固定"+fixAmount+"元"+"</br>";
				}else if("28103".equals(equationType)) {//每套总价
					if(tCount == 1) {
						equationStr +="每套总价;</br>";
					}
					if(conditionEnd==null) {
						if(fixAmount == null || BigDecimal.ZERO.compareTo(fixAmount)==0) {//固定为空
							equationStr +="条件"+tCount+":"+"每套总价(万元)>"+conditionBegin.divide(baseAccount)+";佣金"+tCount+":"+"总价*"+totalPercentage.setScale(2,BigDecimal.ROUND_HALF_UP)+"%</br>";
						}else if(totalPercentage == null || BigDecimal.ZERO.compareTo(totalPercentage)==0){//总价为空
							equationStr +="条件"+tCount+":"+"每套总价(万元)>"+conditionBegin.divide(baseAccount)+";佣金"+tCount+":固定"+fixAmount+"元</br>";
						}else {
							equationStr +="条件"+tCount+":"+"每套总价(万元)>"+conditionBegin.divide(baseAccount)+";佣金"+tCount+":固定"+fixAmount+"元+总价*"+totalPercentage.setScale(2,BigDecimal.ROUND_HALF_UP)+"%</br>";
						}
					}else {
						if(fixAmount == null || BigDecimal.ZERO.compareTo(fixAmount)==0) {//固定为空
							equationStr +="条件"+tCount+":"+conditionBegin.divide(baseAccount)+"<每套总价(万元)<="+conditionEnd.divide(baseAccount)+";佣金"+tCount+":"+"总价*"+totalPercentage.setScale(2,BigDecimal.ROUND_HALF_UP)+"%</br>";
						}else if(totalPercentage == null || BigDecimal.ZERO.compareTo(totalPercentage)==0){//总价为空
							equationStr +="条件"+tCount+":"+conditionBegin.divide(baseAccount)+"<每套总价(万元)<="+conditionEnd.divide(baseAccount)+";佣金"+tCount+":固定"+fixAmount+"元</br>";
						}else {
							equationStr +="条件"+tCount+":"+conditionBegin.divide(baseAccount)+"<每套总价(万元)<="+conditionEnd.divide(baseAccount)+";佣金"+tCount+":固定"+fixAmount+"元+总价*"+totalPercentage.setScale(2,BigDecimal.ROUND_HALF_UP)+"%</br>";
						}
					}
					tCount++;
				}else if("28104".equals(equationType)) {//每套总面积（㎡）
					if(jCount == 1) {
						equationStr +="每套总面积（㎡）;</br>";
					}
					if(conditionEnd==null) {
						if(fixAmount == null || BigDecimal.ZERO.compareTo(fixAmount)==0) {//固定为空
							equationStr +="条件"+jCount+":"+"每套总面积(㎡)>"+conditionBegin+";佣金"+jCount+":"+"总价*"+totalPercentage.setScale(2,BigDecimal.ROUND_HALF_UP)+"%</br>";
						}else if(totalPercentage == null || BigDecimal.ZERO.compareTo(totalPercentage)==0){//总价为空
							equationStr +="条件"+jCount+":"+"每套总面积(㎡)>"+conditionBegin+";佣金"+jCount+":固定"+fixAmount+"元</br>";
						}else {
							equationStr +="条件"+jCount+":"+"每套总面积(㎡)>"+conditionBegin+";佣金"+jCount+":固定"+fixAmount+"元+总价*"+totalPercentage.setScale(2,BigDecimal.ROUND_HALF_UP)+"%</br>";
						}
					}else {
						if(fixAmount == null || BigDecimal.ZERO.compareTo(fixAmount)==0) {//固定为空
							equationStr +="条件"+jCount+":"+conditionBegin+"<每套总面积(㎡)<="+conditionEnd+";佣金"+jCount+":总价*"+totalPercentage.setScale(2,BigDecimal.ROUND_HALF_UP)+"%</br>";
						}else if(totalPercentage == null || BigDecimal.ZERO.compareTo(totalPercentage)==0){//总价为空
							equationStr +="条件"+jCount+":"+conditionBegin+"<每套总面积(㎡)<="+conditionEnd+";佣金"+jCount+":固定"+fixAmount+"元</br>";
						}else {
							equationStr +="条件"+jCount+":"+conditionBegin+"<每套总面积(㎡)<="+conditionEnd+";佣金"+jCount+":固定"+fixAmount+"元+总价*"+totalPercentage.setScale(2,BigDecimal.ROUND_HALF_UP)+"%</br>";
						}
					}
					jCount++;
				}
			}
		}
		planInfoDes += equationStr ;
		lnkYjPlanDto.setPlanInfoDes(planInfoDes);
	}

	public ResultData getProgrammeList(Map<String, Object> param) throws Exception {
        ResultData<List<LnkYjPlanDto>> resultData = new ResultData<>();
        List<LnkYjPlanDto> lnkYjPlanDtoList = new ArrayList<>();

        String reportId = param.get("reportId").toString();
        String planType = param.get("planType").toString();    // 方案类型  --28701	收入,28702	返佣

        Report report = reportMapper.getByReportId(reportId); // 订单ID
        param.put("reportDate", report.getReportDate());       // 报备日期
        param.put("seeDate", report.getSeeDate());             // 带看日期
        param.put("roughDate", report.getRoughDate());         // 大定日期
        //param.put("dealDate", report.getDealDate());           // 成销日期
        param.put("wyTypeCode", report.getWyTypeCode());       // 物业类型

        // 方案类型(planType)：28701-收入方案；28702-返佣
        if ("28701".equals(planType)) {
            lnkYjPlanDtoList = lnkYjPlanMapper.getProgrammeList(param);
        } else {
            // 返佣方案（fyPlanType): 28801-标准, 28802-特殊
            param.put("fyPlanType", "28802");
            lnkYjPlanDtoList = lnkYjPlanMapper.getProgrammeList(param);
            if (lnkYjPlanDtoList.size() == 0) {
                param.put("fyPlanType", "28801");
                param.remove("companyNo");
                lnkYjPlanDtoList = lnkYjPlanMapper.getProgrammeList(param);
            }
        }
        //拼接方案描述
        if(lnkYjPlanDtoList !=null && lnkYjPlanDtoList.size()>0) {
            for (LnkYjPlanDto lnkYjPlanDto : lnkYjPlanDtoList) {
                setPlanInfoDes(lnkYjPlanDto);
            }
        }

        // 默认增加 人工核算
        LnkYjPlanDto dto = new LnkYjPlanDto();
        dto.setId(0);
        dto.setPlanName("人工核算");
        dto.setPlanTypeName("");
        dto.setEquationType("0");
        dto.setPlanInfoDes("");

        lnkYjPlanDtoList.add(0, dto);

        resultData.setReturnData(lnkYjPlanDtoList);
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setSuccess();
        return resultData;
    }

    /**
     * desc:保存/编辑
     * 2020年3月2日
     */
    public ResultData<LnkYjPlanDto> saveYjPlanInfo(LnkYjPlanDto lnkYjPlanDto) {

        ResultData<LnkYjPlanDto> resultData = new ResultData<>();
        Map<String,Object> reqMap = new HashMap<>();
        
        LnkYjPlan lnkYjPlan = getLnkYjPlan(lnkYjPlanDto);
        String planType = lnkYjPlan.getPlanType();
        String planName = lnkYjPlan.getPlanName();
      
        boolean isEnable = lnkYjPlan.getIsEnable();
        Integer id = lnkYjPlanDto.getId();//主表id
        if(!StringUtils.isEmpty(id)) {//编辑
        	reqMap.put("editId", id);
        	LnkYjPlan lnkYjPlanEdit = lnkYjPlanMapper.selectByCondition(reqMap);
        	String isEdit = lnkYjPlanEdit.getIsEdit();
        	//1、check 编辑的时候isEdit为1才可以编辑
        	if(!"1".equals(isEdit)) {
        		resultData.setFail();
            	resultData.setReturnCode("-1");
            	resultData.setReturnMsg("该方案已启用过，不能修改");
            	return resultData;
        	}
        	//2、根据方案类型+方案名称判断是否重复
        	reqMap.put("planType", planType);
        	reqMap.put("planName", planName);
        	reqMap.put("projectNo", lnkYjPlanEdit.getProjectNo());
        	reqMap.put("editFaId", id);
        	reqMap.remove("editId");
        	LnkYjPlan lnkYjPlanAk = lnkYjPlanMapper.selectByCondition(reqMap);
        	if(lnkYjPlanAk !=null) {
        		resultData.setFail();
        		resultData.setReturnCode("-1");
        		resultData.setReturnMsg("方案名称重复，请重新填写！");
        		return resultData;
        	}
        	//3、修改主表
        	lnkYjPlan.setId(id);
        	lnkYjPlan.setUpdateDate(new Date());
        	lnkYjPlan.setUserIdUpdate(lnkYjPlanDto.getUserIdUpdate());
        	int updateCount = lnkYjPlanMapper.updateByPrimaryKeySelective(lnkYjPlan);
        	if(updateCount <= 0) {
        		resultData.setFail();
        		resultData.setReturnCode("-1");
        		resultData.setReturnMsg("修改失败！");
        		return resultData;
        	}
        	//4、根据planId删除明细表，在新增数据
        	reqMap.put("planId", id);
        	lnkYjPlanDetailMapper.updateByPlanId(reqMap);
        	insertYjPlanDetail(id,lnkYjPlanDto);
        	//5、保存物业子表
        	lnkYjWyMapper.updateByPlanId(reqMap);
        	insertYjPlanWy(id,lnkYjPlanDto);
        	//6、保存公司表
        	lnkYjPlanCompanyMapper.updateByPlanId(reqMap);
        	insertYjPlanCompany(id,lnkYjPlanDto);
        	//调用存储过程 begin
        	if(isEnable) {
        		Map<String, Object> callMap = new HashMap<>();
        		logger.info("佣金方案，编辑(启用禁用),调用存储过程begin，入参id:"+id);
        		callMap.put("id", id);
        		lnkYjPlanMapper.callYjPlan(callMap);
        		logger.info("佣金方案，编辑(启用禁用),调用存储过程end，入参id:"+id);
        	}
        	//end
        }else {//新增保存
        	lnkYjPlan.setCreateDate(new Date());
        	lnkYjPlan.setUserIdCreate(lnkYjPlanDto.getUserIdCreate());
        	//1、根据方案类型+方案名称判断是否重复
        	reqMap.put("planType", planType);
        	reqMap.put("planName", planName);
        	reqMap.put("projectNo", lnkYjPlan.getProjectNo());
        	LnkYjPlan lnkYjPlanAk = lnkYjPlanMapper.selectByCondition(reqMap);
        	if(lnkYjPlanAk !=null) {
        		resultData.setFail();
        		resultData.setReturnCode("-1");
        		resultData.setReturnMsg("方案名称重复，请重新填写！");
        		return resultData;
        	}
        	//2、保存主表
        	int planCount = lnkYjPlanMapper.insertSelective(lnkYjPlan);
        	if(planCount <= 0) {
        		resultData.setFail();
        		resultData.setReturnCode("-1");
        		resultData.setReturnMsg("保存失败！");
        		return resultData;
        	}
        	Integer planId = lnkYjPlan.getId();
        	//3、保存明细表
        	insertYjPlanDetail(planId,lnkYjPlanDto);
        	//4、保存物业子表
        	insertYjPlanWy(planId,lnkYjPlanDto);
        	//5、保存公司表
        	insertYjPlanCompany(planId,lnkYjPlanDto);
        	//调用存储过程 begin
        	if(isEnable) {
        		Map<String, Object> callMap = new HashMap<>();
        		logger.info("佣金方案，新增(启用禁用),调用存储过程begin，入参id:"+planId);
        		callMap.put("id", planId);
        		lnkYjPlanMapper.callYjPlan(callMap);
        		logger.info("佣金方案，新增(启用禁用),调用存储过程end，入参id:"+planId);
        	}
        	//end
        }

        resultData.setSuccess();
        return resultData;
    }

    /**
     * desc:新增公司
     * 2020年3月3日
     */
    private void insertYjPlanCompany(Integer planId, LnkYjPlanDto lnkYjPlanDto) {
    	List<LnkYjPlanCompanyDto> yjPlanCompanyList = lnkYjPlanDto.getYjPlanCompanyList();
    	if(yjPlanCompanyList !=null && yjPlanCompanyList.size()>0) {
    		for (LnkYjPlanCompanyDto lnkYjPlanCompanyDto : yjPlanCompanyList) {
    			LnkYjPlanCompany  lnkYjPlanCompany = new LnkYjPlanCompany();
    			BeanUtils.copyProperties(lnkYjPlanCompanyDto, lnkYjPlanCompany);
    			lnkYjPlanCompany.setCreateDate(new Date());
    			lnkYjPlanCompany.setDelFlag(false);
    			lnkYjPlanCompany.setUserIdCreate(lnkYjPlanDto.getUserIdCreate());
    			lnkYjPlanCompany.setPlanId(planId);
    			lnkYjPlanCompanyMapper.insertSelective(lnkYjPlanCompany);
			}
    	}
	}

	/**
     * 
     * desc:新增物业子表
     * 2020年3月3日
     */
    private void insertYjPlanWy(Integer planId, LnkYjPlanDto lnkYjPlanDto) {
    	List<LnkYjPlanWyDto> yjPlanWyList = lnkYjPlanDto.getYjPlanWyList();
    	Map<String,Object> param = new HashMap<>();
    	if(yjPlanWyList !=null && yjPlanWyList.size()>0) {
    		for (LnkYjPlanWyDto lnkYjPlanWyDto : yjPlanWyList) {
    			LnkYjWy  lnkYjPlanWy = new LnkYjWy();
    			param.put("wyTypeCode", lnkYjPlanWyDto.getWyTypeCode());
    			LnkYjWyInfo lnkYjWyInfo = lnkYjWyInfoMapper.getWyTpyeNameByCode(param);
    			BeanUtils.copyProperties(lnkYjPlanWyDto, lnkYjPlanWy);
    			if(lnkYjWyInfo !=null) {
    				lnkYjPlanWy.setWyTypName(lnkYjWyInfo.getWyTypName());
    			}
    			lnkYjPlanWy.setCreateDate(new Date());
    			lnkYjPlanWy.setDelFlag(false);
    			lnkYjPlanWy.setUserIdCreate(lnkYjPlanDto.getUserIdCreate());
    			lnkYjPlanWy.setPlanId(planId);
    			lnkYjWyMapper.insertSelective(lnkYjPlanWy);
			}
    	}
	}

	/**
     * desc:新增明细表
     * 2020年3月3日
     */
    private void insertYjPlanDetail(Integer planId,LnkYjPlanDto lnkYjPlanDto) {
    	List<LnkYjPlanDetailDto> yjPlanDetailList = lnkYjPlanDto.getYjPlanDetailList();
    	String equationType = lnkYjPlanDto.getEquationType();
    	BigDecimal baseAccount = new BigDecimal("10000");
    	if(yjPlanDetailList !=null && yjPlanDetailList.size()>0) {
    		for (LnkYjPlanDetailDto lnkYjPlanDetailDto : yjPlanDetailList) {
    			
    			BigDecimal fixAmount = lnkYjPlanDetailDto.getFixAmount();//固定
    			if(fixAmount != null) {
    				fixAmount = fixAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
    			}
    			BigDecimal totalPercentage = lnkYjPlanDetailDto.getTotalPercentage();//总价
    			if(totalPercentage != null) {
    				totalPercentage = totalPercentage.setScale(2,BigDecimal.ROUND_HALF_UP);
    			}
    			BigDecimal conditionBegin = lnkYjPlanDetailDto.getConditionBegin();//条件开始
    			BigDecimal conditionEnd =lnkYjPlanDetailDto.getConditionEnd();//条件结束
    			if(conditionBegin !=null) {
    				conditionBegin = conditionBegin.setScale(2,BigDecimal.ROUND_HALF_UP);
    				//equationType为28103  乘以10000保留2位小数
    				if("28103".equals(equationType)) {
    					conditionBegin = conditionBegin.multiply(baseAccount);
    				}
    			}
    			if(conditionEnd !=null) {
    				conditionEnd = conditionEnd.setScale(2,BigDecimal.ROUND_HALF_UP);
    				//equationType为28103  乘以10000保留2位小数
    				if("28103".equals(equationType)) {
    					conditionEnd = conditionEnd.multiply(baseAccount);
    				}
    			}
    			LnkYjPlanDetail  lnkYjPlanDetail = new LnkYjPlanDetail();
    			BeanUtils.copyProperties(lnkYjPlanDetailDto, lnkYjPlanDetail);
    			lnkYjPlanDetail.setCreateDate(new Date());
    			lnkYjPlanDetail.setDelFlag(false);
    			lnkYjPlanDetail.setUserIdCreate(lnkYjPlanDto.getUserIdCreate());
    			lnkYjPlanDetail.setEquationType(lnkYjPlanDto.getEquationType());
    			lnkYjPlanDetail.setPlanId(planId);
    			lnkYjPlanDetail.setFixAmount(fixAmount);
    			lnkYjPlanDetail.setTotalPercentage(totalPercentage);
    			lnkYjPlanDetail.setConditionBegin(conditionBegin);
    			lnkYjPlanDetail.setConditionEnd(conditionEnd);
    			lnkYjPlanDetailMapper.insertSelective(lnkYjPlanDetail);
			}
    	}
	}

	/**
     * desc:组装佣金主表
     * 2020年3月2日
     */
	private LnkYjPlan getLnkYjPlan(LnkYjPlanDto lnkYjPlanDto) {
		LnkYjPlan lnkYjPlan = new LnkYjPlan();
//		lnkYjPlan.setCreateDate(new Date());
		lnkYjPlan.setDelFlag(false);
		lnkYjPlan.setEquationType(lnkYjPlanDto.getEquationType());
		lnkYjPlan.setFyPlanType(lnkYjPlanDto.getFyPlanType());
		lnkYjPlan.setIsEnable(lnkYjPlanDto.getIsEnable());
		lnkYjPlan.setPeriodBeginDate(lnkYjPlanDto.getPeriodBeginDate());
		lnkYjPlan.setPeriodEndDate(lnkYjPlanDto.getPeriodEndDate());
		lnkYjPlan.setPeriodType(lnkYjPlanDto.getPeriodType());
		lnkYjPlan.setPlanName(lnkYjPlanDto.getPlanName());
		lnkYjPlan.setPlanType(lnkYjPlanDto.getPlanType());
		lnkYjPlan.setProjectNo(lnkYjPlanDto.getProjectNo());
		lnkYjPlan.setIsEdit(lnkYjPlanDto.getIsEdit());
//		lnkYjPlan.setUpdateDate(new Date());
//		lnkYjPlan.setUserIdCreate(lnkYjPlanDto.getUserIdCreate());
//		lnkYjPlan.setUserIdUpdate(lnkYjPlanDto.getUserIdUpdate());
		return lnkYjPlan;
	}


    /**
     * desc:详情信息
     * 2020年3月4日
     */
    public ResultData<LnkYjPlanDto> getLnkYjPlanDto(Integer planId) {
        ResultData<LnkYjPlanDto> resultData = new ResultData<>();
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("planId",planId);
        List<LnkYjPlanDto> list = lnkYjPlanMapper.getYjPlanByProjectNo(reqMap);
        LnkYjPlanDto lnkYjPlanDto = new LnkYjPlanDto();
        if(!CollectionUtils.isEmpty(list)){
            lnkYjPlanDto = list.get(0);

            BigDecimal baseAccount = new BigDecimal("10000");
            for (LnkYjPlanDetailDto lnkYjPlanDetailDto : lnkYjPlanDto.getYjPlanDetailList()) {
                BigDecimal conditionBegin = lnkYjPlanDetailDto.getConditionBegin();
                BigDecimal conditionEnd =lnkYjPlanDetailDto.getConditionEnd();
                String equationType = lnkYjPlanDetailDto.getEquationType();
                if("28103".equals(equationType)) {
                    if(conditionBegin !=null) {
                        conditionBegin = conditionBegin.divide(baseAccount).setScale(2,BigDecimal.ROUND_HALF_UP);
                        lnkYjPlanDetailDto.setConditionBegin(conditionBegin);
                    }
                    if(conditionEnd !=null) {
                        conditionEnd = conditionEnd.divide(baseAccount).setScale(2,BigDecimal.ROUND_HALF_UP);
                        lnkYjPlanDetailDto.setConditionEnd(conditionEnd);
                    }
                }
            }

        }
        resultData.setReturnData(lnkYjPlanDto);
        resultData.setSuccess();
        return resultData;
    }
    public ResultData<Estate> getLastProjectByCity(Map<String, Object> param) throws Exception {

        ResultData<Estate> resultData = new ResultData<Estate>();
        Estate info  = estateMapper.getLastProjectByCity(param);
        resultData.setReturnData(info);
        return resultData;


    }
    
    
    /**
     * desc:项目-佣金方案日志列表
     * 2020年2月28日
     */
    public ResultData queryChangeLogList(Map<String, Object> param) throws Exception {
    	ResultData<List<EstateChangeDto>> resultData = new ResultData<>();
    	List<EstateChangeDto> estateChangeDtoList = new ArrayList<>();
        List<EstateChange> estateChangeList = this.estateChangeMapper.getByEstateId(param);
        if (null != estateChangeList && estateChangeList.size()>0){
            for (int i=0;i<estateChangeList.size();i++){
            	EstateChange ec = estateChangeList.get(i);
            	EstateChangeDto ecDto = new EstateChangeDto();
            	BeanUtils.copyProperties(ec, ecDto);
            	ecDto.setOrderId(i+1);
            	estateChangeDtoList.add(ecDto);
            }
        }
        resultData.setReturnData(estateChangeDtoList);
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setSuccess();
        return resultData;
    }
    

    /**
     * desc:项目负责人列表
     * 2020年3月23日
     */
    public ResultData<List<UserDto>> getProjectLeaderSelList(Map<?, ?> param)
    		throws Exception
    {
    	ResultData<List<UserDto>> resultData = new ResultData<>();
    	List<UserDto> userDtos = new ArrayList<>();
    	List<User> userList = this.userMapper.getProjectLeaderList(param);
    	for (User user : userList)
    	{
    		UserDto userDto = new UserDto();
    		userDto.setUserId(user.getUserId());
    		userDto.setUserCode(user.getUserCode());
    		userDto.setUserName(user.getUserName());
    		userDto.setGroupName(user.getGroupName());
    		userDto.setCenterId(user.getCenterId());
    		userDto.setCenterName(user.getCenterName());
    		userDto.setCellphone(user.getCellphone());
    		userDtos.add(userDto);
    	}
    	if (null != userDtos && !userDtos.isEmpty())
    	{
    		resultData.setReturnData(userDtos);
    		resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
    	}
    	return resultData;
    }
    
    /**
     * desc:项目-佣金方案垫佣列表
     * 2020年2月28日
     */
    public ResultData queryMattressControlRuleList(Map<String, Object> param) throws Exception {
    	ResultData<List<MattressControlRuleDetailDto>> resultData = new ResultData<>();
        List<MattressControlRuleDetailDto> mattressControlRuleList = estateChangeMapper.getByRuleListByProjectNo(param);
        resultData.setReturnData(mattressControlRuleList);
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setSuccess();
        return resultData;
    }

    /**
     * @Description: 根据Id获取楼盘基本信息
     * @param id
     * @return Estate
     */
    public Estate getEstateById(int id)
            throws Exception{
        Estate estate = this.estateMapper.getById(id);
        return estate;
    }



    /**
     * 项目合作意向状态变更
     * @param estateInfoDto
     * @return ResultData<String>
     */
    public ResultData<String> uptCooperationStatus(EstateInfoDto estateInfoDto) throws Exception{
        ResultData<String> returnData = new ResultData<>();

        EstateDto estateDto = estateInfoDto.getEstate();
        Integer id = estateDto.getId();
        String cooperationStatus = estateDto.getCooperationStatus();

        if(id==null || StringUtil.isEmpty(cooperationStatus)){
            returnData.setFail("变更失败，请刷新页面重试");
            return returnData;
        }

        Estate estateDb = this.estateMapper.getById(id);
        if(estateDb==null){
            returnData.setFail("变更失败，请刷新页面重试");
            return returnData;
        }

        if(cooperationStatus.equals(estateDb.getCooperationStatus())){
            returnData.setFail("重复操作,请刷新页面查看");
            return returnData;
        }

        if(estateDb.getProjectStatus().intValue()!=20301){
            returnData.setFail("项目状态发生变化，变更失败");
            return returnData;
        }



        Estate estate = new Estate();
        estate.setId(id);
        estate.setCooperationStatus(cooperationStatus);
        estate.setIsSpecialProject(estateDb.getIsSpecialProject());
        int count = this.estateMapper.update(estate);


        // TODO 如果有变更操作对象，则记录变更操作
        if (!CollectionUtils.isEmpty(estateInfoDto.getEstateChangeDetails())){
            for (EstateChangeDto estateChangeDto:estateInfoDto.getEstateChangeDetails()) {
                EstateChange estateChange = new EstateChange();
                BeanUtils.copyProperties(estateChangeDto, estateChange);
                this.estateChangeMapper.create(estateChange);
            }
        }
        returnData.setSuccess("更新成功");
        return returnData;
    }
}