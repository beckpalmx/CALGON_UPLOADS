<%@page import="com.util.Uploadfile" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
System.out.println("Uploads : " + request );

Uploadfile obj_upload = new Uploadfile(request);
%>
<%="<script type=\"text/javascript\">window.parent.showResult("+obj_upload.Return_Status()+")</script>"%>
