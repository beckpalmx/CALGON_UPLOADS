<%@ page import="java.io.*" %>
<html>
<head>
	<title>CMMS System</title>
</head>

<script>
    function goBack()
    {
        window.history.back();
    }
</script>

<body>
<%
	//to get the content type information from JSP Request Header
	String contentType = request.getContentType();
	//here we are checking the content type is not equal to Null and

	if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
		DataInputStream in = new DataInputStream(
				request.getInputStream());
		//we are taking the length of Content type data
		int formDataLength = request.getContentLength();
		byte dataBytes[] = new byte[formDataLength];
		int byteRead = 0;
		int totalBytesRead = 0;
		//this loop converting the uploaded file into byte code
		while (totalBytesRead < formDataLength) {
			byteRead = in.read(dataBytes, totalBytesRead,
					formDataLength);
			totalBytesRead += byteRead;
		}
		String file = new String(dataBytes);
		//for saving the file name
		String saveFile = file
				.substring(file.indexOf("filename=\"") + 10);                
		saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
		saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,
				saveFile.indexOf("\""));
		int lastIndex = contentType.lastIndexOf("=");
		String boundary = contentType.substring(lastIndex + 1,
				contentType.length());
		int pos;
		//extracting the index of file 
		pos = file.indexOf("filename=\"");
		pos = file.indexOf("\n", pos) + 1;
		pos = file.indexOf("\n", pos) + 1;
		pos = file.indexOf("\n", pos) + 1;
		int boundaryLocation = file.indexOf(boundary, pos) - 4;
		int startPos = ((file.substring(0, pos)).getBytes()).length;
		int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
		// creating a new file with the same name and writing the content in new file

		//String savePath = application.getRealPath("\\upload\\"+saveFile);
                String savePart = "D:\\CGC\\UPLOADS\\PART\\"+saveFile ;
                System.out.println("request = " + request.getSession().getServletContext());                
                String savePart1 = "D:\\glassfishv4\\glassfish\\domains\\domain1\\applications\\CGC_UPLOADS\\UPLOADS\\PART\\"+saveFile ;
                //String savePart1 = "D:\\glassfishv4\\glassfish\\domains\\domain1\\applications\\CGC_UPLOADS\\UPLOADS\\PART\\"+saveFile ;
		
		out.println("Upload file Successfully.<br>");
		
		out.println("Save to : " + savePart);
		
		FileOutputStream fileOut = new FileOutputStream(savePart);
		fileOut.write(dataBytes, startPos, (endPos - startPos));
		fileOut.flush();
		fileOut.close();
                
		FileOutputStream fileOut1 = new FileOutputStream(savePart1);
		fileOut1.write(dataBytes, startPos, (endPos - startPos));
		fileOut1.flush();
		fileOut1.close();                
                
                
	}
%>	
<tr>[
    <td class="columnlabel">&nbsp;</td>
    <td class="columnobject">    
        <button onclick="goBack()">OK</button>
    </td>
</tr>    
</body>
</html>
