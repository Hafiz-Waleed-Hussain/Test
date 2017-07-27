
package uwanttolearn.astro.core.data.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LinearOttMapping {

    @SerializedName("platform")
    @Expose
    private String platform;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }


    @Override
    public String toString() {
        return "LinearOttMapping{" +
                "platform='" + platform + '\'' +
                '}';
    }
}
