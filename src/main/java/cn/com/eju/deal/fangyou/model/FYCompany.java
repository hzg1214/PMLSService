package cn.com.eju.deal.fangyou.model;

/**
 * Created by Sky on 2016/4/4.
 * 房友公司信息
 */
public class FYCompany {

    private String companyId;
    private String companyName;
    private String companyNo;
    private String x;
    private String y;
    private String cityName;
    private String districtName;
    private String areaName;
    private String picCode;
    private String address;
    private String cityNo;
    private String districtNo;
    private String areaNo;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getPicCode() {
        return picCode;
    }

    public void setPicCode(String picCode) {
        this.picCode = picCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public String getDistrictNo() {
        return districtNo;
    }

    public void setDistrictNo(String districtNo) {
        this.districtNo = districtNo;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    /**
     * 参数转换为url键值对
     *
     * @return 转换后的类型
     */
    public String ConvertToUrlParam() {
        String urlTemplate = "companyId=%s&companyName=%s&companyNo=%s&x=%s&y=%s&cityName=%s&districtName=%s&areaName=%s&picCode=%s&address=%s&areaNo=%s&cityNo=%s&districtNo=%s";
        return String.format(urlTemplate, companyId, companyName, companyNo, x, y, cityName, districtName, areaName, picCode, address, areaNo, cityNo, districtNo);
    }
}
