package app.work.dao;

import app.work.domain.WorkEntry;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.jooq.util.maven.example.Tables.WORK_ENTRIES;

@Repository
public class WorkEntryDao {

    @Autowired
    private DSLContext dslContext;

    public List<WorkEntry> getAllWorkEntries() {
        return dslContext.selectFrom(WORK_ENTRIES).fetchInto(WorkEntry.class);
    }

    public Optional<WorkEntry> getEntry(long entryId){
        return dslContext.selectFrom(WORK_ENTRIES)
                .where(WORK_ENTRIES.ENTRY_ID.eq(entryId))
                .fetchOptionalInto(WorkEntry.class);
    }

    public void addWorkEntry(WorkEntry workEntry) {
        dslContext.insertInto(WORK_ENTRIES, WORK_ENTRIES.EMPLOYEE_ID, WORK_ENTRIES.START_TIME, WORK_ENTRIES.FINISH_TIME)
                .values(workEntry.getEmployeeId(), Timestamp.valueOf(workEntry.getStartTime()), Timestamp.valueOf(workEntry.getFinishTime()))
                .execute();
    }

    public void deleteWorkEntry(long id) {
        dslContext.deleteFrom(WORK_ENTRIES)
                .where(WORK_ENTRIES.ENTRY_ID.eq(id))
                .execute();
    }

    public void updateWorkEntry(long id, WorkEntry workEntry) {
        dslContext.update(WORK_ENTRIES)
                .set(WORK_ENTRIES.EMPLOYEE_ID, workEntry.getEmployeeId())
                .set(WORK_ENTRIES.START_TIME,  Timestamp.valueOf(workEntry.getStartTime()))
                .set(WORK_ENTRIES.FINISH_TIME, Timestamp.valueOf(workEntry.getFinishTime()))
                .where(WORK_ENTRIES.ENTRY_ID.eq(id))
                .execute();
    }

    public List<WorkEntry> getAllEntriesFromEmployee(long id) {
        return dslContext.selectFrom(WORK_ENTRIES).where(WORK_ENTRIES.EMPLOYEE_ID.eq(id)).fetchInto(WorkEntry.class);
    }

    public List<WorkEntry> getMonthlyEmployeeEntries(long id, int month, int year) {
        LocalDateTime from = LocalDateTime.of(year, month, 1, 0 ,0);
        LocalDateTime to = from.plusMonths(1);
        return dslContext.selectFrom(WORK_ENTRIES)
                .where(WORK_ENTRIES.EMPLOYEE_ID.eq(id))
                .and(WORK_ENTRIES.START_TIME.between(Timestamp.valueOf(from), Timestamp.valueOf(to)))
                .fetchInto(WorkEntry.class);
    }

    public List<WorkEntry> getEmployeeTaxYearWorkEntries(long id, int yearStart, int yearFinish) {
        Timestamp from = Timestamp.valueOf(LocalDateTime.of(yearStart, 4, 1, 0, 0));
        Timestamp to  = Timestamp.valueOf(LocalDateTime.of(yearFinish, 3, 31, 23, 59));
        return dslContext.selectFrom(WORK_ENTRIES)
                .where(WORK_ENTRIES.EMPLOYEE_ID.eq(id))
                .and(WORK_ENTRIES.START_TIME.between(from, to))
                .fetchInto(WorkEntry.class);
    }
}

