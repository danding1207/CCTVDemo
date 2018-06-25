package com.msc.cctvdemo.bean;

public class TvData {

    private String  tvName;
    private String  tvIcon;
    private String  tvUri;

    public TvData(String tvName, String tvIcon, String  tvUri) {
        this.tvName = tvName;
        this.tvIcon = tvIcon;
        this.tvUri = tvUri;
    }

    public TvData( ) {
    }

    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public String getTvIcon() {
        return tvIcon;
    }

    public void setTvIcon(String tvIcon) {
        this.tvIcon = tvIcon;
    }

    public String getTvUri() {
        return tvUri;
    }

    public void setTvUri(String tvUri) {
        this.tvUri = tvUri;
    }

    @Override
    public String toString() {
        return "TvData{" +
                "tvName='" + tvName + '\'' +
                ", tvIcon='" + tvIcon + '\'' +
                ", tvUri='" + tvUri + '\'' +
                '}';
    }
}
