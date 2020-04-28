package com.cacoota.porter.plugin.file;

import java.util.List;

public abstract class AbstractFileFilter implements IFileFilter {

    @Override
    public boolean filter(SFile file) {
        return true;
    }

    abstract public List<IFileFilter> filters();

    public boolean check(SFile file) {
        return check(file, filters().toArray(new IFileFilter[]{}));
    }

    public boolean check(SFile file, IFileFilter... filters) {
        for (IFileFilter filter : filters) {
            if (!filter.filter(file)) {
                return false;
            }
        }
        return true;
    }

}
