
package uwanttolearn.astro.core.data.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChannelExtRef {

    @SerializedName("system")
    @Expose
    private String system;
    @SerializedName("subSystem")
    @Expose
    private String subSystem;
    @SerializedName("value")
    @Expose
    private String value;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getSubSystem() {
        return subSystem;
    }

    public void setSubSystem(String subSystem) {
        this.subSystem = subSystem;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ChannelExtRef{" +
                "system='" + system + '\'' +
                ", subSystem='" + subSystem + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
