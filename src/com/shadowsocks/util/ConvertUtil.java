package com.shadowsocks.util;

import java.util.ArrayList;

import com.shadow.bean.ShadowInfoBean;
import com.shadow.bean.ShadowTotalBean;

public class ConvertUtil {

	public static String convertBean(ShadowTotalBean bean) {
		if (bean == null || bean.getConfigs() == null
				|| bean.getConfigs().isEmpty()) {
			return "";
		}
		StringBuffer buffer=new StringBuffer();
		ArrayList<ShadowInfoBean> infoBeans = bean.getConfigs();
		for(int i=0;i<infoBeans.size();i++){
			ShadowInfoBean infoBean=infoBeans.get(i);
			buffer.append(infoBean.getMethod()).append(":").append(infoBean.getPassword()).append("@").append(infoBean.getServer()).append(":").append(infoBean.getServer_port());
		}
		return null;
	}
}
