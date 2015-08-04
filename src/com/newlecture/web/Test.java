package com.newlecture.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/Test")
@MultipartConfig(
		fileSizeThreshold = 1024*1024,
		maxFileSize = 1024*1024*5, //5메가
		maxRequestSize = 1024*1024*5*5 // 5메가 5개까지
)
public class Test extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Part part = request.getPart("f1");
		InputStream ins = part.getInputStream();
		
		String submittedPath = part.getSubmittedFileName();
		//String fileName = submittedPath.substring(submittedPath.lastIndexOf('/')+1);
		
		Path p = Paths.get(submittedPath); // JDK 7+
		String fileName = p.getFileName().toString();
		
		String path = "E:\\2015-04-08\\" + fileName;
		OutputStream outs = new FileOutputStream(path);
		
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = ins.read(buf, 0, 1024)) >= 0)
		{
			outs.write(buf, 0, len);			
		}
		
		outs.flush();
		outs.close();
		ins.close();		
	}
}
