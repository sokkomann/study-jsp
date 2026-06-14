package com.app;

public class Result {
//	트루면 리다이렉트 폴스면 포워드
	private boolean isRedirect;
	private String path;
	
	public Result() {;}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
