package com.Util;
import java.io.UnsupportedEncodingException;
public class ThaiUtil {
	private String EncodeThai;
	public String EncodeTexttoTIS(String strinput) throws UnsupportedEncodingException
	{
		EncodeThai = new String(strinput.getBytes("ISO-8859-1"),"TIS-620");
		return EncodeThai;
	}
	public String EncodeTexttoUTF(String strinput) throws UnsupportedEncodingException
	{
		EncodeThai = new String(strinput.getBytes("ISO-8859-1"),"UTF-8");
		return EncodeThai;
	}        
}