/**
 * 
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
