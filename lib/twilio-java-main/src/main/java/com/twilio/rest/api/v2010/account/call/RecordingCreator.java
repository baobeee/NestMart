/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Api
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.api.v2010.account.call;

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
import java.net.URI;
import java.net.URI;
import java.util.List;
import java.util.List;

public class RecordingCreator extends Creator<Recording> {

    private String pathCallSid;
    private String pathAccountSid;
    private List<String> recordingStatusCallbackEvent;
    private URI recordingStatusCallback;
    private HttpMethod recordingStatusCallbackMethod;
    private String trim;
    private String recordingChannels;
    private String recordingTrack;

    public RecordingCreator(final String pathCallSid) {
        this.pathCallSid = pathCallSid;
    }

    public RecordingCreator(
        final String pathAccountSid,
        final String pathCallSid
    ) {
        this.pathAccountSid = pathAccountSid;
        this.pathCallSid = pathCallSid;
    }

    public RecordingCreator setRecordingStatusCallbackEvent(
        final List<String> recordingStatusCallbackEvent
    ) {
        this.recordingStatusCallbackEvent = recordingStatusCallbackEvent;
        return this;
    }

    public RecordingCreator setRecordingStatusCallbackEvent(
        final String recordingStatusCallbackEvent
    ) {
        return setRecordingStatusCallbackEvent(
            Promoter.listOfOne(recordingStatusCallbackEvent)
        );
    }

    public RecordingCreator setRecordingStatusCallback(
        final URI recordingStatusCallback
    ) {
        this.recordingStatusCallback = recordingStatusCallback;
        return this;
    }

    public RecordingCreator setRecordingStatusCallback(
        final String recordingStatusCallback
    ) {
        return setRecordingStatusCallback(
            Promoter.uriFromString(recordingStatusCallback)
        );
    }

    public RecordingCreator setRecordingStatusCallbackMethod(
        final HttpMethod recordingStatusCallbackMethod
    ) {
        this.recordingStatusCallbackMethod = recordingStatusCallbackMethod;
        return this;
    }

    public RecordingCreator setTrim(final String trim) {
        this.trim = trim;
        return this;
    }

    public RecordingCreator setRecordingChannels(
        final String recordingChannels
    ) {
        this.recordingChannels = recordingChannels;
        return this;
    }

    public RecordingCreator setRecordingTrack(final String recordingTrack) {
        this.recordingTrack = recordingTrack;
        return this;
    }

    @Override
    public Recording create(final TwilioRestClient client) {
        String path =
            "/2010-04-01/Accounts/{AccountSid}/Calls/{CallSid}/Recordings.json";

        this.pathAccountSid =
            this.pathAccountSid == null
                ? client.getAccountSid()
                : this.pathAccountSid;
        path =
            path.replace(
                "{" + "AccountSid" + "}",
                this.pathAccountSid.toString()
            );
        path = path.replace("{" + "CallSid" + "}", this.pathCallSid.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException(
                "Recording creation failed: Unable to connect to server"
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

        return Recording.fromJson(
            response.getStream(),
            client.getObjectMapper()
        );
    }

    private void addPostParams(final Request request) {
        if (recordingStatusCallbackEvent != null) {
            for (String prop : recordingStatusCallbackEvent) {
                request.addPostParam("RecordingStatusCallbackEvent", prop);
            }
        }
        if (recordingStatusCallback != null) {
            request.addPostParam(
                "RecordingStatusCallback",
                recordingStatusCallback.toString()
            );
        }
        if (recordingStatusCallbackMethod != null) {
            request.addPostParam(
                "RecordingStatusCallbackMethod",
                recordingStatusCallbackMethod.toString()
            );
        }
        if (trim != null) {
            request.addPostParam("Trim", trim);
        }
        if (recordingChannels != null) {
            request.addPostParam("RecordingChannels", recordingChannels);
        }
        if (recordingTrack != null) {
            request.addPostParam("RecordingTrack", recordingTrack);
        }
    }
}
