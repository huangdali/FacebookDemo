package com.jwkj.facebook.bean;

/**
 * facebook登录成功之后的结果
 * Created by dali on 2017/5/23.
 */

public class FBLoginResult {

    /**
     * id : 108860823033523
     * name : 黄大力
     * link : https://www.facebook.com/app_scoped_user_id/108860823033523/
     * gender : male
     * picture : {"data":{"is_silhouette":false,"url":"https://fb-s-a-a.akamaihd.net/h-ak-fbx/v/t1.0-1/p50x50/18581763_108650206387918_9197493858679064288_n.jpg?oh=7647bc6c5273b665c7a2692c1e8f1e05&oe=59AF63D5&__gda__=1503814626_5fcee6d153bc1040276d18bb745d3762"}}
     * locale : zh_CN
     * updated_time : 2017-05-23T03:03:13+0000
     * timezone : 8
     * age_range : {"max":20,"min":18}
     * first_name : 大力
     * last_name : 黄
     */

    private String id;//用户id
    private String name;//姓名
    private String link;//主页
    private String emali;//邮箱
    private String gender;//性别
    private PictureBean picture;//图片
    private String locale;//地域信息
    private String updated_time;//最后刷新时间
    private int timezone;//时区
    private AgeRangeBean age_range;//年龄范围
    private String first_name;//名
    private String last_name;//姓

    public String getEmali() {
        return emali;
    }

    public void setEmali(String emali) {
        this.emali = emali;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public PictureBean getPicture() {
        return picture;
    }

    public void setPicture(PictureBean picture) {
        this.picture = picture;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public AgeRangeBean getAge_range() {
        return age_range;
    }

    public void setAge_range(AgeRangeBean age_range) {
        this.age_range = age_range;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public static class PictureBean {
        /**
         * data : {"is_silhouette":false,"url":"https://fb-s-a-a.akamaihd.net/h-ak-fbx/v/t1.0-1/p50x50/18581763_108650206387918_9197493858679064288_n.jpg?oh=7647bc6c5273b665c7a2692c1e8f1e05&oe=59AF63D5&__gda__=1503814626_5fcee6d153bc1040276d18bb745d3762"}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * is_silhouette : false
             * url : https://fb-s-a-a.akamaihd.net/h-ak-fbx/v/t1.0-1/p50x50/18581763_108650206387918_9197493858679064288_n.jpg?oh=7647bc6c5273b665c7a2692c1e8f1e05&oe=59AF63D5&__gda__=1503814626_5fcee6d153bc1040276d18bb745d3762
             */

            private boolean is_silhouette;//是否轮廓
            private String url;//图片地址

            public boolean isIs_silhouette() {
                return is_silhouette;
            }

            public void setIs_silhouette(boolean is_silhouette) {
                this.is_silhouette = is_silhouette;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {

                this.url = url;

            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "is_silhouette=" + is_silhouette +
                        ", url='" + url + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "PictureBean{" +
                    "data=" + data +
                    '}';
        }
    }

    public static class AgeRangeBean {
        /**
         * max : 20
         * min : 18
         */

        private int max;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {

            this.min = min;
        }

        @Override
        public String toString() {
            return "AgeRangeBean{" +
                    "max=" + max +
                    ", min=" + min +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "FBLoginResult{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", emali='" + emali + '\'' +
                ", gender='" + gender + '\'' +
                ", picture=" + picture +
                ", locale='" + locale + '\'' +
                ", updated_time='" + updated_time + '\'' +
                ", timezone=" + timezone +
                ", age_range=" + age_range +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
