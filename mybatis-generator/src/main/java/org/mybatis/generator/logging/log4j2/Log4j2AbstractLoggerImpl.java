/**
 * Copyright 2006-2017 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.mybatis.generator.logging.log4j2;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.mybatis.generator.logging.Log;
import org.mybatis.generator.logging.LogFactory;

/**
 * @author Eduardo Macarron
 */
public class Log4j2AbstractLoggerImpl implements Log {

    private static Marker MARKER = MarkerManager.getMarker(LogFactory.MARKER);

    private static final String FQCN = Log4j2Impl.class.getName();

    private LogExtendedLoggerWrapper log;

    public Log4j2AbstractLoggerImpl(AbstractLogger abstractLogger) {
        this.log = new LogExtendedLoggerWrapper(abstractLogger, abstractLogger.getName(),
                abstractLogger.getMessageFactory());
    }

    @Override
    public boolean isDebugEnabled() {
        return this.log.isDebugEnabled();
    }

    @Override
    public void error(String s, Throwable e) {
        this.log.logIfEnabledExtended(FQCN, Level.ERROR, MARKER, new SimpleMessage(s), e);
    }

    @Override
    public void error(String s) {
        this.log.logIfEnabledExtended(FQCN, Level.ERROR, MARKER, new SimpleMessage(s), null);
    }

    @Override
    public void debug(String s) {
        this.log.logIfEnabledExtended(FQCN, Level.DEBUG, MARKER, new SimpleMessage(s), null);
    }

    @Override
    public void warn(String s) {
        this.log.logIfEnabledExtended(FQCN, Level.WARN, MARKER, new SimpleMessage(s), null);
    }

}
