/**
 * Copyright (C) 2013 the original author or authors.
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
 */

package conf;

import java.util.HashSet;
import java.util.Set;

public enum AppProperties {
    UMA_CACHE_SERVER_ENABLED("uma.cache.server.enabled");

    private static Set<String> keys;
    private String key;

    private AppProperties(String key) {
        init(key);
    }

    private void init(String key) {
        if (keys == null) {
            keys = new HashSet<>();
        }
        if (keys.contains(key)) {
            throw new IllegalArgumentException("key <" + key
                    + "> is already used");
        }
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
