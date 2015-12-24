package com.wen.demo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangenning on 15/11/19.
 */
public class PhotoBean implements Parcelable {

    /**
     * error : false
     */

    private boolean error;
    /**
     * who : 张涵宇
     * publishedAt : 2015-11-19T03:57:22.178Z
     * desc : 11.19
     * type : 福利
     * url : http://ww2.sinaimg.cn/large/7a8aed7bjw1ey6238m03pj20gy0op77l.jpg
     * used : true
     * objectId : 564d2e8060b20fc970e4efa0
     * createdAt : 2015-11-19T02:05:52.684Z
     * updatedAt : 2015-11-19T03:57:23.135Z
     */

    private List<ResultsEntity> results;

    public static PhotoBean objectFromData(String str) {

        return new Gson().fromJson(str, PhotoBean.class);
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public static class ResultsEntity {

        private String who;
        private String publishedAt;
        private String desc;
        private String type;
        private String url;
        private boolean used;
        private String objectId;
        private String createdAt;
        private String updatedAt;

        public static ResultsEntity objectFromData(String str) {

            return new Gson().fromJson(str, ResultsEntity.class);
        }

        public void setWho(String who) {
            this.who = who;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getWho() {
            return who;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public String getDesc() {
            return desc;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }

        public boolean isUsed() {
            return used;
        }

        public String getObjectId() {
            return objectId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(error ? (byte) 1 : (byte) 0);
        dest.writeList(this.results);
    }

    public PhotoBean() {
    }

    protected PhotoBean(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = new ArrayList<ResultsEntity>();
        in.readList(this.results, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<PhotoBean> CREATOR = new Parcelable.Creator<PhotoBean>() {
        public PhotoBean createFromParcel(Parcel source) {
            return new PhotoBean(source);
        }

        public PhotoBean[] newArray(int size) {
            return new PhotoBean[size];
        }
    };
}
