package com.schoolmanagement.config;

import java.util.Locale;

public final class Config {
    private Config() {}

    private static String value(String property, String env, String fallback) {
        String fromProperty = System.getProperty(property);
        if (fromProperty != null && !fromProperty.isBlank()) return fromProperty;
        String fromEnv = System.getenv(env);
        if (fromEnv != null && !fromEnv.isBlank()) return fromEnv;
        return fallback;
    }

    public static final String ENV = value("test.env", "TEST_ENV", "dev").toLowerCase(Locale.ROOT);
    public static final String BASE_URL = normalizeBaseUrl(
            value("base.url", "BASE_URL", "http://localhost:8080/school-management-system/"));
    public static final String LOGIN_PATH = value("login.path", "LOGIN_PATH", "login");
    public static final String BROWSER = value("browser", "BROWSER", "chrome");
    public static final boolean HEADLESS = Boolean.parseBoolean(value("headless", "HEADLESS", "true"));

    public static final String E2E_USERNAME = value("e2e.username", "E2E_USERNAME", "superadmin");
    public static final String E2E_PASSWORD = value("e2e.password", "E2E_PASSWORD", "ChangeMe123!");
    public static final String STUDENT_USERNAME = value("student.username", "STUDENT_USERNAME", "student");
    public static final String STUDENT_PASSWORD = value("student.password", "STUDENT_PASSWORD", "student1234");
    public static final String TEACHER_USERNAME = value("teacher.username", "TEACHER_USERNAME", "teacher");
    public static final String TEACHER_PASSWORD = value("teacher.password", "TEACHER_PASSWORD", "teacher1234");

    private static String normalizeBaseUrl(String url) {
        return url.endsWith("/") ? url : url + "/";
    }

    public static String url(String relativePath) {
        return BASE_URL + (relativePath.startsWith("/") ? relativePath.substring(1) : relativePath);
    }
}
