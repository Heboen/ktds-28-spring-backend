package com.example.demo.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 이 어노테이션이 있어야 end-point로 동작함
@Controller
public class HelloBootController {
	
	public HelloBootController() {
		System.out.println("스프링이 생성자를 호출했습니다.");
	}
	//브라우저에 리소스를 보내주는 GetMapping
	@GetMapping("/hello")
	public ResponseEntity<String>hello(){
		return new ResponseEntity<>("Hello Boot Controller", HttpStatus.OK);
	}
	
	@GetMapping("/jsp")
	public String viewJsp() {
		return "hellospring";
	}
	@GetMapping("/jsp2")
	public String viewJsp2(Model model) {
		model.addAttribute("name","임창진");
		model.addAttribute("age","27");
		model.addAttribute("isDeveloper", true);
		return "hellospring2";
	}
}

