package com.cn.wct.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class MapSortUtil {
	// 比较器类
	public class MapKeyComparator implements Comparator<String> {
		public int compare(String str1, String str2) {
			return str1.compareTo(str2);
		}
	}

	class MapValueComparator implements Comparator<Map.Entry<String, Object>> {

		@Override
		public int compare(Entry<String, Object> me1, Entry<String, Object> me2) {

			return me1.getValue().toString().compareTo(me2.getValue().toString());
		}
	}

	/**
	 * 使用 Map按Key进行排序
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, Object>  sortMapByKey(Map<String, Object> dataMap) {
		Map<String, Object> sortMap = new TreeMap<String, Object>(new MapSortUtil().new MapKeyComparator());
		sortMap.putAll(dataMap);
		return sortMap;
		
	}

	/**
	 * 使用 Map按value进行排序
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, Object> sortMapByValue(Map<String, Object> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}
		Map<String, Object> sortedMap = new LinkedHashMap<String, Object>();
		List<Map.Entry<String, Object>> entryList = new ArrayList<Map.Entry<String, Object>>(oriMap.entrySet());
		Collections.sort(entryList, new MapSortUtil().new MapValueComparator());

		Iterator<Map.Entry<String, Object>> iter = entryList.iterator();
		Map.Entry<String, Object> tmpEntry = null;
		while (iter.hasNext()) {
			tmpEntry = iter.next();
			sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
		}
		return sortedMap;
	}
}
