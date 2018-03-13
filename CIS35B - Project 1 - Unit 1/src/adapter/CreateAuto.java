package adapter;

public interface CreateAuto {
	public void buildAuto(String filename);
	public void printAuto(String autoID);
	public void buildAuto(String filename, String filetype);//in Server.java
}
