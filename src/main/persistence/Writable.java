package persistence;

import org.json.JSONObject;

// JSON Serialization Demo code referenced
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
