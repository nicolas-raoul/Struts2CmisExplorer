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

import jp.aegif.struts2cmisexplorer.domain.Credentials;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Parent class for Struts2 Action support beans that need user and password.
 */
abstract class AuthenticatedAction extends ActionSupport {

	private static final long serialVersionUID = 4132296228128594877L;

	/**
	 * Get user and password.
	 */
	public Credentials getCredentials() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String)session.get("user");
		String password = (String)session.get("password");
		return new Credentials(user, password);
	}
}
