/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gcloud.spi;

import com.google.api.services.compute.model.DiskType;
import com.google.api.services.compute.model.License;
import com.google.api.services.compute.model.MachineType;
import com.google.api.services.compute.model.Region;
import com.google.api.services.compute.model.Zone;
import com.google.gcloud.compute.ComputeException;

import java.util.Map;

public interface ComputeRpc {

  // These options are part of the Google Compute Engine query parameters
  enum Option {
    FIELDS("fields"),
    MAX_RESULTS("maxResults"),
    PAGE_TOKEN("pageToken"),
    FILTER("filter");

    private final String value;

    Option(String value) {
      this.value = value;
    }

    public String value() {
      return value;
    }

    @SuppressWarnings("unchecked")
    <T> T get(Map<Option, ?> options) {
      return (T) options.get(this);
    }

    String getString(Map<Option, ?> options) {
      return get(options);
    }

    Long getLong(Map<Option, ?> options) {
      return get(options);
    }

    Boolean getBoolean(Map<Option, ?> options) {
      return get(options);
    }
  }

  class Tuple<X, Y> {

    private final X x;
    private final Y y;

    private Tuple(X x, Y y) {
      this.x = x;
      this.y = y;
    }

    public static <X, Y> Tuple<X, Y> of(X x, Y y) {
      return new Tuple<>(x, y);
    }

    public X x() {
      return x;
    }

    public Y y() {
      return y;
    }
  }

  /**
   * Returns the requested disk type or {@code null} if not found.
   *
   * @throws ComputeException upon failure
   */
  DiskType getDiskType(String zone, String diskType, Map<Option, ?> options)
      throws ComputeException;

  /**
   * Lists the disk types in the provided zone available to the current project.
   *
   * @throws ComputeException upon failure
   */
  Tuple<String, Iterable<DiskType>> listDiskTypes(String zone, Map<Option, ?> options)
      throws ComputeException;

  /**
   * Lists all disk types available to the current project.
   *
   * @throws ComputeException upon failure
   */
  Tuple<String, Iterable<DiskType>> listDiskTypes(Map<Option, ?> options) throws ComputeException;

  /**
   * Returns the requested machine type or {@code null} if not found.
   *
   * @throws ComputeException upon failure
   */
  MachineType getMachineType(String zone, String diskType, Map<Option, ?> options)
      throws ComputeException;

  /**
   * Lists the machine types in the provided zone available to the current project.
   *
   * @throws ComputeException upon failure
   */
  Tuple<String, Iterable<MachineType>> listMachineTypes(String zone, Map<Option, ?> options)
      throws ComputeException;

  /**
   * Lists all machine types available to the current project.
   *
   * @throws ComputeException upon failure
   */
  Tuple<String, Iterable<MachineType>> listMachineTypes(Map<Option, ?> options)
      throws ComputeException;

  /**
   * Returns the requested region or {@code null} if not found.
   *
   * @throws ComputeException upon failure
   */
  Region getRegion(String region, Map<Option, ?> options) throws ComputeException;

  /**
   * Lists the regions available to the current project.
   *
   * @throws ComputeException upon failure
   */
  Tuple<String, Iterable<Region>> listRegions(Map<Option, ?> options) throws ComputeException;

  /**
   * Returns the requested zone or {@code null} if not found.
   *
   * @throws ComputeException upon failure
   */
  Zone getZone(String zone, Map<Option, ?> options) throws ComputeException;

  /**
   * Lists the zones available to the current project.
   *
   * @throws ComputeException upon failure
   */
  Tuple<String, Iterable<Zone>> listZones(Map<Option, ?> options) throws ComputeException;

  /**
   * Returns the requested license or {@code null} if not found.
   *
   * @throws ComputeException upon failure
   */
  License getLicense(String project, String license, Map<Option, ?> options)
      throws ComputeException;
}
