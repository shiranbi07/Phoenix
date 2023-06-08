package com.example.springboot;

public class CodeResolve {
    private String classResult;
    private String remarks;
    private Integer errorNumbers;

    public CodeResolve(String classResult, String remarks, Integer errorNumbers) {
        this.classResult = classResult;
        this.remarks = remarks;
        this.errorNumbers = errorNumbers;
    }

    public String getClassResult() {
        return classResult;
    }

    public String getRemarks() {
        return remarks;
    }

    public Integer getErrorNumbers() {
        return errorNumbers;
    }
}

