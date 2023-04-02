/*
 * Copyright 2023 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.newasr.api;

import org.topicquests.support.api.IResult;

/**
 * @author jackpark
 *
 */
public interface IDocumentProvider {

	IResult putDocument(IDocument doc);
	
	IResult updateDocument(IDocument doc);

	IResult getDocument(long docId);
	
	IResult removeDocument(long docId);
}
