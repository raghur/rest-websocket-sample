<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/org/cometd.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.cometd.js"></script>
    <script type="text/javascript" src="application.js"></script>
    <script type="text/javascript">
        var config = {
            contextPath: '${pageContext.request.contextPath}'
        };
    </script>

</head>
<body>
<h2>Jersey RESTful Web Application!</h2>
<p><a href="webresources/myresource">Jersey resource</a>
<p>Visit the <a href="http://jersey.java.net">Project Jersey website</a>
for more information on Jersey!

    <div id="body"></div>

</body>
</html>
