package com.example.rodo.memesaurios.Models;


import android.os.Parcel;
import android.os.Parcelable;

public class Posts implements Parcelable {
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private String body;


    public Posts(){}
    public Posts(String userId, String id, String title, String body){
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Posts(Parcel in) {
        this.userId = in.readString();
        this.id = in.readString();
        this.title = in.readString();
        this.body = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(body);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public Posts createFromParcel(Parcel in){
            return new Posts(in);
        }
        @Override
        public Posts[] newArray(int size){
            return new Posts[size];
        }
    };


}
