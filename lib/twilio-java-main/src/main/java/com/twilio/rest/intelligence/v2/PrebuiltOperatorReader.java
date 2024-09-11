/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Intelligence
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.intelligence.v2;

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

public class PrebuiltOperatorReader extends Reader<PrebuiltOperator> {

    private PrebuiltOperator.Availability availability;
    private String languageCode;
    private Integer pageSize;

    public PrebuiltOperatorReader() {}

    public PrebuiltOperatorReader setAvailability(
        final PrebuiltOperator.Availability availability
    ) {
        this.availability = availability;
        return this;
    }

    public PrebuiltOperatorReader setLanguageCode(final String languageCode) {
        this.languageCode = languageCode;
        return this;
    }

    public PrebuiltOperatorReader setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    public ResourceSet<PrebuiltOperator> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    public Page<PrebuiltOperator> firstPage(final TwilioRestClient client) {
        String path = "/v2/Operators/PreBuilt";

        Request request = new Request(
            HttpMethod.GET,
            Domains.INTELLIGENCE.toString(),
            path
        );

        addQueryParams(request);
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        return pageForRequest(client, request);
    }

    private Page<PrebuiltOperator> pageForRequest(
        final TwilioRestClient client,
        final Request request
    ) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "PrebuiltOperator read failed: Unable to connect to server"
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
            "operators",
            response.getContent(),
            PrebuiltOperator.class,
            client.getObjectMapper()
        );
    }

    @Override
    public Page<PrebuiltOperator> previousPage(
        final Page<PrebuiltOperator> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.INTELLIGENCE.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<PrebuiltOperator> nextPage(
        final Page<PrebuiltOperator> page,
        final TwilioRestClient client
    ) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.INTELLIGENCE.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<PrebuiltOperator> getPage(
        final String targetUrl,
        final TwilioRestClient client
    ) {
        Request request = new Request(HttpMethod.GET, targetUrl);

        return pageForRequest(client, request);
    }

    private void addQueryParams(final Request request) {
        if (availability != null) {
            request.addQueryParam("Availability", availability.toString());
        }
        if (languageCode != null) {
            request.addQueryParam("LanguageCode", languageCode);
        }
        if (pageSize != null) {
            request.addQueryParam("PageSize", pageSize.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}
