/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Flex
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.flexapi.v1.plugin;

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
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PluginVersions extends Resource {

    private static final long serialVersionUID = 230518982157206L;

    public static PluginVersionsCreator creator(
        final String pathPluginSid,
        final String version,
        final URI pluginUrl
    ) {
        return new PluginVersionsCreator(pathPluginSid, version, pluginUrl);
    }

    public static PluginVersionsFetcher fetcher(
        final String pathPluginSid,
        final String pathSid
    ) {
        return new PluginVersionsFetcher(pathPluginSid, pathSid);
    }

    public static PluginVersionsReader reader(final String pathPluginSid) {
        return new PluginVersionsReader(pathPluginSid);
    }

    /**
     * Converts a JSON String into a PluginVersions object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return PluginVersions object represented by the provided JSON
     */
    public static PluginVersions fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, PluginVersions.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a PluginVersions object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return PluginVersions object represented by the provided JSON
     */
    public static PluginVersions fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, PluginVersions.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String pluginSid;
    private final String accountSid;
    private final String version;
    private final URI pluginUrl;
    private final String changelog;
    private final Boolean _private;
    private final Boolean archived;
    private final Boolean validated;
    private final ZonedDateTime dateCreated;
    private final URI url;

    @JsonCreator
    private PluginVersions(
        @JsonProperty("sid") final String sid,
        @JsonProperty("plugin_sid") final String pluginSid,
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("version") final String version,
        @JsonProperty("plugin_url") final URI pluginUrl,
        @JsonProperty("changelog") final String changelog,
        @JsonProperty("_private") final Boolean _private,
        @JsonProperty("archived") final Boolean archived,
        @JsonProperty("validated") final Boolean validated,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("url") final URI url
    ) {
        this.sid = sid;
        this.pluginSid = pluginSid;
        this.accountSid = accountSid;
        this.version = version;
        this.pluginUrl = pluginUrl;
        this.changelog = changelog;
        this._private = _private;
        this.archived = archived;
        this.validated = validated;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.url = url;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getPluginSid() {
        return this.pluginSid;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final String getVersion() {
        return this.version;
    }

    public final URI getPluginUrl() {
        return this.pluginUrl;
    }

    public final String getChangelog() {
        return this.changelog;
    }

    public final Boolean get_private() {
        return this._private;
    }

    public final Boolean getArchived() {
        return this.archived;
    }

    public final Boolean getValidated() {
        return this.validated;
    }

    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
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

        PluginVersions other = (PluginVersions) o;

        return (
            Objects.equals(sid, other.sid) &&
            Objects.equals(pluginSid, other.pluginSid) &&
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(version, other.version) &&
            Objects.equals(pluginUrl, other.pluginUrl) &&
            Objects.equals(changelog, other.changelog) &&
            Objects.equals(_private, other._private) &&
            Objects.equals(archived, other.archived) &&
            Objects.equals(validated, other.validated) &&
            Objects.equals(dateCreated, other.dateCreated) &&
            Objects.equals(url, other.url)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            sid,
            pluginSid,
            accountSid,
            version,
            pluginUrl,
            changelog,
            _private,
            archived,
            validated,
            dateCreated,
            url
        );
    }
}