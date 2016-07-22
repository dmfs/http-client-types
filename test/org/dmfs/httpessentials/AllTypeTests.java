package org.dmfs.httpessentials;

import org.dmfs.httpessentials.types.StringMediaTypeTest;
import org.dmfs.httpessentials.types.StructuredMediaTypeTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ StructuredMediaTypeTest.class, StringMediaTypeTest.class })
public class AllTypeTests
{

}
