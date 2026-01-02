package dev.incognity.sanitizer.engine.record;

import dev.incognity.sanitizer.severity.enums.Severity;

/**
 * Record representing the result of a scan operation.
 * 
 * @param detectorName the name of the detector that produced the result
 * @param severity     the severity level of the result
 * @param description  a description of the result
 */
public record ScanResult(String detectorName, Severity severity, String description) {

}
