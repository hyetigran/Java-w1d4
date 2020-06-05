package local.tigran.webemployees;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.lang.Character;

@RestController
@RequestMapping("/data")
public class EmployeeController {

    @GetMapping(value = "/allemployees", produces = {"application/json"})
    public ResponseEntity<?> getAllEmployees() {
        WebEmployeesApplication.ourEmpList.empList.sort((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()));
        return new ResponseEntity<>(WebEmployeesApplication.ourEmpList.empList, HttpStatus.OK);
    }

    @GetMapping(value = "/employee/{empid}", produces = {"application/json"})
    public ResponseEntity<?> getEmployeeById(@PathVariable long empid) {
        Employee rtnEmp = WebEmployeesApplication.ourEmpList.findEmployee((e -> (e.getId() == empid)));
        return new ResponseEntity<>(rtnEmp, HttpStatus.OK);
    }

    @GetMapping(value = "/employees/{letter}", produces = {"application/json"})
    public ResponseEntity<?> getEmloyeesByLetter(@PathVariable char letter) {
        List<Employee> rtnEmps = WebEmployeesApplication.ourEmpList.findEmployees(e -> e.getFname().toUpperCase().charAt(0) == Character.toUpperCase(letter));
        return new ResponseEntity<>(rtnEmps, HttpStatus.OK);
    }
}
