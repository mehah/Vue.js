package org.vuejs;

import java.util.Map;

import com.jrender.jscript.DOM;
import com.jrender.jscript.DOMHandle;
import com.jrender.jscript.dom.Window;

public class Vue extends DOM {
	public Vue(Window window, Map<String, Object> parameters) {
		super(window);
		DOMHandle.newInstance(this, "Vue", parameters);
	}
	
	public void prop(String name, Object value) {
		DOMHandle.setProperty(this, name, value);
	}
	
	public <C> C prop(String name, Class<C> cast) {
		return DOMHandle.getVariableValueByProperty(this, name, cast, name);
	}
}
