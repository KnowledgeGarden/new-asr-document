/*
 * Copyright 2023 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.newasr.api;

/**
 * @author jackpark
 *
 */
public interface IDocumentQueries {

	public static final String
	PUT_DOCUMENT =
		"iNSERT INTO public.document (data, typ) VALUES(?, ?) RETURNING id",
	GET_DOCUMENT =
		"SELECT * FROM public.document WHERE id=?",
	UPDATE_DOCUMENT =
		"UPDATE public.document SET data=? WHERE id=?";

}
