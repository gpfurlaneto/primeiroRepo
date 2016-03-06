package br.com.gpfurlaneto.database.version;

public enum SystemVersion {

	V0_0_1("0.0.1", null, "v0_0_1/changeLog-pre.xml");

	private static String localChangeLogPrefix = "/WEB-INF/";
	private String versionCode;
	private SystemVersion postVersionCode;
	private String changeLog;

	private SystemVersion(String versionCode, SystemVersion postVersionCode, String changeLog) {
		this.versionCode = versionCode;
		this.postVersionCode = postVersionCode;
		this.changeLog = changeLog;
	}

	public String getCode() {
		return versionCode;
	}

	public SystemVersion getPostSystemVersion() {
		return postVersionCode;
	}

	public String getChangeLog() {
		return localChangeLogPrefix + this.changeLog;
	}

	public static SystemVersion valueOfCode(String code) {

		if (code != null) {
			for (SystemVersion systemVersion : SystemVersion.values()) {
				if (systemVersion.getCode().equals(code)) {
					return systemVersion;
				}
			}
		}
		return null;
	}
}
