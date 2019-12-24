package org.dsu.dc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AjaxTestController {
	@GetMapping("/test")
	public void ajaxTest() {
		
	}
}
