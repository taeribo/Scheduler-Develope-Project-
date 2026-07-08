package com.schedule.service;


import com.schedule.dto.*;
import com.schedule.entity.Schedule;
import com.schedule.entity.User;
import com.schedule.repository.ScheduleRepository;
import com.schedule.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

        // 일정 생성
        public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        Schedule schedule = new Schedule(user, request.getTitle(), request.getContent());
            Schedule savedSchedule = scheduleRepository.save(schedule);
            return new CreateScheduleResponse(savedSchedule);
        }

        // 전체 조회
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(GetScheduleResponse::new)
                .toList();
    }
    //단건조회
    @Transactional(readOnly = true)
    public GetScheduleResponse getScheduleById(Long id){
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));
        return new GetScheduleResponse(schedule);
    }
// 수정
    public UpdateScheduleResponse updateSchedule(Long id, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));
        schedule.update(request.getTitle(), request.getContent());
        scheduleRepository.save(schedule);
        return new UpdateScheduleResponse(schedule);
    }
        //삭제
    public void deleteSchedule(Long id){
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));
        scheduleRepository.delete(schedule);
    }
    }

