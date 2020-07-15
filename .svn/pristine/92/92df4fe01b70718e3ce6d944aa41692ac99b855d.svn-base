package cn.com.eju.deal.user.api;

import java.util.Date;
import java.util.List;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.user.model.UserOrderCount;
import cn.com.eju.deal.user.model.UserOrderDist;

/**   
* 用户订单统计接口
* @author xulong
* @date 2015年11月26日 下午2:29:19
*/
public interface IUserOrderAPI
{
    /** 
    * 根据用户和岗位查询订单统计
    * @param startDate 统计起始时间
    * @param endDate 统计结束时间（此处统计为小于该时间点）
    * @param userId 用户Id
    * @param postTypeCode 岗位类别编码
    * @return
    */
    public ResultData<UserOrderCount> getCountByUserId(Date startDate, Date endDate, int userId,
        PostTypeCode postTypeCode, int exchangeCenterId);
    
    /** 
    * 根据交易中心和岗位查询分配人员列表
    * @param exchangeCenterId 交易中心Id
    * @param postTypeCode 岗位类别编码
    * @return
    */
    public ResultData<List<UserOrderDist>> getDistByExchangeCenterId(int exchangeCenterId, PostTypeCode postTypeCode);
    
    /**   
    * 岗位类别编码
    * @author xulong
    * @date 2015年11月26日 下午3:10:21
    */
    public static enum PostTypeCode
    {
        PT_ZSJYY, PT_JYZG, PT_DKZY, PT_BZZY, PT_QZZG, PT_KFZY, PT_CW,PT_JSZY
    }
    
}
