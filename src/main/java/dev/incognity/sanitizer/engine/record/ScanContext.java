package dev.incognity.sanitizer.engine.record;

import java.io.File;

/**
 * Record representing the context for a scan operation.
 * 
 * @param pluginsDirectory the directory containing plugins to be scanned
 * 
 * @author Incognity / 01/02/2026
 */
public record ScanContext(File pluginsDirectory) {

}
