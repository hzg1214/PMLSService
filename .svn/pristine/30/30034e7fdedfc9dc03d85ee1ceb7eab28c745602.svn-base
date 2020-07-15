package cn.com.eju.deal.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.common.FileRecordMainDto;

/** 
* @ClassName: FileRecordMainService 
* @Description: 文件Service
* @author 陆海丹
* @date 2016年3月25日 下午2:51:23 
*/
@Service("fileService")
public class FileRecordMainService
{
    @Resource
    private FileRecordMainMapper fileRecordMainMapper;
    
    /** 
    * @Title: getById 
    * @Description: 根据编号查询文件详细信息
    * @param id
    * @return     
    */
    public FileRecordMainDto getById(int id) throws Exception
    {
        FileRecordMainDto fileRecordMainDto = new FileRecordMainDto();
        FileRecordMain fileRecordMain = this.fileRecordMainMapper.getById(id);
        //Model转换Dto
        BeanUtils.copyProperties(fileRecordMain, fileRecordMainDto);
        return fileRecordMainDto;
    }
    
    /** 
    * @Title: getFileRecordMainStrById 
    * @Description: 根据编号查询文件详情（供接口用）
    * @param id
    * @return ResultData
    */
    public ResultData<FileRecordMainDto> getStrById(int id) throws Exception
    {
        //构建返回
        ResultData<FileRecordMainDto> resultData = new ResultData<FileRecordMainDto>();
        //查询详情
        FileRecordMainDto fileRecordMainDto=this.getById(id);
        if(null!=fileRecordMainDto)
            resultData.setReturnData(fileRecordMainDto);
        else {
            resultData.setFail();
        }
        return resultData;
    }
    
    /** 
    * @Title: queryList 
    * @Description: 查询文件列表
    * @param param
    * @return List<FileRecordMain>   
    */
    public List<FileRecordMainDto> queryList(Map<?, ?> param) throws Exception
    {
        List<FileRecordMain> lstFileRecordMains = this.fileRecordMainMapper.queryList(param);
        List<FileRecordMainDto> lstFileRecordMainDtos = this.convertListData(lstFileRecordMains);
        return lstFileRecordMainDtos;
    }
    
    /** 
    * @Title: queryStrList 
    * @Description: 查询文件列表（供接口调用）
    * @param param
    * @return ResultData<List<FileRecordMainDto>>
    */
    public ResultData<List<FileRecordMainDto>> queryStrList(Map<?, ?> param) throws Exception
    {
        //构建返回
        ResultData<List<FileRecordMainDto>> resultData = new ResultData<List<FileRecordMainDto>>();
        List<FileRecordMainDto> lstFileRecordMainDtos = this.queryList(param);
        if(null!=lstFileRecordMainDtos && !lstFileRecordMainDtos.isEmpty()){
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(lstFileRecordMainDtos);
        }else {
            resultData.setFail();
        }
        return resultData;
    }
    
    /** 
    * @Title: create 
    * @Description: 新增文件
    * @param fileRecordMain
    * @return     
    */
    public int create(FileRecordMain fileRecordMain) throws Exception
    {
        int count = this.fileRecordMainMapper.create(fileRecordMain);
        return count;
    }
    
    /** 
    * @Title: createStr 
    * @Description: 新增文件（供接口调用）
    * @param fileRecordMainDto
    * @return     
    */
    public ResultData<FileRecordMainDto> createStr(FileRecordMainDto fileRecordMainDto) throws Exception
    {
        //构建返回
        ResultData<FileRecordMainDto> resultData = new ResultData<FileRecordMainDto>();
        FileRecordMain fileRecordMain = new FileRecordMain();
        //赋值
        BeanUtils.copyProperties(fileRecordMainDto, fileRecordMain);
        int count = this.create(fileRecordMain);
        if (count <= 0)
        {
            resultData.setFail();
        }
        else
        {
          //赋值回来
            BeanUtils.copyProperties(fileRecordMain,  fileRecordMainDto);
            resultData.setReturnData(fileRecordMainDto);
        }
        return resultData;
    }
    
    /** 
    * @Title: update 
    * @Description: 更新文件信息
    * @param fileRecordMain
    * @return     
    */
    public int update(FileRecordMain fileRecordMain) throws Exception
    {
        return this.fileRecordMainMapper.update(fileRecordMain);
    }
    
    /** 
    * @Title: updateStr 
    * @Description: 更新文件信息（供接口调用）
    * @param fileRecordMainDto
    * @return ResultData<FileRecordMainDto>    
    */
    public ResultData<FileRecordMainDto> updateStr(FileRecordMainDto fileRecordMainDto) throws Exception
    {
        //构建返回
        ResultData<FileRecordMainDto> resultData = new ResultData<FileRecordMainDto>();
        FileRecordMain fileRecordMain = new FileRecordMain();
        //赋值
        BeanUtils.copyProperties(fileRecordMainDto, fileRecordMain);
        int count = this.update(fileRecordMain);
        if (count <= 0)
        {
            resultData.setFail();
        }
        else
        {
            resultData.setReturnData(fileRecordMainDto);
        }
        return resultData;
    }
    
