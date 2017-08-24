package app.work.controller;

import app.work.domain.WorkEntry;
import app.work.service.EmployeeService;
import app.work.service.WorkEntryService;
import app.work.validator.WorkEntryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/work/")
@RestController
public class WorkEntryController {

    @Autowired
    private WorkEntryService workEntryService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private WorkEntryValidator workEntryValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(workEntryValidator);
    }

    @GetMapping("/entry/{entryId}")
    public ResponseEntity<WorkEntry> getWorkEntry(@PathVariable int entryId) {
        if (workEntryService.getEntry(entryId).isPresent()) {
            return new ResponseEntity<>(workEntryService.getEntry(entryId).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/entry")
    public void addWorkEntry(@RequestBody @Valid WorkEntry workEntry) {
        workEntryService.addWorkEntry(workEntry);
    }

    @DeleteMapping("entry/{entryId}")
    public void deleteWorkEntry(@PathVariable int entryId) {
        workEntryService.deleteWorkEntry(entryId);
    }

    @PutMapping("entry/{entryId}")
    public void updateWorkEntry(@PathVariable long entryId, @RequestBody @Valid WorkEntry workEntry) {
        workEntryService.updateWorkEntry(entryId, workEntry);
    }

    @GetMapping("/entries/")
    public List<WorkEntry> getAllWorkEntries() {
        return workEntryService.getAllWorkEntries();
    }

    @GetMapping("/entries/{employeeId}")
    public ResponseEntity<List<WorkEntry>> getEmployeeEntries(@PathVariable long employeeId) {
        if (employeeService.isEmployeePresent(employeeId)) {
            return new ResponseEntity<>(workEntryService.getAllEntriesFromEmployee(employeeId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/hours/{employeeId}/{year}/{month}")
    public ResponseEntity getMonthlyHoursFromEmployee(@PathVariable long employeeId, @PathVariable int year, @PathVariable int month) {
        if (employeeService.isEmployeePresent(employeeId)) {
            return new ResponseEntity<>(workEntryService.getHoursFromEntriesList(workEntryService.getMonthlyEmployeeEntries(employeeId, month, year)), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/salary/gross/{employeeId}/{year}/{month}")
    public ResponseEntity getMonthlyGrossSalary(@PathVariable long employeeId, @PathVariable int year, @PathVariable int month) {
        if (employeeService.isEmployeePresent(employeeId)) {
            return new ResponseEntity<>(workEntryService.getMonthlyGrossSalary(employeeId, month, year), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/salary/net/{employeeId}/{year}/{month}")
    public ResponseEntity getMonthlyNetSalary(@PathVariable long employeeId, @PathVariable int year, @PathVariable int month) {
        if (employeeService.isEmployeePresent(employeeId)) {
            return new ResponseEntity<>(workEntryService.getMonthlyNetSalary(employeeId, month, year), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/salary/taxyear/{id}/{yearStart}/{yearFinish}")
    public ResponseEntity getEmployeeTaxYearSalary(@PathVariable long id, @PathVariable int yearStart, @PathVariable int yearFinish) {
        if (employeeService.isEmployeePresent(id)) {
            return new ResponseEntity<>(workEntryService.getEmployeeTaxYearGrossSalary(id, yearStart, yearFinish), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}