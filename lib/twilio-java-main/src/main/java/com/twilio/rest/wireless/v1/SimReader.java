/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Wireless
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.wireless.v1;

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

public class SimReader extends Reader<Sim> {

    private Sim.Status status;
    private String iccid;
    private String ratePlan;
    private String eid;
    private String simRegistrationCode;
    private Integer pageSize;

    public SimReader() {}

    public SimReader setStatus(final Sim.Status status) {
        this.status = status;
        return this;
    }

    public SimReader setIccid(final String iccid) {
        this.iccid = iccid;
        return this;
    }

    public SimReader setRatePlan(final String ratePlan) {
        this.ratePlan = ratePlan;
        return this;
    }

    public SimReader setEid(final String eid) {
        this.eid = eid;
        return this;
    }

    public SimReader setSimRegistrationCode(final String simRegistrationCode) {
        this.simRegistrationCode = simRegistrationCode;
        return this;
    }

    public SimReader setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    public ResourceSet<Sim> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    public Page<Sim> firstPage(final TwilioRestClient client) {
        String path = "/v1/Sims";

        Request request = new Request(
            HttpMethod.GET,
            Domains.WIRELESS.toString(),
            path
        );

        addQueryParams(request);
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        return pageForRequest(client, request);
    }

    private Page<Sim> pageForRequest(
        final TwilioRestClient client,
        final Request request
    ) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "Sim read failed: Unable to connect to server"
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
            "sims",
            response.getContent(),
            Sim.class,
            client.getObjectMapper()
        );
    }

    @Override
    public Page<Sim> previousPage(
        final Page<Sim> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.WIRELESS.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Sim> nextPage(
        final Page<Sim> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.WIRELESS.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Sim> getPage(
        final String targetUrl,
        final TwilioRestClient client
    ) {
        Request request = new Request(HttpMethod.GET, targetUrl);

        return pageForRequest(client, request);
    }

    private void addQueryParams(final Request request) {
        if (status != null) {
            request.addQueryParam("Status", status.toString());
        }
        if (iccid != null) {
            request.addQueryParam("Iccid", iccid);
        }
        if (ratePlan != null) {
            request.addQueryParam("RatePlan", ratePlan);
        }
        if (eid != null) {
            request.addQueryParam("EId", eid);
        }
        if (simRegistrationCode != null) {
            request.addQueryParam("SimRegistrationCode", simRegistrationCode);
        }
        if (pageSize != null) {
            request.addQueryParam("PageSize", pageSize.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}
