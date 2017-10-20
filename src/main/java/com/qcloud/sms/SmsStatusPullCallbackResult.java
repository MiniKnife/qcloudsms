package com.qcloud.sms;

import java.util.List;

public class SmsStatusPullCallbackResult {
	 public static class Callback{
		String userReceiveTime;
		String nationcode;
		String mobile;
		String reportStatus;
		String errmsg;
		String description;
		String sid;
		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Callback [userReceiveTime=");
			builder.append(userReceiveTime);
			builder.append(", nationcode=");
			builder.append(nationcode);
			builder.append(", mobile=");
			builder.append(mobile);
			builder.append(", reportStatus=");
			builder.append(reportStatus);
			builder.append(", errmsg=");
			builder.append(errmsg);
			builder.append(", description=");
			builder.append(description);
			builder.append(", sid=");
			builder.append(sid);
			builder.append("]");
			return builder.toString();
		}
		/**
		 * @return the userReceiveTime
		 */
		public String getUserReceiveTime() {
			return userReceiveTime;
		}
		/**
		 * @param userReceiveTime the userReceiveTime to set
		 */
		public void setUserReceiveTime(String userReceiveTime) {
			this.userReceiveTime = userReceiveTime;
		}
		/**
		 * @return the nationcode
		 */
		public String getNationcode() {
			return nationcode;
		}
		/**
		 * @param nationcode the nationcode to set
		 */
		public void setNationcode(String nationcode) {
			this.nationcode = nationcode;
		}
		/**
		 * @return the mobile
		 */
		public String getMobile() {
			return mobile;
		}
		/**
		 * @param mobile the mobile to set
		 */
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		/**
		 * @return the reportStatus
		 */
		public String getReportStatus() {
			return reportStatus;
		}
		/**
		 * @param reportStatus the reportStatus to set
		 */
		public void setReportStatus(String reportStatus) {
			this.reportStatus = reportStatus;
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
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		/**
		 * @return the sid
		 */
		public String getSid() {
			return sid;
		}
		/**
		 * @param sid the sid to set
		 */
		public void setSid(String sid) {
			this.sid = sid;
		}
	}
	
	int result;
	String errmsg;
	int count;
	List<Callback> callbacks;
	
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SmsStatusPullCallbackResult [result=");
		builder.append(result);
		builder.append(", errmsg=");
		builder.append(errmsg);
		builder.append(", count=");
		builder.append(count);
		builder.append(", callbacks=");
		builder.append(callbacks);
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
	 * @return the callbacks
	 */
	public List<Callback> getCallbacks() {
		return callbacks;
	}


	/**
	 * @param callbacks the callbacks to set
	 */
	public void setCallbacks(List<Callback> callbacks) {
		this.callbacks = callbacks;
	}
}

