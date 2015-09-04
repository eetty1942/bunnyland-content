package net.bunnyland.dto;

public class ContentFileDto {

	int fileNumber;
	String fileName;
	String filePath;
	String fileType;
	int fileSize;
 	int content_contentNumber;
 	
	public int getFileNumber() {
		return fileNumber;
	}
	public void setFileNumber(int fileNumber) {
		this.fileNumber = fileNumber;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public int getContent_contentNumber() {
		return content_contentNumber;
	}
	public void setContent_contentNumber(int content_contentNumber) {
		this.content_contentNumber = content_contentNumber;
	}
	

}
