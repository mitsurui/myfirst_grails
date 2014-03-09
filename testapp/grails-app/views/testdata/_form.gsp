<%@ page import="testapp.Testdata" %>



<div class="fieldcontain ${hasErrors(bean: testdataInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="testdata.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${testdataInstance?.name}"/>

</div>

