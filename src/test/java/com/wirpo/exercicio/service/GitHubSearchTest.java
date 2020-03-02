package com.wirpo.exercicio.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.wipro.exercicio.service.GitHubSearch;

@RunWith(MockitoJUnitRunner.class)
public class GitHubSearchTest {
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks 
	private GitHubSearch gitHubSearch;

	@SuppressWarnings("unchecked")
	@Test
	public void search_retornaPesquisa() {
		Map<String ,String> myMap=new HashMap<String, String>();
		myMap.put("items","434");
		//.exchange(,HttpMethod.GET,HttpEntity.EMPTY,Map.class).getBody();
		//.exchange(,Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class), Mockito.any(Class.class)
		//Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class))).thenReturn(new ResponseEntity<Map<String, String>>(myMap, HttpStatus.OK));
		//gitHubSearch.search("teste");
		//Mockito.verify(restTemplate, Mockito.atLeastOnce()).exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class), Mockito.any(Class.class));
	}
}
