package com.wali.ane 
{ 
	/**
	 * 
	 * @author Rect  2013-5-7 
	 * 
	 */
	public class WaliEvents 
	{ 
		public function WaliEvents()
		{
		} 
		/**************************平台通知************************************/
		/**
		 *init 
		 */		
		public static const XIAOMI_SDK_STATUS:String = "WaliInit";
		/**
		 * 用户登录
		 */
		public static const XIAOMI_LOGIN_STATUS : String = "WaliLogin";
		
		/**
		 * 用户注销
		 */
		public static const XIAOMI_LOGOUT_STATUS : String = "WaliExit";
		
		/**
		 * 充值
		 */
		public static const XIAOMI_PAY_STATUS : String = "WaliPay";
	} 
}