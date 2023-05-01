/*
 * Copyright 2023 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.newasr.api;

import org.topicquests.support.api.IResult;

import com.google.gson.JsonObject;

/**
 * @author jackpark
 *
 */
public interface IAsrDocumentModel {

	/**
	 * {@code data} comes from Kafka in tne {@link IASRDocument} format
	 * @param data
	 * @return
	 */
	boolean acceptDocument(JsonObject data);
	
	IResult putDocument(IDocument d);
	
	IResult getDocument(long id);
	
	IResult updateDocument(IDocument d);

}
