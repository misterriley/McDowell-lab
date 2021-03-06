/**
 *
 */
package rw;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author bleem
 *
 */
public class Messages
{
	private static final String BUNDLE_NAME = "rw.messages";                        //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	public static String getString(final String key)
	{
		try
		{
			return RESOURCE_BUNDLE.getString(key);
		}
		catch (final MissingResourceException e)
		{
			return '!' + key + '!';
		}
	}

	private Messages()
	{
	}
}
