package com.wali.ane;

import android.util.Log;

import com.adobe.fre.FREArray;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.wali.g.sdk.OnPayProcessListener;
import com.wali.g.sdk.WaCommplatform;
import com.wali.g.sdk.WaErrorCode;
import com.wali.g.sdk.entry.PayMode;
import com.wali.g.sdk.entry.WaBuyInfoOnline;

/**
 * @author Rect
 * @version  Time：2013-5-7 
 */
public class WaliPay implements FREFunction,OnPayProcessListener {

	private String TAG = "WaliPay";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] $args) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null; 
		String CpOrderId = null;
		String CpUserInfo = null;
		String MiBi = null;
		//以下两个参数备选
		String ortherflag = null;
		String otherkey = null;
		int money = 5;
		//大众支付方式
		if(WaliInit.ispayMode)
			WaCommplatform.getInstance().update_pay_mode(PayMode.custom);
		else
			WaCommplatform.getInstance().update_pay_mode(PayMode.simple);
		Log.d(TAG, "---------支付开始-------");
		if($args.length<1)
		{
			_context.dispatchStatusEventAsync(TAG,"参数不正确！");
			return null;
		}
		
		try{
			FREArray __array = (FREArray) $args[0];
			int __len = (int)__array.getLength();
			if(__len != 5)
			{
				_context.dispatchStatusEventAsync(TAG,"传入数组参数不正确！");
				return null;
			}

			CpOrderId = __array.getObjectAt(0).getAsString();
			CpUserInfo = __array.getObjectAt(1).getAsString();
			MiBi = (__array.getObjectAt(2).getAsString()); 
			ortherflag = __array.getObjectAt(3).getAsString();
			otherkey = __array.getObjectAt(4).getAsString();

			//call back string
		}catch (Exception e) { 
			// TODO: handle exception
			_context.dispatchStatusEventAsync(TAG, "PayError:"+e.getMessage());
		}
		
		Log.d(TAG, "---------支付-------"+CpOrderId+CpUserInfo+MiBi+ortherflag+otherkey);
		money = Integer.parseInt(MiBi);
		if(money == 0)money = 5;
		WaBuyInfoOnline online = new WaBuyInfoOnline();
		online.setCpOrderId( CpOrderId );
		online.setCpUserInfo( CpUserInfo );
		online.setWaBi(money);
		try
		{
			WaCommplatform.getInstance().waUniPayOnline(_context.getActivity(),online,WaliPay.this);
		}
		catch ( Exception e )
		{
			_context.dispatchStatusEventAsync(TAG, "PayError:"+e.getMessage());
		}
		
		Log.d(TAG, "---------函数返回-------");
		return result;
	}

	@Override
	public void finishPayProcess( int arg0 )
	{
		if ( arg0 == WaErrorCode.MI_XIAOMI_GAMECENTER_SUCCESS )// 成功
		{
			_context.dispatchStatusEventAsync(TAG, "购买成功");
		}
		else if ( arg0 == WaErrorCode.MI_XIAOMI_GAMECENTER_ERROR_CANCEL || arg0 == WaErrorCode.MI_XIAOMI_GAMECENTER_ERROR_PAY_CANCEL )// 取消
		{
			_context.dispatchStatusEventAsync(TAG, "取消购买");
		}
		else if ( arg0 == WaErrorCode.MI_XIAOMI_GAMECENTER_ERROR_PAY_FAILURE )// 失败
		{
			_context.dispatchStatusEventAsync(TAG, "购买失败");
		}
		else if ( arg0 == WaErrorCode.MI_XIAOMI_GAMECENTER_ERROR_ACTION_EXECUTED )
		{
			_context.dispatchStatusEventAsync(TAG, "正在执行，不要重复操作");
		}
		else if( arg0 == WaErrorCode.MI_XIAOMI_GAMECENTER_ERROR_LOGIN_FAIL )
		{
			_context.dispatchStatusEventAsync(TAG, "您还没有登陆，请先登陆");
		}
	}
}
