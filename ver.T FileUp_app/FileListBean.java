package fileupload;

import java.io.Serializable;

public class FileListBean implements Serializable
{
	private String[] filelist;

	public String[] getFilelist() 
	{
		return filelist;
	}

	public void setFilelist(String[] filelist) 
	{
		this.filelist = filelist;
	}

}
