/*
 * This file is part of dependency-check-gradle.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) 2015 Wei Ma. All Rights Reserved.
 */

package org.owasp.dependencycheck.gradle.extension

import org.gradle.api.file.ConfigurableFileCollection
import static org.owasp.dependencycheck.reporting.ReportGenerator.Format
import org.gradle.api.Project

/*
 * Configuration extension for the dependencyCheck plugin.
 *
 * @author Wei Ma
 * @author Jeremy Long
 */
class DependencyCheckExtension {

    DependencyCheckExtension(Project project) {
        outputDirectory = "${project.buildDir}/reports"
    }

    /**
     * The configuration extension for proxy settings.
     */
    ProxyExtension proxyExtension
    /**
     * The configuration extension that defines the location of the NVD CVE data.
     */
    CveExtension cveExtension
    /**
     * Whether the plugin should fail when errors occur.
     */
    Boolean failOnError = true
    /**
     * The configuration extension for data related configuration options.
     */
    DataExtension dataExtension
    /**
     * Set to false if the proxy does not support HEAD requests. The default is true.
     */
    Boolean quickQueryTimestamp
    /**
     * The number of hours to wait before checking for additional updates from the NVD.
     */
    Integer cveValidForHours
    /**
     * The directory where the reports will be written. Defaults to 'build/reports'.
     */
    String outputDirectory
    /**
     * Configuration for the analyzers.
     */
    AnalyzerExtension analyzerExtension
    /**
     * The path to the suppression file.
     */
    String suppressionFile
    /**
     * The list of paths to suppression files.
     */
    List<String> suppressionFiles = []
    /**
     * The path to the hints file.
     */
    String hintsFile
    /**
     * Sets whether auto-updating of the NVD CVE/CPE data is enabled.
     */
    Boolean autoUpdate

    //The following properties are not used via the settings object, instead
    // they are directly used by the check task.
    /**
     * When set to true configurations that are considered a test configuration will not be included in the analysis.
     * A configuration is considered a test configuration if and only if any of the following conditions holds:
     * <ul>
     *     <li>the name of the configuration or any of its parent configurations equals 'testCompile'</li>
     *     <li>the name of the configuration or any of its parent configurations equals 'androidTestCompile'</li>
     *     <li>the configuration name starts with 'test'</li>
     *     <li>the configuration name starts with 'androidTest'</li>
     * </ul>
     * The default value is true.
     */
    Boolean skipTestGroups = true
    /**
     * The report format to be generated (HTML, XML, CSV, JUNIT, ALL). This configuration option has
     * no affect if using this within the Site plugin unless the externalReport is set to true.
     * The default is HTML.
     */
    Format format = Format.HTML
    /**
     * The list of formats to generate to report (HTML, XML, CSV, JUNIT, ALL).
     */
    List<Format> formats = []
    /**
     * Specifies if the build should be failed if a CVSS score above a specified level is identified. The default is
     * 11 which means since the CVSS scores are 0-10, by default the build will never fail.
     */
    Float failBuildOnCVSS = 11.0
    /**
     * Specifies the CVSS score that should be considered a failure when generating a JUNIT formatted report. The default
     * is 0.0 which means all identified vulnerabilities would be considered a failure.
     */
    Float junitFailOnCVSS = 0.0
    /**
     * Displays a summary of the findings. Defaults to true.
     */
    Boolean showSummary = true
    /**
     * Names of the configurations to scan.
     *
     * This is mutually exclusive with the skipConfigurations property.
     */
    List<String> scanConfigurations = []
    /**
     * Names of the configurations to skip when scanning.
     *
     * This is mutually exclusive with the scanConfigurations property.
     */
    List<String> skipConfigurations = []
    /**
     * Paths of the projects to scan.
     *
     * This is mutually exclusive with the skipProjects property.
     */
    List<String> scanProjects = []
    /**
     * Paths of the projects to skip when scanning.
     *
     * This is mutually exclusive with the scanProjects property.
     */
    List<String> skipProjects = []
    /**
     * The artifact types that will be analyzed in the gradle build.
     */
    List<String> analyzedTypes = ['jar', 'aar', 'js', 'war', 'ear', 'zip']
    /**
     * Whether or not to skip the execution of dependency-check.
     */
    Boolean skip = false
    /**
     * A set of files or folders to scan.
     */
    List<File> scanSet
}
