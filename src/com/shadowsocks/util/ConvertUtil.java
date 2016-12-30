package com.shadowsocks.util;

import java.util.ArrayList;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Base64;

import com.shadow.bean.ShadowInfoBean;
import com.shadow.bean.ShadowTotalBean;

public class ConvertUtil {

	public static String convertBean(ShadowTotalBean bean) {
		if (bean == null || bean.getConfigs() == null
				|| bean.getConfigs().isEmpty()) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		StringBuffer totalBuffer = new StringBuffer();
		ArrayList<ShadowInfoBean> infoBeans = bean.getConfigs();
		for (int i = 0; i < infoBeans.size(); i++) {
			ShadowInfoBean infoBean = infoBeans.get(i);
			if (infoBean != null) {
				totalBuffer.append("\n" + "ss://");
				buffer.append(infoBean.getMethod()).append(":")
						.append(infoBean.getPassword()).append("@")
						.append(infoBean.getServer()).append(":")
						.append(infoBean.getServer_port());
				totalBuffer.append(base64(buffer));
			}
		}
		return totalBuffer.toString();
	}

	public static String base64(StringBuffer buffer) {
		String baseInfo = Base64.encodeToString(buffer.toString().getBytes(),
				Base64.DEFAULT);
		buffer.setLength(0);
		return baseInfo;
	}

	/**
	 * 实现文本复制功能
	 * 
	 * @param content
	 */
	public static void copy(String content, Context context) {
		// 得到剪贴板管理器
		ClipboardManager cmb = (ClipboardManager) context
				.getSystemService(Context.CLIPBOARD_SERVICE);
		cmb.setPrimaryClip(ClipData.newPlainText(null, content));
	}
}
