package fr.formation.department;

import java.util.List;

public interface DepartmentService {

    Department findByName(String name);

    Department findByCode(String code);

    List<Department> findAll();
}
