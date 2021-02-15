package calculator.core;

import java.util.Map;

public interface ViewManager {
    void changeView(int value);
    void changeView(int value, Map<String,String> bundle);
}
