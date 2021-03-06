package net.apnic.whowas.rdap;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A top level RDAP response, containing server notices and conformance.
 */
public class TopLevelObject {
    private static final Set<String> SERVER_CONFORMANCE = Stream.of(
            "rdap_level_0",
            "history_version_0"
    ).collect(Collectors.toSet());

    private final Set<String> rdapConformance;
    private final List<Notice> notices;
    private final Object object;

    private TopLevelObject(Set<String> rdapConformance, List<Notice> notices, Object object) {
        this.rdapConformance = rdapConformance;
        this.notices = notices;
        this.object = object;
    }

    public Set<String> getRdapConformance() {
        return rdapConformance;
    }

    @JsonUnwrapped
    public Object getObject() {
        return object;
    }

    public List<Notice> getNotices() {
        return notices;
    }

    /**
     * Construct a top level object using server defaults
     *
     * @param object The object to wrap
     * @return A top level object using server defaults
     */
    public static TopLevelObject of(Object object, Notice notice) {
        List<Notice> notices = new ArrayList<>();

        if (notice != null) {
            notices.add(notice);
        }

        return new TopLevelObject(
                SERVER_CONFORMANCE,
                notices,
                object);
    }
}
