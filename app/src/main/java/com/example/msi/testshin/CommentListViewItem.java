package com.example.msi.testshin;

import android.graphics.drawable.Drawable;

/**
 * Created by hansangjun on 2017. 2. 25..
 */
public class CommentListViewItem {

    private Drawable iconDrawable ;
    private String idStr ;
    private String commentStr;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setIdStr(String id){idStr=id;}
    public void setCommentStr(String comment){commentStr = comment;}


    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getIdStr() { return this.idStr ; }
    public String getCommentStr() { return this.commentStr ; }
}
