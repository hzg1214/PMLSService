package cn.com.eju.deal.user.dto;

import java.util.List;

import cn.com.eju.deal.core.model.BaseModel;

public class AuthDto extends BaseModel
{
    
    private Integer id;
    
    private String text; //权限名称
    
    private String authCode; //权限编码
    
    private String authDesc; //权限描述
    
    private String type; //权限分类(M:模块;P:页面;B:按钮)
    
    private String url; //页面路径
    
    private Integer sortNo; //排序数
    
    private List<AuthDto> children; //子菜单
    /**
     * 所属系统Id 对应usr_system表
     */
    private Integer systemId;
    /**
     * 所属系统名称 （临时变量，用于列表显示）
     */
    private String systemName;
    
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getText()
    {
        return text;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    public String getAuthCode()
    {
        return authCode;
    }
    
    public void setAuthCode(String authCode)
    {
        this.authCode = authCode;
    }
    
    public String getAuthDesc()
    {
        return authDesc;
    }
    
    public void setAuthDesc(String authDesc)
    {
        this.authDesc = authDesc;
    }
    
    public String getType()
    {
        return type == null ? "" : type;
    }
    
    public void setType(String type)
    {
        this.type = type == null ? null : type.trim();
    }
    
    public String getUrl()
    {
        return url == null ? "" : url;
    }
    
    public void setUrl(String url)
    {
        this.url = url == null ? null : url.trim();
    }
    
    public Integer getSortNo()
    {
        return sortNo;
    }
    
    public void setSortNo(Integer sortNo)
    {
        this.sortNo = sortNo;
    }
    
    public List<AuthDto> getChildren()
    {
        return children;
    }
    
    public void setChildren(List<AuthDto> children)
    {
        this.children = children;
    }

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
    
}
