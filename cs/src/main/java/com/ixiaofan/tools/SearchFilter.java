package com.ixiaofan.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
/**
 * 动态查询对象，封装所有查询规则。
 * @author ixiaofan
 * @version 创建时间：2016年11月9日 下午5:30:10
 */
public class SearchFilter {
	/** 查询字段名称 */
	private String fieldName;
	/** 查询字段查询规则 */
	private Operator operator;
	/** 查询字段的值 */
	private Object value;
	
	
	public SearchFilter(String fieldName, Operator operator, Object value) {
		super();
		this.fieldName = fieldName;
		this.operator = operator;
		this.value = value;
	}

    
	public String getFieldName() {
		return fieldName;
	}


	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


	public Operator getOperator() {
		return operator;
	}


	public void setOperator(Operator operator) {
		this.operator = operator;
	}


	public Object getValue() {
		return value;
	}


	public void setValue(Object value) {
		this.value = value;
	}
	
	/**
	 * searchParams中key的格式为Operator_fieldName
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams){
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		/*map的entrySet方法比通过keySet获取value速度快，建议使用*/
		Set<Entry<String, Object>> entrySet = searchParams.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if(StringUtils.isNotBlank(value.toString())){
				continue;
			}
			String[] names = StringUtils.split(key,"_");
			if(names.length != 3){
				throw new IllegalArgumentException(key
						+ " is not a valid search filter name");
			}
			String fieldName = names[2];
			Operator operator = Operator.valueOf(names[1]);
			SearchFilter filter = new SearchFilter(fieldName, operator, value);
			filters.put(key, filter);
		}
		return filters;
	}
	
	public enum Operator{
		/** 等于 */
		EQ,
		/** 模糊 */
		LIKE,
		/** 大于 */
		GT,
		/** 小于*/
		LT,
		/** 大于等于 */
		GTE,
		/** 小于等于 */
		LTE
	}
}
