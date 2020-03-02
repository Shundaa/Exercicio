package com.wirpo.exercicio.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.wipro.exercicio.service.TwitterSearch;

@RunWith(MockitoJUnitRunner.class)
public class TwitterSearchTest {
	
	@InjectMocks
	TwitterSearch twitterSearch;
	@Test
    public void serch_listaRetornaStrings(){
    	
    }

}
