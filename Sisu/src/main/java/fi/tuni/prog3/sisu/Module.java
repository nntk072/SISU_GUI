package fi.tuni.prog3.sisu;

import java.util.ArrayList;

/**
 * This class stores information about modules
 * @author xblong
 */
public class Module {

    private final String moduleName;
    private final String moduleCode;
    private ArrayList<StudyModule> studyModules;

    /**
     * Constructor
     * @param moduleName
     * @param moduleCode
     * @param studyModules ArrayList of studyModules
     */
    public Module(String moduleName, String moduleCode, ArrayList<StudyModule> studyModules) {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.studyModules = studyModules;
    }

    /**
     * Get the module name
     * @return module name
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Get the module code
     * @return module name
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Gets the ArrayList which stores study modules
     * @return studyModules
     */
    public ArrayList<StudyModule> getStudyModules() {
        return studyModules;
    }

    /**
     * Add the studyModule into ArrayList
     * @param studyModule wanted study module
     */
    public void addStudyModules(StudyModule studyModule) {
        this.studyModules.add(studyModule);
    }
}

