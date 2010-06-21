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

import java.util.List;

import jp.aegif.struts2cmisexplorer.domain.Credentials;
import jp.aegif.struts2cmisexplorer.domain.Node;
import jp.aegif.struts2cmisexplorer.domain.NodesListPage;
import jp.aegif.struts2cmisexplorer.domain.RepositoryClientFacade;
import jp.aegif.struts2cmisexplorer.domain.exceptions.ConnectionFailedException;
import jp.aegif.struts2cmisexplorer.domain.exceptions.UnauthorizedException;
import jp.aegif.struts2cmisexplorer.opencmisbinding.OpenCMISRepositoryClientFacade;

/**
 * Struts2 Action support bean to get the list of nodes from a particular space.
 * In Alfresco, a folder is called a "space".
 */
public class ShowSpaceAction extends AuthenticatedAction {

	private static final long serialVersionUID = 4597510098838425791L;

	/**
	 * Identifier of the space.
	 * For instance: workspace://SpacesStore/cd79b86c-3068-446f-bd76-61d895de7af1
	 */
	private String space;
	
	/**
	 * Identifier of the space, by path.
	 * Should be provided only when spaceNodeRef is not known.
	 * For instance: /Data Dictionary/Messages
	 */
	private String spacePath;

	/**
	 * Number of results that are skipped when displaying.
	 * It is used for paging results.
	 */
    private int skipCount = 0;

    /**
     * Information about nodes that are to be displayed on the current page. 
     */
	private NodesListPage page;
    
	/**
	 * Struts2 execution.
	 */
	@Override
    public String execute() {
    	try {
			if (space == null) {
				space = getRepositoryClientFacade().getNodeRef(spacePath);
			}
			page = getRepositoryClientFacade().getNodesListPage(space, skipCount);
		} catch (UnauthorizedException e) {
			return "unauthorized";
		} catch (ConnectionFailedException e) {
			return "connection-failed";
		}
        return SUCCESS;
    }
	
	private RepositoryClientFacade getRepositoryClientFacade() {
		RepositoryClientFacade facade = null;
    	Credentials credentials = getCredentials();
    	// TODO Set this OpenCMISRepositoryClientFacade by IoC, in order to be CMIS implementation-independent.
		facade = new OpenCMISRepositoryClientFacade(credentials.getUser(), credentials.getPassword());
		return facade;
	}

    /**
     * The "skip count" to be used if the user clicks on "Previous".
     */
    public int getPreviousSkipCount() {
		int previousSkipCount = skipCount - getRepositoryClientFacade().getMaxItemsPerPage();
		if (previousSkipCount < 0) {
			previousSkipCount = 0;
		}
		return previousSkipCount;
	}
    
    /**
     * The "skip count" to be used if the user clicks on "Next".
     */
    public int getNextSkipCount() {
		return skipCount + getRepositoryClientFacade().getMaxItemsPerPage();
	}

    /**
     * Whether the "Previous" button is needed.
     */
    public boolean getShowPrevious() {
    	return skipCount > 0;
    }

    /**
     * Whether the "Previous" button is needed.
     */
    public boolean getShowNext() {
    	return skipCount + getRepositoryClientFacade().getMaxItemsPerPage() < page.getTotalNumberOfNodes();
    }

    /**
     * Getters / Setters
     */	
    public List<Node> getNodes() {
		return page.getNodes();
	}
    
	public long getTotalNumberOfNodes() {
		return page.getTotalNumberOfNodes();
	}

	public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

	public void setSpacePath(String spacePath) {
		this.spacePath = spacePath;
	}
	
	public void setSkipCount(int skipCount) {
		this.skipCount = skipCount;
	}
}