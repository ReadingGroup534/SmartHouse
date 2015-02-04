package com.smarthouse.impl;

/**
 * 处理server和app直接流的接口
 *@author liyangchao
 */
public interface HttpDao {
	
	
	/**
	 * 根据网址解析网页内容返回给app
	 * @param url
	 * @return
	 */
	public String GetContentFromUrl(String url);
}
