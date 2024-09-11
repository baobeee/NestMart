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
import java.util.Map;
import java.util.Map;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PrebuiltOperator extends Resource {

    private static final long serialVersionUID = 114402468875706L;

    public static PrebuiltOperatorFetcher fetcher(final String pathSid) {
        return new PrebuiltOperatorFetcher(pathSid);
    }

    public static PrebuiltOperatorReader reader() {
        return new PrebuiltOperatorReader();
    }

    /**
     * Converts a JSON String into a PrebuiltOperator object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return PrebuiltOperator object represented by the provided JSON
     */
    public static PrebuiltOperator fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, PrebuiltOperator.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a PrebuiltOperator object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return PrebuiltOperator object represented by the provided JSON
     */
    public static PrebuiltOperator fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, PrebuiltOperator.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String sid;
    private final String friendlyName;
    private final String description;
    private final String author;
    private final String operatorType;
    private final Integer version;
    private final PrebuiltOperator.Availability availability;
    private final Map<String, Object> config;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final URI url;

    @JsonCreator
    private PrebuiltOperator(
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("sid") final String sid,
        @JsonProperty("friendly_name") final String friendlyName,
        @JsonProperty("description") final String description,
        @JsonProperty("author") final String author,
        @JsonProperty("operator_type") final String operatorType,
        @JsonProperty("version") final Integer version,
        @JsonProperty(
            "availability"
        ) final PrebuiltOperator.Availability availability,
        @JsonProperty("config") final Map<String, Object> config,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated,
        @JsonProperty("url") final URI url
    ) {
        this.accountSid = accountSid;
        this.sid = sid;
        this.friendlyName = friendlyName;
        this.description = description;
        this.author = author;
        this.operatorType = operatorType;
        this.version = version;
        this.availability = availability;
        this.config = config;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getFriendlyName() {
        return this.friendlyName;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getAuthor() {
        return this.author;
    }

    public final String getOperatorType() {
        return this.operatorType;
    }

    public final Integer getVersion() {
        return this.version;
    }

    public final PrebuiltOperator.Availability getAvailability() {
        return this.availability;
    }

    public final Map<String, Object> getConfig() {
        return this.config;
    }

    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    public final URI getUrl() {
        return this.url;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PrebuiltOperator other = (PrebuiltOperator) o;

        return (
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(sid, other.sid) &&
            Objects.equals(friendlyName, other.friendlyName) &&
            Objects.equals(description, other.description) &&
            Objects.equals(author, other.author) &&
            Objects.equals(operatorType, other.operatorType) &&
            Objects.equals(version, other.version) &&
            Objects.equals(availability, other.availability) &&
            Objects.equals(config, other.config) &&
            Objects.equals(dateCreated, other.dateCreated) &&
            Objects.equals(dateUpdated, other.dateUpdated) &&
            Objects.equals(url, other.url)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            accountSid,
            sid,
            friendlyName,
            description,
            author,
            operatorType,
            version,
            availability,
            config,
            dateCreated,
            dateUpdated,
            url
        );
    }

    public enum Availability {
        INTERNAL("internal"),
        BETA("beta"),
        PUBLIC("public"),
        RETIRED("retired");

        private final String value;

        private Availability(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static Availability forValue(final String value) {
            return Promoter.enumFromString(value, Availability.values());
        }
    }
}
