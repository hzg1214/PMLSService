/**
 * @Title: FollowService.java
 * @Package cn.com.eju.deal.follow.service
 * @Description: 跟进Service包
 * @author 陆海丹
 * @date 2016年3月24日 下午12:13:40
 * @version V1.0
 */
package cn.com.eju.deal.performanceQuery.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.dto.performanceQuery.PerformanceQueryDto;
import cn.com.eju.deal.performanceQuery.dao.PerformanceQueryMapper;

@Service("performanceQueryService")
public class PerformanceQueryService {

    @Resource
    private PerformanceQueryMapper performanceQueryMapper;


    public List<PerformanceQueryDto> queryPerformanceList(Map<?, ?> param){
        //查询
        List<PerformanceQueryDto> list = null;
        // 转换
        try {

            list = performanceQueryMapper.queryPerformanceList(param);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

}
