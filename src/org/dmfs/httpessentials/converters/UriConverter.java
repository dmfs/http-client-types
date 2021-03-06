/*
 * Copyright (C) 2016 Marten Gajda <marten@dmfs.org>
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dmfs.httpessentials.converters;

import java.net.URI;

import org.dmfs.httpessentials.typedentity.EntityConverter;


/**
 * {@link EntityConverter} for {@link URI}s.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public final class UriConverter implements EntityConverter<URI>
{
	public final static UriConverter INSTANCE = new UriConverter();


	@Override
	public URI value(String uriString)
	{
		return URI.create(uriString.trim());
	}


	@Override
	public String valueString(URI uri)
	{
		return uri.toASCIIString();
	}

}
