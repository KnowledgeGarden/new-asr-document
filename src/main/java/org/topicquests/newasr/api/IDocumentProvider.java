/**
 * 
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
