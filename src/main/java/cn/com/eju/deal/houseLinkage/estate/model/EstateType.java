package cn.com.eju.deal.houseLinkage.estate.model;

import java.math.BigDecimal;
import java.util.Date;

public class EstateType {

	private Integer id;
	private String typeId;
	private String estateId;
	// 主力户型
	private Integer countFlg;
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
	private Integer directionKbn;
	// 标签
	private String label;
	private Integer crtEmpId;
	private Integer uptEmpId;
	private Boolean delFlg;
	private Date crtDt;
	private Date uptDt;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getEstateId() {
		return estateId;
	}
	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}
	public Integer getCountFlg() {
		return countFlg;
	}
	public void setCountFlg(Integer countFlg) {
		this.countFlg = countFlg;
	}
	public String getCountF() {
		return countF;
	}
	public void setCountF(String countF) {
		this.countF = countF;
	}
	public String getCountT() {
		return countT;
	}
	public void setCountT(String countT) {
		this.countT = countT;
	}
	public String getCountW() {
		return countW;
	}
	public void setCountW(String countW) {
		this.countW = countW;
	}
	public String getCountY() {
		return countY;
	}
	public void setCountY(String countY) {
		this.countY = countY;
	}
	public BigDecimal getBuildSquare() {
		return buildSquare;
	}
	public void setBuildSquare(BigDecimal buildSquare) {
		this.buildSquare = buildSquare;
	}
	public BigDecimal getUseSquare() {
		return useSquare;
	}
	public void setUseSquare(BigDecimal useSquare) {
		this.useSquare = useSquare;
	}
	
	/**
     * @return the directionKbn
     */
    public Integer getDirectionKbn()
    {
        return directionKbn;
    }
    /**
     * @param directionKbn the directionKbn to set
     */
    public void setDirectionKbn(Integer directionKbn)
    {
        this.directionKbn = directionKbn;
    }
    public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getCrtEmpId() {
		return crtEmpId;
	}
	public void setCrtEmpId(Integer crtEmpId) {
		this.crtEmpId = crtEmpId;
	}
	public Integer getUptEmpId() {
		return uptEmpId;
	}
	public void setUptEmpId(Integer uptEmpId) {
		this.uptEmpId = uptEmpId;
	}
	public Boolean getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(Boolean delFlg) {
		this.delFlg = delFlg;
	}
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	public Date getUptDt() {
		return uptDt;
	}
	public void setUptDt(Date uptDt) {
		this.uptDt = uptDt;
	}
	
}