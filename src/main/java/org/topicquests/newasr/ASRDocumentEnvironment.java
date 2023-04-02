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
import org.topicquests.newasr.kafka.DocumentProducer;
import org.topicquests.support.RootEnvironment;
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
	private DocumentProducer sentenceProducer;
	private DocumentEngine docEngine;
	
	public static final String AGENT_GROUP = "Document";

	/**
	 * @param configPath
	 * @param logConfigPath
	 */
	public ASRDocumentEnvironment() {
		super("asr-document-config.xml", null, "logger.properties");
		kafkaProps = Configurator.getProperties("kafka-topics.xml");
		documentListener = new DocumentListener(this);
		String cTopic = (String)kafkaProps.get("DocumentConsumerTopic");
		documentConsumer = new KafkaHandler(this, (IMessageConsumerListener)documentListener, cTopic, AGENT_GROUP);
		sentenceProducer = new DocumentProducer(this, AGENT_GROUP);
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
