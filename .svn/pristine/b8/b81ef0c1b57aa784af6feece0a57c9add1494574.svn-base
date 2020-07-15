
package cn.com.eju.deal.houseLinkage.estate.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateMapper;

/** 
* @ClassName: EstateReleaseJob 
* @Description: 每**执行一次预约发布
* @author 陆海丹 
* @date 2016年5月16日 上午10:09:31 
*  
*/
public class EstateReleaseJob
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource
    private EstateMapper estateMapper;
    
    /** 
    * @Title: doRelease 
    * @Description: 每**执行一次 发布操作
    */
    public void doRelease(){
        String releaseDate="";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        Date date=new Date();
        releaseDate=sdf.format(date);  
        try
        {
            this.estateMapper.appointmentRelease(releaseDate);
        }
        catch (Exception e)
        {
            logger.error("estate", "EstateReleaseJob", "doRelease", "", null, "", "", e);
        }
    }
}
