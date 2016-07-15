package com.tcps.pay.common;

/**
 * <p>
 * Title: Configure
 * </p>
 * <p>
 * Description: 存储一些配置信息
 * </p>
 * <p>
 * Company: 天津通卡公司
 * </p>
 * 
 * @author yanwenxiong
 * @date 2016年5月27日
 */
public class Configure {

	// 微信公众号的appid
	private static String appid = "wx7e244b5ad1f1e1b9";

	// AppSecret是APPID对应的接口密码
	private static String appsecret = "2dce0b92d741112f686dc0a9fef3b193";

	// API秘钥，交易过程生成签名的密钥
	private static String appkey = "AAFFFF1234BBFFABCDFFDD6789EEFFCC";

	// 商户服务器的ip地址
	private static String ip = "115.159.161.19";

	// 商家的商户收款账号
	private static String mch_id = "1348940101";

	// HTTPS证书的本地路径
	private static String certLocalPath = "";

	// HTTPS证书密码，默认密码等于商户号MCHID
	private static String certPassword = "1348940101";

	// 统一下单的接口地址
	public static String INTERFACE_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	// 查询订单的接口地址
	public static String INTERFACE_INQUIREORDER = "https://api.mch.weixin.qq.com/pay/orderquery";

	// 关闭订单的接口地址
	public static String INTERFACE_CLOSEORDER = "https://api.mch.weixin.qq.com/pay/closeorder";

	// 申请退款接口地址，需要双向证书，请注意。
	public static String INTERFACE_REFUND = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	// 查询退款的接口地址
	public static String INTERFACE_INQUIREREFUND = "https://api.mch.weixin.qq.com/pay/refundquery";

	// 下载对账单的接口地址
	public static String INTERFACE_DOWNLOADBILL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	
	public static String INTERFACE_TRADEGUARANTEE = "https://api.mch.weixin.qq.com/payitil/report";

	public static String getAppid() {
		return appid;
	}

	public static void setAppid(String appid) {
		Configure.appid = appid;
	}

	public static String getAppsecret() {
		return appsecret;
	}

	public static void setAppsecret(String appsecret) {
		Configure.appsecret = appsecret;
	}

	public static String getAppkey() {
		return appkey;
	}

	public static void setAppkey(String appkey) {
		Configure.appkey = appkey;
	}

	public static String getIp() {
		return ip;
	}

	public static void setIp(String ip) {
		Configure.ip = ip;
	}

	public static String getMch_id() {
		return mch_id;
	}

	public static void setMch_id(String mch_id) {
		Configure.mch_id = mch_id;
	}

	public static String getCertLocalPath() {
		return certLocalPath;
	}

	public static void setCertLocalPath(String certLocalPath) {
		Configure.certLocalPath = certLocalPath;
	}

	public static String getCertPassword() {
		return certPassword;
	}

	public static void setCertPassword(String certPassword) {
		Configure.certPassword = certPassword;
	}

	public static String getINTERFACE_ORDER() {
		return INTERFACE_ORDER;
	}

	public static void setINTERFACE_ORDER(String iNTERFACE_ORDER) {
		INTERFACE_ORDER = iNTERFACE_ORDER;
	}

	public static String getINTERFACE_INQUIREORDER() {
		return INTERFACE_INQUIREORDER;
	}

	public static void setINTERFACE_INQUIREORDER(String iNTERFACE_INQUIREORDER) {
		INTERFACE_INQUIREORDER = iNTERFACE_INQUIREORDER;
	}

	public static String getINTERFACE_CLOSEORDER() {
		return INTERFACE_CLOSEORDER;
	}

	public static void setINTERFACE_CLOSEORDER(String iNTERFACE_CLOSEORDER) {
		INTERFACE_CLOSEORDER = iNTERFACE_CLOSEORDER;
	}

	public static String getINTERFACE_REFUND() {
		return INTERFACE_REFUND;
	}

	public static void setINTERFACE_REFUND(String iNTERFACE_REFUND) {
		INTERFACE_REFUND = iNTERFACE_REFUND;
	}

	public static String getINTERFACE_INQUIREREFUND() {
		return INTERFACE_INQUIREREFUND;
	}

	public static void setINTERFACE_INQUIREREFUND(String iNTERFACE_INQUIREREFUND) {
		INTERFACE_INQUIREREFUND = iNTERFACE_INQUIREREFUND;
	}

	public static String getINTERFACE_DOWNLOADBILL() {
		return INTERFACE_DOWNLOADBILL;
	}

	public static void setINTERFACE_DOWNLOADBILL(String iNTERFACE_DOWNLOADBILL) {
		INTERFACE_DOWNLOADBILL = iNTERFACE_DOWNLOADBILL;
	}

}
