package com.ixiaofan.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.FieldNameHelper;
import org.springframework.data.jpa.domain.Specification;

import com.ixiaofan.tools.SearchFilter.Operator;

public class DynamicSpecification {
	
	public static <T> Specification<T> bySearchFilter(final Class<T> entityClazz,final HttpServletRequest request){
		return bySearchFilter(entityClazz, request, null);
	}
	
	public static <T> Specification<T> bySearchFilter(final Class<T> entityClazz,final List<SearchFilter> searchFilters){
		return bySearchFilter(entityClazz, null, searchFilters);
	}
	
	public static List<SearchFilter> searchRequest(ServletRequest request){
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		Map<String, String[]> searchFilter = request.getParameterMap();
		Set<Entry<String, String[]>> entrySet = searchFilter.entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			String key = entry.getKey();
			if(key.toUpperCase().contains("SEARCH_")){
				String[] values = entry.getValue();
				if(values == null || StringUtils.isBlank(values[0])){
					continue;
				}
				String[] keys = StringUtils.split(key,"_");
				String fieldName = keys[2];
				Operator operator = Operator.valueOf(Operator.class, StringUtils.upperCase(keys[1]));
				String value = values[0];
				filters.add(new SearchFilter(fieldName, operator, value));
			}
		}
		return filters;
	}
	
	public static <T> Specification<T> bySearchFilter(final Class<T> entityClazz,final HttpServletRequest request,final List<SearchFilter> searchFilters){
		return new Specification<T>() {
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				List<SearchFilter> filters = new ArrayList<SearchFilter>();
				if(request!=null){
					List<SearchFilter> requestFilters = searchRequest(request);
					filters.addAll(requestFilters);
				}
				if(searchFilters!=null&&searchFilters.size()>0){
					filters.addAll(searchFilters);
				}
				if(filters!=null&&filters.size()>0){
					for (SearchFilter searchFilter : filters) {
						String fieldName = searchFilter.getFieldName();
						Operator operator = searchFilter.getOperator();
						Object value = searchFilter.getValue();
						String[] names = StringUtils.split(fieldName, ".");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}

						switch (operator) {
						case EQ:
							list.add(cb.equal(expression, value));
							break;
						case LIKE:
							list.add(cb.like(expression, "%"+value+"%"));
							break;
						case GT:
							list.add(cb.greaterThan(expression, (Comparable)value));
							break;
						case LT:
							list.add(cb.lessThan(expression, (Comparable)value));
							break;
						case GTE:
							list.add(cb.greaterThanOrEqualTo(expression, (Comparable)value));
							break;
						case LTE:
							list.add(cb.lessThanOrEqualTo(expression, (Comparable)value));
							break;
						}
					}
				}
				
				/*Map<String, String[]> searchFilter = request.getParameterMap();
				Set<Entry<String, String[]>> entrySet = searchFilter.entrySet();
				for (Entry<String, String[]> entry : entrySet) {
					String key = entry.getKey();
					if(!key.toUpperCase().contains("SEARCH_")){
						continue;
					}
					String[] value = entry.getValue();
					if(value == null || StringUtils.isBlank(value[0])){
						continue;
					}
					String[] keys = StringUtils.split(key,"_");
					String[] names = StringUtils.split(keys[2], ".");
					Path expression = root.get(names[0]);
					for (int i = 1; i < names.length; i++) {
						expression = expression.get(names[i]);
					}
					
				    switch (StringUtils.upperCase(keys[1])) {
					case "EQ":
						list.add(cb.equal(expression, value[0]));
						break;
					case "LIKE":
						list.add(cb.like(expression, "%"+value[0]+"%"));
						break;
					case "GT":
						list.add(cb.greaterThan(expression, value[0]));
						break;
					case "LT":
						list.add(cb.lessThan(expression, value[0]));
						break;
					case "GET":
						list.add(cb.greaterThanOrEqualTo(expression, value[0]));
						break;
					case "LET":
						list.add(cb.lessThanOrEqualTo(expression, value[0]));
						break;
					}
				}*/
				if(list.size()>0){
					return cb.and(list.toArray(new Predicate[list.size()]));
				}
				return cb.conjunction();
			}
		};
	}
}
