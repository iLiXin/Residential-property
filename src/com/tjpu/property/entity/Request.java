package com.tjpu.property.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value = "msg")
public class Request {
	
	private String fromUser;
	private String createTime;
	private Content content;
	private String type;
	
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
}
