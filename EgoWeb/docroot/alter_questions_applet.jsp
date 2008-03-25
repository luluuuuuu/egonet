<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">

<%--
 * <p>Title: Egocentric Networks Web Client</p>
 * <p>Description: Subject Interview Client</p>
 * <p>Copyright: Copyright (c) 2002 - 2004 </p>
 * <p>Company: Endless Loop Software</p>
 * @author Peter Schoaff
 *
 *  $Id: alter_questions_applet.jsp,v 1.1 2004/05/14 15:06:39 admin Exp $
 --%>

<%@ page import="java.util.*" %>
<%@ page import="org.jboss.logging.Logger" %>
<%@ page import="com.endlessloopsoftware.egonet.web.WebShared" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>

<%
		Logger logger = Logger.getLogger(this.getClass().getName());
		logger.debug("enter");
%>

<layout:html locale="true" styleClass="FORM" key='global.title'>

	<html:errors property="org.apache.struts.action.GLOBAL_ERROR"/>
	
	<layout:form width="95%" action="/alterquestionsSubmit" onsubmit="return validateAlterQuestionsForm(this)" styleClass="FORM" key="alterquestions.title">

		<bean:define id="interviewId"		name="alterQuestionForm" property="interviewId" 	type="java.lang.Long"/>
		<bean:define id="position"			name="alterQuestionForm" property="position" 		type="java.lang.Integer"/>
	 
		<tr>
			<td colspan="2" align="center">

				<%! String mode = WebShared.ALTER_QUESTION_MODE; %>

				<%@ include file="applet.jsp" %>  
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<p>&nbsp;</p>
			</td>
		</tr>

	 	<logic:iterate 	id="answer" 
	 							name="alterQuestionForm" 
	 							property="answers" 
	 							indexId="index"
	 							type="com.endlessloopsoftware.egonet.util.AnswerDataValue">
			<%@ include file="/WEB-INF/web/show_question.jsp" %>  
		</logic:iterate>
	 
	 	 <tr>&nbsp</tr>

		<layout:row>
			<layout:formActions>
				<layout:submit property="submit" value="Submit"/>   
				<layout:reset/>      
			</layout:formActions>
		 </layout:row>
		 
	</layout:form>

</layout:html>