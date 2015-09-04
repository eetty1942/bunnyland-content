package net.bunnyland.dto;

public class ContentDto {

	int contentNumber;
	String contentName;
	String description;
	String registDate;
	String modifyDate;
	int managerInfo_managerNumber;
	byte[] thumbnail;
	
	
	public byte[] getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}
	public int getContentNumber() {
		return contentNumber;
	}
	public void setContentNumber(int contentNumber) {
		this.contentNumber = contentNumber;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getManagerInfo_managerNumber() {
		return managerInfo_managerNumber;
	}
	public void setManagerInfo_managerNumber(int managerInfo_managerNumber) {
		this.managerInfo_managerNumber = managerInfo_managerNumber;
	}

}
