package com.course.fengzhuang;

import java.util.List;
import java.util.Map;

public class TeacherResult<T>
{
	private boolean success;

	private T data;

	private String error;

	//成功
	public TeacherResult(boolean success, T data)
	{
		super();
		this.success = success;
		this.data = data;
	}

	//失败
	public TeacherResult(boolean success, String error)
	{
		super();
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public T getData()
	{
		return data;
	}

	public void setData(T data)
	{
		this.data = data;
	}

	public String getError()
	{
		return error;
	}

	public void setError(String error)
	{
		this.error = error;
	}

}
