package cn.com.eju.deal.common.model;

import java.util.List;

/**
 * Created by haidan on 2019/12/27.
 */
public class CityFirstLetter {
    private String firstLetter;
    private List<City> cityList;

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
