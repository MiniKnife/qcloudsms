package com.qcloud.sms;

public class SmsSingleSenderResult {
/*
{
    "result": 0,
    "errmsg": "OK", 
    "ext": "", 
    "sid": "xxxxxxx", 
    "fee": 1
}
 */
	public int result;
	public String errMsg = "";
	public String ext = "";
	public String sid = "";
	public int fee;
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SmsSingleSenderResult [result=");
		builder.append(result);
		builder.append(", errMsg=");
		builder.append(errMsg);
		builder.append(", ext=");
		builder.append(ext);
		builder.append(", sid=");
		builder.append(sid);
		builder.append(", fee=");
		builder.append(fee);
		builder.append("]");
		return builder.toString();
	}
}
