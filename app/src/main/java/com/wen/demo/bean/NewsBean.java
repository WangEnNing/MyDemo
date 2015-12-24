package com.wen.demo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangenning on 15/11/20.
 */
public class NewsBean implements Parcelable {

    public BodyEntity body;
    /**
     * documentId : SYDT10
     * expiredTime : 180000
     * id : http://api.3g.ifeng.com/iosNews?id=aid=SYDT10&imgwidth=100&type=list&pagesize=20
     * pageSize : 2
     * type : focus
     */

    public MetaEntity meta;

    public static class BodyEntity implements Parcelable {
        /**
         * channel : 新闻
         * comments : 1284
         * commentsAll : 2195
         * commentsUrl : http://news.ifeng.com/a/20151114/46246822_0.shtml
         * documentId : imcp_102991054
         * editTime : 2015/11/14 22:34:49
         * extensions : [{"style":"","type":"style"}]
         * hasSlide : Y
         * hasSurvey : N
         * hasVideo : N
         * id : http://api.iclient.ifeng.com/ipadtestdoc?aid=102991054&channel=%E6%96%B0%E9%97%BB
         * introduction :
         * links : [{"type":"slide","url":"http://api.iclient.ifeng.com/ipadtestdoc?aid=102991054"},{"type":"doc","url":"http://api.iclient.ifeng.com/ipadtestdoc?aid=102991054&channel=%E6%96%B0%E9%97%BB"}]
         * online : 1
         * source :
         * thumbnail : http://y0.ifengimg.com/ifengimcp/pic/20151114/069f6a224051b20cb732_size149_w640_h360.jpg
         * title : 巴黎：男子靠手机挡住子弹捡回一条命
         * type : list
         * updateTime : 2015/11/14 22:34:49
         */

        public List<ItemEntity> item;

        public static class ItemEntity {
            public String channel;
            public int comments;
            public int commentsAll;
            public String commentsUrl;
            public String documentId;
            public String editTime;
            public String hasSlide;
            public String hasSurvey;
            public String hasVideo;
            public String id;
            public String introduction;
            public String online;
            public String source;
            public String thumbnail;
            public String title;
            public String type;
            public String updateTime;
            /**
             * style :
             * type : style
             */

            public List<ExtensionsEntity> extensions;
            /**
             * type : slide
             * url : http://api.iclient.ifeng.com/ipadtestdoc?aid=102991054
             */

            public List<LinksEntity> links;

            public static class ExtensionsEntity {
                public String style;
                public String type;
            }

            public static class LinksEntity {
                public String type;
                public String url;
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(this.item);
        }

        public BodyEntity() {
        }

        protected BodyEntity(Parcel in) {
            this.item = new ArrayList<ItemEntity>();
            in.readList(this.item, List.class.getClassLoader());
        }

        public static final Creator<BodyEntity> CREATOR = new Creator<BodyEntity>() {
            public BodyEntity createFromParcel(Parcel source) {
                return new BodyEntity(source);
            }

            public BodyEntity[] newArray(int size) {
                return new BodyEntity[size];
            }
        };
    }

    public static class MetaEntity implements Parcelable {
        public String documentId;
        public int expiredTime;
        public String id;
        public int pageSize;
        public String type;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.documentId);
            dest.writeInt(this.expiredTime);
            dest.writeString(this.id);
            dest.writeInt(this.pageSize);
            dest.writeString(this.type);
        }

        public MetaEntity() {
        }

        protected MetaEntity(Parcel in) {
            this.documentId = in.readString();
            this.expiredTime = in.readInt();
            this.id = in.readString();
            this.pageSize = in.readInt();
            this.type = in.readString();
        }

        public static final Creator<MetaEntity> CREATOR = new Creator<MetaEntity>() {
            public MetaEntity createFromParcel(Parcel source) {
                return new MetaEntity(source);
            }

            public MetaEntity[] newArray(int size) {
                return new MetaEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.body, flags);
        dest.writeParcelable(this.meta, flags);
    }

    public NewsBean() {
    }

    protected NewsBean(Parcel in) {
        this.body = in.readParcelable(BodyEntity.class.getClassLoader());
        this.meta = in.readParcelable(MetaEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<NewsBean> CREATOR = new Parcelable.Creator<NewsBean>() {
        public NewsBean createFromParcel(Parcel source) {
            return new NewsBean(source);
        }

        public NewsBean[] newArray(int size) {
            return new NewsBean[size];
        }
    };
}
