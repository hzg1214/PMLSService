package cn.com.eju.deal.picture.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.picture.PictureDto;
import cn.com.eju.deal.picture.dao.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tanlang on 2017-06-12.
 */
@Service(value = "pictureService")
public class PictureService extends BaseService<PictureDto> {

    @Autowired
    private PictureMapper pictureMapper;

    public ResultData<?> getPictureUrl(String pictureRefId) throws Exception {

        ResultData<List<PictureDto>> reback = new ResultData<>();
        List<PictureDto> result = pictureMapper.getPictureUrl(pictureRefId);
        reback.setReturnData(result);

        return reback;
    }

}
