package kr.ezen.yni_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/ai")
public class AIController {
	
	@GetMapping("/ani.do")
	public String animal() {
		
		//return "ai/animalShape";		// 구글 URL 방식 - 한달유효기간 
		return "/ai/animalShape2";		// 구글 model 방식 - 다운로드해서 사용
	}

}
