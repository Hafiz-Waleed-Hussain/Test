
package uwanttolearn.astro.core.data.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChannelLists {

    @SerializedName("responseCode")
    @Expose
    private String responseCode;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("channels")
    @Expose
    private List<Channel> channels = null;
    @SerializedName("channel")
    @Expose
    private List<Channel> channel = null;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<Channel> getMinimalChannelsInfo() {
        return channels;
    }

    public void setMinimalChannelsInfo(List<Channel> channels) {
        this.channels = channels;
    }

    public List<Channel> getChannel() {
        return channel;
    }

    public void setChannel(List<Channel> channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "ChannelLists{" +
                "responseCode='" + responseCode + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                ", channels=" + channels +
                ", channel=" + channel +
                '}';
    }
}
