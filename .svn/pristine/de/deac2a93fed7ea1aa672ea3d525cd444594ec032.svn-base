package cn.com.eju.deal.service.image;

import cn.com.eju.deal.mapper.image.ImgMapper;
import cn.com.eju.deal.model.sweepStreets.WXPictureDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
@Service("imageService")
public class ImageService {

    @Resource
    private ImgMapper imgMapper;

    public String addImageList(String picStr,String usercode){
        if(picStr!=null && !"-".equals(picStr) && !"".equals(picStr)) {
            String[] picArray = picStr.split(",");
            String pRefId = UUID.randomUUID().toString();
            List<WXPictureDto> pictureList = new ArrayList<WXPictureDto>();
            for (String picString : picArray) {
                String[] picDtoArray = picString.split("\\^");
                if (picDtoArray.length > 0) {
                    WXPictureDto pictureDto = new WXPictureDto();
                    pictureDto.setPictureRefId(pRefId);
                    pictureDto.setSmallPictureUrl(picDtoArray[0]);
                    pictureDto.setMiddlePictureUrl(picDtoArray[1]);
                    pictureDto.setBigPictureUrl(picDtoArray[2]);
                    pictureDto.setCreateUser(usercode + "");
                    pictureList.add(pictureDto);
                }
            }
            //上传图片
            imgMapper.addImg(pictureList);
            return pRefId;
        }
        return null;
    }
}
