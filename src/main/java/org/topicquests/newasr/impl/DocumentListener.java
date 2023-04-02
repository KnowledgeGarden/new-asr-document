/**
 * 
 */
package org.topicquests.newasr.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.topicquests.backside.kafka.consumer.api.IMessageConsumerListener;
import org.topicquests.newasr.ASRDocumentEnvironment;
import org.topicquests.newasr.api.IAsrDocumentModel;
import org.topicquests.newasr.api.IKafkaDispatcher;
import org.topicquests.newasr.util.JsonUtil;

import com.google.gson.JsonObject;

/*
 * Copyright 2023 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
public class DocumentListener implements IKafkaDispatcher, IMessageConsumerListener {
	private ASRDocumentEnvironment environment;
	private IAsrDocumentModel model;
	private JsonUtil util;

	/**
	 * 
	 */
	public DocumentListener(ASRDocumentEnvironment env) {
		environment =env;
		model = environment.getModel();
		util = new JsonUtil();
	}

	@Override
	public boolean acceptRecord(ConsumerRecord record) {
		String json = (String)record.value();
		environment.logDebug("SentenceyListener.acceptRecord "+json);
		boolean result = false;
		if (json == null)
			return result;
		try {
			JsonObject data = util.parse(json);
			result = model.acceptDocument(data);
		} catch (Exception e) {
			environment.logError("SentenceyListener: "+e.getMessage(), e);
			e.printStackTrace();
		}

		return result;	
	}

}
