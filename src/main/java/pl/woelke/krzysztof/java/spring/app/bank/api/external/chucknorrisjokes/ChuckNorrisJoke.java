package pl.woelke.krzysztof.java.spring.app.bank.api.external.chucknorrisjokes;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "icon_url",
        "id",
        "url",
        "value"
})
@Generated("jsonschema2pojo")
public class ChuckNorrisJoke {

    @JsonProperty("icon_url")
    private String iconUrl;
    @JsonProperty("id")
    private String id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("value")
    private String value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("icon_url")
    public String getIconUrl() {
        return iconUrl;
    }

    @JsonProperty("icon_url")
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "ChuckNorrisJoke{" +
                "iconUrl='" + iconUrl + '\'' +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", value='" + value + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}