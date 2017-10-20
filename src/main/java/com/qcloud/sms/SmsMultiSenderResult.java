package com.qcloud.sms;

import java.util.List;

public class SmsMultiSenderResult {
/*
{
    "result": 0, 
    "errmsg": "OK", 
    "ext": "", 
    "detail": [
        {
            "result": 0, 
            "errmsg": "OK", 
            "mobile": "13788888888", 
            "nationcode": "86", 
            "sid": "xxxxxxx", 
            "fee": 1
        }, 
        {
            "result": 0, 
            "errmsg": "OK", 
            "mobile": "13788888889", 
            "nationcode": "86", 
            "sid": "xxxxxxx", 
            "fee": 1
        }
    ]
}
 */
	public class Detail {
		public int result;
		public String errMsg = "";
		public String phoneNumber = "";
		public String nationCode = "";
		public String sid = "";
		public int fee;
		
		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Detail [result=");
			builder.append(result);
			builder.append(", errMsg=");
			builder.append(errMsg);
			builder.append(", phoneNumber=");
			builder.append(phoneNumber);
			builder.append(", nationCode=");
			builder.append(nationCode);
			builder.append(", sid=");
			builder.append(sid);
			builder.append(", fee=");
			builder.append(fee);
			builder.append("]");
			return builder.toString();
		}
	}

	public int result;
	public String errMsg = "";
	public String ext = "";
	public List<Detail> details;
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SmsMultiSenderResult [result=");
		builder.append(result);
		builder.append(", errMsg=");
		builder.append(errMsg);
		builder.append(", ext=");
		builder.append(ext);
		builder.append(", details=");
		builder.append(details);
		builder.append("]");
		return builder.toString();
	}
}
