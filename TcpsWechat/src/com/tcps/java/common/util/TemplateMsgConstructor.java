package com.tcps.java.common.util;

import java.util.ArrayList;
import java.util.List;
import org.sword.wechat4j.message.template.TemplateMsgBody;
import org.sword.wechat4j.message.template.TemplateMsgData;
import com.tcps.java.common.model.NewTemplateMsg;



public class TemplateMsgConstructor {
	public  boolean payOk(String toUserName, double sumMoney, String orderTime) {
		NewTemplateMsg templateMsg = new NewTemplateMsg();
		TemplateMsgBody templateMsgBody = new TemplateMsgBody();
		templateMsgBody.setTemplateId("TpS17E4KxCMqprEsBvhBCg_qiGT4g4aFOomDS6BYM1g");
		templateMsgBody.setTouser(toUserName);
	//	templateMsgBody.setUrl("http://www.baidu.com");
		TemplateMsgData first = new TemplateMsgData();
		first.setName("first");
		first.setColor("#173177");
		first.setValue("恭喜您，交易成功~~\n");
		TemplateMsgData remark = new TemplateMsgData();
		remark.setName("remark");
		remark.setColor("#173177");
		remark.setValue("欢迎您下次继续购买");
		List<TemplateMsgData> keynode = new ArrayList<TemplateMsgData>();
		for(int i = 1; i < 4; i++)
		{
			TemplateMsgData temp = new TemplateMsgData();
			temp.setColor("#173177");
			temp.setName("keynode"+i);
			if(i==1)
				temp.setValue((sumMoney-0.5)+"元IC卡额度\n");
			else if(i==2)
				temp.setValue(sumMoney+"元\n");
			else
				temp.setValue(orderTime+"\n");
			keynode.add(temp);
		}
		List<TemplateMsgData> data = new ArrayList<TemplateMsgData>();
		data.add(first);
		data.addAll(keynode);
		data.add(remark);
		templateMsgBody.setData(data);
		templateMsg.send(templateMsgBody);		
		return true;
	}
	
	public  boolean showUser(String toUserName, String cardid, String city) {
		NewTemplateMsg templateMsg = new NewTemplateMsg();
		TemplateMsgBody templateMsgBody = new TemplateMsgBody();
		templateMsgBody.setTemplateId("ftoUPVuOTjiWVGDG-07Z0hsSERzGH3l_HFtsSGpJT1Y");
		templateMsgBody.setTouser(toUserName);
	//	templateMsgBody.setUrl("http://www.baidu.com");
		TemplateMsgData first = new TemplateMsgData();
		first.setName("first");
		first.setColor("#173177");
		first.setValue("尊敬的用户：您好\n");
		TemplateMsgData remark = new TemplateMsgData();
		remark.setName("remark");
		remark.setColor("#173177");
		remark.setValue("感谢您的使用！");
		List<TemplateMsgData> keynode = new ArrayList<TemplateMsgData>();
		for(int i = 1; i < 4; i++)
		{
			TemplateMsgData temp = new TemplateMsgData();
			temp.setColor("#173177");
			temp.setName("keynode"+i);
			if(i==1)
				temp.setValue(toUserName+"\n");
			else if(i==2)
				temp.setValue(cardid+"\n");
			else
				temp.setValue(city+"\n");
			keynode.add(temp);
		}
		List<TemplateMsgData> data = new ArrayList<TemplateMsgData>();
		data.add(first);
		data.addAll(keynode);
		data.add(remark);
		templateMsgBody.setData(data);
		templateMsg.send(templateMsgBody);		
		return true;
	}
}
