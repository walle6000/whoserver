package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileUpload {

	@JsonProperty("fileName")
	private String fileName;
	
	@JsonProperty("fileUrl")
	private String fileUrl;
	
	@JsonProperty("thumbnailUrl")
	private String thumbnailUrl;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	
	
}
