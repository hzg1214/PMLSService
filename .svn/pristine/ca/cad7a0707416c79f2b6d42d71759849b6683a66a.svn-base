package cn.com.eju.deal.dto.api.houseLinkage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.com.eju.deal.core.util.StringUtil;

/** 
* @ClassName: APIEstateTypeFormDto 
* @Description: 楼盘户型
* @author 陆海丹 
* @date 2016年5月16日 下午2:16:38 
*  
*/
public class APIEstateTypeFormDto  implements Serializable
{
    
    /**
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
    */ 
    private static final long serialVersionUID = -3417871524159185322L;

    private String typeId;
    
    private String estateId;
    
    // 主力户型
    private String countFlg;
    
    // 户型(室)
    private String countF;
    
    // 户型(厅)
    private String countT;
    
    // 户型(卫)
    private String countW;
    
    // 户型(阳)
    private String countY;
    
    // 面积
    private BigDecimal buildSquare;
    
    private BigDecimal useSquare;
    
    // 朝向
    private String directionKbn;
    
    // 标签
    private String label;
    
    private List<String> labelList;//标签
    
    // 户型图
    private List<APIPhotoDto> houseTypePhotos;
    
    // 样板间
    private List<APIPhotoDto> templetPhotos;

    /**
     * @return the typeId
     */
    public String getTypeId()
    {
        return typeId;
    }

    /**
     * @param typeId the typeId to set
     */
    public void setTypeId(String typeId)
    {
        this.typeId = typeId;
    }

    /**
     * @return the estateId
     */
    public String getEstateId()
    {
        return estateId;
    }

    /**
     * @param estateId the estateId to set
     */
    public void setEstateId(String estateId)
    {
        this.estateId = estateId;
    }

    /**
     * @return the countFlg
     */
    public String getCountFlg()
    {
        return countFlg;
    }

    /**
     * @param countFlg the countFlg to set
     */
    public void setCountFlg(String countFlg)
    {
        this.countFlg = countFlg;
    }

    /**
     * @return the countF
     */
    public String getCountF()
    {
        return countF;
    }

    /**
     * @param countF the countF to set
     */
    public void setCountF(String countF)
    {
        this.countF = countF;
    }

    /**
     * @return the countT
     */
    public String getCountT()
    {
        return countT;
    }

    /**
     * @param countT the countT to set
     */
    public void setCountT(String countT)
    {
        this.countT = countT;
    }

    /**
     * @return the countW
     */
    public String getCountW()
    {
        return countW;
    }

    /**
     * @param countW the countW to set
     */
    public void setCountW(String countW)
    {
        this.countW = countW;
    }

    /**
     * @return the countY
     */
    public String getCountY()
    {
        return countY;
    }

    /**
     * @param countY the countY to set
     */
    public void setCountY(String countY)
    {
        this.countY = countY;
    }

    /**
     * @return the buildSquare
     */
    public BigDecimal getBuildSquare()
    {
        return buildSquare;
    }

    /**
     * @param buildSquare the buildSquare to set
     */
    public void setBuildSquare(BigDecimal buildSquare)
    {
        this.buildSquare = buildSquare;
    }

    /**
     * @return the useSquare
     */
    public BigDecimal getUseSquare()
    {
        return useSquare;
    }

    /**
     * @param useSquare the useSquare to set
     */
    public void setUseSquare(BigDecimal useSquare)
    {
        this.useSquare = useSquare;
    }

    /**
     * @return the directionKbn
     */
    public String getDirectionKbn()
    {
        return directionKbn;
    }

    /**
     * @param directionKbn the directionKbn to set
     */
    public void setDirectionKbn(String directionKbn)
    {
        this.directionKbn = directionKbn;
    }

    /**
     * @return the label
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label)
    {
        this.label = label;
    }

    /**
     * @return the houseTypePhotos
     */
    public List<APIPhotoDto> getHouseTypePhotos()
    {
        return houseTypePhotos;
    }

    /**
     * @param houseTypePhotos the houseTypePhotos to set
     */
    public void setHouseTypePhotos(List<APIPhotoDto> houseTypePhotos)
    {
        this.houseTypePhotos = houseTypePhotos;
    }

    /**
     * @return the templetPhotos
     */
    public List<APIPhotoDto> getTempletPhotos()
    {
        return templetPhotos;
    }

    /**
     * @param templetPhotos the templetPhotos to set
     */
    public void setTempletPhotos(List<APIPhotoDto> templetPhotos)
    {
        this.templetPhotos = templetPhotos;
    }
    
    /**
     * @return the labelList
     */
    public List<String> getLabelList()
    {
        labelList=new ArrayList<>();
        if(StringUtil.isNotEmpty(label)){
            String[] markArr=label.split(",");
            for (String string : markArr)
            {
                labelList.add(string);
            }
        }
        return labelList;
    }

    /**
     * @param labelList the labelList to set
     */
    public void setLabelList(List<String> labelList)
    {
        this.labelList = labelList;
    }
}
