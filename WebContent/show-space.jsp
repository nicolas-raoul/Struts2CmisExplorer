<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<jsp:include page="header.inc" />

<!-- Link to index -->
<p><a href="<s:url action="Index"/>">Index</a></p>
<hr/>

<!-- Nodes -->
<s:iterator value="nodes" id="node">
	<p>
		<s:if test="#node.space == true ">
			<a href="
				<s:url action="ShowSpace">
					<s:param name="space"><s:property value="id" /></s:param>
				</s:url>
			">
				[DIR] <s:property value="name" />
			</a>
		</s:if>
		<s:else>
			<a href="
				<s:url action="SendFile">
					<s:param name="node"><s:property value="id" /></s:param>
				</s:url>
			">
				[FILE] <s:property value="name" />
			</a>
		</s:else>
	</p>
</s:iterator>
<hr/>

<!-- Previous page / Next page -->
<p>
	<s:if test="showPrevious == true ">
		<a href="
			<s:url action="ShowSpace">
				<s:param name="space"><s:property value="space" /></s:param>
				<s:param name="skipCount"><s:property value="previousSkipCount" /></s:param>
			</s:url>
		">
			&lt; Previous
		</a>
	</s:if>
	&nbsp;
	<s:property value="totalNumberOfNodes" /> results
	&nbsp;
	<s:if test="showNext == true ">
		<a href="
			<s:url action="ShowSpace">
				<s:param name="space"><s:property value="space" /></s:param>
				<s:param name="skipCount"><s:property value="nextSkipCount" /></s:param>
			</s:url>
		">
			Next &gt;
		</a>
	</s:if>
</p>

<jsp:include page="footer.inc" />