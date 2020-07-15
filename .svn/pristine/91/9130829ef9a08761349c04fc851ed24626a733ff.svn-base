package cn.com.eju.deal.common.enums;

/**   
* 问卷测评分数
* @author (luhaidan)
* @date 2019年9月23日
*/
public enum SatisEvaluationEnum
{
    //区域 主城中心区、非主城中心区、非主城区，分数分别为15分、10分、5分。
    ZCZXQ("25701", 15),//区域-主城中心区
    FZCZXQ("25702", 10),//区域-主城非中心区
    FZCQ("25703", 5),//区域-非主城（辐射城区）

    //凝聚力 铁粉、路人粉、僵尸粉，分数分别为10分、5分、0分。
    TF("25801",10),//凝聚力-铁粉（紧跟房友）
    LRF("25802",5),//凝聚力-路人粉（关系一般）
    JSF("25803",0),//凝聚力-僵尸粉（漠不关心）

    //门店规模 大型、中型、小型 和微型，分数分别为20分，15分，10分,5分。
    DX("26901",20),//大型
    ZX("26902",15),//小型
    XX("26903",10),//小型
    WX("26904",5),//微型

    //系统服务 经常使用房友系统、偶尔使用房友系统、开通但未使用房友系统、未开通房友系统，分数分别为20分，10分，5分，0分。
    XTFWJC("26101",20),
    XTFWOE("26102",10),
    XTFWKT("26103",5),
    XTFWW("26104",0),

    //培训服务 经常参加培训且注册人才学院、经常参加培训、偶尔参加培训、未参加培训，分数分别为20分，15分，10分，0分。
    PXFWJC("26201",20),
    PXFWOE("26202",15),
    PXFWKT("26203",10),
    PXFWW("26204",0),

    //招聘服务 达成招聘需求、发布招聘需求、注册直聘、未注册直聘，分数分别为20分，15分，10分，0分。
    ZPFWJC("26301",20),
    ZPFWOE("26302",15),
    ZPFWKT("26303",10),
    ZPFWW("26304",0),

    //交易服务 有交易进件、无交易进件，分数分别为20分、0分。
    JYFWY("26401",20),
    JYFWW("26402",0),

    //联动业务 已成交联动项目、已参加联动项目、未参加联动项目，分数分别为20分、10分、0分。
    LDYWDEAL("26501",20),
    LDYWYIN("26502",10),
    LDYWW("26503",0),

    //公盘业务 已成交公盘业务、已参加公盘业务、未参加公盘业务，分数分别为20分、10分、0分。
    GPYWDEAL("26601",20),
    GPYWYIN("26602",10),
    GPYWW("26603",0);

    private SatisEvaluationEnum(String code, Integer name)
    {
        this.code = code;
        this.name = name;
    }
    
    private String code;
    
    private Integer name;
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public Integer getName()
    {
        return name;
    }
    
    public void setName(Integer name)
    {
        this.name = name;
    }
    
    public static Integer getNameByCode(String code)
    {
        for (SatisEvaluationEnum enumObj : SatisEvaluationEnum.values())
        {
            if (code.equals(enumObj.getCode()))
            {
                return enumObj.getName();
            }
        }
        return -1;
    }
}
