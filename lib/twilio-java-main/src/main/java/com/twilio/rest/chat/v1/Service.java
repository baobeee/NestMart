/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Chat
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.chat.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Service extends Resource {

    private static final long serialVersionUID = 258386577185944L;

    public static ServiceCreator creator(final String friendlyName) {
        return new ServiceCreator(friendlyName);
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
    private final String accountSid;
    private final String friendlyName;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final String defaultServiceRoleSid;
    private final String defaultChannelRoleSid;
    private final String defaultChannelCreatorRoleSid;
    private final Boolean readStatusEnabled;
    private final Boolean reachabilityEnabled;
    private final Integer typingIndicatorTimeout;
    private final Integer consumptionReportInterval;
    private final Map<String, Object> limits;
    private final Map<String, Object> webhooks;
    private final String preWebhookUrl;
    private final String postWebhookUrl;
    private final String webhookMethod;
    private final List<String> webhookFilters;
    private final Map<String, Object> notifications;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private Service(
        @JsonProperty("sid") final String sid,
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("friendly_name") final String friendlyName,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated,
        @JsonProperty(
            "default_service_role_sid"
        ) final String defaultServiceRoleSid,
        @JsonProperty(
            "default_channel_role_sid"
        ) final String defaultChannelRoleSid,
        @JsonProperty(
            "default_channel_creator_role_sid"
        ) final String defaultChannelCreatorRoleSid,
        @JsonProperty("read_status_enabled") final Boolean readStatusEnabled,
        @JsonProperty("reachability_enabled") final Boolean reachabilityEnabled,
        @JsonProperty(
            "typing_indicator_timeout"
        ) final Integer typingIndicatorTimeout,
        @JsonProperty(
            "consumption_report_interval"
        ) final Integer consumptionReportInterval,
        @JsonProperty("limits") final Map<String, Object> limits,
        @JsonProperty("webhooks") final Map<String, Object> webhooks,
        @JsonProperty("pre_webhook_url") final String preWebhookUrl,
        @JsonProperty("post_webhook_url") final String postWebhookUrl,
        @JsonProperty("webhook_method") final String webhookMethod,
        @JsonProperty("webhook_filters") final List<String> webhookFilters,
        @JsonProperty("notifications") final Map<String, Object> notifications,
        @JsonProperty("url") final URI url,
        @JsonProperty("links") final Map<String, String> links
    ) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.friendlyName = friendlyName;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.defaultServiceRoleSid = defaultServiceRoleSid;
        this.defaultChannelRoleSid = defaultChannelRoleSid;
        this.defaultChannelCreatorRoleSid = defaultChannelCreatorRoleSid;
        this.readStatusEnabled = readStatusEnabled;
        this.reachabilityEnabled = reachabilityEnabled;
        this.typingIndicatorTimeout = typingIndicatorTimeout;
        this.consumptionReportInterval = consumptionReportInterval;
        this.limits = limits;
        this.webhooks = webhooks;
        this.preWebhookUrl = preWebhookUrl;
        this.postWebhookUrl = postWebhookUrl;
        this.webhookMethod = webhookMethod;
        this.webhookFilters = webhookFilters;
        this.notifications = notifications;
        this.url = url;
        this.links = links;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final String getFriendlyName() {
        return this.friendlyName;
    }

    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    public final String getDefaultServiceRoleSid() {
        return this.defaultServiceRoleSid;
    }

    public final String getDefaultChannelRoleSid() {
        return this.defaultChannelRoleSid;
    }

    public final String getDefaultChannelCreatorRoleSid() {
        return this.defaultChannelCreatorRoleSid;
    }

    public final Boolean getReadStatusEnabled() {
        return this.readStatusEnabled;
    }

    public final Boolean getReachabilityEnabled() {
        return this.reachabilityEnabled;
    }

    public final Integer getTypingIndicatorTimeout() {
        return this.typingIndicatorTimeout;
    }

    public final Integer getConsumptionReportInterval() {
        return this.consumptionReportInterval;
    }

    public final Map<String, Object> getLimits() {
        return this.limits;
    }

    public final Map<String, Object> getWebhooks() {
        return this.webhooks;
    }

    public final String getPreWebhookUrl() {
        return this.preWebhookUrl;
    }

    public final String getPostWebhookUrl() {
        return this.postWebhookUrl;
    }

    public final String getWebhookMethod() {
        return this.webhookMethod;
    }

    public final List<String> getWebhookFilters() {
        return this.webhookFilters;
    }

    public final Map<String, Object> getNotifications() {
        return this.notifications;
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
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(friendlyName, other.friendlyName) &&
            Objects.equals(dateCreated, other.dateCreated) &&
            Objects.equals(dateUpdated, other.dateUpdated) &&
            Objects.equals(
                defaultServiceRoleSid,
                other.defaultServiceRoleSid
            ) &&
            Objects.equals(
                defaultChannelRoleSid,
                other.defaultChannelRoleSid
            ) &&
            Objects.equals(
                defaultChannelCreatorRoleSid,
                other.defaultChannelCreatorRoleSid
            ) &&
            Objects.equals(readStatusEnabled, other.readStatusEnabled) &&
            Objects.equals(reachabilityEnabled, other.reachabilityEnabled) &&
            Objects.equals(
                typingIndicatorTimeout,
                other.typingIndicatorTimeout
            ) &&
            Objects.equals(
                consumptionReportInterval,
                other.consumptionReportInterval
            ) &&
            Objects.equals(limits, other.limits) &&
            Objects.equals(webhooks, other.webhooks) &&
            Objects.equals(preWebhookUrl, other.preWebhookUrl) &&
            Objects.equals(postWebhookUrl, other.postWebhookUrl) &&
            Objects.equals(webhookMethod, other.webhookMethod) &&
            Objects.equals(webhookFilters, other.webhookFilters) &&
            Objects.equals(notifications, other.notifications) &&
            Objects.equals(url, other.url) &&
            Objects.equals(links, other.links)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            sid,
            accountSid,
            friendlyName,
            dateCreated,
            dateUpdated,
            defaultServiceRoleSid,
            defaultChannelRoleSid,
            defaultChannelCreatorRoleSid,
            readStatusEnabled,
            reachabilityEnabled,
            typingIndicatorTimeout,
            consumptionReportInterval,
            limits,
            webhooks,
            preWebhookUrl,
            postWebhookUrl,
            webhookMethod,
            webhookFilters,
            notifications,
            url,
            links
        );
    }
}
