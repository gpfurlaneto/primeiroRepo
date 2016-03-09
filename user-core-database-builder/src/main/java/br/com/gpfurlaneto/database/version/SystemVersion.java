package br.com.gpfurlaneto.database.version;

import java.util.ArrayList;
import java.util.List;

public enum SystemVersion {

	V0_0_1("0.0.1", "v0_0_1/changeLog-pre.xml", "v0_0_1/changeLog-pos.xml"),
	V0_0_2("0.0.2", "v0_0_2/changeLog-pre.xml", null);
	
	public static List<SystemVersion> sequenceVersions;	
	static {
		sequenceVersions = new ArrayList<>();
		sequenceVersions.add(SystemVersion.V0_0_1);
	}

	private static String localChangeLogPrefix = "/WEB-INF/";
	private String versionCode;
	private String changeLogPre;
	private String changeLogPos;

	private SystemVersion(String versionCode, String changeLogPre, String changeLogPos) {
		this.versionCode = versionCode;
		this.changeLogPre = changeLogPre;
		this.changeLogPos = changeLogPos;
	}

	public String getCode() {
		return versionCode;
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

	public static SystemVersion getLastVersion() {
		return sequenceVersions.get(sequenceVersions.size()-1);
	}
}
