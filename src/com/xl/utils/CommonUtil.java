package com.xl.utils;

import java.util.UUID;

public class CommonUtil {
	
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
		return uuid;
	}
	public static void main(String[] args) {
		System.out.println(CommonUtil.getUUID());
	}
}
