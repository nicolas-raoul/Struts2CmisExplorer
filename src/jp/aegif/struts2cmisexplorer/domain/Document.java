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

import java.io.InputStream;

/**
 * In Alfresco and CMIS vocabulary, a "document" in a file in the repository.
 * It can not be a folder. 
 */
public class Document {

	/**
	 * Input stream from the file.
	 */
    private InputStream inputStream;
    
    /**
     * Content type of the file (MIME, for instance text/html)
     */
    private String contentType;
	
    /**
     * Size of the file.
     * It is used by web browsers to show a progression bar.
     */
	private long contentLength;
	
	/**
	 * Name of the file.
	 */
	private String filename;

	/**
	 * Constructor.
	 */
	public Document(InputStream inputStream, String contentType, long contentLength, String filename) {
		this.inputStream = inputStream;
		this.contentType = contentType;
		this.contentLength = contentLength;
		this.filename = filename;
	}

	/**
	 * Getters.
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	public String getContentType() {
		return contentType;
	}
	
	public long getContentLength() {
		return contentLength;
	}
	
	public String getFilename() {
		return filename;
	}
}