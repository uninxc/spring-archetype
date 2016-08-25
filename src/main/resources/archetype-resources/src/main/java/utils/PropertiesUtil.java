package ${package}.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static String getPropertyValue(String key) {
		try {
			Properties prop = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream stream = loader
					.getResourceAsStream("application.properties");
			prop.load(stream);

			String result = prop.getProperty(key);

			stream.close();

			return result;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
