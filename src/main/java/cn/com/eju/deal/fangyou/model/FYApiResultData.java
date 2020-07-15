package cn.com.eju.deal.fangyou.model;

/**
 * 房友接口Result
 */
public class FYApiResultData<T> extends FYApiResult {
    private T result;

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}


