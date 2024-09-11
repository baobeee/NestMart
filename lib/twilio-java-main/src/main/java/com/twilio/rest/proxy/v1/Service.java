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

package com.twilio.rest.proxy.v1;

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
public class Service extends Resource {

    private static final long serialVersionUID = 120259795390146L;

    public static ServiceCreator creator(final String uniqueName) {
        return new ServiceCreator(uniqueName);
    }

    public static ServiceDeleter deleter(final String pathSid) {
        return new ServiceDeleter(pathSid);
    }

    public static ServiceFetcher fetcher(final String pathSid) {
        return new ServiceFetcher(pathSid);
    }

    public static ServiceReader reader() {
        return new ServiceReader();
    }

    public static ServiceUpdater updater(final String pathSid) {
        return new ServiceUpdater(pathSid);
    }

    /**
     * Converts a JSON String into a Service object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Service object represented by the provided JSON
     */
    public static Service fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Service.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Service object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Service object represented by the provided JSON
     */
    public static Service fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Service.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String uniqueName;
    private final String accountSid;
    private final String chatInstanceSid;
    private final URI callbackUrl;
    private final Integer defaultTtl;
    private final Service.NumberSelectionBehavior numberSelectionBehavior;
    private final Service.GeoMatchLevel geoMatchLevel;
    private final URI interceptCallbackUrl;
    private final URI outOfSessionCallbackUrl;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private Service(
        @JsonProperty("sid") final String sid,
        @JsonProperty("unique_name") final String uniqueName,
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("chat_instance_sid") final String chatInstanceSid,
        @JsonProperty("callback_url") final URI callbackUrl,
        @JsonProperty("default_ttl") final Integer defaultTtl,
        @JsonProperty(
            "number_selection_behavior"
        ) final Service.NumberSelectionBehavior numberSelectionBehavior,
        @JsonProperty(
            "geo_match_level"
        ) final Service.GeoMatchLevel geoMatchLevel,
        @JsonProperty("intercept_callback_url") final URI interceptCallbackUrl,
        @JsonProperty(
            "out_of_session_callback_url"
        ) final URI outOfSessionCallbackUrl,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated,
        @JsonProperty("url") final URI url,
        @JsonProperty("links") final Map<String, String> links
    ) {
        this.sid = sid;
        this.uniqueName = uniqueName;
        this.accountSid = accountSid;
        this.chatInstanceSid = chatInstanceSid;
        this.callbackUrl = callbackUrl;
        this.defaultTtl = defaultTtl;
        this.numberSelectionBehavior = numberSelectionBehavior;
        this.geoMatchLevel = geoMatchLevel;
        this.interceptCallbackUrl = interceptCallbackUrl;
        this.outOfSessionCallbackUrl = outOfSessionCallbackUrl;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
        this.links = links;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getUniqueName() {
        return this.uniqueName;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final String getChatInstanceSid() {
        return this.chatInstanceSid;
    }

    public final URI getCallbackUrl() {
        return this.callbackUrl;
    }

    public final Integer getDefaultTtl() {
        return this.defaultTtl;
    }

    public final Service.NumberSelectionBehavior getNumberSelectionBehavior() {
        return this.numberSelectionBehavior;
    }

    public final Service.GeoMatchLevel getGeoMatchLevel() {
        return this.geoMatchLevel;
    }

    public final URI getInterceptCallbackUrl() {
        return this.interceptCallbackUrl;
    }

    public final URI getOutOfSessionCallbackUrl() {
        return this.outOfSessionCallbackUrl;
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

    public final Map<String, String> getLinks() {
        return this.links;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Service other = (Service) o;

        return (
            Objects.equals(sid, other.sid) &&
            Objects.equals(uniqueName, other.uniqueName) &&
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(chatInstanceSid, other.chatInstanceSid) &&
            Objects.equals(callbackUrl, other.callbackUrl) &&
            Objects.equals(defaultTtl, other.defaultTtl) &&
            Objects.equals(
                numberSelectionBehavior,
                other.numberSelectionBehavior
            ) &&
            Objects.equals(geoMatchLevel, other.geoMatchLevel) &&
            Objects.equals(interceptCallbackUrl, other.interceptCallbackUrl) &&
            Objects.equals(
                outOfSessionCallbackUrl,
                other.outOfSessionCallbackUrl
            ) &&
            Objects.equals(dateCreated, other.dateCreated) &&
            Objects.equals(dateUpdated, other.dateUpdated) &&
            Objects.equals(url, other.url) &&
            Objects.equals(links, other.links)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            sid,
            uniqueName,
            accountSid,
            chatInstanceSid,
            callbackUrl,
            defaultTtl,
            numberSelectionBehavior,
            geoMatchLevel,
            interceptCallbackUrl,
            outOfSessionCallbackUrl,
            dateCreated,
            dateUpdated,
            url,
            links
        );
    }

    public enum GeoMatchLevel {
        AREA_CODE("area-code"),
        OVERLAY("overlay"),
        RADIUS("radius"),
        COUNTRY("country");

        private final String value;

        private GeoMatchLevel(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static GeoMatchLevel forValue(final String value) {
            return Promoter.enumFromString(value, GeoMatchLevel.values());
        }
    }

    public enum NumberSelectionBehavior {
        AVOID_STICKY("avoid-sticky"),
        PREFER_STICKY("prefer-sticky");

        private final String value;

        private NumberSelectionBehavior(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static NumberSelectionBehavior forValue(final String value) {
            return Promoter.enumFromString(
                value,
                NumberSelectionBehavior.values()
            );
        }
    }
}