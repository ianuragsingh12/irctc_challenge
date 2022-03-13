/*
 */
package com.example.demo.component;

/**
 *
 * @author ianur
 */
public enum Coach {
    AC_SLEEPER(60L), NON_AC_SLEEPER(60L), SEATER(120L);

    private final Long count;

    private Coach(Long count) {
        this.count = count;
    }

    public Long getCount() {
        return this.count;
    }

}
