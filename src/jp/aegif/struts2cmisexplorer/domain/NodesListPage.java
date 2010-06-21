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

import java.util.List;

/**
 * Listing/Search results are broken down into pages. This object describes such a page.  
 */
public class NodesListPage {

	/**
	 * Nodes in this page.
	 */
	private List<Node> nodes;
	
	/**
	 * Total number of nodes, not only in this page, but all results for this space/search. 
	 */
	private long totalNumberOfNodes;
	
	/**
	 * Constructor.
	 */
	public NodesListPage (List<Node> nodes, long totalNumberOfNodes) {
		this.nodes = nodes;
		this.totalNumberOfNodes = totalNumberOfNodes;
	}

	/**
	 * Getters.
	 */
	public List<Node> getNodes() {
		return nodes;
	}

	public long getTotalNumberOfNodes() {
		return totalNumberOfNodes;
	}
}
