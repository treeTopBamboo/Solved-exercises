package worldapp;

import java.io.Serializable;

public class PagingBean implements Serializable
{
	public static final int PAGESIZE = 20;
	private int next;
	private int previous;

	public int getNext()
	{
		return next;
	}

	public void setNext(int next)
	{
		this.next = next;
	}

	public int getPrevious()
	{
		return previous;
	}

	public void setPrevious(int previous)
	{
		this.previous = previous;
	}
}