    /** 
    * @Title: delete 
    * @Description: 删除文件
    * @param id
    * @return     
    */
    public int delete(int id) throws Exception
    {
        return this.fileRecordMainMapper.deleteById(id);
    }
    
    /** 
    * @Title: deleteStr 
    * @Description: 删除文件（供接口调用）
    * @param id
    * @return     
    */
    public ResultData<FileRecordMainDto> deleteStr(int id) throws Exception
    {
        //构建返回
        ResultData<FileRecordMainDto> resultData = new ResultData<FileRecordMainDto>();
        int count = this.delete(id);
        if (count <= 0)
        {
            resultData.setFail();
        }
        return resultData;
    }
    
    public String getImgUrlByFileId(Integer fileId) throws Exception
    {
        String imgUrl="";
        
        return imgUrl;
    }

    public ResultData<List<FileRecordMainDto>> getContractList(Map<String, Object> queryParam) {
        //构建返回
        ResultData<List<FileRecordMainDto>> resultData = new ResultData<List<FileRecordMainDto>>();
        List<FileRecordMainDto> lstFileRecordMainDtos = fileRecordMainMapper.getFilesByContractId(queryParam);
        if(null!=lstFileRecordMainDtos && !lstFileRecordMainDtos.isEmpty()){
            resultData.setReturnData(lstFileRecordMainDtos);
        }else {
            resultData.setFail();
        }
        return resultData;
    }

    public ResultData<List<FileRecordMainDto>> getContractChangeList(Map<String, Object> queryParam) {
        //构建返回
        ResultData<List<FileRecordMainDto>> resultData = new ResultData<List<FileRecordMainDto>>();
        List<FileRecordMainDto> lstFileRecordMainDtos = fileRecordMainMapper.getContractChangeList(queryParam);
        if(null!=lstFileRecordMainDtos && !lstFileRecordMainDtos.isEmpty()){
            resultData.setReturnData(lstFileRecordMainDtos);
        }else {
            resultData.setFail();
        }
        return resultData;
    }

    public ResultData<List<FileRecordMainDto>> getContractChangeNewList(Map<String, Object> queryParam) {
        //构建返回
        ResultData<List<FileRecordMainDto>> resultData = new ResultData<List<FileRecordMainDto>>();
        List<FileRecordMainDto> lstFileRecordMainDtos = fileRecordMainMapper.getContractChangeNewList(queryParam);
        if(null!=lstFileRecordMainDtos && !lstFileRecordMainDtos.isEmpty()){
            resultData.setReturnData(lstFileRecordMainDtos);
        }else {
            resultData.setFail();
        }
        return resultData;
    }

    public ResultData<List<FileRecordMainDto>> getGpContractList(Map<String, Object> queryParam) {
        //构建返回
        ResultData<List<FileRecordMainDto>> resultData = new ResultData<List<FileRecordMainDto>>();
        List<FileRecordMainDto> lstFileRecordMainDtos = fileRecordMainMapper.getFilesByGpContractId(queryParam);
        if(null!=lstFileRecordMainDtos && !lstFileRecordMainDtos.isEmpty()){
            resultData.setReturnData(lstFileRecordMainDtos);
        }else {
            resultData.setFail();
        }
        return resultData;
    }
    
    /*-----------------------------------------private function-----------------------------------------*/
    /** 
    * @Title: convertListData 
    * @Description: 将List<FileRecordMain>转换成List<FileRecordMainDto>
    * @param lstStores
    * @return List<FileRecordMainDto>    
    */
    private List<FileRecordMainDto> convertListData(List<FileRecordMain> lstFileRecordMains) throws Exception
    {
        List<FileRecordMainDto> lstFileRecordMainDtos = new ArrayList<FileRecordMainDto>();
        if (null != lstFileRecordMains && !lstFileRecordMains.isEmpty())
        {
            FileRecordMainDto fileRecordMainDto = new FileRecordMainDto();
            for (FileRecordMain fileRecordMain : lstFileRecordMains)
            {
                fileRecordMainDto = new FileRecordMainDto();
                BeanUtils.copyProperties(fileRecordMain, fileRecordMainDto);
                lstFileRecordMainDtos.add(fileRecordMainDto);
            }
        }
        return lstFileRecordMainDtos;
    }

    public void deletePartReportFile(Map<String, Object> fileRecMap) {
        fileRecordMainMapper.deletePartReportFile(fileRecMap);
    }


    
    /*-----------------------------------------private function-----------------------------------------*/
}
