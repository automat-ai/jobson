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
import com.github.jobson.jobinputs.stringarray.StringArrayInput;

import static com.github.jobson.Helpers.toJSON;
import static java.lang.String.format;

public final class ToCSVFunction implements FreeFunction {
    @Override
    public Object call(Object... args) {
        if (args.length != 1) {
            throw new RuntimeException(format("ToCSV called with %s args (expects 1)", args.length));
        } else if (!(args[0] instanceof StringArrayInput)) {
            throw new RuntimeException(format(
                    "ToCSV called with %s, should be called with a StringArrayInput (try using toJSON?)",
                    args[0].getClass().getSimpleName()));
        } else {
            return String.join("\n",((StringArrayInput)args[0]).getValues());
        }
    }
}
