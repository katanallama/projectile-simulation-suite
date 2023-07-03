package com.pss.interfaces;
import com.pss.enums.Settings;

public interface IGetConfiguration {
    public <T> T getSetting(Settings setting);
}
