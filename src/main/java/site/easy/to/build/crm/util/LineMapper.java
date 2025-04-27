package site.easy.to.build.crm.util;

public interface LineMapper<T>
{
    T mapLine(String[] line, int lineNumber) throws Exception;
}
