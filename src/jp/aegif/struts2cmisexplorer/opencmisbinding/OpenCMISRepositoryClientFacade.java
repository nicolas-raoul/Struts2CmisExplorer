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
package jp.aegif.struts2cmisexplorer.opencmisbinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.aegif.struts2cmisexplorer.domain.Document;
import jp.aegif.struts2cmisexplorer.domain.Node;
import jp.aegif.struts2cmisexplorer.domain.NodesListPage;
import jp.aegif.struts2cmisexplorer.domain.RepositoryClientFacade;
import jp.aegif.struts2cmisexplorer.domain.exceptions.ConnectionFailedException;
import jp.aegif.struts2cmisexplorer.domain.exceptions.UnauthorizedException;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.OperationContext;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.ObjectIdImpl;
import org.apache.chemistry.opencmis.client.runtime.OperationContextImpl;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.exceptions.CmisConnectionException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisRuntimeException;

/**
 * Implementation of a repository facade, using OpenCMIS.
 */
public class OpenCMISRepositoryClientFacade implements RepositoryClientFacade {

	/////////////////////////////////////////////////////////
	///////////////// CONFIGURATION : BEGIN /////////////////
	/////////////////////////////////////////////////////////
	
	/**
	 * URL of the CMIS repository.
	 */
	protected final static String CMIS_ATOMPUB_URL = "http://localhost:8080/alfresco/service/cmis";

	/**
	 * Number of results displayed by page (pagination).
	 * Any positive value is fine.
	 */
	private final static int MAX_ITEMS_PER_PAGE = 5;
	
	/////////////////////////////////////////////////////////
	////////////////// CONFIGURATION : END //////////////////
	/////////////////////////////////////////////////////////
	
	/**
	 * Username for the CMIS session.
	 */
	private String user;
	
	/**
	 * Password for the CMIS session.
	 */
	private String password;
	
	/**
	 * Cache for the session object, so that only one session is created by user request.
	 */
	private Session session;
	
	/**
	 * CMIS operation context, it contains a pagination setting.
	 */
	private OperationContext operationContext;
	
	/**
	 * Constructor.
	 */
	public OpenCMISRepositoryClientFacade(String user, String password) {
		this.user = user;
		this.password = password;
		operationContext = new OperationContextImpl();
		operationContext.setMaxItemsPerPage(MAX_ITEMS_PER_PAGE);
	}
	
	/**
	 * Get a CMIS session.
	 */
	private Session getSession() throws ConnectionFailedException {
		if (session != null)
			return session;
		
		// Default factory implementation of client runtime.
		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		Map<String, String> parameter = new HashMap<String, String>();

		// User credentials.
		parameter.put(SessionParameter.USER, user);
		parameter.put(SessionParameter.PASSWORD, password);

		// Connection settings.
		parameter.put(SessionParameter.ATOMPUB_URL, CMIS_ATOMPUB_URL);
		parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
		
		// Session locale.
		parameter.put(SessionParameter.LOCALE_ISO3166_COUNTRY, "");
		parameter.put(SessionParameter.LOCALE_ISO639_LANGUAGE, "en");
		parameter.put(SessionParameter.LOCALE_VARIANT, "US");

		// Create session.
		// To avoid extra configuration: only one repository per CMIS server.
		Session session = null;
		try {
			Repository soleRepository = sessionFactory.getRepositories(parameter).get(0);
			session = soleRepository.createSession();
		}
		catch(CmisConnectionException e) { 
			throw new ConnectionFailedException(e);
		}
		catch(CmisRuntimeException e) {
			throw new UnauthorizedException(e);
		}
		
		session.setDefaultContext(operationContext);
		return session;
	}
	
	/**
	 * List the content of a folder.
	 * More specifically, get a page of items from the given folder, starting at the given offset.
	 */
	@Override
	public NodesListPage getNodesListPage(String folderId, int skipCount) throws ConnectionFailedException {
		CmisObject object = getSession().getObject(new ObjectIdImpl(folderId));
		Folder folder = (Folder)object;
		ItemIterable<CmisObject> children = folder.getChildren();
		long totalNumberOfNodes = children.getTotalNumItems(); 
		ItemIterable<CmisObject> page = children.skipTo(skipCount).getPage();
		Iterator<CmisObject> iterator = page.iterator();
		List<Node> list = new ArrayList<Node>((int) children.getTotalNumItems());
		while(iterator.hasNext()) {
			CmisObject child = iterator.next();
			list.add(new Node(child.getId(), child.getName(), isSpace(child)));
		}
		return new NodesListPage(list, totalNumberOfNodes);
	}

	/**
	 * Get the identifier (Node Reference) of a document, given its path.
	 * Example of path: /Data Dictionary/Messages
	 */
	@Override
	public String getNodeRef(String path) throws ConnectionFailedException {
		CmisObject object = getSession().getObjectByPath(path);
		return object.getId();
	}

	/**
	 * Get a document.
	 */
	@Override
	public Document getDocument(String fileId) throws ConnectionFailedException {
		CmisObject object = getSession().getObject(new ObjectIdImpl(fileId));
		org.apache.chemistry.opencmis.client.api.Document openCMISDocument =
			(org.apache.chemistry.opencmis.client.api.Document)object;
		Document document = new Document(
				openCMISDocument.getContentStream().getStream(),
				openCMISDocument.getContentStreamMimeType(),
				openCMISDocument.getContentStreamLength(),
				openCMISDocument.getName()
		);
		return document;
	}

	/**
	 * Test the connection to the CMIS server.
	 */
	@Override
	public boolean testConnection() {
		// TODO Implement this method.
		// Because authentication will be thrown away, implementation is probably not really necessary.
		return true;
	}
	
	/**
	 * Number of results displayed by page (pagination).
	 */
	@Override
	public int getMaxItemsPerPage() {
		return MAX_ITEMS_PER_PAGE;
	}
	
	/**
	 * Whether the given object is a space (folder) or not.
	 */
	private static boolean isSpace(CmisObject object) {
		return object.getBaseTypeId().value().equals(ObjectType.FOLDER_BASETYPE_ID);
	}
}
