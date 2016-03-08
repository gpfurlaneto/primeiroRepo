package br.com.gpfurlaneto.database.version;

public enum SystemVersion {

	V0_0_1("0.0.1", null, "v0_0_1/changeLog-pre.xml", "v0_0_1/changeLog-pos.xml");

	public static SystemVersion LAST_VERSION =  SystemVersion.V0_0_1;
	private static String localChangeLogPrefix = "/WEB-INF/";
	private String versionCode;
	private SystemVersion postVersionCode;
	private String changeLogPre;
	private String changeLogPos;

	private SystemVersion(String versionCode, SystemVersion postVersionCode, String changeLogPre, String changeLogPos) {
		this.versionCode = versionCode;
		this.postVersionCode = postVersionCode;
		this.changeLogPre = changeLogPre;
		this.changeLogPos = changeLogPos;
	}

	public String getCode() {
		return versionCode;
	}

	public SystemVersion getPostSystemVersion() {
		return postVersionCode;
	}

	public String getChangeLogPre() {
		return localChangeLogPrefix + this.changeLogPre;
	}

	public String getChangeLogPos() {
		return localChangeLogPrefix + this.changeLogPos;
	}
	
	public boolean hasChangeLogPre() {
		return changeLogPre != null;
	}

	public boolean hasChangeLogPos() {
		return changeLogPos != null;
	}
}
