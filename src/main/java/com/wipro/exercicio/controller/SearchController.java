package com.wipro.exercicio.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wipro.exercicio.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {
	

	@Autowired
	private SearchService searchService;
	@GetMapping
	public  String obeterSearch() throws IOException, URISyntaxException,HttpMessageNotWritableException  {
		return searchService.obterSearch().toString();
	}
	@PostMapping
	public String fazerSearch(@RequestBody String search) {
		StringBuilder novaPesquisa = new StringBuilder();
		novaPesquisa.append(search);
		searchService.setSearch(novaPesquisa);
		return "teste";
	}
}
