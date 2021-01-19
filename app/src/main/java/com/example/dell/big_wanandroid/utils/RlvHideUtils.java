package com.example.dell.big_wanandroid.utils;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;

import q.rorbin.verticaltablayout.VerticalTabLayout;

/**
 * Created by Dell on 2019/5/7.
 */

public class RlvHideUtils {

    private static boolean isScroll;


    public static void Hide(RadioGroup radioGroup, RecyclerView recyclerView, FloatingActionButton floatingActionButton) {


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //重写该方法主要是判断recyclerview是否在滑动
                //0停止 ，1,2都是滑动
                if (newState == 0) {
                    isScroll = false;
                } else {
                    isScroll = true;
                }

            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //这个主要是recyclerview滑动时让tab定位的方法

               /*recyclerView : 当前滚动的view
dx : 水平滚动距离
dy : 垂直滚动距离
dx > 0 时为手指向左滚动,列表滚动显示右面的内容
dx < 0 时为手指向右滚动,列表滚动显示左面的内容
dy > 0 时为手指向上滚动,列表滚动显示下面的内容
dy < 0 时为手指向下滚动,列表滚动显示上面的内容*/
                int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstVisibleItem != 0) {
                    if (dy > 15) {
                        floatingActionButton.setVisibility(View.GONE);
                        radioGroup.setVisibility(View.GONE);
                    } else if (dy < -15) {
                        floatingActionButton.setVisibility(View.VISIBLE);
                        radioGroup.setVisibility(View.VISIBLE);
                    }
                }
//                  if (isScroll) {
//                      if (dy < 0) {
//                          radioGroup.setVisibility(View.VISIBLE);
//                          floatingActionButton.setVisibility(View.VISIBLE);
//                      } else if (dy > 0) {
//                          radioGroup.setVisibility(View.GONE);
//                          floatingActionButton.setVisibility(View.GONE);
//                      }
//                  }
            }
        });

    }    public static void HideWechtList( RecyclerView recyclerView, FloatingActionButton floatingActionButton) {


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //重写该方法主要是判断recyclerview是否在滑动
                //0停止 ，1,2都是滑动
                if (newState == 0) {
                    isScroll = false;
                } else {
                    isScroll = true;
                }

            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //这个主要是recyclerview滑动时让tab定位的方法

               /*recyclerView : 当前滚动的view
dx : 水平滚动距离
dy : 垂直滚动距离
dx > 0 时为手指向左滚动,列表滚动显示右面的内容
dx < 0 时为手指向右滚动,列表滚动显示左面的内容
dy > 0 时为手指向上滚动,列表滚动显示下面的内容
dy < 0 时为手指向下滚动,列表滚动显示上面的内容*/
                int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstVisibleItem != 0) {
                    if (dy > 15) {
                        floatingActionButton.setVisibility(View.GONE);
                    } else if (dy < -15) {
                        floatingActionButton.setVisibility(View.VISIBLE);
                    }
                }
//                  if (isScroll) {
//                      if (dy < 0) {
//                          radioGroup.setVisibility(View.VISIBLE);
//                          floatingActionButton.setVisibility(View.VISIBLE);
//                      } else if (dy > 0) {
//                          radioGroup.setVisibility(View.GONE);
//                          floatingActionButton.setVisibility(View.GONE);
//                      }
//                  }
            }
        });

    }

    public static void MoveHide(VerticalTabLayout tabLayout, RadioGroup radioGroup, RecyclerView recyclerView, FloatingActionButton floatingActionButton) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //重写该方法主要是判断recyclerview是否在滑动
                //0停止 ，1,2都是滑动
                if (newState == 0) {
                    isScroll = false;
                } else {
                    isScroll = true;
                }
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //可见的第一条条目
                int top = layoutManager.findFirstVisibleItemPosition();
                tabLayout.setTabSelected(top);
            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //这个主要是recyclerview滑动时让tab定位的方法

               /*recyclerView : 当前滚动的view
dx : 水平滚动距离
dy : 垂直滚动距离
dx > 0 时为手指向左滚动,列表滚动显示右面的内容
dx < 0 时为手指向右滚动,列表滚动显示左面的内容
dy > 0 时为手指向上滚动,列表滚动显示下面的内容
dy < 0 时为手指向下滚动,列表滚动显示上面的内容*/
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //可见的第一条条目
                int top = layoutManager.findFirstVisibleItemPosition();
                //可见的最后一条条目
                int bottom = layoutManager.findLastVisibleItemPosition();
                if (isScroll) {
                    if (dy < 0) {
                        radioGroup.setVisibility(View.VISIBLE);
                        floatingActionButton.setVisibility(View.VISIBLE);
                    } else if (dy > 0) {
                        tabLayout.setTabSelected(top);
                        radioGroup.setVisibility(View.GONE);
                        floatingActionButton.setVisibility(View.GONE);
                    }
                }

            }
        });

    }

    public static void TabHide(RadioGroup radioGroup, RecyclerView recyclerView, FloatingActionButton floatingActionButton) {


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //重写该方法主要是判断recyclerview是否在滑动
                //0停止 ，1,2都是滑动
                if (newState == 0) {
                    isScroll = false;
                } else {
                    isScroll = true;
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //这个主要是recyclerview滑动时让tab定位的方法

               /*recyclerView : 当前滚动的view
dx : 水平滚动距离
dy : 垂直滚动距离
dx > 0 时为手指向左滚动,列表滚动显示右面的内容
dx < 0 时为手指向右滚动,列表滚动显示左面的内容
dy > 0 时为手指向上滚动,列表滚动显示下面的内容
dy < 0 时为手指向下滚动,列表滚动显示上面的内容*/
                int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstVisibleItem != 0) {
                    if (dy > 15) {
                        floatingActionButton.setVisibility(View.GONE);
                        radioGroup.setVisibility(View.GONE);
                    } else if (dy < -15) {
                        floatingActionButton.setVisibility(View.VISIBLE);
                        radioGroup.setVisibility(View.VISIBLE);
                    }
                }
                 /* if (isScroll) {
                      if (dy < 0) {
                          radioGroup.setVisibility(View.VISIBLE);
                      } else if (dy > 0) {
                          radioGroup.setVisibility(View.GONE);
                          floatingActionButton.setVisibility(View.GONE);
                      }
                  }*/
            }
        });

    }

    public static void OnClicks(FloatingActionButton floatingActionButton, RecyclerView recyclerView) {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);

            }
        });
    }

    public static void OnTabClicks(FloatingActionButton floatingActionButton, RecyclerView recyclerView) {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }


}
