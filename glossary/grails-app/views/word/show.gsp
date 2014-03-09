
<%@ page import="glossary.Word" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'word.label', default: 'Word')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-word" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-word" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list word">
			
				<g:if test="${wordInstance?.en}">
				<li class="fieldcontain">
					<span id="en-label" class="property-label"><g:message code="word.en.label" default="En" /></span>
					
						<span class="property-value" aria-labelledby="en-label"><g:fieldValue bean="${wordInstance}" field="en"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wordInstance?.ja}">
				<li class="fieldcontain">
					<span id="ja-label" class="property-label"><g:message code="word.ja.label" default="Ja" /></span>
					
						<span class="property-value" aria-labelledby="ja-label"><g:fieldValue bean="${wordInstance}" field="ja"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:wordInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${wordInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
