/*
 * Copyright 2023 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.newasr.impl;

import org.topicquests.newasr.ASRDocumentEnvironment;
import org.topicquests.newasr.api.IAsrDocumentModel;
import org.topicquests.newasr.api.IDocument;
import org.topicquests.newasr.api.IDocumentProvider;
import org.topicquests.support.api.IResult;

import com.google.gson.JsonObject;

/**
 * @author jackpark
 *
 */
public class ASRDocumentModel implements IAsrDocumentModel {
	private ASRDocumentEnvironment environment;
	private IDocumentProvider database;
	/**
	 * 
	 */
	public ASRDocumentModel(ASRDocumentEnvironment env) {
		environment = env;
		database = new PostgresDocumentProvider(environment);
	}

	@Override
	public boolean acceptDocument(JsonObject data) {
		IDocument d = new ASRDocument(data);
		putDocument(d); //TODO we need a DocumentThread to handle all of these
		return true; //default
	}

	@Override
	public IResult putDocument(IDocument d) {
		return database.putDocument(d);
	}

	@Override
	public IResult getDocument(long id) {
		return database.getDocument(id);
	}

	@Override
	public IResult updateDocument(IDocument d) {
		return database.updateDocument(d);
	}

}
