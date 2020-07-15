package cn.com.eju.deal.fangyou.model;

/**
 * Created by Sky on 2016/4/4.
 * 房友接口返回值
 */
public class FYApiResult {

    private int dataCode;

    private String msg;

    public int getDataCode() {
        return dataCode;
    }

    public void setDataCode(int dataCode) {
        this.dataCode = dataCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
