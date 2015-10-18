package com.clearnight.oa.login.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.alibaba.druid.util.Base64;
import com.clearnight.oa.login.consts.LoginConsts;

public class LoginUtil {

	/**
	 * 对密码进行加密
	 * @param pwd 要加密的密码
	 * @return 加密后的密码
	 */
	public static String encryption(String pwd){
		
		try {
			MessageDigest md5 =  MessageDigest.getInstance(LoginConsts.OA_ENCRYPTION);
			md5.update(pwd.getBytes());
			return Base64.byteArrayToBase64(md5.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return pwd;
		}
		
	}
}
