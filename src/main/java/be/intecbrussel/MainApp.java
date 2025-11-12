package be.intecbrussel;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Student;
import be.intecbrussel.service.SchoolService;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        SchoolService svc = new SchoolService();

        // 1) создать школу + сразу студентов
        Student a = new Student("Anna", "Ivanova");
        Student b = new Student("Bob", "Petrov");
        var school = svc.addSchoolWithStudents("Intec Brussel", "Brussels", List.of(a, b));
        System.out.println("✅ school id=" + school.getId() + " students=" + school.getStudents().size());

        // 2) добавить ещё одного студента в уже существующую школу
        var c = svc.addStudentToSchool(school.getId(), "Carla", "Moreira");
        System.out.println("➕ added student id=" + (c != null ? c.getId() : null));

        // 3) прочитать и обновить школу
        var loaded = svc.getSchool(school.getId());
        System.out.println("🔎 loaded: " + loaded.getName() + " (" + loaded.getCity() + ")");
        svc.updateSchool(loaded.getId(), "Intec Brussels", null);

        // 4) посмотреть все школы
        System.out.println("📋 schools total=" + svc.getAllSchools().size());

        // 5) (опционально) удалить школу
        // boolean removed = svc.removeSchool(school.getId());
        // System.out.println("🗑️ removed? " + removed);

        JpaConfig.close();
    }
}
