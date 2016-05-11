package org.dmfs.httpclient;

import org.dmfs.httpclient.types.SimpleMediaTypeTest;
import org.dmfs.httpclient.types.StringMediaTypeTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ SimpleMediaTypeTest.class, StringMediaTypeTest.class })
public class AllTypeTests
{

}
