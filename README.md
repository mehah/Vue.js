Vue
=========
Plugin to use [Vue](https://vuejs.org/) with [jRender](https://github.com/mehah/jRender).

Example
========
add in <plugins /> jrender.config.xml
```xml
<plugin class="org.vuejs.Core"/>
```

index.html
```html
<html>
	<body>
		<div id="app">
		  {{ message }}
		  <button v-on:click="reverseMessage">Reverse Message</button>
		</div>
	</body>
</html>
```

IndexController.java
```java
@Page(name="index", path="index.html")
public class IndexController extends Window {
	private Vue app;
	
	public void init(JRenderContext arg0) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		final JsonObject data = new JsonObject();
		final Map<String, FunctionHandle> methods = new HashMap<String, FunctionHandle>();
				
		parameters.put("el", "#app");
		parameters.put("data", data);
		parameters.put("methods", methods);
		
		data.addProperty("message", "Hello Vue!");
		
		methods.put("reverseMessage", new FunctionHandle("reverseMessage"));		
		
		this.app = new Vue(this, parameters);
	}
	
	public void reverseMessage() {
		String message = app.prop("message", String.class);
		app.prop("message", new StringBuilder(message).reverse().toString());
	}
}
```
