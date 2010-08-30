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
				<img src="./images/space-icon-default.gif" /><s:property value="name" />
			</a>
		</s:if>
		<s:else>
			<a href="
				<s:url action="SendFile">
					<s:param name="node"><s:property value="id" /></s:param>
				</s:url>
			">
				<s:if test="#node.mimetype == 'application/x-javascript'">
					<img src="./images/js.gif" />
				</s:if>
				<s:elseif test="#node.mimetype == 'text/plain'">
					<img src="./images/text-file-32.png" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'application/msword'">
					<img src="./images/doc-file-32.png" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'text/xml'">
					<img src="./images/xml.gif" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'image/gif'">
					<img src="./images/img-file-32.png" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'image/jpeg'">
					<img src="./images/img-file-32.png" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'image/jpeg2000'">
					<img src="./images/jpg.gif" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'video/mpeg'">
					<img src="./images/mpeg.gif" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'audio/x-mpeg'">
					<img src="./images/mpg.gif" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'video/mp4'">
					<img src="./images/mp4.gif" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'video/mpeg2'">
					<img src="./images/mp2.gif" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'application/pdf'">
					<img src="./images/pdf-file-32.png" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'image/png'">
					<img src="./images/img-file-32.png" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'application/vnd.powerpoint'">
					<img src="./images/ppt-file-32.png" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'audio/x-wav'">
					<img src="./images/wmv.gif" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'application/vnd.excel'">
					<img src="./images/xls-file-32.png" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'application/zip'">
					<img src="./images/zip.gif" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'application/vnd.openxmlformats-officedocument.wordprocessingml.document'">
					<img src="./images/doc-file-32.png" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'">
					<img src="./images/xls-file-32.png" />
				</s:elseif>
				<s:elseif test="#node.mimetype == 'application/vnd.openxmlformats-officedocument.presentationml.presentation'">
					<img src="./images/ppt-file-32.png" />
				</s:elseif>
				<s:else>
					<img src="./images/generic-file-32.png" />
				</s:else>
				<s:property value="name" />
					<br/><span class="style15">
						size
						<s:property value="size" />
					</span>
			
					<br/><span class="style15">
						last updated by
						<s:property value="lastModifiedBy" />
					</span>
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