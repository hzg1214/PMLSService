package cn.com.eju.pmls.report.linkDetail.service;

import cn.com.eju.deal.houseLinkage.linkDetail.dao.LinkDetailMapper;
import cn.com.eju.pmls.report.linkDetail.dto.LinkProjectPartATraceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("linkProjectPartATraceService")
public class LinkProjectPartATraceService {

    @Autowired
    private LinkDetailMapper linkDetailMapper;

    /**
     * 查询-list
     */
    public List<LinkProjectPartATraceDto> queryLinkProjectPartATraceList(Map<?, ?> param) throws Exception {
        //查询
        return linkDetailMapper.queryLinkProjectPartATraceList(param);
    }
}
