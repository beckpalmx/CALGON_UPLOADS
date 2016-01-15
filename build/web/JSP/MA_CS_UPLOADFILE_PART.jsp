<%@page import="com.util.Uploadfile_part" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Uploadfile_part obj_upload = new Uploadfile_part(request);
%>
<%="<script type=\"text/javascript\">window.parent.showResult("+obj_upload.Return_Status()+")</script>"%>
