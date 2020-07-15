package cn.com.eju.deal.service.myCollection;

import cn.com.eju.deal.base.dto.file.FileDto;
import cn.com.eju.deal.base.file.service.FilesService;
import cn.com.eju.deal.base.file.util.FileAssist;
import cn.com.eju.deal.base.helper.FileHelper;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.WeiphotoHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.mapper.myCollection.MyCollectionMapper;
import cn.com.eju.deal.model.sweepStreets.CompanyNewDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import cn.com.eju.deal.model.sweepStreets.WXPictureDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xu on 2017/5/22.
 */
@Service("myCollectionService")
public class MyCollectionService extends BaseService{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private FileRecordMainMapper fileRecordMainMapper;
    @Resource
    private MyCollectionMapper myCollectionMapper;
    @Resource(name = "filesService")
    private FilesService filesService;

    public ResultData<List<StoreNewDto>> getStoreListData(StoreNewDto dto) throws Exception{
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        List<StoreNewDto> list=myCollectionMapper.getStoreListData(dto);
        if(null!=list && !list.isEmpty()){
            for(StoreNewDto sDto:list){
                WXPictureDto pictureDto=getImages(sDto.getStoreId());
                if(pictureDto!=null && pictureDto.getSmallPictureUrl()!=null){
                    sDto.setStorePicUrl(pictureDto.getSmallPictureUrl());
                }
            }
            resultData.setReturnData(list);
        }
        return resultData;
    }

    public ResultData addCollection(StoreNewDto dto) throws Exception {
        ResultData resultData = new ResultData();
        int result=myCollectionMapper.addCollection(dto);
        if(result<=0){
            resultData.setFail("操作失败");
            return resultData;
        }
        resultData.setSuccess();
        return resultData;
    }

    public ResultData cancelCollection(StoreNewDto dto) throws Exception {
        ResultData resultData = new ResultData();
        int result=myCollectionMapper.cancelCollection(dto);
        if(result<=0){
            resultData.setFail("操作失败");
            return resultData;
        }
        resultData.setSuccess();
        return resultData;
    }

    //获取门店图片
    public WXPictureDto getImages(Integer storeId){
        WXPictureDto pictureDto =new WXPictureDto();
        try {
            List<FileRecordMain> fileList = fileRecordMainMapper.getByStoreId(storeId);
            if (null != fileList && !fileList.isEmpty())
            {
                FileRecordMain fileRecordMain = fileList.get(fileList.size()-1);
                StoreDto storeDto=new StoreDto();
                handleFileRecordMain(storeDto, fileRecordMain);

                pictureDto.setSmallPictureUrl(storeDto.getFileAbbrUrl());
                pictureDto.setMiddlePictureUrl(storeDto.getFileAbbrUrl());
                pictureDto.setBigPictureUrl(storeDto.getFileUrl());
            }
        }catch(Exception e){
            logger.error("myCollectionService", "myCollectionService", "getImages", "", null, "", "获取门店图片", e);
        }

        return pictureDto;
    }
    /**
     * 对文件数据进行组装处理
     * @param storeDto
     * @param fileRecordMain
     * @throws Exception
     */
    private void handleFileRecordMain(StoreDto storeDto, FileRecordMain fileRecordMain)
            throws Exception
    {
        //获取图片路径
        storeDto.setFileAbbrUrl(fileRecordMain.getFileAbbrUrl());
        storeDto.setFileName(fileRecordMain.getFileFullName());
        storeDto.setFileRecordMainId(fileRecordMain.getFileRecordMainId().toString());
        storeDto.setFileUrl(fileRecordMain.getFileUrl());
    }

    /**
     * 对文件数据进行组装处理
     * @param storeDto
     * @param fileRecordMain
     * @throws Exception
     */
    public void handleFile(StoreDto storeDto, FileRecordMain fileRecordMain)
            throws Exception
    {
        // 1、先从关系表中取，如果取不到 ，则通过fileNo 去渠道系统主表 拿到渠道及fileCode，去相应文件系统取出文件地址；
        // 获取文件渠道系统配置（外部文件系统的配置）
        Map<?, ?> mapTemp = getChannelConfig();

        //取关系表中的文件Code
        String fileId = fileRecordMain.getFileId();

        //关系主键Id
        String fileRecordMainId = fileRecordMain.getFileRecordMainId().toString();

        if (StringUtil.isNotEmpty(fileId))
        {
            // 查询路径
            String queryUrl = (String)mapTemp.get("CRIC_queryfile_path");
            // 下载路径
            String downloadUrl = (String)mapTemp.get("CRIC_downloadfile_path");
            // 授权号
            String permitCode = (String)mapTemp.get("CRIC_file_permit_code");

            //获取图片路径
            String fileUrl = FileHelper.getFilePath(fileId, queryUrl, downloadUrl, permitCode);
            Map<String, Object> handleMap = new HashMap<>();
            handleMap.put("width", "100");
            handleMap.put("height", "100");
            handleMap.put("waterPic", "0");// 无水印图片
            handleMap.put("waterPosition", "0");//  3:左下角
            handleMap.put("cutType", "0");//0-不裁剪
            String fileAbbrUrl =
                    FileHelper.getFilePath(fileRecordMain.getFileId(),
                            FileAssist.UPLOAD_FILE_IS_HANDLE_YES,
                            queryUrl,
                            downloadUrl,
                            permitCode,
                            handleMap);
            storeDto.setFileAbbrUrl(fileAbbrUrl);
            storeDto.setFileName(fileRecordMain.getFileFullName());
            storeDto.setFileRecordMainId(fileRecordMainId);
            storeDto.setFileUrl(fileUrl);
        }
        else
        {
            //取出fileNo
            String fileNo = fileRecordMain.getFileNo();

            //调用渠道系统，获取文件记录信息
            ResultData<?> reback = filesService.getByFileNo(fileNo);
            Map<?, ?> mapTemppp = (Map<?, ?>)reback.getReturnData();
            if (null != mapTemppp)
            {
                String fileAbbrUrl = null;
                String fileUrl = null;

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
                    fileUrl = FileHelper.getFilePath(fileCode, queryUrl, downloadUrl, permitCode);
                    Map<String, Object> handleMap = new HashMap<>();
                    handleMap.put("width", "100");
                    handleMap.put("height", "100");
                    handleMap.put("waterPic", "0");// 无水印图片
                    handleMap.put("waterPosition", "0");//  3:左下角
                    handleMap.put("cutType", "0");//0-不裁剪
                    fileAbbrUrl =
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

                    fileAbbrUrl = WeiphotoHelper.getFilePath(fileViewUrl, fileCode, "200");
                    fileUrl = WeiphotoHelper.getFilePath(fileViewUrl, fileCode, "5000");
                }

                //设值
                storeDto.setFileAbbrUrl(fileAbbrUrl);//缩略图地址
                storeDto.setFileRecordMainId(fileRecordMainId);
                storeDto.setFileUrl(fileUrl);//原图地址
                storeDto.setFileNo(fileNo);//渠道系统关系

            }
        }
    }
}
