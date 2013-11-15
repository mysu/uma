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
