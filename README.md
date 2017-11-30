Vue
=========
Plugin to use [Vue](https://vuejs.org/) with [jRender](https://github.com/mehah/jRender).

Installing
========
add in <plugins /> jrender.config.xml
```xml
<plugin class="org.vuejs.Core"/>
```

Example
========
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
@Page(name = "index", path = "index.html")
public class IndexController extends Window {
	private Vue app;

	public void init(JRenderContext arg0) {
		app = new Vue();

		app.setEl("#app");
		app.setData("message", "Hello Vue!");
		app.registerMethod("reverseMessage");

		app.init();
	}

	public void reverseMessage() {
		String message = (String) app.getData("message");
		app.setData("message", new StringBuilder(message).reverse().toString());
	}
}
```
