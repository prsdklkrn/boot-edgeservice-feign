package com.ppk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ppk.feign.client.BootMicroserviceFeignClient;

@RestController
public class EdgeController {

	private final BootMicroserviceFeignClient bootMicroserviceFeignClient;

	@Autowired
	public EdgeController(BootMicroserviceFeignClient bootMicroserviceFeignClient) {
		this.bootMicroserviceFeignClient = bootMicroserviceFeignClient;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String welcomeMessage() {
		return bootMicroserviceFeignClient.getMessage();
	}

}
