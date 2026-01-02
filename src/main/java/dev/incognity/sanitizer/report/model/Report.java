package dev.incognity.sanitizer.report.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import dev.incognity.sanitizer.engine.record.ScanResult;
import lombok.Getter;

/**
 * Model representing a report containing scan results.
 * 
 * @author Incognity / 01/02/2026
 */
public class Report {

  @Getter
  private final List<ScanResult> results = new ArrayList<>();

  /**
   * Add a scan result to the report
   * 
   * @param result the scan result to add
   */
  public void add(@Nonnull ScanResult result) {
    results.add(result);
  }
}
