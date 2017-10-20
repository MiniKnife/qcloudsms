package com.qcloud.sms;

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public abstract class SmsSenderUtil {

	protected static Random random = new Random();

	public static String stringMD5(String input) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		byte[] inputByteArray = input.getBytes();
		messageDigest.update(inputByteArray);
		byte[] resultByteArray = messageDigest.digest();
		return byteArrayToHex(resultByteArray);
	}

	private static String strToHash(String str) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] inputByteArray = str.getBytes();
		messageDigest.update(inputByteArray);
		byte[] resultByteArray = messageDigest.digest();
		return byteArrayToHex(resultByteArray);
	}

	public static String byteArrayToHex(byte[] byteArray) {
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] resultCharArray = new char[byteArray.length * 2];
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		return new String(resultCharArray);
	}

	public static int getRandom() {
		return random.nextInt(999999) % 900000 + 100000;
	}

	public static HttpURLConnection getPostHttpConn(String url) throws Exception {
		URL object = new URL(url);
		HttpURLConnection conn;
		conn = (HttpURLConnection) object.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestMethod("POST");
		return conn;
	}

	public static String calculateSig(String appkey, int randomNumber, long timestamp) throws NoSuchAlgorithmException {
		return strToHash(String.format("appkey=%s&random=%d&time=%d", appkey, randomNumber, timestamp));
	}

	public static String calculateSig(String appkey, int random, long curTime, String msg, List<String> phoneNumbers)
			throws NoSuchAlgorithmException {
		String phoneNumbersString = phoneNumbers.get(0);
		for (int i = 1; i < phoneNumbers.size(); i++) {
			phoneNumbersString += "," + phoneNumbers.get(i);
		}
		return strToHash(
				String.format("appkey=%s&random=%d&time=%d&mobile=%s", appkey, random, curTime, phoneNumbersString));
	}

	public static String calculateSigForTempl(String appkey, int random, long curTime, List<String> phoneNumbers)
			throws NoSuchAlgorithmException {
		String phoneNumbersString = phoneNumbers.get(0);
		for (int i = 1; i < phoneNumbers.size(); i++) {
			phoneNumbersString += "," + phoneNumbers.get(i);
		}
		return strToHash(
				String.format("appkey=%s&random=%d&time=%d&mobile=%s", appkey, random, curTime, phoneNumbersString));
	}

	public static String calculateSigForTempl(String appkey, int random, long curTime, String phoneNumber)
			throws NoSuchAlgorithmException {
		List<String> phoneNumbers = new ArrayList<>();
		phoneNumbers.add(phoneNumber);
		return calculateSigForTempl(appkey, random, curTime, phoneNumbers);
	}

	public static JSONArray phoneNumbersToJSONArray(String nationCode, List<String> phoneNumbers) {
		JSONArray tel = new JSONArray();
		int i = 0;
		do {
			JSONObject telElement = new JSONObject();
			telElement.put("nationcode", nationCode);
			telElement.put("mobile", phoneNumbers.get(i));
			tel.add(telElement);
		} while (++i < phoneNumbers.size());

		return tel;
	}

	public static JSONArray smsParamsToJSONArray(List<String> params) {
		JSONArray smsParams = new JSONArray();
		for (int i = 0; i < params.size(); i++) {
			smsParams.add(params.get(i));
		}
		return smsParams;
	}

	public static SmsSingleSenderResult jsonToSmsSingleSenderResult(JSONObject json) {
		SmsSingleSenderResult result = new SmsSingleSenderResult();

		result.result = json.getIntValue("result");
		result.errMsg = json.getString("errmsg");
		if (0 == result.result) {
			result.ext = json.getString("ext");
			result.sid = json.getString("sid");
			result.fee = json.getIntValue("fee");
		}
		return result;
	}

	public static SmsMultiSenderResult jsonToSmsMultiSenderResult(JSONObject json) {
		SmsMultiSenderResult result = new SmsMultiSenderResult();

		result.result = json.getIntValue("result");
		result.errMsg = json.getString("errmsg");
		String ext = json.getString("ext");
		if (!isEmpty(ext)) {
			result.ext = ext;
		}
		if (0 != result.result) {
			return result;
		}

		result.details = new ArrayList<>();
		JSONArray details = json.getJSONArray("detail");
		for (int i = 0, len = details.size(); i < len; ++i) {
			JSONObject jsonDetail = details.getJSONObject(i);
			SmsMultiSenderResult.Detail detail = result.new Detail();
			detail.result = jsonDetail.getIntValue("result");
			detail.errMsg = jsonDetail.getString("errmsg");
			if (0 == detail.result) {
				detail.phoneNumber = jsonDetail.getString("mobile");
				detail.nationCode = jsonDetail.getString("nationcode");
				String sid = json.getString("sid");
				if (!isEmpty(sid)) {
					detail.sid = sid;
				}
				detail.fee = jsonDetail.getIntValue("fee");
			}
			result.details.add(detail);
		}
		return result;
	}

	public static SmsStatusPullCallbackResult jsonToSmsStatusPullCallbackrResult(JSONObject json) {
		SmsStatusPullCallbackResult result = new SmsStatusPullCallbackResult();

		result.result = json.getIntValue("result");
		result.errmsg = json.getString("errmsg");
		String data = json.getString("data");
		if (isEmpty(data)) {
			return result;
		}
		result.callbacks = new ArrayList<>();
		JSONArray datas = json.getJSONArray("data");
		for (int index = 0, len = datas.size(); index < len; index++) {
			JSONObject cb = datas.getJSONObject(index);
			SmsStatusPullCallbackResult.Callback callback = new SmsStatusPullCallbackResult.Callback();
			callback.userReceiveTime = cb.getString("user_receive_time");
			callback.nationcode = cb.getString("nationcode");
			callback.mobile = cb.getString("mobile");
			callback.reportStatus = cb.getString("report_status");
			callback.errmsg = cb.getString("errmsg");
			callback.description = cb.getString("description");
			callback.sid = cb.getString("sid");
			result.callbacks.add(callback);
		}
		return result;
	}

	public static SmsStatusPullReplyResult jsonToSmsStatusPullReplyResult(JSONObject json) {
		SmsStatusPullReplyResult result = new SmsStatusPullReplyResult();

		result.result = json.getIntValue("result");
		result.errmsg = json.getString("errmsg");
		result.count = json.getIntValue("count");

		if (isEmpty(json.getString("data"))) {
			return result;
		}

		result.replys = new ArrayList<>();
		JSONArray datas = json.getJSONArray("data");
		for (int index = 0, len = datas.size(); index < len; index++) {
			JSONObject reply_json = datas.getJSONObject(index);
			SmsStatusPullReplyResult.Reply reply = result.new Reply();
			reply.nationcode = reply_json.getString("nationcode");
			reply.mobile = reply_json.getString("mobile");
			reply.sign = reply_json.getString("sign");
			reply.text = reply_json.getString("text");
			reply.time = reply_json.getLong("time");
			result.replys.add(reply);
		}

		return result;
	}

	public static SmsVoiceVerifyCodeSenderResult jsonToSmsVoiceVerifyCodeSenderResult(JSONObject json) {
		SmsVoiceVerifyCodeSenderResult result = new SmsVoiceVerifyCodeSenderResult();
		result.result = json.getIntValue("result");
		String errmsg = json.getString("errmsg");
		if (!isEmpty(errmsg)) {
			result.errmsg = errmsg;
		}
		if (0 == result.result) {
			result.ext = json.getString("ext");
			result.callid = json.getString("callid");
		}
		return result;
	}

	public static SmsVoicePromptSenderResult jsonToSmsVoicePromptSenderResult(JSONObject json) {
		SmsVoicePromptSenderResult result = new SmsVoicePromptSenderResult();
		result.result = json.getIntValue("result");
		String errmsg = json.getString("errmsg");
		if (!isEmpty(errmsg)) {
			result.errmsg = errmsg;
		}
		if (0 == result.result) {
			result.ext = json.getString("ext");
			result.callid = json.getString("callid");
		}
		return result;
	}

	private static boolean isEmpty(String value) {
		return value == null || value.length() == 0;
	}
}
