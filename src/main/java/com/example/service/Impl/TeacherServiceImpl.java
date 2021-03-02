package com.example.service.Impl;

import com.example.entity.Teacher;
import com.example.mapper.TeacherMapper;
import com.example.service.TeacherService;
import com.example.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public PageBean<Teacher> queryPage(Map<String, Object> paramMap) {
        PageBean<Teacher> pageBean = new PageBean<>((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<Teacher> datas = teacherMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = teacherMapper.count(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public int deleteTeacher(List<Integer> ids) {
        return teacherMapper.deleteBatchIds(ids);
        // return teacherMapper.deleteTeacher(ids);
    }

    @Override
    public int addTeacher(Teacher aTeacher) {
        return teacherMapper.insert(aTeacher);
        // return teacherMapper.addTeacher(aTeacher);
    }

    @Override
    public Teacher getById(Integer tid) {
        return teacherMapper.selectById(tid);
        // return teacherMapper.getById(tid);
    }

    @Override
    public int updateTeacher(Teacher aTeacher) {
        return teacherMapper.updateTeacher(aTeacher);
    }

    @Override
    public Teacher login(Teacher aTeacher) {
        return teacherMapper.login(aTeacher);
    }

    @Override
    public int updatePasswordByTeacher(Teacher aTeacher) {
        return teacherMapper.updatePasswordByTeacher(aTeacher);
    }
}
