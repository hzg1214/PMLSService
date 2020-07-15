package cn.com.eju.pmls.performswitch.dto;

import java.util.List;
import java.util.Map;

/**
 * 树形节构model
 */
public class ZTree {
	private Integer id;
	private String name;
	//自定义属性,城市no
	private String cityNo;
	//设置字体颜色
	private Map<String, Object> font;
	//字节点
	private List<ZTree> children;
	private boolean open = true;
    private String nodeType;

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Map<String, Object> getFont() {
		return font;
	}
	public void setFont(Map<String, Object> font) {
		this.font = font;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public List<ZTree> getChildren() {
		return children;
	}
	public void setChildren(List<ZTree> children) {
		this.children = children;
	}
	
}
