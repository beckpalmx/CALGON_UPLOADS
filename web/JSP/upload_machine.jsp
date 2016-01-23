
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.io.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.io.output.*" %>

<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.db.DBConnect" %>
<html>
    <head>
        <title>CALGON System</title>
    </head>

    <script>
        function goBack()
        {
            window.history.back();
        }
    </script>

    <%

        String machine_id = "";

        int count1 = 0;

        System.out.println("Start = " + count1);

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        System.out.println("isMultipart = " + isMultipart);

        if (!isMultipart) {
            System.out.println("Not Multipart");
        } else {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;
            System.out.println("upload = " + upload);
            try {
                items = upload.parseRequest(request);
                System.out.println("items = " + items);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }

            Iterator itr = items.iterator();

            System.out.println("itr = " + itr);

            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString();
                    System.out.println("name = " + name);
                    System.out.println("value = " + value);
                    if (name.equals("A_machine_id")) {
                        machine_id = value;
                        count1 = 1;
                        System.out.println("Final machine_id = " + machine_id);
                    }
                } else {
                    System.out.println("savedFile Other ");
                    try {

                        //String itemName = item.getName();
                        String pic_name[] = item.getName().split("\\.");

                        System.out.println("pic_name[0] = " + pic_name[0].toUpperCase());
                        System.out.println("pic_name[1] = " + pic_name[1].toUpperCase());

                        String picture_name = machine_id + "." + pic_name[1].toUpperCase();

                        File savedFile1 = new File("D:\\glassfish4\\glassfish\\domains\\domain1\\applications\\CALGON_UPLOADS\\UPLOADS\\" + picture_name);
                        System.out.println("savedFile1 = " + savedFile1);
                        item.write(savedFile1);

                        //File savedFile2 = new File("D:\\CGC\\UPLOADS\\PART\\" + picture_name);
                        //System.out.println("savedFile2 = " + savedFile2);
                        //item.write(savedFile2);
                        String Update_Pic_Name = " update m_machine set pic1 = '" + picture_name + "' ,update_by = 'upload_image' where machine_id = '" + machine_id + "'";

                        System.out.println("Update_Pic_Name = " + Update_Pic_Name);

                        //out.println("Update_Pic_Name = " + Update_Pic_Name);
                        PreparedStatement p;
                        DBConnect dbConnect = new DBConnect();
                        Connection con = dbConnect.openNewConnection();
                        try {
                            p = con.prepareStatement(Update_Pic_Name);
                            p.executeUpdate();
                        } finally {
                            try {
                                //p.close();
                                con.close();
                            } catch (SQLException e) {
                                System.out.println("Error : " + e);
                            }
                        }

                        out.println("Upload file Successfully.<br>");
                        out.println("Image File Name : " + picture_name);
                        //out.println("savedFile1 : " + savedFile1);
                        //out.println("savedFile2 : " + savedFile2);
                        out.println("<br><img src='http://cgc-rv016.dyndns.org:8089/CALGON_UPLOADS/UPLOADS/" + picture_name + "' width='50%' height='50%'><br>");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    %>

    <tr>
        <td class="columnlabel">&nbsp;</td>
        <td class="columnobject">    
            <button onclick="goBack()">OK</button>
        </td>
    </tr>            

</body>
</html>    
