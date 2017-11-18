/*
 * Copyright (c) 2017 Adam Kewley
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.github.jobson.specs;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class JobExpectedOutput {

    @JsonProperty
    @NotNull
    private String path;

    @JsonProperty
    private Optional<String> mimeType = Optional.empty();

    @JsonProperty
    private Optional<String> name = Optional.empty();

    @JsonProperty
    private Optional<String> description = Optional.empty();

    @JsonProperty
    private Map<String, String> metadata = new HashMap<>();


    /**
     * @deprecated Used by JSON deserializer.
     */
    public JobExpectedOutput() {}

    public JobExpectedOutput(
            String path,
            String mimeType) {
        this.path = path;
        this.mimeType = Optional.of(mimeType);
    }

    public JobExpectedOutput(
            String path,
            String mimeType,
            Optional<String> name,
            Optional<String> description,
            Map<String, String> metadata) {

        this.path = path;
        this.mimeType = Optional.of(mimeType);
        this.name = name;
        this.description = description;
        this.metadata = metadata;
    }


    public String getPath() {
        return path;
    }

    public Optional<String> getMimeType() {
        return mimeType;
    }

    public Optional<String> getName() {
        return name;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobExpectedOutput jobExpectedOutput = (JobExpectedOutput) o;

        if (path != null ? !path.equals(jobExpectedOutput.path) : jobExpectedOutput.path != null) return false;
        if (mimeType != null ? !mimeType.equals(jobExpectedOutput.mimeType) : jobExpectedOutput.mimeType != null) return false;
        if (name != null ? !name.equals(jobExpectedOutput.name) : jobExpectedOutput.name != null) return false;
        if (description != null ? !description.equals(jobExpectedOutput.description) : jobExpectedOutput.description != null)
            return false;
        return metadata != null ? metadata.equals(jobExpectedOutput.metadata) : jobExpectedOutput.metadata == null;
    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (mimeType != null ? mimeType.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
        return result;
    }
}
