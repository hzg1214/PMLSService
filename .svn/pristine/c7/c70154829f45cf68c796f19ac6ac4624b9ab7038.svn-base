package cn.com.eju.pmls.commission.service;


import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.pmls.commission.dao.CommissionMapper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.pmls.commission.dto.CommissionImportDto;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * service层
 */
@Service("commissionService")
public class CommissionService extends BaseService {

    @Autowired
    private CommissionMapper commissionMapper;

    @Autowired
    @Qualifier("pmlsSqlSessionFactory")
    private SqlSessionFactory sqlServerSessionFactory;

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    public ResultData<?> queryCityList(Map<?, ?> param) {
        ResultData<List<CommissionImportDto>> resultData = new ResultData<>();
        List<CommissionImportDto> moList = commissionMapper.queryCityList(param);
        resultData.setReturnData(moList);

        return resultData;
    }

    public ResultData<?> queryBusinessTypeList(Map<?, ?> param) {
        ResultData<List<CommissionImportDto>> resultData = new ResultData<>();
        List<CommissionImportDto> moList = commissionMapper.queryBusinessTypeList(param);
        resultData.setReturnData(moList);

        return resultData;
    }

    public ResultData<List<CommissionImportDto>> queryList(Map<?, ?> param) throws Exception {
        //构建返回
        ResultData<List<CommissionImportDto>> resultData = new ResultData<>();
        String importType = (String) param.get("importType");
        List<CommissionImportDto> moList = new ArrayList<>();

        if ("yj".equals(importType)) {
            //应计
            moList = commissionMapper.queryYjList(param);
        } else if ("kf".equals(importType)) {
            //可发
            moList = commissionMapper.queryKfList(param);
        }

        // 转换
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(moList);

        return resultData;
    }

    public ResultData<?> checkAccount(CommissionImportDto dto) {
        ResultData<?> resultData = new ResultData<>();
        //查询该月是否关账
        String[] values = dto.getDateMonth().split("-");

        if ("yj".equals(dto.getImportType())) {
            dto.setRelateSystem("27601");//导入应计，判断OMS是否关账  联动
            if ("19202".equals(dto.getBusinessTypeCode())) {
                dto.setRelateSystem("27601");//导入应计，判断OMS是否关账  非联动
            }
        } else {
            dto.setRelateSystem("27601");//导入可发，判断佣金是否关账    联动
            if ("19202".equals(dto.getBusinessTypeCode())) {
                dto.setRelateSystem("27601");//导入可发，判断佣金是否关账  非联动
            }
        }
        dto.setYear(values[0]);
        dto.setMonth(values[1]);
        CommissionImportDto checkResult = commissionMapper.checkAccount(dto);
        if (checkResult == null) {
            //该月未关账，获取关账最新月份
            CommissionImportDto result = commissionMapper.getLatestAccountMonth(dto);
            if (result != null) {
                //当前关账必须是最新关账月份的下一个月，首次不做要求
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(DateUtil.getDate(dto.getDateMonth(), "yyyy-MM"));
                calendar.add(Calendar.MONTH, -1);
                String fmtDate = DateUtil.fmtDate(calendar.getTime(), "yyyy-MM");
                String latestMonth = result.getDateMonth();
                if (fmtDate.equals(latestMonth)) {
                    resultData.setSuccess();
                } else {
                    resultData.setFail("最新关账月份为" + latestMonth + "，只能导入下个月！");
                }
            }
        } else {
            resultData.setFail("月份" + dto.getDateMonth() + "已关账，只能导入下个月！");
        }

        return resultData;
    }

    public ResultData<?> add(CommissionImportDto importDto) {
        String importType = importDto.getImportType();
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        //先验证数据
        List<CommissionImportDto> checkDataList = commissionMapper.checkData(importDto);
        if (!checkDataList.isEmpty()) {
            int tempId = 0;
            StringBuilder sb = new StringBuilder();
            for (CommissionImportDto checkDto : checkDataList) {
                if (tempId == checkDto.getId()) {
                    buildReturnMsg(checkDto, sb, false);
                } else {
                    tempId = checkDto.getId();
                    //当前ID第一次
                    buildReturnMsg(checkDto, sb, true);
                }
            }

            resultData.setFail(sb.toString());
            return resultData;
        }

        //导入的execel数据全删全进
        if ("yj".equals(importType)) {
            commissionMapper.deleteYjData(importDto);
        } else {
            commissionMapper.deleteKfData(importDto);
        }
        //验证通过
        SqlSession sqlSession = sqlServerSessionFactory.openSession();
        PreparedStatement ps = null;
        Connection con = null;
        String sql;
        if ("yj".equals(importType)) {
            sql = "INSERT INTO bonus_accrued_import"
                    + "(cityNo,monthStr,accountProjectCode,accountProjectName,checkBodyCode,checkBodyName,costCode,costName,projectNo,projectName,serviceType,jobBonus,teamBonus,countDate,createUser,createDate)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,GETDATE())";
        } else {
            sql = "INSERT INTO bonus_actual_import"
                    + "(cityNo,monthStr,accountProjectCode,accountProjectName,checkBodyCode,checkBodyName,costCode,costName,projectNo,projectName,serviceType,userCode,userName,jobBonus,teamBonus,canSendDate,createUser,createDate)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,GETDATE())";
        }

        try {
            con = sqlSession.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            int i = 1;
            long start = System.currentTimeMillis();

            for (CommissionImportDto dto : importDto.getImportDtos()) {
                int index = 1;
                ps.setString(index++, dto.getCityNo());
                ps.setString(index++, dto.getDateMonth());
                ps.setString(index++, dto.getAccountProjectCode());
                ps.setString(index++, dto.getAccountProjectName());
                ps.setString(index++, dto.getCheckBodyCode());
                ps.setString(index++, dto.getCheckBodyName());
                ps.setString(index++, dto.getCostCode());
                ps.setString(index++, dto.getCostName());
                ps.setString(index++, dto.getProjectNo());
                ps.setString(index++, dto.getProjectName());
                ps.setString(index++, dto.getServiceType());
                if ("kf".equals(importType)) {
                    ps.setString(index++, dto.getUserCode());
                    ps.setString(index++, dto.getUserName());
                }
                ps.setBigDecimal(index++, dto.getJobBonus());
                ps.setBigDecimal(index++, dto.getTeamBonus());
                ps.setString(index++, dto.getDate());
                ps.setString(index, dto.getCreateUser());

                ps.addBatch();
                if (i % 10000 == 0) {
                    ps.executeBatch();
                }
                i++;
            }
            ps.executeBatch();
            con.commit();
            logger.info("插入表花了============ " + (System.currentTimeMillis() - start));

            if (i != 1) {
                resultData.setSuccess("导入成功");
            } else {
                resultData.setSuccess("导入失败");
            }
        } catch (Exception e) {
            resultData.setFail("导入失败");
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sqlSession.close();
        }

        return resultData;
    }

