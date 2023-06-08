import java.lang.Object;
import java.util.HashMap;

public class StubGetConfiguration implements IGetConfiguration {
    public StubGetConfiguration() {
        _settings = new HashMap<String, Object>();
    }
    
    public StubGetConfiguration(HashMap<String, Object> settings) {
        _settings = settings;
    }
    
    private HashMap<String, Object> _settings;
    
    public <T> T getSetting(String settingName) {
        return _settings.get(settingName);        
    };
}
