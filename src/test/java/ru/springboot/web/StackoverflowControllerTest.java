package ru.springboot.web;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.springboot.model.StackoverflowWebsite;
import ru.springboot.service.StackoverflowService;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StackoverflowControllerTest  {

    @Mock
    private StackoverflowService stackoverflowService;

    @InjectMocks
    StackoverflowController sut;


    @Test
    public void testGetListOfProviders() throws Exception {
        when(stackoverflowService.findAll()).thenReturn(ImmutableList.of());

        List<StackoverflowWebsite> listOfProviders = sut.getListOfProviders();
        verify(stackoverflowService).findAll();

    }
}