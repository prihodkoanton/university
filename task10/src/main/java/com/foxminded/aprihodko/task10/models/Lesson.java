package com.foxminded.aprihodko.task10.models;

import java.time.DayOfWeek;
import java.util.Objects;

public class Lesson {
    private DayOfWeek dayOfWeek;
    private int id;
    private int roomId;
    private int groupId;
    private int courseId;
    private int teacherId;

    public Lesson(DayOfWeek dayOfWeek, int id, int roomId, int groupId, int courseId, int teacherId) {
        this.dayOfWeek = dayOfWeek;
        this.id = id;
        this.roomId = roomId;
        this.groupId = groupId;
        this.courseId = courseId;
        this.teacherId = teacherId;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, dayOfWeek, groupId, id, roomId, teacherId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Lesson other = (Lesson) obj;
        return courseId == other.courseId && dayOfWeek == other.dayOfWeek && groupId == other.groupId && id == other.id
                && roomId == other.roomId && teacherId == other.teacherId;
    }
}
