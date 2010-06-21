/***************************************************************************************
* Copyright (c) 2010 Aegif  - http://aegif.jp                                          *
*                                                                                      *
* This program is free software; you can redistribute it and/or modify it under        *
* the terms of the GNU General Public License as published by the Free Software        *
* Foundation; either version 3 of the License, or (at your option) any later           *
* version.                                                                             *
*                                                                                      *
* This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
* PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
*                                                                                      *
* You should have received a copy of the GNU General Public License along with         *
* this program.  If not, see <http://www.gnu.org/licenses/>.                           *
****************************************************************************************/
package jp.aegif.struts2cmisexplorer.struts2actions;

import java.util.Map;

import jp.aegif.struts2cmisexplorer.opencmisbinding.OpenCMISRepositoryClientFacade;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Struts2 Action support bean for login.
 * This is just a mock of authentication.
 * CMIS is stateless, so we just store the username/password and send them with each request.
 * It will be thrown away when Alfresco SSO is set.
 */
public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = -8621501151940536209L;

	/**
	 * Username of this session's user.
	 */
	private String user;
	
	/**
	 * Password of this session's user.
	 */
	private String password;

	/**
	 * Struts2 execution.
	 */
	@Override
	public String execute() throws Exception {
		if (checkCredentials(user, password)) {
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("user", user);
			session.put("password", password);
			session.put("logged-in", "true");
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	/**
	 * Check whether the username and password can be used to successfully connect
	 * to the CMIS server. 
	 */
	public boolean checkCredentials(String username, String password) {
		new OpenCMISRepositoryClientFacade(user, password).testConnection();
		return true;
	}

	/**
	 * Whether the user is currently logged in or not.
	 */
	public boolean getLoggedIn() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String loggedIn = (String)session.get("logged-in");
		return loggedIn.equals("true");		
	}
	
	/**
	 * Getters / Setters
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}