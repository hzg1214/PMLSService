package cn.com.eju.deal.user.dto;

import java.util.List;

public class MenuDto {
	private int id;
	private String text;
	private List<MenuDto> children;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<MenuDto> getChildren() {
		return children;
	}
	public void setChildren(List<MenuDto> children) {
		this.children = children;
	}
	
}
