package com.foxminded.aprihodko.task10.models;

import java.time.DayOfWeek;
import java.util.Objects;

public class Lesson extends LongEntity {

    public static final String LESSON_ID = "lesson_id";
    public static final String LESSON_DAY_OF_WEEK = "lesson_day_of_week";
    public static final String LESSON_TIME_SPAN = "lesson_time_span";
    public static final String ROOM_REF = "room_ref";
    public static final String GROUP_REF = "group_ref";
    public static final String COURSE_REF = "course_ref";
    public static final String TEACHER_REF = "teacher_ref";

    private DayOfWeek dayOfWeek;
    private int timeSpan;
    private Long roomId;
    private Long groupId;
    private Long courseId;
    private Long teacherId;

    public Lesson(Long id, DayOfWeek dayOfWeek, int timeSpan, Long roomId, Long groupId, Long courseId,
                  Long teacherId) {
        super(id);
        this.dayOfWeek = dayOfWeek;
        this.timeSpan = timeSpan;
        this.roomId = roomId;
        this.groupId = groupId;
        this.courseId = courseId;
        this.teacherId = teacherId;

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
