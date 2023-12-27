package com.tooth.service;

import com.tooth.domain.Student;
import com.tooth.domain.StudentPW;
import com.tooth.repository.StudentPWRepository;
import com.tooth.repository.StudentRepository;
import com.tooth.service.dto.PWDTO;
import com.tooth.service.dto.StudentDTO;
import com.tooth.service.dto.StudentPWDTO;
import com.tooth.service.dto.UserDTO;
import com.tooth.service.mapper.StudentPWMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.tooth.domain.StudentPW}.
 */
@Service
@Transactional
public class StudentPWService {

    private final Logger log = LoggerFactory.getLogger(StudentPWService.class);

    private final StudentPWRepository studentPWRepository;

    private final StudentPWMapper studentPWMapper;

    @Autowired
    StudentRepository studentRepository;

    public StudentPWService(StudentPWRepository studentPWRepository, StudentPWMapper studentPWMapper) {
        this.studentPWRepository = studentPWRepository;
        this.studentPWMapper = studentPWMapper;
    }

    /**
     * Save a studentPW.
     *
     * @param studentPWDTO the entity to save.
     * @return the persisted entity.
     */
    public StudentPWDTO save(StudentPWDTO studentPWDTO) {
        log.debug("Request to save StudentPW : {}", studentPWDTO);
        StudentPW studentPW = studentPWMapper.toEntity(studentPWDTO);
        studentPW = studentPWRepository.save(studentPW);
        return studentPWMapper.toDto(studentPW);
    }

    /**
     * Update a studentPW.
     *
     * @param studentPWDTO the entity to save.
     * @return the persisted entity.
     */
    public StudentPWDTO update(StudentPWDTO studentPWDTO) {
        log.debug("Request to update StudentPW : {}", studentPWDTO);
        StudentPW studentPW = studentPWMapper.toEntity(studentPWDTO);
        studentPW = studentPWRepository.save(studentPW);
        return studentPWMapper.toDto(studentPW);
    }

    /**
     * Partially update a studentPW.
     *
     * @param studentPWDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<StudentPWDTO> partialUpdate(StudentPWDTO studentPWDTO) {
        log.debug("Request to partially update StudentPW : {}", studentPWDTO);

        return studentPWRepository
            .findById(studentPWDTO.getId())
            .map(existingStudentPW -> {
                studentPWMapper.partialUpdate(existingStudentPW, studentPWDTO);

                return existingStudentPW;
            })
            .map(studentPWRepository::save)
            .map(studentPWMapper::toDto);
    }

    /**
     * Get all the studentPWS.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<StudentPWDTO> findAll() {
        log.debug("Request to get all StudentPWS");
        return studentPWRepository.findAll().stream().map(studentPWMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the studentPWS with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<StudentPWDTO> findAllWithEagerRelationships(Pageable pageable) {
        return studentPWRepository.findAllWithEagerRelationships(pageable).map(studentPWMapper::toDto);
    }

    /**
     * Get one studentPW by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StudentPWDTO> findOne(Long id) {
        log.debug("Request to get StudentPW : {}", id);
        return studentPWRepository.findOneWithEagerRelationships(id).map(studentPWMapper::toDto);
    }

    /**
     * Delete the studentPW by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete StudentPW : {}", id);
        studentPWRepository.deleteById(id);
    }

    public List<StudentPWDTO> findPWByStudent(String username) {
        Student student = studentRepository.findStudentByUserName(username);
        List<StudentPW> studentPWS = studentPWRepository.findStudentPWByStudent(student.getId());
        return studentPWS
            .stream()
            .map(g -> {
                StudentPWDTO gt = new StudentPWDTO();
                gt.setId(g.getId());
                gt.setDate(g.getDate());
                gt.setImageFront(g.getImageFront());
                gt.setImageFrontContentType(g.getImageFrontContentType());
                gt.setTime(g.getTime());
                gt.setAngleCenter(g.getAngleCenter());
                gt.setAngleLeft(g.getAngleLeft());
                gt.setAngleRigth(g.getAngleRigth());
                gt.setImageSide(g.getImageSide());

                StudentDTO st = new StudentDTO();
                st.setId(g.getStudent().getId());
                st.setCin(g.getStudent().getCin());
                st.setCne(g.getStudent().getCne());
                st.setBirthDay(g.getStudent().getBirthDay());
                st.setNumber(g.getStudent().getNumber());

                UserDTO us = new UserDTO();
                us.setId(g.getStudent().getUser().getId());
                us.setLogin(g.getStudent().getUser().getLogin());
                st.setUser(us);
                gt.setStudent(st);

                PWDTO pw = new PWDTO();
                pw.setId(g.getPw().getId());
                pw.setTitle(g.getPw().getTitle());
                gt.setPw(pw);
                return gt;
            })
            .collect(Collectors.toList());
    }
}
