package cn.com.eju.pmls.skStatement.dto;

import cn.com.eju.pmls.skStatement.model.PmlsSkAllocateLog;

/**
 * Created by haidan on 2020/7/3.
 */
public class PmlsSkAllocateLogDto extends PmlsSkAllocateLog{

    private String userNameCreate;
    private String userNameUpdate;

    public String getUserNameCreate() {
        return userNameCreate;
    }

    public void setUserNameCreate(String userNameCreate) {
        this.userNameCreate = userNameCreate;
    }

    public String getUserNameUpdate() {
        return userNameUpdate;
    }

    public void setUserNameUpdate(String userNameUpdate) {
        this.userNameUpdate = userNameUpdate;
    }
}
