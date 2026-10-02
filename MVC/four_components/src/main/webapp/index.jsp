<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Path</th>
        <th>URL</th>
        <th>Request Processing Flow</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><a href="diagnostics">/diagnostics</a></td>
        <td>/diagnostics</td>
        <td>SimpleUrlHandlerMapping -> SimpleControllerHandlerAdapter -> AbstractController -> InternalResourceViewResolver -> JSP</td>
    </tr>
    <tr>
        <td><a href="home">/home</a></td>
        <td>/home</td>
        <td>SimpleUrlHandlerMapping -> SimpleControllerHandlerAdapter -> AbstractController -> InternalResourceViewResolver -> JSP</td>
    </tr>
    <tr>
        <td><a href="about">/about</a></td>
        <td>/about</td>
        <td>SimpleUrlHandlerMapping -> SimpleControllerHandlerAdapter -> ParameterizableViewController -> InternalResourceViewResolver -> JSP</td>
    </tr>
    <tr>
        <td><a href="legacy">/legacy</a></td>
        <td>/legacy</td>
        <td>BeanNameUrlHandlerMapping -> SimpleControllerHandlerAdapter -> Controller interface -> InternalResourceViewResolver -> JSP</td>
    </tr>
    <tr>
        <td><a href="annotated">/annotated</a></td>
        <td>/annotated</td>
        <td>RequestMappingHandlerMapping -> RequestMappingHandlerAdapter -> @Controller -> InternalResourceViewResolver -> JSP</td>
    </tr>
    <tr>
        <td><a href="raw">/raw</a></td>
        <td>/raw</td>
        <td>SimpleUrlHandlerMapping -> HttpRequestHandlerAdapter -> HttpRequestHandler -> Response object (no view)</td>
    </tr>
    <tr>
        <td><a href="greet">/greet</a></td>
        <td>/greet</td>
        <td>SimpleUrlHandlerMapping -> GreetingHandlerAdapter -> GreetingHandler -> InternalResourceViewResolver -> JSP</td>
    </tr>
    <tr>
        <td><a href="plain">/plain</a></td>
        <td>/plain</td>
        <td>SimpleUrlHandlerMapping -> SimpleControllerHandlerAdapter -> AbstractController -> BeanNameViewResolver -> PlainTextView</td>
    </tr>
    <tr>
        <td><a href="go-home">/go-home</a></td>
        <td>/go-home</td>
        <td>SimpleUrlHandlerMapping -> SimpleControllerHandlerAdapter -> ParameterizableViewController -> BeanNameViewResolver -> RedirectView</td>
    </tr>
    </tbody>
</table>
