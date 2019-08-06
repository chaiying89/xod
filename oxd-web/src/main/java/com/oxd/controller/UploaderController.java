package com.oxd.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.oxd.tools.Uploader;

@Controller
@RequestMapping("/upload")
public class UploaderController {

	@RequestMapping(value = "/editorImage")
	public void uploadEditorImage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("upfile") MultipartFile file) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;chartset=UTF-8");
		Uploader up = new Uploader(request, file);
		up.setSavePath("image");
		String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
		up.setAllowFiles(fileType);
		up.setMaxSize(10000); // 单位KB
		up.multiUpload();

		String callback = request.getParameter("callback");
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + request.getContextPath();

		String result = "{\"name\":\"" + up.getFileName()
				+ "\", \"originalName\": \"" + up.getOriginalName()
				+ "\", \"size\": " + up.getSize() + ", \"state\": \""
				+ up.getState() + "\", \"type\": \"" + up.getType()
				+ "\", \"url\": \"" + basePath + "/static/" + up.getUrl() + "\"}";

		result = result.replaceAll("\\\\", "\\\\");
		
		if (callback == null) {
			response.getWriter().write(result);
		} else {
			response.getWriter().write("<script>" + callback + "(" + result + ")</script>");
		}
	}
}
