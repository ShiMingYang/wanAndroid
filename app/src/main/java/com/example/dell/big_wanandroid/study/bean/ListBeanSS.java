package com.example.dell.big_wanandroid.study.bean;

import java.util.List;

/**
 * Created by Dell on 2019/5/1.
 */

public class ListBeanSS {

    /**
     * data : {"curPage":1,"datas":[{"apkLink":"","author":" Michael周","chapterId":172,"chapterName":"deep link","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3008,"link":"https://blog.zhoulujue.com/DeepLink-On-Android/","niceDate":"2018-06-14","origin":"","prefix":"","projectLink":"","publishTime":1528961058000,"superChapterId":168,"superChapterName":"基础知识","tags":[],"title":"Android 上玩转 DeepLink：如何最大程度的向 App 引流","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"markzhai","chapterId":172,"chapterName":"deep link","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1187,"link":"http://blog.zhaiyifan.cn/2016/02/04/deeplink-intro/","niceDate":"2017-09-29","origin":"","prefix":"","projectLink":"","publishTime":1506653423000,"superChapterId":168,"superChapterName":"基础知识","tags":[],"title":"Deep Link是什么","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"airbnb","chapterId":172,"chapterName":"deep link","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1186,"link":"https://github.com/airbnb/DeepLinkDispatch","niceDate":"2017-09-29","origin":"","prefix":"","projectLink":"","publishTime":1506653378000,"superChapterId":168,"superChapterName":"基础知识","tags":[],"title":"【开源项目】DeepLinkDispatch","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"有为","chapterId":172,"chapterName":"DeepLink","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1185,"link":"https://zhuanlan.zhihu.com/p/21770186","niceDate":"2017-09-29","origin":"","prefix":"","projectLink":"","publishTime":1506653172000,"superChapterId":168,"superChapterName":"基础知识","tags":[],"title":"Android移动开发者必须知道的Deep Linking技术","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"房泽龙","chapterId":172,"chapterName":"deep link","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":998,"link":"http://flyou.ren/2016/09/13/%E5%BE%AE%E4%BF%A1%E6%89%AB%E4%B8%80%E6%89%AB%E6%8F%AD%E7%A7%98/","niceDate":"2016-09-17","origin":"flyou.ren","prefix":"","projectLink":"","publishTime":1474080080000,"superChapterId":168,"superChapterName":"基础知识","tags":[],"title":"微信扫一扫揭秘","type":0,"userId":-1,"visible":1,"zan":0}],"offset":0,"over":true,"pageCount":1,"size":20,"total":5}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","author":" Michael周","chapterId":172,"chapterName":"deep link","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3008,"link":"https://blog.zhoulujue.com/DeepLink-On-Android/","niceDate":"2018-06-14","origin":"","prefix":"","projectLink":"","publishTime":1528961058000,"superChapterId":168,"superChapterName":"基础知识","tags":[],"title":"Android 上玩转 DeepLink：如何最大程度的向 App 引流","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"markzhai","chapterId":172,"chapterName":"deep link","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1187,"link":"http://blog.zhaiyifan.cn/2016/02/04/deeplink-intro/","niceDate":"2017-09-29","origin":"","prefix":"","projectLink":"","publishTime":1506653423000,"superChapterId":168,"superChapterName":"基础知识","tags":[],"title":"Deep Link是什么","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"airbnb","chapterId":172,"chapterName":"deep link","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1186,"link":"https://github.com/airbnb/DeepLinkDispatch","niceDate":"2017-09-29","origin":"","prefix":"","projectLink":"","publishTime":1506653378000,"superChapterId":168,"superChapterName":"基础知识","tags":[],"title":"【开源项目】DeepLinkDispatch","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"有为","chapterId":172,"chapterName":"DeepLink","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1185,"link":"https://zhuanlan.zhihu.com/p/21770186","niceDate":"2017-09-29","origin":"","prefix":"","projectLink":"","publishTime":1506653172000,"superChapterId":168,"superChapterName":"基础知识","tags":[],"title":"Android移动开发者必须知道的Deep Linking技术","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"房泽龙","chapterId":172,"chapterName":"deep link","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":998,"link":"http://flyou.ren/2016/09/13/%E5%BE%AE%E4%BF%A1%E6%89%AB%E4%B8%80%E6%89%AB%E6%8F%AD%E7%A7%98/","niceDate":"2016-09-17","origin":"flyou.ren","prefix":"","projectLink":"","publishTime":1474080080000,"superChapterId":168,"superChapterName":"基础知识","tags":[],"title":"微信扫一扫揭秘","type":0,"userId":-1,"visible":1,"zan":0}]
         * offset : 0
         * over : true
         * pageCount : 1
         * size : 20
         * total : 5
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * apkLink :
             * author :  Michael周
             * chapterId : 172
             * chapterName : deep link
             * collect : false
             * courseId : 13
             * desc :
             * envelopePic :
             * fresh : false
             * id : 3008
             * link : https://blog.zhoulujue.com/DeepLink-On-Android/
             * niceDate : 2018-06-14
             * origin :
             * prefix :
             * projectLink :
             * publishTime : 1528961058000
             * superChapterId : 168
             * superChapterName : 基础知识
             * tags : []
             * title : Android 上玩转 DeepLink：如何最大程度的向 App 引流
             * type : 0
             * userId : -1
             * visible : 1
             * zan : 0
             */

            private String apkLink;
            private String author;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String envelopePic;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String origin;
            private String prefix;
            private String projectLink;
            private long publishTime;
            private int superChapterId;
            private String superChapterName;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;
            private List<?> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public List<?> getTags() {
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
            }
        }
    }
}
