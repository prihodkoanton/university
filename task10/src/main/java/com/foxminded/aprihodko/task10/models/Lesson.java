package com.foxminded.aprihodko.task10.models;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "university.lessons")
public class Lesson extends LongEntity implements Serializable {

    public static final String LESSON_ID = "lesson_id";
    public static final String LESSON_DAY_OF_WEEK = "lesson_day_of_week";
    public static final String LESSON_TIME_SPAN = "lesson_time_span";
    public static final String ROOM_REF = "room_ref";
    public static final String GROUP_REF = "group_ref";
    public static final String COURSE_REF = "course_ref";
    public static final String TEACHER_REF = "teacher_ref";

    @Enumerated(EnumType.STRING)
    @Column(name = "lesson_day_of_week")
    private DayOfWeek dayOfWeek;

    @Column(name = "lesson_time_span")
    private int timeSpan;

    @Column(name = "room_ref")
    private Long roomId;

    @Column(name = "group_ref")
    private Long groupId;

    @Column(name = "course_ref")
    private Long courseId;

    @Column(name = "teacher_ref")
    private Long teacherId;

    public Lesson(Long id, DayOfWeek dayOfWeek, int timeSpan, Long roomId, Long groupId, Long courseId,
            Long teacherId) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.timeSpan = timeSpan;
        this.roomId = roomId;
        this.groupId = groupId;
        this.courseId = courseId;
        this.teacherId = teacherId;

    }

    public Lesson(DayOfWeek dayOfWeek, int timeSpan, Long roomId, Long groupId, Long courseId, Long teacherId) {
        this.id = null;
        this.dayOfWeek = dayOfWeek;
        this.timeSpan = timeSpan;
        this.roomId = roomId;
        this.groupId = groupId;
        this.courseId = courseId;
        this.teacherId = teacherId;
    }

    public Lesson() {

    }

    public int getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(int timeSpan) {
        this.timeSpan = timeSpan;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
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
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Lesson other = (Lesson) obj;
        return Objects.equals(courseId, other.courseId) && dayOfWeek == other.dayOfWeek
                && Objects.equals(groupId, other.groupId) && Objects.equals(roomId, other.roomId)
                && Objects.equals(teacherId, other.teacherId) && timeSpan == other.timeSpan;
    }

    @Override
    public String toString() {
        return "Lesson [dayOfWeek=" + dayOfWeek + ", timeSpan=" + timeSpan + ", roomId=" + roomId + ", groupId="
                + groupId + ", courseId=" + courseId + ", teacherId=" + teacherId + ", id=" + id + "]";
    }

}
