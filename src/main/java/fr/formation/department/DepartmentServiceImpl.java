package fr.formation.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department findByName(String name) {
        return departmentRepository.findByName(name);
    }

    public Department findByCode(String code) { return departmentRepository.findByCode(code); }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

}
