<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<jsp:include page="header.inc" />
  
<!-- Login form -->
<p>
	<s:if test="loggedIn == true ">
		Logged in
		(
			<a href="
 						<s:url action="Logout"/>
 				">
						logout
 				</a>
 			)
	</s:if>
	<s:else>
		<s:form action="Login" method="POST">
			<s:textfield name="user" label="User" />
			<br>
			<s:password name="password" label="Password" />
			<br>
			<s:submit value="Login"/>
		</s:form>
	</s:else>
</p>

<hr/>

<!-- Link to the root -->
<p>
		<a href="
			<s:url action="ShowSpace">
				<s:param name="spacePath">
					/
				</s:param>
			</s:url>
		">Explore repository root</a>
</p>

<jsp:include page="footer.inc" />