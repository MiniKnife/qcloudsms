package com.qcloud.sms;

import java.util.List;

public class SmsStatusPullReplyResult {
	public class Reply{
		String nationcode;
		String mobile;
		String text;
		String sign;
		long time;
		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Reply [nationcode=");
			builder.append(nationcode);
			builder.append(", mobile=");
			builder.append(mobile);
			builder.append(", text=");
			builder.append(text);
			builder.append(", sign=");
			builder.append(sign);
			builder.append(", time=");
			builder.append(time);
			builder.append("]");
			return builder.toString();
		}
	}
	
	int result;
	String errmsg;
	int count;
	List<Reply> replys;
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SmsStatusPullReplyResult [result=");
		builder.append(result);
		builder.append(", errmsg=");
		builder.append(errmsg);
		builder.append(", count=");
		builder.append(count);
		builder.append(", replys=");
		builder.append(replys);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}

	/**
	 * @return the errmsg
	 */
	public String getErrmsg() {
		return errmsg;
	}

	/**
	 * @param errmsg the errmsg to set
	 */
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the replys
	 */
	public List<Reply> getReplys() {
		return replys;
	}

	/**
	 * @param replys the replys to set
	 */
	public void setReplys(List<Reply> replys) {
		this.replys = replys;
	}
}

