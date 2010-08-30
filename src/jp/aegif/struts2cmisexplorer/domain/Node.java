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

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A Node is a file or a folder of the repository.
 */
public class Node {

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public GregorianCalendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(GregorianCalendar creationDate) {
		this.creationDate = creationDate;
	}

	public GregorianCalendar getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(GregorianCalendar lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	/**
	 * Identifier of this node.
	 */
	private String id;

	/**
	 * Human-readable name of this node.
	 */
	private String name;

	/**
	 * Whether this node is a space or not. In Alfresco, a folder is called a
	 * "space".
	 */
	private boolean space;

	/**
	 * Mimetype of this node
	 */
	private String mimetype;

	/**
	 * CreationDate / LastModificationDate
	 */
	private GregorianCalendar creationDate, lastModificationDate;

	/**
	 * Creator / Modificator
	 */
	private String createdBy, lastModifiedBy;
	
	/**
	 * Size of this node
	 */
	private long size;
	
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * Constructor.
	 */
	public Node(String id, String name, boolean space) {
		this.id = id;
		this.name = name;
		this.space = space;
	}

	/**
	 * Getters / Setters.
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isSpace() {
		return space;
	}

	public void setSpace(boolean space) {
		this.space = space;
	}
}
