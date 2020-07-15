package cn.com.eju.deal.common.util;

import java.util.Map;

public class BuildingNoUtil {
    public static Map<String,Object> unparse(Map<String,Object> reqMap){
        if(reqMap.get("buildingNo") != null){
            String buildingNo = reqMap.get("buildingNo").toString();
            if(buildingNo.indexOf("%2F") != -1){
                buildingNo = buildingNo.replaceAll("%2F","/");
                reqMap.put("buildingNo",buildingNo);
            }
        }

        if(reqMap.get("oldBuildingNo") != null){
            String oldBuildingNo = reqMap.get("oldBuildingNo").toString();
            if(oldBuildingNo.indexOf("%2F") != -1){
                oldBuildingNo = oldBuildingNo.replaceAll("%2F","/");
                reqMap.put("oldBuildingNo",oldBuildingNo);
            }
        }

        return reqMap;
    }
}
