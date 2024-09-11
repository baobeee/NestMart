/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Messaging
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.messaging.v1.brandregistration;

import com.twilio.base.Creator;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class BrandRegistrationOtpCreator extends Creator<BrandRegistrationOtp> {

    private String pathBrandRegistrationSid;

    public BrandRegistrationOtpCreator(final String pathBrandRegistrationSid) {
        this.pathBrandRegistrationSid = pathBrandRegistrationSid;
    }

    @Override
    public BrandRegistrationOtp create(final TwilioRestClient client) {
        String path =
            "/v1/a2p/BrandRegistrations/{BrandRegistrationSid}/SmsOtp";

        path =
            path.replace(
                "{" + "BrandRegistrationSid" + "}",
                this.pathBrandRegistrationSid.toString()
            );

        Request request = new Request(
            HttpMethod.POST,
            Domains.MESSAGING.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException(
                "BrandRegistrationOtp creation failed: Unable to connect to server"
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

        return BrandRegistrationOtp.fromJson(
            response.getStream(),
            client.getObjectMapper()
        );
    }
}
