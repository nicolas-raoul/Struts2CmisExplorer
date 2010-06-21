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
package jp.aegif.struts2cmisexplorer.domain;

import jp.aegif.struts2cmisexplorer.domain.exceptions.ConnectionFailedException;

/**
 * Facade to access a repository.
 */
public interface RepositoryClientFacade {

	/**
	 * List the content of a folder.
	 * More specifically, get a page of items from the given folder, starting at the given offset.
	 */
	NodesListPage getNodesListPage(String folderId, int skipCount)
			throws ConnectionFailedException;

	/**
	 * Get a document.
	 */
	Document getDocument(String fileId)
			throws ConnectionFailedException;

	/**
	 * Test the connection to the CMIS server.
	 */
	boolean testConnection();
	
	/**
	 * Number of results displayed by page (pagination).
	 */
	int getMaxItemsPerPage();

	/**
	 * Get the identifier (Node Reference) of a document, given its path.
	 * Example of path: /Data Dictionary/Messages
	 */
	String getNodeRef(String path) throws ConnectionFailedException;
}