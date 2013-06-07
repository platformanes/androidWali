package com.wali.ane;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.wali.g.sdk.OnLoginProcessListener;
import com.wali.g.sdk.WaCommplatform;
import com.wali.g.sdk.WaErrorCode;
import com.wali.g.sdk.entry.ScreenOrientation;
import com.wali.g.sdk.entry.WaAccountInfo;
import com.wali.g.sdk.entry.WaAppInfo;
import com.wali.g.sdk.entry.WaGameType;

import android.util.Log;

/**
 * @author Rect
 * @version  Time：2013-5-7 
 */
public class WaliLogin implements FREFunction, OnLoginProcessListener{

	private String TAG = "WaliLogin";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null;
		Log.d(TAG, "---------Login begin-------");
		WaCommplatform.getInstance().waLogin( _context.getActivity(), WaliLogin.this );
		return result;
	}
	@Override
	public void finishLoginProcess( int arg0, WaAccountInfo arg1 )
	{
		
//		System.out.println("----login-------" + arg0);
		if ( WaErrorCode.MI_XIAOMI_GAMECENTER_SUCCESS == arg0 )
		{
			Log.d(TAG, "---------登录返回-------");
//			Toast.makeText( _context.getActivity(), "登录成功", Toast.LENGTH_SHORT ).show();
			// 登陆成功
			//获取用户的登陆后的UID（即用户唯一标识）
			String uid = ""+arg1.getUid();
			//获取用户的登陆的Session（请参考4.2.3.3 流程校验Session 有效性）
			String session = arg1.getSessionId();//若没有登录返回null
			Log.d(TAG, "---------用户登录-------");
			String str = "返回数据";
			str += "*"+0;
			str += "*"+uid;
			str += "*"+session;
			str += "*"+"arr[2] is uid";
			str += "*"+"arr[3] is session";
			_context.dispatchStatusEventAsync(TAG, str);
		}
		else if ( WaErrorCode.MI_XIAOMI_GAMECENTER_ERROR_LOGINOUT_SUCCESS == arg0 )
		{
			// 注销成功
			_context.dispatchStatusEventAsync(TAG, "注销成功");
		}
		else if ( WaErrorCode.MI_XIAOMI_GAMECENTER_ERROR_LOGINOUT_FAIL == arg0 )
		{
			// 注销失败
			_context.dispatchStatusEventAsync(TAG, "注销失败");
		}
		else if ( WaErrorCode.MI_XIAOMI_GAMECENTER_ERROR_ACTION_EXECUTED == arg0 )
		{
			//登录操作正在进行中
			_context.dispatchStatusEventAsync(TAG, "登录操作正在进行中");
		}
		else
		{
			String str = "返回数据";
			str += "*"+"-2";
			_context.dispatchStatusEventAsync(TAG, str);
			// 登录失败
		}
	}
}
