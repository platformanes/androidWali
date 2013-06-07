package com.wali.ane 
{ 
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	
	/**
	 * 
	 * @author Rect  2013-5-7 
	 * 
	 */
	public class WaliExtension extends EventDispatcher 
	{ 
		public static const WALI_FUNCTION_INIT:String = "wali_function_init";//与java端中Map里的key一致
		public static const WALI_FUNCTION_LOGIN:String = "wali_function_login";//与java端中Map里的key一致
		public static const WALI_FUNCTION_PAY:String = "wali_function_pay";//与java端中Map里的key一致
		public static const WALI_FUNCTION_EXIT:String = "wali_function_exit";//与java端中Map里的key一致
		
		public static const EXTENSION_ID:String = "com.wali.ane";//与extension.xml中的id标签一致
		private var extContext:ExtensionContext;
		
		/**单例的实例*/
		private static var _instance:WaliExtension; 
		public function WaliExtension(target:IEventDispatcher=null)
		{
			super(target);
			if(extContext == null) {
				extContext = ExtensionContext.createExtensionContext(EXTENSION_ID, "");
				extContext.addEventListener(StatusEvent.STATUS, statusHandler);
			}
		} 
		//第二个为参数，会传入java代码中的FREExtension的createContext方法
		/**
		 * 获取实例
		 * @return DLExtension 单例
		 */
		public static function getInstance():WaliExtension
		{
			if(_instance == null) 
				_instance = new WaliExtension();
			return _instance;
		}
		
		/**
		 * 转抛事件
		 * @param event 事件
		 */
		private function statusHandler(event:StatusEvent):void
		{
			dispatchEvent(event);
		}
		
		/**
		 * 
		 * @param appID  应用ID
		 * @param appKey 应用KEY
		 * @param appType 应用类型  网游 or 单机
		 * @param appPayMode 支付模式  大众 or 快捷
		 * @param OrinentationMode  屏幕显示模式  横屏 or 竖屏
		 * @return 
		 * 
		 */			
		public function WaliInit(appID:int,appKey:String,appType:Boolean,appPayMode:Boolean,OrinentationMode:Boolean):String{
			if(extContext ){
				return extContext.call(WALI_FUNCTION_INIT,appID,appKey,appType,appPayMode,OrinentationMode) as String;
			}
			return "call login failed";
		} 
		
		/**
		 *登录发送函数  
		 * @param key 暂时传什么都可以  留着可能要用
		 * @return 
		 * 
		 */		
		public function WaliLogIn(key:int):String{
			if(extContext ){
				return extContext.call(WALI_FUNCTION_LOGIN,key) as String;
			}
			return "call login failed";
		} 
		
		/**
		 * data 结构为Vector.String[CpOrderId,CpUserInfo,MiBi,ortherflag,otherkey]
		 * <br>CpOrderId  文档要求的CpID
		 * <br>CpUserInfo 平台传参数
		 * <br>MiBi String类型的 金额 例如　"5"  若输入 "0"则自动变成"5"
		 * <br>ortherflag 自定义参数
		 * <br>otherkey 自定义参数
		 * @param data 
		 * @return 
		 * 
		 */			 
		public function WaliPay(data:Vector.<String>):String{
			if(extContext && data.length == 5){ 
				return extContext.call(WALI_FUNCTION_PAY,data)as String;
			}
			return "call pay failed";
		}
		
		
		/**
		 *退出SDK时候调用   这个函数只在退出游戏的时候调用  
		 * @param key
		 * @return 
		 * 
		 */		
		public function ExitSDKHandle(key:int):String{
			if(extContext){ 
				return extContext.call(WALI_FUNCTION_EXIT,key) as String;
			}
			return "call exit failed";
		}
		/**
		 *获取用户名 暂时没实现 看需要  
		 * @return 
		 * 
		 */		
		public function getUserName():String{
			return null;
		}
		
		/**
		 *获取唯一ID标识 暂时没实现 看需要  
		 * @return 
		 * 
		 */
		public function getUserID():String{
			return null;
		}
	} 
}