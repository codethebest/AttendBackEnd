package com.liyolomoko.attendeventappfrontend.repositories.event.impl;

import android.util.ArraySet;

import com.liyolomoko.attendeventappfrontend.domain.event.Event;
import com.liyolomoko.attendeventappfrontend.repositories.event.IEventRestApi;

import java.util.Set;

/**
 * Created by Leo on 8/31/2016.
 */
public class EventRestApi  {
/*    final String BASE_URL = "http://localhost:8080/api/event";
    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Event create(Event entity) {
       final String url = BASE_URL;
        HttpEntity <Event> requestEntity = new HttpEntity<Event>(entity,requestHeaders);
        try{
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,String.class);
            String result = responseEntity.getBody();
            return result;
        }catch (Exception e) {
            return "Exists";
        }
    }

    @Override
    public Event readById(Long aLong) {
        final String url = BASE_URL+"/"+entity.getId();
        HttpEntity<Event> requestEntity = new HttpEntity<Event>(entity,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.PUT, requestEntity,String.class);
        String result = responseEntity.getBody();
        return result;
    }
    @Override
    public Set<Event> readAll() {
        Iterable<Event> events = new ArraySet<>();
        final String url = BASE_URL;
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Event[]> responseEntity = restTemplate.exchange(url, HttpMethod > GET, requestEntity, Event[].class);
        Event[] results = responseEntity.getBody();
       // Set eventsSet = new HashSet();

        for (Event event:results)
        {
            events.add(event);
        }
        return events;
    }

    @Override
    public Event update(Event entity) {
        final String url = BASE_URL+entity.getId();
        HttpEntity<Event> requestEntity = new HttpEntity<Event>(entity,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.PUT, requestEntity,String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public void delete(Event entity) {
        final String url = BASE_URL+entity.getId();
        restTemplate.delete(url,entity.getId());

    }*/
}
