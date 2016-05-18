package com.ppk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EdgeProps {
	
	private final String	consulMSServiceName;
	private final String	ribbonResourceGroupName;
	private final String	ribbonTemplateName;
	private final String	bootMSMessageEndpointTemplate;
	private final String	bootMSNameEndpointTemplate;
	private final Integer	maxRetry;

	@Autowired
	public EdgeProps(
			@Value("${consul.welcome.service.name}") String consulMSServiceName,
			@Value("${ribbon.resource.group.name}") String ribbonResourceGroupName,
			@Value("${ribbon.template.name}") String ribbonTemplateName,
			@Value("${welcome.message.uri.template}") String bootMSMessageEndpointTemplate,
			@Value("${welcome.name.uri.template}") String bootMSNameEndpointTemplate,
			@Value("${max.auto.retry.for.next.server:1}") Integer maxRetry) {
		this.consulMSServiceName = consulMSServiceName;
		this.ribbonResourceGroupName = ribbonResourceGroupName;
		this.ribbonTemplateName = ribbonTemplateName;
		this.bootMSMessageEndpointTemplate = bootMSMessageEndpointTemplate;
		this.bootMSNameEndpointTemplate = bootMSNameEndpointTemplate;
		this.maxRetry = maxRetry;
	}

	public String getConsulMSServiceName() {
		return consulMSServiceName;
	}

	public String getRibbonResourceGroupName() {
		return ribbonResourceGroupName;
	}

	public String getRibbonTemplateName() {
		return ribbonTemplateName;
	}

	public String getBootMSMessageEndpointTemplate() {
		return bootMSMessageEndpointTemplate;
	}

	public String getBootMSNameEndpointTemplate() {
		return bootMSNameEndpointTemplate;
	}

	public Integer getMaxRetry() {
		return maxRetry;
	}

}
