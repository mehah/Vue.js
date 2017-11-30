package org.vuejs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jrender.jscript.DOM;
import com.jrender.jscript.DOMHandle;
import com.jrender.jscript.dom.FunctionHandle;
import com.jrender.jscript.dom.Window;
import com.jrender.kernel.JRenderContext;

public class Vue extends DOM {
	private final Map<String, Object> options = new HashMap<String, Object>();
	private boolean initialized = false;

	public Vue(Window window) {
		super(window);
	}

	public Vue() {
		super(JRenderContext.getInstance().currentWindow());
	}

	public void el(String query) {
		if (this.initialized) {
			return;
		}

		this.options.put("el", query);
	}

	public String el() {
		return (String) this.options.get("el");
	}
	
	public void delimiters(String[] delimiters) {
		if (this.initialized) {
			return;
		}

		this.options.put("delimiters", delimiters);
	}

	public String[] delimiters() {
		return (String[]) this.options.get("delimiters");
	}	

	public void inject(List<String> list) {
		if (this.initialized) {
			return;
		}

		this.options.put("list", list);
	}

	public List<String> inject() {
		return (List<String>) this.options.get("inject");
	}

	public List<Map<String, Object>> mixins() {
		List<Map<String, Object>> mixins = (List<Map<String, Object>>) options.get("mixins");

		if (mixins == null) {
			mixins = new ArrayList<Map<String, Object>>();
			this.options.put("mixins", mixins);
		}

		return mixins;
	}

	public void mixins(Map<String, Object> options) {
		if (this.initialized) {
			return;
		}

		mixins().add(options);
	}
	
	public List<String> props() {
		List<String> props = (List<String>) options.get("props");

		if (props == null) {
			props = new ArrayList<String>();
			this.options.put("props", props);
		}

		return props;
	}

	public void props(String prop) {
		if (this.initialized) {
			return;
		}

		props().add(prop);
	}

	private Map<String, Object> __data() {
		Map<String, Object> data = (Map<String, Object>) this.options.get("data");

		if (data == null) {
			data = new HashMap<String, Object>();
			this.options.put("data", data);
		}
		
		return data;
	}
	
	public void data(String name, Object value) {
		__data().put(name, value);
		if (this.initialized) {
			DOMHandle.setProperty(this, name, value);
		}
	}

	public Object getData(String name) {
		return __data().get(name);
	}

	public void registerMethod(String name) {
		Map<String, FunctionHandle> methods = (Map<String, FunctionHandle>) options.get("methods");

		if (methods == null) {
			methods = new HashMap<String, FunctionHandle>();
			this.options.put("methods", methods);
		}

		methods.put(name, new FunctionHandle(name));
	}

	public void registerComputed(String methodName) {
		Map<String, FunctionHandle> methods = (Map<String, FunctionHandle>) options.get("computed");

		if (methods == null) {
			methods = new HashMap<String, FunctionHandle>();
			this.options.put("computed", methods);
		}

		methods.put(methodName, new FunctionHandle(methodName));
	}

	public void registerWatch(String methodName) {
		Map<String, FunctionHandle> methods = (Map<String, FunctionHandle>) options.get("watch");

		if (methods == null) {
			methods = new HashMap<String, FunctionHandle>();
			this.options.put("watch", methods);
		}

		methods.put(methodName, new FunctionHandle(methodName));
	}

	public void onCreated(String methodName) {
		this.options.put("created", new FunctionHandle(methodName));
	}

	public void init() {
		this.initialized = true;
		DOMHandle.newInstance(this, "Vue", this.options);
	}
}
