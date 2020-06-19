package com.example.dell.big_wanandroid.GreenDaos;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Dell on 2019/5/7.
 */
@Entity
public class Person {

    @Id(autoincrement = true)
    private Long id;
    private String author;
    private String chapterName;
    private String niceDate;
    private String title;
    @Generated(hash = 438797902)
    public Person(Long id, String author, String chapterName, String niceDate,
            String title) {
        this.id = id;
        this.author = author;
        this.chapterName = chapterName;
        this.niceDate = niceDate;
        this.title = title;
    }
    @Generated(hash = 1024547259)
    public Person() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getChapterName() {
        return this.chapterName;
    }
    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }
    public String getNiceDate() {
        return this.niceDate;
    }
    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


}
