package cn.com.eju.deal.model.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xu on 2018/1/17.
 */
public class PageDto<T> implements Serializable {
    private int pageIndex=1;//当前页数
    private int pageSize=10;//每页显示数量
    private int dataCount=0;//总数
    private T conditionDto;//查询条件对象
    private List<T> results;//返回集合

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public T getConditionDto() {
        return conditionDto;
    }

    public void setConditionDto(T conditionDto) {
        this.conditionDto = conditionDto;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
