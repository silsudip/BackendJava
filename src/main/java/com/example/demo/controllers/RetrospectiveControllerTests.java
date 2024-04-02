package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.models.Retrospective;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RetrospectiveControllerTests {
    @Mock
    private Map<String, Retrospective> retrospectives;

    @InjectMocks
    private RetrospectiveService retrospectiveService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateRetrospective_Success() {
        Retrospective retrospective = new Retrospective();
        retrospective.setName("Retrospective 1");
        retrospective.setSummary("Post release retrospective");
        retrospective.setDate(new Date());
        retrospective.setParticipants(Collections.singletonList("Viktor"));

        when(retrospectives.containsKey("Retrospective 1")).thenReturn(false);

        ResponseEntity<String> responseEntity = retrospectiveService.createRetrospective(retrospective);

        verify(retrospectives, times(1)).put("Retrospective 1", retrospective);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateRetrospective_Failure_DuplicateName() {
        Retrospective retrospective = new Retrospective();
        retrospective.setName("Retrospective 1");

        when(retrospectives.containsKey("Retrospective 1")).thenReturn(true);

        ResponseEntity<String> responseEntity = retrospectiveService.createRetrospective(retrospective);

        verify(retrospectives, never()).put(any(), any());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    // Add more tests for other methods
}
