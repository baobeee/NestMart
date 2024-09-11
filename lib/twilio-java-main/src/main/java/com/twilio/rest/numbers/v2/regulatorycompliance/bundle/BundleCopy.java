/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Numbers
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.numbers.v2.regulatorycompliance.bundle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class BundleCopy extends Resource {

    private static final long serialVersionUID = 169816383944206L;

    public static BundleCopyCreator creator(final String pathBundleSid) {
        return new BundleCopyCreator(pathBundleSid);
    }

    public static BundleCopyReader reader(final String pathBundleSid) {
        return new BundleCopyReader(pathBundleSid);
    }

    /**
     * Converts a JSON String into a BundleCopy object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return BundleCopy object represented by the provided JSON
     */
    public static BundleCopy fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, BundleCopy.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a BundleCopy object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return BundleCopy object represented by the provided JSON
     */
    public static BundleCopy fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, BundleCopy.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String regulationSid;
    private final String friendlyName;
    private final BundleCopy.Status status;
    private final ZonedDateTime validUntil;
    private final String email;
    private final URI statusCallback;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;

    @JsonCreator
    private BundleCopy(
        @JsonProperty("sid") final String sid,
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("regulation_sid") final String regulationSid,
        @JsonProperty("friendly_name") final String friendlyName,
        @JsonProperty("status") final BundleCopy.Status status,
        @JsonProperty("valid_until") final String validUntil,
        @JsonProperty("email") final String email,
        @JsonProperty("status_callback") final URI statusCallback,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated
    ) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.regulationSid = regulationSid;
        this.friendlyName = friendlyName;
        this.status = status;
        this.validUntil = DateConverter.iso8601DateTimeFromString(validUntil);
        this.email = email;
        this.statusCallback = statusCallback;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final String getRegulationSid() {
        return this.regulationSid;
    }

    public final String getFriendlyName() {
        return this.friendlyName;
    }

    public final BundleCopy.Status getStatus() {
        return this.status;
    }

    public final ZonedDateTime getValidUntil() {
        return this.validUntil;
    }

    public final String getEmail() {
        return this.email;
    }

    public final URI getStatusCallback() {
        return this.statusCallback;
    }

    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BundleCopy other = (BundleCopy) o;

        return (
            Objects.equals(sid, other.sid) &&
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(regulationSid, other.regulationSid) &&
            Objects.equals(friendlyName, other.friendlyName) &&
            Objects.equals(status, other.status) &&
            Objects.equals(validUntil, other.validUntil) &&
            Objects.equals(email, other.email) &&
            Objects.equals(statusCallback, other.statusCallback) &&
            Objects.equals(dateCreated, other.dateCreated) &&
            Objects.equals(dateUpdated, other.dateUpdated)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            sid,
            accountSid,
            regulationSid,
            friendlyName,
            status,
            validUntil,
            email,
            statusCallback,
            dateCreated,
            dateUpdated
        );
    }

    public enum Status {
        DRAFT("draft"),
        PENDING_REVIEW("pending-review"),
        IN_REVIEW("in-review"),
        TWILIO_REJECTED("twilio-rejected"),
        TWILIO_APPROVED("twilio-approved"),
        PROVISIONALLY_APPROVED("provisionally-approved");

        private final String value;

        private Status(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static Status forValue(final String value) {
            return Promoter.enumFromString(value, Status.values());
        }
    }
}