package com.irc;

/*
 * バージョン管理クラス
 * 
 * author: deepneko
 */
public class Version {

	/*
	 * メジャーバージョン
	 * 大きな変更（プロトコル変更含む）がある場合に増やします。
	 */
	private int major;

	/*
	 * マイナーバージョン
	 * 機能変更（プロトコル変更含む）がある場合に増やします。
	 */
	private int minor;

	/*
	 * リビジョン
	 * 機能変更を伴なわない修正(バグ修正, セキュリティホール封じ)がある場合に増やします。
	 */
	private int revision;
	
	public Version(int major, int minor, int revision){
		this.major = major;
		this.minor = minor;
		this.revision = revision;
	}
	
	public String getVersion(){
		return major + "." + minor + "." + revision;
	}
}
