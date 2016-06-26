package com.oxd.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oxd.tools.Uploader;



@Controller
@RequestMapping("/upload")
public class UploaderController {

	@RequestMapping(value = "/editorImage")
	@ResponseBody
	public String uploadEditorImage(HttpServletRequest request, @RequestParam("upfile")MultipartFile file)
			throws Exception {
		Uploader up = new Uploader(request, file);
		up.setSavePath("image");
		String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
		up.setAllowFiles(fileType);
		up.setMaxSize(10000); // 单位KB
		up.multiUpload();

		String callback = request.getParameter("callback");

		String result = "{\"name\":\"" + up.getFileName()
				+ "\", \"originalName\": \"" + up.getOriginalName()
				+ "\", \"size\": " + up.getSize() + ", \"state\": \""
				+ up.getState() + "\", \"type\": \"" + up.getType()
				+ "\", \"url\": \"" + up.getUrl() + "\"}";

		result = result.replaceAll("\\\\", "\\\\");

		if (callback == null) {
			return result;
		} else {
			return "<script>" + callback + "(" + result + ")</script>";
		}
	}
	
	@RequestMapping("/index1")
	public String index(Model model) {
		model.addAttribute("msg", "哈哈哈");
		return "index1";
	} 
}
