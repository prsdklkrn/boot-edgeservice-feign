package com.ppk.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ppk.config.EdgeProps;
import com.ppk.entities.RibbonClientRequest;
import com.ppk.service.consul.DiscoverService;
import com.ppk.service.ribbon.RibbonService;

import rx.Observable;
import rx.functions.Func2;

@Service
public class EdgeServiceImpl implements EdgeService {

	private final EdgeProps			edgeProps;

	private final DiscoverService	discoverService;

	private final RibbonService		ribbonService;

	@Autowired
	public EdgeServiceImpl(EdgeProps edgeProps, DiscoverService discoverService, RibbonService ribbonService) {
		this.edgeProps = edgeProps;
		this.discoverService = discoverService;
		this.ribbonService = ribbonService;
	}

	@Override
	public String getWelcomeMessage() {
		Observable<Map<String, String>> welcomeMessageObservable = null;
		Observable<Map<String, String>> welcomeNameObservable = null;
		String commaSepServerList = discoverService.discoverService(edgeProps.getConsulMSServiceName());
		welcomeMessageObservable = getWelcomeMessage(commaSepServerList, edgeProps.getBootMSMessageEndpointTemplate());
		welcomeNameObservable = getWelcomeMessage(commaSepServerList, edgeProps.getBootMSNameEndpointTemplate());
		String finalWelcomeMessage = mergeResponses(welcomeMessageObservable, welcomeNameObservable);
		return finalWelcomeMessage;
	}

	private String mergeResponses(Observable<Map<String, String>> welcomeMessageObservable,
			Observable<Map<String, String>> welcomeNameObservable) {
		String finalMesage = Observable.zip(welcomeMessageObservable, welcomeNameObservable,
				new Func2<Map<String, String>, Map<String, String>, String>() {
					@Override
					public String call(Map<String, String> map1, Map<String, String> map2) {
						String finalMessage = map1.get("message") + map2.get("name");
						return finalMessage;
					}
				}).toBlocking().single();

		return finalMesage;
	}

	private Observable<Map<String, String>> getWelcomeMessage(final String commaSeparatedServerList,
			String uriTemplate) {
		RibbonClientRequest ribbonRequestVO = RibbonClientRequest.getRibbonRequest(
				edgeProps.getRibbonResourceGroupName(), commaSeparatedServerList, edgeProps.getRibbonTemplateName(),
				uriTemplate, edgeProps.getMaxRetry(), HttpMethod.GET.name(), null, null, null, null);
		Observable<Map<String, String>> userBadgeObservable = ribbonService.getRibbonRequestObservable(ribbonRequestVO,
				new TypeReference<Map<String, String>>() {
				});
		return userBadgeObservable;
	}

}
