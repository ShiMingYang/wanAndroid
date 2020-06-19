package com.example.dell.big_wanandroid.GreenDaos;

import com.example.dell.big_wanandroid.dao.DaoMaster;
import com.example.dell.big_wanandroid.dao.DaoSession;
import com.example.dell.big_wanandroid.dao.PersonDao;
import com.example.dell.big_wanandroid.MainUi.MyApp;

import java.util.List;

/**
 * Created by Dell on 2019/5/8.
 */

public class UtilsDao {
    private static UtilsDao utilsDao;
    private final PersonDao personDao;

    public UtilsDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApp.getsMyApp(), "person.db");

        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getReadableDatabase());

        DaoSession daoSession = daoMaster.newSession();

        personDao = daoSession.getPersonDao();
    }

    public static UtilsDao getUtilsDao() {
        if (utilsDao==null) {
            synchronized (UtilsDao.class){
                if (utilsDao==null) {
                    utilsDao=new UtilsDao();
                }
            }
        }
        return utilsDao;
    }

    public boolean has(Person person){
        List<Person> list = personDao.queryBuilder().where(PersonDao.Properties.Author.eq(person.getAuthor())).list();
        if (list.size()>0) {
            return true;
        }else {
            return false;
        }
    }

    public long inser(Person person){

        if(!has(person)){
          return personDao.insert(person);
        }

        return -1;
    }
    public void deletes(Person person){
        personDao.delete(person);
    }
//    public boolean delete(Person person){
//        if (has(person)) {
//            personDao.delete(person);
//            return true;
//        }else {
//            return false;
//        }
//
//    }

    public List<Person> query(){
        return personDao.queryBuilder().list();
    }
    public Person queryData(Person person){
        return personDao.queryBuilder().where(PersonDao.Properties.Author.eq(person.getAuthor())).unique();
    }
}
