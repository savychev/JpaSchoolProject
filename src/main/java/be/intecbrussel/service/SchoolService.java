package be.intecbrussel.service;

import be.intecbrussel.model.School;
import be.intecbrussel.repository.SchoolRepository;

import java.util.List;

// This service class handles school operations for the application.
public class SchoolService {

    // This field holds the repository that performs database work.
    private SchoolRepository schoolRepository;

    // This constructor gives the service access to the school repository.
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    // This method sends a new school to the repository to be stored.
    public void addSchool(School school) {
        schoolRepository.create(school);
    }

    // This method returns all schools by asking the repository.
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    // This method updates a school when the school is found.
    public void updateSchool(Long id, School newSchoolData) {
        // This call loads the current school information from the database.
        School existingSchool = schoolRepository.findById(id);
        if (existingSchool != null) {
            existingSchool.setName(newSchoolData.getName());
            existingSchool.setCity(newSchoolData.getCity());
            schoolRepository.update(existingSchool);
        }
    }

    // This method removes a school by delegating to the repository.
    public void removeSchool(Long id) {
        schoolRepository.delete(id);
    }
}
