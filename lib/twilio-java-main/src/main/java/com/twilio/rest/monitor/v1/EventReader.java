/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Monitor
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.monitor.v1;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.time.ZonedDateTime;

public class EventReader extends Reader<Event> {

    private String actorSid;
    private String eventType;
    private String resourceSid;
    private String sourceIpAddress;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private Integer pageSize;

    public EventReader() {}

    public EventReader setActorSid(final String actorSid) {
        this.actorSid = actorSid;
        return this;
    }

    public EventReader setEventType(final String eventType) {
        this.eventType = eventType;
        return this;
    }

    public EventReader setResourceSid(final String resourceSid) {
        this.resourceSid = resourceSid;
        return this;
    }

    public EventReader setSourceIpAddress(final String sourceIpAddress) {
        this.sourceIpAddress = sourceIpAddress;
        return this;
    }

    public EventReader setStartDate(final ZonedDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public EventReader setEndDate(final ZonedDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public EventReader setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    public ResourceSet<Event> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    public Page<Event> firstPage(final TwilioRestClient client) {
        String path = "/v1/Events";

        Request request = new Request(
            HttpMethod.GET,
            Domains.MONITOR.toString(),
            path
        );

        addQueryParams(request);
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        return pageForRequest(client, request);
    }

    private Page<Event> pageForRequest(
        final TwilioRestClient client,
        final Request request
    ) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "Event read failed: Unable to connect to server"
            );
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(
                response.getStream(),
                client.getObjectMapper()
            );
            if (restException == null) {
                throw new ApiException(
                    "Server Error, no content",
                    response.getStatusCode()
                );
            }
            throw new ApiException(restException);
        }

        return Page.fromJson(
            "events",
            response.getContent(),
            Event.class,
            client.getObjectMapper()
        );
    }

    @Override
    public Page<Event> previousPage(
        final Page<Event> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.MONITOR.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Event> nextPage(
        final Page<Event> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.MONITOR.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Event> getPage(
        final String targetUrl,
        final TwilioRestClient client
    ) {
        Request request = new Request(HttpMethod.GET, targetUrl);

        return pageForRequest(client, request);
    }

    private void addQueryParams(final Request request) {
        if (actorSid != null) {
            request.addQueryParam("ActorSid", actorSid);
        }
        if (eventType != null) {
            request.addQueryParam("EventType", eventType);
        }
        if (resourceSid != null) {
            request.addQueryParam("ResourceSid", resourceSid);
        }
        if (sourceIpAddress != null) {
            request.addQueryParam("SourceIpAddress", sourceIpAddress);
        }
        if (startDate != null) {
            request.addQueryParam(
                "StartDate",
                startDate.toInstant().toString()
            );
        }

        if (endDate != null) {
            request.addQueryParam("EndDate", endDate.toInstant().toString());
        }

        if (pageSize != null) {
            request.addQueryParam("PageSize", pageSize.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}