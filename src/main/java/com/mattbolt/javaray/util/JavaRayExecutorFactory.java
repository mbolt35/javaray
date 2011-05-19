////////////////////////////////////////////////////////////////////////////////
//
//  MATTBOLT.BLOGSPOT.COM
//  Copyright(C) 2011 Matt Bolt
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at:
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//
////////////////////////////////////////////////////////////////////////////////

package com.mattbolt.javaray.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class is used to generate {@code ExecutorService} which contain java-ray named {@code ThreadFactory}
 * implementations.
 */
public final class JavaRayExecutorFactory {

    public static ExecutorService newCachedThreadPool() {
        return Executors.newCachedThreadPool(new JavaRayThreadFactory("JavaRayPool", "JavaRayThread"));
    }

    public static ExecutorService newFixedThreadPool(int nThreads) {
        return Executors.newFixedThreadPool(nThreads, new JavaRayThreadFactory("JavaRayPool", "JavaRayThread"));
    }

    public static class JavaRayThreadFactory implements ThreadFactory {
        private final ThreadGroup group;
        private final String threadPrefix;
        private final AtomicLong threadIndex = new AtomicLong();

        public JavaRayThreadFactory(String groupName, String threadPrefix) {
            this.group = new ThreadGroup(groupName);
            this.threadPrefix = threadPrefix;
        }

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(
                group,
                runnable,
                group.getName() + "_" + threadPrefix + "_" + threadIndex.getAndIncrement());
        }
     }
}
