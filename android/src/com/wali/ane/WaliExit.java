package com.wali.ane;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.wali.g.sdk.OnLoginProcessListener;
import com.wali.g.sdk.WaCommplatform;
import com.wali.g.sdk.WaErrorCode;
import com.wali.g.sdk.entry.WaAccountInfo;

/**
 * @author Rect
 * @version  Time：2013-5-7 
 */
public class WaliExit implements FREFunction ,OnLoginProcessListener{

	private String TAG = "WaliExit";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null; 
		WaCommplatform.getInstance().waLogout( WaliExit.this );
		return result;
	}
	@Override
	public void finishLoginProcess( int arg0, WaAccountInfo arg1 )
	{
		
//		System.out.println("----login-------" + arg0);
		if ( WaErrorCode.MI_XIAOMI_GAMECENTER_SUCCESS == arg0 )
		{
			Log.d(TAG, "---------登录返回-------");
			_context.dispatchStatusEventAsync(TAG, "登录返回");
		}
		else if ( WaErrorCode.MI_XIAOMI_GAMECENTER_ERROR_LOGINOUT_SUCCESS == arg0 )
		{
			Log.d(TAG, "---------注销成功-------");
			_context.dispatchStatusEventAsync(TAG, "注销成功");
			// 注销成功
		}
		else if ( WaErrorCode.MI_XIAOMI_GAMECENTER_ERROR_LOGINOUT_FAIL == arg0 )
		{
			Log.d(TAG, "---------注销失败-------");
			_context.dispatchStatusEventAsync(TAG, "注销失败");
			// 注销失败
		}
		else if ( WaErrorCode.MI_XIAOMI_GAMECENTER_ERROR_ACTION_EXECUTED == arg0 )
		{
			Log.d(TAG, "---------登录操作正在进行中-------");
			//登录操作正在进行中.
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
