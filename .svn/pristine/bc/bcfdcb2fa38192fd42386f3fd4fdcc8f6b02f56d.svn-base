package cn.com.eju.deal.Report.service;

import cn.com.eju.deal.Report.dao.StorePreserveMapper;
import cn.com.eju.deal.Report.model.ExpandDetail;
import cn.com.eju.deal.Report.model.StorePreserveSummary;
import cn.com.eju.deal.Report.model.StorePreserveSummaryInfo;
import cn.com.eju.deal.Report.model.UserCenterAuthDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xuliangliang
 * @Date: 2018\10\24 0024 09:07
 * @Description:
 */
@Service("storePreserveService")
public class StorePreserveServiceImpl {
    @Resource
    StorePreserveMapper storePreserveMapper;

    public List<ExpandDetail> queryStorePreserve(Map<String, Object> map) {
        List<ExpandDetail> list =null;
        try {

            list = storePreserveMapper.searchStorePreserve(map);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }


    /**
     * 根据userId查询城市
     * @param map
     * @return
     */
    public  List<UserCenterAuthDto> getUserCenterAuthCityName(Map<String, Object> map){
        List<UserCenterAuthDto> list =null;
        try {
            list = storePreserveMapper.getUserCenterAuthCityName(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据userId城市Id查询区/事业部
     * @param map
     * @return
     */
    public  List<UserCenterAuthDto> getAreaGroupName(Map<String, Object> map)
    {
        List<UserCenterAuthDto> list =null;
        try {
            list = storePreserveMapper.getAreaGroupName(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /**
     *  根据登录人ID,城市ID，对应区/事业部ID 拿到对应部门/中心
     * @param map
     * @return
     */
    public  List<UserCenterAuthDto> getCenterGroupName(Map<String, Object> map)
    {
        List<UserCenterAuthDto> list =null;
        try {
            list = storePreserveMapper.getCenterGroupName(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据登录人ID,城市ID，对应区/事业部ID 对应部门/中心ID查询组
     * @param map
     * @return
     */
    public  List<UserCenterAuthDto> getGroupName(Map<String, Object> map)
    {
        List<UserCenterAuthDto> list =null;
        try {
            list = storePreserveMapper.getGroupName(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public List<StorePreserveSummary> queryStorePreserveSummary(Map<String, Object> map) {
        List<StorePreserveSummary> list =null;
        try {
            System.out.println(map.get("personal") == null);
            if (map.get("personal") == null || map.get("personal").equals("")){
                list = storePreserveMapper.searchStorePreserveSummary(map);
            }else {
                list = storePreserveMapper.searchStorePreserveSummaryByPersonal(map);
            }
            if (list.size() > 0){
                list = newSummary(list);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    public List<StorePreserveSummary> newSummary(List<StorePreserveSummary> list){
        List<StorePreserveSummary> smList = new ArrayList<>();
        List<StorePreserveSummaryInfo> infoList = new ArrayList<>();
        StorePreserveSummary summary = new StorePreserveSummary();
        String code = list.get(0).getCode();
        for (StorePreserveSummary sps: list) {
            StorePreserveSummaryInfo info = new StorePreserveSummaryInfo();
            if (code.equals(sps.getCode())){
                summary.setCode(sps.getCode());
                summary.setName(sps.getName());
                summary.setOrgType(sps.getOrgType());
                info.setAccountCode(sps.getAccountCode());
                info.setAccountName(sps.getAccountName());
                info.setQ1(sps.getQ1());
                info.setQ2(sps.getQ2());
                info.setQ3(sps.getQ3());
                info.setQ4(sps.getQ4());
                infoList.add(info);
                summary.setInfoList(infoList);
            }else {
                smList.add(summary);
                code = sps.getCode();
                summary = new StorePreserveSummary();
                infoList = new ArrayList<>();
                summary.setCode(sps.getCode());
                summary.setName(sps.getName());
                summary.setOrgType(sps.getOrgType());
                info.setAccountCode(sps.getAccountCode());
                info.setAccountName(sps.getAccountName());
                info.setQ1(sps.getQ1());
                info.setQ2(sps.getQ2());
                info.setQ3(sps.getQ3());
                info.setQ4(sps.getQ4());
                infoList.add(info);
                summary.setInfoList(infoList);
            }
        }
        smList.add(summary);

        return smList;
    }

    public List<StorePreserveSummary> queryStorePreserveSummaryList(Map<String, Object> map) {
        List<StorePreserveSummary> list =null;
        try {

            list = storePreserveMapper.exportStorePreserveSummary(map);

            if (list.size() > 0){
                list = newSummaryEx(list);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    public List<StorePreserveSummary> newSummaryEx(List<StorePreserveSummary> list){
        List<StorePreserveSummary> smList = new ArrayList<>();
        List<StorePreserveSummaryInfo> infoList = new ArrayList<>();
        StorePreserveSummary summary = new StorePreserveSummary();
        String code = list.get(0).getOrgSort();
        for (StorePreserveSummary sps: list) {
            StorePreserveSummaryInfo info = new StorePreserveSummaryInfo();
            if (code.equals(sps.getOrgSort())){
                summary.setCode(sps.getCode());
                summary.setName(sps.getName());
                summary.setOrgType(sps.getOrgType());
                info.setAccountCode(sps.getAccountCode());
                info.setAccountName(sps.getAccountName());
                info.setQ1(sps.getQ1());
                info.setQ2(sps.getQ2());
                info.setQ3(sps.getQ3());
                info.setQ4(sps.getQ4());
                infoList.add(info);
                summary.setInfoList(infoList);
            }else {
                smList.add(summary);
                code = sps.getOrgSort();
                summary = new StorePreserveSummary();
                infoList = new ArrayList<>();
                summary.setCode(sps.getCode());
                summary.setName(sps.getName());
                summary.setOrgType(sps.getOrgType());
                info.setAccountCode(sps.getAccountCode());
                info.setAccountName(sps.getAccountName());
                info.setQ1(sps.getQ1());
                info.setQ2(sps.getQ2());
                info.setQ3(sps.getQ3());
                info.setQ4(sps.getQ4());
                infoList.add(info);
                summary.setInfoList(infoList);
            }
        }
        smList.add(summary);

        return smList;
    }
}
