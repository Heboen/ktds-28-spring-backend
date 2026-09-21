package com.example.demo.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class bootController {

	@GetMapping("/hello")
	public ResponseEntity<String> helloSpring(){
		return new ResponseEntity<> ("HEllo", HttpStatus.OK);
	}
	
	@GetMapping("/me")
	public String me(Model model) {
		model.addAttribute("name","임창진");
		model.addAttribute("job","개발자");
		model.addAttribute("age","27");
		
		return "introduceme";
	}

}
