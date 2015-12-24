package com.wen.demo.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wangenning on 15/12/2.
 */
public class ImageNewsBean {

    /**
     * id : http://api.3g.ifeng.com/iosNews?id=aid=SYDT10&imgwidth=100&type=list&pagesize=20
     * documentId : SYDT10
     * type : focus
     * expiredTime : 180000
     * pageSize : 2
     */

    private MetaEntity meta;
    private BodyEntity body;

    public static ImageNewsBean objectFromData(String str) {

        return new Gson().fromJson(str, ImageNewsBean.class);
    }

    public void setMeta(MetaEntity meta) {
        this.meta = meta;
    }

    public void setBody(BodyEntity body) {
        this.body = body;
    }

    public MetaEntity getMeta() {
        return meta;
    }

    public BodyEntity getBody() {
        return body;
    }

    public static class MetaEntity {
        private String id;
        private String documentId;
        private String type;
        private int expiredTime;
        private int pageSize;

        public static MetaEntity objectFromData(String str) {

            return new Gson().fromJson(str, MetaEntity.class);
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setExpiredTime(int expiredTime) {
            this.expiredTime = expiredTime;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public String getId() {
            return id;
        }

        public String getDocumentId() {
            return documentId;
        }

        public String getType() {
            return type;
        }

        public int getExpiredTime() {
            return expiredTime;
        }

        public int getPageSize() {
            return pageSize;
        }
    }

    public static class BodyEntity {
        /**
         * thumbnail : http://y2.ifengimg.com/ifengimcp/pic/20151202/5a8cf5883098eac1f3c2_size56_w640_h360.jpg
         * hasSlide : Y
         * title : 一个能被wifi杀死的人
         * source :
         * channel : 新闻
         * editTime : 2015/12/02 14:52:15
         * updateTime : 2015/12/02 14:52:15
         * id : http://api.iclient.ifeng.com/ipadtestdoc?aid=103631693&channel=%E6%96%B0%E9%97%BB
         * documentId : imcp_103631693
         * type : list
         * introduction :
         * hasVideo : N
         * hasSurvey : N
         * commentsUrl : http://news.ifeng.com/a/20151202/46489108_0.shtml
         * online : 1
         * comments : 43
         * commentsAll : 173
         * extensions : [{"type":"style","style":""}]
         * links : [{"type":"slide","url":"http://api.iclient.ifeng.com/ipadtestdoc?aid=103631693"},{"type":"doc","url":"http://api.iclient.ifeng.com/ipadtestdoc?aid=103631693&channel=%E6%96%B0%E9%97%BB"}]
         */

        private List<ItemEntity> item;

        public static BodyEntity objectFromData(String str) {

            return new Gson().fromJson(str, BodyEntity.class);
        }

        public void setItem(List<ItemEntity> item) {
            this.item = item;
        }

        public List<ItemEntity> getItem() {
            return item;
        }

        public static class ItemEntity {
            private String thumbnail;
            private String hasSlide;
            private String title;
            private String source;
            private String channel;
            private String editTime;
            private String updateTime;
            private String id;
            private String documentId;
            private String type;
            private String introduction;
            private String hasVideo;
            private String hasSurvey;
            private String commentsUrl;
            private String online;
            private int comments;
            private int commentsAll;
            /**
             * type : style
             * style :
             */

            private List<ExtensionsEntity> extensions;
            /**
             * type : slide
             * url : http://api.iclient.ifeng.com/ipadtestdoc?aid=103631693
             */

            private List<LinksEntity> links;

            public static ItemEntity objectFromData(String str) {

                return new Gson().fromJson(str, ItemEntity.class);
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public void setHasSlide(String hasSlide) {
                this.hasSlide = hasSlide;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public void setEditTime(String editTime) {
                this.editTime = editTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setDocumentId(String documentId) {
                this.documentId = documentId;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public void setHasVideo(String hasVideo) {
                this.hasVideo = hasVideo;
            }

            public void setHasSurvey(String hasSurvey) {
                this.hasSurvey = hasSurvey;
            }

            public void setCommentsUrl(String commentsUrl) {
                this.commentsUrl = commentsUrl;
            }

            public void setOnline(String online) {
                this.online = online;
            }

            public void setComments(int comments) {
                this.comments = comments;
            }

            public void setCommentsAll(int commentsAll) {
                this.commentsAll = commentsAll;
            }

            public void setExtensions(List<ExtensionsEntity> extensions) {
                this.extensions = extensions;
            }

            public void setLinks(List<LinksEntity> links) {
                this.links = links;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public String getHasSlide() {
                return hasSlide;
            }

            public String getTitle() {
                return title;
            }

            public String getSource() {
                return source;
            }

            public String getChannel() {
                return channel;
            }

            public String getEditTime() {
                return editTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public String getId() {
                return id;
            }

            public String getDocumentId() {
                return documentId;
            }

            public String getType() {
                return type;
            }

            public String getIntroduction() {
                return introduction;
            }

            public String getHasVideo() {
                return hasVideo;
            }

            public String getHasSurvey() {
                return hasSurvey;
            }

            public String getCommentsUrl() {
                return commentsUrl;
            }

            public String getOnline() {
                return online;
            }

            public int getComments() {
                return comments;
            }

            public int getCommentsAll() {
                return commentsAll;
            }

            public List<ExtensionsEntity> getExtensions() {
                return extensions;
            }

            public List<LinksEntity> getLinks() {
                return links;
            }

            public static class ExtensionsEntity {
                private String type;
                private String style;

                public static ExtensionsEntity objectFromData(String str) {

                    return new Gson().fromJson(str, ExtensionsEntity.class);
                }

                public void setType(String type) {
                    this.type = type;
                }

                public void setStyle(String style) {
                    this.style = style;
                }

                public String getType() {
                    return type;
                }

                public String getStyle() {
                    return style;
                }
            }

            public static class LinksEntity {
                private String type;
                private String url;

                public static LinksEntity objectFromData(String str) {

                    return new Gson().fromJson(str, LinksEntity.class);
                }

                public void setType(String type) {
                    this.type = type;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getType() {
                    return type;
                }

                public String getUrl() {
                    return url;
                }
            }
        }
    }
}
