package com.bypay.util;

import java.util.HashMap;
import java.util.Map;

public class PageUtil {
	private static Map<String, Object> pageMap = new HashMap<String, Object>();

	public static Map<String, Object> getPageMap(int page, int pageSize) {
		pageMap.put("page", (page - 1) * pageSize);
		pageMap.put("pageSize", pageSize);
		return pageMap;
	}
}
