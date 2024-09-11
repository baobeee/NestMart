/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Proxy
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.proxy.v1.service;

import com.twilio.base.Creator;
import com.twilio.constant.EnumConstants;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class PhoneNumberCreator extends Creator<PhoneNumber> {

    private String pathServiceSid;
    private String sid;
    private com.twilio.type.PhoneNumber phoneNumber;
    private Boolean isReserved;

    public PhoneNumberCreator(final String pathServiceSid) {
        this.pathServiceSid = pathServiceSid;
    }

    public PhoneNumberCreator setSid(final String sid) {
        this.sid = sid;
        return this;
    }

    public PhoneNumberCreator setPhoneNumber(
        final com.twilio.type.PhoneNumber phoneNumber
    ) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public PhoneNumberCreator setPhoneNumber(final String phoneNumber) {
        return setPhoneNumber(Promoter.phoneNumberFromString(phoneNumber));
    }

    public PhoneNumberCreator setIsReserved(final Boolean isReserved) {
        this.isReserved = isReserved;
        return this;
    }

    @Override
    public PhoneNumber create(final TwilioRestClient client) {
        String path = "/v1/Services/{ServiceSid}/PhoneNumbers";

        path =
            path.replace(
                "{" + "ServiceSid" + "}",
                this.pathServiceSid.toString()
            );

        Request request = new Request(
            HttpMethod.POST,
            Domains.PROXY.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException(
                "PhoneNumber creation failed: Unable to connect to server"
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

        return PhoneNumber.fromJson(
            response.getStream(),
            client.getObjectMapper()
        );
    }

    private void addPostParams(final Request request) {
        if (sid != null) {
            request.addPostParam("Sid", sid);
        }
        if (phoneNumber != null) {
            request.addPostParam("PhoneNumber", phoneNumber.toString());
        }
        if (isReserved != null) {
            request.addPostParam("IsReserved", isReserved.toString());
        }
    }
}
