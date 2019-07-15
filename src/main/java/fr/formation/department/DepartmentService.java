package fr.formation.department;

import java.util.List;

public interface DepartmentService {

    Department findByName(String name);

    List<Department> findAll();
}
