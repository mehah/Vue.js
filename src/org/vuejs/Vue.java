package org.vuejs;

import java.util.HashMap;
import java.util.Map;

import com.jrender.jscript.DOM;
import com.jrender.jscript.DOMHandle;
import com.jrender.jscript.dom.FunctionHandle;
import com.jrender.jscript.dom.Window;
import com.jrender.kernel.JRenderContext;

public class Vue extends DOM {
	private final Map<String, Object> map = new HashMap<String, Object>();
	
	private boolean initialized = false;
	private String el;
	
	public Vue(Window window) {
		super(window);
	}
	
	public Vue() {
		super(JRenderContext.getInstance().currentWindow());
	}
	
	public void setEl(String query) {
		if(this.initialized) {
			return;
		}
		
		this.el = query;
		this.map.put("el", el);
	}
	
	public String getEl(String query) {
		return this.el;
	}
	
	public void setData(String name, Object value) {
		Map<String, Object> data = (Map<String, Object>) map.get("data");
		
		if(data == null) {
			data = new HashMap<String, Object>();
			this.map.put("data", data);
		}
		
		data.put(name, value);
		if(this.initialized) {
			DOMHandle.setProperty(this, name, value);
		}
	}
	
	public Object getData(String name) {
		Map<String, Object> data = (Map<String, Object>) map.get("data");
		if(data != null) {
			return data.get(name);
		}
		
		return null;
	}
	
	public void registerMethod(String name) {
		Map<String, FunctionHandle> methods = (Map<String, FunctionHandle>) map.get("methods");
		
		if(methods == null) {
			methods = new HashMap<String, FunctionHandle>();
			this.map.put("methods", methods);
		}
		
		methods.put(name, new FunctionHandle(name));
	}
	
	public void init() {
		this.initialized = true;
		DOMHandle.newInstance(this, "Vue", this.map);
	}
}
