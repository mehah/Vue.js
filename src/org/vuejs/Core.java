package org.vuejs;

import java.io.IOException;

import javax.servlet.ServletContext;

import com.jrender.kernel.CoreFileJS;
import com.jrender.kernel.implementation.PluginImplementation;

public class Core implements PluginImplementation{
	public void init(String projectName, ClassLoader classLoader, ServletContext context, CoreFileJS coreFileJS) {
		try {
			// Vue.js v2.5.9
			coreFileJS.append(classLoader.getResource("org/vuejs/core.js"));
			coreFileJS.save();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}

	public void destroy() {
		
	}
}
