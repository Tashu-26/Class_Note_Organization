package com.noteorg.adapter;

import com.noteorg.model.Subject;
import com.noteorg.repository.SubjectRepository;

public class SubjectAdapter {

    private SubjectRepository subjectRepository;

    public SubjectAdapter(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

    public void delete(Subject subject) {
        subjectRepository.delete(subject);
    }
}