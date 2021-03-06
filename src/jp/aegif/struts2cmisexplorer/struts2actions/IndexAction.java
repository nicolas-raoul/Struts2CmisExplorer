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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Struts2 Action support bean for the index page.
 */
public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = -3707926965856705325L;

	/**
	 * Struts2 execution.
	 */
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * Whether the user is currently logged in or not.
	 */
	public boolean getLoggedIn() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String loggedIn = (String)session.get("logged-in");
		return loggedIn.equals("true");		
	}
}