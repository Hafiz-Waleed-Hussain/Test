
package uwanttolearn.astro.core.data.json;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Getevent {

    @SerializedName("eventID")
    @Expose
    private String eventID;
    @SerializedName("channelId")
    @Expose
    private long channelId;
    @SerializedName("channelStbNumber")
    @Expose
    private String channelStbNumber;
    @SerializedName("channelHD")
    @Expose
    private String channelHD;
    @SerializedName("channelTitle")
    @Expose
    private String channelTitle;
    @SerializedName("epgEventImage")
    @Expose
    private Object epgEventImage;
    @SerializedName("certification")
    @Expose
    private String certification;
    @SerializedName("displayDateTimeUtc")
    @Expose
    private String displayDateTimeUtc;
    @SerializedName("displayDateTime")
    @Expose
    private String displayDateTime;
    @SerializedName("displayDuration")
    @Expose
    private String displayDuration;
    @SerializedName("siTrafficKey")
    @Expose
    private String siTrafficKey;
    @SerializedName("programmeTitle")
    @Expose
    private String programmeTitle;
    @SerializedName("programmeId")
    @Expose
    private String programmeId;
    @SerializedName("episodeId")
    @Expose
    private String episodeId;
    @SerializedName("shortSynopsis")
    @Expose
    private String shortSynopsis;
    @SerializedName("longSynopsis")
    @Expose
    private Object longSynopsis;
    @SerializedName("actors")
    @Expose
    private String actors;
    @SerializedName("directors")
    @Expose
    private String directors;
    @SerializedName("producers")
    @Expose
    private String producers;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("subGenre")
    @Expose
    private String subGenre;
    @SerializedName("live")
    @Expose
    private boolean live;
    @SerializedName("premier")
    @Expose
    private boolean premier;
    @SerializedName("ottBlackout")
    @Expose
    private boolean ottBlackout;
    @SerializedName("highlight")
    @Expose
    private Object highlight;
    @SerializedName("contentId")
    @Expose
    private Object contentId;
    @SerializedName("contentImage")
    @Expose
    private Object contentImage;
    @SerializedName("groupKey")
    @Expose
    private Object groupKey;
    @SerializedName("vernacularData")
    @Expose
    private List<Object> vernacularData = null;

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public String getChannelStbNumber() {
        return channelStbNumber;
    }

    public void setChannelStbNumber(String channelStbNumber) {
        this.channelStbNumber = channelStbNumber;
    }

    public String getChannelHD() {
        return channelHD;
    }

    public void setChannelHD(String channelHD) {
        this.channelHD = channelHD;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public Object getEpgEventImage() {
        return epgEventImage;
    }

    public void setEpgEventImage(Object epgEventImage) {
        this.epgEventImage = epgEventImage;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getDisplayDateTimeUtc() {
        return displayDateTimeUtc;
    }

    public void setDisplayDateTimeUtc(String displayDateTimeUtc) {
        this.displayDateTimeUtc = displayDateTimeUtc;
    }

    public String getDisplayDateTime() {
        return displayDateTime;
    }

    public void setDisplayDateTime(String displayDateTime) {
        this.displayDateTime = displayDateTime;
    }

    public String getDisplayDuration() {
        return displayDuration;
    }

    public void setDisplayDuration(String displayDuration) {
        this.displayDuration = displayDuration;
    }

    public String getSiTrafficKey() {
        return siTrafficKey;
    }

    public void setSiTrafficKey(String siTrafficKey) {
        this.siTrafficKey = siTrafficKey;
    }

    public String getProgrammeTitle() {
        return programmeTitle;
    }

    public void setProgrammeTitle(String programmeTitle) {
        this.programmeTitle = programmeTitle;
    }

    public String getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public String getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    public String getShortSynopsis() {
        return shortSynopsis;
    }

    public void setShortSynopsis(String shortSynopsis) {
        this.shortSynopsis = shortSynopsis;
    }

    public Object getLongSynopsis() {
        return longSynopsis;
    }

    public void setLongSynopsis(Object longSynopsis) {
        this.longSynopsis = longSynopsis;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubGenre() {
        return subGenre;
    }

    public void setSubGenre(String subGenre) {
        this.subGenre = subGenre;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public boolean isPremier() {
        return premier;
    }

    public void setPremier(boolean premier) {
        this.premier = premier;
    }

    public boolean isOttBlackout() {
        return ottBlackout;
    }

    public void setOttBlackout(boolean ottBlackout) {
        this.ottBlackout = ottBlackout;
    }

    public Object getHighlight() {
        return highlight;
    }

    public void setHighlight(Object highlight) {
        this.highlight = highlight;
    }

    public Object getContentId() {
        return contentId;
    }

    public void setContentId(Object contentId) {
        this.contentId = contentId;
    }

    public Object getContentImage() {
        return contentImage;
    }

    public void setContentImage(Object contentImage) {
        this.contentImage = contentImage;
    }

    public Object getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(Object groupKey) {
        this.groupKey = groupKey;
    }

    public List<Object> getVernacularData() {
        return vernacularData;
    }

    public void setVernacularData(List<Object> vernacularData) {
        this.vernacularData = vernacularData;
    }

}
