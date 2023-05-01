/*
 * Copyright 2023 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.newasr.impl;

import org.topicquests.newasr.ASRDocumentEnvironment;
import org.topicquests.newasr.api.IAsrDocumentModel;
import org.topicquests.newasr.api.IDocument;
import org.topicquests.support.api.IResult;

import com.google.gson.JsonObject;

/**
 * @author jackpark
 *
 */
public class ASRDocumentModel implements IAsrDocumentModel {
	private ASRDocumentEnvironment environment;

	/**
	 * 
	 */
	public ASRDocumentModel(ASRDocumentEnvironment env) {
		environment = env;
	}

	@Override
	public boolean acceptDocument(JsonObject data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IResult putDocument(IDocument d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResult getDocument(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResult updateDocument(IDocument d) {
		// TODO Auto-generated method stub
		return null;
	}

}
