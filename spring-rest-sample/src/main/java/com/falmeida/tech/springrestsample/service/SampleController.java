package com.falmeida.tech.springrestsample.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@RequestMapping(method=RequestMethod.GET,path="/new-sample")
	public String newSample() {
		return "new Sample";
	}
	
	@GetMapping(path="new-sample/bean")
	public SampleBean newSampleBean() {
		return new SampleBean("new Sample Bean");
	}
	
	@GetMapping(path="/new-sample/bean/{name}")
	public SampleBean newSampleBeanName(@PathVariable String name) {
		return new SampleBean(String.format("Sample Bean Name, %s",name));
	}
	
}
