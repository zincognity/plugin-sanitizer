package dev.incognity.sanitizer.engine.registry;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import dev.incognity.sanitizer.detector.interfaces.Detector;
import lombok.Getter;

/**
 * Registry for managing detectors within the Sanitizer engine.
 * 
 * @author Incognity / 01/02/2026
 */
public class EngineRegistry {

  @Getter
  private final List<Detector> detectors = new ArrayList<>();

  /**
   * Add a detector to the registry
   * 
   * @param detector the detector to add
   * @return the EngineRegistry instance
   */
  public EngineRegistry add(@Nonnull Detector detector) {
    detectors.add(detector);
    return this;
  }

  public EngineRegistry addAll(@Nonnull Detector... detectors) {
    if (detectors.length == 0) {
      return this;
    }

    for (Detector detector : detectors) {
      add(detector);
    }
    return this;
  }
}
