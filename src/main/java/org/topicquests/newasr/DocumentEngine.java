/*
 * Copyright 2023 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.newasr;

import org.topicquests.newasr.api.IAsrDocumentModel;

/**
 * @author jackpark
 *
 */
public class DocumentEngine {
	private ASRDocumentEnvironment environment;
	private IAsrDocumentModel model;

	/**
	 * 
	 */
	public DocumentEngine(ASRDocumentEnvironment  env) {
		environment = env;
		model = environment.getModel();
	}

	
	public void shutDown() {
		//TODO
	}
}
