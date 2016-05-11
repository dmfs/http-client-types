package org.dmfs.httpclient.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Locale;

import org.dmfs.httpclient.parameters.Parameter;
import org.dmfs.httpclient.parameters.Parameters;
import org.junit.Test;


/**
 * Test {@link SimpleMediaType}.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class SimpleMediaTypeTest
{

	@Test
	public void testHashCode()
	{
		assertEquals("application".hashCode() * 31 + "xml".hashCode(), new SimpleMediaType("Application", "XML").hashCode());
		assertEquals("application".hashCode() * 31 + "xml".hashCode(), new SimpleMediaType("Application", "XML", "utf-8").hashCode());
		assertEquals("application".hashCode() * 31 + "xml".hashCode(), new SimpleMediaType("application", "xml").hashCode());
		assertEquals("application".hashCode() * 31 + "xml".hashCode(), new SimpleMediaType("application", "xml", "utf-8").hashCode());
	}


	@Test
	public void testFirstParameterParameterTypeOfTT()
	{
		assertEquals("latin-1", new SimpleMediaType("Application", "xml").firstParameter(Parameters.CHARSET, "latin-1").value());
		assertEquals("UTF-8", new SimpleMediaType("Application", "xml", "UTF-8").firstParameter(Parameters.CHARSET, "latin-1").value());
		assertEquals("UTF-8", new SimpleMediaType("Application", "xml", "UTF-8").firstParameter(Parameters.CHARSET, "UTF-8").value());
	}


	@Test
	public void testParameters()
	{
		Iterator<Parameter<String>> iterator1 = new SimpleMediaType("Application", "xml").parameters(Parameters.TITLE);
		assertFalse(iterator1.hasNext());

		Iterator<Parameter<String>> iterator2 = new SimpleMediaType("Application", "xml", "UTF-8").parameters(Parameters.CHARSET);
		assertTrue(iterator2.hasNext());
		assertEquals("UTF-8", iterator2.next().value());
		assertFalse(iterator2.hasNext());

		Iterator<Parameter<String>> iterator3 = new SimpleMediaType("Application", "xml", Parameters.CHARSET.entity("UTF-8"),
			Parameters.TITLE.entity("someTitle"), Parameters.CHARSET.entity("latin-1")).parameters(Parameters.CHARSET);
		assertTrue(iterator3.hasNext());
		assertEquals("UTF-8", iterator3.next().value());
		assertTrue(iterator3.hasNext());
		assertEquals("latin-1", iterator3.next().value());
		assertFalse(iterator3.hasNext());

	}


	@Test
	public void testHasParameter()
	{
		assertFalse(new SimpleMediaType("Application", "xml").hasParameter(Parameters.TITLE));
		assertTrue(new SimpleMediaType("Application", "xml", "UTF-8").hasParameter(Parameters.CHARSET));
		assertFalse(new SimpleMediaType("Application", "xml", Parameters.CHARSET.entity("abcde")).hasParameter(Parameters.TITLE));
		assertTrue(new SimpleMediaType("Application", "xml", Parameters.TITLE.entity("123"), Parameters.CHARSET.entity("abcde"),
			Parameters.HREFLANG.entity(Locale.ENGLISH)).hasParameter(Parameters.CHARSET));
	}


	@Test
	public void testType()
	{
		assertTrue("Application/json".equalsIgnoreCase(new SimpleMediaType("Application", "json").type()));
	}


	@Test
	public void testMainType()
	{
		assertTrue("Application".equalsIgnoreCase(new SimpleMediaType("Application", "json").mainType()));
	}


	@Test
	public void testSubType()
	{
		assertTrue("json".equalsIgnoreCase(new SimpleMediaType("Application", "json").subType()));
	}


	@Test
	public void testCharset()
	{
		assertEquals("latin-1", new SimpleMediaType("Application", "xml").charset("latin-1"));
		assertEquals("UTF-8", new SimpleMediaType("Application", "xml", "UTF-8").charset("latin-1"));
		assertEquals("UTF-8", new SimpleMediaType("Application", "xml", "UTF-8").charset("UTF-8"));
	}


	@Test
	public void testToString()
	{
		assertTrue("Application/xml".equalsIgnoreCase(new SimpleMediaType("Application", "xml").toString()));
		assertTrue("Application/xml;charset=\"UTF-8\"".equalsIgnoreCase(new SimpleMediaType("Application", "xml", "UTF-8").toString()));
	}


	@Test
	public void testEqualsObject()
	{
		// equals works with other MediaType implementations
		assertTrue(new SimpleMediaType("application", "xml").equals(new StringMediaType("application/xml")));
		assertTrue(new SimpleMediaType("application", "xml").equals(new StringMediaType("Application/xml; charset=\"UTF-8\"")));
		assertFalse(new SimpleMediaType("application", "xml").equals(new StringMediaType("Application/json")));

		// parameters are not taken into account
		assertTrue(new SimpleMediaType("application", "xml").equals(new SimpleMediaType("application", "xml", "UTF-8")));
		assertTrue(new SimpleMediaType("application", "xml").equals(new SimpleMediaType("Application", "xml", "UTF-8")));
		assertFalse(new SimpleMediaType("application", "xml").equals(new SimpleMediaType("Application", "json", "UTF-8")));

		// equals is case insensitive
		assertTrue(new SimpleMediaType("application", "xml").equals(new SimpleMediaType("application", "xml")));
		assertTrue(new SimpleMediaType("application", "xml").equals(new SimpleMediaType("Application", "xml")));
		assertFalse(new SimpleMediaType("application", "xml").equals(new SimpleMediaType("Application", "json")));

	}
}
