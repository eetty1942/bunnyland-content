package net.bunnyland.dto;

public class PushLogDto {

	int logNumber;
	String pushDescription;
	String url;
	String pushKind;
	String pushDate;
	int managerInfo_managerNumber;
	
	public int getLogNumber() {
		return logNumber;
	}
	public void setLogNumber(int logNumber) {
		this.logNumber = logNumber;
	}
	public String getPushDescription() {
		return pushDescription;
	}
	public void setPushDescription(String pushDescription) {
		this.pushDescription = pushDescription;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPushKind() {
		return pushKind;
	}
	public void setPushKind(String pushKind) {
		this.pushKind = pushKind;
	}
	public String getPushDate() {
		return pushDate;
	}
	public void setPushDate(String pushDate) {
		this.pushDate = pushDate;
	}
	public int getManagerInfo_managerNumber() {
		return managerInfo_managerNumber;
	}
	public void setManagerInfo_managerNumber(int managerInfo_managerNumber) {
		this.managerInfo_managerNumber = managerInfo_managerNumber;
	}
}
