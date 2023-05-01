/*
 * Copyright 2023 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.newasr.test;

import org.topicquests.newasr.api.IDocument;
import org.topicquests.newasr.impl.ASRDocument;
import org.topicquests.support.api.IResult;

/**
 * @author jackpark
 *
 */
public class FirstDocumentTest extends TestingRoot {
	private final String
	B = "Saccharomyces cerevisiae var. boulardii is the most significant probiotic yeast species. S. cerevisiae var. boulardii is a eukaryotic organism that has been used in scientific investigations since the time of its discovery [2]. This model organism has unique importance because of its alterable and flexible genome.",
	C="According to the latest definition of the World Health Organization, probiotics are active microbes that stimulate the growth of other probiotic bacteria in the gut and possess beneficial health effects to the host [1]. These microorganisms are able to produce anti-carcinogenic, antioxidant and anti-mutagenic agents and induce protection against different bacterial diseases including diarrhea and respiratory tract infections. Saccharomyces cerevisiae var. boulardii is the most significant probiotic yeast species. S. cerevisiae var. boulardii is a eukaryotic organism that has been used in scientific investigations since the time of its discovery [2]. This model organism has unique importance because of its alterable and flexible genome. The genome of S. cerevisiae var. boulardii was completely sequenced in 1950 and a genome size of approximately 11.3 Mb was reported. It has approximately 6000 genes and 275 additional tRNA genes. Almost 23% of the S. cerevisiae var. boulardii’s genome is homologous to the hominid genome. This specific yeast is best known for its role in treating gastrointestinal diseases [3,4]. ";

	/**
	 * 
	 */
	public FirstDocumentTest() {
		super();
		IDocument d = new ASRDocument();
		d.setAbstract(B);
		d.setType(IDocument.PUBLICATION_TYPE);
		environment.logDebug("DOC "+d.getData()+"\n"+model);
		IResult r = model.putDocument(d);
		System.out.println("A "+r.getErrorString());
		System.out.println("B "+r.getResultObject());
		
		environment.shutDown();
		System.exit(0);
	}

}
