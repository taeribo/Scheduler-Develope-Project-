package com.schedule.controller;

import com.schedule.dto.*;
import com.schedule.entity.Schedule;
import com.schedule.service.ScheduleService;
import org.hibernate.sql.exec.spi.PostAction;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    // 일정 생성
    @PostMapping
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request){
        CreateScheduleResponse response = scheduleService.createSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    // 전체 조회
    @GetMapping
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedule(){
        List<GetScheduleResponse> responses = scheduleService.getAllSchedules();
        return ResponseEntity.ok(responses);
    }
    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<GetScheduleResponse> getScheduleById(@PathVariable Long id){
        GetScheduleResponse response = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(response);
    }
    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @PathVariable Long id,
            @RequestBody UpdateScheduleRequest request){
        UpdateScheduleResponse response = scheduleService.updateSchedule(id,request);
        return ResponseEntity.ok(response);
    }
    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok().build();
    }
}
