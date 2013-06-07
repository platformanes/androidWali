package com.wali.ane;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

/**
 * @author Rect
 * @version  Time：2013-5-7 
 */
public class WaliContext extends FREContext {
	/**
	 * INIT sdk
	 */
	public static final String WALI_FUNCTION_INIT = "wali_function_init";
	/**
	 * 登录Key
	 */
	public static final String WALI_FUNCTION_LOGIN = "wali_function_login";
	/**
	 * 付费Key
	 */
	public static final String WALI_FUNCTION_PAY = "wali_function_pay";
	/**
	 * 退出Key
	 */
	public static final String WALI_FUNCTION_EXIT = "wali_function_exit";
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		// TODO Auto-generated method stub
		Map<String, FREFunction> map = new HashMap<String, FREFunction>();
	       //映射
		   map.put(WALI_FUNCTION_INIT, new WaliInit());
	       map.put(WALI_FUNCTION_LOGIN, new WaliLogin());
	       map.put(WALI_FUNCTION_PAY, new WaliPay());
	       map.put(WALI_FUNCTION_EXIT, new WaliExit());
	       return map;
	}

}
