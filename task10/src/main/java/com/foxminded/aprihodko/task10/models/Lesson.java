package com.foxminded.aprihodko.task10.models;

import java.time.DayOfWeek;
import java.util.Objects;

public class Lesson extends LongEntity {
    private DayOfWeek dayOfWeek;
    private Long roomId;
    private Long groupId;
    private Long courseId;
    private Long teacherId;
    private int timeSpan;

    public Lesson(Long id, DayOfWeek dayOfWeek, Long roomId, Long groupId, Long courseId, Long teacherId,
            int timeSpan) {
        super(id);
        this.dayOfWeek = dayOfWeek;
        this.roomId = roomId;
        this.groupId = groupId;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.timeSpan = timeSpan;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public int getLessonNum() {
        return timeSpan;
    }

    public void setLessonNum(int lessonNum) {
        this.timeSpan = lessonNum;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(courseId, dayOfWeek, groupId, roomId, teacherId, timeSpan);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Lesson other = (Lesson) obj;
        return Objects.equals(courseId, other.courseId) && dayOfWeek == other.dayOfWeek
                && Objects.equals(groupId, other.groupId) && Objects.equals(roomId, other.roomId)
                && Objects.equals(teacherId, other.teacherId) && timeSpan == other.timeSpan;
    }
}
