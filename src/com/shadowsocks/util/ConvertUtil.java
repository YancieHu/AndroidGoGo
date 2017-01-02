package com.shadowsocks.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
				if(Ping(infoBean.getServer())){
					totalBuffer.append("\n" + "ss://");
					buffer.append(infoBean.getMethod()).append(":")
							.append(infoBean.getPassword()).append("@")
							.append(infoBean.getServer()).append(":")
							.append(infoBean.getServer_port());
					totalBuffer.append(base64(buffer));
				}
			}
		}
		return totalBuffer.toString();
	}

	public static boolean Ping(String str) {
		boolean resault = false;
		Process p;
		try {
			// ping -c 3 -w 100 中 ，-c 是指ping的次数 3是指ping 3次 ，-w 100
			// 以秒为单位指定超时间隔，是指超时时间为100秒
			p = Runtime.getRuntime().exec("ping -c 3 -w 5 " + str);
			int status = p.waitFor();
			InputStream input = p.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			if (status == 0) {
				resault = true;
			} else {
				resault = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return resault;
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
