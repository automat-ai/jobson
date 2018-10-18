/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.github.jobson.scripting.functions;

import com.github.jobson.scripting.FreeFunction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.String.format;

public final class ToFileFunction implements FreeFunction {

    private final Path workingDir;


    public ToFileFunction(Path workingDir) {
        this.workingDir = workingDir;
    }

    private Object toFile(final String fileContent, final Path path) throws IOException {
        Files.write(path, fileContent.getBytes());
        return path.toAbsolutePath().toString();
    }


    @Override
    public Object call(Object... args) {
        try {
            Path path = null;
            switch (args.length) {
            case 2:
                if (!(args[1] instanceof String)) {
                    throw new RuntimeException(format(
                            "asFile called with %s, should be called with a string",
                            args[1].getClass().getSimpleName()));
                }
                path = Paths.get((String)args[1]);

            case 1:
                if (!(args[0] instanceof String)) {
                    throw new RuntimeException(format(
                            "asFile called with %s, should be called with a string (try using toJSON?)",
                            args[0].getClass().getSimpleName()));
                }
                if (args.length == 1) {
                    path = Files.createTempFile(workingDir, "request", "");
                }
                return toFile((String)args[0], path);

            case 0:
            default:
                throw new RuntimeException(format("asFile called with %s args (expects 1 or 2)", args.length));
            }
        } catch (IOException ex) {
            throw new RuntimeException("Could not create an input file.", ex);
        }
}
}
