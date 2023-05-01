/*
 * Copyright 2023 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.newasr.impl;

import java.sql.ResultSet;

import org.topicquests.newasr.ASRDocumentEnvironment;
import org.topicquests.newasr.api.IDocument;
import org.topicquests.newasr.api.IDocumentProvider;
import org.topicquests.newasr.api.IDocumentQueries;
import org.topicquests.newasr.api.IParagraphQueries;
import org.topicquests.newasr.util.JsonUtil;
import org.topicquests.pg.PostgresConnectionFactory;
import org.topicquests.pg.api.IPostgresConnection;
import org.topicquests.support.ResultPojo;
import org.topicquests.support.api.IResult;

/**
 * @author jackpark
 *
 */
public class PostgresDocumentProvider implements IDocumentProvider {
	private ASRDocumentEnvironment environment;
	private PostgresConnectionFactory dbDriver = null;
	private JsonUtil util;

	/**
	 * 
	 */
	public PostgresDocumentProvider(ASRDocumentEnvironment env) {
		environment = env;
		dbDriver = environment.getDatabaseDriver();
		util = new JsonUtil();
	}

	@Override
	public IResult putDocument(IDocument doc) {
		String sql = IDocumentQueries.PUT_DOCUMENT;
		environment.logDebug("PutDocument\n"+sql+"\n"+d.getData());
		IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    try { //TODO Transaction?
	    	conn = dbDriver.getConnection();
	    	//doc_id, data)
	    	int count = 2;
	    	//JsonObject jo = null;
	    	// we do not have id - that's returned
	    	Object [] vals = new Object[count];
	    	vals[0] = new Long(p.getDocumentId());
	    	vals[1] = p.getData().toString();
	    	IResult rx = conn.executeSelect(sql, vals);
		    if (rx.hasError())
				result.addErrorString(rx.getErrorString());
		    ResultSet rs = (ResultSet)rx.getResultObject();
		    if (rs != null) {
		    	if (rs.next())
		    		result.setResultObject(new Long(rs.getLong("id")));
		    }
	    	
	    } catch (Exception e) {
		     result.addErrorString("PPD-Put "+e.getMessage());
		     environment.logError("PPD-Put "+result.getErrorString(), e);
		} finally {
		    conn.closeConnection(result);
		}
		environment.logDebug("PutParagraph+ "+result.getResultObject());
		return result;
	}

	@Override
	public IResult updateDocument(IDocument doc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResult getDocument(long docId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResult removeDocument(long docId) {
		// TODO Auto-generated method stub
		return null;
	}

}
