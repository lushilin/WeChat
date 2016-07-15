package com.tcps.pay.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tcps.java.common.dao.IBaseDAO;
import com.tcps.pay.common.HttpClientUtil;
import com.tcps.pay.common.Signature;
import com.tcps.pay.service.https.HttpsClientUtil;

@Service(value="Notify")
public class NotifyResultService {
	@Resource(name = "jdbcDAO")
	private IBaseDAO iBaseDAO;
	
	private String notify_url = "http://www.yanwenxiong.cn/Wechat/servlet/NotifyServlet";
	
	public static String respXMLStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
	
	public IBaseDAO getDAO(){
		return iBaseDAO;
	}
	
	public void updatePay(String order_no,String transaction_id,
			String trade_time,int check_over){
		String sql = "update wechat_pay_info set "
				+"transaction_id='" + transaction_id + "',"
				+"trade_time=" + "to_date('" + trade_time + "','yyyy-MM-dd HH24:mi:ss'),"
				+"check_over=" + check_over
				+" where order_no='" + order_no + "'";
		try {
			getDAO().update(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryPay(String order_no){
		String querySql = "select * from WECHAT_PAY_INFO where order_no = '" + order_no + "'" ;
		try {
			List<Map<String,Object>> list = (List<Map<String,Object>>)iBaseDAO.findListBySql(querySql);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @return 4代表：通讯失败；3代表：签名不通过；2代表：订单号不存在，无法确认；1代表：订单已经确认过了，不需要再次确认；0代表确认成功。
	 * @param Map<String, String> 通知的参数列表
	 */
	public int notifyResult(Map<String, String> reqData){
		String return_code = reqData.get("return_code");
		if(return_code != null || "SUCCESS".equals(return_code)){
			String sign = reqData.get("sign");
			reqData.remove("sign");
			String mySign = Signature.getSign(reqData);
			if(sign.equals(mySign)){
				String out_trade_no = reqData.get("out_trade_no");
				//查询支付数据库，看此订单是否被处理过。
				List<Map<String, Object>> list = queryPay(out_trade_no);
				if(!list.isEmpty()){
					if("1".equals(list.get(0).get("CHECK_OVER"))){
						//已经检查过了，直接跳过;
						return 1;
					}
					else {
						updatePay(out_trade_no, reqData.get("transaction_id"), reqData.get("time_end"), 1);
						return 0;
					}
				}
				else {
					//没有这条订单，抛出异常。系统管理员进行处理.
					return 2;
				}
			}
			else {
				return 3;  
				//写入日志，抛出通知数据非法的警告，服务器可能遭到攻击。
			}
		}
		//4代表：通讯失败
		return 4;
	}
}