    private void buildReturnMsg(CommissionImportDto dto, StringBuilder sb, boolean isFirst) {
        if (isFirst) {
            sb.append("\n第").append(dto.getId()).append("行：");
        }
        buildReturnMsg(dto, sb);
    }

    private void buildReturnMsg(CommissionImportDto dto, StringBuilder sb) {
        if (!"".equals(dto.getAccountProjectName())) {
            sb.append("核算主体：").append(dto.getAccountProjectName());
            if ("a".equals(dto.getResultType())) {
                sb.append("不存在，");
            } else if ("d".equals(dto.getResultType())) {
                sb.append("和核算主体编码不匹配，");
            }
        }

        if (!"".equals(dto.getAccountProjectCode())) {
            if ("a".equals(dto.getResultType())) {
                sb.append("核算主体编码：").append(dto.getAccountProjectCode()).append("不存在，");
            }
        }

        if (!"".equals(dto.getCheckBodyName())) {
            sb.append("考核主体：").append(dto.getCheckBodyName());
            if ("a".equals(dto.getResultType())) {
                sb.append("不存在，");
            } else if ("d".equals(dto.getResultType())) {
                sb.append("和考核主体编码不匹配，");
            } else if ("e".equals(dto.getResultType())) {
                sb.append("在海波龙不存在，");
            }
        }

        if (!"".equals(dto.getCheckBodyCode())) {
            sb.append("考核主体编码：").append(dto.getCheckBodyCode());
            if ("a".equals(dto.getResultType())) {
                sb.append("不存在，");
            } else if ("e".equals(dto.getResultType())) {
                sb.append("在海波龙不存在，");
            }
        }

        if (!"".equals(dto.getCostName())) {
            sb.append("成本中心：").append(dto.getCostName());
            if ("a".equals(dto.getResultType())) {
                sb.append("不存在，");
            } else if ("b".equals(dto.getResultType())) {
                sb.append("和所选城市不匹配，");
            } else if ("d".equals(dto.getResultType())) {
                sb.append("和成本中心编码不匹配，");
            } else if ("e".equals(dto.getResultType())) {
                sb.append("在海波龙不存在，");
            }
        }

        if (!"".equals(dto.getCostCode())) {
            sb.append("成本中心编码：").append(dto.getCostCode());
            if ("a".equals(dto.getResultType())) {
                sb.append("不存在，");
            } else if ("b".equals(dto.getResultType())) {
                sb.append("和所选城市不匹配，");
            } else if ("e".equals(dto.getResultType())) {
                sb.append("在海波龙不存在，");
            }
        }

        if (!"".equals(dto.getProjectName())) {
            sb.append("项目：").append(dto.getProjectName());
            if ("a".equals(dto.getResultType())) {
                sb.append("不存在，");
            } else if ("b".equals(dto.getResultType())) {
                sb.append("和所选城市不匹配，");
            } else if ("d".equals(dto.getResultType())) {
                sb.append("和项目编码不匹配，");
            } else {
                sb.append("对应的城市和核算主体、考核主体不匹配，");
            }
        }

        if (!"".equals(dto.getProjectNo())) {
            sb.append("项目编码：").append(dto.getProjectNo());
            if ("a".equals(dto.getResultType())) {
                sb.append("不存在，");
            } else if ("b".equals(dto.getResultType())) {
                sb.append("和所选城市不匹配，");
            } else {
                sb.append("对应的城市和核算主体、考核主体不匹配，");
            }
        }

        if (!"".equals(dto.getUserName())) {
            sb.append("姓名：").append(dto.getUserName());
            if ("a".equals(dto.getResultType())) {
                sb.append("不存在，");
            } else if ("d".equals(dto.getResultType())) {
                sb.append("和工号不匹配，");
            }
        }

        if (!"".equals(dto.getUserCode())) {
            if ("a".equals(dto.getResultType())) {
                sb.append("工号：").append(dto.getUserCode()).append("不存在，");
            }
        }
    }
}
