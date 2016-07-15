package com.tcps.java.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.tcps.java.common.dao.IBaseDAO;
import com.tcps.java.common.model.Order;
import com.tcps.java.common.model.User;

@Service(value="wechatservice")
public class WechatService {
	 @Resource(name="jdbcDAO")
	 private IBaseDAO jdbcDAO;
	 
	 @Resource(name = "transaction")
	 private TransactionTemplate transaction;

	 public IBaseDAO getDAO(){
		 return jdbcDAO;
	 }
	 
	 public TransactionTemplate getTransaction(){
		 return transaction;
	 }
	 
	 public String saveRegister(final User user){
		final String sql1 = "select * from WX_REGISTER WHERE WX_ID='"+user.getOpenId()+"'";
		final String sql2 = "select * from BASIC_CITY_INFO where CITY_CODE='"+user.getCityCode()+"'";
	     String result = transaction.execute(new TransactionCallback<Object>() {
	    	 public Object doInTransaction(TransactionStatus status){
				try {
					@SuppressWarnings("unchecked")
					List<Map<String,Object>> city =(List<Map<String,Object>>)getDAO().findListBySql(sql2);
					String cityName = city.get(0).get("CITY_NAME").toString();
					@SuppressWarnings("unchecked")
					List<Map<String,Object>> userList = (List<Map<String,Object>>)getDAO().findListBySql(sql1);
					if(userList.size() == 0)
					getDAO().update("insert into WX_REGISTER(WX_ID,IC_ID1,CITY,ID_NO) VALUES(?,?,?,?)", new Object[]{user.getOpenId(),user.getCardId(),cityName,1});
					else{
						if(Integer.parseInt(userList.get(0).get("ID_NO").toString())==3)
							return "alreay 3 IC_CARD has been bond";
						else if(Integer.parseInt(userList.get(0).get("ID_NO").toString()) < 3){
							String sql3 = "update WX_REGISTER SET IC_ID"+String.valueOf((Integer.parseInt(userList.get(0).get("ID_NO").toString())+1))
											+"='"+user.getCardId()+"',ID_NO="+(Integer.parseInt(userList.get(0).get("ID_NO").toString())+1)+
									        " where WX_ID='"+user.getOpenId()+"'";
							getDAO().update(sql3);
						}else{
							return "System false";
						}
					}
					return "success";
					} catch (Exception e) {
						status.setRollbackOnly();
						return e.getMessage();
					}
				}
			}).toString();
	     return result;
	 }
	 
	 public String saveOrder(Order order){
		   try{
			   			//包含日期函数to_date（）的sql语句,不能用？代替变量？？？？
						String saveSql1 = "insert into TK_RECHARGE_ORDER_TODAY"+
										"(ORDER_NO,ORDER_TIME,IS_OK,ORDER_MONEY,"+
										"POUNDAGE_MONEY,SUM_MONEY,CARD_NO,USER_ID,"+
										"BALANCE_FLAG,PAY_TYPE,WARN_TYPE) values ('"+
										order.getOrderNo()+"',to_date('"+
										order.getOrderTime()+"','YYYY-MM-DD HH24:MI:SS'),"+
										order.getIs_Ok()+","+order.getOrderMoney()+","+order.getPoundageMoney()+","+
										order.getSumMoney()+",'"+order.getCardId()+"','"+order.getUserId()+"',"+order.getBalanceFlag()+
										","+order.getPayType()+","+order.getWarnType()+")";
						getDAO().update(saveSql1);
						return "success";
		      }catch(Exception e){
		    	  return e.getMessage();
		      }
	 }
	 
	 public String updateOrder(String openid, String orderNo){
		   try{
			   			//包含日期函数to_date（）的sql语句,不能用？代替变量？？？？
						String saveSql1 = "UPDATE TK_RECHARGE_ORDER_TODAY SET IS_OK=1 WHERE ORDER_NO='"
										  +orderNo+"' and USER_ID='"+openid+"'";
						getDAO().update(saveSql1);
						return "success";
		      }catch(Exception e){
		    	  return e.getMessage();
		      }
	 }
	 
	@SuppressWarnings("unchecked")
	public String getSerieNo() {
		 String sql = "select max(ORDER_NO) from TK_RECHARGE_ORDER_TODAY";
		 List<Map<String, Object>> result = null;;
		 try {
			result = (List<Map<String,Object>>) getDAO().findListBySql(sql);
			if(result != null && result.size() > 0)
			 {
				 System.out.println(result.get(0).get("ORDER_NO"));
				 String tempNo = (String)result.get(0).get("MAX(ORDER_NO)");
				 tempNo = tempNo.substring(11, 16);
				 int temp = Integer.parseInt(tempNo);
				 if(temp+1 < 10)
					 return "0000" + String.valueOf(temp+1);
				 else if(temp+1 < 100)
					 return "000" + String.valueOf(temp+1);
				 else if(temp+1 < 1000)
					 return "00" + String.valueOf(temp+1);
				 else if(temp+1 < 10000)
					 return "0" + String.valueOf(temp+1); 
				 else{
					 return String.valueOf(temp+1);
				 }
			 }
			 else {
				return "00001";
			}
	    	} catch (Exception e) {
			// TODO Auto-generated catch block
	    		return "00001";
		 }
		 
		
   }
	
	public List<?> queryOkOrder(String openid){
		String sql = "select * from TK_RECHARGE_ORDER_TODAY WHERE IS_OK=1 AND USER_ID='"+openid+"' order by ORDER_NO DESC";
		try {
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> okOrder =(List<Map<String,Object>>)getDAO().findListBySql(sql);
			return okOrder;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Object>();
		}
	} 
	 
	public Map<String,Object> queryUnpaidOrder(String openid){
		String sql = "select * from TK_RECHARGE_ORDER_TODAY WHERE IS_OK=0 AND USER_ID='"+openid+"' order by ORDER_NO DESC";
		try {
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> unpaidOrder =(List<Map<String,Object>>)getDAO().findListBySql(sql);
			return unpaidOrder.get(0);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return new HashMap<String,Object>();
		}
	}
	 
	 
	 
	 public boolean deleteUnpaidOrder(String openid){
		 String sql = "delete from TK_RECHARGE_ORDER_TODAY where IS_OK=0 and USER_ID='"+openid+"'";
		 try {
			 getDAO().update(sql);
			 return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	 }
	 
	 
	 public Map<String,Object> queryUser(String openid){
		 String sql = "SELECT * FROM WX_REGISTER WHERE WX_ID ='"+openid+"'";
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> result = (List<Map<String,Object>>) getDAO().findListBySql(sql);
			return result.get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  new HashMap<String,Object>();
		}
		 
	 }
	 
	 
	 
	 
}