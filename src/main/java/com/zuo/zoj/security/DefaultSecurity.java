package com.zuo.zoj.security;

import java.io.FileDescriptor;
import java.security.Permission;

public class DefaultSecurity extends SecurityManager {
    @Override
    public void checkDelete(String file) {
        throw new SecurityException("删除权限异常"+file);
    }

    @Override
    public void checkWrite(FileDescriptor fd) {
        throw new SecurityException("写权限异常"+fd);
    }

}
