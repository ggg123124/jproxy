package com.lckp.jproxy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.lckp.jproxy.constant.ApiField;

/**
 * <p>
 * xml 工具类
 * </p>
 *
 * @author LuckyPuppy514
 * @date 2023-03-18
 */
public class XmlUtil {
	XmlUtil() {
	}

	/**
	 * 
	 * 计算 xml 中的 ApiField.INDEXER_ITEM 数量
	 *
	 * @param xml
	 * @return int
	 */
	public static int count(String xml) {
		int count = 0;
		if (StringUtils.isBlank(xml)) {
			return count;
		}
		Matcher matcher = Pattern.compile("<" + ApiField.INDEXER_ITEM + ">[^<]+").matcher(xml);
		while (matcher.find()) {
			count++;
		}
		return count;
	}

	/**
	 * 
	 * 合并
	 *
	 * @param xml1
	 * @param xml2
	 * @return String
	 */
	public static String merger(String xml1, String xml2) {
		int index = xml1.indexOf("<" + ApiField.INDEXER_ITEM + ">");
		if (index == -1) {
			return xml2;
		}
		index = xml2.indexOf("<" + ApiField.INDEXER_ITEM + ">");
		if (index == -1) {
			return xml1;
		}
		StringBuilder builder = new StringBuilder();
		builder.append(xml1.substring(0, xml1.indexOf("</" + ApiField.INDEXER_CHANNEL + ">")));
		builder.append(xml2.substring(xml2.indexOf("<" + ApiField.INDEXER_ITEM + ">")));
		return builder.toString();
	}
}
