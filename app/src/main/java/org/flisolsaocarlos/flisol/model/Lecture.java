package org.flisolsaocarlos.flisol.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Lecture implements Parcelable {

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Lecture createFromParcel(Parcel parcel) {
                    return new Lecture(parcel);
                }

                public Lecture[] newArray(int size) {
                    return new Lecture[size];
                }
            };
    int id;
    private String title;
    private String description;
    private String lecturer;
    private String field;
    private String scheduleBegin;
    private String scheduleEnd;
    private String room;

    public Lecture(final String title, final String description, final String lecturer, final String field,
                   final String scheduleBegin, final String scheduleEnd, final String room) {
        this.title = title;
        this.description = description;
        this.lecturer = lecturer;
        this.field = field;
        this.scheduleBegin = scheduleBegin;
        this.scheduleEnd = scheduleEnd;
        this.room = room;
    }

    public Lecture() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lecture(Parcel parcel) {
        readFromParcel(parcel);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getScheduleBegin() {
        return scheduleBegin;
    }

    public void setScheduleBegin(String scheduleBegin) {
        this.scheduleBegin = scheduleBegin;
    }

    public String getScheduleEnd() {
        return scheduleEnd;
    }

    public void setScheduleEnd(String scheduleEnd) {
        this.scheduleEnd = scheduleEnd;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(lecturer);
        parcel.writeString(field);
        parcel.writeString(scheduleBegin);
        parcel.writeString(scheduleEnd);
        parcel.writeString(room);
    }

    public void readFromParcel(Parcel parcel) {
        title = parcel.readString();
        description = parcel.readString();
        lecturer = parcel.readString();
        field = parcel.readString();
        scheduleBegin = parcel.readString();
        scheduleEnd = parcel.readString();
        room = parcel.readString();
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "title='" + title + '\'' +
                '}';
    }
}
