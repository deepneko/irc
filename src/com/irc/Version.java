package com.irc;

/*
 * �o�[�W�����Ǘ��N���X
 * 
 * author: deepneko
 */
public class Version {

	/*
	 * ���W���[�o�[�W����
	 * �傫�ȕύX�i�v���g�R���ύX�܂ށj������ꍇ�ɑ��₵�܂��B
	 */
	private int major;

	/*
	 * �}�C�i�[�o�[�W����
	 * �@�\�ύX�i�v���g�R���ύX�܂ށj������ꍇ�ɑ��₵�܂��B
	 */
	private int minor;

	/*
	 * ���r�W����
	 * �@�\�ύX�𔺂Ȃ�Ȃ��C��(�o�O�C��, �Z�L�����e�B�z�[������)������ꍇ�ɑ��₵�܂��B
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
