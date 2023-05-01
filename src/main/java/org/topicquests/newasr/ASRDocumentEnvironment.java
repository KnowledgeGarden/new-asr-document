/*
 * Copyright 2023 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.newasr;

import java.util.Map;

import org.topicquests.backside.kafka.consumer.api.IMessageConsumerListener;
import org.topicquests.newasr.api.IAsrDocumentModel;
import org.topicquests.newasr.api.IKafkaDispatcher;
import org.topicquests.newasr.impl.ASRBaseEnvironment;
import org.topicquests.newasr.impl.DocumentListener;
import org.topicquests.newasr.kafka.KafkaHandler;
import org.topicquests.pg.PostgresConnectionFactory;
import org.topicquests.support.config.Configurator;

/**
 * @author jackpark
 *
 */
public class ASRDocumentEnvironment extends ASRBaseEnvironment {
	private IAsrDocumentModel model = null;
	private KafkaHandler documentConsumer;
	private Map<String,Object>kafkaProps;
	private IKafkaDispatcher documentListener;
	private DocumentEngine docEngine;
	private PostgresConnectionFactory dbDriver = null;

	

	/**
	 * @param configPath
	 * @param logConfigPath
	 */
	public ASRDocumentEnvironment() {
		super("asr-document-config.xml");
		kafkaProps = Configurator.getProperties("kafka-topics.xml");
		String schemaName = getStringProperty("DatabaseSchema");
		String dbName = getStringProperty("DatabaseName");
		dbDriver = new PostgresConnectionFactory(dbName, schemaName);

		documentListener = new DocumentListener(this);
		String cTopic = (String)kafkaProps.get("DocumentConsumerTopic");
		documentConsumer = new KafkaHandler(this, (IMessageConsumerListener)documentListener, cTopic, AGENT_GROUP);
		model = null; //TODO
		docEngine = new DocumentEngine(this);
		
		// shutdown hook
		Runtime.getRuntime().addShutdownHook(new Thread()
	    {
	      public void run()
	      {
	        shutDown();
	      }
	    });

	}

	public PostgresConnectionFactory getDatabaseDriver() {
		return dbDriver;
	}

	public IAsrDocumentModel getModel() {
		return model;
	}

	@Override
	public void shutDown() {
		System.out.println("Shutting down");
		docEngine.shutDown();
		documentConsumer.shutDown();	
	}

}
