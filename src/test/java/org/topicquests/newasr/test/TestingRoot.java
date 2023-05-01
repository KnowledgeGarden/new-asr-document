/*
 * Copyright 2023 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.newasr.test;

import org.topicquests.newasr.ASRDocumentEnvironment;
import org.topicquests.newasr.api.IAsrDocumentModel;

/**
 * @author jackpark
 *
 */
public class TestingRoot {
	protected ASRDocumentEnvironment environment;
	protected IAsrDocumentModel model;
	/**
	 * 
	 */
	public TestingRoot() {
		environment = new ASRDocumentEnvironment();
		model = environment.getModel();
		environment.logDebug("TR "+model);
	}

}
