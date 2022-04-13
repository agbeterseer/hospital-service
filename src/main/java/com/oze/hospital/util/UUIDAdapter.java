package com.oze.hospital.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.UUID;

public class UUIDAdapter extends XmlAdapter<String, UUID> {

    @Override
    public UUID unmarshal(String v) throws Exception {
        return UUID.fromString(v);
    }

    @Override
    public String marshal(UUID v) throws Exception {
        return v.toString();
    }
}