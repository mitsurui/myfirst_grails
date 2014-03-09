<%@ page import="glossary.Word" %>



<div class="fieldcontain ${hasErrors(bean: wordInstance, field: 'en', 'error')} ">
	<label for="en">
		<g:message code="word.en.label" default="En" />
		
	</label>
	<g:textField name="en" value="${wordInstance?.en}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wordInstance, field: 'ja', 'error')} ">
	<label for="ja">
		<g:message code="word.ja.label" default="Ja" />
		
	</label>
	<g:textField name="ja" value="${wordInstance?.ja}"/>

</div>

