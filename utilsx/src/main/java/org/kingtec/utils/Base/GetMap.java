package org.kingtec.utils.Base;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 03/04/2019.
 */

public class GetMap {


    public static final class Builder {
        Map<String, String> map;

        public Builder(String rowFor) {
            this.map = new HashMap<>();
            this.map.put(Fields.RowFor, rowFor);
        }

        public Map<String, String> get() {
            return map;

        }

        public Builder setRole(String role) {
            this.map.put(Fields.role, role);
            return this;
        }

        public Builder setClassID(String classID) {
            return setValue(Fields.ClassID, classID);
        }

        public Builder setSection(String section) {
            return setValue(Fields.Section, section);
        }

        public Builder setExamid(String examid) {
            return setValue(Fields.Examid, examid);
        }

        public Builder setBranchName(String branchName) {
            return setValue(Fields.BranchName, branchName);
        }

        public Builder setId(String id) {
            return setValue(Fields.id, id);
        }

        public Builder setStudentID(String studentID) {
            return setValue(Fields.StudentID, studentID);
        }

        public Builder setSchoolId(String schoolId) {
            return setValue(Fields.school_id, schoolId);
        }

        public Builder setRowFor(String rowFor) {
            return setValue(Fields.RowFor, rowFor);
        }

        public Builder setNewID(String newID) {
            return setValue(Fields.NewID, newID);
        }

        public Builder setValue(String name, String value) {
            this.map.put(name, value);
            return this;
        }
    }

}
