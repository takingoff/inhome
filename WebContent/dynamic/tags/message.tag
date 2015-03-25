<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="content" type="java.lang.String" required="true" description="消息内容" %>
<%@ attribute name="type" type="java.lang.String" description="消息类型：info、success、warning、error、loading"%>
<script type="text/javascript">
	alert("it's awosome!!"+"${content}" );
</script>
<c:if test="${not empty content}">

	<script type="text/javascript">
		alert("dfadf");
	</script>
</c:if>