<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fnx" uri="http://java.sun.com/jsp/jstl/functionsx"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.jspxcms.com/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Jspxcms管理平台 - Powered by Jspxcms</title>
<jsp:include page="/WEB-INF/views/commons/head.jsp"></jsp:include>
<script type="text/javascript">
$(function() {
	$("#validForm").validate();
	$("input[name='name']").focus();
});
function confirmDelete() {
	return confirm("<s:message code='confirmDelete'/>");
}
</script>
</head>
<body class="c-body">
<jsp:include page="/WEB-INF/views/commons/show_message.jsp"/>
<%-- ............................................
<form action="save.do" method="post">
	<input type="hidden" id="redirect" name="redirect" value="edit"/>
	姓名：<input type="text" name="name"/><br/>
	性别：<f:radio name="sex" value="M" default="M"/>男 &nbsp;
    	<f:radio name="sex" value="O"/>女<br />
         生日：<input type="text" name="birthDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/><br />
         创建时间：<input type="text" name="createDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/><br />
         邮箱：<input type="text" name="email"/><br />
         头像：<tags:image_upload name="icon" width="128" height="128"/><br />
         备注 ：<input type="text" name="remark"/>    
         <input type="submit" value="提交"/>
</form> --%>
<div class="c-bar margin-top5">
  <span class="c-position"><s:message code="demo"/> - <s:message code="${oprt=='edit' ? 'edit' : 'create'}"/></span>
</div>
<form id="validForm" action="${oprt=='edit' ? 'update' : 'save'}.do" method="post">
<tags:search_params/>
<f:hidden name="id" value="${demo.id}"/>
<input type="hidden" id="redirect" name="redirect" value="edit"/>
<table border="0" cellpadding="0" cellspacing="0" class="in-tb margin-top5">
  <tr>
    <td colspan="4" class="in-opt">
			<shiro:hasPermission name="abc:demo:create">
			<div class="in-btn"><input type="button" value="<s:message code="create"/>" onclick="location.href='create.do?${searchstring}';"<c:if test="${oprt=='create'}"> disabled="disabled"</c:if>/></div>
			<div class="in-btn"></div>
			</shiro:hasPermission>
			<shiro:hasPermission name="abc:demo:copy">
			<div class="in-btn"><input type="button" value="<s:message code="copy"/>" onclick="location.href='create.do?id=${demo.id}&${searchstring}';"<c:if test="${oprt=='create'}"> disabled="disabled"</c:if>/></div>
			</shiro:hasPermission>
			<shiro:hasPermission name="abc:demo:delete">
			<div class="in-btn"><input type="button" value="<s:message code="delete"/>" onclick="if(confirmDelete()){location.href='delete.do?ids=${demo.id}&${searchstring}';}"<c:if test="${oprt=='create'}"> disabled="disabled"</c:if>/></div>
			</shiro:hasPermission>
			<div class="in-btn"></div>
			<div class="in-btn"><input type="button" value="<s:message code="prev"/>" onclick="location.href='edit.do?id=${side.prev.id}&position=${position-1}&${searchstring}';"<c:if test="${empty side.prev}"> disabled="disabled"</c:if>/></div>
			<div class="in-btn"><input type="button" value="<s:message code="next"/>" onclick="location.href='edit.do?id=${side.next.id}&position=${position+1}&${searchstring}';"<c:if test="${empty side.next}"> disabled="disabled"</c:if>/></div>
			<div class="in-btn"></div>
			<div class="in-btn"><input type="button" value="<s:message code="return"/>" onclick="location.href='list.do?${searchstring}';"/></div>
      <div style="clear:both;"></div>
    </td>
  </tr>
  <tr>
    <td class="in-lab" width="15%"><s:message code="demo.icon"/>:</td>
    <td class="in-ctt" width="85%" colspan="3">
    	<tags:image_upload name="icon" value="${demo.icon}" width="128" height="128"/>
    </td>
  </tr>
  <tr>
    <td class="in-lab" width="15%"><em class="required">*</em><s:message code="demo.name"/>:</td>
    <td class="in-ctt" width="35%"><f:text name="name" value="${oprt=='edit' ? (demo.name) : ''}" class="required" maxlength="100" style="width:180px;"/></td>
    <td class="in-lab" width="15%"><em class="required">*</em><s:message code="demo.sex"/>:</td>
    <td class="in-ctt" width="35%">
    	<label><f:radio name="sex" value="M" checked="${demo.sex}" default="M"/><s:message code="male"/></label> &nbsp;
    	<label><f:radio name="sex" value="F" checked="${demo.sex}"/><s:message code="female"/></label>
    </td>
  </tr>
  <%-- <tr>
    <td class="in-lab" width="15%"><c:if test="${oprt=='edit'}"><em class="required">*</em></c:if><s:message code="demo.createDate"/>:</td>
    <td class="in-ctt" width="35%"><input type="text" name="createDate" value="<fmt:formatDate value="${demo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" style="width:180px;"/></td>
  </tr> --%>
  <tr>
    <td class="in-lab" width="15%"><s:message code="demo.email"/>:</td>
    <td class="in-ctt" width="35%"><f:text name="email" value="${demo.email}" class="email" maxlength="100" style="width:180px;"/></td>
  </tr>
  <tr>
    <td class="in-lab" width="15%"><s:message code="demo.birthDate"/>:</td>
    <td class="in-ctt" width="35%"><input type="text" name="birthDate" value="<fmt:formatDate value="${demo.birthDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" style="width:180px;"/></td>
  </tr>
  <tr>
    <td class="in-lab" width="15%"><s:message code="demo.remark"/>:</td>
    <td class="in-ctt" width="85%" colspan="3"><f:textarea name="remark" value="${demo.remark}" style="width:500px;height:100px;"/></td>
  </tr>
  <tr>
    <td colspan="4" class="in-opt">
      <div class="in-btn"><input type="submit" value="<s:message code="save"/>"/></div>
      <div class="in-btn"><input type="submit" value="<s:message code="saveAndReturn"/>" onclick="$('#redirect').val('list');"/></div>
      <c:if test="${oprt=='create'}">
      <div class="in-btn"><input type="submit" value="<s:message code="saveAndCreate"/>" onclick="$('#redirect').val('create');"/></div>
      </c:if>
      <div style="clear:both;"></div>
    </td>
  </tr>
</table>
</form>
</body>
</html>