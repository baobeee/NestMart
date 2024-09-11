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

import com.twilio.base.Creator;
import com.twilio.constant.EnumConstants;
import com.twilio.converter.Converter;
import com.twilio.converter.Converter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.util.Map;
import java.util.Map;

public class CustomOperatorCreator extends Creator<CustomOperator> {

    private String friendlyName;
    private String operatorType;
    private Map<String, Object> config;

    public CustomOperatorCreator(
        final String friendlyName,
        final String operatorType,
        final Map<String, Object> config
    ) {
        this.friendlyName = friendlyName;
        this.operatorType = operatorType;
        this.config = config;
    }

    public CustomOperatorCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public CustomOperatorCreator setOperatorType(final String operatorType) {
        this.operatorType = operatorType;
        return this;
    }

    public CustomOperatorCreator setConfig(final Map<String, Object> config) {
        this.config = config;
        return this;
    }

    @Override
    public CustomOperator create(final TwilioRestClient client) {
        String path = "/v2/Operators/Custom";

        path =
            path.replace(
                "{" + "FriendlyName" + "}",
                this.friendlyName.toString()
            );
        path =
            path.replace(
                "{" + "OperatorType" + "}",
                this.operatorType.toString()
            );
        path = path.replace("{" + "Config" + "}", this.config.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.INTELLIGENCE.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException(
                "CustomOperator creation failed: Unable to connect to server"
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

        return CustomOperator.fromJson(
            response.getStream(),
            client.getObjectMapper()
        );
    }

    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        if (operatorType != null) {
            request.addPostParam("OperatorType", operatorType);
        }
        if (config != null) {
            request.addPostParam("Config", Converter.mapToJson(config));
        }
    }
}
