package com.falmeida.tech.springrestsample.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method=RequestMethod.GET,path="/new-sample")
	public String newSample() {
		return "new Sample";
	}
	
	@GetMapping(path="new-sample/bean")
	public SampleBean newSampleBean() {
		return new SampleBean("new Sample Bean");
	}
	
	@GetMapping(path="new-sample/bean/i18n")
	public String newSampleBeanInternational(@RequestHeader(name="Accept-Language",required=false)
													Locale locale) {
		
		return messageSource.getMessage("good.morning.message", null, locale);
		
	}
	
	@GetMapping(path="/new-sample/bean/{name}")
	public SampleBean newSampleBeanName(@PathVariable String name) {
		return new SampleBean(String.format("Sample Bean Name, %s",name));
	}
	
}
