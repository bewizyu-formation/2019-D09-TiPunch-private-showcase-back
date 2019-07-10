package fr.formation.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department findByName(String name) {
        return departmentRepository.findByName(name);
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

}
