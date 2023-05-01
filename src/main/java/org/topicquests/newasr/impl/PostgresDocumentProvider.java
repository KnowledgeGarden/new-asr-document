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
import org.topicquests.newasr.api.IParagraph;
import org.topicquests.newasr.util.JsonUtil;
import org.topicquests.pg.PostgresConnectionFactory;
import org.topicquests.pg.api.IPostgresConnection;
import org.topicquests.support.ResultPojo;
import org.topicquests.support.api.IResult;

import com.google.gson.JsonObject;

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
		environment.logDebug("PutDocument\n"+sql+"\n"+doc.getData());
		IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    try { //TODO Transaction?
	    	conn = dbDriver.getConnection();
	    	//doc_id, data)
	    	int count = 2;
	    	//JsonObject jo = null;
	    	// we do not have id - that's returned
	    	Object [] vals = new Object[count];
	    	vals[0] = doc.getData().toString();
	    	vals[1] = doc.getType();
	    	IResult rx = conn.executeSelect(sql, vals);
		    if (rx.hasError())
				result.addErrorString(rx.getErrorString());
		    ResultSet rs = (ResultSet)rx.getResultObject();
		    if (rs != null) {
		    	if (rs.next())
		    		result.setResultObject(new Long(rs.getLong("id")));
		    }
	    	
	    } catch (Exception e) {
		     result.addErrorString("PDD-Put "+e.getMessage());
		     environment.logError("PDD-Put "+result.getErrorString(), e);
		} finally {
		    conn.closeConnection(result);
		}
		environment.logDebug("PutDocument+ "+result.getResultObject());
		return result;
	}

	@Override
	public IResult updateDocument(IDocument doc) {
		String sql = IDocumentQueries.UPDATE_DOCUMENT;
	    IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    long id = doc.getId();
	    try { 
	    	conn = dbDriver.getConnection();
	    	Object [] vals = new Object[2];
	    	vals[0] = doc.getData().toString();
	    	vals[1] = new Long(id);
	    	
	    	IResult rx = conn.executeSQL(sql, vals);
		    if (rx.hasError())
				result.addErrorString(rx.getErrorString());
	    } catch (Exception e) {
		     result.addErrorString("PDD-US "+id+" "+e.getMessage());
		     environment.logError("PDD-US "+id+" "+result.getErrorString(), e);
		} finally {
		    conn.closeConnection(result);
		}
		return result;
	}

	@Override
	public IResult getDocument(long docId) {
		String sql = IDocumentQueries.GET_DOCUMENT; 
	    IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    try { 
	    	conn = dbDriver.getConnection();
	    	Object [] vals = new Object[1];
	    	vals[0] = new Long(docId);
	    	
	    	IResult rx = conn.executeSelect(sql, vals);
		    if (rx.hasError())
				result.addErrorString(rx.getErrorString());
		    ResultSet rs = (ResultSet)rx.getResultObject();
		    if (rs != null) {
		    	//doc_id, data
		    	if (rs.next()) {
		    		String json = rs.getString("data");
		    		JsonObject jo = util.parse(json);
		    		IDocument p = new ASRDocument(jo);
		    		result.setResultObject(p);
		    	}
		    }
	    
	    } catch (Exception e) {
		     result.addErrorString("PDD-GT "+docId+" "+e.getMessage());
		     environment.logError("PDD-GT "+docId+" "+result.getErrorString(), e);
		} finally {
		    conn.closeConnection(result);
		}
		return result;
	}

	@Override
	public IResult removeDocument(long docId) {
		// TODO Auto-generated method stub
		return null;
	}

}
