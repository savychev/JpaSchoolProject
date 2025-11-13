package be.intecbrussel.service;

import be.intecbrussel.model.School;
import be.intecbrussel.repository.SchoolRepository;

import java.util.List;

public class SchoolService {

    private SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public void addSchool(School school) {
        schoolRepository.create(school);
    }

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public void updateSchool(Long id, School newSchoolData) {
        School existingSchool = schoolRepository.findById(id);
        if (existingSchool != null) {
            existingSchool.setName(newSchoolData.getName());
            existingSchool.setCity(newSchoolData.getCity());
            schoolRepository.update(existingSchool);
        }
    }

    public void removeSchool(Long id) {
        schoolRepository.delete(id);
    }
}
